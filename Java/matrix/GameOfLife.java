package matrix;

public class GameOfLife {

	public void gameOfLife(int[][] board) {
		int m = board.length;
		int n = board[0].length;
		// create a copy of board
		int[][] copy = new int[m][n];
		for (int i = 0; i < m; ++i) {
			copy[i] = board[i].clone();
		}

		int[][] dir = {{1, 1}, {-1, -1}, {1, -1}, {-1, 1}, {0, 1}, {1, 0}, {0, -1}, {-1, 0}};

		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				int lc = 0;
				for (int k = 0; k < 8; ++k) {
					int x = i + dir[k][0];
					int y = j + dir[k][1];
					if (copy[x][y] == 1) {
						++lc;
					}
				}
				if (lc < 2 || lc > 3) {
					board[i][j] = 0;
				} else if (lc == 3) {
					board[i][j] = 1;
				}
			}
		}
	}

}
