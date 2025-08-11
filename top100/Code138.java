package top100;

import java.util.HashMap;
import java.util.Map;

public class Code138 {

    // 随机链表的复制

    /**
     * 第一种思路 使用辅助结构
     *
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<Node, Node>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        // 设置random
        cur = head;
        while (cur != null) {
            Node newNode = map.get(cur);
            newNode.next = map.get(cur.next);
            newNode.random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }

    /**
     * O（1）
     *
     * @param head
     * @return
     */
    public Node copyRandomList2(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        while (cur != null) {
            Node next = cur.next;
            Node node = new Node(cur.val);
            cur.next = node;
            node.next = next;
            cur = next;
        }
        // 设置random
        cur = head;
        while (cur != null) {
            Node next = cur.next.next;
            cur.next.random = cur.random == null ? null : cur.random.next;
            cur = next;
        }
        // 拆分链表
        cur = head;
        Node ans = cur.next;
        Node pre = new Node(-1);
        while (cur != null) {
            Node next = cur.next.next;
            pre.next = cur.next;
            pre = pre.next;
            cur.next = next;
            cur = next;
        }

        return ans;
    }
}
