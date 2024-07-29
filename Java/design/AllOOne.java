package design;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AllOOne {

}


class AllOne {

	public static class NodeDLL {

		NodeDLL prev, next;
		Set<String> keys = new HashSet<>();
		int count;

		NodeDLL(String key, int count) {
			this.keys.add(key);
			this.count = count;
		}

		private void insertRight(NodeDLL node) {
			node.next = this.next;
			node.prev = this;
			this.next.prev = node;
			this.next = node;
		}

		private void remove() {
			this.prev.next = this.next;
			this.next.prev = this.prev;
		}
	}

	Map<String, NodeDLL> m = new HashMap<>(); // key -> DLL Node
	private final NodeDLL head = new NodeDLL("", -1);
	private final NodeDLL tail = new NodeDLL("", -1); // senital nodes

	public AllOne() {
		head.next = tail;
		tail.prev = head;
	}

	public void inc(String key) {
		NodeDLL nodeDLL = head;
		if (m.containsKey(key)) {
			nodeDLL = m.get(key);
			nodeDLL.keys.remove(key);
		}
		int count = nodeDLL == head ? 1 : nodeDLL.count + 1;
		if (nodeDLL.next.count != count) {
			NodeDLL node = new NodeDLL(key, count);
			nodeDLL.insertRight(node);
		}
		nodeDLL.next.keys.add(key);
		m.put(key, nodeDLL.next);

		if (nodeDLL.keys.isEmpty()) { // remove empty node
			nodeDLL.remove();
		}

	}

	public void dec(String key) {
		if (!m.containsKey(key)) {
			return;
		}

		NodeDLL nodeDLL = m.get(key);
		nodeDLL.keys.remove(key);
		if (nodeDLL.count > 1) {
			if (nodeDLL.prev.count != nodeDLL.count - 1) {
				NodeDLL node = new NodeDLL(key, nodeDLL.count - 1);
				nodeDLL.prev.insertRight(node);
			}
			nodeDLL.prev.keys.add(key);
			m.put(key, nodeDLL.prev);
		} else {
			m.remove(key);
		}
		if (nodeDLL.keys.isEmpty()) {
			nodeDLL.remove();
		}
	}

	public String getMaxKey() {
		if (tail.prev == head) {
			return "";
		}
		return tail.prev.keys.iterator().next();
	}

	public String getMinKey() {
		if (head.next == tail) {
			return "";
		}
		return head.next.keys.iterator().next();
	}


}

/**
 * Your AllOne object will be instantiated and called as such: AllOne obj = new AllOne();
 * obj.inc(key); obj.dec(key); String param_3 = obj.getMaxKey(); String param_4 = obj.getMinKey();
 */