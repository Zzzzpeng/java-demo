package sort;

import java.util.*;

public class InsertSort {
    /*
     插入排序函数
  */
    public static void moveInsertSort(int[] nums) {
        if (nums == null && nums.length < 2) {
            return;
        }
        int i, leftIndex;
        int insertNode;// 要插入的数据
        // 从数组的第二个元素开始循环将数组中的元素插入
        for (i = 1; i < nums.length; i++) {
            // 设置数组中的第2个元素为第一次循环要插入的数据
            insertNode = nums[i];
            leftIndex = i;
            // 如果要插入的元素小于第j个元素，就将第j个元素向后移
            while ((leftIndex - 1 >= 0) && insertNode < nums[leftIndex - 1]) {
                nums[leftIndex] = nums[leftIndex - 1];
                leftIndex--;
            }
            nums[leftIndex] = insertNode;
//            System.out.print("第" + i + "趟排序：");
        }
    }

    /*
       简单(低效)插入排序函数
    */
    static void insertSort(int arr[]) {
        int i;
        int j;
        int temp;  //定义一个临时变量，用于交换数据时存储
        for (i = 1; i < arr.length; i++) {  //因为我们要对该待排序列的每一个元素都和前面的已排好序的序列进行插入，所以我们会对序列进行遍历
            for (j = 0; j < i; j++) {  //第二层循环主要用于对已排好序的序列进行扫描，和要插入进来的数据进行逐一比较，然后决定插入到哪里
                if (arr[j] > arr[i]) {//从前往后对已排好序的元素和待插入元素进行大小比较，然后直到找到一个元素比被插入元素大，则交换位置
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }


    public static void main(String[] args) {
//        int[] nums = new int[50000];
//        int[] nums1 = new int[50000];
//        for (int i = 0; i < nums1.length; i++) {
//            nums[i] = (int) (Math.random() * 10000);
//            nums1[i] = nums[i];
//        }
//        long t = System.currentTimeMillis();
//        insertSort(nums);
//        System.out.println(System.currentTimeMillis() - t);
//
//        long t1 = System.currentTimeMillis();
//        moveInsertSort(nums1);
//        System.out.println(System.currentTimeMillis() - t1);
////        System.out.println(Arrays.toString(nums1));

//        System.out.println(0b111);
//        System.out.println(0x1f);
//        System.out.println(011);
//

        byte by = (byte) 0b10000000;
//        byte by1 = (byte) 0b01111111;
        by -= 1;
//        by1 += 1;
        System.out.println(by);
//        System.out.println(by1);
        char ch = 0x41;
        System.out.println(ch);
    }

}
