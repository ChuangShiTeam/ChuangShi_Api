package com.nowui.chuangshi.controller;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.CertificateImage;
import com.nowui.chuangshi.service.CertificateImageService;

public class CertificateImageController extends Controller {

    private final CertificateImageService certificateImageService = new CertificateImageService();

    @ActionKey(Url.CERTIFICATE_IMAGE_LIST)
    public void list() {
        validateRequest_app_id();
        validate(CertificateImage.CERTIFICATE_ID);

        String request_app_id = getRequest_app_id();
        JSONObject jsonObject = getParameterJSONObject();

        authenticateRequest_app_idAndRequest_user_id();

        List<CertificateImage> resultList = certificateImageService
                .listByCertificate_id(jsonObject.getString(CertificateImage.CERTIFICATE_ID));

        for (CertificateImage result : resultList) {
            result.keep(CertificateImage.CERTIFICATE_ID, CertificateImage.SYSTEM_VERSION);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.CERTIFICATE_IMAGE_SAVE)
    public void save() {
        validateRequest_app_id();
        validate(CertificateImage.CERTIFICATE_ID, CertificateImage.FILE_ID, CertificateImage.CERTIFICATE_TYPE,
                CertificateImage.CERTIFICATE_CHANNEL_NAME, CertificateImage.CERTIFICATE_CHANNEL_URL,
                CertificateImage.CERTIFICATE_PEOPLE_NAME, CertificateImage.CERTIFICATE_PEOPLE_ID_CARD,
                CertificateImage.CERTIFICATE_PEOPLE_MOBILE, CertificateImage.CERTIFICATE_SHOP_NAME,
                CertificateImage.CERTIFICATE_SHOP_URL, CertificateImage.CERTIFICATE_START_DATE,
                CertificateImage.CERTIFICATE_END_DATE);

        CertificateImage model = getModel(CertificateImage.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Boolean result = certificateImageService.save(model.getCertificate_id(), model.getFile_id(),
                model.getCertificate_type(), model.getCertificate_channel_name(), model.getCertificate_channel_url(),
                model.getCertificate_people_name(), model.getCertificate_people_id_card(),
                model.getCertificate_people_mobile(), model.getCertificate_shop_name(), model.getCertificate_shop_url(),
                model.getCertificate_start_date(), model.getCertificate_end_date(), request_user_id);

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

    @ActionKey(Url.CERTIFICATE_IMAGE_ADMIN_SAVE)
    public void adminSave() {
        save();
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
                model.getCertificate_people_mobile(), model.getCertificate_shop_name(), model.getCertificate_shop_url(),
                model.getCertificate_start_date(), model.getCertificate_end_date(), request_user_id);

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