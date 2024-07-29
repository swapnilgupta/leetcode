package arrays;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class KSmallestPairs {

	public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
		List<List<Integer>> ans = new ArrayList<>();
		int m = nums1.length, n = nums2.length;
		// use a min heap to store the pairs
		// the heap will be sorted based on the sum of the pairs
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] + a[1]) - (b[0] + b[1]));
		// TODO: add the pairs to the heap
		return ans;
	}

	// driver code for above
	// nums1 = [1,1,2], nums2 = [1,2,3], k = 2
	public static void main(String[] args) {
		KSmallestPairs ksp = new KSmallestPairs();
		int[] nums1 = {1, 1, 2};
		int[] nums2 = {1, 2, 3};
		int k = 10;
		List<List<Integer>> ans = ksp.kSmallestPairs(nums1, nums2, k);
		for (List<Integer> pair : ans) {
			System.out.println(pair.get(0) + " " + pair.get(1));
		}
	}

}
