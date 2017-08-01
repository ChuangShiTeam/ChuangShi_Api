package com.nowui.chuangshi.controller;

import com.nowui.chuangshi.util.ImageUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TestController {



    public static void main(String[] args) {
        BufferedImage sealBufferedImage = ImageUtil.loadImageLocal("/Users/yongqiangzhong/Documents/Workspace/React/website/images/seal.png");
        BufferedImage templateBufferedImage = ImageUtil.loadImageLocal("/Users/yongqiangzhong/Documents/Workspace/React/website/images/template.png");

        String[] numberArray = new String[1];
        numberArray[0] = "授权编号：XX-V-WB-20170418001";
        BufferedImage certificateBufferedImage = ImageUtil.modifyImage(templateBufferedImage, numberArray, 515, 250, 300, 1, new Font("Microsoft YaHei", Font.BOLD, 16));

        String[] titleArray = new String[1];
        titleArray[0] = "授权书";
        certificateBufferedImage = ImageUtil.modifyImage(certificateBufferedImage, titleArray, 447, 346, 300, 1, new Font("Microsoft YaHei", Font.BOLD, 34));

        String[] contentArray = new String[3];
        contentArray[0] = "        我司 上海星销信息技术有限公司  为“V+LAB”品牌在中国区域的授权总代理，兹正式授权以下人员为“V+LAB”品牌的经销商：";
        contentArray[1] = "        周立平（花心）（身份证号：34120319891012034X）";
        contentArray[2] = "        本公司证明该渠道销售的所有“V+LAB”品牌产品均为本公司所提供的正品。";
        certificateBufferedImage = ImageUtil.modifyImage(certificateBufferedImage, contentArray, 225, 437, 530, 2, new Font("Microsoft YaHei", Font.BOLD, 18));

        String[] statementTitleArray = new String[1];
        statementTitleArray[0] = "【声明】";
        certificateBufferedImage = ImageUtil.modifyImage(certificateBufferedImage, statementTitleArray, 222, 730 + 50, 300, 1, new Font("Microsoft YaHei", Font.BOLD, 14));

        String[] statementNumberArray = new String[6];
        statementNumberArray[0] = "1、";
        statementNumberArray[1] = "";
        statementNumberArray[2] = "";
        statementNumberArray[3] = "";
        statementNumberArray[4] = "2、";
        statementNumberArray[5] = "3、";
        certificateBufferedImage = ImageUtil.modifyImage(certificateBufferedImage, statementNumberArray, 228, 759 + 50, 300, 1.2, new Font("Microsoft YaHei", Font.BOLD, 13));

        String[] statementContentArray = new String[3];
        statementContentArray[0] = "若出现下列行为（包括但不限于），我司将立即取消其经销商资格，并由该经销商承担因此造成的责任和损失：利用我司名义从事非法活动，利用我司名义从事未授权的活动、销售非从我司采购的该品牌商品、私自降价或抬价、严重损害客户合法权益并造成损失、不正当竞争等等。";
        statementContentArray[1] = "授权书授权期限：2017年4月18日至2017年12月31日。";
        statementContentArray[2] = "本授权书盖章即可生效，期满后自动失效。";
        certificateBufferedImage = ImageUtil.modifyImage(certificateBufferedImage, statementContentArray, 248, 759 + 50, 510, 1.2, new Font("Microsoft YaHei", Font.BOLD, 13));

        String[] statementUnitArray = new String[1];
        statementUnitArray[0] = "授权单位：上海星销信息技术有限公司";
        certificateBufferedImage = ImageUtil.modifyImage(certificateBufferedImage, statementUnitArray, 459, 940 + 50, 300, 1, new Font("Microsoft YaHei", Font.BOLD, 18));

        String[] statementDateArray = new String[1];
        statementDateArray[0] = "2017年4月19日";
        certificateBufferedImage = ImageUtil.modifyImage(certificateBufferedImage, statementDateArray, 635, 987 + 50, 300, 1, new Font("Microsoft YaHei", Font.BOLD, 18));

        ImageUtil.writeImageLocal("/Users/yongqiangzhong/Documents/Workspace/React/website/images/123.jpg", ImageUtil.modifyImagetogeter(sealBufferedImage, certificateBufferedImage));

        System.out.println("success");
    }

}
