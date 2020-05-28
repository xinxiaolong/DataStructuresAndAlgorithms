package xiaolong.arithmetic.fun_lib;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaolong on 2020-04-21.
 * email：xinxiaolong123@foxmail.com
 * <p>
 * 题目：古典问题：有一对兔子，从出生后第3个月起每个月都生一对兔子，小兔子长到第3个月后每个月
 * 又生一对兔子，假如兔子都不死，问每个月的兔子总数为多少？
 * 1,1,2,3,
 *
 *
 * 这道题把自己难住了，也对自己在算法方面有一个思考。就是需要润物细无声式的学习，反反复复的进行理解思考，才能吃透写。
 * 下面两种算法都是参考网上的。理解也用了不少的时间。然后自己思考还是没有写出一个方案
 * 对于递归和循环的代码块理解还是欠缺，到此为止。过段时间再来好好理解，需要反复的理解
 * 琢磨，思考。才能做到思路敏捷清晰。
 */
public class RabbitBorn {

    public static void main(String[] agr) {

        RabbitBorn born = new RabbitBorn();

        int num = born.born(1);
        System.out.println("一共有" + num + "对兔子");
        num = born.born(2);
        System.out.println("一共有" + num + "对兔子");
        num = born.born(3);
        System.out.println("一共有" + num + "对兔子");
        num = born.born(4);
        System.out.println("一共有" + num + "对兔子");
        num = born.born(6);
        System.out.println("一共有" + num + "对兔子");

        num = born.born2(6);
        System.out.println("一共有" + num + "对兔子");
    }


    /**
     * 开始以为是道比较简单的题。没想到陷入其中，只有画图表出来慢慢理解开来了。
     * 核心在于对于后面新出生的兔子再生兔子进行递归计算。
     * <p>
     * 思路：新出生的兔子要第三个月才生兔子，所以第一个月和第二个月都保持一对。
     * 从第三个月开始生。到第三个月时：有两个兔子了。而这里又分了两部分。
     * 第一部分是：第三月之后，第一只兔子没个月都会生一对。
     * 第二部分是：第三月之后，新出生的兔子每隔两个月会生一对。
     * 所以以这两个节点为初始节点进行递归计算。
     *
     * @param monthNum
     * @return
     */
    public int born(int monthNum) {

        if (monthNum <= 0) {
            return 0;
        }

        if (monthNum == 1 || monthNum == 2) {
            return 1;
        }

        //当达到三个月或以上。第一只兔子会每个月生一只+第二只兔子每三个月生一只。
        return born(monthNum - 1) + born(monthNum - 2);
    }


    /**
     * 这个是网上的一个解决方法，思路很巧妙的。
     *
     * @param monthNum
     * @return
     */
    public int born2(int monthNum) {
        List list = new ArrayList();
        list.add(new LittleRabbit("A"));
        for (int k = 1; k <= monthNum; k++) {
            for (int j = 0; j < list.size(); j++) {
                LittleRabbit rabbit = (LittleRabbit) list.get(j);
                int age = rabbit.getAge();
                if (age >= 3) {
                    list.add(new LittleRabbit(rabbit.getName() + "1"));
                }
                age++;
                rabbit.setAge(age);
            }
        }
        return list.size();
    }


    class LittleRabbit {
        private int age = 1;
        private String name;

        public LittleRabbit(String name) {
            this.name = name;
        }

        public void growUp() {
            this.age++;
        }

        public int getAge() {
            return age;
        }

        public String getName() {
            return name;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}
