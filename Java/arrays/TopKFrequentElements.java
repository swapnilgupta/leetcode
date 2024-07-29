package arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopKFrequentElements {

	public int[] topKFrequent(int[] nums, int k) {
		Map<Integer, Integer> count = new HashMap<>();

		for (int num : nums) {
			count.put(num, count.getOrDefault(num, 0) + 1);
		}

		// create a list of list with fixed size nums.length + 1
		List<List<Integer>> bucket = new ArrayList<>();
		for (int i = 0; i < nums.length + 1; i++) {
			bucket.add(new ArrayList<>());
		}
		for (Integer key : count.keySet()) {
			int freq = count.get(key);
			bucket.get(freq).add(key);
		}
		int[] res = new int[k];
		for (int i = nums.length; i > 0; --i) {
			if (!bucket.get(i).isEmpty()) {
				for (int j = 0; j < bucket.get(i).size(); j++) {
					res[--k] = bucket.get(i).get(j);
					if (k == 0) {
						return res;
					}
				}
			}
		}
		return res;
	}
}
