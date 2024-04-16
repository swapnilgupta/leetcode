package graph.dfs;

import java.util.*;

public class minReorderEdges {

	private int dfs(Map<Integer, List<Integer>> adjList, boolean[] visited, int u) {
		int count = 0;
		visited[u] = true;
		for (int v : adjList.get(u)) {
			if (!visited[Math.abs(v)]) {
				count += dfs(adjList, visited, Math.abs(v)) + (v > 0 ? 1 : 0);
			}
		}
		return count;
	}

	public int minReorder(int n, int[][] connections) {
		Map<Integer, List<Integer>> adjList = new HashMap<>();
		for (int[] edge : connections) {
			int u = edge[0], v = edge[1];
			adjList.putIfAbsent(u, new ArrayList<>());
			adjList.putIfAbsent(v, new ArrayList<>());
			adjList.get(u).add(v);
			adjList.get(v).add(-u);
		}

		return dfs(adjList, new boolean[n], 0);
	}
}
