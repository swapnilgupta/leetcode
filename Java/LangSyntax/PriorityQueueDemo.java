package LangSyntax;

import java.util.PriorityQueue;

class PriorityQueueDemo {

	// Main Method
	public static void main(String args[]) {
		// Creating empty priority queue
		PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>();

		// Adding items to the pQueue using add()
		pQueue.add(10);
		pQueue.add(20);
		pQueue.add(15);

		pQueue.remove();
		pQueue.add(1);
		// Printing the top element of PriorityQueue
		System.out.println(pQueue.peek());

		// Printing the top element and removing it
		// from the PriorityQueue container
		System.out.println(pQueue.poll());

		// Printing the top element again
		System.out.println(pQueue.peek());
	}

}

class Solution {
	public int getNumWidth(int num) {
		int w = 0;
		if (num == 0)
			return 1;
		if (num < 0) {
			++w;
			num = -num;
		}
		while (num > 0) {
			++w;
			num /= 10;
		}
		return w;
	}

	public int[] findColumnWidth(int[][] grid) {
		int rows = grid.length;
		int cols = grid[0].length;
		int[] ans = new int[cols];

		for (int j = 0; j < cols; ++j) {
			int colWidth = 1;
			for (int i = 0; i < rows; ++i) {
				int w = getNumWidth(grid[i][j]);
				colWidth = Math.max(colWidth, w);
			}
			ans[j] = colWidth;
		}

		return ans;
	}

	public long[] findPrefixScore(int[] nums) {
		int n = nums.length;
		int[] conver = new int[n];
		long[] ans = new long[n];
		int currMax = nums[0];
		long currSum = 0;
		for (int i = 0; i < n; ++i) {
			currMax = Math.max(currMax, nums[i]);
			conver[i] = nums[i] + currMax;
			currSum += conver[i];
			ans[i] = currSum;
		}
		return ans;
	}


}