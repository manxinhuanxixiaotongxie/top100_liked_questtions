package top100;

import org.w3c.dom.ls.LSInput;

import java.util.List;

public class Code024 {
    // 两两交换链表中的节点
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;

        // 上一个分组结束位置 影子节点
        ListNode pre = new ListNode(-1);
        ListNode cur = head;
        ListNode newHead = pre;
        while (cur != null && cur.next != null) {
            ListNode next = cur.next.next;
            ListNode begin = cur;
            ListNode end = cur.next;
            end.next = begin;
            begin.next = next;
            pre.next = end;
            pre = begin;
            cur = next;
        }
        return newHead.next;
    }

    /**
     * 一种通用解法
     * 设置上一段结束的节点与下一段开始的节点
     * 翻转在这段链表的中间节点
     * 后续每K个一组翻转链表都可以这么做
     * 参考Code025
     *
     * @param head
     * @return
     */
    public ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode newHead = new ListNode(-1);
        newHead.next = head;
        ListNode lastEnd = newHead;
        ListNode nextBegin = head.next.next;
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            // 翻转cur与cur.next
            ListNode next = cur.next;
            cur.next = nextBegin;
            next.next = cur;
            lastEnd.next = next;
            lastEnd = cur;
            cur = cur.next;
            nextBegin = (cur == null || cur.next == null) ? null : cur.next.next;
        }
        return newHead.next;
    }
}
