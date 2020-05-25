//Dijstra's implementation
#include<bits/stdc++.h>
using namespace std;
class Graph {
	  public:
     int V;
     list<pair<int,int>>*adj;
     Graph(int V){
    	this->V = V;
    	adj = new list<pair<int,int>> [V];
     }
     void addEdge(int u,int v,int w)
     {
       adj[u].push_back({v,w});
       adj[v].push_back({u,w});
     }

    void Dijstra(int src)
    {
        vector<int> cost(V,INT_MAX);
    	vector<int> parent(V,-1);
    	vector<int> visited(V,0); // visited if 1 unvisited if 0;
    	priority_queue<pair<int,int> , vector< pair<int,int> > , greater<pair<int,int>>> pq;
    	pq.push({0,src});
    	cost[src]=0;
    	while(!pq.empty())
    	{
           int u = pq.top().second;
           int w = pq.top().first;
           pq.pop();
           visited[u]=1;
           for(auto c:adj[u])
           {
           	if(!(visited[c.first]) && (cost[c.first] > cost[u]+ c.second))
           	{
           		cost[c.first]=cost[u]+c.second;
                parent[c.first] = u;
                pq.push(make_pair(cost[c.first],c.first));
            }
           }
       }

        for (int i = 0; i < V; ++i)
       	printf("%d - %d\n", i, cost[i]);
    }
};

int main(){
	int V = 9;
  Graph g(V);
  g.addEdge(0,1,4);
  g.addEdge(0,7,8);
  g.addEdge(1,2,8);
  g.addEdge(1,7,11);
  g.addEdge(2,8,2);
  g.addEdge(2,5,4);
  g.addEdge(2,3,7);
  g.addEdge(3,4,9);
  g.addEdge(3,5,14);
  g.addEdge(4,5,10);
  g.addEdge(5,6,2);
  g.addEdge(6,7,1);
  g.addEdge(6,8,6);
  g.addEdge(7,8,7);

  int src = 0;
  g.Dijstra(src);
 }
