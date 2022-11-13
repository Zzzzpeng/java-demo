package algorithm;


import org.junit.Test;
import sun.net.www.http.HttpClient;

import java.math.BigDecimal;
import java.sql.DriverManager;
import java.util.*;

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
     * 描述
     * 给定一个链表，删除链表的倒数第 n 个节点并返回链表的头指针
     * 例如，
     * 给出的链表为: 1\to 2\to 3\to 4\to 51→2→3→4→5, n= 2n=2.
     * 删除了链表的倒数第 nn 个节点之后,链表变为1\to 2\to 3\to 51→2→3→5.
     * <p>
     * 数据范围： 链表长度 0\le n \le 10000≤n≤1000，链表中任意节点的值满足 0 \le val \le 1000≤val≤100
     * 要求：空间复杂度 O(1)O(1)，时间复杂度 O(n)O(n)
     * 备注：
     * 题目保证 nn 一定是有效的
     *
     * @param head ListNode类
     * @param n    int整型
     * @return ListNode类
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n < 1) {
            return head;
        }
        int len = 0;
        ListNode ct = head;
        while (ct != null) {
            len++;
            ct = ct.next;
        }
        ListNode result;
        if (n == len) {
            result = head.next;
            head.next = null;
            return result;
        }
        ct = head;
        for (int i = 0; i < len - n - 1; i++) {
            ct = ct.next;
        }
        if (n == 1) {
            ct.next = null;
        }else{
            ct.next = ct.next.next;
        }
        return head;
    }

    @Test
    public void testRemoveNthFromEnd() {
        ListNode head = buildListNode();
        ListNode result = removeNthFromEnd(head, 2);
        System.out.println(result);

        head = buildListNode();
        result = removeNthFromEnd(head, 1);
        System.out.println(result);

        head = buildListNode();
        result = removeNthFromEnd(head, 5);
        System.out.println(result);

    }


    /**
     * 描述
     * 假设链表中每一个节点的值都在 0 - 9 之间，那么链表整体就可以代表一个整数。
     * 给定两个这种链表，请生成代表两个整数相加值的结果链表。
     * 数据范围：0 \le n,m \le 10000000≤n,m≤1000000，链表任意值 0 \le val \le 90≤val≤9
     * 要求：空间复杂度 O(n)O(n)，时间复杂度 O(n)O(n)
     *
     * 例如：链表 1 为 9->3->7，链表 2 为 6->3，最后生成新的结果链表为 1->0->0->0。
     * @return
     */
    public ListNode addInList (ListNode head1, ListNode head2) {
        // write code here
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        ListNode ct1 = head1;
        ListNode ct2 = head2;
        while (ct1 != null) {
            sb1.append(ct1.val);
            ct1 = ct1.next;
        }
        while (ct2 != null) {
            sb2.append(ct2.val);
            ct2 = ct2.next;
        }
        int len = sb1.length() > sb2.length() ? sb1.length() : sb2.length();
        StringBuilder sumSb = new StringBuilder();

        int preOver = 0;
        for (int i = 0; i < len; i++) {
            int iSum = 0;
            if (i <= sb1.length() - 1) {
                iSum += sb1.charAt(sb1.length() -i -1) - '0';
            }
            if (i <= sb2.length() - 1) {
                iSum += sb2.charAt(sb2.length() -i - 1) - '0';
            }
            iSum += preOver;
            int over  = iSum >= 10 ? 1 : 0;
            sumSb.insert(0, (iSum % 10));
            preOver = over;
        }
        if (preOver == 1) {
            sumSb.insert(0, (preOver));
        }

        String s = sumSb.toString();
        ListNode head = new ListNode(s.charAt(0) - '0');
        ListNode ct = head;
        for (int i = 1; i < s.length(); i++) {
            ct.next = new ListNode(s.charAt(i) - '0');
            ct = ct.next;
        }
        return head;
    }


    @Test
    public void addInList() {
        ListNode head1 = buildListNode();
        ListNode head2 = new ListNode(6);
        head2.next = new ListNode(6);
        ListNode result = addInList(head1, head2);
        System.out.println(result);
    }


    /**
     *描述
     * 给定一个链表，请判断该链表是否为回文结构。
     * 回文是指该字符串正序逆序完全一致。
     * 数据范围： 链表节点数 0 \le n \le 10^50≤n≤10
     * 5
     *  ，链表中每个节点的值满足 |val| \le 10^7∣val∣≤10
     * 7
     * @param head ListNode类 the head
     * @return bool布尔型
     */
    public boolean isPail (ListNode head) {
        if (head == null) {
            return true;
        }
        List<ListNode> list = new ArrayList<>();
        ListNode ct = head;
        while (ct != null) {
            list.add(ct);
            ct = ct.next;
        }
        int endInx = (list.size() - 1) / 2;
        for (int i = 0; i <= endInx; i++) {
            if (list.get(i).val != list.get(list.size() - 1 - i).val) {
                return false;
            }
        }
        return true;
        // 7 -> 3   8 ->3
    }


    /**
     * BM14 链表的奇偶重排
     * 给定一个单链表，请设定一个函数，将链表的奇数位节点和偶数位节点分别放在一起，重排后输出。
     * 注意是节点的编号而非节点的数值。
     *
     * 数据范围：节点数量满足 0 \le n \le 10^50≤n≤10
     * 5
     *  ，节点中的值都满足 0 \le val \le 10000≤val≤1000
     * 要求：空间复杂度 O(n)O(n)，时间复杂度 O(n)O(n)
     * @return
     */

    public ListNode oddEvenList (ListNode head) {
        // write code here
        if (head == null) {
            return head;
        }
        ListNode ct = head;
        ListNode head2 = head.next;
        while (true) {
            // * -> *  ->  *  ->  *
            ListNode ct2 = ct.next;
            ListNode next1;
            if (ct2 == null || (next1 = ct2.next) == null) {
                ct.next = head2;
                return head;
            }
            ListNode next2 = next1 != null ? next1.next : null;
            ct.next = next1;
            ct2.next = next2;
            ct = next1;
        }

    }

    /**
     * BM15 删除有序链表中重复的元素-I
     * 描述
     * 删除给出链表中的重复元素（链表中元素从小到大有序），使链表中的所有元素都只出现一次
     * 例如：
     * 给出的链表为1\to1\to21→1→2,返回1 \to 21→2.
     * 给出的链表为1\to1\to 2 \to 3 \to 31→1→2→3→3,返回1\to 2 \to 31→2→3.
     *
     * 数据范围：链表长度满足 0 \le n \le 1000≤n≤100，链表中任意节点的值满足 |val| \le 100∣val∣≤100
     * 进阶：空间复杂度 O(1)O(1)，时间复杂度 O(n)O(n)
     * @return
     */
    public ListNode deleteDuplicates (ListNode head) {
        // write code here
        ListNode ct = head;
        while (ct != null) {
            ListNode next = ct.next;
            while (next != null && next.val == ct.val) {
                next = next.next;
            }
            ct.next = next;
            ct = next;
        }
        return head;
    }


    /**
     *BM16 删除有序链表中重复的元素-II
     * 给出一个升序排序的链表，删除链表中的所有重复出现的元素，只保留原链表中只出现一次的元素。
     * 例如：
     * 给出的链表为1 \to 2\to 3\to 3\to 4\to 4\to51→2→3→3→4→4→5, 返回1\to 2\to51→2→5.
     * 给出的链表为1\to1 \to 1\to 2 \to 31→1→1→2→3, 返回2\to 32→3.
     *
     * 数据范围：链表长度 0 \le n \le 100000≤n≤10000，链表中的值满足 |val| \le 1000∣val∣≤1000
     * 要求：空间复杂度 O(n)O(n)，时间复杂度 O(n)O(n)
     * 进阶：空间复杂度 O(1)O(1)，时间复杂度 O(n)O(n)
     * @param head ListNode类
     * @return ListNode类
     */
    public ListNode deleteDuplicatesPlus (ListNode head) {
        // write code here
        while (head != null) {

        }
        return head;
    }





    private ListNode buildListNode() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
//        head.next.next.next.next = new ListNode(5);
        return head;
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


    //给定一个链表，返回链表开始入环的第一个节点。?如果链表无环，则返回?null。
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


    static String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            sb.append(node != null ? node.val : "null").append(",");
            if (node != null) {
                queue.add(node.left);
                queue.add(node.right);
            }
        }
        if(sb.toString().endsWith(","))
            sb.deleteCharAt(sb.toString().length() - 1);
        return sb.toString();
    }
     static TreeNode deserialize(String str) {
        if (str == null || !str.contains(",")) return null;
         String[] strArr = str.split(",");
         TreeNode[] nodeArr = new TreeNode[strArr.length];
         for (int i = 0; i < strArr.length; i++) {
             nodeArr[i] = (!"null".equals(strArr[i]) ? new TreeNode(Integer.parseInt(strArr[i])) : null);
         }
         TreeNode head = null;
         for (int i = 0,subIndex = 1; subIndex < nodeArr.length; i++) {
             TreeNode node = nodeArr[i];
             if(node != null){
                 head = head == null ? node : head;
                 node.left = nodeArr[subIndex++];
                 if(subIndex < strArr.length){
                     node.right = nodeArr[subIndex++];
                 }
             }
         }
         return head;
    }
    StringBuilder preOrder(TreeNode root,StringBuilder sb){
        if (root != null) {
            sb.append(root.val).append(",");
            preOrder(root.left,sb);
            preOrder(root.right,sb);
        }
        return sb;
    }
    StringBuilder midOrder(TreeNode root,StringBuilder sb){
        if (root != null) {
            midOrder(root.left,sb);
            sb.append(root.val).append(",");
            midOrder(root.right, sb);
        }
        return sb;
    }

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
        node.left = build(rootPreIndex + 1, inLeftIndex,
                midIndex - 1, preorder, indexMap);
        node.right = build(rootPreIndex + midIndex - inLeftIndex + 1,
                midIndex + 1, inRightIndex, preorder, indexMap);
        return node;
    }

//    public TreeNode buildTree(int[] preorder, int[] inorder) {
////        Map<Integer, Integer> map = new HashMap<>();
////        for (int i = 0; i < inorder.length; i++) {
////            map.put(inorder[i], i);
////        }
////        return build(0, 0, inorder.length - 1,preorder,map);
////    }
////
////    TreeNode build(int rootPreIndex, int inLeftIndex, int inRightIndex,int[] preorder,Map<Integer, Integer> indexMap) {
////        if(inLeftIndex > inRightIndex)
////            return null;
////        TreeNode node = new TreeNode(preorder[rootPreIndex]);
////        int midIndex = indexMap.get(preorder[rootPreIndex]);
////        node.left = build(rootPreIndex + 1, inLeftIndex, midIndex - 1, preorder, indexMap);
////        node.right = build(rootPreIndex + midIndex - inLeftIndex + 1, midIndex + 1, inRightIndex, preorder, indexMap);
////        return node;
////    }




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


    //合并有序链表
    public ListNode Merge(ListNode n1,ListNode n2) {
        if(n1 == null || n2 == null)
            return n1 != null ? n1 : n2;
        ListNode head = n1.val <= n2.val ? n1 : n2;
        ListNode  tail = null;
        while (n1 != null && n2 != null) {
            ListNode tmp = tail;
            if(n1.val <= n2.val){
                tail = n1;
                n1 = n1.next;
            }else {
                tail = n2;
                n2 = n2.next;
            }
            if (tmp != null) {
                tmp.next = tail;
            }
        }
        tail.next = n1 != null ? n1 : n2;
        return head;
    }

    //求二叉树镜像
    public TreeNode mirrorTree(TreeNode root) {
        if (root != null) {
            TreeNode left = root.left;
            root.left = root.right;
            root.right = left;
            mirrorTree(root.left);
            mirrorTree(root.right);
        }
        return root;
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
//        System.out.println(isValidBST(root));
        String serialize = serialize(root);
        System.out.println(serialize);
        TreeNode deserialize = deserialize(serialize);
        System.out.println(deserialize);
    }

    public static void main(String[] args) {
//        System.out.println(GetNumberOfK(new int[]{1},1));
//        System.out.println(midSearch(new int[]{1, 4, 5, 7, 8, 8, 9}, 9));


//        TreeNode deserialize = deserialize("1,2,3,null,null,4,5");
//        String serialize = serialize(deserialize);
//        System.out.println(serialize);


    }

    boolean isSymmetrical(TreeNode pRoot)
    {
        return pRoot == null ? true : cmp(pRoot.left, pRoot.right);
    }
    //简化版cmp
    boolean cmp(TreeNode left,TreeNode right){
        if(left == null && right == null){
            return true;
        } else if (left == null || right == null || left.val != right.val) {
            return false;
        } else {
            return cmp(left.left, right.right) && cmp(left.right, right.left);
        }
    }
    /*//第一版cmp
    boolean cmp(TreeNode left,TreeNode right){
        if(left == null && right == null){
            return true;
        } else if (left == null || right == null) {
            return false;
        } else if (left != null && right != null) {
            if (left.val != right.val) {
                return false;
            }
            if (!cmp(left.left, right.right)) {
                return false;
            }
            if (!cmp(left.right, right.left)) {
                return false;
            }
            return true;
        }
        return false;
    }*/


    //字符串转整数

    public int StrToInt(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int res = 0;
        int sign = 1;
        char head = str.charAt(0);
        if(head == '-'){
            sign = -1;
        }else if(head >= '0' && head <= '9'){
            res = (head - '0');
        } else if (head == '+') {
            sign = 1;
        } else {
            return 0;
        }
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                if(sign == 1){
                    res = res * 10 + (str.charAt(i) - '0');
                }else{
                    res = res * 10 - (str.charAt(i) - '0');
                }
            }else {
                return 0;
            }
        }
        return res;
    }
    @Test
    public void te1st(){
        int a = '0';
        System.out.println((char)0x41);
    }

    //把二叉树打印成多行,从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
    ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        if (pRoot == null) {
            return new ArrayList<>();
        }
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        ArrayList<TreeNode> ctLine = new ArrayList<>();
        ArrayList<TreeNode> nextLine = new ArrayList<>();
        ctLine.add(pRoot);
        for (int n = 0; !ctLine.isEmpty(); n++) {
            ArrayList<Integer> subList = new ArrayList<>();
            for (int i = 0; i < ctLine.size(); i++) {
                TreeNode node = ctLine.get(i);
                subList.add((n & 1) == 0 ? node.val : ctLine.get(ctLine.size() - 1 - i).val);
                if (node.left != null) {
                    nextLine.add(node.left);
                }
                if (node.right != null) {
                    nextLine.add(node.right);
                }
            }
            res.add(subList);
            ctLine = nextLine;
            nextLine = new ArrayList<>();
        }
        return res;
    }
    @Test
    public void  te(){
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        System.out.println(priorityQueue.poll());

    }


    //求二叉树第k大的数
    private int count = 0;

    TreeNode KthNode(TreeNode node, int k)
    {
        TreeNode value = null;
        if (node != null) {
            value = KthNode(node.left, k);
            if (value != null) {
                return value;
            }
            if(++count == k){
                return node;
            }
            value = KthNode(node.left,k);
        }
        return value ;
    }


    PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((n1, n2) -> n2 - n1);
    int size;

    public void Insert(Integer num) {
        priorityQueue.add(num);
        size++;
    }

    public Double GetMedian() {
        if(size == 0)
            return null;
        if((size & 1) == 1){
            for (int i = 0; i < (size - 1) / 2; i++) {
                priorityQueue.poll();
            }
            return priorityQueue.poll() * 1.0;
        }else{
            int left = 0;
            for (int i = 0; i <= (size - 1) / 2; i++) {
                left = priorityQueue.poll();
            }
            return (left + priorityQueue.poll()) * 0.5;
        }
    }
    @Test
    public void getM(){
        List<Integer> integers = Arrays.asList(1,2);
        for (Integer integer : integers) {
            Insert(integer);
        }
        System.out.println(GetMedian());
    }

    //构建乘积数组
    public int[] multiply(int[] A) {
        if (A == null || A.length <= 1) {
            return null;
        }
        int[] b = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            int tmp = 1;
            for (int j = 0; j < A.length; j++) {
                tmp *= j != i ? A[j] : 1;
            }
            b[i] = tmp;
        }
        return b;
    }
    //删除链表中重复的结点
    public ListNode deleteDuplication(ListNode pHead)
    {
        ListNode ct = pHead;
        ListNode last = null;
        while (ct != null){
            if (ct.next != null && ct.next.val == ct.val) {
                int val = ct.val;
                ListNode node = ct;
                while (node != null && node.val == val) {
                    node = node.next;
                }
                if (last != null) {
                    last.next = node;
                }
                if(ct.val == pHead.val){
                    pHead = node;
                }
                ct = node;
            }
            else {
                last = ct;
                ct = ct.next;
            }
        }
        return pHead;
    }
    @Test
    public void deleteDuplicationTest(){
        ListNode n0 = new ListNode(1);
        n0.next = new ListNode(2);
        n0.next.next = new ListNode(3);
        n0.next.next.next = new ListNode(3);

        Map<Integer, Integer> map = new LinkedHashMap(1, 1f, true) {{
            put(1, 2);
            put(2, 2);
            put(2, 2);
            put(1, 2);
            put(3, 2);
        }};
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey());
        }
    }

    private Map<Character, Integer> map = new LinkedHashMap<>();
    //Insert one char from stringstream
    public void Insert(char ch)
    {
        map.put(ch, map.containsKey(ch) ? map.get(ch) + 1 : 1);
    }
    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce()
    {
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (Integer.valueOf(1).equals(entry.getValue())) {
                return entry.getKey().charValue();
            }
        }
        return '#';
    }
    @Test
    public void FirstAppearingOnceTest() {
        String google = "google";
        for (int i = 0; i < google.length(); i++) {
            Insert(google.charAt(i));
        }
        System.out.println(FirstAppearingOnce());
    }



    public int Sum_Solution(int n) {
        int sum = n;
        boolean b = sum > 0 && (sum += Sum_Solution(n - 1)) > 0;
        return sum;
    }

    public int LastRemaining_Solution(int n, int m) {
        if (n < 1 || m < 1) {
            return -1;
        }
        ListNode head = null;
        ListNode tail = null;
        for (int i = 0; i < n; i++) {
            ListNode node = new ListNode(i);
            head = head != null ? head : node;
            if(tail!=null)
                tail.next = node;
            tail = node;
        }
        tail.next = head;
        int count = 0;
        ListNode node = head;
        ListNode pre = tail;
        while (node.next != node) {
            if (count++ == m - 1) {
                if (pre != null) {
                    pre.next = node.next;
                }
                count = 0;
            } else {
                pre = node;
            }
            node = node.next;
        }
        return node.val;
    }
    public int getCut(int target){
        if (target < 2) return 0;
        if (target <= 3) {
            return target;
        }
        int[] result = new int[target + 1];
        for (int i = 1; i <= target; i++) {
            int temp = 0;
            for (int k = 1; k < i; k++) {
                int value = result[i - k];
                if (k * value > temp) {
                    temp = k * value;
                }
            }
            result[i] = temp > i ? temp : i;
        }
        return result[target];
    }
    @Test
    public void runGetCut(){
        for (int i = 3; i < 15; i++) {
            System.out.print(getCut(i)+"      ");
            System.out.println(cutRope(i));
        }

    }
    private  int cutRope(int target) {
        int a = 0;
        int c = 0;
        int maxValue = 2;

        //输入参数范围验证
//        if (2 > target || 60 < target) {
//            return -1;
//        }
        if (target == 2) {
            return 1;
        }
        if (target == 3) {
            return 2;
        }
        if (target % 3 == 0) {
            maxValue = (int)Math.pow(3, target / 3);
        } else{
            a = target - 2;
            c = a % 3;

            maxValue = maxValue * (int)Math.pow(3, a / 3);
            if (0 != c) {
                maxValue = maxValue * c;
            }
        }


        return maxValue;
    }

}