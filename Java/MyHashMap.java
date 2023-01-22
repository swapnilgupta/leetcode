class MyHashMap {
    ListNode[] nodes = new ListNode[100000];

    // Get the element from HashMap
    public int get(int key) {
        // 1. Get index of the element
        int index = getIndex(key);
        // 2. find the node in Linked List associated with the key
        ListNode prev = findElement(index, key);
        return prev.next == null ? -1 : prev.next.value;
    }

    // Insert the element into HashMap
    public void put(int key, int value) {
        int index = getIndex(key);
        ListNode prev = findElement(index, key);

        if (prev.next == null) {
            prev.next = new ListNode(key, value);
        } else {
            prev.next.value = value;
        }
    }

    // Remove the element from HashMap
    public void remove(int key) {
        int index = getIndex(key);
        ListNode prev = findElement(index, key);
        if (prev.next == null)
            return;
        prev.next = prev.next.next;
        // Non - referenced elements will be garbage collected
    }

    private int getIndex(int key) {
        return Integer.hashCode(key) % nodes.length;
    }

    private ListNode findElement(int index, int key) {
        if (nodes[index] == null) {
            return nodes[index] = new ListNode(-1, -1);
        }

        ListNode prev = nodes[index];
        while (prev.next != null && prev.next.key != key) {
            prev = prev.next;
        }
        return prev;
    }

    private static class ListNode {
        int key, value;
        ListNode next;

        ListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
