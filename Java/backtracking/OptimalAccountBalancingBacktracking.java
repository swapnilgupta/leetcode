package backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/optimal-account-balancing
public class OptimalAccountBalancingBacktracking {

	public static void main(String[] args) {
		System.out.println("OptimalAccountBalancingBacktracking");
	}

	private List<Integer> creditList;

	public int minTransfers(int[][] transactions) {
		Map<Integer, Integer> creditMap = new HashMap<>();
		for (int[] t : transactions) {
			creditMap.put(t[0], creditMap.getOrDefault(t[0], 0) - t[2]);
			creditMap.put(t[1], creditMap.getOrDefault(t[1], 0) + t[2]);
		}

		creditList = new ArrayList<>();

		for (int v : creditMap.values()) {
			if (v != 0) {
				creditList.add(v);
			}
		}

		int n = creditList.size();
		return dfs(0, n);
	}

	private int dfs(int cur, int n) {
		// skip zeros
		while (cur < n && creditList.get(cur) == 0) {
			cur++;
		}

		if (cur == n) {
			return 0;
		}

		int min = Integer.MAX_VALUE;
		for (int nxt = cur + 1; nxt < n; ++nxt) {
			if (creditList.get(nxt) * creditList.get(cur) < 0) {
				creditList.set(nxt, creditList.get(nxt) + creditList.get(cur));
				min = Math.min(min, 1 + dfs(cur + 1, n)); // reverting
				creditList.set(nxt, creditList.get(nxt) - creditList.get(cur));
			}
		}
		return min;
	}

}
