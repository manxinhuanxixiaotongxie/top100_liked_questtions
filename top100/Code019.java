package top100;

public class Code019 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        int delIndex = len - n;
        if (delIndex == 0) {
            return head.next;
        }
        // 找到要删除的节点
        ListNode deleteNode = null;
        cur = head;
        while (delIndex > 0) {
            cur = cur.next;
            deleteNode = cur;
            delIndex--;
        }
        // cur就是要删除的节点
        ListNode pre = null;
        cur = head;
        while (cur != null) {
            ListNode nextNode = cur.next;
            if (cur == deleteNode) {
                pre.next = cur.next;
            }
            pre = cur;
            cur = nextNode;
        }

        return head;
    }

    public static void main(String[] args) {
        Code019 code019 = new Code019();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
//        head.next.next = new ListNode(3);
//        head.next.next.next = new ListNode(4);
//        head.next.next.next.next = new ListNode(5);
        int n = 1;
        ListNode result = code019.removeNthFromEnd(head, n);
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}
