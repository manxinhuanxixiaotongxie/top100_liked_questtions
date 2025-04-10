package newer20;

/**
 * 给定一个长度为 n 的整数 山脉 数组 arr ，其中的值递增到一个 峰值元素 然后递减。
 * <p>
 * 返回峰值元素的下标。
 * <p>
 * 你必须设计并实现时间复杂度为 O(log(n)) 的解决方案。
 */
public class Code852 {
    public int peakIndexInMountainArray(int[] arr) {
        int l = 0;
        int r = arr.length - 1;
        int ans = l;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] < arr[mid + 1]) {
                ans = mid + 1;
                l = mid + 1;
            } else {
                ans = mid;
                r = mid;
            }
        }
        return ans;
    }

    /**
     * 二分
     * 如果mid+1位置的值是大于mid的数的话 那么峰值元素一定在右侧
     * 否则峰值元素一定在左侧
     *
     * @param arr
     * @return
     */
    public int peakIndexInMountainArray2(int[] arr) {
        int l = 0;
        int r = arr.length - 1;
        int ans = l;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] < arr[mid + 1]) {
                ans = mid + 1;
                l = mid + 1;
            } else {
                ans = mid;
                r = mid - 1;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        Code852 code852 = new Code852();
        int[] arr = {40,48,61,75,100,99,98,39,30,10};
        int i = code852.peakIndexInMountainArray(arr);
        System.out.println(i);
        int i1 = code852.peakIndexInMountainArray2(arr);
        System.out.println(i1);
    }
}
