package graph.topologicalSort;

import java.util.ArrayList;

public class TopologicalSortBFS {

	static class Graph {

		private final int V; // No. of vertices
		private final ArrayList<ArrayList<Integer>> adj; // Adjacency List

		// constructor
		Graph(int v) {
			V = v;
			adj = new ArrayList<>(v);
			for (int i = 0; i < v; ++i) {
				adj.add(new ArrayList<>());
			}
		}

		// Function to add an edge into the graph
		void addEdge(int v, int w) {
			adj.get(v).add(w);
		}

		// A recursive function used by topological sort
		void topologicalSort() {
			int[] in = new int[this.V];
			// calculate in-degree and create adjacency list
			for (int i = 0; i < V; ++i) {

			}
		}
	}

	public static void main(String[] args) {
		// Create a graph given in the above diagram
		TopologicalSortingDFS.Graph g = new TopologicalSortingDFS.Graph(6);
		g.addEdge(5, 2);
		g.addEdge(5, 0);
		g.addEdge(4, 0);
		g.addEdge(4, 1);
		g.addEdge(2, 3);
		g.addEdge(3, 1);

		System.out.println("Following is a Topological "
			+ "sort of the given graph");
		// Function Call
		g.topologicalSort();
	}
}
