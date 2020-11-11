package org.zilaijuan;

import org.zilaijuan.service.CaptureService;
import org.zilaijuan.service.MainFrame;
import org.zilaijuan.service.ScreenShotService;
import org.zilaijuan.service.action.ScanAction;

import javax.swing.*;
import java.awt.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws AWTException {
        System.out.println("Hello World!");

        MainFrame mainFrame = new MainFrame();
        JFrame jFrame = mainFrame.initFrame();
        jFrame.setVisible(true);


        /*CaptureService captureService = new CaptureService();
        captureService.snapShot();

        ScreenShotService ssw = new ScreenShotService();
        ssw.setVisible(true);*/
/*        EventQueue.invokeLater(() -> {
            try {
                ScreenShotService ssw = new ScreenShotService();
                ssw.setVisible(true);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        });*/
    }
}
