package top100;

public class Trie {
    Node root;

    public Trie() {
        root = new Node(' ');
    }

    public void insert(String word) {
        char[] str = word.toCharArray();
        Node cur = root;
        for (char c : str) {
            cur.pass++;
            if (cur.next[c - 'a'] == null) {
                cur.next[c - 'a'] = new Node(c);
            }
            cur = cur.next[c - 'a'];
        }
        cur.pass++;
        cur.end++;
    }

    public boolean search(String word) {
        boolean ans = true;
        char[] str = word.toCharArray();
        Node cur = root;
        for (char c : str) {
            if (cur.next[c - 'a'] == null) {
                ans = false;
                break;
            }
            cur = cur.next[c - 'a'];
        }
        if (ans && cur.end > 0) {
            return true;
        }
        return false;
    }

    public boolean startsWith(String prefix) {
        char[] str = prefix.toCharArray();
        Node cur = root;
        for (char c : str) {
            if (cur.next[c - 'a'] == null) {
                return false;
            }
            cur = cur.next[c - 'a'];
        }
        return cur.pass > 0;
    }

    class Node {
        char c;
        int pass;
        int end;
        Node[] next;

        public Node(char c) {
            this.c = c;
            this.pass = 0;
            this.end = 0;
            this.next = new Node[26]; // Assuming only lowercase letters a-z
        }
    }
}
