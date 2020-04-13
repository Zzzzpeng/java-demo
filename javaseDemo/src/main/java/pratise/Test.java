package pratise;

import java.util.*;

public class Test {
    static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node(int val) {
            this.val = val;
        }
    }
    public Node treeToDoublyList(Node node) {
        ArrayList<Node> que = new ArrayList();
        doConvert(node,que);
        // Node pre = que.poll();
        // Node leftest = pre;
        // Node cur;
        // while(!que.isEmpty()){
        //     cur = que.poll();
        //     pre.right = cur;
        //     cur.left = pre;
        //     pre = cur;
        // }
        for (int i = 0; i < que.size(); i++) {
            if(i + 1 < que.size())
                 que.get(i).right = que.get(i + 1);
            if(i - 1 >= 0)
                 que.get(i).left = que.get(i -1);
        }
        return que.get(0);
    }
    public void doConvert(Node node,List<Node> que) {
        if(node != null){
            doConvert(node.left,que);
            que.add(node);
            doConvert(node.right,que);
        }
    }
    public static int MoreThanHalfNum_Solution(int [] array) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : array) {
            Integer integer = map.get(i);
            integer = integer == null ? 0 : integer;
            map.put(i,integer + 1);
            if(integer + 1 > array.length)
                return i;
        }
       return 0;
    }

    public static void main(String[] args) {
        System.out.println(MoreThanHalfNum_Solution(new int[]{1, 2, 3, 2, 2, 2, 5, 4, 2}));
    }
}
