package arrays;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Candy {

	public int candy(int[] ratings) {
		// sort by ratings
		int ans = 0;
		int n = ratings.length;
		int[] candies = new int[n];
		// sort by ratings while preserving the index in hashmap
		HashMap<Integer, Integer> map = new HashMap<>();
		// put ratings into hashmap
		for (int i = 0; i < n; ++i) {
			map.put(i, ratings[i]);
		}

		// Sorted Map in ascending order
		Map<Integer, Integer> sorted = map.entrySet()
			.stream()
			.sorted(Map.Entry.comparingByValue())
			.collect(Collectors.toMap(
				Map.Entry::getKey,
				Map.Entry::getValue,
				(e1, e2) -> e2,
				LinkedHashMap::new
			));

		for (Map.Entry<Integer, Integer> entry : sorted.entrySet()) {
			int idx = entry.getKey();
			int rating = entry.getValue();
			// check neighbors
			int left = idx - 1;
			int right = idx + 1;
			int c1 = 0, c2 = 0;
			if (left >= 0) {
				if (rating > ratings[left]) {
					c1 = candies[left] + 1;
				} else {
					c1 = 1;
				}
			}

			if (right < n) {
				if (rating > ratings[right]) {
					c2 = candies[right] + 1;
				} else {
					c2 = 1;
				}
			}

			candies[idx] = Math.max(c1, c2);
		}

		for (int c : candies) {
			ans += c;
		}

		return ans;
	}

	// driver code for above
	public static void main(String[] args) {
		Candy candySolver = new Candy();

		int[] ratings = {13, 9, 21, 5, 18, 19};
		int totalCandies = candySolver.candy(ratings);

		System.out.println("The minimum number of candies teacher could give: " + totalCandies);
	}

}
