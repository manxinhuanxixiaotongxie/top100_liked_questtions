package top100;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Code023 {
    // k个一组升序链表
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return Integer.compare(o1.val, o2.val);
            }
        });
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        for (ListNode node : lists) {
            if (node == null) continue;
            pq.add(node);
        }
        while (!pq.isEmpty()) {
            ListNode poll = pq.poll();
            cur.next = poll;
            if (poll.next != null) {
                pq.add(poll.next);
            }
            cur = poll;
        }
        return pre.next;
    }
}
