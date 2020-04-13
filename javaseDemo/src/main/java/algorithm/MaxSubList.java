package algorithm;

import java.util.*;

/**
 * 最大子序列
 */
public class MaxSubList {

    public int FindGreatestSumOfSubArray(int[] array) {
        if(array == null || array .length == 0)
            return 0;
        //默认有正有负
        int max = 0;
        int sum = 0;
        boolean allF = true;
        for (int i = 0; i < array.length; i++) {
            if(sum + array[i] < 0){
                sum = 0;
                continue;
            }
            allF = false;
            sum += array[i];
            if(sum > max){
                max = sum;
            }
        }
        if(allF){
            int res = Integer.MIN_VALUE;
            for (int num : array) {
                if(num > res)
                    res = num;
            }
            return res;
        }
        return max;
    }

    /**
     * 求1出现的次数
     * @param n 暴力法
     * @return
     */
    public static int NumberOf1Between1AndN_Solution(int n) {
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(String.valueOf(i));
        }
         count +=  sb.toString().replaceAll("[^1]","").length();
        return count;
    }

    /**
     * 求1出现的次数
     * @param n 递归降位
     * @return
     */
    public static int countDigitOne(int n) {
        if(n<=0)
            return 0;
        if(n < 10)
            return 1;
        String str = String.valueOf(n);
        int hi= str.charAt(0) - 0x30;
        int pow  = (int)Math.pow(10,str.length() - 1);
        int last = n - hi*pow;
        if(hi != 1 ){
            return  hi* countDigitOne(pow - 1) + pow + countDigitOne(last);
        }else {
            return countDigitOne(pow - 1) + countDigitOne(last) + last + 1;
        }
    }

    public static void countDigitOneTest(String[] args) {
        {
            long l = System.currentTimeMillis();
            System.out.println(NumberOf1Between1AndN_Solution(33959555));
            System.out.println(System.currentTimeMillis() - l);
        }

        {
            long l = System.currentTimeMillis();
            System.out.println(countDigitOne(33959555));
            System.out.println(System.currentTimeMillis() - l);
        }
    }

    /**    把数组排成最小的数
     * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个
     * 例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
     * @param numbers
     * @return
     */
    public static String PrintMinNumber(int [] numbers) {
        List<String> queue = new ArrayList<>(numbers.length);

        for (int number : numbers) {
            queue.add(String.valueOf(number));
        }
        //todo 思考为什么有传递性???
        // 传递性(str1 + str2).compareTo(str2 + str1) < 0, 记为 s1 -> s2,
        // 即s1 -> s2, s2 -> s3,则有 s1 -> s3
        queue.sort((str1, str2)->
                (str1 + str2).compareTo(str2 + str1));
        StringBuilder sb = new StringBuilder();
        for (String s : queue) {
            sb.append(s);
        }
        String res = sb.toString();
        for (int i = 0; i < res.length(); i++) {
            if(res.charAt(i) != 0){
                res = res.substring(i);
                break;
            }
        }
        return res;
    }

    /**
     * 求第n个丑数--暴力
     * @param n
     * @return
     */
    public static int GetUglyNumber_Solution(int n) {
        int count = 0;
        int cur = 1;
        while(cur <= Integer.MIN_VALUE){
            int temp = cur;
            while(temp % 2 == 0){
                temp /= 2;
            }
            while(temp % 3 == 0){
                temp /= 3;
            }
            while(temp % 5 == 0){
                temp /= 5;
            }
            if(temp == 1 && ++count == n){
                break;
            }else {
                cur++;
            }
        }
        return cur;
    }

    /**
     * 求第n个丑数--三指针
     * @param n
     * @return
     */
    public static int getUglyNum_3P(int n) {
        if(n < 1)
            return 0;
       int idx2 = 0, idx3 = 0, idx5 = 0;
        int[] res = new int[n];
        res[0] = 1;
        for (int i = 1; i < n; i++) {
            int v2 = 2 * res[idx2];
            int v3 = 3 * res[idx3];
            int v5 = 5 * res[idx5];
            int min = Math.min(Math.min(v2, v3), v5);
            if(min == v2){
                idx2++;
            }else if(min == v3){
                idx3++;
            }else{
                idx5++;
            }
            //如果重复
            if(min != res[i - 1]){
                res[i] = min;
            }else {
                i--;
            }
        }
        return res[n-1];
    }

    /**
     * 优先级队列法 //todo 需要修正
     * @param n
     * @return
     */
    public static int getUglyNumber(int n) {
        if(n < 1)
            return 0;
        int[] base = new int[]{2,3,5};
        Queue<Integer> queue = new PriorityQueue<>((int1,int2)-> int1 < int2 ? 1 : -1);
        queue.addAll(Arrays.asList(1,2,3,5));
        int tempCount = 3;
        while(true){
            if(queue.size() < 2*n){
                int[] temps = new int[tempCount];
                for (int i = 0; i < temps.length; i++) {
                    temps[i] = queue.poll();
                }
                Set<Integer> set = new HashSet<>();
                for (int temp : temps) {
                    for (int i : base) {
                        set.add(i * temp);
                    }
                }
                tempCount = set.size();
                for (int temp : temps) {
                    queue.add(temp);
                }
                for (Integer integer : set) {
                    queue.add(integer);
                }

            }else {
                while (queue.size() != n){
                    queue.poll();
                }
                return queue.poll();
            }
        }
    }

    /**
     * 在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置, 如果没有则返回 -1（需要区分大小写）.
     * @param str
     * @return
     */
    public static char FirstNotRepeatingChar(String str) {
        int[] chars = new int[128];
        int len = str.length();
        for (int i = 0; i < str.length(); i++) {
            int index = str.charAt(i);
            chars[index]++;
        }
        for (int i = 0; i < str.length(); i++) {
            if(chars[str.charAt(i)] == 1)
                return str.charAt(i);
        }
        return ' ';
    }
    public static void main(String[] args) {

    }

}
