package tree;

import java.util.LinkedList;
import java.util.Queue;

class Node {

	int val;
	Node left, right;

	public Node(int item) {
		val = item;
		left = right = null;
	}
}

class BinaryTree {

	// Root of Binary Tree
	Node root;

	BinaryTree() {
		root = null;
	}

	/* Given a binary tree, print its nodes in inorder */
	void printInorder(Node node) {
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
	void printPostorder(Node node) {
		if (node == null) {
			return;
		}

		// recur for a left
		printPostorder(node.left);
		// recur for a right
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
	void printPreorder(Node node) {
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
	void printLevelOrder() {
		if (root == null) {
			return;
		}
		Queue<Node> queue = new LinkedList<>();
		queue.add(root);

		while (!queue.isEmpty()) {
			int sz = queue.size();
			for (int i = 0; i < sz; ++i) {
				Node temp = queue.poll();
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

public class TreeTraversals {

	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree();
		tree.root = new Node(1);
		tree.root.left = new Node(2);
		tree.root.right = new Node(3);
		tree.root.left.left = new Node(4);
		tree.root.left.right = new Node(5);

		System.out.println("Inorder traversal of the Tree: ");
		tree.printInorder();

		System.out.println("\nPostorder traversal of the Tree: ");
		tree.printPostorder();

		System.out.println("\nPreorder traversal of the Tree: ");
		tree.printPreorder();

		System.out.println("\nLevel Order traversal of the Tree: ");
		tree.printLevelOrder();

	}
}
