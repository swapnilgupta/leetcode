package unionfind;

class UnionFind {

	private final int[] parent;

	public UnionFind(int n) {
		parent = new int[n];
		for (int i = 0; i < n; ++i) {
			parent[i] = i;
		}
	}

	public int findIterative(int u) {
		while (parent[u] != u) {
			u = parent[u];
		}
		return u;
	}

	public int findRecursive(int u) {
		if (parent[u] != u) {
			return findRecursive(parent[u]);
		}
		return u;
	}

	public void union(int u, int v) {
		int pu = findRecursive(u);
		int pv = findRecursive(v);
		if (pu != pv) {
			parent[pu] = pv;
		}
	}
}

class UnionFindPathCompression {

	private final int[] parent;

	UnionFindPathCompression(int n) {
		parent = new int[n];
		for (int i = 0; i < n; ++i) {
			parent[i] = i;
		}
	}

	public int find(int u) {
		if (parent[u] != u) {
			parent[u] = find(parent[u]);
		}
		return parent[u];
	}

	public int findIterative(int u) {
		int root = u;

		// Find the root of the tree (representative element)
		while (root != parent[root]) {
			root = parent[root];
		}

		// Compress the path leading back to the root, helping future operations be faster.
		while (u != root) {
			int nextNode = parent[u];
			parent[u] = root;
			u = nextNode;
		}

		return root;
	}

	public void union(int u, int v) {
		int pu = find(u);
		int pv = find(v);
		if (pu != pv) {
			parent[pu] = pv;
		}
	}

}

/**
 * This class implements the Union-Find data structure using the Rank and Path Compression
 * techniques.
 * </br>
 * The Union-Find data structure provides an efficient way to keep track of a partition of a set
 * into disjoint subsets. It is commonly used to solve problems with disjoint sets, such as
 * implementing disjoint-set forests or finding connected components in a graph.
 * </br>
 * The Rank technique is used to optimize the performance of the Union operation by merging smaller
 * trees into larger trees. This helps to keep the overall height of the trees relatively small,
 * which leads to better performance.
 * </br>
 * The Path Compression technique is used to optimize the performance of the Find operation by
 * compressing the path from a node to its root. This helps to flatten the tree, reducing future
 * traversals and improving overall performance.
 * </br>
 * The class provides methods to perform the Find and Union operations on elements, as well as
 * initializing the data structure with a specific number of elements.
 */
class UnionFindByRankAndPathCompression {

	private final int[] parent;
	private final int[] rank;

	UnionFindByRankAndPathCompression(int n) {
		parent = new int[n];
		rank = new int[n];
		for (int i = 0; i < n; ++i) {
			parent[i] = i;
			rank[i] = 0;
		}
	}

	public int findRecursive(int u) {
		if (parent[u] != u) {
			parent[u] = findRecursive(parent[u]); // Compress every node on the path
		}
		return parent[u];
	}

	public void union(int u, int v) {
		int pu = findRecursive(u);
		int pv = findRecursive(v);
		if (pu != pv) {
			if (rank[pu] > rank[pv]) {
				parent[pv] = pu;
			} else {
				parent[pu] = pv;
				if (rank[pu] == rank[pv]) {
					rank[pv]++; // by height
				}
			}
		}
	}
}
