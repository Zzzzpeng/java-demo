import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.util.ReflectionUtils;

public class Test{
    public static void main(String[] args) {
//        new SubContext().init();
    }
}
 class SuperContext {
    public SuperContext(){}
    public void init(){
        System.out.println("父类初始化");
        play();
    }
    public void play(){
        System.out.println("父类玩耍");
    }
}
class SubContext extends SuperContext {

    @Override
    public void play() {
        super.play();
        System.out.println("子类玩耍");
    }

//    @Override
//    public void init(){
//        super.init();
//        System.out.println("子类初始化");
//    }
}