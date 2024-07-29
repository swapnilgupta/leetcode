package arrays;

public class CountMatchingSubArrays {

	/**
	 * A subarray nums[i..j] of size m + 1 is said to match the pattern if the following conditions
	 * hold for each element pattern[k]:
	 * <p>
	 * nums[i + k + 1] > nums[i + k] if pattern[k] == 1. nums[i + k + 1] == nums[i + k] if
	 * pattern[k] == 0. nums[i + k + 1] < nums[i + k] if pattern[k] == -1.
	 *
	 * @param nums
	 * @param pattern
	 * @return
	 */
	public int countMatchingSubarrays(int[] nums, int[] pattern) {
		int res = 0;
		int n = nums.length;
		int m = pattern.length;
		for (int i = 0; i < n - m; i++) {
			int j = i;
			for (int value : pattern) {
				if (value == 1 && nums[j + 1] > nums[j]) {
					j++;
				} else if (value == 0 && nums[j + 1] == nums[j]) {
					j++;
				} else if (value == -1 && nums[j + 1] < nums[j]) {
					j++;
				} else {
					break;
				}
			}
			if (j - i == m) {
				res++;
			}
		}
		return res;
	}

	// test above with nums = [1,4,4,1,3,5,5,3], pattern = [1,0,-1]
	// and
	// nums = [1,2,3,4,5,6], pattern = [1,1]
	public static void main(String[] args) {
		CountMatchingSubArrays cma = new CountMatchingSubArrays();
		int[] nums = {1, 4, 4, 1, 3, 5, 5, 3};
		int[] pattern = {1, 0, -1};
		System.out.println(cma.countMatchingSubarrays(nums, pattern));
		nums = new int[]{1, 2, 3, 4, 5, 6};
		pattern = new int[]{1, 1};
		System.out.println(cma.countMatchingSubarrays(nums, pattern));
	}
}
