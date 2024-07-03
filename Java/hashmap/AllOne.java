package hashmap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class AllOne {

	static class NodeDLL {

		int count;
		Set<String> keys = new HashSet<>();
		NodeDLL prev, next;

		NodeDLL(String key, int count) {
			this.keys.add(key);
			this.count = count;
		}

		void insertRight(NodeDLL node) {
			node.prev = this;
			node.next = this.next;
			this.next.prev = node;
			this.next = node;
		}

		void remove() {
			this.prev.next = this.next;
			this.next.prev = this.prev;
		}
	}

	Map<String, NodeDLL> map = new HashMap<>(); // Key --> NodeDLL
	NodeDLL head = new NodeDLL("", -1), tail = new NodeDLL("", -1); // sentinel nodes

	/**
	 * Initialize your data structure here.
	 */
	public AllOne() {
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Inserts a new key <Key> with value 1. Or increments an existing key by 1.
	 */
	public void inc(String key) {
		NodeDLL nodeDLL = head;       // if new key point to the head
		if (map.containsKey(key)) {
			nodeDLL = map.get(key); // not a new key, get from a map
		}
		nodeDLL.keys.remove(key); // remove current key from key
		int count = nodeDLL == head ? 1 : nodeDLL.count + 1; // count + 1
		// inert a right node if its right node's count is not equals to count+1
		if (nodeDLL.next.count != count) {
			nodeDLL.insertRight(new NodeDLL(key, count));
		}
		nodeDLL.next.keys.add(key);     // put this key into its right node's key set
		map.put(key, nodeDLL.next);     // map the key to the node with count+1
		// empty node which should be removed
		if (nodeDLL != head && nodeDLL.keys.isEmpty()) {
			nodeDLL.remove();
		}
	}

	/**
	 * Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
	 */
	public void dec(String key) {
		if (!map.containsKey(key)) {
			return;
		}
		NodeDLL nodeDLL = map.get(key);   // get current node from map
		nodeDLL.keys.remove(key);      // remove key from current node's keys set
		if (nodeDLL.count > 1) {       // find the count - 1 node
			// inert a left node if its left node's count is not equals to count-1
			if (nodeDLL.prev.count != nodeDLL.count - 1) {
				nodeDLL.prev.insertRight(new NodeDLL(key, nodeDLL.count - 1));
			}
			nodeDLL.prev.keys.add(key);   // put this key into its left node's keys set
			map.put(key, nodeDLL.prev);   // map the key to the node with count-1
		} else {
			// remove the key if its count is 1
			map.remove(key);
		}
		if (nodeDLL.keys.isEmpty()) {
			// empty node which should be removed
			nodeDLL.remove();
		}
	}

	/**
	 * Returns one of the keys with maximal value.
	 */
	public String getMaxKey() {
		if (tail.prev == head) {
			return "";
		}
		return tail.prev.keys.iterator().next();
	}

	/**
	 * Returns one of the keys with Minimal value.
	 */
	public String getMinKey() {
		if (head.next == tail) {
			return "";
		}
		return head.next.keys.iterator().next();
	}

	public static void main(String[] args) {
		AllOne obj = new AllOne();
		obj.inc("a");
		obj.inc("b");
		obj.inc("b");
		obj.inc("c");
		obj.inc("c");
		obj.inc("c");
		obj.dec("b");
		obj.dec("b");
		System.out.println(obj.getMinKey());
		obj.dec("a");
		System.out.println(obj.getMaxKey());
		System.out.println(obj.getMinKey());
	}


}
