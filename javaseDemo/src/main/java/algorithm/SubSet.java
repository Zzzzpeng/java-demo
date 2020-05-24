package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 和为sum的子集合问题
 */
public class SubSet {
    static int count = 0;
    static void order(int[] nums,int position,int sum,int target){
        if (sum == target) {
            count++;
            return;
        }
        if (position == nums.length) {
            if (sum == target) {
                count++;
            }
        } else {
            order(nums, position + 1, sum + nums[position], target);
            order(nums, position + 1, sum, target);
        }
    }

    public static void main(String[] args) {
//        int[] nums = {1,1,22,23,24,1,3,5,7};
        int[] nums = {1,1,4,1,1,1};
        order(nums, 0, 0, 5);
        System.out.println(count);
    }
}
