package dp;

import java.util.Arrays;

public class TargetSum {

	public static int findTargetSumWays(int[] nums, int i, int target, int[][] memo) {
		if (i == nums.length) {
			if (target == 0) {
				return 1;
			}
			return 0;
		}

		if (memo[i][target + 1000] != -1) {
			return memo[i][target + 1000];
		}

		int add = findTargetSumWays(nums, i + 1, target - nums[i], memo);
		int subtract = findTargetSumWays(nums, i + 1, target + nums[i], memo);

		memo[i][target + 1000] = add + subtract;
		return memo[i][target + 1000];
	}

	public int findTargetSumWays(int[] nums, int target) {
		int[][] memo = new int[nums.length][target + 1000];
		for (int[] mem : memo) {
			Arrays.fill(mem, -1);
		}

		return findTargetSumWays(nums, 0, target, memo);
	}

	public int findTargetSumWaysTabulation(int[] nums, int S) {
		int total = Arrays.stream(nums).sum(); // Array Sum
		int[][] dp = new int[nums.length][2 * total + 1];
		dp[0][nums[0] + total] = 1;
		dp[0][-nums[0] + total] += 1;

		for (int i = 1; i < nums.length; i++) {
			for (int sum = -total; sum <= total; sum++) {
				if (dp[i - 1][sum + total] > 0) {
					dp[i][sum + nums[i] + total] += dp[i - 1][sum + total];
					dp[i][sum - nums[i] + total] += dp[i - 1][sum + total];
				}
			}
		}

		return Math.abs(S) > total ? 0 : dp[nums.length - 1][S + total];
	}


}
