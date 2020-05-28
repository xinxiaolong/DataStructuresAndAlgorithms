package xiaolong.arithmetic.fun_lib;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiaolong on 2020-04-22.
 * email：xinxiaolong123@foxmail.com
 * <p>
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 这个算法不算难。难在于对时间复杂度的计算。如何巧妙避免嵌套循环，利用hashMap进行优化
 *
 */
public class SumOfTwoNumbers {

    public static void main(String[] arg) {

        SumOfTwoNumbers sum = new SumOfTwoNumbers();
        int[] nums1 = {1, 2, 6, 4, 3, 7, 8};
        sum.printIndexSum(nums1, 7);
    }

    /**
     * 这个是我首先想到的方案。
     * 很简单的思路。类似于冒泡排序了。先取第一个数和后面的数挨个相加，如果不符合就取第二个数和后面的数挨个相加
     * 从性能上来说的话
     * 执行用时 : 91 ms , 在所有 Java 提交中击败了 15.29% 的用户
     * 内存消耗 : 39.4 MB , 在所有 Java 提交中击败了 5.06%的用户
     * 表现的比较差
     * 思考怎么优化.....
     *
     * @param nums1
     * @param sum
     * @return
     */
    public int[] printIndexSum(int[] nums1, int sum) {

        int[] index = new int[]{0, 0};
        for (int i = 0; i < nums1.length; i++) {
            int number1 = nums1[i];
            for (int n = i + 1; n < nums1.length; n++) {
                if (number1 + nums1[n] == sum) {
                    System.out.println("数组一的下标+" + i + "数组二的小标" + n);
                    index = new int[]{i, n};
                }
            }
        }
        return index;
    }

    /**
     * 这个是网站的方法
     * <p>
     * 测试后执行3ms比我之前的87ms将近40倍！内存消耗上是差不多的
     * 为什么呢？
     * 首先很明显的一点，虽然有两个for循环，但是少了嵌套。
     *
     * 思路很巧妙！利用了减法，
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum1(int[] nums, int target) {

        //用map缓存数据 value为数组下标，key为对应下标的数值。
        Map<Integer, Integer> map = new HashMap<>();

        //遍历将所有数组的值都放入map中。方便查找。
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        //只需一次遍历
        for (int i = 0; i < nums.length; i++) {

            //用减法的形式，求的满足 当前数+complement==target的数值
            int complement = target - nums[i];
            //利用hashmap特性找出对应的值，这里需要排除的是这个值不能是自己。
            //比如target==8 自身==4 那么与自身相加也满足条件，这个是需要排除的。
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[] { i, map.get(complement) };
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }


    /**
     * 这个是网站的方法
     * <p>
     * 测试后执行2ms比我之前的91ms快了40多倍！内存消耗上是差不多的
     * 为什么呢？
     * 首先很明显的一点，少了一层循环！
     *
     * 如果看过上面的twoSum1算法，再看这个就更好了
     * 这里巧妙在于优化了map的储存。可以在进行求差值的遍历时进行记录。
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {

        //用map缓存数据 value为数组下标，key为对应下标的数值。

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            //利用减法求的，满足和当前数值相加之和==target的值
            int complement = target - nums[i];

            //从map里取出是否保存过符合条件的值。
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }

            //将这次的值放入map中备用，为后面寻找满足差值条件。
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

}
