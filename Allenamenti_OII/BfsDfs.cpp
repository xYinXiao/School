#include <cstdio>
#include <vector>
#include <algorithm>
#include <stack>
#include <queue>

bool cmp(const std::pair <int, int>& lhs, const std::pair <int, int>& rhs) {
    return (lhs.first > rhs.first);
}

int main() {
    FILE *in;
    in = fopen("input.txt", "r");

    int i, j;
    int nRighe = 100, nColonne = 100;
    int n;

    std::vector< std::pair <int, int> > edgeList;
	std::vector< std::vector <int> > adjList(100);
	int matriceAdiacenza[100][100];
	int da, a;

    for(i = 0;i < nRighe;i++)
        for(j = 0;j < nColonne;j++)
            matriceAdiacenza[i][j] = 0;
    fscanf(in, "%d", &n);
    for(i = 0;i < n;i++) {
        fscanf(in, "%d%d", &da, &a);
        edgeList.push_back(std::make_pair(da, a));
    }
    std::sort(edgeList.begin(), edgeList.end(), cmp);
    std::vector<int> visitato(100, 0);

    for(i = 0;i < n;i++) {
        adjList[edgeList[i].first].push_back(edgeList[i].second);
        adjList[edgeList[i].second].push_back(edgeList[i].first);
    }
    std::stack<int> dfs;
    std::queue<int> bfs;


    //Teoricamente è una DFS
    int sorgente;
    dfs.push(sorgente);
    int u;
    printf("Inserisci il nodo sorgente: ");
    scanf("%d", &sorgente);
    printf("DFS: ");
    while(!dfs.empty()) {
        u = dfs.top();
        dfs.pop();

        if (!visitato[u]) {
            visitato[u] = 1;
            printf("%d ", u);
            std::vector<int>::iterator first = adjList[u].begin(), last = adjList[u].end() - 1;
            while(first <= last) {
                dfs.push(*last);
                last--;
            }
        }
    }
    for(i = 0;i < n;i++) {
        adjList[edgeList[i].first].push_back(edgeList[i].second);
        adjList[edgeList[i].second].push_back(edgeList[i].first);
    }
    //Teoricamente è una BFS
    printf("\nBFS: ");
    bfs.push(sorgente);
    visitato.clear();
    visitato.assign(100, 0);
    while(!bfs.empty()) {
        u = bfs.front();
        bfs.pop();

        if (!visitato[u]) {
            visitato[u] = 1;
            printf("%d ", u);

            std::vector<int>::iterator first = adjList[u].begin(), last = adjList[u].end() - 1;

            while(first <= last) {
                bfs.push(*last);
                last--;
            }
        }
    }


	fclose(in);
	return 0;
}
