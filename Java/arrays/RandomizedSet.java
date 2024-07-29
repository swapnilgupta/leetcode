package arrays;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


class RandomizedSet {

	public final Map<Integer, Integer> hm;
	public final List<Integer> nums;
	public int sz;
	java.util.Random rand = new java.util.Random();

	public RandomizedSet() {
		hm = new HashMap<>();
		nums = new LinkedList<>();
		sz = 0;
	}

	public boolean insert(int val) {
		if (hm.containsKey(val)) {
			return false;
		} else {
			hm.put(val, nums.size());
			nums.add(val);
			return true;
		}
	}

	public boolean remove(int val) {
		if (hm.containsKey(val)) {
			int last = nums.get(nums.size() - 1);
			int delIdx = hm.get(val);
			nums.set(delIdx, last);
			hm.put(last, delIdx);
			hm.remove(val);
			nums.remove(nums.size() - 1);
			return true;
		} else {
			return false;
		}
	}

	public int getRandom() {
		return nums.get(rand.nextInt(nums.size()));
	}
}

/**
 * Your RandomizedSet object will be instantiated and called as such: RandomizedSet obj = new
 * RandomizedSet(); boolean param_1 = obj.insert(val); boolean param_2 = obj.remove(val); int
 * param_3 = obj.getRandom();
 */