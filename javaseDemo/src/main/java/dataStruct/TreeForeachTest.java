package dataStruct;

import org.springframework.util.CollectionUtils;
import sun.reflect.generics.tree.Tree;

import java.util.*;

public class TreeForeachTest {
    private static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        public TreeNode(int data) {
            this.data = data;
        }
    }

    static TreeNode createTree(LinkedList<Integer> list) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        TreeNode treeNode = null;
        Integer data = list.removeFirst();
        if (data != null) {
            treeNode = new TreeNode(data);
            treeNode.left = createTree(list);
            treeNode.right = createTree(list);
        }
        return treeNode;
    }

    static void levelTrade(TreeNode treeNode) {
        if(treeNode!=null){
            System.out.println(treeNode.data);
        }
    }
    static void midTraval(TreeNode node) {
        if (node != null) {
            midTraval(node.left);
            System.out.print(node.data + "  ");
            midTraval(node.right);
        }
    }

    static void preTraval(TreeNode node) {
        if (node != null) {
            System.out.print(node.data + "  ");
            preTraval(node.left);
            preTraval(node.right);
        }
    }

    static void postTraval(TreeNode node) {
        if (node != null) {
            postTraval(node.left);
            postTraval(node.right);
            System.out.print(node.data + "  ");
        }
    }


    //非递归中序遍历
    static void midTravalNoRecursive(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = node;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            if (!stack.isEmpty()) {
                current = stack.pop();
                System.out.print(current.data + "  ");
                current = current.right;
            }
        }

    }

    //非递归前序遍历
    static void preTravalNoRecursive(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = node;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                System.out.print(current.data + "  ");
                stack.push(current);
                current = current.left;
            }
            if (!stack.isEmpty()) {
                current = stack.pop();
                current = current.right;
            }
        }

    }

    //非递归后序遍历
    static void postTravalNoRecursive(TreeNode node) {
        TreeNode lastVist = null;
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> temp = new Stack<>();
        TreeNode current = node;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            if (!stack.isEmpty()) {
                current = stack.pop();
                if (current.right == null || lastVist == current) {
                    System.out.print(current.data + "  ");
                    continue;
                } else {
                    //进入右节点并记录,弹出时要输出
                    lastVist = current;
                    stack.push(current);
                    current = current.right;
                }

            }
        }
    }

    //非递归后序遍历--最左法
    static void postTravalNoRecursive_mostLeft(TreeNode node) {
        TreeNode lastVist = null;
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> temp = new Stack<>();
        TreeNode current = node;
        TreeNode lastVisit = null;
        //先移动到最左边
        while (current  != null) {
            stack.push(current);
            current = current.left;
        }
        while (!stack.isEmpty()) {
            current = stack.pop();
            if(current.right == null || lastVisit == current.right){
                System.out.print(current.data + "  ");
                lastVisit = current;//9 10
            }
            else {
                stack.push(current);
                current  = current.right;
                while (current != null){
                    stack.push(current);
                    current = current.left;
                }

            }
        }

    }

    public static void main(String[] args) {
        LinkedList<Integer> integers = new LinkedList<>(Arrays.asList(3, 2, 9, null, null, 10, null, null, 8, null, 4));
        TreeNode tree = createTree(integers);
//        preTraval(tree);
//        System.out.println();
//        preTravalNoRecursive(tree);
//        System.out.println();
//        midTraval(tree);
//        System.out.println();
//        midTravalNoRecursive(tree);

        System.out.println("((((((");
        postTraval(tree);
        System.out.println();
        postTravalNoRecursive_mostLeft(tree);
//        new TreeMap<>().forEach();
    }
}
