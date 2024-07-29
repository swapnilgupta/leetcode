package bitmanipulation.bitmasking;

import java.util.Arrays;

public class SpecialPermutation {

	// declare a 2D array to store the results of subproblems
	int[][] dp;

	/**
	 * define a recursive function that takes four parameters:
	 * <p>
	 *
	 * @param nums    the input array of integers
	 * @param prevIdx the index of the previous element chosen in the permutation
	 * @param curIdx  the number of elements chosen so far in the permutation
	 * @param mask    a bitmask that represents the chosen indices in the permutation
	 */
	int solve(int[] nums, int prevIdx, int curIdx, int mask) {
		// base case: if the curIdx reaches the end of the nums array,
		// it means we have formed a complete permutation
		if (curIdx == nums.length) {
			// return 1 to indicate a valid permutation
			return 1;
		}
		// check if the subproblem has already been solved and stored in the dp array
		if (dp[prevIdx + 1][mask] != -1) {
			// return the stored result
			return dp[prevIdx + 1][mask];
		}
		// initialize a variable to store the total number of valid permutations
		int tot = 0;
		// loop through all the elements in the nums array
		for (int j = 0; j < nums.length; j++) {
			// check if the j-th bit in the mask is set, meaning that the j-th element has been chosen
			if ((mask & (1 << j)) != 0) {
				// skip this element and continue to the next iteration
				continue;
			}
			// check if the current element can be chosen based on the condition:
			// either prevIdx is -1 (meaning no element has been chosen yet)
			// or nums[prevIdx] is divisible by nums[j]
			// or nums[j] is divisible by nums[prevIdx]
			if (prevIdx == -1 || nums[prevIdx] % nums[j] == 0
				|| nums[j] % nums[prevIdx] == 0) {
				// add the result of recursively choosing this element and moving to the next index
				tot += solve(nums, j, curIdx + 1, mask | (1 << j));
				// modulo by 10^9 + 7 to avoid overflow
				tot %= 1000000007;
			}
		}
		// store the result in the dp array for future reference
		dp[prevIdx + 1][mask] = tot;
		// return the result
		return dp[prevIdx + 1][mask];
	}

	public int specialPerm(int[] nums) {
		//The current index simply represents the number of set bits in the mask.
		// We use a 2D DP array to store the values of permutations.
		// When all bits in the mask are set, indicating that all elements have been chosen,
		// we increment the count by 1 to account for a valid permutation.

		// The expression 1 << 14 + 5 is used to set enough bits in the bitmask
		// to accommodate indices from 0 to 14, and the additional
		// 5 bits are added to handle any potential overflow.
		// This ensures that the bitmask can handle the required indices and prevents any overflow issues.
		dp = new int[20][(1 << 14) + 5];
		for (int[] ints : dp) {
			Arrays.fill(ints, -1);
		}
		// call the recursive function with initial parameters: -1, 0, and 0
		return solve(nums, -1, 0, 0);
	}

	// following is the driver code for special permutation
	public static void main(String[] args) {
		SpecialPermutation sp = new SpecialPermutation();
		int[] nums = {1, 2, 3};
		System.out.println("Number of special permutations: " + sp.specialPerm(nums));
	}


}
