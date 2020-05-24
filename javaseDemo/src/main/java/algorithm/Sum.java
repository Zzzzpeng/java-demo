package algorithm;

import sun.reflect.Reflection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Sum {

    /*小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他
    马上就写出了正确答案是100。但是他并不满足于此,他在想究竟有多少
    种连续的正数序列的和为100(至少包括两个数)。没多久,他就得到另一
    组连续正数和为100的序列:18,19,20,21,22。现在把问题交给你,你能
    不能也很快的找出所有和为S的连续正数序列? Good Luck!*/
    public static ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        for (int i = 1; i < sum/2 + 1; i++) {
            int tempSum = i;
            for (int j = i + 1; j < sum; j++) {
                tempSum += j;
                if (tempSum == sum) {
                    ArrayList<Integer> subRes = new ArrayList<>();
                    for (int k = i; k <= j; k++) {
                        subRes.add(i);
                    }
                    res.add(subRes);
                }else if(tempSum > sum){
                    break;
                }
            }
        }
        return res;
    }
    public int[][] findContinuousSequence(int sum) {
        ArrayList<int[]> res = new ArrayList<>();
        for (int i = 1; i < sum/2 + 1; i++) {
            int tempSum = i;
            for (int j = i + 1; j < sum; j++) {
                tempSum += j;
                if (tempSum == sum) {
                    int[] subRes = new int[j - i + 1];
                    for (int k = i; k <= j; k++) {
                        subRes[k - i] = k;
                    }
                    res.add(subRes);
                }else if(tempSum > sum){
                    break;
                }
            }
        }
        int[][] out = new int[res.size()][];
        for (int i = 0; i < res.size(); i++) {
            out[i] = res.get(i);
        }
        return out;
    }

    public ArrayList<Integer> FindNumbersWithSum(int [] array,int sum) {
        Set<Integer> set = new HashSet<>();
        for (int i : array) {
            set.add(i);
        }
        for (int i : array) {
            int target = sum - i;
            if (set.contains(target)) {
                return new ArrayList<>(Arrays.asList(i, target));
            }
        }
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        Thread xixixi = new Thread(() -> {
            System.out.println("xixixi");
        });
        AtomicInteger integer = new AtomicInteger();
    }
}
