package dp;

import java.util.HashMap;
import java.util.Map;

public class MarkIndicesI {

	// nums[2,2,0] and changeIndices[2,2,2,2,3,2,,2,1]
	public static void main(String[] args) {
		MarkIndicesI mi = new MarkIndicesI();
		int[] nums = {2, 2, 0};
		int[] changeIndices = {2, 2, 2, 2, 3, 2, 2, 1};
		System.out.println(mi.earliestSecondToMarkIndices(nums, changeIndices));
	}

	private boolean canMarkIndices(int[] nums, int[] changeIndices, int idx) {
		Map<Integer, Integer> last = new HashMap<>();
		for (int i = 0; i < idx; i++) {
			last.put(changeIndices[i], i);
		}

		if (last.size() != nums.length) {
			return false;
		}
		int cnt = 0; // record how many we can reduce the number
		for (int i = 0; i < idx; ++i) {
			// if it sis last time we visited this index, we must mark this index
			// so, we check weather this idx already reduced to zero, if not then return false
			if (i == last.get(changeIndices[i])) {
				if (cnt < nums[changeIndices[i] - 1]) {
					return false;
				} else {
					cnt -= nums[changeIndices[i] - 1];
				}
			} else {
				++cnt;
			}
		}
		return true;
	}

	public int earliestSecondToMarkIndices(int[] nums, int[] changeIndices) {
		int n = nums.length;
		int m = changeIndices.length;
		int lo = 0, hi = m + 1;
		while (lo < hi) {
			int mid = lo + (hi - lo) / 2;
			if (canMarkIndices(nums, changeIndices, mid)) {
				hi = mid;
			} else {
				lo = mid + 1;
			}
		}
		return hi == m + 1 ? -1 : hi;
	}
}