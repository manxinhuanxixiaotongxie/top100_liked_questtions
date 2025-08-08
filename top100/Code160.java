package top100;

public class Code160 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 链表相交的问题
        int n1 = 0;
        ListNode cur1 = headA;
        while (cur1 != null) {
            n1++;
            cur1 = cur1.next;
        }
        ListNode cur2 = headB;
        while (cur2 != null) {
            n1--;
            cur2 = cur2.next;
        }
        if (n1 > 0) {
            // 说明headA比headB长
            cur1 = headA;
            cur2 = headB;
        } else {
            // 说明headA的长度是小于等于headB的长度
            cur1 = headB;
            cur2 = headA;
        }
        n1 = Math.abs(n1);
        while (n1-- > 0) {
            cur1 = cur1.next;
        }
        // 然后让cu1 cur2同时向下走
        while (cur1 != null && cur2 != null) {
            if (cur1 == cur2) {
                return cur1;
            }
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return null;
    }
}
