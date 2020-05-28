package xiaolong.arithmetic.fun_lib;

/**
 * Created by xiaolong on 2020-05-04.
 * email：xinxiaolong123@foxmail.com
 */
public class MaximumNumber {


    public static void main(String[] arg) {

        int num = 99666669;

        MaximumNumber maxNum = new MaximumNumber();

        int max = maxNum.getMax(num);

        System.out.print(max);
    }

    /**
     * 思路，就是将最高位的6反转成9就可以
     * <p>
     * 进行复杂的数学运算。
     *
     * @param num
     * @return
     */
    public int getMax(int num) {

        int len = String.valueOf(num).length();

        int temp = num;
        while (len > 0) {

            int multiple = (int) Math.pow(10, len - 1);

            //取出最高位的数
            int value = temp / multiple;

            if (value == 6) {
                num = (int) (num + (Math.pow(10, len - 1) * 3));
                break;
            }

            temp = temp - (multiple * value);

            len--;
        }

        return num;
    }

    /**
     * 思路，就是将最高位的6反转成9就可以
     * <p>
     * 利用字符串查找，进行翻转。
     *
     * @param num
     * @return
     */
    public int getMax1(int num) {

        String s = num + "";

        s = s.replaceFirst("6", "9");

        return Integer.valueOf(s);

    }


}
