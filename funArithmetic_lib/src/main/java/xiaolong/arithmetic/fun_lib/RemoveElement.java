package xiaolong.arithmetic.fun_lib;

import java.util.Arrays;

/**
 * Created by xiaolong on 2020-05-01.
 * email：xinxiaolong123@foxmail.com
 * <p>
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * <p>
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 给定 nums = [3,2,2,3], val = 3,
 * <p>
 * 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
 * <p>
 * 你不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 链接：https://leetcode-cn.com/problems/remove-element
 */
public class RemoveElement {

    public static void main(String[] arg) {

        int[] nums = new int[]{0, 1, 2, 2, 3, 0, 4, 2};

        RemoveElement removeElement = new RemoveElement();

        int len = removeElement.remove2(nums, 2);

        System.out.print(len + "   " + Arrays.toString(nums));
    }

    /**
     * 我草````怒了都.写了半天没写出来，头还写的疼，一看答案，更加崩溃。
     * 小学的题吗？明天看吧：5月1日晚9点-11点。
     * <p>
     * 思路里面带了将val都交换到后面的想法，所以做的也很复杂了。
     * 而且陷入了一个思维误区。双指针要进行一次遍历对两个指针都做移动是，一般都是同向的：
     * 要么都左到右，要么从右到左。我这里出现了相反的方向，for循序只能对一个方向的指针进行移动，
     * 另一个也一定的话只能--或者做内部循环。这个内部循环就显的非常复杂了，而且很难被注意到。
     * <p>
     * 这也是一个对双指针使用误区的一个典型例子。值得思考。
     *
     * @param nums
     * @param val
     * @return
     */
    public int remove(int[] nums, int val) {

        int endIndex = nums.length - 1;

        while (endIndex >= 0) {
            if (nums[endIndex] == val) {
                endIndex--;
            } else {
                break;
            }
        }

        if (endIndex == -1) {
            return 0;
        }

        for (int i = 0; i < nums.length; i++) {

            int num = nums[i];

            if (nums[i] == val && endIndex > i) {
                nums[i] = nums[endIndex];
                nums[endIndex] = val;

                //这里只做一次向左移动，是有问题的。当想做移动的时候，指向的下标数据也为val。误将为val的值交换到前面。
                //这种思路还是有不合理的。
                endIndex--;
            }
        }

        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                break;
            }
            count++;
        }

        return count;
    }

    /**
     * 5月3日早11点40
     * <p>
     * 如果实在要用收尾双指针，那么就要用while进行循环。分别对两个指针进行操作。
     * 不用在for理在写个while循环，去遍历另一个指针了。
     * 而且好处是将给定的值都做了后移。
     *
     * @param nums
     * @param val
     * @return
     */
    public int remove2(int[] nums, int val) {

        int endIndex = nums.length - 1;

        int index = 0;

        while (index < nums.length) {

            if (endIndex <= index) {
                break;
            }
            if (nums[endIndex] == val) {
                endIndex--;
            } else if (nums[index] == val) {
                nums[index] = nums[endIndex];
                nums[endIndex] = val;
                endIndex--;
                index++;
            } else {
                index++;
            }
        }

        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                break;
            }
            count++;
        }

        return count;
    }


    /**
     * 上面的收尾双指针实现起来非常复杂。难以计算出移除后剩余的个数。
     * <p>
     * 头部双指针的处理方式
     *
     * @param nums
     * @param val
     * @return
     */
    public int remove1(int[] nums, int val) {
        int index = 0;

        //i 是遍历格子的指针，一次取出数组值，和val进行判断。
        //index 是从0开始将不是val一次放入的格子下标指针。每存入一次就后移一次。
        //而且这是对起不为val进行了存储，并没有做交换。做交换的换，就成了我自己写的那种也是一道比较复杂的题吧。
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[index] = nums[i];
                index++;
            }
        }

        return index;
    }

    /**
     * 乖乖的分析下答案吧
     * <p>
     * 也是双指针的思路
     * <p>
     * {0,1,2,2,3,0,4,2}
     * <p>
     * 2
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }

    /**
     * 这个是对上面解法的优化。
     * <p>
     * 确切的说，应该是对我上面的收尾指针进行的优化。
     * <p>
     * 他没有做交换，利用了左指针，做了中间缓存区，分割。缓存区左边是合法数，右边是待验证数。
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement1(int[] nums, int val) {
        int i = 0;
        int n = nums.length;
        while (i < n) {

            //这里i指针下，缓存了从右到左取出的值，然后进行判断。
            if (nums[i] == val) {
                //使i指针的值等于有效末尾的值。
                nums[i] = nums[n - 1];
                // reduce array size by one
                n--;
            } else {
                i++;
            }
        }
        return n;

    }

}
