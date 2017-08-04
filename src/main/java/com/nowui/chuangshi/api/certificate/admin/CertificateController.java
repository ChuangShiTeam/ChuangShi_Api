package com.nowui.chuangshi.api.certificate.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.certificate.model.Certificate;
import com.nowui.chuangshi.api.certificate.service.CertificateService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/certificate")
public class CertificateController extends Controller {

    private final CertificateService certificateService = CertificateService.me;

    @ActionKey("/admin/certificate/list")
    public void list() {
        validateRequest(Certificate.USER_ID, Certificate.CERTIFICATE_NUMBER, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Certificate model = getModel(Certificate.class);
        model.where(Certificate.APP_ID).andEmpty(Certificate.USER_ID).andEmpty(Certificate.CERTIFICATE_NUMBER);

        Integer resultCount = certificateService.count(model);
        List<Certificate> resultList = certificateService.list(model.paginate());

        validateResponse(Certificate.CERTIFICATE_ID, Certificate.USER_ID, Certificate.CERTIFICATE_NUMBER, Certificate.CERTIFICATE_START_DATE, Certificate.CERTIFICATE_END_DATE, Certificate.CERTIFICATE_IS_PAY, Certificate.SYSTEM_VERSION);

        renderSuccessModeListlJson(resultCount, resultList);
    }

    @ActionKey("/admin/certificate/find")
    public void find() {
        validateRequest(Certificate.CERTIFICATE_ID);

        Certificate model = getModel(Certificate.class);
        model.where(Certificate.CERTIFICATE_ID);

        Certificate result = certificateService.find(model);

        validateResponse(Certificate.USER_ID, Certificate.CERTIFICATE_NUMBER, Certificate.CERTIFICATE_START_DATE, Certificate.CERTIFICATE_END_DATE, Certificate.CERTIFICATE_IS_PAY, Certificate.SYSTEM_VERSION);

        renderSuccessModelJson(result);
    }

    @ActionKey("/admin/certificate/save")
    public void save() {
        validateRequest(Certificate.USER_ID, Certificate.CERTIFICATE_NUMBER, Certificate.CERTIFICATE_START_DATE, Certificate.CERTIFICATE_END_DATE, Certificate.CERTIFICATE_IS_PAY);

        Certificate model = getModel(Certificate.class);
        model.setCertificate_id(Util.getRandomUUID());

        Boolean result = certificateService.save(model);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/certificate/update")
    public void update() {
        validateRequest(Certificate.CERTIFICATE_ID, Certificate.USER_ID, Certificate.CERTIFICATE_NUMBER, Certificate.CERTIFICATE_START_DATE, Certificate.CERTIFICATE_END_DATE, Certificate.CERTIFICATE_IS_PAY, Certificate.SYSTEM_VERSION);

        Certificate model = getModel(Certificate.class);
        model.where(model.CERTIFICATE_ID).and(Certificate.SYSTEM_VERSION);

        Boolean result = certificateService.update(model);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/certificate/delete")
    public void delete() {
        validateRequest(Certificate.CERTIFICATE_ID, Certificate.SYSTEM_VERSION);

        Certificate model = getModel(Certificate.class);
        model.where(model.CERTIFICATE_ID).and(Certificate.SYSTEM_VERSION);

        Boolean result = certificateService.delete(model);

        renderSuccessJson(result);
    }

}