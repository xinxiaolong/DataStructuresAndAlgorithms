package xiaolong.arithmetic.fun_lib;

/**
 * Created by xiaolong on 2020-04-26.
 * email：xinxiaolong123@foxmail.com
 * <p>
 * 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。
 * 例如输入字符串"I am a student. "，则输出"student. a am I"。
 * <p>
 * 链接：https://leetcode-cn.com/problems/fan-zhuan-dan-ci-shun-xu-lcof
 * <p>
 * 无空格字符构成一个单词。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * <p>
 * <p>
 * 参考网站：
 * https://zhuanlan.zhihu.com/p/22298080             了解String ,StringBuffer,StringBuilder区别。
 * https://juejin.im/post/5a5d5c66f265da3e261bf46c   了解String为何是不可变字符串，优缺点。
 * <p>
 * https://www.jianshu.com/p/aa4242253645            String在编译其和for循环都是使用StringBuilder实现的。
 */
public class WordFlip {

    public static void main(String[] agr) {

        String sentence = "I am a student. ";

        WordFlip wordFlip = new WordFlip();

        String flip = wordFlip.flip3(sentence);

        System.out.println(flip);

    }

    /**
     * 这个是初步答案
     * <p>
     * 16 ms   在所有 Java 提交中击败了 11.20%的用户
     * 40.8 MB
     * <p>
     * 运行时间还需要提高！
     * <p>
     * 思考优化：
     * 既然对字符串进行操作：那么就要认识 String、StringBuffer、StringBuilder区别
     * 这里就给一个网站：https://zhuanlan.zhihu.com/p/22298080
     * 了解了StringBuilder是最高效的，那么用StringBuilder再实现。
     *
     * @param sentence
     * @return
     */
    public String flip(String sentence) {
        String flip = "";
        String[] words = sentence.split(" ");
        for (int i = words.length - 1; i >= 0; i--) {
            String word = words[i];
            if (word.length() == 0) {
                continue;
            }
            flip = flip + words[i] + (i == 0 ? "" : " ");
        }

        StringBuffer b = new StringBuffer();

        return flip.trim();
    }


    /**
     * 这个使用StringBuilder进行优化的
     * <p>
     * 1 ms, 在所有 Java 提交中击败了 100.00% 的用户
     *
     * @param sentence
     * @return
     */
    public String flip1(String sentence) {

        String[] words = sentence.split(" ");
        StringBuilder flip = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            String word = words[i];
            if (word.length() > 0) {
                flip.append(words[i]);
                flip.append(" ");
            }
        }

        return flip.toString().trim();
    }

    /**
     * 2 ms, 在所有 Java 提交中击败了 100.00% 的用户
     * 为了看出与StringBuilder的差别。写了用StringBuffer实现的
     * 发现运行时间为2ms
     * 做同步的时候，拿锁和释放锁确实带来开销
     */
    public String flip2(String sentence) {
        String[] words = sentence.split(" ");
        StringBuffer flip = new StringBuffer();
        for (int i = words.length - 1; i >= 0; i--) {
            String word = words[i];
            if (word.length() > 0) {
                flip.append(words[i]);
                flip.append(" ");
            }
        }

        return flip.toString().trim();
    }


    /**
     * 执行用时 : 5 ms, 在所有 Java 提交中击败了 44.58% 的用户
     *
     * 自己用双指针实现的，但是速度有些慢
     * 从后往前，根据空格出现的交替，来判断一个完整的单词。
     */
    public String flip3(String sb) {

        String[] words = new String[sb.length()];
        sb = sb.trim();

        if(sb.length()==0){
            return "";
        }

        //一个单词的终止index(注意：因为使用的是subString所以这个index是实际的最后一个单词的下标+1)
        int endIndex = sb.length();
        //一个单词的开始index
        int startIndex = sb.length();

        int index = 0;
        for (int i = sb.length() - 1; i >= 0; i--) {

            //如果遇到空格
            if (isSpaceChar(sb.charAt(i))) {

                //当再次遇到时，判断startIndex < endIndex就知道出现了一个完整的单词。
                if (startIndex < endIndex) {
                    words[index] = sb.substring(startIndex, endIndex);
                    index++;
                }
                //更新endIndex;
                endIndex = i;
            } else {
                //如果没遇到空格，则更新startIndex,使其向游动。
                startIndex = i;
            }

            //当为0时需要需要特殊处理了。
            if (i == 0) {
                words[index] = sb.substring(startIndex, endIndex);
            }
        }

        StringBuilder stringBuilder=new StringBuilder();

        for (int i = 0; i <= index; i++) {
            stringBuilder.append(words[i]).append(" ");
        }
        return stringBuilder.toString().trim();
    }

    public boolean isSpaceChar(char s) {
        return Character.isSpaceChar(s);
    }


}
