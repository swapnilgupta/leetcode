package tree;

import java.util.LinkedList;
import java.util.Queue;
import tree.nodeTypes.TreeNode;

class Symmetric {

	public boolean _isSymmetric(TreeNode left, TreeNode right) {
		if (left == null && right == null) {
			return true;
		}
		if (left == null || right == null) {
			return false;
		}
		if (left.val != right.val) {
			return false;
		}

		return _isSymmetric(left.right, right.left) && _isSymmetric(left.left, right.right);
	}

	public boolean isSymmetric(TreeNode root) {
		if (root == null) {
			return true;
		}
		if (root.left == null && root.right == null) {
			return true;
		}
		if (root.left == null || root.right == null) {
			return false;
		}
		return _isSymmetric(root.left, root.right);
	}


	public boolean _isSymmetricIterativeBFS(Queue<TreeNode> q1, Queue<TreeNode> q2) {
		if (q1.isEmpty() && q2.isEmpty()) {
			return true;
		}
		if (q1.isEmpty() || q2.isEmpty()) {
			return false;
		}

		if (q1.size() != q2.size()) {
			return false;
		}

		while (!q1.isEmpty()) {
			int sz = q1.size();
			for (int i = 0; i < sz; ++i) {
				TreeNode n1 = q1.poll();
				TreeNode n2 = q2.poll();
				if (n1 == null && n2 == null) {
					continue;
				}
				if (n1 == null || n2 == null) {
					return false;
				}
				if (n1.val != n2.val) {
					return false;
				}

				q1.add(n1.left);
				q1.add(n1.right);

				q2.add(n2.right);
				q2.add(n2.left);
			}
		}

		return true;
	}

	public boolean isSymmetricIterative(TreeNode root) {
		if (root == null) {
			return true;
		}
		if (root.left == null && root.right == null) {
			return true;
		}
		if (root.left == null || root.right == null) {
			return false;
		}
		Queue<TreeNode> q1 = new LinkedList<>();
		Queue<TreeNode> q2 = new LinkedList<>();
		q1.add(root.left);
		q2.add(root.right);
		return _isSymmetricIterativeBFS(q1, q2);
	}

	// driver code for root =[1,2,2,null,3,null,3]
	public static void main(String[] args) {
		Symmetric obj = new Symmetric();
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(2);
		root.left.left = null;
		root.left.right = new TreeNode(3);
		root.right.left = null;
		root.right.right = new TreeNode(3);

		System.out.println("Is Symmetric: " + obj.isSymmetricIterative(root));
	}


}