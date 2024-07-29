package binarySearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class ConstructLongestIncreasingSubsequence {

	class Pair {

		int num;
		int numIdx;

		Pair(int num, int numIdx) {
			this.num = num;
			this.numIdx = numIdx;
		}
	}

	// Binary search
	public static int lowerBound(Pair[] array, int target) {
		int low = 0;
		int high = array.length;
		while (low < high) {
			int mid = (low + high) / 2;
			if (array[mid].num >= target) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		return low;
	}


	public int constructLIS(int[] nums) {
		Pair[] dp = new Pair[nums.length];
		for (int i = 0; i < nums.length; ++i) {
			dp[i] = new Pair(Integer.MAX_VALUE, -1);
		}

		int[] parent = new int[nums.length];

		Arrays.fill(parent, -1);

		int len = 0;
		for (int i = 0; i < nums.length; ++i) {
			int num = nums[i];
			int idx = lowerBound(dp, num);
			dp[idx].num = num;
			dp[idx].numIdx = i;
			if (idx > 0) {
				parent[i] = dp[idx - 1].numIdx;
			}
			if (idx == len) {
				++len;
			}
		}

		List<Integer> lis = new ArrayList<>();
		int cur = dp[len - 1].numIdx;
		while (cur != -1) {
			lis.add(nums[cur]);
			cur = parent[cur];
		}

		Collections.reverse(lis);
		System.out.println("Constructed LIS: " + lis);
		return len;
	}

	// Driver code
	public static void main(String[] args) {
		int[] arr = {2, 6, 8, 3, 4, 5, 1};
		ConstructLongestIncreasingSubsequence cLIS = new ConstructLongestIncreasingSubsequence();
		System.out.print("LIS size: " + cLIS.constructLIS(arr) + "\n");
	}


}
