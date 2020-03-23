package sort;

import java.util.Arrays;
import java.util.TreeMap;

public class ShellSort {
    public static void shellSort(int []arr){
        //增量gap，并逐步缩小增量
         for(int gap=arr.length/2;gap>0;gap/=2){
            //从第gap个元素，逐个对其所在组进行直接插入排序操作
             for(int i=gap;i<arr.length;i++){
                 int j = i;
                 int temp = arr[j];
                 if(temp<arr[j-gap]){
                     while(j-gap>=0 && temp<arr[j-gap]){
                         //移动法
                         arr[j] = arr[j-gap];
                         j-=gap;
                     }
                     arr[j] = temp;
                 }
             }
         }
    }

    public static void myShellSort(int []arr){
        //增量gap，并逐步缩小增量
        for(int gap=arr.length/2;gap>0;gap/=2){
            //从第gap个元素，逐个对其所在组进行直接插入排序操作
            for(int i=gap;i<arr.length;i++){
                int j = i;
                int temp = arr[i];
                    while(j-gap>=0 && arr[j-gap]>temp){
                        arr[j] = arr[j-gap];
                        j = j-gap;
                        arr[j] = temp;
                    }
            }
        }
    }

    public static void myLowShellSort(int []arr){
        //增量gap，并逐步缩小增量
        for(int gap=arr.length/2;gap>0;gap/=2){
            //从第gap个元素，逐个对其所在组进行直接插入排序操作
            for(int i=gap;i<arr.length;i++){
               for(int j = 0;j<i;j+=gap){
                   if(arr[j]>arr[i]){
                       swap(arr,j,i);
                   }
               }

            }
        }
    }
    public static void InsertSort(int[] nums) {
        int i, j;
        int insertNode;// 要插入的数据
        // 从数组的第二个元素开始循环将数组中的元素插入
        for (i = 1; i < nums.length; i++) {
            // 设置数组中的第2个元素为第一次循环要插入的数据
            insertNode = nums[i];
            j = i - 1;
            // 如果要插入的元素小于第j个元素，就将第j个元素向后移
            while ((j >= 0) && insertNode < nums[j]) {
                nums[j + 1] = nums[j];
                j--;
            }
            // 直到要插入的元素不小于第j个元素,将insertNote插入到数组中
            nums[j + 1] = insertNode;
//            System.out.print("第" + i + "趟排序：");
        }
    }
    public static void main(String[] args) {

        int[] nums = new int[50000*2];
        for (int i = 0; i <nums.length ; i++) {
            nums[i] = (int)(Math.random()*10000);
        }
        long t1 = System.currentTimeMillis();
        myShellSort(nums);
        System.out.println(System.currentTimeMillis()-t1);
//        System.out.println(Arrays.toString(nums));
        boolean isCorrect = true;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]<nums[i-1<0?0:i-1])
                isCorrect = false;
        }
        System.out.println(isCorrect);
    }

    static void swap(int[] nums ,int indexA ,int indexB){
        int temp = nums[indexA];
        nums[indexA] = nums[indexB];
        nums[indexB] = temp;
    }
}
