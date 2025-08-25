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

}
