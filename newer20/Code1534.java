package newer20;

/**
 * 给你一个整数数组 arr ，以及 a、b 、c 三个整数。请你统计其中好三元组的数量。
 * <p>
 * 如果三元组 (arr[i], arr[j], arr[k]) 满足下列全部条件，则认为它是一个 好三元组 。
 * <p>
 * 0 <= i < j < k < arr.length
 * |arr[i] - arr[j]| <= a
 * |arr[j] - arr[k]| <= b
 * |arr[i] - arr[k]| <= c
 * 其中 |x| 表示 x 的绝对值。
 * <p>
 * 返回 好三元组的数量 。
 */
public class Code1534 {

    /**
     * 暴力解法
     *
     * @param arr
     * @param a
     * @param b
     * @param c
     * @return
     */
    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int i = 0;
        int ans = 0;
        while (i < arr.length - 2) {
            int j = i + 1;
            while (j < arr.length - 1) {
                if (Math.abs(arr[i] - arr[j]) <= a) {
                    int k = j + 1;
                    while (k < arr.length) {
                        if (Math.abs(arr[j] - arr[k]) <= b && Math.abs(arr[i] - arr[k]) <= c) {
                            ans++;
                        }
                        k++;
                    }
                }
                j++;
            }
            i++;
        }
        return ans;
    }
}
