package xiaolong.arithmetic.fun_lib;

/**
 * Created by xiaolong on 2020/6/7 8:42 PM
 * email：xinxiaolong123@foxmail.com
 * <p>
 * <p>
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * <p>
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * <p>
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 * 示例 2:
 * <p>
 * 输入: [1,2,3,4,5]
 * 输出: 4
 * <p>
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii
 */
public class StockTransaction2 {


    public static void main(String[] agr) {

        int[] nums = new int[]{5, 4, 3, 2, 1};

        StockTransaction2 transaction2 = new StockTransaction2();

        int max = transaction2.getMaxProfit1(nums);

        System.out.println("可获得最大值为" + max);
    }

    /**
     * 思路: 滑动判断，先从第一天开始，假设当前为最低值，值得买入。设为i 。第i+1 是不是比第i天价格高？
     * 1：高则尝试卖出得出value：再过一天判断价格是不是比前一天还高？
     * 高：尝试卖出，更新value值。
     * 底：则买入。之前的value保存，作为后续累计使用。
     * 2：底则买入。将最低价更新成当前价格。累计的max变
     * <p>
     * <p>
     * 执行用时 :1 ms , 在所有 Java 提交中击败了 99.23% 的用户
     *
     * @param prices
     * @return
     */
    public int getMaxProfit(int[] prices) {
        int max = 0;
        int min = prices[0];

        int tempMax = 0;
        for (int i = 1; i < prices.length; i++) {

            int m = prices[i] - min;

            if (m > tempMax) {
                //说明价格在升高，先保存差值，看第二天是不是比这差值还大？大则先保存差值，小则说明降价了。
                tempMax = m;
            } else {
                //变小了，说明降价了,需要在前一天做抛售了。将抛售的收益累加
                max = max + tempMax;
                //将差值置为0
                tempMax = 0;
                //可以买入;
                min = prices[i];
            }
        }

        if (tempMax > 0) {
            max += tempMax;
        }
        return max;

    }

    /**
     * 贪心算法思路：只要涨就算收益
     * 要想去的最大收益，无非是第二天有涨的钱就算作收益的钱。所以累加第二天和第一天差值为正数的，也就是涨的钱
     * 就是最大收益值。
     *
     * @param prices
     * @return
     */
    public int getMaxProfit1(int[] prices) {
        int max = 0;

        for (int i = 1; i < prices.length; i++) {
            //每天都会计算一次，只要有涨就算做收益
            max = max + Math.max(prices[i] - prices[i - 1], 0);
        }

        return max;

    }

}
