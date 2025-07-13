
public class SplitList {
	
	/* Recursive funciton to split a list L into two lists. The first list (ret[0]) contains all elements at odd positions (position starts from 1). The second list (ret[1]) contains all elements at even positions.
		*/
	private static ListNode[] split(ListNode L) {
		ListNode [] ret = new ListNode[2];
		int count = 0;
		
		// TODO: This returns null currently. Modify this function to return the split lists.
		for(ListNode i = L; i != null; i = i.next) {
			if (count % 2 == 0) {
				ret[0] = new ListNode(i.data);
			} else {
				ret[1] = new ListNode(i.data);
			}
		}
		
		ret[0] = null;
		ret[1] = null;
		return ret;
	}

	private static void print(ListNode L) {
		for (ListNode cur = L; cur != null; cur = cur.next)
			System.out.print(cur.data + " ");
		System.out.println();
	}

	public static void main(String [] args) {
		MyList L = new MyList();
		ListNode head = L.getHead();		// Get the head node of the linked list.

		ListNode [] R = split(head);		// Split into two list. The first list contains all elements in odd positions, the second contains all elements at even positions.
		System.out.println("Printing the list with odd positions");
		print(R[0]);
		System.out.println("Printing the list with even positions");
		print(R[1]);
	}
}
