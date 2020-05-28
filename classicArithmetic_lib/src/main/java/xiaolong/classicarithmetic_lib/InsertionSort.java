package xiaolong.classicarithmetic_lib;

/**
 * Created by xiaolong on 2020-04-24.
 * email：xinxiaolong123@foxmail.com
 * <p>
 * 插入排序的代码实现虽然没有冒泡排序和选择排序那么简单粗暴，但它的原理应该是最容易理解的了，
 * 因为只要打过扑克牌的人都应该能够秒懂。插入排序是一种最简单直观的排序算法，它的工作原理是
 * 通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
 * <p>
 * 插入排序和冒泡排序一样，也有一种优化算法，叫做拆半插入。
 * <p>
 * 思考：
 * 1：和冒泡，选择算法的对比的优缺点。
 * 2：如何优化？
 * <p>
 * <p>
 * 1：在高度有序的数组排序中，插入排序以及优化后的插入排序相比于选择排序效率大大的提高了，
 * 虽然二者都是O（n^2）的复杂度。为什么呢？
 * <p>
 * 首先：肯定比冒泡效率高的，因为时间复杂度是都O（n^2）而冒泡需要不停的交换。
 * 再者，跟选择排序对比，选择排序还是跟冒泡一样一直再一个无序中找最大值或者最小值。
 * 而插入是在一个有序中找最大或者最小值。所以在一个高度有序时，插入算法更显优势。
 * <p>
 * 结合：插入排序平均性能>选择排序>冒泡排序
 * <p>
 * 2：如何优化？
 * 开始看到介绍这个的网站时，提到了优化办法，折半查找。做完了插入排序的代码，自然想到了什么叫折半查找
 * 因为插入算法是在有序中找到目标数的合适位置。基本的是遍历，还有就是二分法查找。这里我突然又想到了SparseArray
 * 数据结构的key是不是插入算法+二分法查找做的优化？
 * 这个网址很值得看一下：https://www.jianshu.com/p/6d55ac4b72b4
 * <p>
 * 先完成一个二分法查找的插入算法优化吧。
 */
public class InsertionSort {

    public static void main(String[] agr) {

        InsertionSort insertionSort = new InsertionSort();

        int[] numbers = new int[]{1, 6,6,6,6,4,4,4,4,2,4,199, 200, 25, 26, 89, 103, 55, 66, 160, 6, 566, 965, 985};
        insertionSort.sort2(numbers);

        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + "  ");
        }

    }

    /**
     * 这个是我写的第一个版本。费了些力气，半个小时！
     * 还不是一个好的方案，因为多了一次交换。
     * <p>
     * https://blog.csdn.net/qq_19782019/article/details/78021705
     * <p>
     * 这个网站和好的对比了两张个的区别
     *
     * @param numbers
     */
    public void sort(int[] numbers) {

        for (int i = 0; i < numbers.length - 1; i++) {

            for (int n = i + 1; n > 0; n--) {

                //这里写成了类似冒泡的步骤了，将新的数在之前有序数组里进行冒泡！
                //和插入思路有点不一样，插入也会往后面移动，但不会做冒泡式的交换。
                if (numbers[n] < numbers[n - 1]) {
                    int temp = numbers[n];
                    numbers[n] = numbers[n - 1];
                    numbers[n - 1] = temp;
                }
            }
        }
    }

    /**
     * 这个是插入算法
     *
     * @param numbers
     */
    public int[] sort1(int[] numbers) {

        //从下标为1的元素开始选择合适的位置插入，因为下标为0的只有一个元素，默认是有序的
        for (int i = 1; i < numbers.length; i++) {

            //记录即将插入的数
            int temp = numbers[i];

            // 从已经排序的序列最右边的开始比较，找到比其小的数
            int index = i;

            //从已经排序的序列最右边的开始比较，找到比其小的数。
            //这里我翻了个错，就是误将temp和下标为index值做对比，忘记将index-1了。真是粗心粗心！
            //耐心就好，慢慢训练大脑。
            while (index > 0 && temp < numbers[index - 1]) {
                // 需要反复把已排序元素逐步向后挪位，为最新元素提供插入空间。
                numbers[index] = numbers[index - 1];
                index--;
            }

            // 存在比其小的数，插入
            if (index != i) {
                numbers[index] = temp;
            }

        }

        return numbers;
    }


    /**
     * 这个是插入算法，用二分法优化。
     *
     * @param numbers
     */
    public void sort2(int[] numbers) {

        //从下标为1的元素开始选择合适的位置插入，因为下标为0的只有一个元素，默认是有序的
        for (int rightIndex = 1; rightIndex < numbers.length; rightIndex++) {

            //记录即将插入的数
            int temp = numbers[rightIndex];

            //使用二分法查找出最近temp在之前有序数组里最近的位置
            int start = 0;
            //这个赋值比较关键，需要在自己之前的数组里找，不包含自己。所以减1
            int end = rightIndex-1;
            int middle = (start + end) / 2;

            //这里实现二分法查找
            while (start <= end) {

                if(temp==numbers[middle]){
                    break;
                }else if(temp>numbers[middle]){
                    start=middle+1;
                }else {
                    end=middle-1;
                }
                middle=(start + end) / 2;
            }

            /**
             * 这是是要进行+1处理的，因为找不到的时候最后的middleIndex上的值小于这个值的。
             * 再插入的index=middleIndex+1;
             */
            int insertIndex=middle+1;

            int rightMove=rightIndex;

            //已要插入的下边为开始将所有数据进行后移。就插入新的数据腾地方。
            for (;rightMove>=insertIndex;rightMove--){
                numbers[rightMove]=numbers[rightMove-1];
            }

            //插入新的数据
            numbers[insertIndex]=temp;

        }
    }

}
