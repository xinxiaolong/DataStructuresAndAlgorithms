package xiaolong.arithmetic.fun_lib;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by xiaolong on 2020-05-07 07:22
 * email：xinxiaolong123@foxmail.com
 * <p>
 * <p>
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * <p>
 * <p>
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 */
public class LongestSub {


    public static void main(String[] agr) {

        String str = "pwihwpkewabcdefdwfghsjdueiioeiowqeqwp";

        LongestSub longestSub = new LongestSub();

        int num = longestSub.longestSubStr(str);

        System.out.print(num);
    }


    /**
     * 看了网上的思路和我这个一样的，被称为：滑动窗口。
     * 执行效率：125 ms
     * 少了Sring.valueOf()会快一些 ：101ms
     *
     * @param str
     * @return
     */
    public int longestSubStr(String str) {

        //使用hashMap保存当前字符串和下标
        HashMap<Character, Integer> map = new HashMap<>();

        int len = 0;
        int maxLen = 0;

        int count = 0;

        for (int i = 0; i < str.length(); i++) {

            //取出当前元素
            char c = str.charAt(i);

            //当前元素出现过
            if (map.containsKey(c)) {

                //回溯下标
                i = map.get(c);

                //清除之前缓存的数据，开始新的单词缓存
                map.clear();

                //获取最大长度
                maxLen = Math.max(maxLen, len);

                //将当前有效单词数大小置为0
                len = 0;

            } else {

                //当没有出现重复则长度加1
                len++;
                //加入缓存
                map.put(c, i);
            }


            count++;
        }

        System.out.println("执行次数" + count);

        return Math.max(len, maxLen);

    }


    /**
     * 这个是网上答案，使用HashSet进行滑动
     * <p>
     * 而且执行效率很高， 13 ms
     * <p>
     * 为何呢？
     *
     * @param s
     * @return
     */
    public int longestSubStr1(String s) {

        // 哈希集合，记录每个字符是否出现过
        Set<String> occ = new HashSet<>();

        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;


        for (int i = 0; i < n; ++i) {

            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                String key = String.valueOf(s.charAt(i - 1));
                occ.remove(key);
            }


            while (rk + 1 < n && !occ.contains(String.valueOf(s.charAt(rk + 1)))) {
                // 不断地移动右指针
                String key = String.valueOf(s.charAt(rk + 1));

                occ.add(key);
                ++rk;

            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }

        return ans;
    }

}
