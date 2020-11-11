package org.zilaijuan.service;

import com.google.zxing.NotFoundException;
import com.google.zxing.Result;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * 截图窗口
 */
public class ScreenShotService extends JWindow {
    private int orgx, orgy, endx, endy;
    private BufferedImage image = null;
    private BufferedImage tempImage = null;
    private BufferedImage saveImage = null;

    private MainFrame frame;

    public ScreenShotService(MainFrame frame) throws AWTException {
        this.frame = frame;
        //获取屏幕尺寸
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(0, 0, d.width, d.height);

        //截取屏幕
        Robot robot = new Robot();
        image = robot.createScreenCapture(new Rectangle(0, 0, d.width, d.height));

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //鼠标松开时记录结束点坐标，并隐藏操作窗口
                orgx = e.getX();
                orgy = e.getY();


            }

            @Override
            public void mouseReleased(MouseEvent e) {

                //鼠标松开时，显示操作窗口

                Result qRresult = null;
                try {
                    qRresult = ScanService.getQRresult(saveImage);
                    String text = qRresult.getText();
                    System.out.println("qRresult = " + text);
                    frame.updateTxt(text);
                } catch (NotFoundException notFoundException) {
                    notFoundException.printStackTrace();
                    System.out.println("没有找到二维码");
                    frame.updateTxt("没有找到二维码");
                }

                close();
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {
                //鼠标拖动时，记录坐标并重绘窗口
                endx = e.getX();
                endy = e.getY();

                //临时图像，用于缓冲屏幕区域放置屏幕闪烁
                Image tempImage2 = createImage(ScreenShotService.this.getWidth(), ScreenShotService.this.getHeight());
                Graphics g = tempImage2.getGraphics();
                g.drawImage(tempImage, 0, 0, null);
                int x = Math.min(orgx, endx);
                int y = Math.min(orgy, endy);
                int width = Math.abs(endx - orgx) + 1;
                int height = Math.abs(endy - orgy) + 1;
                // 加上1防止width或height0
                g.setColor(Color.BLUE);
                g.drawRect(x - 1, y - 1, width + 1, height + 1);
                //减1加1都了防止图片矩形框覆盖掉
                saveImage = image.getSubimage(x, y, width, height);
                g.drawImage(saveImage, x, y, null);

                ScreenShotService.this.getGraphics().drawImage(tempImage2, 0, 0, ScreenShotService.this);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        RescaleOp ro = new RescaleOp(0.8f, 0, null);
        tempImage = ro.filter(image, null);
        g.drawImage(tempImage, 0, 0, this);
    }

    //保存图像到文件
    public void saveImage() throws IOException {
        JFileChooser jfc = new JFileChooser();
        jfc.setDialogTitle("保存");

        //文件过滤器，用户过滤可选择文件
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG", "jpg");//过滤文件名，只显示jpg格式文件
        jfc.setFileFilter(filter);  //将文件过滤器加入到文件选择中

        //初始化一个默认文件（此文件会生成到桌面上）
        SimpleDateFormat sdf = new SimpleDateFormat("yyyymmddHHmmss");
        String fileName = sdf.format(new Date());  //把时间作为图片名（防止图片名重复，而把之前的图片覆盖）
        File filePath = FileSystemView.getFileSystemView().getHomeDirectory();  //获取系统桌面的路径
        File defaultFile = new File(filePath + File.separator + fileName + ".jpg");
        jfc.setSelectedFile(defaultFile);

        int flag = jfc.showSaveDialog(this);
        if (flag == JFileChooser.APPROVE_OPTION) {
            File file = jfc.getSelectedFile();
            String path = file.getPath();
            //检查文件后缀，放置用户忘记输入后缀或者输入不正确的后缀
            if (!(path.endsWith(".jpg") || path.endsWith(".JPG"))) {
                path += ".jpg";
            }
            //写入文件
            ImageIO.write(saveImage, "jpg", new File(path));
            System.exit(0);
        }
    }

    public void close(){
        this.setVisible(false);
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println(" again" );
//        this.setVisible(true);
    }
}