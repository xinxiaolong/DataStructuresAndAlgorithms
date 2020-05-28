package xiaolong.arithmetic.fun_lib;

/**
 * Created by xiaolong on 2020-05-05.
 * email：xinxiaolong123@foxmail.com
 * <p>
 * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。
 * 在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
 * <p>
 * <p>
 * 输入: [0,1,2,3,4,5,6,7,9]
 * 输出: 8
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/que-shi-de-shu-zi-lcof
 */
public class MissNumber {

    public static void main(String[] arg) {

        int[] nums = {0,1,2,3,4,5};

        MissNumber miss = new MissNumber();

        int n = miss.miss(nums);

        System.out.print(n);

    }


    /**
     * 二分法查找
     *
     * @param array
     * @return
     */
    public int miss(int[] array) {

        int low = 0;
        int height = array.length-1;

        //需要在好好分析.....
        while (low <= height) {

            int middle = (low + height) / 2;

            //如果等于当前说，说明缺失的数字在后面
            if (array[middle] == middle) {
                low = middle + 1;  //向后移动一位，也将就是缺失数字的下一位。下标为缺失的数字。
            } else {
                //如果下于当前数，说明缺失的数在前面
                height = middle-1;
            }
        }

        return low;
    }
}
