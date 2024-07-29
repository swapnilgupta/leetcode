package graph;

import java.util.ArrayList;
import java.util.List;

public class MinJobCompletionTimeGFG {

	// https://practice.geeksforgeeks.org/problems/minimum-time-taken-by-each-job-to-be-completed-given-by-a-directed-acyclic-graph/1
	public static int[] minimumTime(int n, int m, int[][] edges) {
		int[] in = new int[n];
		List<List<Integer>> adj = new ArrayList<>(n);
		List<Integer> bfs = new ArrayList<>();
		// Initialize graph
		// a_i <--- b_i
		// maintain in_degree for topological sorting
		for (int i = 0; i < n; ++i) {
			adj.add(new ArrayList<>());
		}
		for (int[] edge : edges) {
			++in[edge[1] - 1];
			adj.get(edge[0] - 1).add((edge[1] - 1));
		}

		// First take degree zero(independent courses)
		for (int i = 0; i < n; ++i) {
			if (in[i] == 0) {
				bfs.add(i);
			}
		}

		// Topological sorting (Kahn's Algorithm)
		int count = 0;
		int[] ans = new int[n];
		while (!bfs.isEmpty()) {
			++count;
			int sz = bfs.size();
			while (sz-- > 0) {
				int u = bfs.remove(0);
				ans[u] = count;
				for (int v : adj.get(u)) {
					if (--in[v] == 0) {
						bfs.add(v);
					}
				}
			}
		}
		return ans;
	}

	// driver code for above
	public static void main(String[] args) {
		int n = 10;
		int m = 13;
		int[][] edges = {{1, 3}, {1, 4}, {1, 5}, {2, 3}, {2, 8}, {2, 9}, {3, 6}, {4, 6}, {4, 8},
			{5, 8}, {6, 7}, {7, 8}, {8, 10}};

		int[] ans = minimumTime(n, m, edges);

		for (int i : ans) {
			System.out.print(i + " ");
		}
	}

}
