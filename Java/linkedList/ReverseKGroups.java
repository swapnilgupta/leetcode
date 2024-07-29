package linkedList;

public class ReverseKGroups {

	public ListNode reverseKGroup(ListNode head, int k) {
		ListNode begin;
		if (head == null || head.next == null
			|| k == 1) { // if k == 1, no need to reverse or there is only one node
			return head;
		}
		ListNode dummyNode = new ListNode(-1);
		dummyNode.next = head;
		begin = dummyNode;
		int i = 0;
		while (head != null) {
			i++;
			if (i % k == 0) { // Now head will at kth node
				begin = reverse(begin, head.next);
				head = begin.next;
			} else {
				head = head.next; // skipping k nodes
			}
		}
		return dummyNode.next;

	}

	public ListNode reverse(ListNode begin,
		ListNode end) { // 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10
		ListNode start = begin.next;
		ListNode pre = begin;
		ListNode then = start.next;
		while (then != end) {
			start.next = then.next;
			then.next = pre.next;
			pre.next = then;
			then = start.next;
		}
		return start;
	}

	public static void main(String[] args) {
		// driver code for reversing the k - ListNode in a linked list
		ReverseKGroups rkg = new ReverseKGroups();
		ListNode head = new ListNode(1);
		ListNode curr = head;
		for (int i = 2; i <= 10; i++) {
			curr.next = new ListNode(i);
			curr = curr.next;
		}
		ListNode newHead = rkg.reverseKGroup(head, 3);
		while (newHead != null) {
			System.out.print(newHead.val + " ");
			newHead = newHead.next;
		}
	}

}
