package org.zilaijuan.service.action;

import org.zilaijuan.service.MainFrame;
import org.zilaijuan.service.ScreenShotService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ScanAction
 *
 * @author xiaobo2.zhang
 * @date 2020/11/10 19:45
 */

public class ScanAction implements ActionListener {

    private ScreenShotService ssw;
    private MainFrame frame;

    public ScanAction(MainFrame frame) throws AWTException {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            ssw = new ScreenShotService(frame);
            ssw.setVisible(true);
        } catch (AWTException awtException) {
            awtException.printStackTrace();
        }
    }
}
