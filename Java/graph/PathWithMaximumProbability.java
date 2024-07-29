package graph;

import java.util.PriorityQueue;

public class PathWithMaximumProbability {

	public static class Pair {

		int v;
		Double d;

		Pair(int v, Double d) {
			this.v = v;
			this.d = d;
		}
	}

	public double maxProbability(int n, int[][] edges, double[] successorProb, int sn, int en) {

		double[][] adj = new double[n][n];

		for (int i = 0; i < edges.length; i++) {

			adj[edges[i][0]][edges[i][1]] = successorProb[i];
			adj[edges[i][1]][edges[i][0]] = successorProb[i];
		}

		boolean[] visited = new boolean[n];

		double[] dist = new double[n];

		PriorityQueue<Pair> queue = new PriorityQueue<>((p1, p2) -> Double.compare(p2.d, p1.d));

		queue.add(new Pair(sn, 1.0));

		while (!queue.isEmpty()) {
			Pair p = queue.poll();

			if (visited[p.v]) {
				continue;
			}

			visited[p.v] = true;

			if (p.d >= dist[p.v]) {
				dist[p.v] = p.d;
			} else {
				continue;
			}

			if (p.v == en) {
				return dist[p.v];
			}

			for (int i = 0; i < n; i++) {
				if (adj[p.v][i] != 0.0) {
					Pair pair = new Pair(i, adj[p.v][i] * p.d);
					queue.add(pair);
				}
			}
		}

		return dist[en];
	}
}