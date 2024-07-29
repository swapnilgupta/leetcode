package backtracking;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {

	public void backtrack(int[] can, int tar, int sum, List<List<Integer>> res,
		List<Integer> cur, int start) {
		if (sum == tar) {
			res.add(new ArrayList<>(cur));
			return;
		}
		if (sum > tar) {
			return;
		}

		for (int i = start; i < can.length; ++i) {
			cur.add(can[i]);
			backtrack(can, tar, sum + can[i], res, cur, i);
			cur.remove(cur.size() - 1);
		}
	}

	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> res = new ArrayList<>();
		backtrack(candidates, target, 0, res, new ArrayList<>(), 0);
		return res;
	}

	// add the driver main function for the above method
	public static void main(String[] args) {
		CombinationSum cs = new CombinationSum();
		int[] can = {2, 3, 6, 7};
		int tar = 7;
		List<List<Integer>> res = cs.combinationSum(can, tar);
		System.out.println(res);
	}

}
