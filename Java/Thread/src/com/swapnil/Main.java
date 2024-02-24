package com.swapnil;

import java.util.*;

public class Main {
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

	class Solution {
		public int maxDepthUtil(TreeNode root, int depth) {
			if (root == null) return depth;
			int ld = 0, rd = 0;
			if (root.left != null)
				ld = maxDepthUtil(root.left, depth + 1);
			if (root.right != null)
				rd = maxDepthUtil(root.right, depth + 1);
			return Math.max(ld, rd);
		}

		public int maxDepth(TreeNode root) {
			return maxDepthUtil(root, 0);
		}
	}

	static class SumNumbersSolution {
		public int sumNumbers(TreeNode root) {
			return sumNumbersUtils(root, new ArrayList<>(), new int[1]);
		}

		private static int sumNumbersUtils(TreeNode root, List<Integer> sum, int[] totalSum) {
			if (root == null) {
				return 0;
			}
			sum.add(root.val);
			if (root.left == null && root.right == null) {
				for (int i = 0; i < sum.size(); i++) {
					totalSum[0] += (int) (Math.pow(10, sum.size() - 1 - i)) * sum.get(i);
				}
			}

			sumNumbersUtils(root.left, sum, totalSum);

			sumNumbersUtils(root.right, sum, totalSum);
			sum.remove(sum.size() - 1);

			return totalSum[0];
		}
	}

	class SolutionCanFinish {
		public boolean canFinish(int n, int[][] pre) {
			// Kahn's Algorithm
			//
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

		public boolean isBipartite(int[][] graph) {
			int n = graph.length;
			int[] color = new int[n];
			Arrays.fill(color, -1);
			Queue<Integer> q = new LinkedList<>();
			int[] visited = new int[n];

			for (int i = 0; i < n; ++i) {
				if (visited[i] == 1) continue;
				color[i] = 1;
				visited[i] = 1;
				q.add(i);

				while (!q.isEmpty()) {
					int cur = q.remove();
					int nc = color[cur] == 1 ? 0 : 1;
					for (var node : graph[cur]) {
						if (color[node] != -1) {
							if (color[node] == color[cur]) return false;
						} else {
							color[node] = nc;
							q.offer(node);
							visited[node] = 1;
						}
					}
				}
			}
			return true;
		}
	}

	;

	public static void main(String[] args) {
		int[] num = new int[]{1, 2, 0, 0};
		int k = 346899009;
		SumNumbersSolution solution = new SumNumbersSolution();
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);

		System.out.println("sum numbers: " + solution.sumNumbers(root));

	}
}