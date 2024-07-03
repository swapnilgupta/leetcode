package graph;

import java.util.ArrayList;
import java.util.List;

public class CourseScheduleIIBFS {
	// Kahn's Algorithm

	public int[] findOrder(int numCourses, int[][] prerequisites) {
		int[] in = new int[numCourses];
		List<List<Integer>> adj = new ArrayList<>(numCourses);
		List<Integer> bfs = new ArrayList<>();
		// Initialize graph
		// a_i <--- b_i
		// maintain in_degree for topological sorting
		for (int i = 0; i < numCourses; ++i) {
			adj.add(new ArrayList<>());
		}
		for (int[] edge : prerequisites) {
			++in[edge[0]];
			adj.get(edge[1]).add((edge[0]));
		}

		// First take degree zero(independent courses)
		for (int i = 0; i < numCourses; ++i) {
			if (in[i] == 0) {
				bfs.add(i);
			}
		}

		// Topological sorting (Kahn's Algorithm)
		for (int i = 0; i < bfs.size(); ++i) {
			int u = bfs.get(i);
			for (int v : adj.get(u)) {
				if (--in[v] == 0) {
					bfs.add(v);
				}
			}
		}

		// If you could finish up all courses, then the size will be n
		return bfs.size() == numCourses ? bfs.stream().mapToInt(i -> i).toArray() : new int[0];
	}

	// driver code for above
	public static void main(String[] args) {
		CourseScheduleIIBFS obj = new CourseScheduleIIBFS();
		int numCourses = 2;
		int[][] prerequisites = {{1, 0}};
		int[] ans = obj.findOrder(numCourses, prerequisites);
		for (int i : ans) {
			System.out.print(i + " ");
		}
	}

}
