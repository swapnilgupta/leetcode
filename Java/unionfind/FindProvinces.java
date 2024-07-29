package unionfind;

import java.util.HashSet;
import java.util.Set;

public class FindProvinces {

	class UnionFind {

		public int[] parent;

		UnionFind(int n) {
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

		public void union(int u, int v) {
			int pu = find(u);
			int pv = find(v);
			if (pu != pv) {
				parent[pu] = pv;
			}
		}
	}

	public int findCircleNum(int[][] isConnected) {
		int n = isConnected.length;
		UnionFind uf = new UnionFind(n);
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				if (isConnected[i][j] == 1) {
					uf.union(i, j);
				}
			}
		}

		Set<Integer> provinces = new HashSet<>();
		for (int i = 0; i < n; ++i) {
			provinces.add(uf.find(i));
		}
		return provinces.size();
	}


	// driver code for the above function
	public static void main(String[] args) {
		int[][] isConnected = {{1, 0, 0, 1},
			{0, 1, 1, 0},
			{0, 1, 1, 1},
			{1, 0, 1, 1}};

		FindProvinces solution = new FindProvinces();
		int provinces = solution.findCircleNum(isConnected);

		System.out.println("Number of Provinces: " + provinces);
	}
}
