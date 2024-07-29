package slidingWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringWithConcatenationOfAllWords {

	public long maxSum(List<Integer> nums, int m, int k) {
		int n = nums.size();
		Map<Integer, Integer> hm = new HashMap<>();
		int currSum = 0;
		long sum = 0;
		int dis = 0;
		int i = 0, j = 0;
		while (i < n - k + 1 && j < n) {
			while ((j - i + 1) <= k) {
				int num = nums.get(j);
				currSum += num;
				if (hm.containsKey(num)) {
					hm.put(num, hm.get(num) + 1);
				} else {
					hm.put(num, 1);
					++dis;
				}
				++j;
			}

			if (dis >= m && currSum > sum) {
				sum = currSum;
			}

			// forwarding a window from left and resetting things
			int num = nums.get(i);
			currSum -= num;
			if (hm.get(num) == 1) {
				--dis;
			}
			hm.put(num, hm.get(num) - 1);
			if (hm.get(num) == 0) {
				hm.remove(num);
			}
			++i;

			// next, we will try to add 1 more jth element
		}
		return sum;
	}

	// driver code for the above program
    /*
        nums = [2,6,7,3,1,7], m = 3, k = 4
        nums = [5,9,9,2,4,5,4], m = 1, k = 3
        nums = [1,2,1,2,1,2,1], m = 3, k = 3


        driver code which tests above test cases
         */
	public static void main(String[] args) {
		StringWithConcatenationOfAllWords obj = new StringWithConcatenationOfAllWords();
		List<Integer> nums = new ArrayList<>();
		nums.add(2);
		nums.add(6);
		nums.add(7);
		nums.add(3);
		nums.add(1);
		nums.add(7);
		int m = 3, k = 4;
//        System.out.println(obj.maxSum(nums, m, k));

		nums.clear();
		nums.add(5);
		nums.add(9);
		nums.add(9);
		nums.add(2);
		nums.add(4);
		nums.add(5);
		nums.add(4);
		m = 1;
		k = 3;
//        System.out.println(obj.maxSum(nums, m, k));

		nums.clear();
		nums.add(1);
		nums.add(2);
		nums.add(1);
		nums.add(2);
		nums.add(1);
		nums.add(2);
		nums.add(1);
		m = 3;
		k = 3;
		System.out.println(obj.maxSum(nums, m, k));

	}
}