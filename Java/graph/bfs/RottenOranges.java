package graph.bfs;

import java.util.Deque;
import java.util.LinkedList;

public class RottenOranges {

	public int orangesRotting(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;
		int fresh = 0;
		int time = 0;
		int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
		Deque<int[]> queue = new LinkedList<>();

		// Add all rotten oranges to queue
		// 0 empty 1 fresh 2 denotes rotten
		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				if (grid[i][j] == 1) {
					++fresh;
				} else if (grid[i][j] == 2) {
					queue.add(new int[]{i, j});
				}
			}
		}

		while (!queue.isEmpty() && fresh > 0) {
			int size = queue.size();
			++time;

			for (int i = 0; i < size; ++i) {
				int[] rotten = queue.poll();
				for (int[] dir : dirs) {
					int x = rotten[0] + dir[0];
					int y = rotten[1] + dir[1];
					if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 0 || grid[x][y] == 2) {
						continue;
					}
					--fresh;
					grid[x][y] = 2;
					queue.add(new int[]{x, y});
				}
			}
		}

		return fresh == 0 ? time : -1;
	}

	// driver code for the above
	// Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
	//Output: 4

	// Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
	//Output: -1
	public static void main(String[] args) {
		RottenOranges rottenOranges = new RottenOranges();

		int[][] grid1 = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
		int result1 = rottenOranges.orangesRotting(grid1);
		System.out.println("Expected: 4, Actual: " + result1);

		int[][] grid2 = {{2, 1, 1}, {0, 1, 1}, {1, 0, 1}};
		int result2 = rottenOranges.orangesRotting(grid2);
		System.out.println("Expected: -1, Actual: " + result2);
	}
}
