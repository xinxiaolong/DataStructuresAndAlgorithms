package xiaolong.arithmetic.fun_lib;

import java.awt.Stroke;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * Created by xiaolong on 2020/5/31 6:30 PM
 * email：xinxiaolong123@foxmail.com
 * <p>
 * <p>
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * <p>
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * <p>
 * 链接：https://leetcode-cn.com/problems/majority-element
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,3]
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 */
public class MajorityNum {

    public static void main(String[] agr) {

        int[] nums = new int[]{3, 2, 3};

        MajorityNum majorityNum = new MajorityNum();

        int num = majorityNum.majorityElement4(nums);

        System.out.println("多数元素为" + num);
    }

    /**
     * 思路 对数组进行排序，然后遍历去统计数字个数，当统计到一半时还未有多数的出现，则可以判断没有majority数。
     * <p>
     * 执行用时 : 4 ms , 在所有 Java 提交中击败了41.68% 的用户
     * <p>
     * 看来效率有待提高
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {

        if (nums == null) {
            return -1;
        }

        if (nums.length == 1) {
            return nums[0];
        }
        //排序
        Arrays.sort(nums);

        return nums[nums.length / 2];
    }


    /**
     * 用hashMap去保存数字和当前数字的个数。但是效率没有上面的算法快
     * <p>
     * 执行用时 : 17 ms , 在所有 Java 提交中击败了 27.40% 的用户
     * <p>
     * 多了13秒
     * <p>
     * 估计是HashMap的 hash算法耗时严重了
     *
     * @param nums
     * @return
     */
    public int majorityElement1(int[] nums) {

        if (nums.length == 1) {
            return nums[0];
        }

        HashMap<Integer, Integer> map = new HashMap<>();

        int num = 0;
        int maxCount = 0;
        double dmjCount = ((double) nums.length) / 2;
        int mjCount = (int) Math.ceil(dmjCount);

        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            int count = 0;

            if (map.get(value) == null) {
                map.put(value, 1);
                count = 1;
            } else {
                count = map.get(value);
                count++;
                map.put(value, count);
            }
            if (count > maxCount) {
                num = value;
                maxCount = count;
            }
        }
        return num;
    }


    /**
     * [5,2,5,4,1,5,5,4,6,5]
     * 摩尔投票
     * <p>
     * 这个需要善于利用题目提供的条件，要审题到位。
     * 并且给定的数组总是存在多数元素。 这句话很重要。
     * <p>
     * 也即是说，数组中一定会存在一个数的个数大于nums.length/2的数量的
     * <p>
     * 抽象一些来说：数组里可划分为 n和不是n的数，称为m吧
     * 简单的划分为n和m两种类型的数。
     * n的个数大于nums.length/2。非n的m数个数必然是小于nums.length/2。
     * <p>
     * 2 ms
     *
     * @param nums
     * @return
     */
    public int majorityElement2(int[] nums) {

        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }

    /**
     * 写这个算法，其实就是为了理解摩尔投票的抵消思路的
     * <p>
     * 7 ms 运行速度有些慢
     * <p>
     * 根据两两抵消思路。用一个集合存储当前相同的值，如果遇到不同的值，进行抵消。将之前的存入的值删除，也不加入新的值。
     * 最终集合里至少会有一个数，这个数就是多数元素。
     *
     * @param nums
     * @return
     */
    public int majorityElement3(int[] nums) {

        int num = 0;

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < nums.length; i++) {

            if (stack.size() == 0) {
                //当集合为0时，说明全部抵消，那么就从新开始，将新的数加入，记录下加入集合的数字
                num = nums[i];
                stack.add(nums[i]);
            } else if (num == nums[i]) {
                //当和集合里的数一样，则将新的数加入
                stack.add(nums[i]);
            } else {
                //和集合里数不一样，则不加入新的数，并且将集合里的数删除一个。
                stack.pop();
            }
        }

        return stack.pop();
    }

    /**
     * 这里 我们尝试取掉Stack。而用count来代表抵消后num的数量
     * <p>
     * 速度竟然只用了1s 看来stack的数组操作还是比较耗时的
     * <p>
     * 看到这里其实对于摩尔投票基本有所理解了。
     *
     * @param nums
     * @return
     */
    public int majorityElement4(int[] nums) {

        int num = 0;
        int count = 0;

        for (int i = 0; i < nums.length; i++) {

            if (count == 0) {
                //说明是抵消完了，或者是开始遍历。记录下num，并记录下num的数量
                num = nums[i];
                count = 1;
            } else if (num == nums[i]) {
                //当新数字和上个数字一样时，给num的数量加一
                count++;
            } else {
                //当新的数字和上个数字不一样时，说明要产生抵消了，那么num数量要减一。
                count--;
            }
        }
        return num;
    }


}
