package top100;

public class Code021 {
    // 合并两个有序链表
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 影子节点
        ListNode pre = new ListNode(-1);
        ListNode cur1 = list1, cur2 = list2, cur = pre;
        while (cur1 != null && cur2 != null) {
            if (cur1.val <= cur2.val) {
                ListNode next = cur1.next;
                cur1.next = null;
                pre.next = cur1;
                pre = cur1;
                cur1 = next;
            } else {
                ListNode next = cur2.next;
                cur2.next = null;
                pre.next = cur2;
                pre = cur2;
                cur2 = next;
            }
        }
        if (cur1 != null) {
            pre.next = cur1;
        }
        if (cur2 != null) {
            pre.next = cur2;
        }
        return cur.next;
    }
}
