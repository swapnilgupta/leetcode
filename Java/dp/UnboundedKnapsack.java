package dp;

public class UnboundedKnapsack {

	public static int unboundedKnapsack(int cap, int n, int[] profit, int[] wt) {
		// Create a dynamic programming table to store the maximum value for each capacity
		int[] dp = new int[cap + 1];

		// Iterate over each capacity
		for (int i = 0; i <= cap; i++) {
			// Iterate over each item
			for (int j = 0; j < n; j++) {
				// Check if the weight of the current item is less than or equal to the current capacity
				if (wt[j] <= i) {
					// Update the dp table with the maximum value that can be obtained by including or excluding the current item
					dp[i] = Math.max(dp[i], dp[i - wt[j]] + profit[j]);
				}
			}
		}

		// Return the maximum value that can be obtained with the given capacity
		return dp[cap];
	}

	public static void main(String[] args) {
		// Define the capacity of the knapsack
		int capacity = 100;
		// Define the profit of each item
		int[] profit = {10, 30, 20};
		// Define the weight of each item
		int[] w = {5, 10, 15};
		// Get the number of items
		int n = w.length;

		// Print the maximum profit that can be obtained with the given capacity and items
		System.out.println(unboundedKnapsack(capacity, n, profit, w));
	}
}