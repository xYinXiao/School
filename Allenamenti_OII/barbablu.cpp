#include <stdio.h>
#include <assert.h>
#include <vector>
#include <algorithm>
#include <set>
#include <limits.h>

using namespace std;

vector<int> isAria(31, 0);

int nCabine, nCorridoi, tesoro, nAria;

struct edge {
    int to, length;
};

struct Cmp {
    bool operator()(const pair<int, pair<int, int> >& a, const pair<int, pair<int, int> >&b) {
        return a.first < b.first;
    }
};

int dijkstra(const vector< vector<edge> >& graph, int sorgente, int destinazione) {
    vector<int> distanze_minime(graph.size() + 1, INT_MAX);
    set< pair<int, pair<int, int> >, Cmp> nodi_attivi;
    int ossigeno = 20;

    distanze_minime[sorgente] = 0;
    nodi_attivi.insert( {0, {sorgente, ossigeno}} );

    set< pair<int, pair<int, int> >, Cmp>::iterator it;

    while(!nodi_attivi.empty()) {
        int u = nodi_attivi.begin()->second.first;
        if (u == destinazione)
            return distanze_minime[u];

        it = nodi_attivi.find({distanze_minime[u], {u, ossigeno} });
        ossigeno = (*it).second.second;

        nodi_attivi.erase(nodi_attivi.begin());

        for(edge dge : graph[u]) {
            if ((distanze_minime[dge.to] > distanze_minime[u] + dge.length) && (ossigeno - dge.length > 0)) {
                nodi_attivi.erase({distanze_minime[dge.to], {dge.to, ossigeno} });
                distanze_minime[dge.to] = distanze_minime[u] + dge.length;
                nodi_attivi.insert({distanze_minime[dge.to], {dge.to, ((isAria[dge.to]) ? 20 : ossigeno - dge.length)} });
            }
        }
    }
    return INT_MAX;
}

int main() {
    FILE *in, *out;
    int i;
    vector< vector <edge> > graph(31);


    in = fopen("input.txt", "r");
    out = fopen("output.txt", "w");

    fscanf(in, "%d%d%d%d", &nCabine, &nCorridoi, &tesoro, &nAria);
    int a, b, peso;

    for(i = 0;i < nAria;i++) {
        fscanf(in, "%d", &a);
        isAria[a] = 1;
    }
    for(i = 0;i < nCorridoi;i++) {
        fscanf(in, "%d%d%d", &a, &b, &peso);
        graph[a].push_back({b, peso});
        graph[b].push_back({a, peso});
    }

    int dist = dijkstra(graph, 1, tesoro);
    fprintf(out, "%d", ((dist != INT_MAX) ? dist : -1));

    fclose(in);
    fclose(out);
    return 0;
}
