package dataStruct;

import java.util.HashMap;
import java.util.TreeMap;

public class TreeTest {
    static final boolean BLACK = true;
    static final boolean RED = false;

    static class TreeNote {
        int data;
        TreeNote left;
        TreeNote right;
        TreeNote parent;
        boolean color;

        public TreeNote(int data) {
            this.data = data;
        }
    }

    static class MyTree {
        TreeNote root;

        TreeNote getMax(TreeNote note) {
            if (note == null)
                return null;
            while (note.right != null) {
                note = note.right;
            }
            return note;
        }

        TreeNote getMin(TreeNote note) {
            if (note == null)
                return null;
            while (note.left != null) {
                note = note.left;
            }
            return note;
        }

        static boolean colorOf(TreeNote note) {
            return note == null ? BLACK : note.color;
        }

        //左旋
        void leftRotate(TreeNote x) {
            if (x == null || x.right == null)
                return;
            TreeNote y = x.right;
            x.right = y.left;
            if (x.right != null) {
                x.right.parent = x;
            }
            y.parent = x.parent;
            //x为根
            if (x == root) {
                root = y;
            }//不是根就肯定有父节点
            else if (x == x.parent.left) {
                x.parent.left = y;
            } else {
                x.parent.right = y;
            }

            y.left = x;
            x.parent = y;
        }

        //右旋转
        void rightRotate(TreeNote x) {
            if (x == null || x.left == null)
                return;
            TreeNote y = x.left;
            x.left = y.right;
            if (x.left != null) {
                x.left.parent = x;
            }

            y.parent = x.parent;
            //x为根
            if (x == root) {
                root = y;
            }//不是根就肯定有父节点
            else if (x == x.parent.left) {
                x.parent.left = y;
            } else {
                x.parent.right = y;
            }

            y.right = x;
            x.parent = y;
        }

        //求后驱节点
        TreeNote getSuccessor(TreeNote note) {
            if (note == null)
                return null;
            if (note.right != null)
                return getMin(note.right);
            else {
                //向上迭代直至找到左父节点
                while (note.parent != null && note == note.parent.right) {
                    note = note.parent;
                }
                return note.parent;
            }
        }

        //子树替换,只设置与父节点的关系; 注意替换者可以为空树,这样以后可以很省事
        void transplant(TreeNote old, TreeNote replacement) {
            if (replacement != null)
                replacement.parent = old.parent;

            if (old.parent == null) {
                root = replacement;
            } else if (old.parent.left == old) {
                old.parent.left = replacement;
            } else {
                old.parent.right = replacement;
            }
            //todo 是否要清除旧节点的连接关系 如令old.left=null等等
        }


        void deleteNote(int value) {
            TreeNote z = find(value);
            if (z == null)
                return;
            if (z.left == null)
                transplant(z, z.right);
            else if (z.right == null)
                transplant(z, z.left);
            else {
                //如果有两个子节点
                TreeNote successor = getSuccessor(z);
                if (successor.parent != z) {
                    transplant(successor, successor.right);
                    z.right.parent = successor;
                    successor.right = z.right;
                }
                transplant(z, successor);
                successor.left = z.left;
                z.left.parent = successor;
            }
        }

        //刪除节点--红黑树版
        void rb_deleteNote(int value) {
            TreeNote z = find(value);
            if (z == null)
                return;
            TreeNote x;
            TreeNote y = z;
            boolean y_originColor = y.color;
            if (z.left == null || z.right == null) {
                if (z.right != null) {
                    x = z.right;
                    transplant(z, z.right);
                } else if (z.left != null) {
                    x = z.left;
                    transplant(z, z.left);
                }//如果z没有子节点
                else {
                    x = z;
                    if (x.color == BLACK)
                        rb_deleteNote_fixUp(x);
                    if (x.parent == null) {
                        root = null;
                    } else if (x == x.parent.left) {
                        x.parent.left = null;
                    } else {
                        x.parent.right = null;
                    }
                    return;
                }
            } else {
                //如果有两个子节点
                y = getSuccessor(z);
                x = y.right;
                y_originColor = y.color;
                if (y.parent != z) {
                    transplant(y, y.right);
                    z.right.parent = y;
                    y.right = z.right;
                }
                transplant(z, y);
                y.left = z.left;
                z.left.parent = y;
                y.color = z.color;
            }
            if (y_originColor == BLACK) {
                rb_deleteNote_fixUp(x);
            }
        }

        void rb_deleteNote_fixUp(TreeNote x) {
//            if (x == null)
//                return;
            //当x不是跟且是黑色节点才进入
            //此时x必有兄弟节点,且x父节点为起点黑高必>=2(因为x为双黑,如为红黑就不进入循环)
            //注意x可能为叶子节点，即null
            while (root != x && x.color == BLACK) {
                if (x == x.parent.left) {
                    TreeNote bro = x.parent.right;
                    //情况一,旋转+着色
                    if (colorOf(bro) == RED) {
                        x.parent.color = RED;
                        bro.color = BLACK;
                        bro = bro.left;
                        leftRotate(x.parent);
                    }
                    //情况二
                    if (colorOf(bro.left) == BLACK && colorOf(bro.right) == BLACK
                        /*&&colorOf(bro) == BLACK 思考为什么可以省略*/) {
                        bro.color = RED;
                        x = x.parent;
                        /*bro = x.parent.right;错误,要考虑null*/
                    } else {
                        //情况三
                        if (colorOf(bro.right) == BLACK /*&& colorOf(bro.left) == RED 思考为什么可以省略*/) {
                            bro.left.color = BLACK;
                            bro.color = RED;
                            rightRotate(bro);
                            bro = x.parent.right;
                        }
                        //情况四
                        bro.color = bro.parent.color;
                        bro.parent.color = BLACK;
                        bro.right.color = BLACK;
                        leftRotate(x.parent);
                        x = root;
                    }

                }//对称情况
                else {
                    TreeNote bro = x.parent.left;
                    //情况一,旋转+着色
                    if (colorOf(bro) == RED) {
                        x.parent.color = RED;
                        bro.color = BLACK;
                        rightRotate(x.parent);
                        bro = x.parent.left;
                    }
                    //情况二
                    if (/*colorOf(bro) == BLACK &&*/ colorOf(bro.left) == BLACK && colorOf(bro.right) == BLACK) {
                        bro.color = RED;
                        x = x.parent;
                        /*bro = x.parent.right;错误,要考虑null*/
                    } else {
                        //情况三
                        if (colorOf(bro.left) == BLACK) {
                            bro.right.color = BLACK;
                            bro.color = RED;
                            leftRotate(bro);
                            bro = x.parent.left;
                        }
                        //情况四
                        bro.color = bro.parent.color;
                        bro.parent.color = BLACK;
                        bro.left.color = BLACK;
                        rightRotate(x.parent);
                        x = root;
                    }
                }
            }
            x.color = BLACK;
        }


        TreeNote find(int value) {
            TreeNote current = root;
            while (current != null) {
                if (value < current.data)
                    current = current.left;
                else if (value > current.data)
                    current = current.right;
                else
                    return current;
            }
            return null;
        }


        TreeNote insertNote(TreeNote current, int value) {
            if (current == null) {
                TreeNote treeNote = new TreeNote(value);
                if (root == null)
                    root = treeNote;
                return treeNote;
            }
            if (value < current.data) {
                current.left = insertNote(current.left, value);
            } else if (value > current.data) {
                current.right = insertNote(current.right, value);
            } else {
                current.data = value; //基本數據類型時可以省略
            }
            return current;
        }

        TreeNote insertNoteWithParent(TreeNote current, TreeNote parent, int value) {
            if (current == null) {
                TreeNote treeNote = new TreeNote(value);
                treeNote.parent = parent;
                if (root == null)
                    root = treeNote;
                return treeNote;
            }
            if (value < current.data) {
                current.left = insertNoteWithParent(current.left, current, value);
            } else if (value > current.data) {
                current.right = insertNoteWithParent(current.right, current, value);
            } else {
                current.data = value; //基本數據類型時可以省略
            }
            return current;
        }

        //迭代式插入
        void insert(int value) {
            TreeNote current = root;
            TreeNote p = null;
            while (current != null) {
                p = current;
                if (value < current.data)
                    current = current.left;
                else if (value > current.data)
                    current = current.right;
                else return;
            }
            TreeNote newOne = new TreeNote(value);
            newOne.parent = p;
            if (p == null) {
                root = newOne;
            } else if (value < p.data) {
                p.left = newOne;
            } else { // (value > p.data)
                p.right = newOne;
            }
        }

        void rb_insert(int value) {
            TreeNote current = root;
            TreeNote p = null;
            while (current != null) {
                p = current;
                if (value < current.data)
                    current = current.left;
                else if (value > current.data)
                    current = current.right;
                else return;
            }
            TreeNote newOne = new TreeNote(value);
            newOne.parent = p;
            if (p == null) {
                root = newOne;
            } else if (value < p.data) {
                p.left = newOne;
            } else { // (value > p.data)
                p.right = newOne;
            }
            newOne.color = RED;
            rb_insertFixUp(newOne);
        }

        void rb_insertFixUp(TreeNote z) {
            if (z == null) {
                return;
            }
            //以叔节点的颜色进行讨论, 大前提:父节点为红色,则父节点必定不是根,z.p.p不为null
            while (z.parent != null && z.parent.color == RED) {
                TreeNote y;
                if (z.parent == z.parent.parent.left) {
                    y = z.parent.parent.right;
                    if (y != null && y.color == RED) {
                        y.color = BLACK;
                        z.parent.color = BLACK;
                        z.parent.parent.color = RED;
                        z = z.parent.parent;
                    } else {
                        if (z == z.parent.right) {
                            z = z.parent;
                            leftRotate(z);
                        }
                        z.parent.color = BLACK;
                        z.parent.parent.color = RED;
                        rightRotate(z.parent.parent);
                    }

                } else {
                    y = z.parent.parent.left;
                    if (y != null && y.color == RED) {
                        y.color = BLACK;
                        z.parent.color = BLACK;
                        z.parent.parent.color = RED;
                        z = z.parent.parent;
                    } else {
                        if (z == z.parent.left) {
                            z = z.parent;
                            rightRotate(z);
                        }
                        z.parent.color = BLACK;
                        z.parent.parent.color = RED;
                        leftRotate(z.parent.parent);
                    }
                }
            }
            root.color = BLACK;
        }

        private void inOrderTravel() {
            inOrderTravel(root);
        }

        private void inOrderTravel(TreeNote current) {
            if (current != null) {
                inOrderTravel(current.left);
                String color = current.color ? "黑" : "红";
                System.out.print(current.data + color + "  ");
                inOrderTravel(current.right);
            }
        }

        void delNote(int value) {
            if (root == null)
                return;
            //如果删除的节点是叶子节点,直接删除
            //2.如果删除的节点只有一个子节点,直接替换
            //3.如果删除的节点有两子节点,寻找左子树最右节点或者右子树最左节点进行替换
            TreeNote current = root;
            TreeNote parent = null;
            //删除的节点是否是其父节点的左节点
            boolean leftChild = true;
            while (current != null) { //null则删除的节点不存在
                if (value < current.data) {
                    parent = current;
                    current = current.left;
                    leftChild = true;
                } else if (value > current.data) {
                    parent = current;
                    current = current.right;
                    leftChild = false;
                }
                //找到了
                else {
                    TreeNote succssor;
                    //如果删除的节点是叶子节点,直接删除
                    if (current.right == null && current.right == null) {
                        //如果是根节点
                        if (root.data == value) {
                            root = null;
                            return;
                        }
                        succssor = null;
                    }//2.如果删除的节点只有一个子节点,直接替换
                    else if (current.right == null || current.left == null) {
                        succssor = current.left == null ? current.right : current.left;
                        //根节点特殊处理
                        if (root.data == value) {
                            root.left = null;
                            root.right = null;
                            root = succssor;
                            return;
                        }
                    }
                    //3.如果删除的节点有两子节点,寻找左子树最右节点或者右子树最左节点进行替换
                    else {
                        succssor = current.left;
                        TreeNote p = succssor;
                        while (succssor.right != null) {
                            p = succssor;
                            succssor = succssor.right;
                        }
                        //替换者与父节点切掉联系
                        p.right = null;
                        if (root.data == value) {
                            succssor.right = root.right;
                            if (root.left.data != succssor.data)
                                succssor.left = root.left;
                            root.left = null;
                            root.right = null;
                            root = succssor;
                            return;
                        }
                    }
                    //对1、2、3情况统一处理
                    if (parent != null) {
                        if (leftChild)
                            parent.left = succssor;
                        else
                            parent.right = succssor;
                        return;
                    }
                }

            }
        }

    }

    public static void main(String[] args) {
        TreeMap treeMap = new TreeMap();
        int[] nums = {41, 38, 31, 12, 19, 8};
        for (int num : nums) {
            treeMap.put(num, num);
        }
        treeMap.remove(8);
        treeMap.remove(12);
//        treeMap.remove()
        rb_insert_test();

    }

    static void test1() {
        MyTree myTree = new MyTree();
        int[] nums = {4, 1, 2, 6, 5, 7};
        for (int num : nums) {
            myTree.insertNote(myTree.root, num);
        }
        myTree.inOrderTravel();
        System.out.println();
        myTree.delNote(4);
        myTree.inOrderTravel();
    }

    static void test0() {
        MyTree myTree = new MyTree();
        int[] nums = {4, 1, 6, 5, 7};
        for (int num : nums) {
            myTree.insertNote(myTree.root, num);
        }
        myTree.inOrderTravel();
        System.out.println();
        myTree.delNote(4);
        myTree.inOrderTravel();
    }

    static void test2() {
        MyTree myTree = new MyTree();
        int[] nums = {15, 6, 3, 2, 4, 7, 13, 9, 18, 17, 20};
        for (int num : nums) {
            myTree.insert(num);
        }
        myTree.inOrderTravel();
        System.out.println();
        myTree.deleteNote(6);
        myTree.inOrderTravel();
    }

    static void rb_insert_test() {
        MyTree myTree = new MyTree();
        int[] nums = {41, 38, 31, 12, 19, 8};
        for (int num : nums) {
            myTree.rb_insert(num);
        }
        myTree.inOrderTravel();

        myTree.rb_deleteNote(8);
        System.out.println();
        myTree.inOrderTravel();

        myTree.rb_deleteNote(12);
        System.out.println();
        myTree.inOrderTravel();

        myTree.rb_deleteNote(19);
        System.out.println();
        myTree.inOrderTravel();

        myTree.rb_deleteNote(31);
        System.out.println();
        myTree.inOrderTravel();

        myTree.rb_deleteNote(38);
        System.out.println();
        myTree.inOrderTravel();
    }

}
