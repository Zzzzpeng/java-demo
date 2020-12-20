package sort;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class QuickSort {
   public  static void quicklySort(int[] nums,int left,int right){
           if(right<=left)
               return;
          int midIndex = partition(nums,left,right);
          quicklySort(nums,left,midIndex-1);
          quicklySort(nums,midIndex+1,right);
   }
   public static int  partitionAndSort(int[] nums,int startIndex,int endIndex){
       int pilot = nums[startIndex];
       int left = startIndex;
       int right = endIndex;
       int index = startIndex;
       while(left<right){
           while(left<right){
                if(nums[right]<pilot){
                    nums[index] = nums[right];
                    index = right;
                    left++;
                    break;
                }
                right--;
           }
           while(left<right){
                if(nums[left]>pilot){
                    nums[index] = nums[left];
                    index = left;
                    right--;
                    break;
                }
                left++;
           }
       }
       nums[index] = pilot;
       return index;
   }


    public static void main(String[] args) {
        int[] nums = new int[50];
        for (int i = 0; i <nums.length ; i++) {
            nums[i] = (int)(Math.random()*100);
        }
        System.out.println(Arrays.toString(nums));
        quicklySort(nums,0,nums.length-1);
        System.out.println(Arrays.toString(nums));

        boolean isCorrect = true;
        for (int i = 0; i < nums.length - 1; i++) {
            if(nums[i] > nums[i + 1]){
                isCorrect = false;
                break;
            }
        }
        System.out.println(isCorrect);
    }


    /**
     * 双指针法
     * 要注意默认左边的[startIdx]最后要处理,所以左指针必须是<=而不能是<
     *
     * @param nums
     * @param startIdx
     * @param endIdx
     * @return
     */
    private static int partition(int[] nums, int startIdx, int endIdx) {
        int pivot = nums[startIdx];
        int left = startIdx, right = endIdx;
        while (left < right) {
            while (left < right && nums[right] > pivot)right--;
            while (left < right && nums[left] <= pivot)left++;
            int t = nums[right];
            nums[right] = nums[left];
            nums[left] = t;
        }
        //
        nums[startIdx] = nums[right];
        nums[right] = pivot;
        return right;
   }
}
