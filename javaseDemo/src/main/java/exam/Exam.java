package exam;

import java.util.Scanner;

public class Exam {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        while (i % 2 == 0) {
            i /= 2;
        }
        while (i % 3 == 0) {
            i /= 3;
        }
        while (i % 5 == 0) {
            i /= 5;
        }
        if (i != 1) {
            System.out.println(false);
        }else {
            System.out.println(true);
        }
    }
}
