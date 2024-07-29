package dp;

public class HouseRobberII {

	// circular street
	private int rob(int[] nums, int i, int j) {
		int[] dp = new int[j - i + 1];
		dp[0] = nums[i];
		dp[1] = Math.max(nums[i], nums[i + 1]);
		for (int k = 2; k < j - i + 1; ++k) {
			dp[k] = Math.max(dp[k - 1], dp[k - 2] + nums[i + k]);
		}
		return dp[j - i];
	}

	public int rob(int[] nums) {
		int n = nums.length;
		if (n == 1) {
			return nums[0];
		}
		return Math.max(rob(nums, 0, n - 2), rob(nums, 1, n - 1));
	}

	// driver code
	public static void main(String[] args) {
		HouseRobberII hr = new HouseRobberII();
		int[] nums = {2, 3, 2};
		System.out.println(hr.rob(nums)); // prints 3
	}

}
