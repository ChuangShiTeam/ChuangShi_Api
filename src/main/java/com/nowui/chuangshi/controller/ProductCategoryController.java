package com.nowui.chuangshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.ProductCategory;
import com.nowui.chuangshi.service.ProductCategoryService;
import com.nowui.chuangshi.util.Util;

import java.util.List;
import java.util.Map;

public class ProductCategoryController extends Controller {

    private final ProductCategoryService productCategoryService = new ProductCategoryService();

    @ActionKey(Url.PRODUCT_CATEGORY_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(ProductCategory.PRODUCT_CATEGORY_NAME);

        ProductCategory model = getModel(ProductCategory.class);
        String request_app_id = getRequest_app_id();

        authenticateRequest_app_idAndRequest_user_id();

        List<Map<String, Object>>  resultList = productCategoryService.treeByApp_idOrLikeProduct_category_name(request_app_id, model.getProduct_category_name());

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.PRODUCT_CATEGORY_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(ProductCategory.PRODUCT_CATEGORY_ID);

        ProductCategory model = getModel(ProductCategory.class);

        authenticateRequest_app_idAndRequest_user_id();

        ProductCategory product_category = productCategoryService.findByProduct_category_id(model.getProduct_category_id());

        authenticateApp_id(product_category.getApp_id());

        product_category.keep(ProductCategory.PRODUCT_CATEGORY_ID, ProductCategory.PRODUCT_CATEGORY_PARENT_ID, ProductCategory.PRODUCT_CATEGORY_NAME, ProductCategory.PRODUCT_CATEGORY_SORT, ProductCategory.SYSTEM_VERSION);

        renderSuccessJson(product_category);
    }

    @ActionKey(Url.PRODUCT_CATEGORY_ADMIN_SAVE)
    public void adminSave() {
        validateRequest_app_id();
        validate(ProductCategory.PRODUCT_CATEGORY_PARENT_ID, ProductCategory.PRODUCT_CATEGORY_NAME, ProductCategory.PRODUCT_CATEGORY_SORT);

        ProductCategory model = getModel(ProductCategory.class);
        String product_category_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Boolean result = productCategoryService.save(product_category_id, request_app_id, model.getProduct_category_parent_id(), model.getProduct_category_name(), model.getProduct_category_sort(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.PRODUCT_CATEGORY_ADMIN_UPDATE)
    public void adminUpdate() {
        validateRequest_app_id();
        validate(ProductCategory.PRODUCT_CATEGORY_ID, ProductCategory.PRODUCT_CATEGORY_PARENT_ID, ProductCategory.PRODUCT_CATEGORY_NAME, ProductCategory.PRODUCT_CATEGORY_SORT, ProductCategory.SYSTEM_VERSION);

        ProductCategory model = getModel(ProductCategory.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        ProductCategory product_category = productCategoryService.findByProduct_category_id(model.getProduct_category_id());

        authenticateApp_id(product_category.getApp_id());

        Boolean result = productCategoryService.updateValidateSystem_version(model.getProduct_category_id(), model.getProduct_category_parent_id(), model.getProduct_category_name(), model.getProduct_category_sort(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.PRODUCT_CATEGORY_ADMIN_DELETE)
    public void adminDelete() {
        validateRequest_app_id();
        validate(ProductCategory.PRODUCT_CATEGORY_ID, ProductCategory.SYSTEM_VERSION);

        ProductCategory model = getModel(ProductCategory.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        ProductCategory product_category = productCategoryService.findByProduct_category_id(model.getProduct_category_id());

        authenticateApp_id(product_category.getApp_id());

        Boolean result = productCategoryService.deleteByProduct_category_idAndSystem_update_user_idValidateSystem_version(model.getProduct_category_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.PRODUCT_CATEGORY_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(ProductCategory.APP_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        ProductCategory model = getModel(ProductCategory.class);
        String request_app_id = getRequest_app_id();

        List<Map<String, Object>>  resultList = productCategoryService.treeByOrApp_idOrLikeProduct_category_name(request_app_id, model.getProduct_category_name());

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.PRODUCT_CATEGORY_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(ProductCategory.PRODUCT_CATEGORY_ID);

        ProductCategory model = getModel(ProductCategory.class);

        ProductCategory product_category = productCategoryService.findByProduct_category_id(model.getProduct_category_id());

        product_category.keep(ProductCategory.PRODUCT_CATEGORY_ID, ProductCategory.SYSTEM_VERSION);

        renderSuccessJson(product_category);
    }

    @ActionKey(Url.PRODUCT_CATEGORY_SYSTEM_SAVE)
    public void systemSave() {
        validateRequest_app_id();
        validate(ProductCategory.APP_ID, ProductCategory.PRODUCT_CATEGORY_PARENT_ID, ProductCategory.PRODUCT_CATEGORY_NAME, ProductCategory.PRODUCT_CATEGORY_SORT);

        ProductCategory model = getModel(ProductCategory.class);
        String product_category_id = Util.getRandomUUID();
        String request_user_id = getRequest_user_id();

        Boolean result = productCategoryService.save(product_category_id, model.getApp_id(), model.getProduct_category_parent_id(), model.getProduct_category_name(), model.getProduct_category_sort(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.PRODUCT_CATEGORY_SYSTEM_UPDATE)
    public void systemUpdate() {
        validateRequest_app_id();
        validate(ProductCategory.PRODUCT_CATEGORY_ID, ProductCategory.PRODUCT_CATEGORY_PARENT_ID, ProductCategory.PRODUCT_CATEGORY_NAME, ProductCategory.PRODUCT_CATEGORY_SORT, ProductCategory.SYSTEM_VERSION);

        ProductCategory model = getModel(ProductCategory.class);
        String request_user_id = getRequest_user_id();

        Boolean result = productCategoryService.updateValidateSystem_version(model.getProduct_category_id(), model.getProduct_category_parent_id(), model.getProduct_category_name(), model.getProduct_category_sort(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.PRODUCT_CATEGORY_SYSTEM_DELETE)
    public void systemDelete() {
        validateRequest_app_id();
        validate(ProductCategory.PRODUCT_CATEGORY_ID, ProductCategory.SYSTEM_VERSION);

        ProductCategory model = getModel(ProductCategory.class);
        String request_user_id = getRequest_user_id();

        Boolean result = productCategoryService.deleteByProduct_category_idAndSystem_update_user_idValidateSystem_version(model.getProduct_category_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}