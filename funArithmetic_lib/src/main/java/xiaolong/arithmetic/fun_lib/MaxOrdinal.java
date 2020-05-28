package xiaolong.arithmetic.fun_lib;

/**
 *
 * Created by xiaolong on 2020-04-30.
 * email：xinxiaolong123@foxmail.com
 *
 */
public class MaxOrdinal {

    public static void main(String[] agr) {

        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};

        MaxOrdinal maxOrdinal = new MaxOrdinal();

        int maxValue = maxOrdinal.getMaxOrdinal1(nums);

        System.out.print(maxValue);
    }

    /**
     * 初步的思路；竟然写出了三个循环嵌套。总比写不出来好。至少顺利下思路。
     *
     * 思路：
     * 先两两相加。然后三个三个相加，然后四个四个相加.....
     *
     * 直接时间太长，没能提交上去.........
     *
     * 思考优化吧：要求O(n)。。。。。有点变态啊。一个循环?
     *
     *
     * @param nums
     * @return
     */
    public int getMaxOrdinal(int[] nums) {

        //开始start和end的距离为1 ，也就是两两相加
        int startIndex = 0;
        int distance = 1;

        int max = nums[0];

        int maxStart = 0;
        int maxEnd = 1;

        //
        while (distance <= nums.length) {

            startIndex = 0;

            //更新需要游走的endIndex

            int moveEndIndex = startIndex + distance;

            while (moveEndIndex <= nums.length) {

                int sum = 0;

                //计算的这一组的值
                for (int i = startIndex; i < moveEndIndex; i++) {
                    sum = sum + nums[i];
                }

                //和之前的最大数对比。
                if (sum > max) {
                    //更新数据
                    max = sum;
                    maxStart = startIndex;
                    maxEnd = moveEndIndex;
                }

                //起始位置都向后移动一位，进行下一组的计算
                startIndex++;
                moveEndIndex++;
            }

            distance++;
        }

        return max;
    }


    /**
     * 这个是网上给的暴力思路，比我得要简单很多
     *
     * {-2,1,-3,4,-1,2,1,-5,4}
     *
     *  看过之后，觉得昨天自己想的太复杂了
     *  这个算法到是简单直接。
     *
     *  住要是在一组计算的过程中找出最大值。
     *  而我是在一组计算完毕才去找最大值。
     *
     * @param nums
     * @return
     */
    public int getMaxOrdinal1(int[] nums) {
        int n = nums.length;
        int ans = nums[0];
        int sum;
        for(int i = 0; i < n; i++){
            sum = 0;
            for(int j = i; j < n; j++){
                sum += nums[j];
                ans = Math.max(ans, sum);
            }
        }
        return ans;
    }


    /**
     * 这个解法````需要理解半天
     *
     * 看了会大概能看懂，
     *
     * 重点还是看看贪心算法。要出门了，先放着。。。
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int sum = 0;
        for(int num: nums) {
            if(sum > 0) {
                //只有出现正数时才累加
                sum += num;
            } else {
                //当出现负数时，就单个取出，毕竟这个时候对之前的sum是做减法的。
                sum = num;
            }

            max = Math.max(max, sum);
        }
        return max;
    }

}
