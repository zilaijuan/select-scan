package org.zilaijuan;

import org.zilaijuan.service.MainFrame;


import javax.swing.*;
import java.awt.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws AWTException {

        MainFrame mainFrame = new MainFrame();
        JFrame jFrame = mainFrame.initFrame();
        jFrame.setVisible(true);

    }
}
