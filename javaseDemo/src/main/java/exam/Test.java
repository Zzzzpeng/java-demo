package exam;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String next = in.next();
        String[] arr1 = next.split(",");
        next = in.next();
        String[] arr2 = next.split(",");
        int len = arr1.length;
        int[] values = new int[len];
        int[] w = new int[len];
        for(int j = 0; j < len; j++){
            values[j] = Integer.valueOf(arr1[j]);
        }
        for(int j = 0; j < len; j++){
            w[j] = Integer.valueOf(arr2[j]);
        }
        int cap = in.nextInt();
        System.out.println("偷到的宝贝重量:"+get(values,w,cap));
    }

    static int get(int[] values, int[] weight, int cap) {
        return getMax(values, weight, 0, cap);
    }

    static int getMax(int[] values, int[] w, int n, int cap) {
        if (n >= values.length)
            return 0;
        if (w[n] <= cap) {
            return Math.max(values[n] + getMax(values, w, n + 1, cap - w[n]), getMax(values, w, n + 1, cap));
        } else {
            return getMax(values, w, n + 1, cap);
        }
    }
}
