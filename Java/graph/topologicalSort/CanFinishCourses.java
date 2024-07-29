package graph.topologicalSort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class CanFinishCourses {

	public boolean canFinish(int n, int[][] pre) {
		// Kahn's Algorithm | Topological Sort
		Map<Integer, List<Integer>> adj = new HashMap<>(); // adjacency list
		int[] degree = new int[n]; // in-degree count
		Queue<Integer> queue = new LinkedList<>();

		// Create Adjacency List
		for (int[] e : pre) {
			adj.computeIfAbsent(e[1], k -> new ArrayList<>()).add(e[0]);
			++degree[e[0]];
		}

		// Initialize BFS queue by adding vertices with zero in-degree
		for (int i = 0; i < n; ++i) {
			if (degree[i] == 0) {
				queue.add(i);
			}
		}

		// Process the queue using BFS (Topological sorting)
		int processedCourses = 0;
		while (!queue.isEmpty()) {
			int u = queue.poll();
			processedCourses++;
			for (int v : adj.getOrDefault(u, new ArrayList<>())) {
				if (--degree[v] == 0) {
					queue.add(v);
				}
			}
		}

		// If processed courses equals the total number of courses, return true
		return processedCourses == n;
	}

	// driver code for the above function
	public static void main(String[] args) {
		int n = 2;
		int[][] pre = {{1, 0}};
		CanFinishCourses cfc = new CanFinishCourses();
		System.out.println(cfc.canFinish(n, pre)); // true

		pre = new int[][]{{1, 0}, {0, 1}};
		System.out.println(cfc.canFinish(n, pre)); // false
	}
}
