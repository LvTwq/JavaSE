package com.example.image;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/11/7 10:35
 */
public class CreateImage {

    public static void main(String[] args) {
        try {
            // 创建一个宽高为1024x1024的白色图像
            BufferedImage image = new BufferedImage(1024, 1024, BufferedImage.TYPE_INT_RGB);
            ImageIO.write(image, "jpg", new File("spFilePath/output.jpg"));

            // 通过逐渐增加图像质量来确保图片大小接近1MB
            int targetSize = 1024 * 1024; // 1MB
            File output = new File("output.jpg");
            ImageWriter writer = ImageIO.getImageWritersByFormatName("jpg").next();
            ImageWriteParam param = new JPEGImageWriteParam(null);

            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            float quality = 1.0f;
            long bytes = 0;

            do {
                quality -= 0.01;
                param.setCompressionQuality(quality);
                writer.setOutput(ImageIO.createImageOutputStream(output));
                writer.write(null, new IIOImage(image, null, null), param);

                bytes = output.length();

            } while (bytes > targetSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
