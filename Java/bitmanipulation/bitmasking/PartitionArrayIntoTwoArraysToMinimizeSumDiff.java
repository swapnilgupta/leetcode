package bitmanipulation.bitmasking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PartitionArrayIntoTwoArraysToMinimizeSumDiff {

	public int minimumDifference(int[] arr) {
		int diff = Integer.MAX_VALUE;
		int total = Arrays.stream(arr).sum();
		Map<Integer, List<Integer>> mp1 = new HashMap<>();
		Map<Integer, List<Integer>> mp2 = new HashMap<>();
		int n = arr.length / 2;

		// Creating the map for the first half
		for (int i = 0; i < (1 << n); ++i) {
			int setBits = 0, sum = 0;
			for (int j = 0; j < n; ++j) {
				if ((i & (1 << j)) != 0) {
					++setBits;
					sum += arr[j];
				}
			}
			if (!mp1.containsKey(setBits)) {
				mp1.put(setBits, new ArrayList<>());
			}
			mp1.get(setBits).add(sum);
		}

		// creating the map for the second half
		for (int i = 0; i < (1 << n); ++i) {
			int setBits = 0, sum = 0;
			for (int j = 0; j < n; ++j) {
				if ((i & (1 << j)) != 0) {
					++setBits;
					sum += arr[n + j];
				}
			}
			if (!mp2.containsKey(setBits)) {
				mp2.put(setBits, new ArrayList<>());
			}
			mp2.get(setBits).add(sum);
		}

		// from no elements in arr that's i = 0 to all `n` elements in arr that's i <= n
		for (int i = 0; i <= n; ++i) {
			if (mp1.containsKey(i)) {
				Collections.sort(mp1.get(i));
			}

			if (mp2.containsKey(i)) {
				Collections.sort(mp2.get(i));
			}
		}

		for (int i = 0; i <= n; ++i) {
			// a - size of left half
			// b - size of right half
			int a = 0, b = mp2.get(n - i).size() - 1;
			while (a < mp1.get(i).size() && b >= 0) {
				int sum = mp1.get(i).get(a) + mp2.get(n - i).get(b);
				int curDiff = Math.abs(total - 2 * sum);
				diff = Math.min(diff, curDiff);
				if (total / 2 > sum) {
					++a;
				} else {
					--b;
				}
			}
		}
		return diff;
	}

	// add the driver code for this
	public static void main(String[] args) {
		PartitionArrayIntoTwoArraysToMinimizeSumDiff obj = new PartitionArrayIntoTwoArraysToMinimizeSumDiff();
		int[] arr = {3, 9, 7, 3};
		System.out.println(obj.minimumDifference(arr));

		arr = new int[]{-36, 36};
		System.out.println(obj.minimumDifference(arr));

		arr = new int[]{2, -1, 0, 4, -2, -9};
		System.out.println(obj.minimumDifference(arr));
	}

}
