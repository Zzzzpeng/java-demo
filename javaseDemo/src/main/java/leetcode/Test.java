package leetcode;

import javax.rmi.CORBA.Util;
import java.util.*;

public class Test {
    //求回文子串-暴力法
    public int countSubstrings(String s) {
        class Util {
            boolean isGoal(String str) {
                int len = str.length();
                for (int i = 0; i < len / 2; i++) {
                    if (str.charAt(i) != str.charAt(len - 1 - i)) {
                        return false;
                    }
                }
                return true;
            }
        }
        if (s.length() < 1)
            return 0;
        int sum = 0;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j + i <= s.length(); j++) {
                if (new Util().isGoal(s.substring(j, j + i)))
                    sum++;
            }
        }
        return sum;

    }

    //求回文子串-动态规划
    public static int countSubstrings_1(String s) {
        int length = s.length();
        boolean[][] result = new boolean[length][length];
        int sum = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j + i < length; j++) {
                int left = j, right = j + i;
                if (left == right || (s.charAt(left) == s.charAt(right) && (left + 1 == right || result[left + 1][right - 1]))) {
                    sum++;
                    result[left][right] = true;
                }
            }
        }
        return sum;
    }

    //求回文子串-中心拓展
    public static int countSubstrings_2(String s) {
        int count = 0;
        for (int i = 0; s != null && i < s.length(); i++) {
            count += getCountWhenIdx(s, i, i);
            count += getCountWhenIdx(s, i, i + 1);
        }
        return count;
    }

    //求以"left、right的中心"为中点的回文子串数
    static int getCountWhenIdx(String s, int left, int right) {
        int count = 0;
        while (left >= 0 && right < s.length() && s.charAt(left--) == s.charAt(right++)) {
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
//        System.out.println(new Test().countSubstrings("aaaa"));
//        System.out.println(countSubstrings_1("aaaa"));
//        System.out.println(countSubstrings_2("abc"));
////        System.out.println("".charAt(0));
//        int[] output = null;
//        System.out.println(output.length);


//        findMedianSortedArrays(new int[]{1, 2, 2}, new int[]{1, 2, 3});
//        compare();
//        System.out.println(lengthOfLongestSubstring("abcca"));
        System.out.println(new Test().new Solution().myAtoi("9223372036854775808"));
//        System.out.println((char)0x30);
//        System.out.println((char)0x41);
//        System.out.println((char)0x61);
    }



    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        nums1 = (nums1 != null)? nums1 : new int[]{};
        nums2 = (nums2 != null)? nums2 : new int[]{};
        if(nums1.length + nums2.length == 1)
            return nums1.length == 0? nums2[0] : nums1[0];

        int totalLen = nums1.length + nums2.length;
        int k = (totalLen+2) / 2;
        int idx1 = 0, idx2 = 0;
        int current = 0, pre = 0;
        while (k > 1) {
            if ((idx2 + k / 2 - 1) >= nums2.length ||
                    (idx1 + k / 2 - 1 < nums1.length) && nums1[idx1 + k / 2 - 1] <= nums2[idx2 + k / 2 - 1])
                idx1 += k / 2;
            else
                idx2 += k / 2;
            k -= k / 2;
        }
        if (idx2 >= nums2.length || idx1 < nums1.length && nums1[idx1] <= nums2[idx2])
            current = nums1[idx1];
        else
            current = nums2[idx2];
        if((totalLen & 1) == 0){
            idx2--;idx1--;
            if(idx1 < 0){
                pre = nums2[idx2];
            }else if(idx2 < 0){
                pre = nums1[idx2];
            }else if(nums1[idx1] <= nums2[idx2]){
                pre = nums2[idx2];
            }else {
                pre = nums1[idx1];
            }
        }
        if ((totalLen & 1) ==1)
            return current;
        return (current + pre)*0.5;
    }

    public static double findMergeSortedArrays(int[] nums1, int[] nums2) {
        if(nums1 == null)
            nums1 = new int[]{};
        if(nums2 == null)
            nums2 = new int[]{};
        if(nums1.length + nums2.length == 1){
            return nums1.length == 0? nums2[0] : nums1[0];
        }
        int pre  = -1, current = -1;
        int index1  = 0, index2 = 0;
        int total = (nums1.length + nums2.length)/2 ;
        for(int i = 0; i < total + 1; i++ ){
            pre = current;
            if(index2 >= nums2.length || index1 < nums1.length && (nums1[index1] <= nums2[index2]))
                current = nums1[index1++];
            else
                current = nums2[index2++];
        }
        if(((nums1.length + nums2.length) & 1) == 1)
            return current;
        return (double)(pre+current)/2;
    }

    static void compare(){
        int[] num1 = new int[10000 * 10000];
        int[] num2 = new int[10000 * 10000];
        Random random = new Random();
        for (int i = 0; i < num1.length; i++) {
            num1[i] = random.nextInt(10000);
            num2[i] = random.nextInt(10000);
        }
        Arrays.sort(num1);
        Arrays.sort(num2);
        {
            long s1 = System.currentTimeMillis();
            System.out.println(findMergeSortedArrays(num1,num2));
            System.out.println("time:"+ (System.currentTimeMillis() - s1));
        }
        {
            long s1 = System.currentTimeMillis();
            System.out.println(findMedianSortedArrays(num1,num2));
            System.out.println("time:"+ (System.currentTimeMillis() - s1));
        }
    }
    static int[] getSumIndex(int[] nums,int sum){
        Map<Integer, Integer> map = new HashMap<>();
        int target;
        for (int i = 0; nums != null && i < nums.length; i++) {
            target = sum - nums[i];
            if(map.get(target)==null){
                map.put(nums[i], i);
            }else{
                return new int[]{map.get(target), i};
            }
        }
        return null;
    }
    //不重复字符串
    public static  int lengthOfLongestSubstring(String s) {
        int max = 0;
        char[] chars = s.toCharArray();
        byte[] bytes = new byte[128];//假设是正常字符 asii码0-127
        int rightIdx  = 0;
        for (int i = 0;  i < chars.length; i++) {
            for (; rightIdx < chars.length; rightIdx++) {
                if(bytes[chars[rightIdx]] != 0){
                    break;
                }else {
                    bytes[chars[rightIdx]] = 1;
                    max = rightIdx - i + 1 > max ? rightIdx - i + 1: max;
                }
            }
            bytes[chars[i]] = 0;
        }
        return max;
    }
    class Solution {
        public int myAtoi(String str) {
            char[] src = str.toCharArray();
            int left = -1, right = -1;
            for(int i = 0;i<src.length;i++){
                if(src[i]==' ')
                    continue;
                if(src[i] >= 0x30 && src[i] <= 0x39 || src[i]=='-' || src[i] =='+'){
                    left = i;
                    break;
                }
                return 0;
            }
            right = left;
            for(int i = left + 1 ;i<src.length;i++){
                if(!(src[i] >= 0x30 && src[i] <= 0x39)){
                    break;
                }
                right = i;
            }
            if(left == -1)
                return 0;
            //考虑正常情况
            boolean fu = false;
            if( src[left]=='-' || src[left] =='+'){
                if(src[left]=='-')
                    fu = true;
                left++;
            }
            int res = 0;
            for(;left <= right; left++){
                if(res > Integer.MAX_VALUE/10 ||
                        (res == Integer.MAX_VALUE/10)&&(src[left]-0x30 > 7)&&!fu){
                    return Integer.MAX_VALUE;
                }
                if(res < Integer.MIN_VALUE/10 ||
                        (res == Integer.MIN_VALUE/10)&&(src[left]-0x30 > 8)&&fu){
                    return Integer.MIN_VALUE;
                }
                if(fu)
                    res = res*10 - (src[left]-0x30);
                else
                    res = res*10 + (src[left]-0x30);
            }
            return res;
        }
        public int reverse(int x) {

            int res = 0;
           while (x!=0){
               res = 10*res + x%10;
               x /= 10;
               if((res  >= Integer.MAX_VALUE/10 || res <=Integer.MIN_VALUE/10) && x!=0){
                   if(res  == Integer.MAX_VALUE/10 && x%10 < Integer.MAX_VALUE%10 ||
                        res  == Integer.MIN_VALUE/10 && x%10 > Integer.MIN_VALUE%10){
                       continue;
                   }
                   return 0;
               }
           }
           return res;
        }
    }
}
