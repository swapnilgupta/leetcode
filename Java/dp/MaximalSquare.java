package dp;

public class MaximalSquare {

	public int maximalSquare(char[][] a) {
		// https://leetcode.com/problems/maximal-square/solutions/600149/python-thinking-process-diagrams-dp-approach/?envType=study-plan-v2&envId=top-interview-150
		if (a == null || a.length == 0 || a[0].length == 0) {
			return 0;
		}

		int max = 0, n = a.length, m = a[0].length;

		// dp(i, j) represents the length of the square
		// whose lower-right corner is located at (i, j)
		// dp(i, j) = min{ dp(i-1, j-1), dp(i-1, j), dp(i, j-1) }
		int[][] dp = new int[n + 1][m + 1];

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (a[i - 1][j - 1] == '1') {
					dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
					max = Math.max(max, dp[i][j]);
				}
			}
		}
		// return the area
		return max * max;
	}

	// driver code for the above function
	public static void main(String[] args) {
		MaximalSquare maximalSquare = new MaximalSquare();

		char[][] matrix = {
			{'1', '0', '1', '0', '0'},
			{'1', '0', '1', '1', '1'},
			{'1', '1', '1', '1', '1'},
			{'1', '0', '0', '1', '0'}
		};

		int max = maximalSquare.maximalSquare(matrix);
		System.out.println(max);  // Prints: 4
	}

}
