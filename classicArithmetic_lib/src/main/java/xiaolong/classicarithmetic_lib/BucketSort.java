package xiaolong.classicarithmetic_lib;

import java.util.Arrays;

/**
 * Created by xiaolong on 2020-05-05.
 * email：xinxiaolong123@foxmail.com
 *
 * 桶排序是计数排序的升级版。它利用了函数的映射关系，高效与否的关键就在于这个映射函数的确定。
 * 为了使桶排序更加高效，我们需要做到这两点：
 *
 * 在额外空间充足的情况下，尽量增大桶的数量
 * 使用的映射函数能够将输入的 N 个数据均匀的分配到 K 个桶中
 *
 * 同时，对于桶中元素的排序，选择何种比较排序算法对于性能的影响至关重要。
 *
 * 1. 什么时候最快
 * 当输入的数据可以均匀的分配到每一个桶中。
 *
 * 2. 什么时候最慢
 * 当输入的数据被分配到了同一个桶中。这种还不如直接就用计数排序。
 *
 *
 * 与计数排序来说。计数排序就是一个桶最多嘴个元素。拿到桶里的数据，不需要再用其他的排序算法再进行排序
 * 但是计数排序的内存消耗比桶排序要大很多。如果一个桶放2个数据。那么内存空间少一半。
 *
 * 但是计数排序时一个稳定的时间复杂度达到O(n)的一个算法。速度上对小数范围数据排序很快，内存也不太大。比如0-100的排序。
 *
 * 而桶排，更多的是利用充足的空间，对数据进行区域排序，然后结合快排，归并等优秀排序。会大大降低快排，归并等的O(nlogn)的时间。
 * 一般都是在空间充足情况下，对大数据进行分割处理的，做排序前提处理。
 *
 *
 */
public class BucketSort {

    public static void main(String[] agr){
        int[] numbers = new int[]{0, 9, 3, 199, 200, 25, 1, 99, 6, 26, 89, 103, 55, 66, 160, 6, 566, 965, 985};

        BucketSort sort=new BucketSort();

        sort.sort(numbers);

        System.out.print(Arrays.toString(numbers));
    }


    public int[] sort(int[] arr){
        if (arr.length == 0) {
            return arr;
        }

        //每个桶里面放100个数据
        int bucketSize=100;


        int minValue = arr[0];
        int maxValue = arr[0];

        for (int i=1;i<arr.length;i++){
            if(minValue>arr[i]){
                minValue=arr[i];
            }

            if(maxValue<arr[i]){
                maxValue=arr[i];
            }
        }

        //计算出来需要通的数量
        int bucketCount = (int) Math.floor((maxValue - minValue) / bucketSize) + 1;

        int[][] buckets = new int[bucketCount][0];

        for (int i=0;i<arr.length;i++){

            //得到存放在那个桶里
            int index = (int) Math.floor((arr[i] - minValue) / bucketSize);

            //对应的桶里放入数据
            buckets[index] = arrAppend(buckets[index], arr[i]);

        }

        int arrIndex = 0;

        InsertionSort insertionSort=new InsertionSort();

        for (int[] bucket:buckets){

            bucket=insertionSort.sort1(bucket);

            for (int i=0;i<bucket.length;i++){
                arr[arrIndex]=bucket[i];

                arrIndex++;
            }
        }
        return arr;

    }

    /**
     * 自动扩容，并保存数据
     *
     * @param arr
     * @param value
     */
    private int[] arrAppend(int[] arr, int value) {
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = value;
        return arr;
    }


}
