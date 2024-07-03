package dp;

import java.util.Arrays;

public class LongestIncreasingSubsequence {

	public static int lowerBound(int[] array, int target) {
		int low = 0;
		int high = array.length;
		while (low < high) {
			int mid = (low + high) / 2;
			if (array[mid] >= target) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		return low;
	}


	public static int lengthOfLIS(int[] nums) {
		int[] dp = new int[nums.length];
		Arrays.fill(dp, Integer.MAX_VALUE);
		int len = 0;
		for (int num : nums) {
			int idx = lowerBound(dp, num);
			dp[idx] = num;
			if (idx == len) {
				++len;
			}
		}
		return len;
	}

	public static void main(String[] args) {
		int[] nums = {2, 6, 8, 3, 4, 5, 1};
		System.out.println("Length of LIS: " + lengthOfLIS(nums));
	}

}
