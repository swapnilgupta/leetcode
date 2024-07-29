package linkedList;

public class MergeTwoSortedLinkedList {

	public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
		// PreHead node
		ListNode preHead = new ListNode(-1);

		// Helper reference
		ListNode prev = preHead;

		// While neither list is exhausted
		while (list1 != null && list2 != null) {
			if (list1.val <= list2.val) {
				prev.next = list1;
				list1 = list1.next;
			} else {
				prev.next = list2;
				list2 = list2.next;
			}

			// Advance the position of prev
			prev = prev.next;
		}

		// At least one list is not exhausted, so point to that list
		prev.next = list1 == null ? list2 : list1;

		return preHead.next;
	}

	// driver code for the above function
	public static void main(String[] args) {
		MergeTwoSortedLinkedList m = new MergeTwoSortedLinkedList();
		ListNode list1 = new ListNode(1);
		list1.next = new ListNode(2);
		list1.next.next = new ListNode(4);
		ListNode list2 = new ListNode(1);
		list2.next = new ListNode(3);
		list2.next.next = new ListNode(4);

		// print the first list
		LinkedListUtility.printSinglyLinkedList(list1);
		System.out.println();
		// print the second list
		LinkedListUtility.printSinglyLinkedList(list2);
		System.out.println();
		ListNode mergedList = m.mergeTwoLists(list1, list2);
		LinkedListUtility.printSinglyLinkedList(mergedList);
	}

}
