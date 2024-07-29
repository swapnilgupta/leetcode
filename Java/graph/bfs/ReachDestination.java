package graph.bfs;

import java.util.Arrays;
import java.util.PriorityQueue;

public class ReachDestination {


	static class Node implements Comparable<Node> {

		int row;
		int col;
		int dist;  // cost to reach this node

		Node(int row, int col, int dist) {
			this.row = row;
			this.col = col;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node other) {
			return Integer.compare(this.dist, other.dist);
		}
	}

	private static final int[] dr = {0, 0, -1, 1};
	private static final int[] dc = {1, -1, 0, 0};
	private static int n = 3, m = 3;
	private static int[][] distance;
	private static boolean[][] visited;
	private static int sx = 0, sy = 0;
	private static int dx = 1, dy = 2;
	private static int[] costRows = {2, 2, 5};
	private static int[] costCols = {2, 6, 1};

	public static boolean withinBounds(int i, int j) {
		return i >= 0 && i < n && j >= 0 && j < m;
	}

	public static int getCost(int x1, int y1, int x2, int y2) {
		if (x1 == x2) {
			// moving horizontally
			return costCols[x1];
		}
		// moving vertically
		return costRows[y1];
	}

	public static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(sx, sy, 0));
		distance[sx][sy] = 0;

		while (!pq.isEmpty()) {
			Node node = pq.poll();
			if (visited[node.row][node.col]) {
				continue;
			}
			visited[node.row][node.col] = true;

			for (int d = 0; d < 4; ++d) {
				int nx = node.row + dr[d];
				int ny = node.col + dc[d];
				if (withinBounds(nx, ny) && !visited[nx][ny]) {
					int newDistance = node.dist + getCost(node.row, node.col, nx, ny);
					if (newDistance < distance[nx][ny]) {
						distance[nx][ny] = newDistance;
						pq.add(new Node(nx, ny, newDistance));
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		visited = new boolean[n][m];
		distance = new int[n][m];
		for (int[] row : distance) {
			Arrays.fill(row, Integer.MAX_VALUE);
		}
		dijkstra();
		System.out.println(distance[dx][dy]);
	}


}
