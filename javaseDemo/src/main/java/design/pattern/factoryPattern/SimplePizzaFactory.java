package design.pattern.factoryPattern;

import java.util.Objects;

public class SimplePizzaFactory {
    Pizza createPizza(PizzaEnum pizzaEnum){
        Objects.requireNonNull(pizzaEnum,"PizzaEnum不得为null");
        Pizza pizza = null;
        if(PizzaEnum.CHINA_PIZZA == pizzaEnum){
            pizza =  new ChinaPizza();
        }else if(PizzaEnum.GREEK_PIZZA == pizzaEnum){
            pizza = new GreekPizza();
        }
        pizza.setName(pizzaEnum.getNameEn());
        return pizza;
    }
}
enum PizzaEnum{
    GREEK_PIZZA("希腊披萨","greek"),
    CHINA_PIZZA("中国披萨","china");
    private String nameCn;
    private String nameEn;

    public String getNameCn() {
        return nameCn;
    }

    public String getNameEn() {
        return nameEn;
    }

    private PizzaEnum(String nameCn, String nameEn) {
        this.nameCn = nameCn;
        this.nameEn = nameEn;
    }

    @Override
    public String toString() {
        return "PizzaEnum{" +
                "nameCn='" + nameCn + '\'' +
                ", nameEn='" + nameEn + '\'' +
                '}';
    }
}
 abstract class Pizza {

    protected String name;
    public abstract void prepare();

    public void bake() {
        System.out.println(name + " baking;");
    }

    public void cut() {
        System.out.println(name + " cutting;");
    }

    public void box() {
        System.out.println(name + " boxing;");
    }

    public void setName(String name) {
        this.name = name;
    }
}
class GreekPizza extends Pizza{
    @Override
    public void prepare() {
        System.out.println("希腊披萨正在准备……");
    }
}
class ChinaPizza extends  Pizza{

    @Override
    public void prepare() {
        System.out.println("中国披萨正在准备……");
    }
}
