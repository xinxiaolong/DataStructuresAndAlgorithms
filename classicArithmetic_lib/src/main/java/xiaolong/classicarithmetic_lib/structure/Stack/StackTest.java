package xiaolong.classicarithmetic_lib.structure.Stack;

import java.util.Stack;

/**
 * Created by xiaolong on 2020-05-17 13:48
 * email：xinxiaolong123@foxmail.com
 */
public class StackTest {


    public static void main(String[] agr){


        Stack stack=new Stack();

        stack.add(1);
        stack.add(2);
        stack.add(3);
        stack.add(4);
        stack.push(5);
        stack.push(6);

        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }

        StackArray<String> stackArray=new StackArray<>(String.class);

        stackArray.push("1");
        stackArray.push("2");
        stackArray.push("3");
        stackArray.push("4");

        System.out.println("size="+stackArray.size());
        System.out.println("pop="+stackArray.pop());
        System.out.println("peek="+stackArray.peek());
        System.out.println(stackArray.toString());

        for (int i=0;i<80;i++){
            stackArray.push(i+"");
        }

        System.out.println(stackArray.toString());

        while (!stackArray.isEmpty()){
          String element=  stackArray.pop();

          System.out.println(element);
        }

        System.out.println(stackArray.isEmpty());


    }
}
