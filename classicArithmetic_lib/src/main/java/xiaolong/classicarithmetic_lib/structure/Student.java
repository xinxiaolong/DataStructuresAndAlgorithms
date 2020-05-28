package xiaolong.classicarithmetic_lib.structure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaolong on 2020-04-27.
 * email：xinxiaolong123@foxmail.com
 *
 * 这是一个链表
 */
public class Student {


    int age;

    String name;

    //建表指针下一个学生的
    Student next;


    public List<Student> put(List<Student> oldStudents) {

        //用100个格子保存学生数据
        Student[] list = new Student[100];
        //新的无重复学生列表
        List<Student> newStudents = new ArrayList<>();

        for (int i = 0; i < oldStudents.size(); i++) {

            //遍历取出一个学生
            Student student = oldStudents.get(i);

            int index = getHashCode() % 99;

            //取出这个格子桶里的数据
            Student bucketStu = list[index];

            if (bucketStu == null) {

                //如果当前的格子里没有学生，则放入新列表里
                newStudents.add(student);

                //将学生信息放入对应的格子中
                list[index] = student;

            } else if (getStudent(bucketStu, student) == null) {
                //说明链表里也没找到这个学生，则存入新列表里
                newStudents.add(student);
            } else {
                //如果已经放过了说明重复，也无需将老的数据做替换。
            }

        }

        return newStudents;
    }


    /**
     * 从当前节点查找目标对象。
     * @param current 当前节点对象
     * @param target  目标对象
     * @return  找到目标对象返回目标对象;找不到则将目标对象插入链表最后，返回null
     */
    public Student getStudent(Student current, Student target) {

        //首先判断当前的学生是不是目标数据
        if(current.equals(target)){
            return target;
        }

        //开始遍历链表，判断存不存在目标数据
        while (current.next != null) {

            //如果存在则直接返回
            if (current.equals(target)) {
                return target;
            }
            //遍历一下个
            current = current.next;
        }

        //如果不存在则将目标对象插入链表最后
        current.next = target;

        return null;
    }

    public int getHashCode() {
        return name.hashCode();
    }
}
