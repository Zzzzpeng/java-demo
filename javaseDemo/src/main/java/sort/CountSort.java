package sort;/* 	public class CountSort {

        private static int[] countSort(int[] array,int k)
        {
            int[] C=new int[k+1];//构造C数组
            int length=array.length,sum=0;//获取A数组大小用于构造B数组
            int[] B=new int[length];//构造B数组
            for(int i=0;i<length;i++)
            {
                C[array[i]]+=1;// 统计A中各元素个数，存入C数组
            }
            for(int i=0;i<k+1;i++)//修改C数组
            {
                sum+=C[i];
                C[i]=sum;
            }
            for(int i=length-1;i>=0;i--)//遍历A数组，构造B数组
            {

                B[C[array[i]]-1]=array[i];//将A中该元素放到排序后数组B中指定的位置
                C[array[i]]--;//将C中该元素-1，方便存放下一个同样大小的元素

            }
            return B;//将排序好的数组返回，完成排序

        }
        public static void main(String[] args)
        {
            int[] A=new int[]{2,5,3,0,2,3,0,3};
            int[] B=countSort(A, 5);
            for(int i=0;i<A.length;i++)
            {
                System.out.println((i+1)+"th:"+B[i]);
            }
        }
    } */

import java.util.Arrays;

public class CountSort {
    public static int[] simCountSort(int[] nums){
        return null;
    }
    public static int[] sort(int[] nums){
        int max = max(nums);
        int[] count = new int[max+1];//计数数组
        for(int i=0;i<nums.length;i++){ //在index=nums[i]位置计数
            count[nums[i]]++;
        }
        for(int i=0,sum=0;i<max+1;i++){
            count[i]+=sum;
            sum = count[i];
        }
        int[] res = new int[nums.length];


//        for(int i=nums.length-1;i>=0;i--){
//                res[count[nums[i]]-1] = nums[i];
//                count[nums[i]]--;
//        }
        for(int i=0;i<nums.length;i++){
            res[count[nums[i]]-1] = nums[i];
            count[nums[i]]--;
        }
        return res;
    }
    public static Integer max(int[] nums){
        int max = nums==null?null:nums[0];
        for(int i =0;i<nums.length;i++){
            if(nums[i]>max)
                max=nums[i];
        }
        return max;
    }

    public static int[] countSort(int[] nums){
        int max = max(nums);
        int[] count = new int[max+1];

        for (int i = 0; i <nums.length; i++) {
            count[nums[i]]++;
        }
        for (int i = 0,sum=0; i <count.length ; i++) {
            count[i]+=sum;
            sum = count[i];
        }
        int[] res = new int[nums.length];
        for (int i = 0; i <nums.length ; i++) {
            res[count[nums[i]]-1] = nums[i];
        }
        return res;
    }

    static int[] simpleCountSort(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num > max)
                max = num;
        }
        int[] countArr = new int[max + 1];
        for (int num : nums) {
            countArr[num]++;
        }
        //输出
        for (int i = 0,index = 0; i < countArr.length; i++) {
            while (countArr[i]-- > 0){
                nums[index++] = i;
            }
        }
        return nums;
    }
    public static void main(String[] args){
        int[] nums = new int[10000];
        for(int i =0;i<nums.length;i++){
            nums[i] = (int)(10000*Math.random())+1;
        }
//        System.out.println(Arrays.toString(nums));
        System.out.println(isSort(nums));
        int[] newNums = simpleCountSort(nums);
//        System.out.println(Arrays.toString(newNums));
        System.out.println(isSort(newNums));
    }
    static boolean isSort(int[] nums){
        for (int i = 0; i < nums.length - 1; i++) {
            if(nums[i] > nums[i + 1])
                return false;
        }
        return true;
    }
}
					
					
					
					
					
					
					
					
					
				
					
					
					
					
					
					
					