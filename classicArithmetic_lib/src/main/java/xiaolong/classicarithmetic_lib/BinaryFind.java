package xiaolong.classicarithmetic_lib;

/**
 * Created by xiaolong on 2020-04-24.
 * email：xinxiaolong123@foxmail.com
 */
public class BinaryFind {

    public static void main(String[] agr) {

        int[] numbers = new int[]{1,6,6,6,6,4,4,4,4};

        BinaryFind find = new BinaryFind();
        int index = find.find(2, numbers);

        System.out.println("找到下标为" + index);
    }

    /**
     * 二分法查找
     * @param tagNum
     * @param nums
     * @return
     */
    public int find(int tagNum, int[] nums) {

        int start = 0;
        int end = start + nums.length;
        int middle = (start + nums.length) / 2;

        while (start <= end) {
            if (tagNum == nums[middle]) {
                return middle;
            } else if (tagNum > nums[middle]) {
                start = middle+1;
            } else {
                end = middle-1;
            }
            middle = (start + end) / 2;

            System.out.println("start"+start+"   end"+end+"    middle"+middle);
        }

        return -1;
    }

}
