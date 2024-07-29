package graph.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class NearestExitFromEntranceInMaze {

	static class Pair {

		int x;
		int y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public int nearestExit(char[][] maze, int[] entrance) {
		int[][] dir = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
		Queue<Pair> q = new LinkedList<>();
		Pair p = new Pair(entrance[0], entrance[1]);
		q.add(p);
		int m = maze.length;
		int n = maze[0].length;
		boolean[][] visited = new boolean[m][n];
		visited[entrance[0]][entrance[1]] = true;
		int steps = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			while (size-- > 0) {
				Pair curr = q.poll();
				assert curr != null;
				for (int[] d : dir) {
					int x = curr.x + d[0];
					int y = curr.y + d[1];
					if (x > 0 && y > 0 && x < m - 1 && y < n - 1 && maze[x][y] == '.'
						&& !visited[x][y]) {
						visited[x][y] = true;
						q.add(new Pair(x, y));
					} else {
						if (x == 0 || y == 0 || x == m - 1 || y == n - 1) {
							return steps + 1;
						}
					}
				}
			}
			steps++;
		}
		return -1;
	}

	// add driver code for nearestExit function
	public static void main(String[] args) {
		NearestExitFromEntranceInMaze mazeObj = new NearestExitFromEntranceInMaze();

		char[][] maze = {
			{'+', '+', '.', '+'},
			{'.', '.', '.', '+'},
			{'+', '+', '+', '.'}
		};
		int[] entrance = {1, 2};

		int result = mazeObj.nearestExit(maze, entrance);

		if (result == -1) {
			System.out.println("There is no exit from the entrance in the maze.");
		} else {
			System.out.println(
				"The nearest exit from the entrance in the maze is at " + result + " steps.");
		}
	}

}
