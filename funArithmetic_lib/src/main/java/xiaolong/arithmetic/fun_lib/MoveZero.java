package xiaolong.arithmetic.fun_lib;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by xiaolong on 2020-05-04.
 * email：xinxiaolong123@foxmail.com
 * <p>
 * <p>
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * <p>
 * 链接：https://leetcode-cn.com/problems/move-zeroes
 */
public class MoveZero {

    public static void main(String[] agr) {

        int[] nums = new int[]{0, 1, 0, 3, 12};

        MoveZero moveZero = new MoveZero();

        moveZero.moveZero2(nums);

        System.out.print(Arrays.toString(nums));
    }

    /**
     * 双指针解法，将有效数据进行移动。
     *
     * @param nums
     */
    public void moveZero(int[] nums) {


        if (nums.length == 0) {
            return;
        }

        //有效数字插入下标
        int index = -1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                index++;
                nums[index] = nums[i];
            }
        }

        //有效数字为0 则不用将有效下标后的值修改为零
        if (index == -1) {
            return;
        }

        //将非有效数字修改为0
        for (int i = nums.length - 1; i > index; i--) {
            nums[i] = 0;
        }

    }

    /**
     * 双指针解法，将有效数据进行移动。
     * <p>
     * 快速排序思路，只需一次循环
     *
     * https://leetcode-cn.com/problems/move-zeroes/solution/dong-hua-yan-shi-283yi-dong-ling-by-wang_ni_ma/
     *
     * @param nums
     */
    public void moveZero2(int[] nums) {

        if (nums.length == 0) {
            return;
        }

        //有效数字插入下标
        int index = 0;

        for (int i = 0; i < nums.length; i++) {

            //如果当前格子的数比为零
            if (nums[i] != 0) {
                //发现和有效数子格子位置不同，需要进行一次交换
                if (index <i ) {
                    int temp = nums[i];
                    nums[index] = temp;
                    nums[i] = 0;
                }
                //更新有效格子下标。
                index++;
            }

        }

    }
}
