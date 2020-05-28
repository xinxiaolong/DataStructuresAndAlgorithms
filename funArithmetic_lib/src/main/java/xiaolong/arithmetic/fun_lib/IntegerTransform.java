package xiaolong.arithmetic.fun_lib;

/**
 * Created by xiaolong on 2020-04-29.
 * email：xinxiaolong123@foxmail.com
 *
 * 整数转换。编写一个函数，确定需要改变几个位才能将整数A转成整数B。
 *
 * 示例1:
 *
 * 输入：A = 29 （或者0b11101）, B = 15（或者0b01111）
 * 输出：2
 *
 * 示例2:
 *
 * 输入：A = 1，B = 2
 * 输出：2
 *
 * 链接：https://leetcode-cn.com/problems/convert-integer-lcci
 *
 */
public class IntegerTransform {

    public static void main(String[] agr) {

        int num1 = 1;
        int num2 = -1;

        IntegerTransform transform = new IntegerTransform();
        int num = transform.transform(num1, num2);

        System.out.println(num);
    }

    /**
     * 执行用时 : 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     *
     * @param num1
     * @param num2
     * @return 我去，终于扬眉吐气了一次，打败了100%的用户！
     */
    public int transform(int num1, int num2) {

        int ero = num1 ^ num2;

        int num = 0;
        int count = 0;
        //因为当时负数时，做位移时，会在最左边加上1.ero不会为零。所以控制下位移的次数，也就是做多31次。
        while (ero != 0 && count < 32) {
            if ((ero & 1) == 1) {
                num++;
            }
            ero = ero >> 1;
            count++;
        }
        return num;
    }

    /**
     * 执行用时 : 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     *
     * >>>无符号位移的使用。可以少了对负数位移，高位不断加1的情况。
     *
     * @param num1
     * @param num2
     * @return
     */
    public int transform1(int num1, int num2) {

        int ero = num1 ^ num2;

        int num = 0;
        while (ero != 0 ) {
            if ((ero & 1) == 1) {
                num++;
            }
            ero = ero >>> 1;  //这里用无符号位移，在做位移时，左边统一添加0占位
        }
        return num;
    }


    /**
     * 更厉害的 一行代码。但是借助了系统方法。
     * @param a
     * @param b
     * @return
     */
    public int transform3(int a, int b) {
        return Integer.bitCount(a ^ b);
    }


}
