package unionfind;

class UnionFind {
    private int[] parent;

    public UnionFind(int n) {
        parent = new int[n];
        for(int i = 0; i < n; ++i) {
            parent[i] = i;
        }
    }

    public int findIterative(int u) {
        while(parent[u] != u) {
            u = parent[u];
        }
        return u;
    }

    public int findRecursive(int u) {
        if(parent[u] != u) {
            return findRecursive(parent[u]);
        }
        return u;
    }

    public void union(int u, int v) {
        int pu = findRecursive(u);
        int pv = findRecursive(v);
        if(pu != pv) {
            parent[pu] = pv;
        }
    }
}

class UnionFindPathCompression {
    private final int[] parent;

    UnionFindPathCompression(int n) {
        parent = new int[n];
        for(int i = 0; i < n; ++i) {
            parent[i] = i;
        }
    }

    public int find(int u) {
        if(parent[u] != u) {
            parent[u] = find(parent[u]);
        }
        return parent[u];
    }

    public void union(int u, int v) {
        int pu = find(u);
        int pv = find(v);
        if(pu != pv) {
            parent[pu] = pv;
        }
    }

}

class UnionFindByRankAndPathCompression {
    private final int[] parent;
    private final int[] rank;

    UnionFindByRankAndPathCompression(int n) {
        parent = new int[n];
        rank = new int[n];
        for(int i = 0; i < n; ++i) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int u) {
        if(parent[u] != u) {
            parent[u] = find(parent[u]); // Compress every node on the path
        }
        return parent[u];
    }

    public void union(int u, int v) {
        int pu = find(u);
        int pv = find(v);
        if(pu != pv) {
            if(rank[pu] > rank[pv]) {
                parent[pv] = pu;
            } else {
                parent[pu] = pv;
                if(rank[pu] == rank[pv]) {
                    rank[pv]++;
                }
            }
        }
    }
}
