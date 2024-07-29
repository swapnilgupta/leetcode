package twoPointer;

import java.util.Arrays;

public class ValidTriangleNumber {

	private void reverse(int[] nums) {
		int n = nums.length;
		int i = 0, j = n - 1;
		while (i < j) {
			int temp = nums[i];
			nums[i] = nums[j];
			nums[j] = temp;
			++i;
			--j;
		}
	}

	public int triangleNumber(int[] nums) {
		Arrays.sort(nums);
		reverse(nums);
		int n = nums.length;
		int res = 0;
		for (int i = 0; i < n - 2; ++i) {
			int j = i + 1;
			int k = n - 1;
			while (j < k) {
				if (nums[i] < nums[j] + nums[k]) {
					res += k - j;
					++j; // increment j as nums is sorted in descending order
				} else {
					--k;
				}
			}
		}
		return res;
	}
}
