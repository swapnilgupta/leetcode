package dp;

import java.util.ArrayList;
import java.util.List;

public class MaximizeTheGoldHouseOffers {

	public int maximizeTheProfit(int n, List<List<Integer>> offers) {
		int[] dp = new int[n + 1];
		// Offers ending at certain point
		List<List<List<Integer>>> m = new ArrayList<>();

		for (int i = 0; i < n; ++i) {
			m.add(new ArrayList<List<Integer>>());
		}

		for (List<Integer> a : offers) {
			m.get(a.get(1)).add(a);
		}

		for (int e = 1; e <= n; ++e) {
			dp[e] = dp[e - 1];
			for (List<Integer> a : m.get(e - 1)) {
				dp[e] = Math.max(dp[e], dp[a.get(0)] + a.get(2));
			}
		}

		return dp[n];
	}

	// driver code for above function
	public static void main(String[] args) {
		MaximizeTheGoldHouseOffers m = new MaximizeTheGoldHouseOffers();
		List<List<Integer>> offers = new ArrayList<>();
		List<Integer> a = new ArrayList<>();
		a.add(0);
		a.add(0);
		a.add(10);
		offers.add(a);
		a = new ArrayList<>();
		a.add(1);
		a.add(2);
		a.add(5);
		offers.add(a);
		a = new ArrayList<>();
		a.add(0);
		a.add(2);
		a.add(9);
		offers.add(a);
		System.out.println(m.maximizeTheProfit(3, offers));
	}
}
