package org.zilaijuan.service;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

/**
 * ScanService
 *
 * @author xiaobo2.zhang
 * @date 2020/11/10 14:27
 */

public class ScanService {
    public static final String CHARTSET = "utf-8";

    public static Result getQRresult(BufferedImage bufferedImage) throws NotFoundException {

        Result result = null;
        try {

            BinaryBitmap bitmap = new BinaryBitmap(
                    new HybridBinarizer(new BufferedImageLuminanceSource(bufferedImage)));

            HashMap hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, CHARTSET);
            result = new MultiFormatReader().decode(bitmap, hints);
        } catch (NotFoundException e) {
            e.printStackTrace();
            throw e;
        }

        return result;
    }
}
