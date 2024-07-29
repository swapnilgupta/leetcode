package math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxPrimeDifference {

	public int maximumPrimeDifference(int[] nums) {
		int n = 300001;
		// Sieve of Eratosthenes
		boolean[] prime = new boolean[300001];

		Arrays.fill(prime, true);

		for (int i = 2; i * i < n; i++) {
			if (prime[i]) {

				for (int j = i; j * i < n; j++) {
					prime[j * i] = false;
				}

			}
		}
		// create a list of primes in nums
		List<Integer> primes = new ArrayList<>();
		for (int i = 0; i < nums.length; i++) {
			if (prime[nums[i]]) {
				primes.add(i);
			}
		}
		if (primes.size() < 2) {
			return 0;
		}
		// find the maximum prime difference between indices

		return primes.get(primes.size() - 1) - primes.get(0);
	}

	// nums = [4,2,9,5,3] output: 3
	public static void main(String[] args) {
		MaxPrimeDifference mpd = new MaxPrimeDifference();
		int[] nums = {1, 7};
		System.out.println(mpd.maximumPrimeDifference(nums));
	}

}
