package top100;

public class Code148 {
    // 排序链表

    /**
     * 思路：对链表进行归并排序
     *
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        return process(head, null);
    }

    // 利用归并排序的特性 将整个链表修改成有序的链表
    // 这个函数的含义是 在链表的范围内进行归并排序 并且返回经过归并排序的之后的新的头结点
    public ListNode process(ListNode head, ListNode tail) {
        if (head == tail || head.next == null) {
            return head;
        }
        // 找到中点 从head到tail的终点 这点是上中点或者是下中点不是很重要
        ListNode slow = head;
        ListNode fast = head.next.next;
        // 快慢指针 快指针一次走两步 慢指针一次走一步 知道快指针来到了最后一个位置
        while (fast != tail) {
            slow = slow.next;
            if (fast == null || fast.next == null) {
                break;
            }
            fast = fast.next.next;
        }
        // 中点
        // 切断左右两段链表
        ListNode mid = slow.next;
        slow.next = null;
        // 利用归并排序的特性对链表进行排序
        ListNode l = process(head, null);
        ListNode r = process(mid, null);
        // 合并左右两侧链表
        return merge(l, r);
    }

    public ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        while (head1 != null && head2 != null) {
            if (head1.val <= head2.val) {
                cur.next = head1;
                head1 = head1.next;
            } else {
                cur.next = head2;
                head2 = head2.next;
            }
            cur = cur.next;
        }
        if (head1 != null) {
            cur.next = head1;
        }
        if (head2 != null) {
            cur.next = head2;
        }

        return dummyHead.next;
    }

}
