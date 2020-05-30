package xiaolong.arithmetic.fun_lib;

import java.util.Arrays;

/**
 * Created by xinxiaolong on 2020/5/29.
 * email：xinxiaolong123@foxmail.com
 * <p>
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 2:
 * <p>
 * 输入: [2,7,9,3,1]
 * 输出: 12
 * 解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 * <p>
 * <p>
 * 一开始小看了，其实是一个知识盲点，关于动态规划的算法。
 *
 * https://leetcode-cn.com/problems/house-robber/
 * 
 */
public class Thief {

    public static void main(String[] agr) {
        int[] nums = new int[]{1000, 300, 400, 50, 20, 10, 100, 200};
        Thief thief = new Thief();


        for (int i = 0; i < nums.length; i++) {
            int[] newNums = Arrays.copyOfRange(nums, 0, i + 1);
            int max1 = thief.rob1(newNums);
            System.out.println(max1);

            int max2 = thief.rob2(newNums);
            System.out.println(max2);

            System.out.println("**************");
        }
    }


    /**
     * 初步思路 挨个偷一遍，计算出最大的。
     * <p>
     * [2,2,3]
     * [2,3,2]
     * <p>
     * 仔细想了一下，其实就是检出最大的数，如果不相邻则进行相加，如果相邻则取最大值。
     * 那么这里就涉及到排序，和对原下表值的保存。
     * 事实证明这个思路不对！
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {

        int max = 0;
        Room[] rooms = new Room[nums.length];

        for (int i = 0; i < nums.length; i++) {
            Room room = new Room();
            room.index = i;
            room.value = nums[i];
            rooms[i] = room;
        }

        for (int i = 1; i < rooms.length; i++) {
            Room newRoom = rooms[i];
            int j = i - 1;
            while (j >= 0 && rooms[j].value > newRoom.value) {
                rooms[j + 1] = rooms[j];
                j--;
            }
            if (j + 1 != i) {
                rooms[j + 1] = newRoom;
            }
        }

        Room currentRoom = null;

        for (int i = rooms.length - 1; i >= 0; i--) {

            Room room = rooms[i];
            if (currentRoom == null) {
                currentRoom = room;
                max = currentRoom.value;
                continue;
            }

            if (currentRoom.index - 1 == room.index || currentRoom.index + 1 == room.index) {
                continue;
            }

            max = max + room.value;
            currentRoom = room;
        }
        return max;
    }

    class Room {
        int value;
        int index;
    }


    //[1,0,2,100]

    /**
     * 引入官方答案吧 仔细分析····
     *
     *
     */
    public int rob1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int[] dp = new int[length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {


            //dp[i - 2] 等于上上个房间得到最大金币数，dp[i - 1]是上个房间所能得到的最大金币数。

            //dp[i]是这个房间偷或者不偷得到的最大金币数=上上个房间最大金币数+这个房间金币数和上个房间所得到最大金币数做比较。

            //理解起来太绕了

            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[length - 1];
    }


    /**
     * 官方答案 和上面的思路一样的 说是用了滚动数组
     *
     * 其实就是把数组保存的值，换成了变量，理解起来更容易...。或许是越想越明白把
     *
     * 要想得到最大的金币数，一定是一个累计的过程，完美的情况下，全部都偷一遍。奈何连续偷两个房子时，会有警报呜呜呜。
     * 所以这个偷和不偷真是个问题！
     *
     * 能偷到最多也就是保证金额是由增量的，所以在每次决定偷与不偷时，保证得到金币是最多的。
     *
     * 当前的房间要不要偷，取决于偷与不偷那个增量更大。
     * 这样的话，需要在要不要偷当前这个房子时做一下思考。如果偷能得到多还是不偷得到的多。
     * 偷的话，能偷到的金币数等于上上个房间得到的最大金币数+这个房间的金币数。
     * 不偷的话，这个房间的金币就要舍弃的，能拿到的金币数等于上个房间得到最大金币数。
     *
     * 这里：
     * first代表上上个房子获取的最大金币数
     * second 代表上个房间获取的最大金币数
     *
     * @param nums
     * @return
     */
    public int rob3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }

        int first = nums[0], second = Math.max(nums[0], nums[1]);

        //first  代表第上上个房间获取的最大金币数
        //second 代表第上个房间获取的最大金币数
        //第三个房间的金币数为num,那么到第三个房间能获取最大金币数会是多少呢？
        //偷的话=first+num 不偷的话则为 second 能获取的最大金币数为 Math.max(first+num,second)
        //一次遍历完成后，将房子指针移动
        for (int i = 2; i < length; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }



    /**
     * 想了两个小时画图分析等作出的解题思路，中间一度陷入自闭状态。
     *
     * 思路：
     *
     *
     * 图标： @see <http://note.youdao.com/noteshare?id=35387fa8efc0e55a9591af783d759165>
     * 通过画表格分析得知，确定当前房子偷和不偷那个获得的金币数大，要取决于上个房间偷和不偷时的最大金币值。
     *
     * 仔细观察图标可知：
     * 不偷当前房子时可得到的最大金币数=A(偷上个房间所的大的最大金币数)>B(不偷上个房间可得的最大金币数)？A:B;
     * 偷当前房子时可得到的最大金币数=B(不偷上个房间可得的最大金币数)+这个房间的金币数。
     *
     * 根据么这个规律，进行遍历递进。
     *
     * 现在在看来，我这思路也挺绕哦，不过是正确的
     * on 2020/5/30 6:32PM
     *
     * @param nums
     * @return
     */
    public int rob2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        //偷当前房子的最大金币数
        int rob = 0;
        //不偷当前房子的最大金币数
        int skip = 0;
        //开始遍历挨个尝试偷窃
        for (int i = 0; i < nums.length; i++) {
            //取出上个房子没偷时的金币数
            int lastSkip=skip;
            //如果这个房子不偷窃，最大金币数取决于上个房子的偷与不偷的金币数
            skip = Math.max(skip, rob);
            //如果偷窃这个房子，则偷窃的金币数==这个房子金币数+上个房子没偷窃时的最大金币数。
            rob = lastSkip + nums[i];
        }
        //最大值=最后一个房间偷或不偷的最大值。
        return Math.max(skip, rob);

    }
}
