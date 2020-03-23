package sort;

import java.util.Arrays;

public class QuickSort {
   public  static void quicklySort(int[] nums,int left,int right){
           if(right<=left)
               return;
          int midIndex = partitionAndSort(nums,left,right);
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
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]<nums[i-1<0?0:i-1])
                isCorrect = false;
        }
        System.out.println(isCorrect);
    }
}
