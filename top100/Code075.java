package top100;

public class Code075 {
    public void sortColors(int[] nums) {

        // 0 红色
        // 1 白色
        // 2 蓝色
        // 红 白 蓝 三色排序
        int left = 0;
        int right = nums.length - 1;
        // 荷兰国旗问题
        int num = 1;
        int i = 0;
        while (i <= right) {
            if (nums[i] < num) {
                swap(nums, i++, left++);
            } else if (nums[i] == num) {
                i++;
            } else {
                swap(nums, i, right--);
            }
        }
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
