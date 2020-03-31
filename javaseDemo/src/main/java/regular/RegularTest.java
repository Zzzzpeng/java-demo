package regular;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularTest {
    static void regular(){
//        {   String str = "123kkkqwe-123qwe-123qwe";
//            String reg = "kkk|dddd";
//
//            Pattern p = Pattern.compile(reg);
//            Matcher matcher = p.matcher(str);
////            System.out.println(matcher.matches());
//            while (matcher.find()){
//                System.out.println("find");
//                System.out.println(str.substring(matcher.start(),matcher.end()));
//            }
//        }

        {   String str = "    -922337wwww";
            String reg = " *[\\+\\-]{0,1}\\d+(?:.*)";//(?=.*)

            Pattern p = Pattern.compile(reg);
            Matcher matcher = p.matcher(str);
            System.out.println(matcher.matches());
            matcher.reset();
            while (matcher.find()){
                System.out.println("找到");
                System.out.println(matcher.start());
                System.out.println(matcher.end());
                System.out.println(str.substring(matcher.start(),matcher.end()));
            }
        }

//        {
//            String str = "    922337www";
//            str=str.trim();
//
//            String pattern="^[\\+\\-\\d]\\d+";//正则表达式，表示以正号或负号或数字开头，且后面是0个或多个数字
//            Pattern p=Pattern.compile(pattern);
//            Matcher m=p.matcher(str);
//
//            String res="";
//            if(m.find()){//能匹配到
//                res=str.substring(m.start(),m.end());
//                System.out.println(res);
//            }else{//不能匹配到
//                System.out.println("不能");
//            } }


    }

    public static void main(String[] args) {
        regular();
    }
}
