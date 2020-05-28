package xiaolong.classicarithmetic_lib;

/**
 * Created by xiaolong on 2020-04-22.
 * email：xinxiaolong123@foxmail.com
 *
 *
 * https://www.runoob.com/w3cnote/bubble-sort.html
 *
 * 冒泡排序（Bubble Sort）也是一种简单直观的排序算法。它重复地走访过要排序的数列，一次比较两个元素，如果他们的顺序错误就把他们交换过来。
 * 走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成。这个算法的名字由来是因为越小的元素会经由交换慢慢"浮"到数列的顶端。
 * 作为最简单的排序算法之一，冒泡排序给我的感觉就像 Abandon 在单词书里出现的感觉一样，每次都在第一页第一位，所以最熟悉。冒泡排序还有一种优化算法，
 * 就是立一个 flag，当在一趟序列遍历中元素没有发生交换，则证明该序列已经有序。但这种改进对于提升性能来
 * 说并没有什么太大作用。
 * <p>
 * 冒泡有冒泡的一个很大特点，因为是两两进行比较，它可以检测整个数组是否已经有序，
 * 当某次遍历没有发生任何交换的时候你就可以提前终止了。也算是个小优化吧。
 *
 *
 *
 */
public class BubbleSort {

    public static void main(String[] arg) {

        BubbleSort bubbleSort = new BubbleSort();

        int[] numbers = new int[]{1, 3, 199, 200, 25, 26, 89, 103, 55, 66, 160, 6, 566, 965, 985};
        bubbleSort.sort(numbers);

        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + "  ");
        }

        System.out.println("");

        //当是一个已经有序的数据，则只会执行numbers.length-1次
        bubbleSort.sort(numbers);

        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + "  ");
        }

    }


    /**
     * 如果打开网站去看遍历的动态图表就能更加清晰的看清。冒泡排序的思路
     * 从头部取一个数据出来，一一和后面的做对比和替换。直到替换到最后。
     * <p>
     * 特点就是遍历和相邻的数据进行对比，然后互换。
     *
     * @param numbers
     */
    public void sort(int[] numbers) {

        int count = 0;

        //为什么i是从1开始？因为冒泡的遍历一直自下而上。也就是一直从0开始到最大值-i；
        //一次的遍历，已经找出最大或最小值了。下次的遍历应该是这次的长度-1；
        for (int i = 1; i < numbers.length; i++) {

            boolean isSwap = false;

            for (int k = 0; k < numbers.length - i; k++) {

                count++;

                int number1 = numbers[k];
                int number2 = numbers[k + 1];
                if (number1 > number2) {
                    numbers[k] = number2;
                    numbers[k + 1] = number1;
                    isSwap=true;
                }
            }
            if(!isSwap){
                break;
            }
        }

        System.out.println("一共执行了" + count + "次" + "  length=" + numbers.length);

    }
}
