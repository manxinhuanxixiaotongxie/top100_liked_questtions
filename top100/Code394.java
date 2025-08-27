package top100;

public class Code394 {
    // 这个递归可以解决大多数类似的嵌套问题
    public String decodeString(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        return process(s.toCharArray(), 0).str;
    }

    // 这个函数的含义是 从index出发 返回处理后的字符串 和 处理到的位置
    public Info process(char[] str, int index) {
        StringBuilder res = new StringBuilder();
        int times = 0;
        while (index < str.length && str[index] != ']') {
            if (str[index] >= '0' && str[index] <= '9') {
                times = times * 10 + (str[index++] - '0');
            } else {
                // 中括号
                if (str[index] == '[') {
                    Info next = process(str, index +1);
                    // 拼接字符串
                    for (int i = 0; i < times; i++) {
                        res.append(next.str);
                    }
                    times = 0;
                    index = next.index + 1;
                } else {
                    // 普通字符
                    res.append(str[index++]);
                }
            }
        }
        // 跳出循环的的时候要么是index已经来到了结束位置 要么是遇到了]
        return new Info(res.toString(), index);
    }

    class Info {
        String str;
        int index;

        Info(String str, int index) {
            this.str = str;
            this.index = index;
        }
    }
}
