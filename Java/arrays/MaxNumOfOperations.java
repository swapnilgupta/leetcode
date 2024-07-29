package arrays;

import java.util.HashMap;
import java.util.Map;

public class MaxNumOfOperations {

	/**
	 * Given an array of integers called nums, you can perform any of the following operation while
	 * nums contains at least 2 elements:
	 *
	 * <p>Choose the first two elements of nums and delete them. Choose the last two elements of
	 * nums
	 * and delete them. Choose the first and the last elements of nums and delete them. The score of
	 * the operation is the sum of the deleted elements.
	 *
	 * <p>Your task is to find the maximum number of operations that can be performed, such that
	 * all
	 * operations have the same score.
	 *
	 * <p>Return the maximum number of operations possible that satisfy the condition mentioned
	 * above.
	 *
	 * @param nums
	 * @return
	 */
	// test above with nums = [2,4,1,3,-2,-3]
	public static void main(String[] args) {
		MaxNumOfOperations mno = new MaxNumOfOperations();
		int[] nums = {3, 2, 1, 2, 3, 4};
		System.out.println(mno.maxOperations(nums));
		// [1,9,7,3,2,7,4,12,2,6]
		nums = new int[]{1, 9, 7, 3, 2, 7, 4, 12, 2, 6};
		System.out.println(mno.maxOperations(nums));
		// [3,2,1,2,3,4]
		nums = new int[]{3, 2};
		System.out.println(mno.maxOperations(nums));
	}

	Map<String, Integer> memo = new HashMap<>();

	public int maxOperations(int[] nums) {
		int s1 = nums[0] + nums[nums.length - 1];
		int s2 = nums[0] + nums[1];
		int s3 = nums[nums.length - 1] + nums[nums.length - 2];

		// based on above sum we can find the maximum number of operations using recursion
		return 1
			+ Math.max(
			recur(nums, s1, 1, nums.length - 2),
			Math.max(recur(nums, s2, 2, nums.length - 1), recur(nums, s3, 0, nums.length - 3)));
	}

	public int recur(int[] nums, int sum, int i, int j) {
		if (i >= j) {
			return 0;
		}

		int s1 = nums[i] + nums[j];
		int s2 = nums[i] + nums[i + 1];
		int s3 = nums[j] + nums[j - 1];
		// if result in the memo then return it otherwise calculate it and store it in the memo and
		// return it
		String key = i + "-" + j;
		if (memo.containsKey(key)) {
			return memo.get(key);
		}
		// s1, s2, s3 equal to the sum then we can perform the operation further recursively
		int first = 0, second = 0, third = 0;
		if (s1 == sum) {
			first = 1 + recur(nums, sum, i + 1, j - 1);
		}
		if (s2 == sum) {
			second = 1 + recur(nums, sum, i + 2, j);
		}
		if (s3 == sum) {
			third = 1 + recur(nums, sum, i, j - 2);
		}

		memo.put(key, Math.max(first, Math.max(second, third)));
		return memo.get(key);
	}
}
