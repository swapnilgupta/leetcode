package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import tree.nodeTypes.TreeNode;

class BinaryTree {

	// Root of Binary Tree
	TreeNode root;

	public void insert(int key) {
		TreeNode newNode = new TreeNode(key);
		if (root == null) {
			root = newNode;
			return;
		}

		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);

		while (!queue.isEmpty()) {
			TreeNode temp = queue.poll();

			if (temp.left == null) {
				temp.left = newNode;
				break;
			} else {
				queue.add(temp.left);
			}

			if (temp.right == null) {
				temp.right = newNode;
				break;
			} else {
				queue.add(temp.right);
			}
		}
	}

	/* Given a binary tree, print its nodes in inorder */
	void printInorder(TreeNode node) {
		if (node == null) {
			return;
		}

		/* first recur on left child */
		printInorder(node.left);

		// print root
		System.out.print(node.val + " ");

		// now recur on right child
		printInorder(node.right);
	}

	// wrappers over recursive functions
	void printInorder() {
		printInorder(root);
	}

	/* Given a binary tree, print its nodes in post-order */
	void printPostorder(TreeNode node) {
		if (node == null) {
			return;
		}

		// recur for the left
		printPostorder(node.left);
		// recur for the right
		printPostorder(node.right);

		// print the root
		System.out.print(node.val + " ");
	}

	// wrapper function
	void printPostorder() {
		printPostorder(root);
	}

	/*
	write a func to print nodes of a tree in preorder fashion
	 */
	void printPreorder(TreeNode node) {
		if (node == null) {
			return;
		}
		System.out.print(node.val + " ");
		printPreorder(node.left);
		printPreorder(node.right);
	}

	// write a wrapper func
	void printPreorder() {
		printPreorder(root);
	}

	/*
	Given a binary tree. Print its nodes in level order
	using an array for implementing queue
	*/
	void printLevelOrder(TreeNode root) {
		if (root == null) {
			return;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);

		while (!queue.isEmpty()) {
			int sz = queue.size();
			for (int i = 0; i < sz; ++i) {
				TreeNode temp = queue.poll();
				if (temp.left != null) {
					queue.add(temp.left);
				}
				if (temp.right != null) {
					queue.add(temp.right);
				}

				System.out.print(temp.val + " ");
			}
		}
	}

}

class BinaryTreeWithRandomPointer extends BinaryTree {

	void addRandomPointers() {
		if (root == null) {
			return;
		}

		List<TreeNode> nodes = new ArrayList<>();
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			TreeNode temp = queue.poll();
			nodes.add(temp);
			if (temp.left != null) {
				queue.add(temp.left);
			}
			if (temp.right != null) {
				queue.add(temp.right);
			}
		}

		Random random = new Random();
		for (TreeNode node : nodes) {
			node.random = nodes.get(random.nextInt(nodes.size()));
		}
	}
}

class CloneBinaryTree {

	Map<TreeNode, TreeNode> map = new HashMap<>();

	public TreeNode cloneTree(TreeNode root) {
		if (root == null) {
			return null;
		}
		if (map.containsKey(root)) {
			return map.get(root);
		}

		TreeNode newRoot = new TreeNode(root.val);
		map.put(root, newRoot);
		newRoot.left = cloneTree(root.left);
		newRoot.right = cloneTree(root.right);
		newRoot.random = cloneTree(root.random);
		return newRoot;
	}
}

public class TreeTraversals {

	public static void main(String[] args) {
		// Create a BinaryTree instance
		BinaryTree tree = new BinaryTree();

		// Insert nodes into the tree
		tree.insert(1);
		tree.insert(2);
		tree.insert(3);
		tree.insert(4);
		tree.insert(5);

		// Print traversals
		System.out.println("Inorder traversal of the Tree: ");
		tree.printInorder();

		System.out.println("\nPostorder traversal of the Tree: ");
		tree.printPostorder();

		System.out.println("\nPreorder traversal of the Tree: ");
		tree.printPreorder();

		System.out.println("\nLevel Order traversal of the Tree: ");
		tree.printLevelOrder(tree.root);

		// Create a BinaryTreeWithRandomPointer instance
		BinaryTreeWithRandomPointer treeWithRandom = new BinaryTreeWithRandomPointer();
		treeWithRandom.root = tree.root; // Use the same tree structure
		treeWithRandom.addRandomPointers();

		// Clone the tree
		CloneBinaryTree cloner = new CloneBinaryTree();
		TreeNode clonedRoot = cloner.cloneTree(tree.root);

		// Print traversals of the cloned tree
		System.out.println("\nInorder traversal of the Cloned Tree: ");
		tree.printInorder(clonedRoot);

		System.out.println("\nPostorder traversal of the Cloned Tree: ");
		tree.printPostorder(clonedRoot);

		System.out.println("\nPreorder traversal of the Cloned Tree: ");
		tree.printPreorder(clonedRoot);

		System.out.println("\nLevel Order traversal of the Cloned Tree: ");
		tree.printLevelOrder(clonedRoot);
	}
}

