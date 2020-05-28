package xiaolong.classicarithmetic_lib.structure.Stack;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Stack;

/**
 * Created by xiaolong on 2020-05-17 14:09
 * email：xinxiaolong123@foxmail.com
 *
 * 自己实现的 栈的操作
 *
 *
 */
public class StackArray<T> {

    private T[] mArray;//用数组实现

    private int top; //栈顶指针

    private static final int DEFAULT_SIZE = 6;

    private Class cls;

    public StackArray(Class c) {
        this.cls=c;
        mArray = (T[]) Array.newInstance(c, DEFAULT_SIZE);
        top = -1; //栈空的时候
    }

    //压栈
    public void push(T element) {
        if (top == mArray.length - 1) {
            grow();
        }
        mArray[++top] = element;
    }

    public void grow() {
        T[] arr = (T[]) Array.newInstance(cls, mArray.length * 2);
        System.arraycopy(mArray, 0, arr, 0, mArray.length);
        mArray = arr;
    }

    public T peek() {

        if (isEmpty()) {
            return null;
        }
        return mArray[top];
    }


    public T pop() {

        if (isEmpty()) {
            return null;
        }
        T element = mArray[top];

        mArray[top] = null;
        top--;

        return element;
    }

    public int size(){
        return top+1;
    }

    public boolean isEmpty() {
        return size()==0;
    }

    public String toString(){
        StringBuilder builder=new StringBuilder();

        for (int i=0;i<=top;i++){
            builder.append(mArray[i]).append(",");
        }
        return builder.toString();
    }

}
