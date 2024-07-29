package backtracking;

import java.util.ArrayList;
import java.util.List;

public class PermutationSequence {

	public static void backtrack(int n, int k, List<Integer> nums, List<Integer> curr,
		List<List<Integer>> res, int pos) {
		if (curr.size() == n) {
			res.add(new ArrayList<>(curr));
			return;
		}

		for (int i = pos; i < nums.size(); i++) {
			curr.add(nums.get(i));
			backtrack(n, k, nums, curr, res, pos + 1);
			curr.remove(curr.size() - 1);
		}
	}

	// LC #60 Permutation Sequence
	public String getPermutation(int n, int k) {
		List<Integer> nums = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			nums.add(i);
		}

		List<List<Integer>> res = new ArrayList<>();
		backtrack(n, k, nums, new ArrayList<>(), res, 0);

		StringBuilder sb = new StringBuilder();
		for (int i : res.get(k - 1)) {
			sb.append(i);
		}

		return sb.toString();
	}


}
