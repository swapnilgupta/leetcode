package tree;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class NAryTreeSerialization {

	final static int N = 5; // 5 - Ary Tree
	final static int MARKER = ')';

	// A Node a N-Ary Tree
	static class Node {

		char key;
		Node[] child; // Array of pointers for N children

		Node(char key) {
			this.key = key;
			child = new Node[N];
		}
	}

	// This function stores the given N-ary tree in a file pointed by fp
	static void serialize(Node root, PrintWriter writer) {
		// Base Case
		if (root == null) {
			return;
		}

		// Else, store current node and recur for its children
		writer.print(root.key + " ");
		for (int i = 0; i < N && root.child[i] != null; ++i) {
			serialize(root.child[i], writer);
		}

		// when child is done then write the marker
		writer.print(MARKER + " ");
	}

	static Node deSerialize(BufferedReader reader) throws IOException {
		// Read next item from file. If there are no more items or next
		// item is marker, then return null to indicate this
		int val = reader.read();
		if (val == -1 || val == MARKER) {
			return null;
		}
		char c = (char) val;
		// Else create a node with this item and recur for its children
		Node node = new Node(c);
		for (int i = 0; i < N; ++i) {
			node.child[i] = deSerialize(reader);
			if (node.child[i] == null) {
				break;
			}
		}
		return node;
	}
}
