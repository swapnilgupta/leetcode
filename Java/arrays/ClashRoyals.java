package arrays;

import java.util.Arrays;

public class ClashRoyals {

	public int[] winTrophies(int[] testCases) {
		int[] result = new int[testCases.length];
		for (int i = 0; i < testCases.length; i++) {
			int n = testCases[i];
			int sum = 0;
			int trophies = 1;
			// Initialize bitwise AND to a value that's not 0
			int bitwise_and = 1;

			// Iterate until sum is less than n or bitwise AND operation isn't zero.
			while (sum + trophies <= n) {
				sum += trophies; // Add to the sum
				// If sum still less than n, perform bitwise and operation
				if (sum + trophies <= n) {
					bitwise_and &= trophies;
				}
				trophies++; // Increase trophies
			}
			if (sum == n && bitwise_and == 0) {
				result[i] = trophies
					- 1; // If sum reaches 'n' and bitwise AND is 0, store the starting trophies
			} else {
				result[i] = -1; // If conditions are not met, return -1
			}
		}
		return result;
	}

	public static void main(String[] args) {
		int[] testCases = {10, 8, 5, 9, 1};
		ClashRoyals cr = new ClashRoyals();
		System.out.println(Arrays.toString(cr.winTrophies(testCases)));
	}


}
