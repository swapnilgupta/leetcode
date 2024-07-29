package arrays;

import java.util.Comparator;
import java.util.PriorityQueue;

public class FindKthLargest {

	public int findKthLargest(int[] nums, int k) {
		// max heap
		PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a));
		for (int num : nums) {
			if (!pq.isEmpty() && pq.size() == k) {
				if (pq.peek() > num) {
					pq.poll();
					pq.add(num);
				}
			} else {
				pq.add(num);
			}
		}
		return pq.isEmpty() ? -1 : pq.peek();
	}
}
