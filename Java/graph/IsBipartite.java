package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class IsBipartite {

	public boolean isBipartite(int[][] graph) {
		int n = graph.length;
		int[] color = new int[n];
		Arrays.fill(color, -1);
		Queue<Integer> q = new LinkedList<>();
		int[] visited = new int[n];

		for (int i = 0; i < n; ++i) {
			if (visited[i] == 1) {
				continue;
			}
			color[i] = 1;
			visited[i] = 1;
			q.add(i);

			while (!q.isEmpty()) {
				int cur = q.remove();
				int nc = color[cur] == 1 ? 0 : 1;
				for (var node : graph[cur]) {
					if (color[node] != -1) {
						if (color[node] == color[cur]) {
							return false;
						}
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
