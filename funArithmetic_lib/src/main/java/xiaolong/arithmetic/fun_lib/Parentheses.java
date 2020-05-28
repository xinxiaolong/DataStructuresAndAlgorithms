package xiaolong.arithmetic.fun_lib;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by xiaolong on 2020-05-11 21:26
 * email：xinxiaolong123@foxmail.com
 *
 *
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 *
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 *
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 *
 * 输入: "()[]{}"
 * 输出: true
 *
 * 输入: "([)]"
 * 输出: false
 *
 * 输入: "{[]}"
 * 输出: true
 *
 * 知识点： Stack 的使用和认识：
 * https://www.jianshu.com/p/01192de8c21a
 *
 * 又学习了下包装数据类型和基础数据类型
 *
 *
 */
public class Parentheses {

    public static void main(String[] arg) {

        String s = "([{[}])";

        Parentheses parentheses = new Parentheses();

        System.out.print(parentheses.isValid(s));
    }


    /**
     * 根据官方提供的思路 写的
     *
     * 另外 直接判断length为奇数则判断为不合法。
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {

        HashMap<Character, Character> map = new HashMap();


        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        map.put('?', '?');

        char[] chars = s.toCharArray();

        Stack<Character> stack = new Stack<>();

        stack.push('?');

        for (int i = 0; i < chars.length; i++) {

            char c=chars[i];

            //注意 当stack为空时 peek会抛异常
            if(map.get(stack.peek())!=null&&map.get(stack.peek())==c){
                stack.pop();
            }else {
                stack.push(chars[i]);
            }
        }
        return stack.size()==1;
    }


    /**
     * 官方解答
     *
     * 比我的优秀之处在于，对于不合法性做了提前预判。
     * 拿出第一个反括号 判断前一个括号是不是与之对应。不对应则直接不合法，后面无需判断了。
     * @param s
     * @return
     */
    public boolean isValid2(String s){


        HashMap<Character, Character> map = new HashMap();

        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');


        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // If the current character is a closing bracket.
            if (map.containsKey(c)) {

                // Get the top element of the stack. If the stack is empty, set a dummy value of '#'
                char topElement = stack.empty() ? '#' : stack.pop();

                //如果遇到第一个) } ] 发现前面对应的不是( { [ 则直接判定不合法。
                if (topElement != map.get(c)) {
                    return false;
                }
            } else {
                // If it was an opening bracket, push to the stack.
                stack.push(c);
            }
        }

        // If the stack still contains elements, then it is an invalid expression.
        return stack.isEmpty();
    }


    public boolean isValid3(String s) {
        char[] charArray = new char[s.length() + 1];

        int p = 1;

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                charArray[p++] = c;
            } else {
                p--;
                if (c == ')' && charArray[p] != '(') {
                    return false;
                }
                if (c == '}' && charArray[p] != '{') {
                    return false;
                }
                if (c == ']' && charArray[p] != '[') {
                    return false;
                }
            }
        }
        return p == 1; // 如果左括号还有剩余 括号没有一一对应，属于无效情况
    }

}
