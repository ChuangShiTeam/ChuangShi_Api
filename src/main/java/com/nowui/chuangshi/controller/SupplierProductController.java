package com.nowui.chuangshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.SupplierProduct;
import com.nowui.chuangshi.service.SupplierProductService;
import com.nowui.chuangshi.util.Util;

import java.util.List;

public class SupplierProductController extends Controller {

    private final SupplierProductService supplierProductService = new SupplierProductService();

    @ActionKey(Url.SUPPLIER_PRODUCT_LIST)
    public void list() {
        validateRequest_app_id();
        validate(Constant.PAGE_SIZE, Constant.FIRST_CREATE_TIME, Constant.LAST_CREATE_TIME);

        String request_app_id = getRequest_app_id();
        JSONObject jsonObject = getParameterJSONObject();

        authenticateRequest_app_idAndRequest_user_id();

        List<SupplierProduct> resultList = supplierProductService.listByApp_idAndSystem_create_timeAndLimit(request_app_id, jsonObject.getDate(Constant.LAST_CREATE_TIME), 0, getN());

        for (SupplierProduct result : resultList) {
            result.keep(SupplierProduct.SUPPLIER_PRODUCT_ID, SupplierProduct.SYSTEM_VERSION);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.SUPPLIER_PRODUCT_FIND)
    public void find() {
        validateRequest_app_id();
        validate(SupplierProduct.SUPPLIER_PRODUCT_ID);

        SupplierProduct model = getModel(SupplierProduct.class);

        authenticateRequest_app_idAndRequest_user_id();

        SupplierProduct supplier_product = supplierProductService.findBySupplier_product_id(model.getSupplier_product_id());

        authenticateApp_id(supplier_product.getApp_id());
        authenticateSystem_create_user_id(supplier_product.getSystem_create_user_id());

        supplier_product.keep(SupplierProduct.SUPPLIER_PRODUCT_ID, SupplierProduct.SYSTEM_VERSION);

        renderSuccessJson(supplier_product);
    }

    @ActionKey(Url.SUPPLIER_PRODUCT_SAVE)
    public void save() {
        validateRequest_app_id();
        validate(SupplierProduct.SUPPLIER_ID, SupplierProduct.PRODUCT_ID);

        SupplierProduct model = getModel(SupplierProduct.class);
        String supplier_product_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Boolean result = supplierProductService.save(supplier_product_id, request_app_id, model.getSupplier_id(), model.getProduct_id(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.SUPPLIER_PRODUCT_UPDATE)
    public void update() {
        validateRequest_app_id();
        validate(SupplierProduct.SUPPLIER_ID, SupplierProduct.PRODUCT_ID, SupplierProduct.SYSTEM_VERSION);

        SupplierProduct model = getModel(SupplierProduct.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        SupplierProduct supplier_product = supplierProductService.findBySupplier_product_id(model.getSupplier_product_id());

        authenticateApp_id(supplier_product.getApp_id());
        authenticateSystem_create_user_id(supplier_product.getSystem_create_user_id());

        Boolean result = supplierProductService.updateValidateSystem_version(model.getSupplier_id(), model.getProduct_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.SUPPLIER_PRODUCT_DELETE)
    public void delete() {
        validateRequest_app_id();
        validate(SupplierProduct.SUPPLIER_PRODUCT_ID, SupplierProduct.SYSTEM_VERSION);

        SupplierProduct model = getModel(SupplierProduct.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        SupplierProduct supplier_product = supplierProductService.findBySupplier_product_id(model.getSupplier_product_id());

        authenticateApp_id(supplier_product.getApp_id());
        authenticateSystem_create_user_id(supplier_product.getSystem_create_user_id());

        Boolean result = supplierProductService.deleteBySupplier_product_idAndSystem_update_user_idValidateSystem_version(model.getSupplier_product_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.SUPPLIER_PRODUCT_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(SupplierProduct.SUPPLIER_PRODUCT_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        SupplierProduct model = getModel(SupplierProduct.class);
        String request_app_id = getRequest_app_id();

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = supplierProductService.countByApp_idOrLikeSupplier_product_name(request_app_id, model.getSupplier_product_name());
        List<SupplierProduct> resultList = supplierProductService.listByApp_idOrLikeSupplier_product_nameAndLimit(request_app_id, model.getSupplier_product_name(), getM(), getN());

        for (SupplierProduct result : resultList) {
            result.keep(SupplierProduct.SUPPLIER_PRODUCT_ID, SupplierProduct.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.SUPPLIER_PRODUCT_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(SupplierProduct.SUPPLIER_PRODUCT_ID);

        SupplierProduct model = getModel(SupplierProduct.class);

        authenticateRequest_app_idAndRequest_user_id();

        SupplierProduct supplier_product = supplierProductService.findBySupplier_product_id(model.getSupplier_product_id());

        authenticateApp_id(supplier_product.getApp_id());

        supplier_product.keep(SupplierProduct.SUPPLIER_PRODUCT_ID, SupplierProduct.SYSTEM_VERSION);

        renderSuccessJson(supplier_product);
    }

    @ActionKey(Url.SUPPLIER_PRODUCT_ADMIN_SAVE)
    public void adminSave() {
        save();
    }

    @ActionKey(Url.SUPPLIER_PRODUCT_ADMIN_UPDATE)
    public void adminUpdate() {
        validateRequest_app_id();
        validate(SupplierProduct.SUPPLIER_ID, SupplierProduct.PRODUCT_ID, SupplierProduct.SYSTEM_VERSION);

        SupplierProduct model = getModel(SupplierProduct.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        SupplierProduct supplier_product = supplierProductService.findBySupplier_product_id(model.getSupplier_product_id());

        authenticateApp_id(supplier_product.getApp_id());

        Boolean result = supplierProductService.updateValidateSystem_version(model.getSupplier_id(), model.getProduct_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.SUPPLIER_PRODUCT_ADMIN_DELETE)
    public void adminDelete() {
        validateRequest_app_id();
        validate(SupplierProduct.SUPPLIER_PRODUCT_ID, SupplierProduct.SYSTEM_VERSION);

        SupplierProduct model = getModel(SupplierProduct.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        SupplierProduct supplier_product = supplierProductService.findBySupplier_product_id(model.getSupplier_product_id());

        authenticateApp_id(supplier_product.getApp_id());

        Boolean result = supplierProductService.deleteBySupplier_product_idAndSystem_update_user_idValidateSystem_version(model.getSupplier_product_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.SUPPLIER_PRODUCT_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(SupplierProduct.APP_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        SupplierProduct model = getModel(SupplierProduct.class);

        Integer total = supplierProductService.countByOrApp_idOrLikeSupplier_product_name(model.getApp_id(), model.getSupplier_product_name());
        List<SupplierProduct> resultList = supplierProductService.listByOrApp_idOrLikeSupplier_product_nameAndLimit(model.getApp_id(), model.getSupplier_product_name(), getM(), getN());

        for (SupplierProduct result : resultList) {
            result.keep(SupplierProduct.SUPPLIER_PRODUCT_ID, SupplierProduct.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.SUPPLIER_PRODUCT_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(SupplierProduct.SUPPLIER_PRODUCT_ID);

        SupplierProduct model = getModel(SupplierProduct.class);

        SupplierProduct supplier_product = supplierProductService.findBySupplier_product_id(model.getSupplier_product_id());

        supplier_product.keep(SupplierProduct.SUPPLIER_PRODUCT_ID, SupplierProduct.SYSTEM_VERSION);

        renderSuccessJson(supplier_product);
    }

    @ActionKey(Url.SUPPLIER_PRODUCT_SYSTEM_SAVE)
    public void systemSave() {
        validateRequest_app_id();
        validate(SupplierProduct.SUPPLIER_ID, SupplierProduct.PRODUCT_ID);

        SupplierProduct model = getModel(SupplierProduct.class);
        String supplier_product_id = Util.getRandomUUID();
        String request_user_id = getRequest_user_id();

        Boolean result = supplierProductService.save(supplier_product_id, model.getSupplier_id(), model.getProduct_id(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.SUPPLIER_PRODUCT_SYSTEM_UPDATE)
    public void systemUpdate() {
        validateRequest_app_id();
        validate(SupplierProduct.SUPPLIER_ID, SupplierProduct.PRODUCT_ID, SupplierProduct.SYSTEM_VERSION);

        SupplierProduct model = getModel(SupplierProduct.class);
        String request_user_id = getRequest_user_id();

        Boolean result = supplierProductService.updateValidateSystem_version(model.getSupplier_id(), model.getProduct_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.SUPPLIER_PRODUCT_SYSTEM_DELETE)
    public void systemDelete() {
        validateRequest_app_id();
        validate(SupplierProduct.SUPPLIER_PRODUCT_ID, SupplierProduct.SYSTEM_VERSION);

        SupplierProduct model = getModel(SupplierProduct.class);
        String request_user_id = getRequest_user_id();

        Boolean result = supplierProductService.deleteBySupplier_product_idAndSystem_update_user_idValidateSystem_version(model.getSupplier_product_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}