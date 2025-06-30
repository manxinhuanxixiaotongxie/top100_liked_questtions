package newer20;

public class Code1422 {
    public int maxScore(String s) {
        char[] str = s.toCharArray();
        int num1 = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '1') num1++;
        }
        int ans = 0;
        int num0 = 0;
        for (int i = 0; i < str.length - 1; i++) {
            if (str[i] == '0') {
                num0++;
            } else {
                num1--;
            }
            ans = Math.max(num0 + num1, ans);
        }
        return ans;
    }

    public int maxScore2(String s) {
        char[] str = s.toCharArray();
        int nums1 = 0;
        for (int i = str.length - 1; i >= 0; i--) {
            if (str[i] == '1') {
                nums1++;
            }
        }
        int ans = 0;
        int num0 = 0;
        for (int i = 0; i < str.length - 1; i++) {
            if (str[i] == '0') {
                num0++;
            }
            if (str[i] == '1') {
                nums1--;
            }
            ans = Math.max(ans, num0 + nums1);
        }
        return ans;
    }
}
