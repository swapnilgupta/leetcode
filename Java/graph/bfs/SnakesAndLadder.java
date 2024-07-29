package graph.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class SnakesAndLadder {

	public int snakesAndLadders(int[][] board) {
		int n = board.length;
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(1);
		boolean[] visited = new boolean[n * n + 1];
		for (int move = 0; !queue.isEmpty(); move++) {
			for (int size = queue.size(); size > 0; size--) {
				int num = queue.poll();
				if (visited[num]) {
					continue;
				}
				visited[num] = true;
				if (num == n * n) {
					return move;
				}
				for (int i = 1; i <= 6 && num + i <= n * n; i++) {
					int next = num + i;
					int value = getBoardValue(board, next);
					if (value > 0) {
						next = value;
					}
					if (!visited[next]) {
						queue.offer(next);
					}
				}
			}
		}
		return -1;
	}

	private int getBoardValue(int[][] board, int num) {
		int n = board.length;
		int oldRow = (num - 1) / n;
		int row = n - 1 - oldRow;
		int oldCol = (num - 1) % n;
		int col = oldRow % 2 == 0 ? oldCol : n - 1 - oldCol;

		return board[row][col];
	}


	public static class Main {

		public static void main(String[] args) {
			//Initialize a snake and ladders game board
			int[][] board = new int[][]{
				{-1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1},
				{-1, 35, -1, -1, 13, -1},
				{-1, -1, -1, -1, -1, -1},
				{-1, 15, -1, -1, -1, -1}
			};

			SnakesAndLadder game = new SnakesAndLadder();
			int steps = game.snakesAndLadders(board);
			System.out.println("The minimum number of steps is: " + steps);
		}
	}

}
