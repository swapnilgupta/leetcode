package dp;

public class UniquePathWithObstacles {

	public int uniquePathsWithObstacles(int[][] board) {
		int m = board.length, n = board[0].length;
		// int[][] dp = new int[m][n];
		// for(int i = 0; i < m; ++i) {
		//     for(int j = 0; j < n; ++j) {
		//         if(i == 0 && j == 0 && board[i][j] == 0) {
		//             dp[i][j] = 1;
		//         } else if(board[i][j] == 0) {
		//             if(i == 0) {
		//                 dp[i][j] = dp[i][j - 1];
		//             } else if(j == 0) {
		//                 dp[i][j] = dp[i - 1][j];
		//             } else {
		//                 dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
		//             }
		//         }
		//     }
		// }

		// space optimized
		int[] dp = new int[n];
		dp[0] = 1;
		for (int[] ints : board) {
			for (int j = 0; j < n; ++j) {
				if (ints[j] == 1) {
					dp[j] = 0;
				} else if (j > 0) {
					dp[j] += dp[j - 1];
				}
			}
		}
		return dp[n - 1];
	}

	// write the driver code for the above function
	public static void main(String[] args) {
		UniquePathWithObstacles uniquePathWithObstacles = new UniquePathWithObstacles();
		int[][] board = {
			{0, 0, 0},
			{0, 1, 0},
			{0, 0, 0}
		};
		System.out.println(uniquePathWithObstacles.uniquePathsWithObstacles(board));  // prints 2
	}

}
