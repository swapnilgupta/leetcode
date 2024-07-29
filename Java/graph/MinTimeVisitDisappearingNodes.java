package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class MinTimeVisitDisappearingNodes {

	public int[] minimumTime(int n, int[][] edges, int[] disappear) {
		Map<String, Integer> edgeWeights = new HashMap<>();
		List<List<int[]>> adj = new ArrayList<>();
		for (int i = 0; i < n; ++i) {
			adj.add(new ArrayList<>());
		}
		for (int[] edge : edges) {
			int u = edge[0];
			int v = edge[1];
			int weight = edge[2];
			// ignore loops
			if (u != v) {
				String edgeStr = u < v ? u + "-" + v : v + "-" + u;
				// If the edge is not present in the map or the current weight is less than the existing weight
				if (!edgeWeights.containsKey(edgeStr) || weight < edgeWeights.get(edgeStr)) {
					edgeWeights.put(edgeStr, weight);
				}
			}
		}

		// Build the adjacency list using the edgeMap
		for (Map.Entry<String, Integer> entry : edgeWeights.entrySet()) {
			String[] edge = entry.getKey().split("-");
			int u = Integer.parseInt(edge[0]);
			int v = Integer.parseInt(edge[1]);
			int weight = entry.getValue();
			adj.get(u).add(new int[]{v, weight});
			adj.get(v).add(new int[]{u, weight});
		}

		// calculate minTime for each node by doing a BFS traversal using PriorityQueue
		int[] minTime = new int[n];
		Arrays.fill(minTime, Integer.MAX_VALUE);
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		pq.add(new int[]{0, 0});
		minTime[0] = 0;
		while (!pq.isEmpty()) {
			int[] curr = pq.poll();
			int u = curr[0];
			int time = curr[1];
			if (time > minTime[u]) {
				continue;
			}
			for (int[] edge : adj.get(u)) {
				int v = edge[0];
				int length = edge[1];
				if (minTime[u] + length < minTime[v]) {
					minTime[v] = minTime[u] + length;
					if (minTime[v] >= disappear[v]) {
						minTime[v] = -1;
					} else {
						pq.add(new int[]{v, minTime[v]});
					}
				}
			}
		}

		// nodes which are now Infinity should be set to -1
		for (int i = 0; i < n; ++i) {
			if (minTime[i] == Integer.MAX_VALUE) {
				minTime[i] = -1;
			}
		}
		return minTime;
	}


	// driver code
	public static void main(String[] args) {
		//5
		//[[0,3,10],[2,3,2],[3,3,6],[3,3,9],[1,3,2],[3,0,4]]
		//[17,17,17,8,20]
		int n = 5;
		int[][] edges = {{0, 3, 10}, {2, 3, 2}, {3, 3, 6}, {3, 3, 9}, {1, 3, 2}, {3, 0, 4}};
		int[] disappear = {17, 17, 17, 8, 20};
		MinTimeVisitDisappearingNodes mtvdn = new MinTimeVisitDisappearingNodes();
		int[] minTime = mtvdn.minimumTime(n, edges, disappear);
		System.out.println(Arrays.toString(minTime)); // prints [0, 2, -1]
	}
}
