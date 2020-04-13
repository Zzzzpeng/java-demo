package exam;

import javax.swing.tree.TreeNode;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;


public class Solution {

static class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}

    public RandomListNode Clone(RandomListNode pHead)
    {
        if(pHead == null)
            return null;
        java.util.Map<RandomListNode,RandomListNode> map = new HashMap();
        RandomListNode head = new RandomListNode(pHead.label);
        RandomListNode pre = head;
        RandomListNode pCur = pHead.next;
        map.put(pHead,head);
        //处理next的关系
        while(pCur != null){
            RandomListNode node = new RandomListNode(pCur.label);
            map.put(pCur,node);
            pre.next = node;
            pre = node;
            pCur = pCur.next;
        }
        pCur = pHead;
        RandomListNode cur = head;
        while(pCur != null){
            cur.random = map.get(pCur);
            pCur = pCur.next;
            cur = cur.next;
        }
        return head;
    }


//    static class Node {
//        int val;
//        Node next;
//        Node random;
//
//        public Node(int val) {
//            this.val = val;
//            this.next = null;
//            this.random = null;
//        }
//    public Node copyRandomList(Node pHead) {
//        if(pHead == null)
//            return null;
//        Map<Node,Node> map = new HashMap();
//        Node head = new Node(pHead.val);
//        Node pre = head;
//        Node pCur = pHead.next;
//        map.put(pHead,head);
//        //处理next的关系
//        while(pCur != null){
//            Node node = new Node(pCur.val);
//            map.put(pCur,node);
//            pre.next = node;
//            pre = node;
//            pCur = pCur.next;
//        }
//        pCur = pHead;
//        Node cur = head;
//        while(pCur != null){
//            cur.random = map.get(pCur.random);
//            pCur = pCur.next;
//            cur = cur.next;
//        }
//        return head;
//    }


}