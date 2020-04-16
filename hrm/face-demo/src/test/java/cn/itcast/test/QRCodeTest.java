package cn.itcast.test;

import com.baidu.aip.util.Base64Util;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Path;

public class QRCodeTest {
    public static void main(String[] args) throws Exception {
        //1. information in QR code
        String content = "www.baidu.com";

        //2. generate QR code by zxing(save to local picture, supporting data url)
        //create QRCodeWriter object
        QRCodeWriter writer = new QRCodeWriter();

        //configure
        /**
         * 1. QRCode information
         * 2. type of pic
         * 3. width
         * 3. height
         *
         */
        BitMatrix bt = writer.encode(content, BarcodeFormat.QR_CODE,200,200);
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        BufferedImage image = MatrixToImageWriter.toBufferedImage(bt);
        ImageIO.write(image,"png",os);

        String encode = Base64Util.encode(os.toByteArray());
        System.out.println(new String("data:image/png;base64,"+encode));
    }

}
