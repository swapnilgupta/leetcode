package twoPointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> ans = new ArrayList<>();
		int n = nums.length;
		Arrays.sort(nums);
		int i = 0;
		while (i < n) {
			int target = -nums[i];
			int j = i + 1;
			int k = n - 1;
			while (j < k) {
				int sum = nums[j] + nums[k];
				if (target > sum) {
					++j;
				} else if (target < sum) {
					--k;
				} else {
					List<Integer> triplet = new ArrayList<>();
					triplet.add(nums[i]);
					triplet.add(nums[j]);
					triplet.add(nums[k]);
					ans.add(triplet);
					// skip duplicates
					while (j < k && nums[j] == triplet.get(1)) {
						++j;
					}
					while (j < k && nums[k] == triplet.get(2)) {
						--k;
					}
				}
				// skip duplicates
				while (i < n && nums[i] == -target) {
					++i;
				}
			}
		}
		return ans;
	}

	// write driver code for the above function
	public static void main(String[] args) {
		ThreeSum ts = new ThreeSum();
		int[] arr = {-1, 0, 1, 2, -1, -4};
		// print output
		System.out.println(ts.threeSum(arr));
	}


}
