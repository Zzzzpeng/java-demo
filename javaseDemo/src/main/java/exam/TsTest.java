package exam;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TsTest {

    public static void main(String[] args) {
//        Node n1 = new Node(1);
//        n1.next = new Node(3);
//
//        Node n2 = new Node(2);
//        n2.next = new Node(5);
//        n2.next.next = new Node(6);
//        Node merge = Merge(n1, n2);
//        System.out.println(merge);
        Object o1 = new Object();
        Object o2 = new Object();
        new Thread(()->{
            synchronized (o1){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2){
                    System.out.println("获取o2");
                }
            }
        }).start();

        new Thread(()->{
            synchronized (o2){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1){
                    System.out.println("获取o2");
                }
            }
        }).start();
    }
    static class Node{
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    public static Node Merge(Node n1, Node n2){
        if (n1 == null || n2 == null) {
            return n1 != null ? n1 : n2;
        }
        Node head = n1.val <= n2.val ? n1 : n2;
        Node node;
        if(n1.val <= n2.val){
            node = n1;
            n1 = n1.next;
        }else{
            node = n2;
            n2 = n2.next;
        }
        while(n1 != null && n2 != null){
            if (n1.val <= n2.val) {
                node.next = n1;
                n1 = n1.next;
            } else {
                node.next = n2;
                n2 = n2.next;
            }
            node = node.next;
        }
        node.next = n1 != null ? n1 : n2;
        return head;
    }

    public Node Merge1(Node n1, Node n2){
        if (n1 == null || n2 == null) {
            return n1 != null ? n1 : n2;
        }

        Node node = n1.val <= n2.val ? n1 : n2;
        Node head = node;
        while(n1 != null || n2 != null){
            if (n1 != null && n2 != null) {
                if (n1.val <= n2.val) {
                    node.next = n1;
                    n1 = n1.next;
                } else {
                    node.next = n2;
                    n2 = n2.next;
                }
            } else if (n1 != null) {
                node.next = n1;
                n1 = n1.next;
            }else{
                node.next = n2;
                n2 = n2.next;
            }
            node = node.next;
        }
        return head;
    }

}
