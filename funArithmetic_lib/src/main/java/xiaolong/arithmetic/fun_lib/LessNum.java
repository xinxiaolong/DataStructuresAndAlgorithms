package xiaolong.arithmetic.fun_lib;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by xiaolong on 2020-04-25.
 * email：xinxiaolong123@foxmail.com
 *
 * 给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。
 * 换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i] 。
 * 以数组形式返回答案。
 *
 * 链接：https://leetcode-cn.com/problems/how-many-numbers-are-smaller-than-the-current-number
 *
 * 2 <= nums.length <= 500
 * 0 <= nums[i] <= 100
 *
 * 这个是条件：
 * 1：长度在2-500之间
 * 2：值的范围在0到100之间，可能有重复。
 */
public class LessNum {

    public static void main(String[] arg) {

        int[] nums = {8, 1, 4, 10, 9, 6, 10};

        LessNum less = new LessNum();
        int[] lessNums = less.ge6(nums);

        for (int i = 0; i < lessNums.length; i++) {
            System.out.print(lessNums[i] + " ");
        }
    }


    /**
     * 这个是初步写的一个答案。
     * 暴力 用时19ms
     *
     *
     * 执行效率：19 ms  空间使用 39.8 MB
     *
     * @param nums
     * @return
     */
    public int[] get(int[] nums) {

        int[] lessNums = new int[nums.length];

        //最外层控制遍历出去当前要对比的目标数
        for (int i = 0; i < nums.length; i++) {

            int count = 0;

            //这层需要是取拿除自己以外的所有数字进行对比
            for (int n = 0; n < nums.length; n++) {
                if (n != i && nums[i] > nums[n]) {
                    //满足条件进行计数加一
                    count++;
                }
            }
            lessNums[i] = count;
        }

        return lessNums;
    }


    /**
     * 第二个方案想到了排序，进行升序。二分法查找，找到位置以前的都是小与的数量。
     * 有个需要处理的问题是：如何还能输出以前的下标志。
     * 下标的问题我利用数据结构处理。
     */
    class Number {
        int value;
        int index;

        public String toString() {
            return value + "  " + index;
        }
    }

    class NumberComparator implements Comparator<Number> {
        @Override
        public int compare(Number number1, Number number2) {

            if (number1.value < number2.value) {
                return -1;
            } else if (number1.value > number2.value) {
                return 1;
            }
            return 0;
        }
    }

    /**
     * 思路：原数组转换成Number数组，利用value保存值，index保存原下标。
     * 利用Arrays.sort进行对value的升序排序。排序后的位置之前的数是都小于这个数。
     * 当前的下标值刚好等于小于这个值的数量。
     * <p>
     * 然后再用一次for循环，去给lessNums赋值。
     * <p>
     * 但是这个有个局限条件：假定这个数字里没有重复的值。如果有的话，那么会出现计算错误。
     *
     * 执行效率：7 ms  空间使用 40 MB
     *
     * @param nums
     * @return
     */
    public int[] get1(int[] nums) {

        int[] lessNums = new int[nums.length];

        Number[] numbers = new Number[nums.length];
        for (int i = 0; i < nums.length; i++) {
            Number number = new Number();
            number.index = i;
            number.value = nums[i];
            numbers[i] = number;
        }

        Arrays.sort(numbers, new NumberComparator());

        for (int i = 0; i < numbers.length; i++) {
            Number number = numbers[i];
            lessNums[number.index] = i;
            System.out.print(number);
        }
        return lessNums;
    }

    /**
     * 这个方法是基于上面的对于重复数据做的解决。
     *
     * 不知道为什么，运行时长比暴力的还要长。因为system.out大大加载了运行市场
     *
     * 执行效率：7 ms  空间使用 40 MB
     *
     * @param nums
     * @return
     */
    public int[] get2(int[] nums) {

        int[] lessNums = new int[nums.length];

        Number[] numbers = new Number[nums.length];
        for (int i = 0; i < nums.length; i++) {
            Number number = new Number();
            number.index = i;
            number.value = nums[i];
            numbers[i] = number;
        }

        Arrays.sort(numbers, new NumberComparator());

        int fstAppearValue = 0;
        int fstAppearIndex = 0;

        //也可以使用倒叙进行巧妙处理！
        for (int i = 0; i < numbers.length; i++) {
            Number number = numbers[i];

            //当新的值和上次的值一样
            if (fstAppearValue == number.value) {
                //就使用这个值第一次出现时的下标
                lessNums[number.index] = fstAppearIndex;
            } else {
                //记录下第一次出现时的值和下标
                fstAppearValue = number.value;
                fstAppearIndex = i;
                lessNums[number.index] = i;
            }
            System.out.print(number);
        }
        return lessNums;
    }


    /**
     * 看到网上那个有人用HashMap暂存值和小标信息。我也尝试写一下
     *
     * 执行效率：5 ms  空间使用 40.2 MB
     *
     * @param nums
     * @return 使用hashMap的value是个集合。可以存储多个index。
     * 使用倒叙巧妙的处理了相同值只记录从小到大排第一次出现的下标值。
     */
    public int[] get3(int[] nums) {

        int[] lessNums = new int[nums.length];
        int[] sortNums = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sortNums);

        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            if (!map.containsKey(nums[i])) {
                map.put(nums[i], new HashSet<>());
            }
            map.get(nums[i]).add(i);
        }

        //利用倒叙 巧妙的处理了相同值只记录从小到大排第一次出现的下标值。
        for (int i = sortNums.length - 1; i > 0; i--) {
            HashSet<Integer> set = map.get(sortNums[i]);
            for (Integer index : set) {
                lessNums[index] = i;
            }
        }

        return lessNums;
    }

    /**
     * 网上提供一中也很巧妙的办法。
     * 用hashMap只存这个比这个数字小的所有数字个数，也就是存储这个数字在排序后第一次出现的位置
     * 我觉得这个更为直观巧妙，更符合运用hashMap的特别。
     *
     *
     * 执行效率：5 ms  空间使用 40.2 MB
     *
     * @param nums
     * @return
     */
    public int[] get4(int[] nums) {

        int[] lessNums = new int[nums.length];
        int[] sortNums = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sortNums);

        HashMap<Integer, Integer> map = new HashMap<>();

        //这里只存入这个数字第一次出现的位置。这个位置大小也就是小于他的数字的数量大小。
        for (int i = 0; i < sortNums.length; i++) {
            if (map.get(sortNums[i]) == null) {
                map.put(sortNums[i], i);
            }
        }

        for (int i = 0; i < sortNums.length; i++) {
            lessNums[i] = map.get(nums[i]);
        }

        return lessNums;
    }

    /**
     * 网上提供一中也很巧妙的办法：计数排序
     *
     * 执行效率：1 ms	  空间使用 39.3 MB
     *
     * 8, 1, 4, 10, 9, 6, 10
     *
     * @param nums
     * @return
     */
    public int[] get5(int[] nums) {

        int[] lessNums = new int[nums.length];
        //这里利用到 0 <= nums[i] <= 100 这个条件
        int[] freq = new int[101];

        //统计出某个数字出现的次数。index为这个数字，value为出现的次数
        for (int num : nums) {
            freq[num]++;
        }


        int[] less = new int[101];

        int number = 0;
        //这里处理的绕了一下。根据数字出现的频率，拣出这个数字之前出现数字的个数
        //[0]=0 ,[1]=4;[2]=0,[3]=1;[4]=5;[5]=1; 这里用数字4举例：循环遍历将
        //下标4之前所有数字出现次数相加，就得到比[1]+[3]=5小的数字个数。这里是5.
        //index和fre一样是源数字。value的在是出现个数，而是这个数之前所有数出现的个数。
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] > 0) {
                less[i] = number;
                number = number + freq[i];
            }
        }

        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            int lessNum = less[n];
            lessNums[i] = lessNum;
        }
        return lessNums;
    }

    /**
     *
     * 这个是网上的原答案。很巧妙处理了
     * 执行效率：1 ms	  空间使用 39.3 MB
     * @param nums
     * @return
     *
     */
    public int[] ge6(int[] nums) {
        // 统计出现频率 frequency
        int[] freq = new int[101]; //这里利用到 0 <= nums[i] <= 100 这个条件。数组的长度为101即可。
        
        for (int num : nums) freq[num]++;

        //这里很巧妙了。并不是向我上面那个方法那样，统计出这个数之前的所有出现的数字个数。
        //而是统计出这个数出现的次数+之前的数出现的次数的和；真拗口！
        //官方解释：对频率(而非对原数组nums)从前到后累加
        for (int i = 1; i < freq.length; i++) {
            freq[i] = freq[i] + freq[i - 1];
        }

        // 输出结果
        int[] res = new int[nums.length];
        for (int i = 0; i < res.length; i++) {
            if (nums[i] > 0) {
                res[i] = freq[nums[i] - 1];
            }
        }
        return res;
    }

}

