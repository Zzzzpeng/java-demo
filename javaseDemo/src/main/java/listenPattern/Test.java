package listenPattern;

public class Test {
    public static void main(String[] args) {
        Kid kid = new Kid();
        kid.addIWListener(new IWListener() {
            @Override
            public void handleEvent(WashingHandsEvent event) {
                System.out.println("监听触发: "+"敦促"+event.getSource()+"童鞋洗手");
            }
        });
        kid.eat();
    }
}
