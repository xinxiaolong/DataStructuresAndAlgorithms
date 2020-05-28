package xiaolong.classicarithmetic_lib;

import java.util.Arrays;

/**
 * Created by xiaolong on 2020-04-30.
 * email：xinxiaolong123@foxmail.com
 * <p>
 * 这里感谢面试我算法的哥们，和leetCode.叫我提早接触到双指针的运用。
 * 以前看起来的归并算法很复杂。还是在于对双指针的使用不熟悉。
 * <p>
 * 这个算法是一种 “分治法” 的思路。
 * <p>
 * 归并排序（英语：Merge sort，或mergesort），是建立在归并操作上的一种有效的排序算法，效率为O(nlogn)
 * 1945年由约翰·冯·诺伊曼首次提出。该算法是采用分治法（Divide and Conquer）的一个非常典型的应用，
 * 且各层分治递归可以同时进行。
 * <p>
 * 思考：
 * <p>
 * 时间复杂度如何计算？
 * <p>
 * 这篇文章对于时间复杂度符号的说明很清晰:
 * https://www.jianshu.com/p/c7928775abac
 * <p>
 * 这篇文章对merge算法时间复杂度的计算
 * https://blog.csdn.net/YuZhiHui_No1/article/details/44223225
 * <p>
 * 归并的时间复杂度分析：主要是考虑两个函数的时间花销，
 * 一、数组划分函数sort()；
 * 二、有序数组归并函数merge()；
 * <p>
 * 也就是：拆分的时间+合并的时间
 * <p>
 * 1：在merge算法里。时间复杂度为 ：函数所花的时间为  O(n)  n有多大，就训换几次。
 * <p>
 * 2：简单的分析下元素长度为n的归并排序所消耗的时间 T[n]：调用sort()函数划分两部分，那每一小部分排序好所花时间则为  
 * T[n/2]，而最后把这两部分有序的数组合并成一个有序的数组merge()函数所花的时间为  O(n)；
 * <p>
 * 公式：T[n]  =  2T[n/2] + O(n)；
 * 所以得出的结果为：T[n] = O( nlogn )
 * <p>
 * <p>
 * 这篇文章也进行了细致分析：
 * <p>
 * https://www.jianshu.com/p/dfcfe667687b
 * <p>
 * 有个比较清晰的认识了：自己画个拆分的树形结构就明白了，比如这个数据为8，每次分解一半。
 * <p>
 * 计算思路：
 * 比如数据长度为n:
 * 因为要差分成2，2，一组的。所以得出 拆分次数==logn 。
 * 拆分后。自下而上，每一层都需要一一进行对比。每层的数据都有n个。有多少层就比较多少个n次
 * 所以总体时间为n*logn 标示为O(nlogn);
 */
public class MergerSort {

    public static void main(String[] agr) {

        int[] numbers = new int[]{9, 3, 199, 200, 25, 1, 99, 6, 26, 89, 103, 55, 66, 160, 6, 566, 965, 985};

        MergerSort mergeSort = new MergerSort();

        numbers = mergeSort.sort2(numbers);

        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + "  ");
        }
    }

    /*****************************************************************************************
     *************************************官方答案**********************************************
     ******************************************************************************************
     */

    public int[] sort(int[] sourceArray) {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        if (arr.length < 2) {
            return arr;
        }
        int middle = (int) Math.floor(arr.length / 2);

        int[] left = Arrays.copyOfRange(arr, 0, middle);
        int[] right = Arrays.copyOfRange(arr, middle, arr.length);

        return merge(sort(left), sort(right));
    }

    protected int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0;
        while (left.length > 0 && right.length > 0) {
            if (left[0] <= right[0]) {
                result[i++] = left[0];
                left = Arrays.copyOfRange(left, 1, left.length);
            } else {
                result[i++] = right[0];
                right = Arrays.copyOfRange(right, 1, right.length);
            }
        }

        while (left.length > 0) {
            result[i++] = left[0];
            left = Arrays.copyOfRange(left, 1, left.length);
        }

        while (right.length > 0) {
            result[i++] = right[0];
            right = Arrays.copyOfRange(right, 1, right.length);
        }

        return result;
    }


    /*****************************************************************************************
     *************************************自己实现**********************************************
     ******************************************************************************************
     */
    public int[] sort1(int[] array) {
        int[] nums = Arrays.copyOf(array, array.length);

        //这里需要小于2,最终将两边数组分割成最小粒度，也就是只有一个元素。
        if (nums.length < 2) {
            return nums;
        }

        int middle = (int) Math.floor(nums.length / 2);

        int[] nums1 = Arrays.copyOfRange(array, 0, middle);
        int[] nums2 = Arrays.copyOfRange(array, middle, array.length);
        return merge1(sort1(nums1), sort1(nums2));
    }

    /**
     * 来到了使用双指针，对两个数组进行合并排序的环节
     * <p>
     * 以为之前使用到双指针对两个数组的交集进行判断，所以看到这里就觉得比较简单了
     * 学习算法的确要循序渐进!
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] merge1(int[] nums1, int[] nums2) {

        //用一个大的数组，对两个数组进行排序合并的储存。
        int[] nums = new int[nums1.length + nums2.length];

        //数组1的下标
        int n1 = 0;
        //数组2的下表
        int n2 = 0;

        for (int i = 0; i < nums.length; i++) {
            //num1数组数据被取完
            if (n1 >= nums1.length) {
                nums[i] = nums2[n2];
                n2++;
                continue;
            }

            //num2的数据被取完
            if (n2 >= nums2.length) {
                nums[i] = nums1[n1];
                n1++;
                continue;
            }

            //num1当前下标的数比num2当前的数小。将num1[n1]放入大数组，num1小标自增。
            if (nums1[n1] <= nums2[n2]) {
                nums[i] = nums1[n1];
                n1++;
                continue;
            }

            //num1当前下标的数比num2当前的数大。将num2[n2]放入大数组，num2下标自增。
            if (nums1[n1] > nums2[n2]) {
                nums[i] = nums2[n2];
                n2++;
            }
        }
        return nums;
    }


    /*****************************************************************************************
     *************************************回顾反刍**********************************************
     ******************************************************************************************
     */
    public int[] sort2(int[] array) {
        int[] nums = Arrays.copyOf(array, array.length);

        if (array.length < 2) {
            return nums;
        }

        int middle = nums.length / 2;


        int[] nums1 = Arrays.copyOfRange(nums, 0, middle);
        int[] nums2 = Arrays.copyOfRange(nums, middle, nums.length);

        return merge2(sort2(nums1), sort2(nums2));
    }


    public int[] merge2(int[] nums1, int[] nums2) {

        int index1 = 0;
        int index2 = 0;

        int i = 0;

        int[] nums = new int[nums1.length + nums2.length];

        while (index1 < nums1.length && index2 < nums2.length) {

            if (nums1[index1] < nums2[index2]) {

                nums[i] = nums1[index1];
                index1++;

            } else {
                nums[i] = nums2[index2];
                index2++;
            }

            i++;
        }

        while (index1 < nums1.length) {
            nums[i] = nums1[index1];
            index1++;
            i++;
        }

        while (index2 < nums2.length) {
            nums[i] = nums2[index2];
            index2++;
            i++;
        }

        return nums;
    }


}
