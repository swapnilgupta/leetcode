package graph.mst;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KruskalMST {

	// Define edge class
	public static class Edge implements Comparable<Edge> {

		int src, dest, weight;

		public Edge(int src, int dest, int weight) {
			this.src = src;
			this.dest = dest;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}

	// Define subset class
	public static class Subset {

		int parent, rank;

		public Subset(int parent, int rank) {
			this.parent = parent;
			this.rank = rank;
		}
	}

	// Define find function - with the path compression algorithm
	public static int find(Subset[] subsets, int u) {
		if (subsets[u].parent != u) {
			subsets[u].parent = find(subsets, subsets[u].parent);
		}

		return subsets[u].parent;
	}

	// Define union function
	public static void union(Subset[] subsets, int u, int v) {
		int pu = find(subsets, u);
		int pv = find(subsets, v);

		// higher rank sets as parent
		if (subsets[pu].rank > subsets[pv].rank) {
			subsets[pv].parent = pu;
		} else if (subsets[pu].rank < subsets[pv].rank) {
			subsets[pu].parent = pv;
		} else {
			subsets[pv].parent = pu;
			subsets[pu].rank++;
		}
	}

	// Kruskal's Method to find the MST using Greedy paradigm.
	public static void kruskal(int V, List<Edge> edges) {
		int j = 0;
		int noOfEdges = 0;

		// Creating V subsets
		Subset[] subsets = new Subset[V];

		// Edges in a MST
		Edge[] results = new Edge[V];

		for (int i = 0; i < V; ++i) {
			subsets[i] = new Subset(i, 0);
		}

		// The Number of edges to be taken is equal to (V - 1) in the MST
		while (noOfEdges < V - 1) {
			// Pick the smallest edge and increment
			// the index for the next iteration
			Edge nextEdge = edges.get(j);
			int x = find(subsets, nextEdge.src);
			int y = find(subsets, nextEdge.dest);

			// If including this edge doesn't cause a cycle,
			// include it in the result and increment the index
			// of the result for the next edge
			if (x != y) {
				results[noOfEdges] = nextEdge;
				union(subsets, x, y);
				noOfEdges++;
			}

			++j; // move to next edge
		}

		// Print the MST by Kruskal's Algorithm
		System.out.println("Following are the edges of the constructed MST: ");
		int minCost = 0;
		for (int i = 0; i < noOfEdges; ++i) {
			System.out.println(
				results[i].src + " -- " + results[i].dest + " == " + results[i].weight);
			minCost += results[i].weight;
		}
		System.out.println("Total cost of the MST: " + minCost);
	}

	// Starting point of program execution
	public static void main(String[] args) {
		int V = 4;
		List<Edge> graphEdges = new ArrayList<>(
			List.of(new Edge(0, 1, 10), new Edge(0, 2, 6),
				new Edge(0, 3, 5), new Edge(1, 3, 15),
				new Edge(2, 3, 4)));

		// Sort the edges in non-decreasing order
		// (increasing with repetition allowed)
		Collections.sort(graphEdges);

		kruskal(V, graphEdges);
	}
}
