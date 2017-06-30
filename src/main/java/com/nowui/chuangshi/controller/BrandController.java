package com.nowui.chuangshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.Brand;
import com.nowui.chuangshi.service.BrandService;
import com.nowui.chuangshi.util.Util;

import java.util.List;

public class BrandController extends Controller {

    private final BrandService brandService = new BrandService();

    @ActionKey(Url.BRAND_LIST)
    public void list() {
        validateRequest_app_id();
        validate(Constant.PAGE_SIZE, Constant.FIRST_CREATE_TIME, Constant.LAST_CREATE_TIME);

        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();
        JSONObject jsonObject = getParameterJSONObject();

        authenticateRequest_app_idAndRequest_user_id();

        List<Brand> resultList = brandService.listByApp_idAndSystem_create_timeAndLimit(request_app_id, jsonObject.getDate(Constant.LAST_CREATE_TIME), 0, getN(), request_app_id, request_http_id, request_user_id);

        for (Brand result : resultList) {
            result.keep(Brand.BRAND_ID, Brand.SYSTEM_VERSION);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.BRAND_FIND)
    public void find() {
        validateRequest_app_id();
        validate(Brand.BRAND_ID);

        Brand model = getModel(Brand.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Brand brand = brandService.findByBrand_id(model.getBrand_id(), request_app_id, request_http_id, request_user_id);

        authenticateApp_id(brand.getApp_id());
        authenticateSystem_create_user_id(brand.getSystem_create_user_id());

        brand.keep(Brand.BRAND_ID, Brand.SYSTEM_VERSION);

        renderSuccessJson(brand);
    }

    @ActionKey(Url.BRAND_SAVE)
    public void save() {
        validateRequest_app_id();
        validate(Brand.CATEGORY_ID, Brand.BRAND_NAME, Brand.BRAND_IMAGE, Brand.BRAND_CONTENT);

        Brand model = getModel(Brand.class);
        String brand_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Boolean result = brandService.save(brand_id, request_app_id, model.getCategory_id(), model.getBrand_name(), model.getBrand_image(), model.getBrand_content(), request_user_id, request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.BRAND_UPDATE)
    public void update() {
        validateRequest_app_id();
        validate(Brand.BRAND_ID, Brand.CATEGORY_ID, Brand.BRAND_NAME, Brand.BRAND_IMAGE, Brand.BRAND_CONTENT, Brand.SYSTEM_VERSION);

        Brand model = getModel(Brand.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Brand brand = brandService.findByBrand_id(model.getBrand_id(), request_app_id, request_http_id, request_user_id);

        authenticateApp_id(brand.getApp_id());
        authenticateSystem_create_user_id(brand.getSystem_create_user_id());

        Boolean result = brandService.updateValidateSystem_version(model.getBrand_id(), model.getCategory_id(), model.getBrand_name(), model.getBrand_image(), model.getBrand_content(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.BRAND_DELETE)
    public void delete() {
        validateRequest_app_id();
        validate(Brand.BRAND_ID, Brand.SYSTEM_VERSION);

        Brand model = getModel(Brand.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Brand brand = brandService.findByBrand_id(model.getBrand_id(), request_app_id, request_http_id, request_user_id);

        authenticateApp_id(brand.getApp_id());
        authenticateSystem_create_user_id(brand.getSystem_create_user_id());

        Boolean result = brandService.deleteByBrand_idAndSystem_update_user_idValidateSystem_version(model.getBrand_id(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.BRAND_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(Brand.BRAND_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Brand model = getModel(Brand.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = brandService.countByApp_idOrLikeBrand_name(request_app_id, model.getBrand_name(), request_app_id, request_http_id, request_user_id);
        List<Brand> resultList = brandService.listByApp_idOrLikeBrand_nameAndLimit(request_app_id, model.getBrand_name(), getM(), getN(), request_app_id, request_http_id, request_user_id);

        for (Brand result : resultList) {
            result.keep(Brand.BRAND_ID, Brand.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.BRAND_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(Brand.BRAND_ID);

        Brand model = getModel(Brand.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Brand brand = brandService.findByBrand_id(model.getBrand_id(), request_app_id, request_http_id, request_user_id);

        authenticateApp_id(brand.getApp_id());

        brand.keep(Brand.BRAND_ID, Brand.SYSTEM_VERSION);

        renderSuccessJson(brand);
    }

    @ActionKey(Url.BRAND_ADMIN_SAVE)
    public void adminSave() {
        save();
    }

    @ActionKey(Url.BRAND_ADMIN_UPDATE)
    public void adminUpdate() {
        validateRequest_app_id();
        validate(Brand.BRAND_ID, Brand.CATEGORY_ID, Brand.BRAND_NAME, Brand.BRAND_IMAGE, Brand.BRAND_CONTENT, Brand.SYSTEM_VERSION);

        Brand model = getModel(Brand.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Brand brand = brandService.findByBrand_id(model.getBrand_id(), request_app_id, request_http_id, request_user_id);

        authenticateApp_id(brand.getApp_id());

        Boolean result = brandService.updateValidateSystem_version(model.getBrand_id(), model.getCategory_id(), model.getBrand_name(), model.getBrand_image(), model.getBrand_content(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.BRAND_ADMIN_DELETE)
    public void adminDelete() {
        validateRequest_app_id();
        validate(Brand.BRAND_ID, Brand.SYSTEM_VERSION);

        Brand model = getModel(Brand.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Brand brand = brandService.findByBrand_id(model.getBrand_id(), request_app_id, request_http_id, request_user_id);

        authenticateApp_id(brand.getApp_id());

        Boolean result = brandService.deleteByBrand_idAndSystem_update_user_idValidateSystem_version(model.getBrand_id(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.BRAND_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(Brand.APP_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Brand model = getModel(Brand.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        Integer total = brandService.countByOrApp_idOrLikeBrand_name(model.getApp_id(), model.getBrand_name(), request_app_id, request_http_id, request_user_id);
        List<Brand> resultList = brandService.listByOrApp_idOrLikeBrand_nameAndLimit(model.getApp_id(), model.getBrand_name(), getM(), getN(), request_app_id, request_http_id, request_user_id);

        for (Brand result : resultList) {
            result.keep(Brand.BRAND_ID, Brand.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.BRAND_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(Brand.BRAND_ID);

        Brand model = getModel(Brand.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        Brand brand = brandService.findByBrand_id(model.getBrand_id(), request_app_id, request_http_id, request_user_id);

        brand.keep(Brand.BRAND_ID, Brand.SYSTEM_VERSION);

        renderSuccessJson(brand);
    }

    @ActionKey(Url.BRAND_SYSTEM_SAVE)
    public void systemSave() {
        validateRequest_app_id();
        validate(Brand.APP_ID, Brand.CATEGORY_ID, Brand.BRAND_NAME, Brand.BRAND_IMAGE, Brand.BRAND_CONTENT);

        Brand model = getModel(Brand.class);
        String brand_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        Boolean result = brandService.save(brand_id, model.getApp_id(), model.getCategory_id(), model.getBrand_name(), model.getBrand_image(), model.getBrand_content(), request_user_id, request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.BRAND_SYSTEM_UPDATE)
    public void systemUpdate() {
        validateRequest_app_id();
        validate(Brand.BRAND_ID, Brand.CATEGORY_ID, Brand.BRAND_NAME, Brand.BRAND_IMAGE, Brand.BRAND_CONTENT, Brand.SYSTEM_VERSION);

        Brand model = getModel(Brand.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        Boolean result = brandService.updateValidateSystem_version(model.getBrand_id(), model.getCategory_id(), model.getBrand_name(), model.getBrand_image(), model.getBrand_content(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.BRAND_SYSTEM_DELETE)
    public void systemDelete() {
        validateRequest_app_id();
        validate(Brand.BRAND_ID, Brand.SYSTEM_VERSION);

        Brand model = getModel(Brand.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        Boolean result = brandService.deleteByBrand_idAndSystem_update_user_idValidateSystem_version(model.getBrand_id(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

}