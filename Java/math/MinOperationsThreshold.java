package math;

import java.util.PriorityQueue;

public class MinOperationsThreshold {

	public static void main(String[] args) {
		MinOperationsThreshold mot = new MinOperationsThreshold();
		/**
		 * [999999999,999999999,999999999]
		 * 1000000000
		 */
		int[] nums = {999999999, 999999999, 999999999};
		System.out.println(mot.minOperations(nums, 1000000000));
	}


	public int minOperations(int[] nums, int k) {
		int n = nums.length;
		// create min heap with the help of priority queue with the element less than k
		PriorityQueue<Long> pq = new PriorityQueue<>();
		for (int num : nums) {
			if (num < k) {
				pq.add((long) num);
			}
		}
		int res = 0;
		while (pq.size() > 1) {
			long x = pq.poll();
			long y = pq.poll();
			long sum = Math.min(x, y) * 2 + Math.max(x, y);
			if (sum < k) {
				pq.add(sum);
			}
			res++;
		}

		return res + pq.size();
	}
}
