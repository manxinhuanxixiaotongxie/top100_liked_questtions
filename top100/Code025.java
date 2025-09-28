package top100;

public class Code025 {
    // k个一组翻转链表
    public ListNode reverseKGroup(ListNode head, int k) {
        // 上一组的结束节点
        ListNode pre = new ListNode(-1);
        ListNode newHead = pre;
        pre.next = head;
        // 分组  每组k个数一组
        int index = 0;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            if (++index == k) {
                // 返回新头部
                ListNode preTemp = pre.next;
                ListNode reverse = reverse(pre.next, next);
                pre.next = reverse;
                preTemp.next = next;
                pre = preTemp;
                index = 0;
            }
            cur = next;
        }
        return newHead.next;
    }

    public ListNode reverse(ListNode head, ListNode end) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != end) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /**
     * 通用做法
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup2(ListNode head, int k) {
        // 影子节点
        ListNode newHead = new ListNode(-1);
        newHead.next = head;
        // 上一组的结束节点
        ListNode lastEnd = newHead;
        ListNode cur = head;
        // 下一组的开始节点
        ListNode nextBegin;
        int index = 0;
        while (cur != null) {
            nextBegin = cur.next;
            if (++index == k) {
                // 翻转从lastEnd到nextBegin之间的节点
                ListNode lastEndTemp = lastEnd.next;
                ListNode reverse = reverse2(lastEnd.next, nextBegin);
                lastEnd.next = reverse;
                lastEnd = lastEndTemp;
                index = 0;
            }
            cur = nextBegin;
        }
        return newHead.next;
    }

    public ListNode reverse2(ListNode head, ListNode end) {
        ListNode pre = end;
        ListNode cur = head;
        while (cur != end) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }


    public static void main(String[] args) {
        Code025 code025 = new Code025();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        ListNode result = code025.reverseKGroup(head, 2);
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}
