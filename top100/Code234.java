package top100;

import java.util.Stack;

public class Code234 {
    // 使用辅助结构
    public boolean isPalindrome(ListNode head) {
        // 判断一个链表是否是回文链表
        Stack<ListNode> stack = new Stack<>();
        ListNode current = head;
        while (current != null) {
            stack.push(current);
            current = current.next;
        }
        current = head;
        while (current != null) {
            ListNode top = stack.pop();
            if (top.val != current.val) {
                return false; // 如果栈顶元素和当前元素不相等，则不是回文链表
            }
            current = current.next;
        }
        return true; // 如果所有元素都相等，则是回文链表
    }

    /**
     * 最优解 断开链表 然后重新拼接上链表
     *
     * @param head
     * @return
     */
    public boolean isPalindrome2(ListNode head) {
        if (head == null || head.next == null) {
            return true; // 空链表或单节点链表是回文链表
        }
        // 判断一个链表是否是回文链表
        ListNode slow = head;
        ListNode fast = head.next;
        // 找到链表的中点
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode pre = null;
        ListNode cur = slow;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        ListNode temp1 = pre;
        ListNode temp2 = head;
        while (temp2 != null) {
            ListNode next1 = temp1.next;
            ListNode next2 = temp2.next;
            if (temp1.val != temp2.val) {
                return false; // 如果两个链表的值不相等，则不是回文链表
            }
            temp1 = next1;
            temp2 = next2;
        }
        // 翻转链表
        temp1 = pre;
        pre = null;
        while (temp1 != null) {
            ListNode next1 = temp1.next;
            temp1.next = pre;
            pre = temp1;
            temp1 = next1;
        }
        return true; // 如果所有元素都相等，则是回文链表
    }
}
