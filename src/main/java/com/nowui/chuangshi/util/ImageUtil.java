package com.nowui.chuangshi.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ImageUtil {


    public static BufferedImage loadImageLocal(String imgName) {
        try {
            return ImageIO.read(new File(imgName));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static BufferedImage loadImageUrl(String imgName) {
        try {
            URL url = new URL(imgName);
            return ImageIO.read(url);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void writeImageLocal(String newImage, BufferedImage img) {
        if (newImage != null && img != null) {
            try {
                File outputfile = new File(newImage);
                ImageIO.write(img, "jpg", outputfile);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static BufferedImage modifyImage(BufferedImage bufferedImage, String[] contentArray, int x, int y, int width, double lineHeight, Font font) {
        try {
            Graphics2D graphics2D = bufferedImage.createGraphics();
            graphics2D.setBackground(Color.WHITE);
            graphics2D.setColor(Color.BLACK);
            graphics2D.setFont(font);
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            FontMetrics fm = graphics2D.getFontMetrics();
            int w = x;
            int h = y + fm.getHeight();
            for (String content : contentArray) {
                char[] textChar = content.toCharArray();
                for (int i = 0; i < textChar.length; i++) {

                    graphics2D.drawString(String.valueOf(textChar[i]), w, h);
                    w += fm.charWidth(textChar[i]);

                    if (w > x + width) {
                        w = x;
                        h += fm.getHeight() * lineHeight;
                    }
                }
                w = x;
                h += fm.getHeight() * lineHeight;
            }
            graphics2D.dispose();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return bufferedImage;
    }

    public static BufferedImage modifyImagetogeter(BufferedImage newBufferedImage, BufferedImage bufferedImage) {

        try {
            int w = newBufferedImage.getWidth();
            int h = newBufferedImage.getHeight();


            Graphics2D graphics2D = bufferedImage.createGraphics();
            graphics2D.drawImage(newBufferedImage, 0, 0, w, h, null);
            graphics2D.dispose();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return bufferedImage;
    }

}
