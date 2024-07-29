package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class CloneGraph {

	// Definition for a Node.
	class Node {

		public int val;
		public List<Node> neighbors;

		public Node() {
			val = 0;
			neighbors = new ArrayList<Node>();
		}

		public Node(int _val) {
			val = _val;
			neighbors = new ArrayList<Node>();
		}

		public Node(int _val, ArrayList<Node> _neighbors) {
			val = _val;
			neighbors = _neighbors;
		}
	}

	public Node cloneGraph(Node node) {
		if (node == null) {
			return node;
		}
		Queue<Node> queue = new LinkedList<>();
		queue.add(node);
		Map<Node, Integer> visited = new HashMap<>();
		Map<Node, Node> map = new HashMap<>();
		Node ans = null;
		// create a map to store the cloned nodes
		while (!queue.isEmpty()) {
			Node curr = queue.poll();
			visited.put(curr, 1);
			// create a new node with the same value as the current node

			Node newNode = new Node(curr.val);
			if (!map.containsKey(curr)) {
				map.put(curr, newNode);
			}

			if (ans == null) {
				ans = newNode;
			}

			for (Node neighbor : curr.neighbors) {
				if (!visited.containsKey(neighbor)) {
					visited.put(neighbor, 0);
				}
				if (visited.get(neighbor) == 0) {
					queue.add(neighbor);
				}
				// add the neighbor to the new node's neighbors
				if (!map.containsKey(neighbor)) {
					map.put(neighbor, new Node(neighbor.val));
				}
				map.get(curr).neighbors.add(map.get(neighbor));
			}
		}
		return ans;
	}

}
