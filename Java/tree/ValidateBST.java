package tree;

public class ValidateBST {

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

	public boolean isValidBST(TreeNode root) {
		int max = Integer.MAX_VALUE, min = Integer.MIN_VALUE;
		return isValidBST(root, max, min);
	}

	// check if this is a valid binary search tree
	public boolean isValidBST(TreeNode root, int max, int min) {
		if (root == null) {
			return true;
		}
		if (root.val >= max || root.val <= min) {
			return false;
		}

		return isValidBST(root, root.val, min) && isValidBST(root, max, root.val);
	}

}
