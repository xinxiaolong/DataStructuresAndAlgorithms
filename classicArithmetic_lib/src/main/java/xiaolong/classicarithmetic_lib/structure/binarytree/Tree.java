package xiaolong.classicarithmetic_lib.structure.binarytree;

/**
 * Created by xiaolong on 2020-05-06 22:30
 * email：xinxiaolong123@foxmail.com
 */
public interface Tree {

    // 查找节点
     Node find(int key);

    // 插入新节点
     boolean insert(int data);

    // 中序遍历
     void infixOrder(Node current);

    // 前序遍历
     void preOrder(Node current);

    // 后序遍历
     void postOrder(Node current);

    // 查找最大值
     Node findMax();

    // 查找最小值
     Node findMin();

    // 删除节点
     boolean delete(int key);
        
}
