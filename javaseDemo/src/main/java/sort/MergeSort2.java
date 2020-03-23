package sort;


import java.lang.reflect.Array;
import java.util.Arrays;

public class MergeSort2 {
    /**
     * 二路归并排序的递归算法-入口
     *
     * @param <T>
     * @param t
     * @return
     */
    public static <T extends Comparable> boolean mergeSortRecursive(T[] t) {
        if (t == null || t.length <= 1)
            return true;
        T[] temp = t.clone();
        MSortRecursive(t, 0, t.length - 1,temp);

        return true;
    }

    /**
     * 二路归并排序的递归算法-递归主体
     *
     * @param <T>
     * @param t
     * @param low
     * @param high
     * @return
     */
    private static <T extends Comparable> boolean MSortRecursive(T[] t,
                                                                 int low, int high,T[] temp) {
        if (t == null || t.length <= 1 || low == high)
            return true;

        int mid = (low + high) / 2;
        MSortRecursive(t, low, mid,temp);
        MSortRecursive(t, mid + 1, high,temp);
        merge(t, low, mid, high,temp);

        return true;
    }


    public static <T extends Comparable> boolean mergeSortNonRecursive(T[] t) {
        if (t == null || t.length <= 1)
            return true;

        int len = 1;
        int low = 0;
        int mid;
        int high;
        T[] temp = (T[]) Array.newInstance(t.getClass().getComponentType(), t.length);
        System.out.println(temp[0]);
        // 程序边界的处理非常重要
        while (len < t.length) {
            for (int i = 0; i + len <= t.length - 1; i += len * 2) {
                //    System.out.println("len="+len);
                low = i;
                mid = i + len - 1;//0
                high = i + len * 2 - 1; //1
                if (high > t.length - 1) //1  5-1
                    high = t.length - 1;  //
                merge(t, i, mid, high,temp);
            }
                //长度加倍
            len += len;
        }
        return true;
    }

    public static <T extends Comparable>  void myMergeSort_no_Recursive(){
//        List<?> names =

    }

    public static void main(String[] args) {

        Integer[] arr = new Integer[10000];
        Integer[] arr1 = new Integer[10000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random()*10000);
            arr1[i] = arr[i];
        }

//        new TreeMap<>().forEach();
        long startTime = System.currentTimeMillis(); // 获取开始时间
        mergeSortRecursive(arr);
        long endTime = System.currentTimeMillis(); // 获取开始时间
        System.out.println("执行时间：" + (endTime - startTime));
        boolean isRight = true;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]>arr[i+1==arr.length-1?i+1:arr.length-1])
                isRight = false;
        }
        System.out.println(isRight);


        startTime = System.currentTimeMillis(); // 获取开始时间
        mergeSortNonRecursive(arr1);
        endTime = System.currentTimeMillis(); // 获取开始时间
        System.out.println("执行时间：" + (endTime - startTime));
         isRight = true;
        for (int i = 0; i < arr1.length; i++) {
            if(arr1[i]>arr1[i+1==arr1.length-1?i+1:arr1.length-1])
                isRight = false;
        }
        System.out.println(isRight);

        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arr1));
    }

    /**
     * 将两个归并段合并成一个归并段
     *
     * @param <T>
     * @param arr
     * @param low
     * @param mid
     * @param high
     * @return
     */
    private static <T extends Comparable> boolean merge(T[] arr, int low,
                                                        int mid, int high, T[] temp) {
        int i, j, k;// 三个指示器，i指示t[low...mid]，j指示t[mid+1...high]，k指示s[low...high]
        for (i = low, j = mid + 1, k = 0; i <= mid || j <= high; ) {
            if (j>high || i<=mid && arr[i].compareTo(arr[j] ) <= 0) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        // 将辅助数组中的排序好的元素复制回原数组
        for (int m = low, index = 0; m <= high;) {
            arr[m++] = temp[index++];
        }

        return true;
    }
}
