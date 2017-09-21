package com.nowui.chuangshi.controller;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.file.model.File;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.FeijiuRecommendProduct;
import com.nowui.chuangshi.service.FeijiuRecommendProductService;
import com.nowui.chuangshi.util.Util;
import com.nowui.chuangshi.util.ValidateUtil;

import java.util.List;

public class FeijiuRecommendProductController extends Controller {

    private final FeijiuRecommendProductService feijiuRecommendProductService = new FeijiuRecommendProductService();

    @ActionKey(Url.FEIJIU_RECOMMEND_PRODUCT_LIST)
    public void list() {
        validateRequest_app_id();

        String request_app_id = getRequest_app_id();

        authenticateRequest_app_idAndRequest_user_id();

        List<FeijiuRecommendProduct> resultList = feijiuRecommendProductService.listByApp_id(request_app_id);

        for (FeijiuRecommendProduct result : resultList) {
            if (!ValidateUtil.isNullOrEmpty(result.getProduct_image())) {
                File file = FileService.instance.find(result.getProduct_image());

                result.setProduct_image(file.getFile_path());
            }

            result.keep(FeijiuRecommendProduct.PRODUCT_ID, FeijiuRecommendProduct.PRODUCT_NAME, FeijiuRecommendProduct.PRODUCT_IMAGE, FeijiuRecommendProduct.PRODUCT_LINK, FeijiuRecommendProduct.PRODUCT_CONTENT, FeijiuRecommendProduct.SYSTEM_VERSION);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.FEIJIU_RECOMMEND_PRODUCT_FIND)
    public void find() {
        validateRequest_app_id();
        validate(FeijiuRecommendProduct.PRODUCT_ID);

        FeijiuRecommendProduct model = getModel(FeijiuRecommendProduct.class);

        authenticateRequest_app_idAndRequest_user_id();

        FeijiuRecommendProduct feijiu_recommend_product = feijiuRecommendProductService.findByProduct_id(model.getProduct_id());

        authenticateApp_id(feijiu_recommend_product.getApp_id());
        authenticateSystem_create_user_id(feijiu_recommend_product.getSystem_create_user_id());

        feijiu_recommend_product.keep(FeijiuRecommendProduct.PRODUCT_ID, FeijiuRecommendProduct.SYSTEM_VERSION);

        renderSuccessJson(feijiu_recommend_product);
    }

    @ActionKey(Url.FEIJIU_RECOMMEND_PRODUCT_SAVE)
    public void save() {
        validateRequest_app_id();
        validate(FeijiuRecommendProduct.PRODUCT_NAME, FeijiuRecommendProduct.PRODUCT_IMAGE, FeijiuRecommendProduct.PRODUCT_LINK, FeijiuRecommendProduct.PRODUCT_CONTENT);

        FeijiuRecommendProduct model = getModel(FeijiuRecommendProduct.class);
        String product_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Boolean result = feijiuRecommendProductService.save(product_id, request_app_id, model.getProduct_name(), model.getProduct_image(), model.getProduct_link(), model.getProduct_content(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.FEIJIU_RECOMMEND_PRODUCT_UPDATE)
    public void update() {
        validateRequest_app_id();
        validate(FeijiuRecommendProduct.PRODUCT_ID, FeijiuRecommendProduct.PRODUCT_NAME, FeijiuRecommendProduct.PRODUCT_IMAGE, FeijiuRecommendProduct.PRODUCT_LINK, FeijiuRecommendProduct.PRODUCT_CONTENT, FeijiuRecommendProduct.SYSTEM_VERSION);

        FeijiuRecommendProduct model = getModel(FeijiuRecommendProduct.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        FeijiuRecommendProduct feijiu_recommend_product = feijiuRecommendProductService.findByProduct_id(model.getProduct_id());

        authenticateApp_id(feijiu_recommend_product.getApp_id());
        authenticateSystem_create_user_id(feijiu_recommend_product.getSystem_create_user_id());

        Boolean result = feijiuRecommendProductService.updateValidateSystem_version(model.getProduct_id(), model.getProduct_name(), model.getProduct_image(), model.getProduct_link(), model.getProduct_content(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.FEIJIU_RECOMMEND_PRODUCT_DELETE)
    public void delete() {
        validateRequest_app_id();
        validate(FeijiuRecommendProduct.PRODUCT_ID, FeijiuRecommendProduct.SYSTEM_VERSION);

        FeijiuRecommendProduct model = getModel(FeijiuRecommendProduct.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        FeijiuRecommendProduct feijiu_recommend_product = feijiuRecommendProductService.findByProduct_id(model.getProduct_id());

        authenticateApp_id(feijiu_recommend_product.getApp_id());
        authenticateSystem_create_user_id(feijiu_recommend_product.getSystem_create_user_id());

        Boolean result = feijiuRecommendProductService.deleteByProduct_idAndSystem_update_user_idValidateSystem_version(model.getProduct_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.FEIJIU_RECOMMEND_PRODUCT_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        String request_app_id = getRequest_app_id();

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = feijiuRecommendProductService.countByApp_id(request_app_id);
        List<FeijiuRecommendProduct> resultList = feijiuRecommendProductService.listByApp_idAndLimit(request_app_id, getM(), getN());

        for (FeijiuRecommendProduct result : resultList) {
            result.keep(FeijiuRecommendProduct.PRODUCT_ID, FeijiuRecommendProduct.PRODUCT_NAME, FeijiuRecommendProduct.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.FEIJIU_RECOMMEND_PRODUCT_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(FeijiuRecommendProduct.PRODUCT_ID);

        FeijiuRecommendProduct model = getModel(FeijiuRecommendProduct.class);

        authenticateRequest_app_idAndRequest_user_id();

        FeijiuRecommendProduct feijiu_recommend_product = feijiuRecommendProductService.findByProduct_id(model.getProduct_id());

        authenticateApp_id(feijiu_recommend_product.getApp_id());

        feijiu_recommend_product.keep(FeijiuRecommendProduct.PRODUCT_ID, FeijiuRecommendProduct.PRODUCT_NAME, FeijiuRecommendProduct.PRODUCT_IMAGE, FeijiuRecommendProduct.PRODUCT_LINK, FeijiuRecommendProduct.PRODUCT_CONTENT, FeijiuRecommendProduct.SYSTEM_VERSION);

        if (ValidateUtil.isNullOrEmpty(feijiu_recommend_product.getProduct_image())) {
            feijiu_recommend_product.put(FeijiuRecommendProduct.PRODUCT_IMAGE_FILE, "");
        } else {
            File file = FileService.instance.find(feijiu_recommend_product.getProduct_image());
            feijiu_recommend_product.put(FeijiuRecommendProduct.PRODUCT_IMAGE_FILE, file.keep(File.FILE_ID, File.FILE_PATH));
        }

        renderSuccessJson(feijiu_recommend_product);
    }

    @ActionKey(Url.FEIJIU_RECOMMEND_PRODUCT_ADMIN_SAVE)
    public void adminSave() {
        save();
    }

    @ActionKey(Url.FEIJIU_RECOMMEND_PRODUCT_ADMIN_UPDATE)
    public void adminUpdate() {
        validateRequest_app_id();
        validate(FeijiuRecommendProduct.PRODUCT_ID, FeijiuRecommendProduct.PRODUCT_NAME, FeijiuRecommendProduct.PRODUCT_IMAGE, FeijiuRecommendProduct.PRODUCT_LINK, FeijiuRecommendProduct.PRODUCT_CONTENT, FeijiuRecommendProduct.SYSTEM_VERSION);

        FeijiuRecommendProduct model = getModel(FeijiuRecommendProduct.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        FeijiuRecommendProduct feijiu_recommend_product = feijiuRecommendProductService.findByProduct_id(model.getProduct_id());

        authenticateApp_id(feijiu_recommend_product.getApp_id());

        Boolean result = feijiuRecommendProductService.updateValidateSystem_version(model.getProduct_id(), model.getProduct_name(), model.getProduct_image(), model.getProduct_link(), model.getProduct_content(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.FEIJIU_RECOMMEND_PRODUCT_ADMIN_DELETE)
    public void adminDelete() {
        validateRequest_app_id();
        validate(FeijiuRecommendProduct.PRODUCT_ID, FeijiuRecommendProduct.SYSTEM_VERSION);

        FeijiuRecommendProduct model = getModel(FeijiuRecommendProduct.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        FeijiuRecommendProduct feijiu_recommend_product = feijiuRecommendProductService.findByProduct_id(model.getProduct_id());
        authenticateApp_id(feijiu_recommend_product.getApp_id());

        Boolean result = feijiuRecommendProductService.deleteByProduct_idAndSystem_update_user_idValidateSystem_version(model.getProduct_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.FEIJIU_RECOMMEND_PRODUCT_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(FeijiuRecommendProduct.APP_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        FeijiuRecommendProduct model = getModel(FeijiuRecommendProduct.class);

        Integer total = feijiuRecommendProductService.countByOrApp_id(model.getApp_id());
        List<FeijiuRecommendProduct> resultList = feijiuRecommendProductService.listByOrApp_idAndLimit(model.getApp_id(), getM(), getN());

        for (FeijiuRecommendProduct result : resultList) {
            result.keep(FeijiuRecommendProduct.PRODUCT_ID, FeijiuRecommendProduct.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.FEIJIU_RECOMMEND_PRODUCT_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(FeijiuRecommendProduct.PRODUCT_ID);

        FeijiuRecommendProduct model = getModel(FeijiuRecommendProduct.class);

        FeijiuRecommendProduct feijiu_recommend_product = feijiuRecommendProductService.findByProduct_id(model.getProduct_id());

        feijiu_recommend_product.keep(FeijiuRecommendProduct.PRODUCT_ID, FeijiuRecommendProduct.PRODUCT_NAME, FeijiuRecommendProduct.PRODUCT_IMAGE, FeijiuRecommendProduct.PRODUCT_LINK, FeijiuRecommendProduct.PRODUCT_CONTENT, FeijiuRecommendProduct.SYSTEM_VERSION);

        if (ValidateUtil.isNullOrEmpty(feijiu_recommend_product.getProduct_image())) {
            feijiu_recommend_product.put(FeijiuRecommendProduct.PRODUCT_IMAGE_FILE, "");
        } else {
            File file = FileService.instance.find(feijiu_recommend_product.getProduct_image());
            feijiu_recommend_product.put(FeijiuRecommendProduct.PRODUCT_IMAGE_FILE, file.keep(File.FILE_ID, File.FILE_PATH));
        }

        renderSuccessJson(feijiu_recommend_product);
    }

    @ActionKey(Url.FEIJIU_RECOMMEND_PRODUCT_SYSTEM_SAVE)
    public void systemSave() {
        validateRequest_app_id();
        validate(FeijiuRecommendProduct.APP_ID, FeijiuRecommendProduct.PRODUCT_NAME, FeijiuRecommendProduct.PRODUCT_IMAGE, FeijiuRecommendProduct.PRODUCT_LINK, FeijiuRecommendProduct.PRODUCT_CONTENT);

        authenticateRequest_app_idAndRequest_user_id();

        FeijiuRecommendProduct model = getModel(FeijiuRecommendProduct.class);
        String product_id = Util.getRandomUUID();
        String request_user_id = getRequest_user_id();

        Boolean result = feijiuRecommendProductService.save(product_id, model.getApp_id(), model.getProduct_name(), model.getProduct_image(), model.getProduct_link(), model.getProduct_content(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.FEIJIU_RECOMMEND_PRODUCT_SYSTEM_UPDATE)
    public void systemUpdate() {
        validateRequest_app_id();
        validate(FeijiuRecommendProduct.PRODUCT_ID, FeijiuRecommendProduct.PRODUCT_NAME, FeijiuRecommendProduct.PRODUCT_IMAGE, FeijiuRecommendProduct.PRODUCT_LINK, FeijiuRecommendProduct.PRODUCT_CONTENT, FeijiuRecommendProduct.SYSTEM_VERSION);

        FeijiuRecommendProduct model = getModel(FeijiuRecommendProduct.class);
        String request_user_id = getRequest_user_id();

        Boolean result = feijiuRecommendProductService.updateValidateSystem_version(model.getProduct_id(), model.getProduct_name(), model.getProduct_image(), model.getProduct_link(), model.getProduct_content(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.FEIJIU_RECOMMEND_PRODUCT_SYSTEM_DELETE)
    public void systemDelete() {
        validateRequest_app_id();
        validate(FeijiuRecommendProduct.PRODUCT_ID, FeijiuRecommendProduct.SYSTEM_VERSION);

        FeijiuRecommendProduct model = getModel(FeijiuRecommendProduct.class);
        String request_user_id = getRequest_user_id();

        Boolean result = feijiuRecommendProductService.deleteByProduct_idAndSystem_update_user_idValidateSystem_version(model.getProduct_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}