package factoryPattern;

public class Test {
    public static void main(String[] args) {
        SimplePizzaFactory simplePizzaFactory = new SimplePizzaFactory();
        Pizza pizza = simplePizzaFactory.createPizza(PizzaEnum.CHINA_PIZZA);
        Pizza pizza1 = simplePizzaFactory.createPizza(PizzaEnum.CHINA_PIZZA);
        System.out.println(pizza==pizza1);
    }
}
