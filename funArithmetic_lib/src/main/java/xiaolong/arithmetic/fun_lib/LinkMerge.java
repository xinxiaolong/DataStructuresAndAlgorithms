package xiaolong.arithmetic.fun_lib;

/**
 * Created by xinxiaolong on 2020/5/19.
 * email：xinxiaolong123@foxmail.com
 * <p>
 * 将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 * <p>
 * 示例：
 * <p>
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 * <p>
 * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
 */
public class LinkMerge {


    public static void main(String[] arg) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(4);

        node1.next = node2;
        node2.next = node3;

        ListNode node4 = new ListNode(1);
        ListNode node5 = new ListNode(3);
        ListNode node6 = new ListNode(4);

        node4.next = node5;
        node5.next = node6;

        LinkMerge link = new LinkMerge();

        ListNode listNode = link.mergeTwoLists(node1, node4);

        listNode.next.val++;
    }


    /**
     * 其实还是一个双指针的思路。这里的指针直接成了next的复制了
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode firstNode = null;

        ListNode currentNode = null;

        while (l1 != null) {

            if (l2 == null || l1.val <= l2.val) {

                if (firstNode == null) {
                    firstNode = new ListNode(l1.val);
                    currentNode = firstNode;
                } else {
                    currentNode.next = new ListNode(l1.val);
                    currentNode = currentNode.next;
                }

                l1 = l1.next;

            } else {
                if (firstNode == null) {
                    firstNode = new ListNode(l2.val);
                    currentNode = firstNode;
                } else {
                    currentNode.next = new ListNode(l2.val);
                    currentNode = currentNode.next;
                }

                l2 = l2.next;
            }
        }

        while (l2 != null) {
            if (firstNode == null) {
                firstNode = new ListNode(l2.val);
                currentNode = firstNode;
            } else {
                currentNode.next = new ListNode(l2.val);
                currentNode = currentNode.next;
            }
            l2 = l2.next;
        }
        return firstNode;
    }

    static class ListNode {

        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 官方答案，十分巧妙和清晰
     *
     */
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode preHead = new ListNode(-1);

        ListNode prev = preHead;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        prev.next=l1==null?l2:l1;

        return preHead.next;
    }
}
