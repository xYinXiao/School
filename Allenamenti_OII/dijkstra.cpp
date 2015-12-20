#include <vector>
#include <set>
#include <climits>

int dijkstra(const vector< vector<edge> > &graph, int source, int target) {
    vector<int> min_distance( graph.size(), INT_MAX);
    min_distance[ source ] = 0;
    set< pair<int,int> > active_vertices;
    active_vertices.insert( {0,source} );

    while (!active_vertices.empty()) {
        int where = active_vertices.begin()->second;
        if (where == target) return min_distance[where];

        active_vertices.erase( active_vertices.begin() );
        for (auto edge : graph[where])
            if (min_distance[edge.to] > min_distance[where] + edge.length) {
                active_vertices.erase( { min_distance[edge.to], edge.to } );
                min_distance[edge.to] = min_distance[where] + edge.length;
                active_vertices.insert( { min_distance[edge.to], edge.to } );
            }
    }
    return INT_MAX;
}