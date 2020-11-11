package org.zilaijuan.service;

import org.zilaijuan.service.action.ScanAction;

import javax.swing.*;
import java.awt.*;

/**
 * MainFrame
 *
 * @author xiaobo2.zhang
 * @date 2020/11/10 19:57
 */

public class MainFrame {
    private JFrame jFrame;
    private JTextArea textArea;

    public JFrame getjFrame(){
        return this.jFrame;
    }

    public JFrame initFrame() throws AWTException {
        JFrame frame = new JFrame("二维码扫描");    //创建Frame窗口
        frame.setSize(400, 200);
        JPanel jp = new JPanel();    //创建JPanel对象
        JButton btn = new JButton("扫描");    //创建JButton对象
        ScanAction scanAction = new ScanAction(this);
        btn.addActionListener(scanAction);
        jp.add(btn);

        //创建文本域
        JTextArea ta = new JTextArea(10, 40);
        ta.setLineWrap(true);

        jp.add(ta);

        frame.add(jp);
        frame.setBounds(300, 200, 600, 300);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jFrame = frame;
        this.textArea = ta;
        return frame;
    }

    public void updateTxt(String txt){
        textArea.setText(txt);
    }


}
