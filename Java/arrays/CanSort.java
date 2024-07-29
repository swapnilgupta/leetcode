package arrays;

public class CanSort {

	public boolean canSortArray(int[] nums) {
		// Count the set bits for each element
		int[] bitCount = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			bitCount[i] = Integer.bitCount(nums[i]);
		}

		// Check if the array can be sorted using allowed swaps
		for (int i = 0; i < nums.length - 1; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (bitCount[i] == bitCount[j] && nums[i] > nums[j]) {
					// Swap if the set bits are the same and the current element is greater
					int temp = nums[i];
					nums[i] = nums[j];
					nums[j] = temp;
				}
			}
		}

		// Check if the array is sorted
		for (int i = 0; i < nums.length - 1; i++) {
			if (nums[i] > nums[i + 1]) {
				return false;
			}
		}

		return true;
	}

	// add the driver code for above
	public static void main(String[] args) {
		CanSort cs = new CanSort();
		int[] nums = {75, 34, 30}; // [75,34,30]
		System.out.println(cs.canSortArray(nums));
	}
}
