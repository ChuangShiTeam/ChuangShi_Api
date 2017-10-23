package com.nowui.chuangshi.util;

import com.jfinal.kit.PathKit;
import com.nowui.chuangshi.constant.Constant;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.InputFormatException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.channels.FileChannel;

public class FileUtil {

    private static String webRootPath;

    static {
        createPath(PathKit.getWebRootPath() + "/" + Constant.UPLOAD);
    }

//    public static String getWebRootPath() {
//        if (webRootPath == null)
//            try {
//                String path = FileUtil.class.getResource("/").toURI().getPath();
//
//                if (path.contains("target/classes/")) {
//
//                    path = path.replace("target/classes/", "src/main/webapp/");
//
//                    webRootPath = new File(path).getCanonicalPath();
//                } else {
//                    webRootPath = new File(path).getParentFile().getParentFile().getCanonicalPath();
//                }
//            } catch (Exception e) {
//                throw new RuntimeException("Exception: " + e.toString());
//            }
//        return webRootPath;
//    }

    public static boolean createPath(String path) {
        File file = new File(path);

        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();

            return true;
        }

        return false;
    }

    public static void resizeImage(File image, String suffix, String path, int width) {
        try {
            BufferedImage bufferedImage = ImageIO.read(image);

            int originalWidth = bufferedImage.getWidth();
            int originalHeight = bufferedImage.getHeight();

            int newWidth = 0;
            int newHeight = 0;


            if (originalWidth > width && width > 0) {
//                if (originalWidth > originalHeight) {
                newWidth = width;

                double scale = (double) originalWidth / (double) newWidth;
                newHeight = (int) (originalHeight / scale);
//                } else {
//                    newHeight = width;
//
//                    double scale = (double) originalHeight / (double) newHeight;
//                    newWidth = (int) (originalWidth / scale);
//                }
            } else {
                newWidth = originalWidth;
                newHeight = originalHeight;
            }

            if (suffix != null && (suffix.trim().toLowerCase().endsWith("png") || suffix.trim().toLowerCase().endsWith("gif"))) {
                BufferedImage to = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
                Graphics2D g2d = to.createGraphics();
                to = g2d.getDeviceConfiguration().createCompatibleImage(newWidth, newHeight, Transparency.TRANSLUCENT);
                g2d.dispose();

                g2d = to.createGraphics();
                Image from = bufferedImage.getScaledInstance(newWidth, newHeight, Image.SCALE_AREA_AVERAGING);
                g2d.drawImage(from, 0, 0, null);
                g2d.dispose();

                ImageIO.write(to, suffix, new File(path));
            } else {
                BufferedImage newImage = new BufferedImage(newWidth, newHeight, bufferedImage.getType());
                Graphics g = newImage.getGraphics();
                g.drawImage(bufferedImage, 0, 0, newWidth, newHeight, null);
                g.dispose();
                ImageIO.write(newImage, suffix, new File(path));
            }
        } catch (IOException e) {
            throw new RuntimeException("IOException: " + e.toString());
        }

    }

    public static void copy(File source, File dest) {
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            inputChannel = new FileInputStream(source).getChannel();
            outputChannel = new FileOutputStream(dest).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } catch (IOException e) {
            throw new RuntimeException("IOException: " + e.toString());
        } finally {
            try {
                inputChannel.close();
                outputChannel.close();
            } catch (IOException e) {
                throw new RuntimeException("IOException: " + e.toString());
            }
        }
    }

    public static void writeFile(String content, String fileName) {
        try {
            Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(new File(fileName)), "UTF-8"));
            writer.write(content.toCharArray());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static byte[] readBytes(BufferedInputStream bufin) throws IOException {
        int buffSize = 1024;
        ByteArrayOutputStream out = new ByteArrayOutputStream(buffSize);

        byte[] temp = new byte[buffSize];
        int size = 0;
        while ((size = bufin.read(temp)) != -1) {
            out.write(temp, 0, size);
        }
        bufin.close();

        byte[] content = out.toByteArray();
        return content;
    }
    
    public static void changeToMp3(String sourcePath, String targetPath) {  
        File source = new File(sourcePath);  
        File target = new File(targetPath);  
        AudioAttributes audio = new AudioAttributes();  
        Encoder encoder = new Encoder();  
  
        audio.setCodec("libmp3lame");  
        EncodingAttributes attrs = new EncodingAttributes();  
        attrs.setFormat("mp3");  
        attrs.setAudioAttributes(audio);  
  
        try {  
            encoder.encode(source, target, attrs);  
        } catch (IllegalArgumentException e) {  
            e.printStackTrace();  
        } catch (InputFormatException e) {  
            e.printStackTrace();  
        } catch (EncoderException e) {  
            e.printStackTrace();  
        }
    }

}
