package graph.topologicalSort;

import java.util.ArrayList;

public class CanFinishCourses {
	public boolean canFinish(int n, int[][] pre) {
		// Kahn's Algorithm
		ArrayList<Integer>[] adj = new ArrayList[n];
		int[] degree = new int[n];
		ArrayList<Integer> bfs = new ArrayList<>();

		// 1. Making the adjacency list
		// a_i <-- b_i
		// 2. maintain in_degree
		for (int i = 0; i < n; ++i) adj[i] = new ArrayList<>();
		for (int[] e : pre) {
			adj[e[1]].add(e[1]);
			++degree[e[1]];
		}

		// First take degree zero(independent courses)
		for (int i = 0; i < n; ++i)
			if (degree[i] == 0) bfs.add(i);

		// Topological Sorting (Kahn's Algorithm)
		for (int i = 0; i < bfs.size(); ++i) {
			int u = bfs.get(i);
			for (var v : adj[u]) {
				if (--degree[v] == 0) bfs.add(v);
			}
		}

		// If you could finish up all courses then size will be n.
		return bfs.size() == n;
	}
}
