package top100;

/**
 * 买卖股票的最佳时机
 */
public class Code121 {
    /**
     * 思路：考虑要在当前位置卖掉
     * 当前位置卖掉能够获取到的最大利润就是 当前价格-之前的最低价格
     * 注意：
     * 1.当前值可能会比0要小 这时候获取到最大收益是0
     * 2.最小值在当前位置遍历之后要进行更新
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int ans = 0;
        // 必须要在当前位置卖掉
        // 需要记录之前的最小值
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            ans = Math.max(ans, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        return ans;
    }
}
