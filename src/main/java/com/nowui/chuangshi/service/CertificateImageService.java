package com.nowui.chuangshi.service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.kit.FileKit;
import com.jfinal.kit.PathKit;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.cache.CertificateImageCache;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.CertificateImage;
import com.nowui.chuangshi.type.FileType;
import com.nowui.chuangshi.util.FileUtil;
import com.nowui.chuangshi.util.ImageUtil;
import com.nowui.chuangshi.util.Util;

public class CertificateImageService extends Service {

    private CertificateImageCache certificateImageCache = new CertificateImageCache();

    public List<CertificateImage> listByCertificate_id(String certificate_id) {
        return certificateImageCache.listByCertificate_id(certificate_id);
    }

    public Boolean deleteByCertificate_idAndSystem_update_user_id(String certificate_id, String request_user_id) {
        return certificateImageCache.deleteByCertificate_idAndSystem_update_user_id(certificate_id, request_user_id);
    }

    public Boolean save(String certificate_id, String file_id, String certificate_type, String certificate_channel_name,
            String certificate_channel_url, String certificate_people_name, String certificate_people_id_card,
            String certificate_people_mobile, String certificate_people_wx, String certificate_shop_name,
            String certificate_shop_url, Date certificate_start_date, Date certificate_end_date,
            String system_create_user_id) {
        return certificateImageCache.save(certificate_id, file_id, certificate_type, certificate_channel_name,
                certificate_channel_url, certificate_people_name, certificate_people_id_card, certificate_people_mobile,
                certificate_people_wx, certificate_shop_name, certificate_shop_url, certificate_start_date,
                certificate_end_date, system_create_user_id);
    }

    // 微信平台授权文件生成
    public Map<String, Object> saveWXCertificateFile(String app_id, String system_create_user_id,
            String certificate_type, String certificate_number, String user_name, String certificate_people_name,
            String certificate_people_id_card, String certificate_people_mobile, String certificate_people_wx,
            Date certificate_start_date, Date certificate_end_date) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        String start_date = sdf.format(certificate_start_date);
        String end_date = sdf.format(certificate_end_date);

        BufferedImage sealBufferedImage = ImageUtil
                .loadImageLocal(PathKit.getRootClassPath() + File.separator + "seal.png");
        BufferedImage templateBufferedImage = ImageUtil
                .loadImageLocal(PathKit.getRootClassPath() + File.separator + "template.png");
        BufferedImage maskBufferedImage = ImageUtil.loadImageLocal(PathKit.getRootClassPath() + File.separator + "mask.png");

        String[] numberArray = new String[1];
        numberArray[0] = "授权编号：" + certificate_number;
        BufferedImage certificateBufferedImage = ImageUtil.modifyImage(templateBufferedImage, numberArray, 576, 250,
                300, 1, new Font("Microsoft YaHei", Font.BOLD, 16));// TODO

        String[] titleArray = new String[1];
        titleArray[0] = "授权书";
        certificateBufferedImage = ImageUtil.modifyImage(certificateBufferedImage, titleArray, 447, 346, 300, 1,
                new Font("Microsoft YaHei", Font.BOLD, 34));

        String[] contentArray = new String[4];
        contentArray[0] = "        我司 上海星销信息技术有限公司  为“V+LAB”品牌在中国区域的授权总代理，兹正式授权以下人员为“V+LAB”品牌的经销商：";
        contentArray[1] = "        " + certificate_people_name + "（身份证号：" + certificate_people_id_card + "）";
        contentArray[2] = "        电话：" + certificate_people_mobile + "    微信：" + certificate_people_wx;
        contentArray[3] = "        本公司证明该渠道销售的所有“V+LAB”品牌产品均为本公司所提供的正品。";
        certificateBufferedImage = ImageUtil.modifyImage(certificateBufferedImage, contentArray, 222, 437, 540, 2, // TODO
                new Font("Microsoft YaHei", Font.BOLD, 18));

        String[] statementTitleArray = new String[1];
        statementTitleArray[0] = "【声明】";
        certificateBufferedImage = ImageUtil.modifyImage(certificateBufferedImage, statementTitleArray, 222, 730 + 50,
                300, 1, new Font("Microsoft YaHei", Font.BOLD, 14));

        String[] statementNumberArray = new String[6];
        statementNumberArray[0] = "1、";
        statementNumberArray[1] = "";
        statementNumberArray[2] = "";
        statementNumberArray[3] = "";
        statementNumberArray[4] = "2、";
        statementNumberArray[5] = "3、";
        certificateBufferedImage = ImageUtil.modifyImage(certificateBufferedImage, statementNumberArray, 228, 759 + 50,
                300, 1.2, new Font("Microsoft YaHei", Font.BOLD, 13));

        String[] statementContentArray = new String[3];
        statementContentArray[0] = "若出现下列行为（包括但不限于），我司将立即取消其经销商资格，并由该经销商承担因此造成的责任和损失：利用我司名义从事非法活动，利用我司名义从事未授权的活动、销售非从我司采购的该品牌商品、私自降价或抬价、严重损害客户合法权益并造成损失、不正当竞争等等。";
        statementContentArray[1] = "授权书授权期限：" + start_date + "至" + end_date + "。";
        statementContentArray[2] = "本授权书盖章即可生效，期满后自动失效。";
        certificateBufferedImage = ImageUtil.modifyImage(certificateBufferedImage, statementContentArray, 255, 759 + 50,
                518, 1.2, new Font("Microsoft YaHei", Font.BOLD, 13));

        String[] statementUnitArray = new String[1];
        statementUnitArray[0] = "授权单位：上海星销信息技术有限公司";
        certificateBufferedImage = ImageUtil.modifyImage(certificateBufferedImage, statementUnitArray, 470, 940 + 50,
                300, 1, new Font("Microsoft YaHei", Font.BOLD, 18));

        String[] statementDateArray = new String[1];
        statementDateArray[0] = start_date;
        certificateBufferedImage = ImageUtil.modifyImage(certificateBufferedImage, statementDateArray, 645, 987 + 50,
                300, 1, new Font("Microsoft YaHei", Font.BOLD, 18));

        String file_id = Util.getRandomUUID();
        String resultFilePath = PathKit.getRootClassPath() + File.separator + file_id + ".jpg";
        String maskFilePath = PathKit.getRootClassPath() + File.separator + file_id + "_mask.jpg";

        certificateBufferedImage = ImageUtil.modifyImagetogeter(sealBufferedImage, certificateBufferedImage, 0, 0);

        Graphics2D graphics2D = certificateBufferedImage.createGraphics();
        graphics2D.setBackground(Color.WHITE);
        graphics2D.setColor(Color.BLACK);
        graphics2D.setFont(new Font("Microsoft YaHei", Font.BOLD, 18));
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        FontMetrics fm = graphics2D.getFontMetrics();

        int maxX = 222;
        char[] textChar = ("        " + certificate_people_name + "（身份证号：").toCharArray();
        for (int i = 0; i < textChar.length; i++) {
            maxX += fm.charWidth(textChar[i]);
        }

        ImageUtil.writeImageLocal(resultFilePath, certificateBufferedImage);
        ImageUtil.writeImageLocal(maskFilePath, ImageUtil.modifyImagetogeter(maskBufferedImage, certificateBufferedImage, maxX, 540));

        return this.saveFile(resultFilePath, maskFilePath, app_id, system_create_user_id, file_id);
    }

    public static void main(String[] args) {
        System.out.println("PathKit.getWebRootPath()" + PathKit.getWebRootPath());
        System.out.println("PathKit.getRootClassPath()" + PathKit.getRootClassPath());
        System.out.println("File.separator" + File.separator);
    }

    // 其他平台授权文件生成
    public Map<String, Object> saveOtherCertificateFile(String app_id, String system_create_user_id,
            String certificate_type, String certificate_number, String user_name, String certificate_channel_name,
            String certificate_channel_url, String certificate_people_name, String certificate_people_id_card,
            String certificate_people_mobile, String certificate_people_wx, String certificate_shop_name,
            String certificate_shop_url, Date certificate_start_date, Date certificate_end_date) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        String start_date = sdf.format(certificate_start_date);
        String end_date = sdf.format(certificate_end_date);

        BufferedImage sealBufferedImage = ImageUtil.loadImageLocal(PathKit.getRootClassPath() + File.separator + "seal.png");
        BufferedImage templateBufferedImage = ImageUtil.loadImageLocal(PathKit.getRootClassPath() + File.separator + "template.png");
        BufferedImage maskBufferedImage = ImageUtil.loadImageLocal(PathKit.getRootClassPath() + File.separator + "mask.png");

        String[] numberArray = new String[1];
        numberArray[0] = "授权编号：" + certificate_number;
        BufferedImage certificateBufferedImage = ImageUtil.modifyImage(templateBufferedImage, numberArray, 576, 250,
                300, 1, new Font("Microsoft YaHei", Font.BOLD, 16));// TODO

        String[] titleArray = new String[1];
        titleArray[0] = "授权书";
        certificateBufferedImage = ImageUtil.modifyImage(certificateBufferedImage, titleArray, 447, 346, 300, 1,
                new Font("Microsoft YaHei", Font.BOLD, 34));

        String[] contentArray = new String[6];
        contentArray[0] = "        我司 上海星销信息技术有限公司  为“V+LAB”品牌在中国区域的授权总代理，兹正式授权以下人员为“V+LAB”品牌的经销商：";
        contentArray[1] = "        " + certificate_people_name + "（身份证号：" + certificate_people_id_card + "）";
        contentArray[2] = "        " + certificate_type + "店铺名：" + certificate_shop_name;
        contentArray[3] = "        店铺链接：" + certificate_shop_url;
        contentArray[4] = "        电话：" + certificate_people_mobile + "    微信：" + certificate_people_wx;
        contentArray[5] = "        本公司证明该渠道销售的所有“V+LAB”品牌产品均为本公司所提供的正品。";
        certificateBufferedImage = ImageUtil.modifyImage(certificateBufferedImage, contentArray, 222, 437, 540, 2, // TODO
                new Font("Microsoft YaHei", Font.BOLD, 18));

        String[] statementTitleArray = new String[1];
        statementTitleArray[0] = "【声明】";
        certificateBufferedImage = ImageUtil.modifyImage(certificateBufferedImage, statementTitleArray, 222,
                730 + 50 + 100, 300, 1, new Font("Microsoft YaHei", Font.BOLD, 14));

        String[] statementNumberArray = new String[6];
        statementNumberArray[0] = "1、";
        statementNumberArray[1] = "";
        statementNumberArray[2] = "";
        statementNumberArray[3] = "";
        statementNumberArray[4] = "2、";
        statementNumberArray[5] = "3、";
        certificateBufferedImage = ImageUtil.modifyImage(certificateBufferedImage, statementNumberArray, 228,
                759 + 50 + 100, 300, 1.2, new Font("Microsoft YaHei", Font.BOLD, 13));

        String[] statementContentArray = new String[3];
        statementContentArray[0] = "若出现下列行为（包括但不限于），我司将立即取消其经销商资格，并由该经销商承担因此造成的责任和损失：利用我司名义从事非法活动，利用我司名义从事未授权的活动、销售非从我司采购的该品牌商品、私自降价或抬价、严重损害客户合法权益并造成损失、不正当竞争等等。";
        statementContentArray[1] = "授权书授权期限：" + start_date + "至" + end_date + "。";
        statementContentArray[2] = "本授权书盖章即可生效，期满后自动失效。";
        certificateBufferedImage = ImageUtil.modifyImage(certificateBufferedImage, statementContentArray, 255,
                759 + 50 + 100, 518, 1.2, new Font("Microsoft YaHei", Font.BOLD, 13));

        String[] statementUnitArray = new String[1];
        statementUnitArray[0] = "授权单位：上海星销信息技术有限公司";
        certificateBufferedImage = ImageUtil.modifyImage(certificateBufferedImage, statementUnitArray, 470,
                940 + 50 + 100, 300, 1, new Font("Microsoft YaHei", Font.BOLD, 18));

        String[] statementDateArray = new String[1];
        statementDateArray[0] = start_date;
        certificateBufferedImage = ImageUtil.modifyImage(certificateBufferedImage, statementDateArray, 645,
                987 + 50 + 100, 300, 1, new Font("Microsoft YaHei", Font.BOLD, 18));

        String file_id = Util.getRandomUUID();
        String resultFilePath = PathKit.getRootClassPath() + File.separator + file_id + ".jpg";
        String maskFilePath = PathKit.getRootClassPath() + File.separator + file_id + "_mask.jpg";

        certificateBufferedImage = ImageUtil.modifyImagetogeter(sealBufferedImage, certificateBufferedImage, 0, 0);

        Graphics2D graphics2D = certificateBufferedImage.createGraphics();
        graphics2D.setBackground(Color.WHITE);
        graphics2D.setColor(Color.BLACK);
        graphics2D.setFont(new Font("Microsoft YaHei", Font.BOLD, 18));
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        FontMetrics fm = graphics2D.getFontMetrics();

        int maxX = 222;
        char[] textChar = ("        " + certificate_people_name + "（身份证号：").toCharArray();
        for (int i = 0; i < textChar.length; i++) {
            maxX += fm.charWidth(textChar[i]);
        }

        ImageUtil.writeImageLocal(resultFilePath, certificateBufferedImage);
        ImageUtil.writeImageLocal(maskFilePath, ImageUtil.modifyImagetogeter(maskBufferedImage, certificateBufferedImage, maxX, 540));

        return this.saveFile(resultFilePath, maskFilePath, app_id, system_create_user_id, file_id);
    }

    private Map<String, Object> saveFile(String resultFilePath, String maskFilePath, String app_id, String system_create_user_id,
            String file_id) {
        File uploadFile = new File(resultFilePath);
        File maskUploadFile = new File(maskFilePath);
        // 优化分离
        // TODO
        String path = PathKit.getWebRootPath() + "/" + Constant.UPLOAD + "/" + app_id + "/" + system_create_user_id;
        String thumbnailPath = PathKit.getWebRootPath() + "/" + Constant.UPLOAD + "/" + app_id + "/"
                + system_create_user_id + "/" + Constant.THUMBNAIL;
        String originalPath = PathKit.getWebRootPath() + "/" + Constant.UPLOAD + "/" + app_id + "/"
                + system_create_user_id + "/" + Constant.ORIGINAL;

        FileUtil.createPath(path);
        FileUtil.createPath(thumbnailPath);
        FileUtil.createPath(originalPath);

        String file_suffix = uploadFile.getName().substring(uploadFile.getName().lastIndexOf(".") + 1);
        String file_name = Util.getRandomUUID() + "." + file_suffix;

        path = path + "/" + file_name;
        thumbnailPath = thumbnailPath + "/" + file_name;
        originalPath = originalPath + "/" + file_name;

        String file_type = FileType.IMAGE.getKey();

        FileUtil.resizeImage(uploadFile, file_suffix, thumbnailPath, 100);
        FileUtil.resizeImage(maskUploadFile, file_suffix, path, 0);
        FileUtil.resizeImage(uploadFile, file_suffix, originalPath, 0);

        FileKit.delete(uploadFile);
        FileKit.delete(maskUploadFile);

        Integer file_size = (int) uploadFile.length();
        String file_path = path.replace(PathKit.getWebRootPath(), "");
        String file_thumbnail_path = thumbnailPath.replace(PathKit.getWebRootPath(), "");
        String file_original_path = originalPath.replace(PathKit.getWebRootPath(), "");
        String file_image = "";
        Boolean file_is_external = false;

        Boolean result = FileService.instance.save(file_id, app_id, file_type, file_name, file_suffix, file_size, file_path,
                file_thumbnail_path, file_original_path, file_image, file_is_external, system_create_user_id);

        Map<String, Object> map = new HashMap<String, Object>();
        if (result) {
            map.put(com.nowui.chuangshi.api.file.model.File.FILE_ID, file_id);
            map.put(com.nowui.chuangshi.api.file.model.File.FILE_NAME, file_name);
            map.put(com.nowui.chuangshi.api.file.model.File.FILE_PATH, file_path);
        }

        return map;
    }
}