package xiaolong.classicarithmetic_lib;

/**
 * Created by xiaolong on 2020-04-23.
 * email：xinxiaolong123@foxmail.com
 * <p>
 * https://www.runoob.com/w3cnote/bubble-sort.html
 * <p>
 * <p>
 * 选择排序是一种简单直观的排序算法，无论什么数据进去都是 O(n²) 的时间复杂度。
 * 所以用到它的时候，数据规模越小越好。比起冒泡排序的有点，交换数据次数减少
 * 内存使用降低。
 */
public class SelectSort {

    public static void main(String[] agr) {

        int[] numbers = new int[]{9, 3, 199, 200, 25, 1, 26, 89, 103, 55, 66, 160, 6, 566, 965, 985};

        SelectSort selectSort = new SelectSort();

        selectSort.sort(numbers);

        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + "  ");
        }

    }

    /**
     * 和冒泡的基本思路很像，只是这里先记录先最小值的下标。
     * 一次遍历完成才进行数据交换。
     * 值得说的是，今天写这个选择算法循环是有这比较清晰的思路的，一次跑成功的。
     *
     * @param numbers
     */
    public void sort(int[] numbers) {

        //从0开始遍历，取出最小值的放入，在从后面找出最小值，再放入之前最小值的下一位。
        for (int i = 0; i < numbers.length; i++) {

            //假设最开始的值是最小的
            int minIndex = i;

            //进行循环遍历对比
            for (int n = i + 1; n < numbers.length; n++) {

                //当遍历出后面有值比之前最小值还小，则记录下数组小标
                if (numbers[minIndex] > numbers[n]) {
                    minIndex = n;
                }
            }

            //遍历完成，得出最小值跟开头假设的下标不一致则进行一次数据交换。将最小值交换到数据头。
            if (minIndex != i) {
                int temp = numbers[i];
                numbers[i] = numbers[minIndex];
                numbers[minIndex] = temp;
            }
        }
    }


}
