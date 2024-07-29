package arrays;

public class SearchInsert {

	public int searchInsert(int[] nums, int target) {
		int n = nums.length;
		int i = 0, j = n - 1;
		while (i < j) {
			int mid = i + (j - i) / 2;
			if (nums[mid] > target) {
				j = mid;
			} else {
				i = mid + 1;
			}
		}
		return nums[i] < target ? i + 1 : i;
	}

	// driver code for the above function with 3 test cases
	public static void main(String[] args) {
		SearchInsert si = new SearchInsert();
		int[] nums = {1, 3, 5, 6};
		int target = 5;
		System.out.println(si.searchInsert(nums, target));
		target = 2;
		System.out.println(si.searchInsert(nums, target));
		target = 7;
		System.out.println(si.searchInsert(nums, target));
		target = 0;
		System.out.println(si.searchInsert(nums, target));
	}

}
