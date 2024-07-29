package backtracking;

public class WordSearch {

	public boolean isValid(int i, int j, int m, int n) {
		return i >= 0 && j >= 0 && i < m && j < n;
	}

	public boolean dfs(char[][] board, String word, boolean[][] vis, int i, int j, int idx) {
		int m = board.length, n = board[0].length;
		if (!isValid(i, j, m, n) || vis[i][j]) {
			return false;
		}
		vis[i][j] = true;
		if (board[i][j] == word.charAt(idx)) {
			if (idx == word.length() - 1) {
				return true;
			} else {
				return dfs(board, word, vis, i + 1, j, idx + 1) ||
					dfs(board, word, vis, i - 1, j, idx + 1) ||
					dfs(board, word, vis, i, j + 1, idx + 1) ||
					dfs(board, word, vis, i, j - 1, idx + 1);
			}
		} else {
			return false;
		}
	}

	public boolean exist(char[][] board, String word) {
		int m = board.length, n = board[0].length;
		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				boolean[][] vis = new boolean[m][n];
				if (dfs(board, word, vis, i, j, 0)) {
					return true;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		char[][] board = {{'a', 'b'}, {'c', 'd'}};
		String word = "cdba"; // replace with the word you need to find

		WordSearch solution = new WordSearch();
		boolean doesExist = solution.exist(board, word);
		System.out.println("Does the word " + word + " exist in the board? " + doesExist);
	}

}
