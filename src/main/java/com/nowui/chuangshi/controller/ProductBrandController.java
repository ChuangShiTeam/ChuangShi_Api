package com.nowui.chuangshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.ProductBrand;
import com.nowui.chuangshi.service.ProductBrandService;
import com.nowui.chuangshi.util.Util;

import java.util.List;

public class ProductBrandController extends Controller {

    private final ProductBrandService productBrandService = new ProductBrandService();

    @ActionKey(Url.PRODUCT_BRAND_LIST)
    public void list() {
        validateRequest_app_id();
        validate(Constant.PAGE_SIZE, Constant.FIRST_CREATE_TIME, Constant.LAST_CREATE_TIME);

        String request_app_id = getRequest_app_id();
        JSONObject jsonObject = getParameterJSONObject();

        authenticateRequest_app_idAndRequest_user_id();

        List<ProductBrand> resultList = productBrandService.listByApp_idAndSystem_create_timeAndLimit(request_app_id, jsonObject.getDate(Constant.LAST_CREATE_TIME), 0, getN());

        for (ProductBrand result : resultList) {
            result.keep(ProductBrand.PRODUCT_BRAND_ID, ProductBrand.SYSTEM_VERSION);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.PRODUCT_BRAND_FIND)
    public void find() {
        validateRequest_app_id();
        validate(ProductBrand.PRODUCT_BRAND_ID);

        ProductBrand model = getModel(ProductBrand.class);

        authenticateRequest_app_idAndRequest_user_id();

        ProductBrand product_brand = productBrandService.findByProduct_brand_id(model.getProduct_brand_id());

        authenticateApp_id(product_brand.getApp_id());
        authenticateSystem_create_user_id(product_brand.getSystem_create_user_id());

        product_brand.keep(ProductBrand.PRODUCT_BRAND_ID, ProductBrand.SYSTEM_VERSION);

        renderSuccessJson(product_brand);
    }

    @ActionKey(Url.PRODUCT_BRAND_SAVE)
    public void save() {
        validateRequest_app_id();
        validate(ProductBrand.PRODUCT_BRAND_NAME, ProductBrand.PRODUCT_BRAND_IMAGE, ProductBrand.PRODUCT_BRAND_CONTENT);

        ProductBrand model = getModel(ProductBrand.class);
        String product_brand_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Boolean result = productBrandService.save(product_brand_id, request_app_id, model.getProduct_brand_name(), model.getProduct_brand_image(), model.getProduct_brand_content(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.PRODUCT_BRAND_UPDATE)
    public void update() {
        validateRequest_app_id();
        validate(ProductBrand.PRODUCT_BRAND_ID, ProductBrand.PRODUCT_BRAND_NAME, ProductBrand.PRODUCT_BRAND_IMAGE, ProductBrand.PRODUCT_BRAND_CONTENT, ProductBrand.SYSTEM_VERSION);

        ProductBrand model = getModel(ProductBrand.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        ProductBrand product_brand = productBrandService.findByProduct_brand_id(model.getProduct_brand_id());

        authenticateApp_id(product_brand.getApp_id());
        authenticateSystem_create_user_id(product_brand.getSystem_create_user_id());

        Boolean result = productBrandService.updateValidateSystem_version(model.getProduct_brand_id(), model.getProduct_brand_name(), model.getProduct_brand_image(), model.getProduct_brand_content(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.PRODUCT_BRAND_DELETE)
    public void delete() {
        validateRequest_app_id();
        validate(ProductBrand.PRODUCT_BRAND_ID, ProductBrand.SYSTEM_VERSION);

        ProductBrand model = getModel(ProductBrand.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        ProductBrand product_brand = productBrandService.findByProduct_brand_id(model.getProduct_brand_id());

        authenticateApp_id(product_brand.getApp_id());
        authenticateSystem_create_user_id(product_brand.getSystem_create_user_id());

        Boolean result = productBrandService.deleteByProduct_brand_idAndSystem_update_user_idValidateSystem_version(model.getProduct_brand_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.PRODUCT_BRAND_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(ProductBrand.PRODUCT_BRAND_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        ProductBrand model = getModel(ProductBrand.class);
        String request_app_id = getRequest_app_id();

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = productBrandService.countByApp_idOrLikeProduct_brand_name(request_app_id, model.getProduct_brand_name());
        List<ProductBrand> resultList = productBrandService.listByApp_idOrLikeProduct_brand_nameAndLimit(request_app_id, model.getProduct_brand_name(), getM(), getN());

        for (ProductBrand result : resultList) {
            result.keep(ProductBrand.PRODUCT_BRAND_ID, ProductBrand.PRODUCT_BRAND_NAME, ProductBrand.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.PRODUCT_BRAND_ADMIN_ALL_LIST)
    public void adminAllList() {
        validateRequest_app_id();

        String request_app_id = getRequest_app_id();

        authenticateRequest_app_idAndRequest_user_id();

        List<ProductBrand> resultList = productBrandService.listByApp_id(request_app_id);

        for (ProductBrand result : resultList) {
            result.keep(ProductBrand.PRODUCT_BRAND_ID, ProductBrand.PRODUCT_BRAND_NAME);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.PRODUCT_BRAND_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(ProductBrand.PRODUCT_BRAND_ID);

        ProductBrand model = getModel(ProductBrand.class);

        authenticateRequest_app_idAndRequest_user_id();

        ProductBrand product_brand = productBrandService.findByProduct_brand_id(model.getProduct_brand_id());

        authenticateApp_id(product_brand.getApp_id());

        product_brand.keep(ProductBrand.PRODUCT_BRAND_ID, ProductBrand.PRODUCT_BRAND_NAME, ProductBrand.PRODUCT_BRAND_IMAGE, ProductBrand.PRODUCT_BRAND_CONTENT, ProductBrand.SYSTEM_VERSION);

        renderSuccessJson(product_brand);
    }

    @ActionKey(Url.PRODUCT_BRAND_ADMIN_SAVE)
    public void adminSave() {
        save();
    }

    @ActionKey(Url.PRODUCT_BRAND_ADMIN_UPDATE)
    public void adminUpdate() {
        validateRequest_app_id();
        validate(ProductBrand.PRODUCT_BRAND_ID, ProductBrand.PRODUCT_BRAND_NAME, ProductBrand.PRODUCT_BRAND_IMAGE, ProductBrand.PRODUCT_BRAND_CONTENT, ProductBrand.SYSTEM_VERSION);

        ProductBrand model = getModel(ProductBrand.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        ProductBrand product_brand = productBrandService.findByProduct_brand_id(model.getProduct_brand_id());

        authenticateApp_id(product_brand.getApp_id());

        Boolean result = productBrandService.updateValidateSystem_version(model.getProduct_brand_id(), model.getProduct_brand_name(), model.getProduct_brand_image(), model.getProduct_brand_content(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.PRODUCT_BRAND_ADMIN_DELETE)
    public void adminDelete() {
        validateRequest_app_id();
        validate(ProductBrand.PRODUCT_BRAND_ID, ProductBrand.SYSTEM_VERSION);

        ProductBrand model = getModel(ProductBrand.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        ProductBrand product_brand = productBrandService.findByProduct_brand_id(model.getProduct_brand_id());

        authenticateApp_id(product_brand.getApp_id());

        Boolean result = productBrandService.deleteByProduct_brand_idAndSystem_update_user_idValidateSystem_version(model.getProduct_brand_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.PRODUCT_BRAND_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(ProductBrand.APP_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        ProductBrand model = getModel(ProductBrand.class);

        Integer total = productBrandService.countByOrApp_idOrLikeProduct_brand_name(model.getApp_id(), model.getProduct_brand_name());
        List<ProductBrand> resultList = productBrandService.listByOrApp_idOrLikeProduct_brand_nameAndLimit(model.getApp_id(), model.getProduct_brand_name(), getM(), getN());

        for (ProductBrand result : resultList) {
            result.keep(ProductBrand.PRODUCT_BRAND_ID, ProductBrand.PRODUCT_BRAND_NAME, ProductBrand.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.PRODUCT_BRAND_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(ProductBrand.PRODUCT_BRAND_ID);

        ProductBrand model = getModel(ProductBrand.class);

        ProductBrand product_brand = productBrandService.findByProduct_brand_id(model.getProduct_brand_id());

        product_brand.keep(ProductBrand.PRODUCT_BRAND_ID, ProductBrand.PRODUCT_BRAND_NAME, ProductBrand.PRODUCT_BRAND_IMAGE, ProductBrand.PRODUCT_BRAND_CONTENT, ProductBrand.SYSTEM_VERSION);

        renderSuccessJson(product_brand);
    }

    @ActionKey(Url.PRODUCT_BRAND_SYSTEM_SAVE)
    public void systemSave() {
        validateRequest_app_id();
        validate(ProductBrand.APP_ID, ProductBrand.PRODUCT_BRAND_NAME, ProductBrand.PRODUCT_BRAND_IMAGE, ProductBrand.PRODUCT_BRAND_CONTENT);

        ProductBrand model = getModel(ProductBrand.class);
        String product_brand_id = Util.getRandomUUID();
        String request_user_id = getRequest_user_id();

        Boolean result = productBrandService.save(product_brand_id, model.getApp_id(), model.getProduct_brand_name(), model.getProduct_brand_image(), model.getProduct_brand_content(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.PRODUCT_BRAND_SYSTEM_UPDATE)
    public void systemUpdate() {
        validateRequest_app_id();
        validate(ProductBrand.PRODUCT_BRAND_ID, ProductBrand.PRODUCT_BRAND_NAME, ProductBrand.PRODUCT_BRAND_IMAGE, ProductBrand.PRODUCT_BRAND_CONTENT, ProductBrand.SYSTEM_VERSION);

        ProductBrand model = getModel(ProductBrand.class);
        String request_user_id = getRequest_user_id();

        Boolean result = productBrandService.updateValidateSystem_version(model.getProduct_brand_id(), model.getProduct_brand_name(), model.getProduct_brand_image(), model.getProduct_brand_content(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.PRODUCT_BRAND_SYSTEM_DELETE)
    public void systemDelete() {
        validateRequest_app_id();
        validate(ProductBrand.PRODUCT_BRAND_ID, ProductBrand.SYSTEM_VERSION);

        ProductBrand model = getModel(ProductBrand.class);
        String request_user_id = getRequest_user_id();

        Boolean result = productBrandService.deleteByProduct_brand_idAndSystem_update_user_idValidateSystem_version(model.getProduct_brand_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}