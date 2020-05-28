package xiaolong.classicarithmetic_lib;

import java.util.Arrays;

/**
 * Created by xiaolong on 2020-05-04.
 * email：xinxiaolong123@foxmail.com
 * <p>
 * 计数排序
 *
 * 计数排序本质上是一种特殊的桶排序，当桶的个数取最大( maxV-minV+1 )的时候，就变成了计数排序。
 *
 * <p>
 * 计数排序的核心在于将输入的数据值转化为键存储在额外开辟的数组空间中。
 * 作为一种线性时间复杂度的排序，计数排序要求输入的数据必须是有确定范围的整数。
 * <p>
 * 1：数据有确定范围。
 * 2：正整数。
 * <p>
 * 空间换时间的典型思路。HashMap的优化思路。
 * <p>
 * 计数排序是用来排序0到100之间的数字的最好的算法，但是它不适合按字母顺序排序人名。
 * 但是，计数排序可以用在基数排序中的算法来排序数据范围很大的数组。
 * <p>
 * 算法的步骤如下：
 * <p>
 * （1）找出待排序的数组中最大和最小的元素
 * （2）统计数组中每个值为i的元素出现的次数，存入数组C的第i项
 * （3）对所有的计数累加（从C中的第一个元素开始，每一项和前一项相加）
 * （4）反向填充目标数组：将每个元素i放在新数组的第C(i)项，每放一个元素就将C(i)减去1
 *
 *
 *  https://blog.csdn.net/qq_19446965/article/details/81517552 值得读一读的好文章
 *
 */
public class CountSort {

    public static void main(String[] agr) {

        int[] numbers = new int[]{5, 9, 6, 199, 200, 25, 290, 99, 6, 26, 89, 103, 55, 66, 160, 6, 566, 965, 985};

        CountSort countSort = new CountSort();

        int[] array = countSort.sort2(numbers);

        System.out.print(Arrays.toString(array));

    }


    /**
     * 这里只取了max值，最小值没有
     *
     * 没有进过优化的 数组长度为0-max;
     *
     * @param nums
     * @return
     */
    public int[] sort(int[] nums) {

        if (nums.length == 0) {
            return nums;
        }

        int max = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }

        int[] CNums = new int[max + 1];

        for (int i = 0; i < nums.length; i++) {

            int index = nums[i];

            CNums[index]++;
        }

        int index = 0;
        int[] newNums = new int[nums.length];

        for (int i = 0; i < CNums.length; i++) {

            int count = CNums[i];

            while (count > 0) {
                newNums[index] = i;

                index++;
                count--;
            }
        }
        return newNums;
    }


    /**
     * 2020-05-04. 早11：00
     * <p>
     * 做一次空间上的优化。
     * 使其空间复杂度和时间复杂度为 O(n+min+max)
     * 数组长度为 最大值max-最小值min+1;
     *
     * 注意的是：
     * 1：在计数数组的起始坐标=取出的值-最小值。
     * 2：在取出计数数组，用下标映射排序时，需要对下标+最小值，进行映射还原原值。
     *
     * @param nums
     * @return
     */
    public int[] sort1(int[] nums) {

        if (nums.length == 0) {
            return nums;
        }

        int maxValue = nums[0];
        int minValue = nums[0];

        for (int i = 0; i < nums.length; i++) {

            if(maxValue<nums[i]){
                maxValue=nums[i];
            }

            if(minValue>nums[i]){
                minValue=nums[i];
            }
        }

        //这里的长度做了优化
        int[] CNums = new int[maxValue-minValue + 1];

        for (int i = 0; i < nums.length; i++) {

            //这里存入的值相应的要进行更改。
            int index = nums[i]-minValue;

            CNums[index]++;
        }

        int index = 0;
        int[] newNums = new int[nums.length];

        for (int i = 0; i < CNums.length; i++) {

            int count = CNums[i];

            while (count > 0) {

                //这里需要将值还原
                newNums[index] = i+minValue;

                index++;
                count--;
            }
        }
        return newNums;
    }


    /**
     * 加入对负数排序的支持。
     *
     * @param nums
     * @return
     */
    public int[] sort2(int[] nums) {

        if (nums.length == 0) {
            return nums;
        }

        int maxValue = nums[0];
        int minValue = nums[0];

        for (int i = 0; i < nums.length; i++) {

            if(maxValue<nums[i]){
                maxValue=nums[i];
            }

            if(minValue>nums[i]){
                minValue=nums[i];
            }
        }

        //如果这个minValue是个负数怎么办？那求出最小值和0的距离，然后给所有的值都加上这个距离。保持了每个数距离不变
        int distanceZero=0;

        if(minValue<0){
            distanceZero=0-minValue;
        }


        //这里的长度做了优化
        int[] CNums = new int[maxValue-minValue + 1];

        for (int i = 0; i < nums.length; i++) {

            //将所有的数加上distanceZero
            int value=nums[i]+distanceZero;

            //最小数也加上distanceZero；使其变为正数
            int min=minValue+distanceZero;

            int index = value-min;

            CNums[index]++;
        }

        int index = 0;
        int[] newNums = new int[nums.length];

        for (int i = 0; i < CNums.length; i++) {

            int count = CNums[i];

            while (count > 0) {

                //这里需要将值还原
                newNums[index] = i+minValue;

                index++;
                count--;
            }
        }
        return newNums;
    }


}
