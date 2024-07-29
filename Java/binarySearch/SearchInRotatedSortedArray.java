package binarySearch;

public class SearchInRotatedSortedArray {

	public int search(int[] nums, int target) {
		int lo = 0, hi = nums.length - 1;
		while (lo < hi) {
			int mid = lo + (hi - lo) / 2;
			if (nums[mid]
				> nums[hi]) { //if mid element is greater than hi, it means the rotation is on right half.
				lo = mid + 1;
			} else { //rotation is on left half
				hi = mid;
			}
		}
		//lo is now the index of smallest element (the pivot)
		int pivot = lo;
		lo = 0;
		hi = nums.length - 1;
		while (lo <= hi) { //standard binary search
			int mid = lo + (hi - lo) / 2;
			int realMid = (mid + pivot) % nums.length; //adjust mid for rotation
			if (nums[realMid] == target) {
				return realMid;
			} else if (nums[realMid] < target) {
				lo = mid + 1;
			} else {
				hi = mid - 1;
			}
		}
		return -1;
	}

	// driver code for the above function
	public static void main(String[] args) {
		SearchInRotatedSortedArray searchInRotatedSortedArray = new SearchInRotatedSortedArray();
		int[] nums = {4, 5, 6, 7, 0, 1, 2};
		int target = 0;
		int result = searchInRotatedSortedArray.search(nums, target);
		System.out.println(result);
	}
}
