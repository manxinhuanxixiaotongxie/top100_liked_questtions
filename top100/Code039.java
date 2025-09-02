package top100;

import java.util.ArrayList;
import java.util.List;

public class Code039 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        process(candidates, 0, new ArrayList<>(), ans, target);
        return ans;
    }

    public void process(int[] candidates, int index, List<Integer> cur, List<List<Integer>> ans, int rest) {
        if (index == candidates.length) {
            if (rest == 0) {
                ans.add(cur);
            }
        } else {
            for (int zhang = 0; zhang * candidates[index] <= rest; zhang++) {
                List<Integer> list = new ArrayList<>(cur);
                for (int i = 0; i < zhang; i++) {
                    list.add(candidates[index]);
                }
                process(candidates, index + 1, list, ans, rest - zhang * candidates[index]);
            }
        }
    }

    List<List<Integer>> ans;


    /**
     * 更优解法
     * 恢复现场
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        ans = new ArrayList<>();
        process2(candidates, 0, target, new ArrayList<>());
        return ans;
    }

    public void process2(int[] candidates, int index, int rest, List<Integer> cur) {
        if (index == candidates.length) {
            if (rest == 0) {
                ans.add(new ArrayList<>(cur));
            }
        } else {
            for (int zhang = 0; zhang * candidates[index] <= rest; zhang++) {
                for (int i = 0; i < zhang; i++) {
                    cur.add(candidates[index]);
                }
                process2(candidates, index + 1, rest - zhang * candidates[index], cur);
                for (int i = 0; i < zhang; i++) {
                    cur.remove(cur.size() - 1);
                }
            }
        }
    }

}
