package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Deque;
import java.util.List;

public class CourseScheduleIIDFS {

	private void initializeGraph(int[] in, List<List<Integer>> adj, int[][] pre) {
		int n = in.length;
		while (n-- > 0) {
			adj.add(new ArrayList<>());
		}
		for (int[] edge : pre) {
			++in[edge[0]];
			adj.get(edge[1]).add(edge[0]);
		}
	}

	private int[] solveByDFS(List<List<Integer>> adj) {
		BitSet visited = new BitSet(adj.size());
		BitSet onStack = new BitSet(adj.size());
		Deque<Integer> order = new ArrayDeque<>();
		for (int i = adj.size() - 1; i >= 0; --i) {
			if (!visited.get(i) && hasOrderDFS(i, adj, visited, onStack, order)) {
				return new int[0];
			}
		}

		// Converting complete topological order into array
		int[] orderArray = new int[adj.size()];
		for (int i = 0; !order.isEmpty(); ++i) {
			orderArray[i] = order.pop();
		}
		return orderArray;
	}

	private boolean hasOrderDFS(int from, List<List<Integer>> adj, BitSet visited, BitSet onStack,
		Deque<Integer> order) {
		visited.set(from);
		onStack.set(from);
		for (int to : adj.get(from)) {
			if (!visited.get(to)) {
				if (hasOrderDFS(to, adj, visited, onStack, order)) {
					return true;
				}
			} else if (onStack.get(to)) {
				return true;
			}
		}
		onStack.clear(from);
		order.push(from);
		return false;
	}

	public int[] findOrder(int numCourses, int[][] prerequisites) {
		int[] in = new int[numCourses];
		List<List<Integer>> adj = new ArrayList<>(numCourses);
		initializeGraph(in, adj, prerequisites);
		return solveByDFS(adj);
	}

	// driver code for above
	public static void main(String[] args) {
		CourseScheduleIIDFS obj = new CourseScheduleIIDFS();
		int numCourses = 2;
		int[][] prerequisites = {{1, 0}};
		int[] ans = obj.findOrder(numCourses, prerequisites);
		for (int i : ans) {
			System.out.print(i + " ");
		}
	}
}
