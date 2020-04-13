package exam;

import java.util.*;

public class Test1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        if(str == null || str.length()<4 || str.length() > 12)
            return;
        int right = str.length()-1;
        int left = 0;
        //left--m1-----m2---m3--right
        List<int[]> list = new ArrayList<>();
        List<int[]> resList = new ArrayList<>();
        for (int i = 1; i < str.length()-3; i++) {
            for (int j = i + 1; j < str.length() - 2; j++) {
                for (int k = j + 1; k < str.length() - 1 ; k++) {
                    int[] idxs = new int[3];
                    idxs[0] = i;
                    idxs[1] = j;
                    idxs[2] = k;
                    list.add(idxs);
                }
            }
        }
        for (int[] idxs : list) {
            int m1 = idxs[0];
            int m2 = idxs[1];
            int m3 = idxs[2];
           if (!isOk(str.substring(left,m1)) || !isOk(str.substring(m1,m2))
                   || !isOk(str.substring(m2,m3))|| !isOk(str.substring(m3,right + 1))){
               continue;
            }
            resList.add(idxs);
        }
        List<String> res = new ArrayList<>(resList.size());
        for (int[] idxs : resList) {
            int m1 = idxs[0];
            int m2 = idxs[1];
            int m3 = idxs[2];
            StringBuilder sb = new StringBuilder();
            sb.append(str.substring(left, m1)).append(".")
            .append(str.substring(m1, m2)).append(".")
            .append(str.substring(m2, m3)).append(".")
            .append(str.substring(m3, right + 1));
             res.add(sb.toString());
        }
        System.out.println(res);
    }
    static boolean isOk(String subStr){
        if(subStr == null || subStr.length() >3 || subStr.length() == 0)
            return false;
        if(subStr.length() > 1 && subStr.charAt(0) == '0')
            return false;
        int num = Integer.valueOf(subStr);
        if(num >= 0 && num <= 255){
            return true;
        }
        return false;
    }

}
