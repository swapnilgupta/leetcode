#include <bits/stdc++.h>
using namespace std;
typedef pair<int, int> pare;
class Graph {
public:
  int V;
  list<pair<int, int>> *adj;
  // constructor for initializing graph
  Graph(int V) {
    this->V = V;
    this->adj = new list<pare>[V];
  }

  void addEdge(int u, int v, int w) {
    adj[u].push_back({v, w});
    adj[v].push_back({u, w});
  }

  void MSTprim() {
    int src = 0;
    vector<bool> inMST(V, false);
    // things we need to take care
    // create a vector to hold the cost of each vertex
    vector<int> cost(V, INT_MAX);
    // create a vector to hold the parent;
    cost[src] = 0;
    vector<int> parent(V, -1);
    parent[src] = 0;
    // make a min priority queue
    priority_queue<pare, vector<pare>, greater<pare>> pq;
    // initialize the priority queue
    pq.push(make_pair(0, src));
    while (!pq.empty()) {
      int u = pq.top().second;
      pq.pop();
      inMST[u] = true;
      // for each adjacent of the u fo the checking
      for (auto &e : adj[u]) {
        int v = e.first;
        int weight = e.second;
        if (inMST[v] == false && cost[v] > weight) {
          // Updating key of v
          cost[v] = weight;
          pq.push({cost[v], v});
          parent[v] = u;
        }
      }
    }
    for (int i = 1; i < V; ++i)
      printf("%d - %d\n", parent[i], i);
  }
};

int main() {
  int V = 9;
  Graph g(V);
  g.addEdge(0, 1, 4);
  g.addEdge(0, 7, 8);
  g.addEdge(1, 2, 8);
  g.addEdge(1, 7, 11);
  g.addEdge(2, 3, 7);
  g.addEdge(2, 8, 2);
  g.addEdge(2, 5, 4);
  g.addEdge(3, 4, 9);
  g.addEdge(3, 5, 14);
  g.addEdge(4, 5, 10);
  g.addEdge(5, 6, 2);
  g.addEdge(6, 7, 1);
  g.addEdge(6, 8, 6);
  g.addEdge(7, 8, 7);
  g.MSTprim();
}
