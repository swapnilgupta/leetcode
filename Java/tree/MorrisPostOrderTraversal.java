package tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MorrisPostOrderTraversal {

	public static class TreeNode {

		int val;
		TreeNode left, right;

		public TreeNode(int item) {
			val = item;
			left = right = null;
		}

		public TreeNode() {
			val = 0;
			left = right = null;
		}
	}

	public static void morrisPostOrderTraversal(TreeNode root) {
		List<Integer> postOrder = new ArrayList<>();
		TreeNode current = root;
		while (current != null) {
			if (current.right == null) {
				postOrder.add(
					current.val); // left nothing to process, so this is the inorder node to print
				current = current.left;
			} else {
				TreeNode pre = current.right; // Predecessor
				while (pre.left != null && pre.left != current) {
					pre = pre.left;
				}

				if (pre.left == null) {
					pre.left = current;
					postOrder.add(current.val); // as reverse of preorder left will be printed first
					current = current.right;
				} else {
					pre.left = null;
					current = current.left;
				}
			}
		}

		Collections.reverse(postOrder);
		// print postOrder
		for (int i : postOrder) {
			System.out.print(i + " ");
		}
	}

	public void printPostOrderRecursive(TreeNode root) {
		if (root == null) {
			return;
		}
		printPostOrderRecursive(root.left);
		printPostOrderRecursive(root.right);
		System.out.print(root.val + " ");
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);
		root.left.left.left = new TreeNode(8);
		root.left.left.right = new TreeNode(9);
		root.left.right.left = new TreeNode(10);
		root.left.right.right = new TreeNode(11);
		morrisPostOrderTraversal(root);

		System.out.println();
		MorrisPostOrderTraversal obj = new MorrisPostOrderTraversal();
		obj.printPostOrderRecursive(root);
	}

}
