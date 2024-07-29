package linkedList;

public class LinkedListUtility {

	public static void printSinglyLinkedList(ListNode head) {
		ListNode curr = head;
		while (curr != null) {
			System.out.print(curr.val + " ");
			curr = curr.next;
		}
	}

}
