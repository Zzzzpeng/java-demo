package design.pattern.observerPattern;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIFrame extends JFrame {
    public GUIFrame() throws InterruptedException {
        setTitle("星形啊");
        setVisible(true);
        setSize(500,500);
        MyMouseListener myMouseListener = new MyMouseListener();
        MyMouseListener myMouseListener1 = new MyMouseListener();
        MyMouseListener myMouseListener2 = new MyMouseListener();
        addMouseListener(myMouseListener);
        addMouseListener(myMouseListener1);
        addMouseListener(myMouseListener2);
//        while (true){
//            Thread.sleep(1000);
//        }
        removeMouseListener(myMouseListener2);

//        JButton jButton = new JButton("点我");
//         jButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//
//             }
//         });
//         jButton.addActionListener(new MyActionListener());

    }

    public static void main(String[] args) throws InterruptedException {
        new GUIFrame();
    }
}
class MyActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("嘻嘻嘻");
    }
}
