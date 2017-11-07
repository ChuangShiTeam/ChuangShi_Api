package com.nowui.chuangshi.api.certificate.admin;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.certificate.model.Certificate;
import com.nowui.chuangshi.api.certificate.model.CertificatePay;
import com.nowui.chuangshi.api.certificate.service.CertificateService;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;


@ControllerKey("/admin/certificate")
public class CertificateController extends Controller {

    @ActionKey("/admin/certificate/list")
    public void list() {
        validateRequest(User.USER_NAME, Certificate.CERTIFICATE_NUMBER, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Certificate model = getModel(Certificate.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = CertificateService.instance.adminCount(request_app_id, model.getStr(User.USER_NAME), model.getCertificate_number());
        List<Certificate> resultList = CertificateService.instance.adminList(request_app_id, model.getStr(User.USER_NAME), model.getCertificate_number(), getM(), getN());

        validateResponse(Certificate.CERTIFICATE_ID, Certificate.USER_ID, Certificate.CERTIFICATE_NUMBER, Certificate.CERTIFICATE_START_DATE, Certificate.CERTIFICATE_END_DATE, Certificate.CERTIFICATE_IS_PAY, User.USER_NAME, CertificatePay.CERTIFICATE_AMOUNT, Certificate.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/certificate/find")
    public void find() {
        validateRequest(Certificate.CERTIFICATE_ID);

        Certificate model = getModel(Certificate.class);

        Certificate result = CertificateService.instance.find(model.getCertificate_id());

        validateResponse(Certificate.USER_ID, Certificate.CERTIFICATE_NUMBER, Certificate.CERTIFICATE_START_DATE, Certificate.CERTIFICATE_END_DATE, Certificate.CERTIFICATE_IS_PAY, Certificate.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/certificate/save")
    public void save() {
        validateRequest(Certificate.USER_ID, Certificate.CERTIFICATE_NUMBER, Certificate.CERTIFICATE_START_DATE, Certificate.CERTIFICATE_END_DATE, Certificate.CERTIFICATE_IS_PAY);

        Certificate model = getModel(Certificate.class);
        model.setCertificate_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = CertificateService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/certificate/update")
    public void update() {
        validateRequest(Certificate.CERTIFICATE_ID, Certificate.USER_ID, Certificate.CERTIFICATE_NUMBER, Certificate.CERTIFICATE_START_DATE, Certificate.CERTIFICATE_END_DATE, Certificate.CERTIFICATE_IS_PAY, Certificate.SYSTEM_VERSION);

        Certificate model = getModel(Certificate.class);
        String request_user_id = getRequest_user_id();

        Boolean result = CertificateService.instance.update(model, model.getCertificate_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/certificate/delete")
    public void delete() {
        validateRequest(Certificate.CERTIFICATE_ID, Certificate.SYSTEM_VERSION);

        Certificate model = getModel(Certificate.class);
        String request_user_id = getRequest_user_id();

        Boolean result = CertificateService.instance.delete(model.getCertificate_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}