package com.nowui.chuangshi.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.file.model.File;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.user.service.UserService;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.Certificate;
import com.nowui.chuangshi.model.CertificateImage;
import com.nowui.chuangshi.service.CertificateImageService;
import com.nowui.chuangshi.service.CertificateService;
import com.nowui.chuangshi.type.CertificateImageType;
import com.nowui.chuangshi.util.DateUtil;
import com.nowui.chuangshi.util.Util;

public class CertificateImageController extends Controller {

    private final CertificateService certificateService = new CertificateService();
    private final CertificateImageService certificateImageService = new CertificateImageService();

    @ActionKey(Url.CERTIFICATE_IMAGE_LIST)
    public void list() {
        validateRequest_app_id();
        validate(CertificateImage.CERTIFICATE_ID);

        JSONObject jsonObject = getParameterJSONObject();

        authenticateRequest_app_idAndRequest_user_id();

        List<CertificateImage> resultList = certificateImageService
                .listByCertificate_id(jsonObject.getString(CertificateImage.CERTIFICATE_ID));

        for (CertificateImage result : resultList) {
            result.keep(CertificateImage.CERTIFICATE_ID, CertificateImage.SYSTEM_VERSION);
        }

        renderSuccessJson(resultList);
    }

    // 会员添加微信授权文件（需要校验）
    @ActionKey(Url.CERTIFICATE_IMAGE_WX_SAVE)
    public void saveWX() throws ParseException {
        validateRequest_app_id();
        validate(CertificateImage.CERTIFICATE_TYPE, CertificateImage.CERTIFICATE_PEOPLE_NAME,
                CertificateImage.CERTIFICATE_PEOPLE_ID_CARD, CertificateImage.CERTIFICATE_PEOPLE_MOBILE,
                CertificateImage.CERTIFICATE_PEOPLE_WX);

        CertificateImage model = getModel(CertificateImage.class);
        model.setCertificate_start_date(new Date());
        model.setCertificate_end_date(DateUtil.getNowYearLastDay());
        String app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Certificate certificate = certificateService.findByUser_id(request_user_id);
        // 判断是否支付
        if (certificate == null || !certificate.getCertificate_is_pay()) {
            throw new RuntimeException("授权未支付！");
        }
        // 判断是否已经生成微信授权
        List<CertificateImage> certificateImageList = certificateImageService
                .listByCertificate_id(certificate.getCertificate_id());
        for (CertificateImage certificateImage : certificateImageList) {
            if (CertificateImageType.WX.getValue().equals(certificateImage.getCertificate_type())) {
                throw new RuntimeException("微信授权已经生成！");
            }
        }

        User user = UserService.instance.find(request_user_id);
        Map<String, Object> retMap = certificateImageService.saveWXCertificateFile(app_id, request_user_id,
                model.getCertificate_type(), certificate.getCertificate_number(), user.getUser_name(),
                model.getCertificate_people_name(), model.getCertificate_people_id_card(),
                model.getCertificate_people_mobile(), model.getCertificate_people_wx(),
                model.getCertificate_start_date(), model.getCertificate_end_date());
        String file_id = String.valueOf(retMap.get(File.FILE_ID));

        Boolean result = certificateImageService.save(certificate.getCertificate_id(), file_id,
                model.getCertificate_type(), "", "", model.getCertificate_people_name(),
                model.getCertificate_people_id_card(), model.getCertificate_people_mobile(),
                model.getCertificate_people_wx(), "", "", model.getCertificate_start_date(),
                model.getCertificate_end_date(), request_user_id);

        renderSuccessJson(result);
    }

    // 会员添加其他授权文件（需要校验）
    @ActionKey(Url.CERTIFICATE_IMAGE_OTHER_SAVE)
    public void save() {
        validateRequest_app_id();
        validate(CertificateImage.CERTIFICATE_TYPE, CertificateImage.CERTIFICATE_PEOPLE_NAME,
                CertificateImage.CERTIFICATE_PEOPLE_ID_CARD, CertificateImage.CERTIFICATE_PEOPLE_MOBILE,
                CertificateImage.CERTIFICATE_PEOPLE_WX, CertificateImage.CERTIFICATE_SHOP_NAME,
                CertificateImage.CERTIFICATE_SHOP_URL);

        CertificateImage model = getModel(CertificateImage.class);
        model.setCertificate_start_date(new Date());
        model.setCertificate_end_date(DateUtil.getNowYearLastDay());
        model.setCertificate_channel_name("");
        model.setCertificate_channel_url("");
        String app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Certificate certificate = certificateService.findByUser_id(request_user_id);
        // 判断是否支付
        if (certificate == null || !certificate.getCertificate_is_pay()) {
            throw new RuntimeException("授权未支付！");
        }

        User user = UserService.instance.find(request_user_id);
        Map<String, Object> retMap = certificateImageService.saveOtherCertificateFile(app_id, request_user_id,
                model.getCertificate_type(), certificate.getCertificate_number(), user.getUser_name(),
                model.getCertificate_channel_name(), model.getCertificate_channel_url(),
                model.getCertificate_people_name(), model.getCertificate_people_id_card(),
                model.getCertificate_people_mobile(), model.getCertificate_people_wx(),
                model.getCertificate_shop_name(), model.getCertificate_shop_url(), model.getCertificate_start_date(),
                model.getCertificate_end_date());
        String file_id = String.valueOf(retMap.get(File.FILE_ID));

        Boolean result = certificateImageService.save(certificate.getCertificate_id(), file_id,
                model.getCertificate_type(), model.getCertificate_channel_name(), model.getCertificate_channel_url(),
                model.getCertificate_people_name(), model.getCertificate_people_id_card(),
                model.getCertificate_people_mobile(), model.getCertificate_people_wx(),
                model.getCertificate_shop_name(), model.getCertificate_shop_url(), model.getCertificate_start_date(),
                model.getCertificate_end_date(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.CERTIFICATE_IMAGE_DELETE)
    public void delete() {
        validateRequest_app_id();
        validate(CertificateImage.CERTIFICATE_ID, CertificateImage.SYSTEM_VERSION);

        CertificateImage model = getModel(CertificateImage.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Boolean result = certificateImageService
                .deleteByCertificate_idAndSystem_update_user_id(model.getCertificate_id(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.CERTIFICATE_IMAGE_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(CertificateImage.CERTIFICATE_ID);

        CertificateImage model = getModel(CertificateImage.class);

        authenticateRequest_app_idAndRequest_user_id();

        List<CertificateImage> resultList = certificateImageService.listByCertificate_id(model.getCertificate_id());

        for (CertificateImage result : resultList) {
            result.keep(CertificateImage.CERTIFICATE_ID, CertificateImage.SYSTEM_VERSION);
        }

        renderSuccessJson(resultList.size(), resultList);
    }

    // 后台管理员 添加微信授权文件（不需要校验）
    @ActionKey(Url.CERTIFICATE_IMAGE_WX_ADMIN_SAVE)
    public void adminWXSave() {
        validateRequest_app_id();
        validate(User.USER_ID, CertificateImage.CERTIFICATE_TYPE, CertificateImage.CERTIFICATE_PEOPLE_NAME,
                CertificateImage.CERTIFICATE_PEOPLE_ID_CARD, CertificateImage.CERTIFICATE_PEOPLE_MOBILE,
                CertificateImage.CERTIFICATE_PEOPLE_WX, CertificateImage.CERTIFICATE_START_DATE,
                CertificateImage.CERTIFICATE_END_DATE);

        CertificateImage model = getModel(CertificateImage.class);
        String app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();
        JSONObject jsonObject = getParameterJSONObject();
        String user_id = jsonObject.getString(User.USER_ID);

        authenticateRequest_app_idAndRequest_user_id();

        // 判断是否支付
        // TODO
        Certificate certificate = certificateService.findByUser_id(user_id);
        User user = UserService.instance.find(user_id);
        String certificate_number = "";
        String certificate_id = "";
        if (certificate == null) {
            certificate_number = DateUtil.getDateString(new Date()).replace("-", "") + Util.getRandomNumber();
            certificate_id = Util.getRandomUUID();

            certificateService.save(certificate_id, app_id, user_id, certificate_number,
                    model.getCertificate_start_date(), model.getCertificate_end_date(), false, request_user_id);
        } else {
            certificate_number = certificate.getCertificate_number();
            certificate_id = certificate.getCertificate_id();
        }

        Map<String, Object> retMap = certificateImageService.saveWXCertificateFile(app_id, request_user_id,
                model.getCertificate_type(), certificate_number, user.getUser_name(),
                model.getCertificate_people_name(), model.getCertificate_people_id_card(),
                model.getCertificate_people_mobile(), model.getCertificate_people_wx(),
                model.getCertificate_start_date(), model.getCertificate_end_date());
        String file_id = String.valueOf(retMap.get(File.FILE_ID));

        Boolean result = certificateImageService.save(certificate_id, file_id, model.getCertificate_type(), "", "",
                model.getCertificate_people_name(), model.getCertificate_people_id_card(),
                model.getCertificate_people_mobile(), model.getCertificate_people_wx(), "", "",
                model.getCertificate_start_date(), model.getCertificate_end_date(), request_user_id);

        renderSuccessJson(result);
    }

    // 后台管理员 添加其他授权文件（不需要校验）
    @ActionKey(Url.CERTIFICATE_IMAGE_OTHER_ADMIN_SAVE)
    public void adminOtherSave() {
        validateRequest_app_id();
        validate(CertificateImage.CERTIFICATE_TYPE, CertificateImage.CERTIFICATE_PEOPLE_NAME,
                CertificateImage.CERTIFICATE_PEOPLE_ID_CARD, CertificateImage.CERTIFICATE_PEOPLE_MOBILE,
                CertificateImage.CERTIFICATE_PEOPLE_WX, CertificateImage.CERTIFICATE_START_DATE,
                CertificateImage.CERTIFICATE_END_DATE, CertificateImage.CERTIFICATE_CHANNEL_NAME,
                CertificateImage.CERTIFICATE_CHANNEL_URL, CertificateImage.CERTIFICATE_SHOP_NAME,
                CertificateImage.CERTIFICATE_SHOP_URL);

        CertificateImage model = getModel(CertificateImage.class);
        String app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();
        JSONObject jsonObject = getParameterJSONObject();
        String user_id = jsonObject.getString(User.USER_ID);

        authenticateRequest_app_idAndRequest_user_id();

        // 判断是否支付
        // TODO
        Certificate certificate = certificateService.findByUser_id(user_id);
        User user = UserService.instance.find(user_id);
        String certificate_number = "";
        String certificate_id = "";
        if (certificate == null) {
            certificate_number = DateUtil.getDateString(new Date()).replace("-", "") + Util.getRandomNumber();
            certificate_id = Util.getRandomUUID();

            certificateService.save(certificate_id, app_id, user_id, certificate_number,
                    model.getCertificate_start_date(), model.getCertificate_end_date(), false, request_user_id);
        } else {
            certificate_number = certificate.getCertificate_number();
            certificate_id = certificate.getCertificate_id();
        }

        Map<String, Object> retMap = certificateImageService.saveOtherCertificateFile(app_id, request_user_id,
                model.getCertificate_type(), certificate.getCertificate_number(), user.getUser_name(),
                model.getCertificate_channel_name(), model.getCertificate_channel_url(),
                model.getCertificate_people_name(), model.getCertificate_people_id_card(),
                model.getCertificate_people_mobile(), model.getCertificate_people_wx(),
                model.getCertificate_shop_name(), model.getCertificate_shop_url(), model.getCertificate_start_date(),
                model.getCertificate_end_date());
        String file_id = String.valueOf(retMap.get(File.FILE_ID));

        Boolean result = certificateImageService.save(certificate_id, file_id, model.getCertificate_type(),
                model.getCertificate_channel_name(), model.getCertificate_channel_url(),
                model.getCertificate_people_name(), model.getCertificate_people_id_card(),
                model.getCertificate_people_mobile(), model.getCertificate_people_wx(),
                model.getCertificate_shop_name(), model.getCertificate_shop_url(), model.getCertificate_start_date(),
                model.getCertificate_end_date(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.CERTIFICATE_IMAGE_ADMIN_DELETE)
    public void adminDelete() {
        validateRequest_app_id();
        validate(CertificateImage.CERTIFICATE_ID, CertificateImage.SYSTEM_VERSION);

        CertificateImage model = getModel(CertificateImage.class);
        String request_user_id = getRequest_user_id();

        Boolean result = certificateImageService
                .deleteByCertificate_idAndSystem_update_user_id(model.getCertificate_id(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.CERTIFICATE_IMAGE_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(CertificateImage.CERTIFICATE_ID);

        CertificateImage model = getModel(CertificateImage.class);

        List<CertificateImage> resultList = certificateImageService.listByCertificate_id(model.getCertificate_id());

        for (CertificateImage result : resultList) {
            result.keep(CertificateImage.CERTIFICATE_ID, CertificateImage.SYSTEM_VERSION);
        }

        renderSuccessJson(resultList.size(), resultList);
    }

    @ActionKey(Url.CERTIFICATE_IMAGE_SYSTEM_SAVE)
    public void systemSave() {
        validateRequest_app_id();
        validate(CertificateImage.CERTIFICATE_ID, CertificateImage.FILE_ID, CertificateImage.CERTIFICATE_TYPE,
                CertificateImage.CERTIFICATE_CHANNEL_NAME, CertificateImage.CERTIFICATE_CHANNEL_URL,
                CertificateImage.CERTIFICATE_PEOPLE_NAME, CertificateImage.CERTIFICATE_PEOPLE_ID_CARD,
                CertificateImage.CERTIFICATE_PEOPLE_MOBILE, CertificateImage.CERTIFICATE_SHOP_NAME,
                CertificateImage.CERTIFICATE_SHOP_URL, CertificateImage.CERTIFICATE_START_DATE,
                CertificateImage.CERTIFICATE_END_DATE);

        CertificateImage model = getModel(CertificateImage.class);
        String request_user_id = getRequest_user_id();

        Boolean result = certificateImageService.save(model.getCertificate_id(), model.getFile_id(),
                model.getCertificate_type(), model.getCertificate_channel_name(), model.getCertificate_channel_url(),
                model.getCertificate_people_name(), model.getCertificate_people_id_card(),
                model.getCertificate_people_mobile(), model.getCertificate_people_wx(),
                model.getCertificate_shop_name(), model.getCertificate_shop_url(), model.getCertificate_start_date(),
                model.getCertificate_end_date(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.CERTIFICATE_IMAGE_SYSTEM_DELETE)
    public void systemDelete() {
        validateRequest_app_id();
        validate(CertificateImage.CERTIFICATE_ID, CertificateImage.SYSTEM_VERSION);

        CertificateImage model = getModel(CertificateImage.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Boolean result = certificateImageService
                .deleteByCertificate_idAndSystem_update_user_id(model.getCertificate_id(), request_user_id);

        renderSuccessJson(result);

    }

}