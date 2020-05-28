package xiaolong.arithmetic.fun_lib;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by xiaolong on 2020-04-29.
 * email：xinxiaolong123@foxmail.com
 * <p>
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: 123
 * 输出: 321
 * <p>
 * <p>
 * 示例 2:
 * <p>
 * 输入: -123
 * 输出: -321
 * <p>
 * 示例 3:
 * <p>
 * 输入: 120
 * 输出: 21
 */
public class IntegerReverse {

    public static void main(String[] agr) {
        int num = 987654321;

        IntegerReverse reverse = new IntegerReverse();

        num = reverse.reverse(num);

        System.out.println(num);

    }


    /**
     * 第一种，使用常规办法。
     * <p>
     * 这个方法先写简单点的，不考虑溢出问题的，翻转算法。
     *
     * @param num
     * @return
     */
    public int reverse(int num) {


        //先防止反转发生溢出。
        //int 最大值Integer.MAX_VALUE 2147483647 是十位。 也就是说翻转过来的数要小于等于这个数.
        //那么那么合法的数：位数<=10
        //2147447412 。能翻转的最大数是这个。
        //尝试写出来，发现还是很吃力的。
        //能不能判断滞后，先得到翻转后的片段数。还未组合，来判断是否合法？
        //这个判断就想了20多分钟````.先做滞后判断吧


        //写了一大段代码，果断删除。根据例子的启发，写了个最简单。


        //将原数称之为韭菜，因为他是待割的，每次我都会拿走他的最后一位数，用来给新的数做拼接用。所以称之为韭菜。
        int leek = num;
        //翻转后的数。
        int result = 0;

        //当leek==0是，被掏空了。
        while (leek != 0) {
            //从韭菜哪里拿出（leek%10 取出韭菜的最后一位值数）最后一位数字拼接给自己。拼接步骤，首先需要向左位移一位，十进制就要*10。然后将割来的数放置自己的个位。
            result = result * 10 + leek % 10;
            //更新韭菜的值，被拿掉了最后一位。所以要/10
            leek = leek / 10;
        }

        //在做溢出判断的时候，没有想到很好的办法。所以仔细看看例子
        return result;
    }

    /**
     * 这个是网上给的答案
     * 也是用的滞后判断。
     *
     * @param x
     * @return
     */
    public int reverse1(int x) {
        int ans = 0;

        //这个拼接算法和上面的一样的思路。这里主要看看对溢出的判断
        while (x != 0) {

            //取出即将拼接的数
            int pop = x % 10;

            //当为正数时
            if (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            //为负数时。
            if (ans < Integer.MIN_VALUE / 10 || (ans == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }

            ans = ans * 10 + pop;
            x /= 10;
        }
        return ans;
    }

}
