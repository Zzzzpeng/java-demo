package sort;

import java.util.Arrays;

public class MergeSort {


    public static void main(String[] args) {
        int[] nums = new int[500000];
        for(int i =0;i<nums.length;i++){
            nums[i] = (int)(100*Math.random())+1;
        }
        System.out.println(Arrays.toString(nums));
        simpleMergeSort(nums);
        System.out.println(Arrays.toString(nums));
    }


    public static void simpleMergeSort(int[] nums){
        int[] temp = new int[nums.length];
        mergeSort(nums,0,nums.length-1,temp);
    }
   public static void mergeSort(int[] nums, int left, int right, int[] temp){
       if(left>=right)
           return;
       int mid = (left+right)/2;
       mergeSort(nums,left,mid,temp);
       mergeSort(nums,mid+1,right,temp);
       merge(nums,left,mid,right,temp);

   }
    public static void merge(int[] nums,int left,int mid,int right,int[] temp){
       int leftP = left;
       int rightP = mid+1;
       int p = 0;
       while(leftP<=mid&&rightP<=right){
           if(nums[leftP]<=nums[rightP]){
               temp[p++] = nums[leftP++];
           }else {
               temp[p++] = nums[rightP++];
           }
       }
       while(leftP<=mid){
           temp[p++] = nums[leftP++];
       }
        while(rightP<=right){
            temp[p++] = nums[rightP++];
        }
        int i =0;
        while(left<=right){
            nums[left++] = temp[i++];
        }
    }
}
