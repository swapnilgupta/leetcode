package graph.topologicalSort;

// A Java program to print topological sorting of a DAG

import java.util.ArrayList;
import java.util.Stack;

class TopologicalSortingDFS {

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
		void topologicalSortUtil(int v, boolean[] visited, Stack<Integer> stack) {
			// Mark the current node as visited
			visited[v] = true;
			Integer i;

			// Recur for all the vertices adjacent to this vertex
			for (Integer integer : adj.get(v)) {
				i = integer;
				if (!visited[i]) {
					topologicalSortUtil(i, visited, stack);
				}
			}

			// All adj nodes are done now push current vertex to
			// stack, which stores the result in reverse order
			stack.push(v);
		}

		// The function to do topological sort
		// It uses recursive topologicalSortUtil()
		void topologicalSort() {
			Stack<Integer> stack = new Stack<>();
			// Mark all the vertices as non-visited first
			boolean[] visited = new boolean[V];

			// call dfs helper to store the topological sort from all the vertices
			for (int i = 0; i < V; ++i) {
				if (!visited[i]) {
					topologicalSortUtil(i, visited, stack);
				}
			}

			// display the topological sort from the stack content
			for (int v : stack) {
				System.out.print(v + " ");
			}
		}
	}

	// Driver code
	public static void main(String[] args) {
		// Create a graph given in the above diagram
		Graph g = new Graph(6);
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
