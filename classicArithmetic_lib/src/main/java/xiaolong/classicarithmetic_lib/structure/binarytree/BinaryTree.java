package xiaolong.classicarithmetic_lib.structure.binarytree;

/**
 * Created by xiaolong on 2020-05-06 22:12
 * email：xinxiaolong123@foxmail.com
 */
public class BinaryTree implements Tree {

    private Node root;

    @Override
    public Node find(int key) {

        Node current = root;
        while (current != null) {
            if (current.data > key) {
                // 当前值比查找值大，搜索左子树
                current = current.leftChild;
            } else if (current.data < key) {
                // 当前值比查找值小，搜索右子树
                current = current.rightChild;
            } else {
                return current;
            }
        }
        return null;// 遍历完整个树没找到，返回null
    }

    @Override
    public boolean insert(int data) {
        return false;
    }

    @Override
    public void infixOrder(Node current) {

    }

    @Override
    public void preOrder(Node current) {

    }

    @Override
    public void postOrder(Node current) {

    }

    @Override
    public Node findMax() {
        return null;
    }

    @Override
    public Node findMin() {
        return null;
    }

    @Override
    public boolean delete(int key) {
        return false;
    }
}


