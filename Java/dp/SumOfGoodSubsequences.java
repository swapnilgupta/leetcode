package dp;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SumOfGoodSubsequences {
	public static int mod = 1000000007;
	public static int sumOfGoodSubsequences(int[] nums) {
		long res = 0;
		Map<Long, long[]> dp = new HashMap<>(); // index -> [sum, count]
		for(long i : nums) {
			long sum = i, count = 1;
			if (dp.containsKey(i - 1)) { // if there is a subsequence ending with i - 1
				long[] prev = dp.get(i - 1);
				sum = (sum + prev[0]) % mod; // add the sum of the subsequence ending with i - 1
				sum = (sum + prev[1] * i) % mod; // add the sum of the subsequence ending with i - 1 and i
				count = (count + prev[1]) % mod; // add the count of the subsequence ending with i - 1
			}
			if(dp.containsKey(i + 1)) { // if there is a subsequence ending with i + 1
				long[] next = dp.get(i + 1);
				sum = (sum + next[0]) % mod;
				sum = (sum + next[1] * i) % mod;
				count = (count + next[1]) % mod;
			}
			res = (res + sum) % mod;
			long[] cur = dp.getOrDefault(i, new long[2]);
			cur[0] = (cur[0] + sum) % mod;
			cur[1] = (cur[1] + count) % mod;
			dp.put(i, cur);
		}
		return (int) res;
	}


	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter the number of elements: ");
		int n = scanner.nextInt();

		int[] nums = new int[n];
		System.out.println("Enter the elements:");
		for (int i = 0; i < n; i++) {
			nums[i] = scanner.nextInt();
		}

		int result = sumOfGoodSubsequences(nums);

		System.out.println("Sum of Good Subsequences: " + result);

		scanner.close();
	}
}
