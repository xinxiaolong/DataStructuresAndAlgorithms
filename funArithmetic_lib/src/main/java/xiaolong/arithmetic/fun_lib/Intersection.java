package xiaolong.arithmetic.fun_lib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaolong on 2020-04-23.
 * email：xinxiaolong123@foxmail.com
 * 这个题目也是之前线上面试的一道题。
 * <p>
 * https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/
 * <p>
 * 给定两个数组，编写一个函数来计算它们的交集。
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2,2]
 * <p>
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [4,9]
 */

public class Intersection {


    public static void main(String[] agr) {


        int[] nums1 = new int[]{-2147483648,1,2,3};
        int[] nums2 = new int[]{-2147483648,-2147483648,1};

        Intersection intersection = new Intersection();

        int[] numbers = intersection.intersection2(nums1, nums2);

    }

    /**
     * @param nums1 数组1
     * @param nums2 数组2
     * @return 交集的新数组
     *
     * 这个是自己思考加网上思路给你答案。刚开始是想到了要用hashMap的快速查找原理。
     * 先对第一个数组进行hashMap化。再遍历第二个数组进行查找。中间碰到一个问题
     * 开始我使用数组的值作为key,下标作为value。这样的话数组里出现相同的值是key就会被
     * 覆盖掉。一直没有想出好的办法，以为这个map是不能解决这类问题了。
     * 看到网上给的思路是，还是把数组的值作为key。value保存这个值出现的次数。
     * 当遍历第二个数组，当发生数据的碰撞时，则将map里保存这个出现的次数value值减1。
     * 这个思路真的很好！
     *
     */
    public int[] intersection(int[] nums1, int[] nums2) {

        ArrayList<Integer> list = new ArrayList();

        //这里用hashMap去保存第一个数组的元素信息。key数组每个下标对应的值，
        //value就很巧妙来保存这个值出现的次数。

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums1.length; i++) {
            //取得这个值之前被put进去的次数
            Integer value = map.get(nums1[i]);
            map.put(nums1[i], (value == null ? 1 : value + 1));
        }


        for (int i = 0; i < nums2.length; i++) {

            Integer value = map.get(nums2[i]);

            if (value != null && value != 0) {

                list.add(nums2[i]);
                map.put(nums2[i], value - 1);
                System.out.print(nums2[i] + " ");

            }
        }

        int[] numbers = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            numbers[i] = list.get(i);
        }

        return numbers;
    }


    /**
     * 这个是自己根据网上思路写的答案。但是运行起来的时间复杂度和上面的差不多
     * 没有网上的答案快。为什么？
     *
     * 原因找到了：for和while的效率是差不多的
     * 因为我这比多了:  System.out.print(nums1[i] + " "); 这句
     *
     * 而且for循环的时候要选出最长的数组，和小标不走的时候需要做一次i--的操作
     * 用while循环的更为合理
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection2(int[] nums1, int[] nums2) {


        //这里必须满足num1>=nums2才往下执行
        if(nums1.length<nums2.length){
            return intersection2(nums2,nums1);
        }

        //先进行排序
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        ArrayList<Integer> list = new ArrayList();


        int num2Index=0;

        for (int num1Index=0;num1Index<nums1.length;){

            if(num2Index>=nums2.length){
                break;
            }

            if(nums1[num1Index]==nums2[num2Index]){
                list.add(nums1[num1Index]);

                System.out.print(nums1[num1Index] + " ");

                num2Index++;
                num1Index++;
            }else if(nums1[num1Index]>nums2[num2Index]){
                num2Index++;

            }
        }

        int[] numbers = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            numbers[i] = list.get(i);
        }

        return numbers;
    }


    /**
     * 思路一样，用的是while实现，更加简洁。
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection3(int[] nums1, int[] nums2) {

        //先进行排序
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        ArrayList<Integer> list = new ArrayList();

        int num1Index=0;
        int num2Index=0;

        while (num2Index<nums2.length&&num1Index<nums1.length){
            if(nums1[num1Index]==nums2[num2Index]){
                list.add(nums1[num1Index]);
                System.out.print(nums1[num1Index] + " ");
                num2Index++;
                num1Index++;
            }else if(nums1[num1Index]>nums2[num2Index]){
                num2Index++;
            }else {
                num1Index++;
            }
        }

        int[] numbers = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            numbers[i] = list.get(i);
        }

        return numbers;
    }
}
