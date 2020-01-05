class Solution {
public:
    vector<vector<int>> bridges; // for storing the answer [low-link value of node > rank of parent]
    vector<vector<int>> neighbors; // neighbor based graph storage
    vector<int> rank; // rank: In order the dfs node is visited (starting node rank is 1)
    vector<int> low; // Low-link value: the lowest rank that a node can visit during DFS.
    
    // Building the graph
    void buildGraph(int n, vector<vector<int>>& connections) {
        neighbors.resize(n);
        for(auto& edge : connections) {
            neighbors[edge[0]].push_back(edge[1]);
            neighbors[edge[1]].push_back(edge[0]);
        }
    }
    
    // Finding bridges...
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
        rank.resize(n); // resize initializes by 0
        low.resize(n);
        buildGraph(n, connections);
        int r = 1; // let's start with rank 1
        
        dfs(0, -1, r);
        return bridges;
    }
};
