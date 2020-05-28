package xiaolong.classicarithmetic_lib;

import java.util.Arrays;

/**
 * Created by xiaolong on 2020-05-01.
 * email：xinxiaolong123@foxmail.com
 * <p>
 * <p>
 * 希尔排序是希尔（Donald Shell）于1959年提出的一种排序算法。希尔排序也是一种插入排序，
 * 它是简单插入排序经过改进之后的一个更高效的版本，也称为缩小增量排序，同时该算法是冲破O(n2）
 * 的第一批算法之一。本文会以图解的方式详细介绍希尔排序的基本思想及其代码实现。
 * <p>
 * <p>
 * 主要思路：分组进行排序。排序的时候使用，插入排序的思想。取有序数组相邻一位，插入到有序数组中去。
 * <p>
 * 为何能优化插入算法：
 * <p>
 * 主要通过两点来改进排序算法：
 * <p>
 * 一是插入排序在对近似有序的数列进行排序时，排序性能会比较好；（通过分组，进行排成一个近似有序的数组）
 * 二是插入排序的性能比较低效，即每次只能将数据移动一位。
 * <p>
 * https://www.cnblogs.com/chengxiao/p/6104371.html
 * 这个网站给了我清晰的思路。
 */
public class ShellSort {

    public static void main(String[] agr) {

        int[] array = new int[]{1, 3, 199,44, 200, 4,25,5, 26, 55, 66, 160,6,6};

        ShellSort shellSort = new ShellSort();

        System.out.print(Arrays.toString(shellSort.sort1(array)));

    }


    public int[] sort(int[] array) {
        int len = array.length;

        //控制步长，每次等分，直到为1
        for (int gap = len / 2; gap > 0; gap = gap / 2) {

            //开始根据步长进行排序
            for (int i = 0; i < len - gap; i++) {

                //拿出第一个元素
                int j = i;
                //拿出第二个元素
                int n = i + gap;

                //从后到前，遍历对比，遇到n比j小的则交换，并继续向前判断。
                while (j >= 0 && array[j] > array[n]) {

                    //进行冒泡交换。
                    swap(array, j, n);

                    //交换完之后，指针前移，进行下两个值的判断。
                    j -= gap;
                    n -= gap;
                }
            }
        }
        return array;
    }


    public void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * 和插入算法一样，对于数据对比时，进行的数据后移优化。
     * <p>
     * 前者进行交换，现在是进行数组后移，然后插入。
     *
     * @param array
     * @return
     */
    public int[] sort1(int[] array) {
        int len = array.length;

        //控制步长，每次等分，直到为1
        for (int gap = len / 2; gap > 0; gap = gap / 2) {

            //开始根据步长进行排序
            for (int i = 0; i <= len - gap-1; i++) {

                int n = i + gap;

                //拿出将要对比的值
                int temp = array[n];

                //从后到前，遍历对比，遇到n比j小的则交换，并继续向前判断。
                while (n >= gap && temp < array[n-gap]) {

                    //进行数组后移
                    array[n] = array[n - gap];

                    //交换完之后，指针后移，准备对下个值进去判断
                    n -= gap;
                }

                //如果对比完成后，发现新数据的下标n发生了移动。说明需要进行数据更新，将temp插入到n位置。
                if (n != i + gap) {
                    array[n] = temp;
                }
            }
        }
        return array;
    }


    public int[] sort2(int[] array) {

        int len = array.length;

        for (int step = len / 2; step >= 1; step = step / 2) {

            //当前遍历到的位置
            int i = 0;
            //取出即将做对比的数下标
            int j = i + step;

            while (j <= len - 1) {

                //取出新的数据
                int num = array[j];

                //向后遍历插入新的数据。
                while (j >= step && num < array[j-step]) {

                    //移动数组数据，给即将插入的新数据留出位置。
                    array[j] = array[j-step];
                    //将指针向左移动一个步长。
                    j = j - step;
                }

                if (j != i+step) {
                    array[j] = num;
                }

                i++;
                j = i + step;
            }
        }

        return array;
    }


}
