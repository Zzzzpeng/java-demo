package listenPattern;


import java.util.*;

public class Kid {
    private List<IWListener> listenerList = new ArrayList<>();

    public List<IWListener> getListenerList() {
        return listenerList;
    }
    public void setListenerList(List<IWListener> listenerList) {
        this.listenerList = listenerList;
    }

    public void addIWListener(IWListener iwListener){
        this.listenerList.add(iwListener);
    }

    void eat(){
        executeListening();
        System.out.println("开始吃饭");
    }
    void executeListening(){
        WashingHandsEvent washingHandsEvent = new WashingHandsEvent(this);
        if(listenerList!=null){
            Iterator<IWListener> iterator = listenerList.iterator();
            while (iterator.hasNext()){
                IWListener next = iterator.next();
                    next.handleEvent(washingHandsEvent);

            }
        }
    }

}
 class WashingHandsEvent extends EventObject{
     /**
      * Constructs a prototypical Event.
      *
      * @param source The object on which the Event initially occurred.
      * @throws IllegalArgumentException if source is null.
      */
     public WashingHandsEvent(Object source) {
         super(source);
     }
 }



 interface IWListener extends EventListener{
      void handleEvent(WashingHandsEvent event);
 }
 class WashingHandsListener implements IWListener{

     @Override
     public void handleEvent(WashingHandsEvent event) {
         System.out.println("监听器触发: 执行提醒..."+event.getSource()+"请记得洗手");
     }
 }

