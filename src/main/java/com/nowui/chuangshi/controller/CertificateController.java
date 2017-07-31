package com.nowui.chuangshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.Certificate;
import com.nowui.chuangshi.service.CertificateService;
import com.nowui.chuangshi.util.Util;

import java.util.List;

public class CertificateController extends Controller {

    private final CertificateService certificateService = new CertificateService();

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
        validate(Certificate.CERTIFICATE_ID);

        Certificate model = getModel(Certificate.class);

        authenticateRequest_app_idAndRequest_user_id();

        Certificate certificate = certificateService.findByCertificate_id(model.getCertificate_id());

        authenticateApp_id(certificate.getApp_id());
        authenticateSystem_create_user_id(certificate.getSystem_create_user_id());

        certificate.keep(Certificate.CERTIFICATE_ID, Certificate.SYSTEM_VERSION);

        renderSuccessJson(certificate);
    }

    @ActionKey(Url.CERTIFICATE_SAVE)
    public void save() {
        validateRequest_app_id();
        validate(Certificate.USER_ID, Certificate.CERTIFICATE_NUMBER, Certificate.CERTIFICATE_START_DATE,
                Certificate.CERTIFICATE_END_DATE, Certificate.CERTIFICATE_CONTENT, Certificate.CERTIFICATE_FILE);

        Certificate model = getModel(Certificate.class);
        String certificate_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Boolean result = certificateService.save(certificate_id, request_app_id, model.getUser_id(),
                model.getCertificate_number(), model.getCertificate_start_date(), model.getCertificate_end_date(),
                model.getCertificate_content(), model.getCertificate_file(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.CERTIFICATE_UPDATE)
    public void update() {
        validateRequest_app_id();
        validate(Certificate.CERTIFICATE_ID, Certificate.USER_ID, Certificate.CERTIFICATE_NUMBER,
                Certificate.CERTIFICATE_START_DATE, Certificate.CERTIFICATE_END_DATE, Certificate.CERTIFICATE_CONTENT,
                Certificate.CERTIFICATE_FILE, Certificate.SYSTEM_VERSION);

        Certificate model = getModel(Certificate.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Certificate certificate = certificateService.findByCertificate_id(model.getCertificate_id());

        authenticateApp_id(certificate.getApp_id());
        authenticateSystem_create_user_id(certificate.getSystem_create_user_id());

        Boolean result = certificateService.updateValidateSystem_version(model.getCertificate_id(), model.getUser_id(),
                model.getCertificate_number(), model.getCertificate_start_date(), model.getCertificate_end_date(),
                model.getCertificate_content(), model.getCertificate_file(), request_user_id,
                model.getSystem_version());

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
        validate(Certificate.CERTIFICATE_ID);

        Certificate model = getModel(Certificate.class);

        authenticateRequest_app_idAndRequest_user_id();

        Certificate certificate = certificateService.findByCertificate_id(model.getCertificate_id());

        authenticateApp_id(certificate.getApp_id());

        certificate.keep(Certificate.CERTIFICATE_ID, Certificate.SYSTEM_VERSION);

        renderSuccessJson(certificate);
    }

    @ActionKey(Url.CERTIFICATE_ADMIN_SAVE)
    public void adminSave() {
        save();
    }

    @ActionKey(Url.CERTIFICATE_ADMIN_UPDATE)
    public void adminUpdate() {
        validateRequest_app_id();
        validate(Certificate.CERTIFICATE_ID, Certificate.USER_ID, Certificate.CERTIFICATE_NUMBER,
                Certificate.CERTIFICATE_START_DATE, Certificate.CERTIFICATE_END_DATE, Certificate.CERTIFICATE_CONTENT,
                Certificate.CERTIFICATE_FILE, Certificate.SYSTEM_VERSION);

        Certificate model = getModel(Certificate.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Certificate certificate = certificateService.findByCertificate_id(model.getCertificate_id());

        authenticateApp_id(certificate.getApp_id());

        Boolean result = certificateService.updateValidateSystem_version(model.getCertificate_id(), model.getUser_id(),
                model.getCertificate_number(), model.getCertificate_start_date(), model.getCertificate_end_date(),
                model.getCertificate_content(), model.getCertificate_file(), request_user_id,
                model.getSystem_version());

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
                Certificate.CERTIFICATE_START_DATE, Certificate.CERTIFICATE_END_DATE, Certificate.CERTIFICATE_CONTENT,
                Certificate.CERTIFICATE_FILE);

        Certificate model = getModel(Certificate.class);
        String certificate_id = Util.getRandomUUID();
        String request_user_id = getRequest_user_id();

        Boolean result = certificateService.save(certificate_id, model.getApp_id(), model.getUser_id(),
                model.getCertificate_number(), model.getCertificate_start_date(), model.getCertificate_end_date(),
                model.getCertificate_content(), model.getCertificate_file(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.CERTIFICATE_SYSTEM_UPDATE)
    public void systemUpdate() {
        validateRequest_app_id();
        validate(Certificate.CERTIFICATE_ID, Certificate.USER_ID, Certificate.CERTIFICATE_NUMBER,
                Certificate.CERTIFICATE_START_DATE, Certificate.CERTIFICATE_END_DATE, Certificate.CERTIFICATE_CONTENT,
                Certificate.CERTIFICATE_FILE, Certificate.SYSTEM_VERSION);

        Certificate model = getModel(Certificate.class);
        String request_user_id = getRequest_user_id();

        Boolean result = certificateService.updateValidateSystem_version(model.getCertificate_id(), model.getUser_id(),
                model.getCertificate_number(), model.getCertificate_start_date(), model.getCertificate_end_date(),
                model.getCertificate_content(), model.getCertificate_file(), request_user_id,
                model.getSystem_version());

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