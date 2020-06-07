package xiaolong.arithmetic.fun_lib;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 * Created by xiaolong on 2020/6/7 3:55 PM
 * email：xinxiaolong123@foxmail.com
 * <p>
 * <p>
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * <p>
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 * <p>
 * 注意：你不能在买入股票前卖出股票。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * 示例 2:
 * <p>
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
 */
public class StockTransaction {


    public static void main(String[] agr) {
        int[] prices = new int[]{1,2};

        StockTransaction transaction = new StockTransaction();

        int maxPrice = transaction.maxProfit1(prices);

        System.out.println("可获得最大钱数=" + maxPrice);
    }


    /**
     * 326 ms , 在所有 Java 提交中击败了 12.57% 的用户
     * @param prices
     * @return
     *
     * 一开始的思路，用每个数组去减后面的数组，选出距离最远的值。
     *
     * 但是有两层for循环，执行效率底。想想如何优化...
     */
    public int maxProfit(int[] prices) {

        int maxProfit = 0;

        for (int i = 0; i < prices.length-1; i++) {

            int price = prices[i];

            for (int j = i + 1; j < prices.length; j++) {
                //因为实际交易为后面减前面，而现在用的是前面减去后面的数，就成了负数，那么就比较那个负数大，则获取钱数就大。
                maxProfit = price - prices[j] < maxProfit ? price - prices[j] : maxProfit;
            }
        }

        //将距离回正。
        return -maxProfit;
    }


    /**
     * 受答案的启发，有了只遍历一次的思路，来实现以下。
     *
     * 执行用时 : 2 ms, 在所有 Java 提交中击败了 62.76% 的用户
     *
     * 可以看出，速度提高了100倍。
     *
     * 思路是这样的：
     * 想要卖出赚的多，必然是不断尝试用最低价格收入，然后向后滑动计算，得出最大差值则为最佳卖出时机。
     *
     *
     * @param prices
     * @return
     */
    public int maxProfit1(int[] prices) {

        if(prices==null||prices.length<=1){
            return 0;
        }

        //当前滑动到的日期，以相对最低价格买入和最高价格卖出的最大值
        int maxProfit = 0;
        //当前遇到的最低价格，这里假设开头就是最低价格。后面会最判断替换。
        int minPrice=prices[0];


        //从第二天开始尝试进行交易
        for (int i = 1; i < prices.length; i++) {
            //加入遇到最低价格了，这天肯定不会卖出，而要记录下最低价格，记录下，待后面遇到高价算差值
            if(prices[i]<minPrice){
                minPrice=prices[i];
                continue;
            }
            //计算出历史出现最低价和当天的价格进行差值计算，比较历史最大值那个大就取那个。
            maxProfit=Math.max(prices[i]-minPrice,maxProfit);
        }
        return maxProfit;
    }

}
