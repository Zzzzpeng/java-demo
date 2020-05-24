package design.pattern.listenPattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IoBootstrap {
    private List<MsgListner> listeners = new ArrayList();
    private Map body = new HashMap();

    public void addMsgListenner(MsgListner msgListner){
        listeners.add(msgListner);
    }
    public void process(String msg){
        postMsgProcessor(msg);
        System.out.println("IoBootstrap.process()...msg = " + msg);
    }
    private void postMsgProcessor(String msg){
        for (MsgListner listener : listeners) {
            listener.processMsg(msg);
        }
    }
}
interface MsgListner{
    void processMsg(String msg);
}