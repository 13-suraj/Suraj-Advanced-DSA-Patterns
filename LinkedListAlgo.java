package LinkedList;

public class LinkedList {
    public static Node getNthNodeFromLast(Node head, int n) {
        Node back = head, front = head;
        for(int i = 0; i < n; i++) {
            front = front.next;
        }
        if(front == null) return head;
        while(front != null) {
            back = back.next;
            front = front.next;
        }
        return back;
    }

    public static boolean hasCycle(Node head) {
        Node slow = head, fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast) return true;
        }
        return false;
    }

    public static int lengthOfCycle(Node head) {
        Node slow = head, fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast) {
                int length = 1;
                Node temp = slow.next;
                while(temp != slow) {
                    temp = temp.next;
                    length += 1;
                }
                return length;
            }
        }
        return 0;
    }

    public static Node startNodeCycle(Node head) {
        Node slow = head, fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast) {
                slow = head;
                while(slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }

    public static Node getMid(Node head) {
        Node slow = head, fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static Node reverse(Node head) {
        Node prev = null, curr = head, front = null;
        while(curr != null) {
            front = curr.next;
            curr.next = prev;
            prev = curr;
            curr = front;
        }
        return prev;
    }

    public static Node getMidAndReverse(Node head) {
        Node curr = head, fast = head, prev = null;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            Node temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return head;
    }

    public static Node mergeSortedLists(Node list1 , Node list2) {
        Node dummyHead = new Node(0), dummy = dummyHead;
        while(list1 != null && list2 != null) {
            if(list1.val < list2.val) {
                dummy.next = list1;
                list1 = list1.next;
                dummy = dummy.next;
            } else {
                dummy.next = list2;
                list2 = list2.next;
                dummy = dummy.next;
            }
        }
        dummy.next = (list1 == null) ? list2 : list1;
        return dummyHead.next;
    }

    public static Node rotateList(Node head, int k) {
        if(head == null || head.next == null) return head;
        Node curr = head; int size = 1;
        while(curr.next != null) {
            size += 1;
            curr  = curr.next;
        }
        curr.next = head;

        k = k % size;
        int stepsToNewTail = size - k - 1;

        curr = head;
        for(int  i = 0; i < stepsToNewTail; i++) {
            curr = curr.next;
        }
        head = curr.next;
        curr.next = null;
        return head;
    }
}

class Node {
    int val;
    Node next;

    Node(int val) {
        this.val = val;
        this.next = null;
    }

    Node(int val, Node next) {
        this.val = val;
        this.next = next;
    }
}
