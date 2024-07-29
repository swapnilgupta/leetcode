package linkedList;

import java.util.HashMap;
import java.util.Map;

/*
  Your LRUCache object will be instantiated and called as such: LRUCache obj = new
  LRUCache(capacity); int param_1 = obj.get(key); obj.put(key,value);
 */
class LRUCache {

	public static class DoublyLinkedListNode {

		int key;
		int val;
		DoublyLinkedListNode prev;
		DoublyLinkedListNode next;

		DoublyLinkedListNode(int key, int val) {
			this.key = key;
			this.val = val;
		}
	}

	int cap;
	Map<Integer, DoublyLinkedListNode> cache;
	DoublyLinkedListNode head = new DoublyLinkedListNode(-1, -1); // head.next is the first node
	DoublyLinkedListNode tail = new DoublyLinkedListNode(-1, -1); // tail.prev is the last node
	// head and tail are the senitals for the LinkedList

	public LRUCache(int capacity) {
		this.cap = capacity;
		cache = new HashMap<>();
		head.next = tail; // Initially, both senitals pointing to each other
		tail.prev = head;
	}

	/*
	Adds a node to this list at the first
	 */
	public void addNode(DoublyLinkedListNode node) {
		DoublyLinkedListNode temp = head.next;

		node.prev = head;
		node.next = head.next;

		head.next = node;
		temp.prev = node;
	}

	public void deleteNode(DoublyLinkedListNode node) {
		DoublyLinkedListNode prev = node.prev;
		DoublyLinkedListNode next = node.next;

		prev.next = next;
		next.prev = prev;
	}

	public int get(int key) {
		if (cache.containsKey(key)) {
			DoublyLinkedListNode resNode = cache.get(key);
			int ans = resNode.val;

			cache.remove(key);
			deleteNode(resNode);
			addNode(resNode);

			cache.put(key, resNode);
			return ans;
		}
		return -1;
	}

	public void put(int key, int value) {
		if (cache.containsKey(key)) {
			DoublyLinkedListNode curr = cache.get(key);
			cache.remove(key);
			deleteNode(curr);
		}
		// check for overflow of cache
		if (cache.size() == cap) {
			cache.remove(tail.prev.key);
			deleteNode(tail.prev);
		}
		addNode(new DoublyLinkedListNode(key, value));
		cache.put(key, head.next);
	}
}

class Main {

	public static void main(String[] args) {
		LRUCache lruCache = new LRUCache(2);

		// corresponds to ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
		lruCache.put(1, 1);
		lruCache.put(2, 2);

		System.out.println(lruCache.get(1));  // prints: 1

		lruCache.put(3, 3);

		System.out.println(lruCache.get(2));  // prints: -1

		lruCache.put(4, 4);

		System.out.println(lruCache.get(1));  // prints: -1
		System.out.println(lruCache.get(3));  // prints: 3
		System.out.println(lruCache.get(4));  // prints: 4
	}
}

