package exam;

import java.util.Scanner;

public class Test {
    static int[][] temp;
    public static void main(String[] args) {

//        Scanner in = new Scanner(System.in);
//        String next = in.next();
//        String[] arr1 = next.split(",");
//        next = in.next();
//        String[] arr2 = next.split(",");
//        int len = arr1.length;
//        int[] values = new int[len];
//        int[] cost = new int[len];
//        for(int j = 0; j < len; j++){
//            values[j] = Integer.valueOf(arr1[j]);
//        }
//        for(int j = 0; j < len; j++){
//            cost[j] = Integer.valueOf(arr2[j]);
//        }
//        int cap = in.nextInt();
        int[]values = new int[]{6, 3, 5, 4, 6,6, 3, 5, 4, 6,6, 3, 5, 4, 6,6, 3, 5, 4, 6,6, 3, 5, 4, 6,6, 3, 5, 4, 6};
        int[] cost = new int[] {2, 2, 6, 5, 4,2, 2, 6, 5, 4,2, 2, 6, 5, 4,2, 2, 6, 5, 4,2, 2, 6, 5, 4,2, 2, 6, 5, 4};
        int cap = 100;
        temp = new int[values.length + 1][cap + 1];
        {
            long start = System.currentTimeMillis();
            System.out.println("偷到的宝贝重量:"+get(values,cost,cap));
            System.out.println(""+(System.currentTimeMillis() - start));
        }

        {
            long start = System.currentTimeMillis();
            System.out.println("偷到的宝贝重量:"+getPlus(values,cost,cap));
            System.out.println(""+(System.currentTimeMillis() - start));
        }
    }

    static int get(int[] values, int[] weight, int cap) {
        return getMax(values, weight, values.length, cap);
    }

    static int getPlus(int[] values, int[] weight, int cap) {
        return getPlus(values, weight, values.length, cap);
    }

    static int getPlus(int[] values, int[] cost, int n, int cap) {
        for (int i = 1; i <= n ; i++) {
            for (int j = 1; j <= cap; j++) {
                if(cost[i - 1] > j)
                    temp[i][j] = temp[i - 1][j];
                else
                    temp[i][j] = Math.max(temp[i - 1][j], values[i - 1] + temp[i - 1][j - cost[i - 1]]);
            }
        }
        return temp[n][cap];


//        if(temp[n][cap] != 0)
//            return temp[n][cap];
//        if (cost[n - 1] <= cap) {
//            int max = Math.max(values[n - 1] + getMax(values, cost, n - 1, cap - cost[n - 1]), getMax(values, cost, n - 1, cap));
//            temp[n][cap] = max;
//            return max;
//        } else {
//            int max = getMax(values, cost, n - 1, cap);
//            temp[n][cap] = max;
//            return max;
//        }
    }

    static int getMax(int[] values, int[] w, int n, int cap) {
        if( n ==0 || cap ==0)
            return 0;
        if (w[n - 1] <= cap) {
            return  Math.max(values[n - 1] + getMax(values, w, n - 1, cap - w[n - 1]), getMax(values, w, n - 1, cap));

        } else {
            return getMax(values, w, n - 1, cap);
        }
    }



}
