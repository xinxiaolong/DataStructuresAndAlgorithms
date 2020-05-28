package xiaolong.arithmetic.fun_lib;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * Created by xiaolong on 2020-04-27.
 * email：xinxiaolong123@foxmail.com
 *
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 说明:  你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * 输入: [4,1,2,1,2]
 * 输出: 4
 */
public class SingleNumber {

    public static void main(String[] agr) {

        int[] nums = new int[]{1, 2, 3, 2, 3, 4, 4, 5, 1 };

        SingleNumber singleNumber = new SingleNumber();

        int single = singleNumber.getSingleNumber3(nums);

        System.out.println(single);
    }


    /**
     *
     *
     * 耗时：11 ms
     *
     * 初步的算法，拿来了之前的计数思路。
     *
     * @param nums
     * @return
     *
     * 优化思路：
     * 1：排序遍历，可以更快找到相同数，因为排了序相同的数会在一块。 但是排序需要花费时间。
     * 2：是用数学的思想。因为规定的数组里的数字只有一个是单的，其他都是两两出现，2n+x=m1
     * 可以利用set去重 得出n+x=m2。 就可以求得x的值
     *
     *
     */
    public int getSingleNumber(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            Integer value = map.get(nums[i]);

            if (value == null) {
                map.put(nums[i], 1);
            } else {
                value++;
                map.put(nums[i], value);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            Integer value = map.get(nums[i]);

            if (value == 1) {
                return nums[i];
            }
        }
        return -1;
    }

    /**
     *
     * 执行用时：9 ms
     *
     * 是用数学的思想。因为规定的数组里的数字只有一个是单的，其他都是两两出现，2n+x=m1
     * 可以利用set去重 得出n+x=m2。 就可以求得x的值
     *
     * @param nums
     * @return
     */
    public int getSingleNumber1(int[] nums) {

        Set<Integer> set = new HashSet();

        //sum1=2n+x
        int sum1 = 0;
        //sum2=n+x
        int sum2 = 0;

        for (int i = 0; i < nums.length; i++) {

            if (!set.contains(nums[i])) {
                sum2 += nums[i];
            }
            sum1 += nums[i];
            set.add(nums[i]);
        }

        return sum2 * 2 - sum1;
    }

    /**
     *
     * 执行用时：9 ms
     *
     * 既然用set可以去重，那么何不判断重复后就不要输入呢？
     *
     */

    public int getSingleNumber2(int[] nums) {

        HashSet<Integer> set = new HashSet();

        for (int i = 0; i < nums.length; i++) {

            if (set.contains(nums[i])) {
                set.remove(nums[i]);
            }else {
                set.add(nums[i]);
            }
        }

        int single=0;
        for (Integer integer:set){
            single=integer;
        }
        return single;
    }


    /**
     *
     *   执行用时 : 1 ms , 在所有 Java 提交中击败了 99.73%
     *
     *
     *   这个思路很巧妙，用java的运算符^
     *   因为出现了对的数和一个单数。
     *
     *   (两者相反则为真)
     *
     *  ^异或运算符顾名思义，异就是不同，其运算规则为
     *   1^0 = 1 , 1^1 = 0 , 0^1 = 1 , 0^0 = 0
     *
     *   第一点：任何数xor自己本身会等于0。有个数n，n^n=0;
     *   第二点：任何数xor 0就等于任何数。有个数n，n^0=n;
     *
     *   所以呢，集合里的数，如果全部都是两两出现，那么结果必反为0；剩下的一个数进行^就会等于这个数
     *
     *   a ^ b ^ a ^ b ^ c = a ^ a ^ b ^ b ^ c = (a ^ a) ^ (b ^ b) ^ c = 0 ^ 0 ^ c = 0 ^ c = c
     *
     *
     *
     */
    public int getSingleNumber3(int[] nums) {

        int number=0;
        for (int n:nums) {
            number=number^n;
        }
        return number;
    }

}
