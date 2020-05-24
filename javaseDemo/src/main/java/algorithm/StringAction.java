package algorithm;

public class StringAction {
    public String LeftRotateString(String str,int n) {
        if(n > str.length() || n< 0)
            return str;
        String res = str.substring(0, n);
        res = str.substring(n) + res;
        return res;
    }

    public static void main(String[] args) {
        System.out.println(ReverseSentence(" "));
    }
    public static String ReverseSentence(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        String[] arr = str.split(" ");
        if(arr.length == 0)
            return str;
        StringBuilder sb = new StringBuilder();
        for (int i = arr.length - 1; i >= 0; i--) {
            sb.append(arr[i] + (i != 0 ? " " : ""));
        }
        return sb.toString();
    }
}
