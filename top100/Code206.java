package top100;

public class Code206 {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next; // 保存下一个节点
            head.next = pre; // 当前节点指向前一个节点
            pre = head; // 前一个节点更新为当前节点
            head = next; // 当前节点更新为下一个节点
        }
        return pre;
    }
}
