package kadane;

public class MaxSubarraySumCircular {

	public int maxSubarraySumCircular(int[] nums) {
		int n = nums.length;
		int maxSum = nums[0];
		int curMax = nums[0];
		int minSum = nums[0];
		int curMin = nums[0];
		int total = nums[0];
		for (int i = 1; i < n; i++) {
			if (curMax > 0) {
				curMax += nums[i];
			} else {
				curMax = nums[i];
			}
			maxSum = Math.max(maxSum, curMax); // updating the global max
			if (curMin < 0) {
				curMin += nums[i];
			} else {
				curMin = nums[i];
			}
			minSum = Math.min(minSum, curMin);  // updating the global min
			total += nums[i]; // calculating the total sum
		}
		if (maxSum > 0) { // if the maxSum is greater than 0, then we can return the max of the two cases
			return Math.max(maxSum, total - minSum); // returning the max of the two cases
		}
		return maxSum;
	}

	// driver code for the above function
	public static void main(String[] args) {
		MaxSubarraySumCircular maxSumCircular = new MaxSubarraySumCircular();
		int[] nums = {1, -2, 3, -2};
		int result = maxSumCircular.maxSubarraySumCircular(nums);
		System.out.println(result);

		nums = new int[]{5, -3, 5};
		result = maxSumCircular.maxSubarraySumCircular(nums);
		System.out.println(result);

		nums = new int[]{3, -1, 2, -1};
		result = maxSumCircular.maxSubarraySumCircular(nums);
		System.out.println(result);
	}

}
