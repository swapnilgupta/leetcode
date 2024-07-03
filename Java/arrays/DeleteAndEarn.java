package arrays;

public class DeleteAndEarn {

	// driver code for the above function with 3 test cases
	public static void main(String[] args) {
		DeleteAndEarn dae = new DeleteAndEarn();
		int[] nums = {3, 4, 2};
		System.out.println(dae.deleteAndEarn(nums));
		int[] nums1 = {2, 2, 3, 3, 3, 4};
		System.out.println(dae.deleteAndEarn(nums1));
		int[] nums2 = {1, 1, 1, 2, 4, 5, 5, 5, 6};
		System.out.println(dae.deleteAndEarn(nums2));
	}

	public int deleteAndEarn(int[] nums) {
		int[] count = new int[10001];
		// store the count of each number in the array
		for (int num : nums) {
			count[num]++;
		}

		int[] dp = new int[10001];
		dp[1] = count[1];
		for (int i = 2; i <= 10000; ++i) {
			// if we choose to delete i, then we can't choose i - 1,
			// so we add the count of i * i and the max of dp[i - 2]
			dp[i] = Math.max(dp[i - 1], dp[i - 2] + i * count[i]);
		}
		return dp[10000];
	}

}
