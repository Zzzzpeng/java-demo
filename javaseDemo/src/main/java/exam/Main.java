package exam;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        StringBuilder sb = new StringBuilder();
        StringBuilder sbChar = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if( c == '*'){
                sb.append(c);
            }else{
                sbChar.append(c);
            }
        }
        System.out.println(sb.toString()+sbChar.toString());
    }
}
