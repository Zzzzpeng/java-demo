package algorithm;

public class bitAction {
    //不用加减乘除做加法（位运算，清晰图解）,也可以拆成循环
    public int Add(int num1,int num2) {
        return num2 == 0 ? num1 : Add(num1 ^ num2, (num1 & num2) << 1);
    }
}
