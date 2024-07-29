package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Triangle {

	public int getMin(int i, int j) {
		return i > j ? j : i;
	}

	public int minimumTotal(List<List<Integer>> triangle) {
		// 5_ 6_ _ _
		//
		// 1 - D
		int res = 0;
		int n = triangle.size();
		int[] dp = new int[n];
		dp[0] = triangle.get(0).get(0);
		res = Math.min(res, dp[0]);
		for (int i = 1; i < n; ++i) {
			int prev = 0;
			res = Integer.MAX_VALUE;
			for (int j = 0; j < triangle.get(i).size(); ++j) {
				int left = Integer.MAX_VALUE, right = Integer.MAX_VALUE;
				if (j != 0) {
					left = prev;
				}
				if (j != triangle.get(i).size() - 1) {
					right = dp[j];
				}
				prev = dp[j]; // saving j
				dp[j] = getMin(left, right) + triangle.get(i).get(j); // overwriting j
				res = getMin(res, dp[j]);
			}
		}
		return res;
	}

	// add driver code for the above function
	public static void main(String[] args) {
		List<List<Integer>> triangle = new ArrayList<>();

		triangle.add(List.of(2));
		triangle.add(Arrays.asList(3, 4));
		triangle.add(Arrays.asList(6, 5, 7));
		triangle.add(Arrays.asList(4, 1, 8, 3));
		Triangle triangle1 = new Triangle();
		System.out.println(triangle1.minimumTotal(triangle));  // prints 11
	}
}
