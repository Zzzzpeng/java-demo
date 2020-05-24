package exam;

import java.util.Scanner;

public class Exam1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double h = 500;
        double res = h;
        if (n > 1) {
            for (int i = 1; i < n; i++) {
                res += h;
                h *= 0.5;
            }
        }
        System.out.println(res);
    }
}
