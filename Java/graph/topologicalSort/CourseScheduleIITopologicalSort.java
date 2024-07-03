package graph.topologicalSort;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class CourseScheduleIITopologicalSort {

	public int[] findOrder(int numCourses, int[][] prerequisites) {
		int[] in = new int[numCourses];
		List<List<Integer>> adj = new ArrayList<>(numCourses);
		for (int i = 0; i < numCourses; ++i) {
			adj.add(new ArrayList<>());
		}
		// Initialize graph
		// a_i <--- b_i
		// maintain in_degree for topological sorting
		for (int[] edge : prerequisites) {
			++in[edge[0]];
			adj.get(edge[1]).add((edge[0]));
		}
		int[] order = new int[numCourses];
		Deque<Integer> queue = new LinkedList<>();
		// First take degree zero(independent courses)
		for (int i = 0; i < numCourses; ++i) {
			if (in[i] == 0) {
				queue.add(i);
			}
		}

		int i = 0;
		// Topological sorting (Kahn's Algorithm)
		while (!queue.isEmpty()) {
			int u = queue.removeFirst();
			order[i++] = u;
			for (int v : adj.get(u)) {
				if (--in[v] == 0) {
					queue.add(v);
				}
			}
		}
		return i == numCourses ? order : new int[0];
	}

	// driver code for above
	public static void main(String[] args) {
		// Input: numCourses = 2, prerequisites = [[1,0]]
		//Output: [0,1]
		CourseScheduleIITopologicalSort obj = new CourseScheduleIITopologicalSort();
		int numCourses = 2;
		int[][] prerequisites = {{1, 0}};
		int[] ans = obj.findOrder(numCourses, prerequisites);
		for (int i : ans) {
			System.out.print(i + " ");
		}
		System.out.println();
		//		Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
		//		Output: [0,2,1,3]
		numCourses = 4;
		prerequisites = new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}};
		ans = obj.findOrder(numCourses, prerequisites);
		for (int i : ans) {
			System.out.print(i + " ");
		}
	}

}
