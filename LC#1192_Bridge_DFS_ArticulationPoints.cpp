class Solution {
public:
    vector<vector<int>> bridges;
    vector<vector<int>> neighbors;
    vector<int> rank;
    vector<int> low;
    
    void buildGraph(int n, vector<vector<int>>& connections) {
        neighbors.resize(n);
        for(auto& edge : connections) {
            neighbors[edge[0]].push_back(edge[1]);
            neighbors[edge[1]].push_back(edge[0]);
        }
    }
    
    void dfs(int node, int parent, int& r) {
        // If node is visited then return
        if(rank[node] > 0) return ;
        
        rank[node] = low[node] = r++;
        
        for(int neighbor : neighbors[node]) {
            // if reaching to starting node in dfs return
            if(neighbor == parent) continue;
            // unvisited neighbor should be recursed
            if(rank[neighbor] == 0) dfs(neighbor, node, r);
            // update low link
            low[node] = min(low[node], low[neighbor]);
        }
        
        // bridges adding
        if(parent != -1 && low[node] > rank[parent])
            bridges.push_back({node, parent});
    }
    
    vector<vector<int>> criticalConnections(int n, vector<vector<int>>& connections) {
        ios_base::sync_with_stdio(false);
        cin.tie(NULL);
        cout.tie(NULL);
        rank.resize(n);
        low.resize(n);
        buildGraph(n, connections);
        int r = 1;
        
        dfs(0, -1, r);
        return bridges;
    }
};
