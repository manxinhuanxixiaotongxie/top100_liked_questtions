package top100;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    // lru缓存
    // 双向链表
    Map<Integer, LRUNode> map;
    LRUNode head;
    LRUNode tail;
    int capacity;
    int size;

    public LRUCache(int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;
        this.size = 0;
        head = null;
        tail = null;
    }

    public int get(int key) {
        if (head == null || !map.containsKey(key)) {
            return -1;
        }
        int ans = -1;
        LRUNode cur = map.get(key);
        if (head == tail) {
            // 不做处理
            ans = map.get(key).val;
        } else {
            if (cur == head) {
                // 不做处理
            } else if (cur == tail) {
                cur.pre.next = null;
                tail = cur.pre;
                cur.pre = null;
                cur.next = head;
                head.pre = cur;
                head = cur;

            } else {
                // 中间位置
                cur.pre.next = cur.next;
                cur.next.pre = cur.pre;
                cur.next = head;
                head.pre = cur;
                head = cur;
            }
            ans = map.get(key).val;
        }
        return ans;
    }

    public void put(int key, int value) {
        if (!map.containsKey(key)) {
            if (size >= capacity) {
                if (head == tail) {
                    map.remove(tail.key);
                    head = null;
                    tail = null;
                } else {
                    map.remove(tail.key);
                    tail = tail.pre;
                    tail.next = null;
                }
                size--;
            }
            LRUNode newNode = new LRUNode(key, value);
            if (head == null) {
                head = newNode;
                tail = newNode;
                map.put(key, newNode);
            } else if (head == tail) {
                newNode.next = head;
                head.pre = newNode;
                head = newNode;
                map.put(key, newNode);
            } else {
                newNode.next = head;
                head.pre = newNode;
                head = newNode;
                map.put(key, newNode);
            }
            size++;
        } else {
            // 已经存在
            LRUNode cur = map.get(key);
            if (head == tail || cur == head) {
                cur.val = value;
            } else {
                if (cur == tail) {
                    cur.val = value;
                    cur.pre.next = null;
                    tail = cur.pre;
                    cur.pre = null;
                    cur.next = head;
                    head.pre = cur;
                    head = cur;
                } else {
                    // 中间位置
                    cur.val = value;
                    cur.pre.next = cur.next;
                    cur.next.pre = cur.pre;
                    cur.next = head;
                    head.pre = cur;
                    head = cur;
                }
            }
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(2, 1);
        lruCache.put(1, 1);
        lruCache.put(2, 3);
        lruCache.put(4, 1);
        System.out.println(lruCache.get(1)); // 返回 1
        System.out.println(lruCache.get(2)); // 返回 3
    }
}


class LRUNode {
    int val;
    int key;
    LRUNode pre;
    LRUNode next;

    LRUNode(int key, int val) {
        this.key = key;
        this.val = val;
        this.pre = null;
        this.next = null;
    }
}

