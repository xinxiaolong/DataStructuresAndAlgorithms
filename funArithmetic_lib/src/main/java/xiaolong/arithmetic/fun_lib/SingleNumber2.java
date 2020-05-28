package xiaolong.arithmetic.fun_lib;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by xiaolong on 2020-04-28.
 * email：xinxiaolong123@foxmail.com
 *
 * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。
 * 要求时间复杂度是O(n)，空间复杂度是O(1)。
 *
 * https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof/
 *
 * 和昨天做的题目一样的。为了加深熟练度，和好奇有么有其他巧妙解法。
 *
 * 条件：
 * 2 <= nums <= 10000
 */
public class SingleNumber2 {

    public static void main(String[] agr) {

        int[] nums = new int[]{14, 2, 5, 6, 2, 6, 5, 20};

        SingleNumber2 single = new SingleNumber2();

        int[] singles = single.get2(nums);

        System.out.println(singles[0] + "   " + singles[1]);
    }


    /**
     * 12 ms , 在所有 Java 提交中击败了 18.73% 的用户
     * <p>
     * 用set去重，运行时间还是长
     *
     * @param nums
     * @return
     */
    public int[] get(int[] nums) {
        Integer[] singles = new Integer[2];

        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {


            if (set.contains(nums[i])) {
                set.remove(nums[i]);
            } else {
                set.add(nums[i]);
            }
        }

        singles = set.toArray(singles);
        return new int[]{singles[0], singles[1]};
    }

    /**
     * 2 ms , 在所有 Java 提交中击败了 95.47% 的用户
     * <p>
     * 已知了2 <= nums <= 10000 这个条件，那么就利用空间换时间。
     * 更具之前看到的用数组下标排序思路，最多用长度10000放置数据就可以，
     * 然后计算出现的次数就行
     * <p>
     * 和get()方法对比:get1()直接使用空间换时间，没有其他中间处理算法处理，虽然get1()有两个for循环。
     * 但是set的contains ,remove。都是花时间进行hash算法，找出对应位置。
     * <p>
     * 还是第一次写出打败90%以上用户的代码。
     *
     * @param nums
     * @return
     */
    public int[] get1(int[] nums) {
        int[] singles = new int[2];

        int[] grids = new int[10001];

        for (int i = 0; i < nums.length; i++) {

            int index = nums[i];

            if (grids[index] == 1) {
                grids[index] = 0;
            } else {
                grids[index] = 1;
            }
        }

        int index = 0;
        for (int i = 0; i < grids.length; i++) {
            if (grids[i] > 0) {
                singles[index] = i;
                index++;
            }
        }
        return singles;
    }

    /**
     *
     *
     *  2 ms , 在所有 Java 提交中击败了 95.47% 的用户
     *
     * 这个是利用java为移运算进行巧妙解法。
     *
     * 分析过程比较复杂，所以写在了有道上。
     *
     * 分析过程：
     * http://note.youdao.com/noteshare?id=7e0fe0ed864af9ab76a208a4671c97b9
     *
     * 位移符认识：
     * http://note.youdao.com/noteshare?id=7d6b488251ff0803c5cce22f1e77c34c
     *
     * @param nums
     * @return
     */
    public int[] get2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        int eor = 0;

        for (int num : nums) {
            //遍历进行^，得到两个落单的数^的结果
            eor ^= num;
        }

        //在结果eor里找出低位首次出现差异的数。也就是出现1的数
        int rightOne = 1;
        //找出eor最低位为1的数，即E和F中一个在该位为1，另一个在该位为0
        //也就是从eor中找到从低位开始，首次出现1的数。
        while ((eor & rightOne) == 0) {
            rightOne = rightOne << 1;
        }
        //第一个数
        int eorHasOne = 0;
        //第二个数
        int eorHasTwo = 0;
        //开始遍历分组进行^计算，得到两个落单的数
        for (int num : nums) {
            if ((num & rightOne) == 0) {
                eorHasOne ^= num;
            } else {
                eorHasTwo ^= num;
            }
        }

        return new int[]{eorHasOne, eorHasTwo};
    }


}
