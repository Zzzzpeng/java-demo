package algorithm;


import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class Node {

    static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }
    }

    static class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode parent = null;

        TreeLinkNode(int val) {
            this.val = val;
        }
    }

    /**
     * 查找二叉树的右驱节点
     * @param pNode
     * @return
     */
    public TreeLinkNode GetNext(TreeLinkNode pNode)
        {
            if (pNode == null) {
                return null;
            }
            if (pNode.right == null) {
                while (pNode.parent != null && pNode == pNode.parent.right){
                    pNode = pNode.parent;
                }
                return pNode.parent;
            }else {
                TreeLinkNode res = pNode.right;
                while (res.left != null){
                    res = res.left;
                }
                return res;
            }
        }

    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        ListNode head1 = pHead1;
        ListNode head2 = pHead2;
        while (head1 != head2) {
            head1 = head1 != null ? head1.next : pHead2;
            head2 = head2 != null ? head2.next : pHead1;
        }
        return head1;
    }


    //给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
    //为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
    //说明：不允许修改给定的链表。
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/linked-list-cycle-ii
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public ListNode EntryNodeOfLoop(ListNode pHead)
    {
        ListNode slower = pHead,faster =  pHead;
        while (faster != null){
            slower = slower.next;
            faster = faster.next;
            faster = faster != null ? faster.next : null;
            if(faster == slower)
                break;
        }
        if (faster == null) {
            return faster;
        }
        slower = pHead;
        while (slower != faster){
            slower = slower.next;
            faster = faster.next;
        }
        return faster;
    }

    public static int GetNumberOfK(int[] array, int k) {
        if (array == null || array.length == 0)
            return 0;
        int count = 0;
        int left = 0;
        int right = array.length - 1;
        int mid = (left + right) / 2;
        while (left <= right) {
            if (array[mid] == k) {
                for (int i = mid; i < array.length && array[i] == k; i++) {
                    count++;
                }
                for (int i = mid - 1; i >= 0 && array[i] == k; i--) {
                    count++;
                }
                return count;
            } else if (k < array[mid]) {
                right = mid - 1;
            } else if (k > array[mid]) {
                left = mid + 1;
            }
            mid = (left + right) / 2;
        }
        return count;
    }


    public static int midSearch(int[] nums, int k) {
        int left = 0, right = nums.length - 1, mid = (left + right) / 2;
        while (left <= right) {
            if (k > nums[mid]) {
                left = mid + 1;
            } else if (k < nums[mid]) {
                right = mid - 1;
            } else {
                return mid;
            }
            mid = (right + left) / 2;
        }
        return -1;
    }


    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return 1;
        else if (root.left == null) {
            return 1 + maxDepth(root.right);
        } else if (root.right == null) {
            return 1 + maxDepth(root.left);
        } else {
            return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
        }
    }

    public static void main(String[] args) {
//        System.out.println(GetNumberOfK(new int[]{1},1));
        System.out.println(midSearch(new int[]{1, 4, 5, 7, 8, 8, 9}, 9));
    }




//    //重构二叉树
//    //输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
//    // 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
//    // 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
//    HashMap<Integer, Integer> dic = new HashMap<>();
//    int[] preOrders;
//
//    public TreeNode buildTree(int[] preorder, int[] inorder) {
//        preOrders = preorder;
//        for (int i = 0; i < inorder.length; i++)
//            dic.put(inorder[i], i);
//        return recur(0, 0, inorder.length - 1);
//    }
//
//    /**
//     *
//     * @param pre_root
//     * @param in_left
//     * @param in_right
//     * @return
//     */
//    TreeNode recur(int pre_root, int in_left, int in_right) {
//        if (in_left > in_right) return null;
//        TreeNode root = new TreeNode(preOrders[pre_root]);
//        int i = dic.get(preOrders[pre_root]);
//        root.left = recur(pre_root + 1, in_left, i - 1);
//        root.right = recur(pre_root + i - in_left + 1, i + 1, in_right);
//        return root;
//    }


    Map<Integer, Integer> indexMap = new HashMap<>();
    int[] preorder;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return build(0, 0, inorder.length - 1);
    }

    TreeNode build(int rootPreIndex, int inLeftIndex, int inRightIndex) {
        if (inLeftIndex > inRightIndex)
            return null;
        TreeNode node = new TreeNode(preorder[rootPreIndex]);
        int midIndex = indexMap.get(preorder[rootPreIndex]);
        node.left = build(rootPreIndex + 1, inLeftIndex, midIndex - 1);
        node.right = build(rootPreIndex + midIndex - inLeftIndex + 1, midIndex + 1, inRightIndex);
        return node;
    }


//    public TreeNode buildTree(int[] preorder, int[] inorder) {
//        Map<Integer, Integer> map = new HashMap<>();
//        for (int i = 0; i < inorder.length; i++) {
//            map.put(inorder[i], i);
//        }
//        return build(0, 0, inorder.length - 1,preorder,map);
//    }
//
//    TreeNode build(int rootPreIndex, int inLeftIndex, int inRightIndex,int[] preorder,Map<Integer, Integer> indexMap) {
//        if(inLeftIndex > inRightIndex)
//            return null;
//        TreeNode node = new TreeNode(preorder[rootPreIndex]);
//        int midIndex = indexMap.get(preorder[rootPreIndex]);
//        node.left = build(rootPreIndex + 1, inLeftIndex, midIndex - 1, preorder, indexMap);
//        node.right = build(rootPreIndex + midIndex - inLeftIndex + 1, midIndex + 1, inRightIndex, preorder, indexMap);
//        return node;
//    }


    public TreeNode reConstructBinaryTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0)
            return null;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return build(0, 0, inorder.length - 1, preorder, map);
    }

    TreeNode build(int rootPreIndex, int inLeftIndex, int inRightIndex, int[] preorder, Map<Integer, Integer> indexMap) {
        if (inLeftIndex > inRightIndex)
            return null;
        TreeNode node = new TreeNode(preorder[rootPreIndex]);
        int midIndex = indexMap.get(preorder[rootPreIndex]);
        node.left = build(rootPreIndex + 1, inLeftIndex, midIndex - 1, preorder, indexMap);
        node.right = build(rootPreIndex + midIndex - inLeftIndex + 1, midIndex + 1, inRightIndex, preorder, indexMap);
        return node;
    }

    /*输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）*/
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        return root2 == null? false : hasSubtree(root1, root2);
    }
    public boolean hasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 != null) {
            if (root1.val == root2.val && isEquals(root1, root2)) {
                return true;
            }
            if (HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2)) {
                return true;
            }
        }
        return false;
    }
    public boolean isEquals(TreeNode root1, TreeNode root2) {
        if (root2 == null || root1 == root2) return true;
        if (root1 == null) return false;
        return !(root1.val != root2.val || !isEquals(root1.left, root2.left) || !isEquals(root1.right, root2.right));
    }


    /*给定一个二叉树，判断它是否是高度平衡的二叉树。
    本题中，一棵高度平衡二叉树定义为：
    一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。*/
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        //子树有不平衡的直接返回false
        if (!isBalanced(root.left) || !isBalanced(root.right)) return false;
        //子树都平衡,则比较高度
        return Math.abs(getHeight(root.left, 0) - getHeight(root.right, 0)) <= 1;
    }

    public int getHeight(TreeNode node, int h) {
        if (node == null) return h;
        return Math.max(getHeight(node.left, h + 1), getHeight(node.right, h + 1));
    }

    //验证是否二叉搜索树
    private Integer temp;
    private boolean isValidBST(TreeNode node){
        if (node != null) {
            if(!isValidBST(node.left))
                return false;
            if(temp != null && temp >= node.val){
               return false;
            }else{
                temp = node.val;
            }
            return isValidBST(node.right);
        }
        return true;
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(0);
//        root.left = new TreeNode(-1);
        System.out.println(isValidBST(root));
    }
}
