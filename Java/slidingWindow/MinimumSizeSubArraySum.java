package slidingWindow;

public class MinimumSizeSubArraySum {

	public static int minSubArrayLen(int target, int[] nums) {
		int n = nums.length, i = 0, j = 0, cs = 0, ans = Integer.MAX_VALUE;
		while (i < n && j < n) {
			cs += nums[j++];
			// inc
			while (j < n && cs < target) {
				cs += nums[j++];
			}
			// try to remove
			while (i < j && (cs - nums[i]) >= target) {
				cs -= nums[i++];
			}
			// update ans
			ans = Math.min(ans, j - i);
		}
		return ans == -1 ? 0 : ans;
	}

	// Add driver code for this
	public static void main(String[] args) {
		// create an instance of the class where your method locate

		// Initialize the variables
		int target = 4;
		int[] nums = {1, 4, 4};

		// Call the method and print the result
		System.out.println("The minimum subarray length: " + minSubArrayLen(target, nums));
	}
}