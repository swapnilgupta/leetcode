package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class SolutionTree {

	public class TreeNode {

		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

	void printLevelOrder(TreeNode root) {

	}

	public void setParent(HashMap<TreeNode, TreeNode> parent, TreeNode root) {
		if (root == null) {
			return;
		}
		if (root.left != null) {
			parent.putIfAbsent(root.left, root);
		}
		if (root.right != null) {
			parent.putIfAbsent(root.right, root);
		}
	}

	public TreeNode replaceValueInTree(TreeNode root) {
		if (root == null) {
			return null;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		int level = 0;
		HashMap<TreeNode, Integer> childSum = new HashMap<>();
		HashMap<TreeNode, TreeNode> parent = new HashMap<>();
		parent.putIfAbsent(root, null);
		ArrayList<Integer> levelSum = new ArrayList<>();
		setParent(parent, root);
		while (!queue.isEmpty()) {
			int sz = queue.size();
			int ls = 0;
			for (int i = 0; i < sz; ++i) {
				TreeNode temp = queue.poll();
				int cs = 0;
				assert temp != null;
				if (temp.left != null) {
					queue.add(temp.left);
					cs += temp.left.val;
				}
				if (temp.right != null) {
					queue.add(temp.right);
					cs += temp.right.val;
				}
				childSum.put(temp, cs);
				ls += temp.val;
			}
			levelSum.add(ls);
		}
		Queue<TreeNode> q = new LinkedList<>();
		if (root != null) {
			q.add(root);
		}

		while (!q.isEmpty()) {
			int sz = q.size();
			for (int i = 0; i < sz; ++i) {
				TreeNode temp = q.poll();
				if (parent.get(temp) == null) {
					temp.val = 0;
				} else {
					temp.val = levelSum.get(level) - childSum.get(parent.get(temp));
					System.out.print("level: " + level);
					System.out.print(" parent: " + parent.get(temp));
					System.out.println(" childsum: " + childSum.get(parent.get(temp)));
					System.out.println();
				}
				if (temp.left != null) {
					q.add(temp.left);
				}
				if (temp.right != null) {
					q.add(temp.right);
				}
			}
			++level;
		}
		return root;
	}

	boolean _isSymmetric(TreeNode left, TreeNode right) {
		if (left != null && right == null) {
			return false;
		}
		if (right != null && left == null) {
			return false;
		}
		if (left == null && right == null) {
			return true;
		}
		if (left.val != right.val) {
			return false;
		} else {
			return _isSymmetric(left.left, right.right) && _isSymmetric(left.right, right.left);
		}
	}

	boolean isSymmetric(TreeNode root) {
		if (root == null) {
			return true;
		}

		return _isSymmetric(root.left, root.right);
	}


	public int deepestLeavesSum(TreeNode root) {
		// handling edge case
		if (root == null) {
			return 0;
		}
		// tracking each level sum in 'ans'
		int sum = 0;
		// creating a queue for doing level order traversal
		LinkedList<TreeNode> q = new LinkedList<>();
		q.add(root);
		while (!q.isEmpty()) {
			// number of nodes at current level
			int sz = q.size();
			sum = 0;
			for (int i = 0; i < sz; ++i) {
				TreeNode temp = q.poll();
				// Keep track of current level value in 'ans' variable
				assert temp != null;
				sum += temp.val;
				// Add left child if present
				if (temp.left != null) {
					q.add(temp.left);
				}
				// Add right child if present
				if (temp.right != null) {
					q.add(temp.right);
				}
			}
		}
		return sum;
	}

	public int longestCommonSubsequence(String A, String B) {
		int n = A.length(), m = B.length();
		// Dynamic Programming table
		int[][] dp = new int[n + 1][m + 1];
		for (int i = 1; i <= n; ++i) {
			for (int j = 1; j <= m; ++j) {
				if (A.charAt(i) == B.charAt(j)) {
					dp[i][j] = 1 + dp[i - 1][j - 1];
				} else {
					dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
				}
			}
		}
		return dp[n][m];
	}

	int lcs(int i, int j, String A, String B) {
		// Base cond.
		if (i == A.length() && j == B.length()) {
			return 0;
		}
		// main logic - possibilities all
		else if (A.charAt(i) == B.charAt(j)) {
			return 1 + lcs(i + 1, j + 1, A, B);
		} else {
			return Math.max(lcs(i, j + 1, A, B), lcs(i + 1, j, A, B));
		}
	}

	public int addMinimum(String word) {
		int ans = 0;
		int n = word.length();
		char[] words = word.toCharArray();
		char[] pat = new char[]{'a', 'b', 'c'};
		int j = 0;
		for (int i = 0; i < n; ++i) {
			if (words[i] == pat[j]) {
				j++;
				if (j == 3) {
					j = 0;
				}
			} else {
				ans += (3 - j - 1);
				j = 0;
			}
		}
		return ans;
	}
}
