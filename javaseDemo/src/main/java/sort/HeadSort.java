package sort;

import java.util.Arrays;

public class HeadSort {
    static int getMaxPubYueShu(int a, int b) {
        if (a == 0 || b == 0 || a < 0 || b < 0)
            return 0;
        int max = a >= b ? a : b;
        int min = a >= b ? b : a;
        return doGetMaxPubYueShuNoRerurval(max, min);
    }

    static int doGetMaxPubYueShu(int max, int min) {
        int tempRes = max % min;
        if (tempRes == 0)
            return min;
        return doGetMaxPubYueShu(min, tempRes);
    }

    static int doGetMaxPubYueShuNoRerurval(int max, int min) {
        int tempMax = max, tempMin = min;
        int tempRes;
        while ((tempRes = tempMax % tempMin) != 0) {
            tempMax = tempMin;
            tempMin = tempRes;
        }
        return tempMin;
    }


    public static void main(String[] args) {
        int maxPubYueShu = getMaxPubYueShu (50, 5);
        System.out.println(maxPubYueShu);
//        int[] nums = new int[5];
//        for (int i = 0; i < nums.length; i++) {
//            nums[i] = (int) (Math.random() * 100);
//        }
//        nums[4] = 1000;
//        System.out.println(Arrays.toString(nums));
//        sort(nums);
//        System.out.println(Arrays.toString(nums));
    }

    public static void sort(int[] arr) {
        //1.构建大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            //从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(arr, i, arr.length);
        }
        //2.调整堆结构+交换堆顶元素与末尾元素
        for (int j = arr.length - 1; j > 0; j--) {
            swap(arr, 0, j);//将堆顶元素与末尾元素进行交换
            adjustHeap(arr, 0, j);//重新对堆进行调整
        }

    }

    /**
     * 调整大顶堆（仅是调整过程，建立在大顶堆已构建的基础上）
     *
     * @param arr
     * @param i
     * @param length
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];//先取出当前元素i
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {//从i结点的左子结点开始，也就是2i+1处开始
            if (k + 1 < length && arr[k] < arr[k + 1]) {//如果左子结点小于右子结点，k指向右子结点
                k++;
            }
            if (arr[k] > temp) {//如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;//将temp值放到最终的位置
    }

    /**
     * 交换元素
     *
     * @param arr
     * @param a
     * @param b
     */
    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
