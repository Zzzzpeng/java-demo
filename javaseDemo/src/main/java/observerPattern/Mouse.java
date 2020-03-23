package observerPattern;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

interface IMouseAction{
    void onClick();
    void onMoveOver();
}
public class Mouse extends AbstrctCommonListener implements IMouseAction{
   public void onClick(){
        System.out.println("我被点击了,请求监听对象进行处理");
        this.onEvent(Thread.currentThread().getStackTrace()[1].getMethodName());
    }
   public void onMoveOver(){
        System.out.println("我被移开了,请求监听对象进行处理");
        this.onEvent(Thread.currentThread().getStackTrace()[1].getMethodName());
    }

}
class MouseListener implements IMouseAction{
    @Override
    public void onClick() {
        System.out.println("监听到点击事件...处理中...");
    }
    @Override
    public void onMoveOver() {
        System.out.println("监听到离开事件...处理中...");
    }
}
abstract class AbstrctCommonListener{
    private Object handler;
    private Map<String, Method> eventMethodsMap = new HashMap<>();

    protected void onEvent(String methodName){
        if("onEvent".equals(methodName) || handler == null) //防止递归
            return;
        Method method = null;
        try {
            method = handler.getClass().getMethod(methodName);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        if(method != null && handler!=null){
            try {
                method.invoke(handler);
            } catch (Exception e) {
                throw new RuntimeException("反射调用异常");
            }
        }
    }
    public void setHandler(Object handler) {
        this.handler = handler;
    }

}
