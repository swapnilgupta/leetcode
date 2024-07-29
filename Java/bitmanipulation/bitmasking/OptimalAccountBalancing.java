package bitmanipulation.bitmasking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OptimalAccountBalancing {

	private List<Integer> creditList;

	public int minTransfers(int[][] transactions) {
		Map<Integer, Integer> creditMap = new HashMap<>();
		for (int[] t : transactions) {
			creditMap.put(t[0], creditMap.getOrDefault(t[0], 0) - t[2]);
			creditMap.put(t[1], creditMap.getOrDefault(t[1], 0) + t[2]);
		}

		creditList = new ArrayList<>();
		for (int amount : creditMap.values()) {
			if (amount != 0) {
				creditList.add(amount);
			}
		}

		int n = creditList.size();
		int[] memo = new int[1 << n];
		Arrays.fill(memo, -1);
		memo[0] = 0;
		return n - dfs((1 << n) - 1, memo);
	}

	private int dfs(int totalMask, int[] memo) {
		if (memo[totalMask] != -1) {
			return memo[totalMask];
		}

		int balanceSum = 0, answer = 0;

		// remove 1 person at a time in total_mask
		for (int i = 0; i < creditList.size(); ++i) {
			int curBit = 1 << i;
			if ((totalMask & curBit) != 0) {
				balanceSum += creditList.get(i);
				answer = Math.max(answer, dfs(totalMask ^ curBit, memo));
			}
		}

		// if the total balance of total_mask is 0, increment answer by 1
		memo[totalMask] = answer + (balanceSum == 0 ? 1 : 0);
		return memo[totalMask];
	}
}
