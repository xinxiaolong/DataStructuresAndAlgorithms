package xiaolong.arithmetic.fun_lib;

/**
 * Created by xiaolong on 2020-04-24.
 * email：xinxiaolong123@foxmail.com
 * <p>
 * 编写一个方法，找出两个数字a和b中最大的那一个。不得使用if-else或其他比较运算符。
 * https://leetcode-cn.com/problems/maximum-lcci/
 * <p>
 * 今天来个相对简单的算法。一早上和中午分析优化插入算法有些类。
 * <p>
 * 解决这个问题的方法有很多，比较经典的是做位的运算。所以这里就要学习到一些java对位运算的符号
 * <p>
 * 也是对位运算符学习的一节。
 * 结合https://www.sojson.com/hexconvert.html这工具网站，看起来更方便。
 * <p>
 * 参考网站：
 * https://www.jianshu.com/p/6c518e7b4690 补码反码
 * https://zhuanlan.zhihu.com/p/30108890 位移运算的清晰介绍
 */
public class MaxOfTwo {


    public static void main(String[] agr) {

        symbol1();
        symbol2();
        symbol3();
        symbol4();
        symbol5();
        symbol6();
        symbol7();

        int num = (Integer.MAX_VALUE >> 1);
        System.out.println(num);

        MaxOfTwo maxOfTwo = new MaxOfTwo();
        int max = maxOfTwo.getMax(104, 11);
        System.out.println("最大值为:" + max);
    }


    int getMax(int a, int b) {

        //a>b时，(a-b)>>31=0000 0000 0000 0000 0000 0000 0000 0000
        //a<b时  (a-b)>>31=1111 1111 1111 1111 1111 1111 1111 1111
        int difference = a - b;

        //当a>b时 max=a=a-0; 也就是说 (difference & (difference >> 31))=0
        //当b>a时 max=b=a-(a-b) 也就是说 (difference & (difference >> 31))=a-b
        int max = a - (difference & (difference >> 31));

        return max;
    }

    /**
     * (两个为真才为真)
     * &按位与的运算规则是将两边的数转换为二进制位，然后运算最终值，
     * 运算规则即1&1=1 , 1&0=0 , 0&1=0 , 0&0=0
     * <p>
     * 下面的例子：
     * 4的二进制位是101；6的二进制位是110；就是011 & 101，
     * 由按位与运算规则得知：100; 最终十进制值为4
     */
    public static void symbol1() {
        int n = 5 & 6;
        System.out.println(n);

    }

    /**
     * (一个为真即为真)
     * |按位或和&按位与计算方式都是转换二进制再计算，不同的是运算规则
     * 1|0 = 1 , 1|1 = 1 , 0|0 = 0 , 0|1 = 1
     * <p>
     * 下面的例子：
     * 4的二进制位是101；6的二进制位是110；就是011 & 101，
     * 由按位与运算规则得知：111 & 最终值为7
     */
    public static void symbol2() {
        int n = 5 | 6;
        System.out.println(n);

    }

    /**
     * (两者相反则为真)
     * ^异或运算符顾名思义，异就是不同，其运算规则为
     * 1^0 = 1 , 1^1 = 0 , 0^1 = 1 , 0^0 = 0
     * <p>
     * 下面的例子：
     * 4的二进制位是101；6的二进制位是110；就是011 & 101，
     * 由按位与运算规则得知：011 & 最终值为3
     */
    public static void symbol3() {
        int n = 5 ^ 6;
        System.out.println(n);

    }


    /**
     * ~（取反运算符）
     * TODO 还需再次理解
     * 取反就是1为0,0为1,
     * 下面例子：当然这里涉及到一个知识点，就是2进制对于正负数的标示。正数最高位1 负数最高位0
     * <p>
     * int 32位系统。6的二进制位为：1000 0000 0000 0000 0000 0000 0000 0110
     * 取反后的二进制：0
     */
    public static void symbol4() {
        int n = ~6;
        System.out.println(n);

    }

    /**
     * <<（左移运算符）向左移动数据。移动n位就给数据右边加n个零
     * <p>
     * 下面的例子：
     * 9<<2的意思为5的二进制位往左挪2位，右边补0，9的二进制位是1001 ，
     * 移动完成后是：1001 00。 最终值为36
     * <p>
     * 所以移动过的
     * 数值=原数值*（2的n次方），需要在一定范围内才生效。不然位移最高位，0变成了1那就成负数了。就是所谓的位移溢出
     */
    public static void symbol5() {
        int n = 9 << 2;
        System.out.println(n);

    }

    /**
     * >>（右移运算符）向右移动数据。就是将数据从右边开始，拿掉n位
     * <p>
     * 下面的例子：
     * 9>>2 的意思为9的二进制位往右挪2位，右边拿掉2位，9的二进制位是1001 ，
     * 移动完成后是：10。最终值为2
     * <p>
     * 注意：正数时 补0
     * 负数时 补1
     */
    public static void symbol6() {
        int n = 9 >> 2;
        System.out.println(n);

    }

    /**
     * >>>（无符号右移运算符）
     * <p>
     * 无符号右移运算符和右移运算符的主要区别在于负数的计算，因为无符号右移是高位补0，移多少位补多少个0。
     * 15的二进制位是0000 1111 ， 右移2位0000 0011，结果为3
     */
    public static void symbol7() {
        int n = 15 >>> 2;
        System.out.println(n);

    }


}
