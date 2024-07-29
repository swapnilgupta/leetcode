package graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

public class IsReachablePoint {

	// Eight possible movements
	private static final int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
	private static final int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};

	// Using HashSet to check if a cell has been visited
	private static class Point {

		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}
			Point point = (Point) o;
			return x == point.x &&
				y == point.y;
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}
	}

	public boolean isReachableAtTime(int sx, int sy, int fx, int fy, int t) {
		// Using LinkedList to implement BFS (queue)
		if (Math.abs(sx - fx) + Math.abs(sy - fy) != t) {
			return false;
		}
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[]{sx, sy, 0}); // add start point

		Set<Point> visited = new HashSet<>();
		visited.add(new Point(sx, sy));

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int curX = cur[0];
			int curY = cur[1];
			int curT = cur[2];

			// If this is the target point
			if (curX == fx && curY == fy) {
				return curT <= t;
			}

			// Explore the 8 directions
			for (int i = 0; i < 8; i++) {
				int newX = curX + dx[i];
				int newY = curY + dy[i];
				if (newX < 1 || newY < 1) {
					continue;
				}
				Point newPoint = new Point(newX, newY);

				// If this cell has not been visited and time does not exceed t
				if (!visited.contains(newPoint) && curT < t) {
					queue.offer(new int[]{newX, newY, curT + 1}); // add to queue
					visited.add(newPoint); // mark cell as visited
				}
			}
		}

		// If finished BFS and never reach target
		return false;
	}

	public static void main(String[] args) {
		IsReachablePoint irp = new IsReachablePoint();
		System.out.println(irp.isReachableAtTime(1, 2, 1, 2, 1));
		System.out.println(irp.isReachableAtTime(1, 1, 3, 3, 9));
	}

}
