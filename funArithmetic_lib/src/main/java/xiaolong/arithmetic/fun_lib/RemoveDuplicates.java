package xiaolong.arithmetic.fun_lib;

import java.util.Arrays;

/**
 * Created by xiaolong on 2020-05-03.
 * email：xinxiaolong123@foxmail.com
 *
 * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array
 *
 * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
 *
 * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
 *
 *
 */
public class RemoveDuplicates {


    public static void main(String[] arg){
       int[] nums=new int[]{1,1,2};

       RemoveDuplicates removeDuplicates=new RemoveDuplicates();


       int num=removeDuplicates.removeDuplicates(nums);

       System.out.print(num+"  "+ Arrays.toString(nums));
    }

    /**
     * 条件：1：排序的，2：原地 修改 3：并在使用 O(1) 额外空间的条件下完成
     *
     * 想到齐头并进双指针。
     * @param nums
     * @return
     *
     * 大体思路正确，也是因为做了之前的 RemoveElement 算法。对双指针有了进一步熟悉。
     *
     */
    public int removeDuplicates(int[] nums){

        //代码的健壮
        if (nums.length == 0) return 0;

        //遍历下标
        int i=1;
        //无重复数据分割下标
        int j=0;

        for (;i<nums.length;i++){
            if(nums[i]!=nums[j]){
                //j向右移动，准备存储合法值
                j++;
                nums[j]=nums[i];
            }
        }

        return j+1;
    }



}
