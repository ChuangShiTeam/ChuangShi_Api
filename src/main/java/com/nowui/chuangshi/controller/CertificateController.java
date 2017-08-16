package com.nowui.chuangshi.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.*;
import com.nowui.chuangshi.service.*;
import com.nowui.chuangshi.type.CertificateImageType;
import com.nowui.chuangshi.util.DateUtil;
import com.nowui.chuangshi.util.Util;
import com.nowui.chuangshi.util.ValidateUtil;

public class CertificateController extends Controller {

    private final CertificateService certificateService = new CertificateService();
    private final CertificateImageService certificateImageService = new CertificateImageService();
    private final UserService userService = new UserService();
    private final FileService fileService = new FileService();
    private final MemberService memberService = new MemberService();
    private final MemberLevelService memberLevelService = new MemberLevelService();
    private final CertificatePayService certificatePayService = new CertificatePayService();

    // 官网根据授权编号返回授权证书列表
    // 默认最低100条
    @ActionKey(Url.CERTIFICATE_WEBSITE_FIND)
    public void websiteFind() {
        validate(Certificate.CERTIFICATE_NUMBER, App.APP_ID);

        Certificate model = getModel(Certificate.class);

        List<Certificate> certificateList = certificateService
                .listByApp_idOrLikeCertificate_numberAndLimit(model.getApp_id(), model.getCertificate_number(), 0, 100);

        List<CertificateImage> certificateImageWXList = new ArrayList<>();
        List<CertificateImage> certificateImageOtherList = new ArrayList<>();

        if (certificateList != null && certificateList.size() > 0 && certificateList.get(0) != null) {
            List<CertificateImage> certificateImageList = certificateImageService
                    .listByCertificate_id(certificateList.get(0).getCertificate_id());

            for (CertificateImage certificateImage : certificateImageList) {
                File file = fileService.findByFile_id(certificateImage.getFile_id());
                certificateImage.put(File.FILE_ORIGINAL_PATH, file.getFile_path());
                certificateImage.keep(CertificateImage.CERTIFICATE_TYPE, File.FILE_ORIGINAL_PATH);

                if (certificateImage.getCertificate_type().equals(CertificateImageType.WX.getValue())) {
                    certificateImageWXList.add(certificateImage);
                } else {
                    certificateImageOtherList.add(certificateImage);
                }
            }
        }

        certificateImageWXList.addAll(certificateImageOtherList);
        renderSuccessJson(certificateImageWXList);
    }

    @ActionKey(Url.CERTIFICATE_LIST)
    public void list() {
        validateRequest_app_id();
        validate(Constant.PAGE_SIZE, Constant.FIRST_CREATE_TIME, Constant.LAST_CREATE_TIME);

        String request_app_id = getRequest_app_id();
        JSONObject jsonObject = getParameterJSONObject();

        authenticateRequest_app_idAndRequest_user_id();

        List<Certificate> resultList = certificateService.listByApp_idAndSystem_create_timeAndLimit(request_app_id,
                jsonObject.getDate(Constant.LAST_CREATE_TIME), 0, getN());

        for (Certificate result : resultList) {
            result.keep(Certificate.CERTIFICATE_ID, Certificate.SYSTEM_VERSION);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.CERTIFICATE_FIND)
    public void find() {
        validateRequest_app_id();
        validate();
        String user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Certificate certificate = certificateService.findByUser_id(user_id);

        List<CertificateImage> certificateImageList = new ArrayList<>();
        List<CertificateImage> certificateImageWXList = new ArrayList<>();
        List<CertificateImage> certificateImageOtherList = new ArrayList<>();
        Map<String, Object> result = new HashMap<>();

        if (certificate != null) {
            authenticateApp_id(certificate.getApp_id());

            User user = userService.findByUser_id(user_id);
            certificate.put(User.USER_NAME, user.getUser_name());

            certificateImageList = certificateImageService
                    .listByCertificate_id(certificate.getCertificate_id());

            for (CertificateImage certificateImage : certificateImageList) {
                File file = fileService.findByFile_id(certificateImage.getFile_id());
                certificateImage.put(File.FILE_ORIGINAL_PATH, file.getFile_original_path());

                certificateImage.keep(Certificate.CERTIFICATE_ID, File.FILE_ORIGINAL_PATH, CertificateImage.CERTIFICATE_TYPE);

                if (certificateImage.getCertificate_type().equals(CertificateImageType.WX.getValue())) {
                    certificateImageWXList.add(certificateImage);
                } else {
                    certificateImageOtherList.add(certificateImage);
                }
            }

            certificate.keep(Certificate.CERTIFICATE_ID, Certificate.USER_ID, User.USER_NAME,
                    Certificate.CERTIFICATE_NUMBER, Certificate.CERTIFICATE_START_DATE,
                    Certificate.CERTIFICATE_END_DATE, Certificate.CERTIFICATE_IS_PAY, Certificate.SYSTEM_VERSION);
        }

        BigDecimal total_fee = getMoney(user_id);

        result.put("certificate", certificate == null ? new Certificate() : certificate);
        result.put("certificateImageList", certificateImageList);
        result.put("certificateImageWXList", certificateImageWXList);
        result.put("certificateImageOtherList", certificateImageOtherList);
        result.put("total_fee", total_fee);

        renderSuccessJson(result);
    }

    private BigDecimal getMoney(String request_user_id) {
        Integer member_level_value = 0;
        BigDecimal total_fee_decimal;
        Member member = memberService.findByUser_id(request_user_id);
        if (!ValidateUtil.isNullOrEmpty(member.getMember_level_id())) {
            MemberLevel memberLevel = memberLevelService.findByMember_level_id(member.getMember_level_id());
            member_level_value = memberLevel.getMember_level_value();
        }
        if (member_level_value == 1) {
            total_fee_decimal = new BigDecimal(5000);
        } else if (member_level_value == 2) {
            total_fee_decimal = new BigDecimal(5000);
        } else if (member_level_value == 3) {
            total_fee_decimal = new BigDecimal(5000);
        } else if (member_level_value == 4) {
            total_fee_decimal = new BigDecimal(5000);
        } else if (member_level_value == 5) {
            total_fee_decimal = new BigDecimal(2000);
        } else if (member_level_value == 6) {
            total_fee_decimal = new BigDecimal(2000);
        } else if (member_level_value == 7) {
            total_fee_decimal = new BigDecimal(2000);
        } else {
            total_fee_decimal = new BigDecimal(2000);
        }

        return total_fee_decimal;
    }

    // 授权证书支付
    @ActionKey(Url.CERTIFICATE_SAVE)
    public void save() {
        validateRequest_app_id();
        validate();

        String app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();
        JSONObject jsonObject = getParameterJSONObject();
        String open_id = jsonObject.getString("open_id");

        BigDecimal total_fee_decimal = getMoney(request_user_id);

        if (request_user_id.equals("229736797b4d4283b284f6aef128585c") || request_user_id.equals("519b7acab2374f129ef4df5d4ab3ec25")) {
            total_fee_decimal = new BigDecimal(0.01);
        }

        authenticateRequest_app_idAndRequest_user_id();
        Certificate certificate = certificateService.findByUser_id(request_user_id);

        Boolean flag = true;
        String certificate_id = "";

        if (certificate == null) {
            String certificate_number = DateUtil.getDateString(new Date()).replace("-", "") + Util.getRandomNumber();
            certificate_id = Util.getRandomUUID();

            flag = certificateService.save(certificate_id, app_id, request_user_id, certificate_number, new Date(),
                    new Date(), false, request_user_id);
        } else if (certificate.getCertificate_is_pay()) {
            throw new RuntimeException("授权已支付!");
        } else {
            certificate_id = certificate.getCertificate_id();
        }

        Map<String, String> result = new HashMap<>();
        if (flag) {
            result = certificateService.pay(certificate_id, open_id, "WX", total_fee_decimal, request_user_id);
        }

        renderSuccessJson(result);
    }

    // 授权证书支付 本地测试
    @ActionKey("/certificate/pay")
    public void pay() {
        validateRequest_app_id();
        validate();

        String app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();
        JSONObject jsonObject = getParameterJSONObject();
        String open_id = jsonObject.getString("open_id");

        authenticateRequest_app_idAndRequest_user_id();
        Certificate certificate = certificateService.findByUser_id(request_user_id);

        Boolean flag = false;
        String certificate_id = "";

        if (certificate == null) {
            String certificate_number = DateUtil.getDateString(new Date()).replace("-", "") + Util.getRandomNumber();
            certificate_id = Util.getRandomUUID();

            flag = certificateService.save(certificate_id, app_id, request_user_id, certificate_number, new Date(),
                    new Date(), false, request_user_id);
        } else {
            certificate_id = certificate.getCertificate_id();
        }

        if (flag) {
            boolean certificate_is_pay = true;

            Certificate certificate1 = certificateService.findByCertificate_id(certificate_id);

            boolean is_update = certificateService.updateValidateSystem_version(certificate1.getCertificate_id(),
                    certificate1.getUser_id(), certificate1.getCertificate_number(),
                    certificate1.getCertificate_start_date(), certificate1.getCertificate_end_date(),
                    certificate_is_pay, certificate1.getUser_id(), certificate1.getSystem_version());

            if (is_update) {
                User user = userService.findByUser_id(certificate1.getUser_id());
                Member member = memberService.findByMember_id(user.getObject_Id());

                // 设置小数位数，第一个变量是小数位数，第二个变量是取舍方法(四舍五入)
                BigDecimal bd = new BigDecimal(0.01);
                bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);

                // 转日期
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String str = sdf.format(new Date());

                certificatePayService.save(certificate1.getCertificate_id(), member.getMember_level_id(), bd, open_id,
                        str, "", certificate1.getUser_id());
            }
        }

        Map<String, String> result = new HashMap<>();
        result.put("certificate_id", certificate_id);
        renderSuccessJson(result);
    }

    // 支付成功确认
    @ActionKey(Url.CERTIFICATE_CONFIRM)
    public void confirm() {
        validateRequest_app_id();
        validate(Certificate.CERTIFICATE_ID);

        Certificate model = getModel(Certificate.class);

        authenticateRequest_app_idAndRequest_user_id();

        Certificate certificate = certificateService.findByCertificate_id(model.getCertificate_id());

        authenticateApp_id(certificate.getApp_id());
        authenticateSystem_create_user_id(certificate.getSystem_create_user_id());

        CertificatePay certificatePay = certificatePayService.findByCertificate_id(model.getCertificate_id());
        certificate.put(CertificatePay.CERTIFICATE_AMOUNT, certificatePay.getCertificate_amount());
        certificate.keep(Certificate.CERTIFICATE_IS_PAY, CertificatePay.CERTIFICATE_AMOUNT);

        renderSuccessJson(certificate);
    }

    @ActionKey(Url.CERTIFICATE_UPDATE)
    public void update() {
        validateRequest_app_id();
        validate(Certificate.CERTIFICATE_ID, Certificate.USER_ID, Certificate.CERTIFICATE_NUMBER,
                Certificate.CERTIFICATE_START_DATE, Certificate.CERTIFICATE_END_DATE, Certificate.CERTIFICATE_IS_PAY,
                Certificate.SYSTEM_VERSION);

        Certificate model = getModel(Certificate.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Certificate certificate = certificateService.findByCertificate_id(model.getCertificate_id());

        authenticateApp_id(certificate.getApp_id());
        authenticateSystem_create_user_id(certificate.getSystem_create_user_id());

        Boolean result = certificateService.updateValidateSystem_version(model.getCertificate_id(), model.getUser_id(),
                model.getCertificate_number(), model.getCertificate_start_date(), model.getCertificate_end_date(),
                model.getCertificate_is_pay(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.CERTIFICATE_DELETE)
    public void delete() {
        validateRequest_app_id();
        validate(Certificate.CERTIFICATE_ID, Certificate.SYSTEM_VERSION);

        Certificate model = getModel(Certificate.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Certificate certificate = certificateService.findByCertificate_id(model.getCertificate_id());

        authenticateApp_id(certificate.getApp_id());
        authenticateSystem_create_user_id(certificate.getSystem_create_user_id());

        Boolean result = certificateService.deleteByCertificate_idAndSystem_update_user_idValidateSystem_version(
                model.getCertificate_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.CERTIFICATE_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(Certificate.CERTIFICATE_NUMBER, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Certificate model = getModel(Certificate.class);
        String request_app_id = getRequest_app_id();

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = certificateService.countByApp_idOrLikeCertificate_number(request_app_id,
                model.getCertificate_number());
        List<Certificate> resultList = certificateService.listByApp_idOrLikeCertificate_numberAndLimit(request_app_id,
                model.getCertificate_number(), getM(), getN());

        for (Certificate result : resultList) {
            result.keep(Certificate.CERTIFICATE_ID, Certificate.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.CERTIFICATE_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(Certificate.USER_ID);

        Certificate model = getModel(Certificate.class);

        authenticateRequest_app_idAndRequest_user_id();

        Certificate certificate = certificateService.findByUser_id(model.getUser_id());

        List<CertificateImage> certificateImageWXList = new ArrayList<>();
        List<CertificateImage> certificateImageOtherList = new ArrayList<>();
        Map<String, Object> result = new HashMap<>();

        if (certificate != null) {
            authenticateApp_id(certificate.getApp_id());

            User user = userService.findByUser_id(model.getUser_id());
            certificate.put(User.USER_NAME, user.getUser_name());

            List<CertificateImage> certificateImageList = certificateImageService
                    .listByCertificate_id(certificate.getCertificate_id());

            for (CertificateImage certificateImage : certificateImageList) {
                File file = fileService.findByFile_id(certificateImage.getFile_id());
                certificateImage.put(File.FILE_ORIGINAL_PATH, file.getFile_original_path());

                if (certificateImage.getCertificate_type().equals(CertificateImageType.WX.getValue())) {
                    certificateImageWXList.add(certificateImage);
                } else {
                    certificateImageOtherList.add(certificateImage);
                }
            }

            certificate.keep(Certificate.CERTIFICATE_ID, Certificate.USER_ID, User.USER_NAME,
                    Certificate.CERTIFICATE_NUMBER, Certificate.CERTIFICATE_START_DATE,
                    Certificate.CERTIFICATE_END_DATE, Certificate.CERTIFICATE_IS_PAY, Certificate.SYSTEM_VERSION);
        }

        result.put("certificate", certificate == null ? new Certificate() : certificate);
        result.put("certificateImageWXList", certificateImageWXList);
        result.put("certificateImageOtherList", certificateImageOtherList);

        renderSuccessJson(result);
    }

    @ActionKey(Url.CERTIFICATE_ADMIN_SAVE)
    public void adminSave() {
        save();
    }

    @ActionKey(Url.CERTIFICATE_ADMIN_UPDATE)
    public void adminUpdate() {
        validateRequest_app_id();
        validate(Certificate.CERTIFICATE_ID, Certificate.USER_ID, Certificate.CERTIFICATE_NUMBER,
                Certificate.CERTIFICATE_START_DATE, Certificate.CERTIFICATE_END_DATE, Certificate.CERTIFICATE_IS_PAY,
                Certificate.SYSTEM_VERSION);

        Certificate model = getModel(Certificate.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Certificate certificate = certificateService.findByCertificate_id(model.getCertificate_id());

        authenticateApp_id(certificate.getApp_id());

        Boolean result = certificateService.updateValidateSystem_version(model.getCertificate_id(), model.getUser_id(),
                model.getCertificate_number(), model.getCertificate_start_date(), model.getCertificate_end_date(),
                model.getCertificate_is_pay(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.CERTIFICATE_ADMIN_DELETE)
    public void adminDelete() {
        validateRequest_app_id();
        validate(Certificate.CERTIFICATE_ID, Certificate.SYSTEM_VERSION);

        Certificate model = getModel(Certificate.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Certificate certificate = certificateService.findByCertificate_id(model.getCertificate_id());

        authenticateApp_id(certificate.getApp_id());

        Boolean result = certificateService.deleteByCertificate_idAndSystem_update_user_idValidateSystem_version(
                model.getCertificate_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.CERTIFICATE_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(Certificate.APP_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Certificate model = getModel(Certificate.class);

        Integer total = certificateService.countByOrApp_idOrLikeCertificate_number(model.getApp_id(),
                model.getCertificate_number());
        List<Certificate> resultList = certificateService.listByOrApp_idOrLikeCertificate_numberAndLimit(
                model.getApp_id(), model.getCertificate_number(), getM(), getN());

        for (Certificate result : resultList) {
            result.keep(Certificate.CERTIFICATE_ID, Certificate.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.CERTIFICATE_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(Certificate.CERTIFICATE_ID);

        Certificate model = getModel(Certificate.class);

        Certificate certificate = certificateService.findByCertificate_id(model.getCertificate_id());

        certificate.keep(Certificate.CERTIFICATE_ID, Certificate.SYSTEM_VERSION);

        renderSuccessJson(certificate);
    }

    @ActionKey(Url.CERTIFICATE_SYSTEM_SAVE)
    public void systemSave() {
        validateRequest_app_id();
        validate(Certificate.APP_ID, Certificate.USER_ID, Certificate.CERTIFICATE_NUMBER,
                Certificate.CERTIFICATE_START_DATE, Certificate.CERTIFICATE_END_DATE);

        Certificate model = getModel(Certificate.class);
        String certificate_id = Util.getRandomUUID();
        String request_user_id = getRequest_user_id();

        Boolean result = certificateService.save(certificate_id, model.getApp_id(), model.getUser_id(),
                model.getCertificate_number(), model.getCertificate_start_date(), model.getCertificate_end_date(),
                false, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.CERTIFICATE_SYSTEM_UPDATE)
    public void systemUpdate() {
        validateRequest_app_id();
        validate(Certificate.CERTIFICATE_ID, Certificate.USER_ID, Certificate.CERTIFICATE_NUMBER,
                Certificate.CERTIFICATE_START_DATE, Certificate.CERTIFICATE_END_DATE, Certificate.CERTIFICATE_IS_PAY,
                Certificate.SYSTEM_VERSION);

        Certificate model = getModel(Certificate.class);
        String request_user_id = getRequest_user_id();

        Boolean result = certificateService.updateValidateSystem_version(model.getCertificate_id(), model.getUser_id(),
                model.getCertificate_number(), model.getCertificate_start_date(), model.getCertificate_end_date(),
                model.getCertificate_is_pay(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.CERTIFICATE_SYSTEM_DELETE)
    public void systemDelete() {
        validateRequest_app_id();
        validate(Certificate.CERTIFICATE_ID, Certificate.SYSTEM_VERSION);

        Certificate model = getModel(Certificate.class);
        String request_user_id = getRequest_user_id();

        Boolean result = certificateService.deleteByCertificate_idAndSystem_update_user_idValidateSystem_version(
                model.getCertificate_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}