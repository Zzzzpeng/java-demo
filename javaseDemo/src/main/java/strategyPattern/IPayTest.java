package strategyPattern;

import java.util.HashMap;
import java.util.Map;

public interface IPayTest {
}
class Order{
    String payMethod;
    String userId;
    public void pay(){
        PayStrategyFactory.getIPay(payMethod).pay();
    }
}
class PayStrategyFactory {
    static Map<String, IPay> payMap = new HashMap<>();
    static {
        payMap.put(Alipay.class.getName(), new Alipay());
        payMap.put(WXpay.class.getName(), new Alipay());
    }
    public static IPay getIPay(String name) {
        return payMap.get(name);
    }
}

interface IPay {
    Integer pay();
}
class Alipay implements IPay {
    @Override
    public Integer pay() {
        return null;
    }
}
class WXpay implements IPay {
    @Override
    public Integer pay() {
        return null;
    }
}
