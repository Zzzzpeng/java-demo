package extend;

public class ExtendTest {
    public static void main(String[] args) {
        new Dog().show();
    }
}
class Ani{
    protected String name = "3";
}
class Dog extends Ani{
    String name = "5";
    public void show(){
        System.out.println(this.name);
        System.out.println(super.name);
    }
}