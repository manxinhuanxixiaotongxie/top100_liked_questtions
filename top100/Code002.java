package top100;

public class Code002 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 翻转两个链表
        int add = 0;
        ListNode cur1 = l1;
        ListNode cur2 = l2;
        ListNode newHead = new ListNode(0);
        ListNode pre = newHead;
        while (cur1 != null && cur2 != null) {
            ListNode next1 = cur1.next;
            ListNode next2 = cur2.next;
            int sum = cur1.val + cur2.val + add;
            add = sum / 10;
            ListNode newNode = new ListNode(sum % 10);
            pre.next = newNode;
            pre = newNode;
            cur1 = next1;
            cur2 = next2;
        }

        while (cur1 != null) {
            ListNode next1 = cur1.next;
            int sum = cur1.val + add;
            add = sum / 10;
            ListNode newNode = new ListNode(sum % 10);
            pre.next = newNode;
            pre = newNode;
            cur1 = next1;
        }
        while (cur2 != null) {
            ListNode next2 = cur2.next;
            int sum = cur2.val + add;
            add = sum / 10;
            ListNode newNode = new ListNode(sum % 10);
            pre.next = newNode;
            pre = newNode;
            cur2 = next2;
        }
        if (add != 0) {
            ListNode newNode = new ListNode(add);
            pre.next = newNode;
            pre = newNode;
        }
        return newHead.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(9);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        l2.next.next.next = new ListNode(9);

        Code002 code002 = new Code002();
        ListNode result = code002.addTwoNumbers(l1, l2);
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}
