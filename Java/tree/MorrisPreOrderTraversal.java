package tree;

public class MorrisPreOrderTraversal {

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

	public static void displayPreOrderRecursive(TreeNode root) {
		if (root == null) {
			return;
		}
		System.out.print(root.val + " ");
		displayPreOrderRecursive(root.left);
		displayPreOrderRecursive(root.right);
	}

	public static void morrisPreOrderTraversal(TreeNode root) {
		TreeNode current = root;
		while (current != null) {
			if (current.left == null) {
				System.out.print(current.val
					+ " "); // left nothing to process, so this is the inorder node to print
				current = current.right;
			} else {
				TreeNode pre = current.left; // Predecessor
				while (pre.right != null && pre.right != current) {
					pre = pre.right;
				}

				if (pre.right == null) {
					pre.right = current;
					// before going to the left, print the current node as it's preorder
					System.out.print(current.val
						+ " "); // make current as right child of its inorder predecessor
					current = current.left; // go to left subtree
				} else {
					pre.right = null;
					current = current.right;
				}
			}
		}
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
		System.out.println("Iterative: ");
		morrisPreOrderTraversal(root);
		System.out.println();
		System.out.println("Recursive: ");
		displayPreOrderRecursive(root);
	}

}
