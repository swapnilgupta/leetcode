package design;

class ListNode {

	int key, val;
	ListNode next;

	public ListNode(int key, int val, ListNode next) {
		this.key = key;
		this.val = val;
		this.next = next;
	}
}

/**
 * Your MyHashMap object will be instantiated and called as such: MyHashMap obj = new MyHashMap();
 * obj.put(key,value); int param_2 = obj.get(key); obj.remove(key);
 */
class MyHashMap {
	// https://leetcode.com/problems/design-hashmap/solutions/1097755/js-python-java-c-updated-hash-array-solutions-w-explanation/

	private static final int size = 19997; // big enough prime number
	private static final int mult = 12582917;
	ListNode[] data;

	public MyHashMap() {
		this.data = new ListNode[size];
	}

	private int hash(int key) {
		return (int) ((long) key * mult % size);
	}

	public void put(int key, int val) {
		remove(key);
		int h = hash(key);
		ListNode node = new ListNode(key, val, data[h]);
		data[h] = node;
	}

	public int get(int key) {
		int h = hash(key);
		ListNode node = data[h];
		while (node != null) {
			if (node.key == key) {
				return node.val;
			}
			node = node.next;
		}
		return -1;
	}

	public void remove(int key) {
		int h = hash(key);
		ListNode node = data[h];
		if (node == null) {
			return;
		}
		if (node.key == key) {
			data[h] = node.next;
		}
		while (node.next != null) {
			if (node.next.key == key) {
				node.next = node.next.next;
				return;
			}
			node = node.next;
		}
	}

	// add driver code here for sample run for hashmap
	public static void main(String[] args) {
		MyHashMap hashMap = new MyHashMap();

		hashMap.put(1, 1);
		hashMap.put(2, 2);

		System.out.println(hashMap.get(1)); // returns 1
		System.out.println(hashMap.get(3)); // returns -1 (not found)

		hashMap.put(2, 3); // update the existing value
		System.out.println(hashMap.get(2)); // returns 3

		hashMap.remove(2); // remove the mapping for 2
		System.out.println(hashMap.get(2)); // returns -1 (not found), hashMap is {1=1}
	}
}


