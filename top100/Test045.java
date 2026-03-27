package top100;


public class Test045 {
    // 写在里面
    static int jump_inside(int[] nums) {
        int ans = 0;
        int mostRight = 0;
        int nextMostRight = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            nextMostRight = Math.max(nextMostRight, nums[i] + i);
            if (i == mostRight) {
                if (i == nextMostRight) {
                    return -1;
                }
                mostRight = nextMostRight;
                ans++;
            }
        }
        return ans;
    }

    // 写在外面
    static int jump_outside(int[] nums) {
        int ans = 0;
        int mostRight = 0;
        int nextMostRight = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            nextMostRight = Math.max(nextMostRight, nums[i] + i);
            if (i == nextMostRight) {
                return -1;
            }
            if (i == mostRight) {
                mostRight = nextMostRight;
                ans++;
            }
        }
        return ans;
    }

    static void main(String[] args) {
        int[][][] cases = {
                {{1, 0, 1}, {-1}},          // 1: 0->1, 1+0=1, 不可达
                {{2, 0, 0, 0}, {-1}},         // 2: 只能到index2, 不可达
                {{3, 0, 0, 1}, {1}},          // 3: 0跳3步直接到3
                {{2, 0, 1, 0, 1}, {-1}},       // 4: 从0能到2, 2+1=3, 3+0=3, 不可达4
                {{1, 1, 1, 1, 0}, {-1}},       // 5: 能走到3, 3+1=4(终点索引4), 可达? 终点是4, 可达
                {{1, 1, 1, 0, 0}, {-1}},       // 6: 能走到2, 2+1=3, 3+0=3, 不可达4
                {{2, 3, 1, 1, 4}, {2}},        // 7: 经典可达
                {{2, 3, 0, 1, 4}, {2}},        // 8: 经典可达
                {{1, 2, 3}, {2}},            // 9: 可达
                {{0}, {0}},                // 10: 只有一个元素, 已在终点
                {{1, 0}, {1}},              // 11: 跳一步
                {{2, 0, 0}, {-1}},           // 12: 0跳到2(终点), 可达, 答案1
                {{1, 0, 0}, {-1}},           // 13: 只能到1, 1+0=1, 不可达2
                {{3, 0, 0, 0, 0}, {-1}},       // 14: 0跳3步到3, 3+0=3, 不可达4
                {{4, 0, 0, 0, 1}, {-1}},       // 15: 0跳4步到4(终点), 可达, 答案1
                {{1, 1, 0, 1}, {-1}},         // 16: 0->1->2, 2+0=2, 不可达3
                {{2, 1, 0, 1}, {-1}},         // 17: 0到2, 2+0=2, 不可达3
                {{2, 1, 1, 0, 0}, {-1}},       // 18: 能到3, 3+0=3, 不可达4
                {{1, 2, 0, 0, 4}, {-1}},       // 19: 0->1->3, 3+0=3, 不可达4
                {{3, 1, 0, 0, 2}, {-1}},       // 20: 0跳3到3, 3+0=3, 不可达4? 0跳1到1,1+1=2,不行. 0跳2到2,2+0不行. 0跳3到3, 3+0不行. 确实不可达
        };
        System.out.println("序号 | nums | inside | outside | 一致?");
        System.out.println("-----|------|--------|---------|------");
        for (int t = 0; t < cases.length; t++) {
            int[] nums = cases[t][0];
            int inside = jump_inside(nums);
            int outside = jump_outside(nums);
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < nums.length; i++) {
                sb.append(nums[i]);
                if (i < nums.length - 1) sb.append(",");
            }
            sb.append("]");
            System.out.printf("%3d  | %-20s | %6d | %7d | %s%n",
                    t + 1, sb, inside, outside,
                    inside == outside ? "一致" : "不一致!");
        }
    }
}
