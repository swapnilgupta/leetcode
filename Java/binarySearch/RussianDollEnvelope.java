package binarySearch;

import java.util.Arrays;

public class RussianDollEnvelope {

	public static int lowerBound(int[][] array, int[] target) {
		int lo = 0, hi = array.length;
		while (lo < hi) {
			int mid = (lo + hi) / 2;
			if (array[mid][0] >= target[0] && array[mid][1] >= target[1]) {
				hi = mid;
			} else {
				lo = mid + 1;
			}
		}
		return lo;
	}

	public int maxEnvelopes(int[][] envelopes) {
		int n = envelopes.length;
		int len = 0;
		Arrays.sort(envelopes);
		int[][] dp = new int[n][2];
		for (int i = 0; i < n; ++i) {
			dp[i][0] = Integer.MAX_VALUE;
			dp[i][1] = Integer.MAX_VALUE;
		}

		for (int[] envelope : envelopes) {
			int idx = lowerBound(dp, envelope);
			dp[idx][0] = envelope[0];
			dp[idx][1] = envelope[1];
			if (idx == len) {
				++len;
			}
		}
		return len;
	}

	/**
	 * Driver code for the above method
	 * <p>
	 * for two following examples
	 * <p>
	 * Example 1:
	 * <p>
	 * Input: envelopes = [[5,4],[6,4],[6,7],[2,3]] Output: [3] Explanation: The maximum number of
	 * enveloping you can Russian doll is 3 ([2,3] => [5,4] => [6,7]). Example 2:
	 * <p>
	 * Input: envelopes = [[1,1],[1,1],[1,1]] Output: 1
	 */

	public static void main(String[] args) {
		RussianDollEnvelope rde = new RussianDollEnvelope();
		int[][] envelopes = {{5, 4}, {6, 4}, {6, 7}, {2, 3}};
		System.out.println("Max number of envelopes: " + rde.maxEnvelopes(envelopes));

		int[][] envelopes2 = {{1, 1}, {1, 1}, {1, 1}};
		System.out.println("Max number of envelopes: " + rde.maxEnvelopes(envelopes2));
	}


}
