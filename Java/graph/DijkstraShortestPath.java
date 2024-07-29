package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstraShortestPath {

	/**
	 * Create the adjacency list node add a way to compare as well
	 */
	public static class AdjListNode implements Comparable<AdjListNode> {

		int v, weight;

		public AdjListNode(int v, int weight) {
			this.v = v;
			this.weight = weight;
		}

		@Override
		public int compareTo(AdjListNode o) {
			return this.weight - o.weight; // min heap by weight (ascending order)
		}
	}

	public static class Graph {

		int V;
		List<List<AdjListNode>> adj;

		public Graph(int V) {
			this.V = V;
			adj = new ArrayList<>();
			for (int i = 0; i < V; ++i) {
				adj.add(new ArrayList<>());
			}
		}

		public void addEdge(int u, int v, int weight) {
			adj.get(u).add(new AdjListNode(v, weight));
		}
	}

	public static int[] dijkstra(Graph graph, int src) {
		int[] distance = new int[graph.V];
		// just optimization without visited Dijkstra will still work
		boolean[] visited = new boolean[graph.V];
		for (int i = 0; i < graph.V; ++i) {
			distance[i] = Integer.MAX_VALUE;
		}
		distance[src] = 0;

		PriorityQueue<AdjListNode> pq = new PriorityQueue<>(); // min heap by weight
		pq.add(new AdjListNode(src, 0));

		while (!pq.isEmpty()) {
			AdjListNode current = pq.poll();
			if (visited[current.v]) {
				continue;
			}
			visited[current.v] = true;
			for (AdjListNode adjNode : graph.adj.get(current.v)) {
				// RELAXATION
				if (!visited[adjNode.v]
					&& distance[current.v] + adjNode.weight < distance[adjNode.v]) {
					distance[adjNode.v] = distance[current.v] + adjNode.weight;
					pq.add(new AdjListNode(adjNode.v, distance[adjNode.v]));
				}
			}
		}

		// If you want to calculate distance from source to
		// a particular target, then return dist[target]
		return distance;
	}

	public static void main(String[] args) {
		int V = 9;
		Graph graph = new Graph(V);
		graph.addEdge(0, 1, 4);
		graph.addEdge(0, 7, 8);
		graph.addEdge(1, 2, 8);
		graph.addEdge(1, 7, 11);
		graph.addEdge(1, 0, 7);
		graph.addEdge(2, 1, 8);
		graph.addEdge(2, 3, 7);
		graph.addEdge(2, 8, 2);
		graph.addEdge(2, 5, 4);
		graph.addEdge(3, 2, 7);
		graph.addEdge(3, 4, 9);
		graph.addEdge(3, 5, 14);
		graph.addEdge(4, 3, 9);
		graph.addEdge(4, 5, 10);
		graph.addEdge(5, 4, 10);
		graph.addEdge(5, 6, 2);
		graph.addEdge(6, 5, 2);
		graph.addEdge(6, 7, 1);
		graph.addEdge(6, 8, 6);
		graph.addEdge(7, 0, 8);
		graph.addEdge(7, 1, 11);
		graph.addEdge(7, 6, 1);
		graph.addEdge(7, 8, 7);
		graph.addEdge(8, 2, 2);
		graph.addEdge(8, 6, 6);
		graph.addEdge(8, 7, 1);

		int[] res = dijkstra(graph, 0);
		for (int i = 0; i < res.length; ++i) {
			System.out.println("Distance from 0 to " + i + " is " + res[i]);
		}

	}
}
