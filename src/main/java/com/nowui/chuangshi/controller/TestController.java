package com.nowui.chuangshi.controller;

import sun.font.FontDesignMetrics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Vector;

public class TestController {

    private Font font = new Font("Microsoft YaHei", Font.BOLD, 18);// 添加字体的属性设置

    private Graphics2D g = null;

    private int fontsize = 0;

    private int x = 0;

    private int y = 0;

    /**
     * 导入本地图片到缓冲区
     */
    public BufferedImage loadImageLocal(String imgName) {
        try {
            return ImageIO.read(new File(imgName));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * 导入网络图片到缓冲区
     */
    public BufferedImage loadImageUrl(String imgName) {
        try {
            URL url = new URL(imgName);
            return ImageIO.read(url);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * 生成新图片到本地
     */
    public void writeImageLocal(String newImage, BufferedImage img) {
        if (newImage != null && img != null) {
            try {
                File outputfile = new File(newImage);
                ImageIO.write(img, "jpg", outputfile);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * 设定文字的字体等
     */
    public void setFont(String fontStyle, int fontSize) {
        this.fontsize = fontSize;
        this.font = new Font(fontStyle, Font.PLAIN, fontSize);
    }

    /**
     * 修改图片,返回修改后的图片缓冲区（只输出一行文本）
     */
    public BufferedImage modifyImage(BufferedImage img, Object content, int x,
                                     int y) {

        try {
            int w = img.getWidth();
            int h = img.getHeight();
            g = img.createGraphics();
            g.setBackground(Color.WHITE);
            g.setColor(Color.orange);//设置字体颜色
            if (this.font != null)
                g.setFont(this.font);
            // 验证输出位置的纵坐标和横坐标
            if (x >= h || y >= w) {
                this.x = h - this.fontsize + 2;
                this.y = w;
            } else {
                this.x = x;
                this.y = y;
            }
            if (content != null) {
                g.drawString(content.toString(), this.x, this.y);
            }
            g.dispose();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return img;
    }

    /**
     * 修改图片,返回修改后的图片缓冲区（输出多个文本段） xory：true表示将内容在一行中输出；false表示将内容多行输出
     */
    public BufferedImage modifyImage(BufferedImage img, Object[] contentArr, int x, int y, int width, boolean xory) {
        try {
            FontMetrics fm = FontDesignMetrics.getMetrics(font);
            int w = img.getWidth();
            int h = img.getHeight();
            g = img.createGraphics();
            g.setBackground(Color.WHITE);
            g.setColor(Color.BLACK);
            g.setFont(this.font);
            this.x = x;
            this.y = y + fm.getHeight();
            int arrlen = contentArr.length;
            for (int i = 0; i < arrlen; i++) {
                String text = contentArr[i].toString();
                char[] textChar = text.toCharArray();
                for (int j = 0; j < textChar.length; j++) {
                    g.drawString(String.valueOf(textChar[j]), this.x, this.y);
                    this.x += fm.charWidth(textChar[j]);

                    if (this.x > x + width) {
                        this.x = x;
                        this.y += fm.getHeight() * 2;// 重新计算文本输出位置
                    }
                }
                this.x = x;
                this.y += fm.getHeight() * 2;// 重新计算文本输出位置
            }
            g.dispose();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return img;
    }

    /**
     * 修改图片,返回修改后的图片缓冲区（只输出一行文本）
     * <p>
     * 时间:2007-10-8
     *
     * @param img
     * @return
     */
    public BufferedImage modifyImageYe(BufferedImage img) {

        try {
            int w = img.getWidth();
            int h = img.getHeight();
            g = img.createGraphics();
            g.setBackground(Color.WHITE);
            g.setColor(Color.BLACK);//设置字体颜色
            if (this.font != null)
                g.setFont(this.font);
            g.drawString("reyo.cn", w - 85, h - 5);
            g.dispose();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return img;
    }

    public BufferedImage modifyImagetogeter(BufferedImage b, BufferedImage d) {

        try {
            int w = b.getWidth();
            int h = b.getHeight();


            g = d.createGraphics();
            g.drawImage(b, 0, 0, w, h, null);
            g.dispose();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return d;
    }

    public static void main(String[] args) {

//        String[] fontNames = GraphicsEnvironment
//                .getLocalGraphicsEnvironment()
//                .getAvailableFontFamilyNames();
//
//        //... Make vector of all fonts that can display basic chars.
//        //    Vector (not newer ArrayList) is used by JComboBox.
//        Vector<String> visFonts = new Vector<String>(fontNames.length);
//        for (String fontName : fontNames) {
//            System.out.println(fontName);
//        }

        TestController tt = new TestController();

        BufferedImage b = tt.loadImageLocal("/Users/yongqiangzhong/Documents/Workspace/React/website/images/00.png");
        BufferedImage d = tt.loadImageLocal("/Users/yongqiangzhong/Documents/Workspace/React/website/images/template.png");
        Object[] objects = new Object[3];
        objects[0] = "        我司 上海星销信息技术有限公司  为“V+LAB”品牌在中国区域的授权总代理，兹正式授权以下人员为“V+LAB”品牌的经销商：";
        objects[1] = "        周立平（花心）（身份证号：34120319891012034X）";
        objects[2] = "        本公司证明该渠道销售的所有“V+LAB”品牌产品均为本公司所提供的正品。";
        BufferedImage c = tt.modifyImage(d, objects, 220, 437, 530, false);

        tt.writeImageLocal("/Users/yongqiangzhong/Documents/Workspace/React/website/images/123.jpg", tt.modifyImagetogeter(b, c));
        //将多张图片合在一起
        System.out.println("success");
    }

}
