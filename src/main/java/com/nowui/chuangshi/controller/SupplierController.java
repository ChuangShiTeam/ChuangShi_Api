package com.nowui.chuangshi.controller;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.Product;
import com.nowui.chuangshi.model.StockProductSku;
import com.nowui.chuangshi.model.Supplier;
import com.nowui.chuangshi.model.User;
import com.nowui.chuangshi.service.ProductService;
import com.nowui.chuangshi.service.SupplierService;
import com.nowui.chuangshi.service.UserService;
import com.nowui.chuangshi.type.UserType;
import com.nowui.chuangshi.util.Util;

public class SupplierController extends Controller {

    private final SupplierService supplierService = new SupplierService();
    private final UserService userService = new UserService();
    private final ProductService productService = new ProductService();

    @ActionKey(Url.SUPPLIER_LIST)
    public void list() {
        validateRequest_app_id();
        validate(Constant.PAGE_SIZE, Constant.FIRST_CREATE_TIME, Constant.LAST_CREATE_TIME);

        String request_app_id = getRequest_app_id();
        JSONObject jsonObject = getParameterJSONObject();

        authenticateRequest_app_idAndRequest_user_id();

        List<Supplier> resultList = supplierService.listByApp_idAndSystem_create_timeAndLimit(request_app_id,
                jsonObject.getDate(Constant.LAST_CREATE_TIME), 0, getN());

        for (Supplier result : resultList) {
            result.keep(Supplier.SUPPLIER_ID, Supplier.SYSTEM_VERSION);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.SUPPLIER_FIND)
    public void find() {
        validateRequest_app_id();
        validate(Supplier.SUPPLIER_ID);

        Supplier model = getModel(Supplier.class);

        authenticateRequest_app_idAndRequest_user_id();

        Supplier supplier = supplierService.findBySupplier_id(model.getSupplier_id());

        authenticateApp_id(supplier.getApp_id());
        authenticateSystem_create_user_id(supplier.getSystem_create_user_id());

        supplier.keep(Supplier.SUPPLIER_ID, Supplier.SYSTEM_VERSION);

        renderSuccessJson(supplier);
    }

    @ActionKey(Url.SUPPLIER_SAVE)
    public void save() {
        validateRequest_app_id();
        validate(Supplier.USER_ID, Supplier.SUPPLIER_STATUS);

        Supplier model = getModel(Supplier.class);
        String supplier_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Boolean result = supplierService.save(supplier_id, request_app_id, model.getUser_id(),
                model.getSupplier_status(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.SUPPLIER_UPDATE)
    public void update() {
        validateRequest_app_id();
        validate(Supplier.SUPPLIER_ID, Supplier.USER_ID, Supplier.SUPPLIER_STATUS, Supplier.SYSTEM_VERSION);

        Supplier model = getModel(Supplier.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Supplier supplier = supplierService.findBySupplier_id(model.getSupplier_id());

        authenticateApp_id(supplier.getApp_id());
        authenticateSystem_create_user_id(supplier.getSystem_create_user_id());

        Boolean result = supplierService.updateValidateSystem_version(model.getSupplier_id(), model.getUser_id(),
                model.getSupplier_status(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.SUPPLIER_DELETE)
    public void delete() {
        validateRequest_app_id();
        validate(Supplier.SUPPLIER_ID, Supplier.SYSTEM_VERSION);

        Supplier model = getModel(Supplier.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Supplier supplier = supplierService.findBySupplier_id(model.getSupplier_id());

        authenticateApp_id(supplier.getApp_id());
        authenticateSystem_create_user_id(supplier.getSystem_create_user_id());

        Boolean result = supplierService.deleteBySupplier_idAndSystem_update_user_idValidateSystem_version(
                model.getSupplier_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.SUPPLIER_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Supplier model = getModel(Supplier.class);
        String request_app_id = getRequest_app_id();

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = supplierService.countByApp_id(request_app_id);
        List<Supplier> resultList = supplierService.listByApp_idAndLimit(request_app_id, getM(), getN());

        for (Supplier result : resultList) {
            User user = userService.findByUser_id(result.getUser_id());
            result.put(User.USER_NAME, user.getUser_name());
            result.keep(Supplier.SUPPLIER_ID, Supplier.SUPPLIER_STATUS, User.USER_NAME, Supplier.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.SUPPLIER_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(Supplier.SUPPLIER_ID);

        Supplier model = getModel(Supplier.class);

        authenticateRequest_app_idAndRequest_user_id();

        Supplier supplier = supplierService.findBySupplier_id(model.getSupplier_id());

        authenticateApp_id(supplier.getApp_id());

        User user = userService.findByUser_id(supplier.getUser_id());
        supplier.put(User.USER_NAME, user.getUser_name());

        List<Product> productList = productService.listByApp_id(supplier.getApp_id());
        for (Product product : productList) {
            product.keep(Product.PRODUCT_ID, Product.PRODUCT_NAME);
        }
        supplier.put(Product.PRODUCT_LIST, productList);

        supplier.keep(Supplier.SUPPLIER_ID, Supplier.SUPPLIER_STATUS, User.USER_NAME, Supplier.SYSTEM_VERSION,
                Product.PRODUCT_LIST);

        renderSuccessJson(supplier);
    }

    @ActionKey(Url.SUPPLIER_ADMIN_SAVE)
    public void adminSave() {
        validateRequest_app_id();

        Supplier model = getModel(Supplier.class);
        String request_user_id = getRequest_user_id();
        String app_id = getRequest_app_id();
        JSONObject jsonObject = getParameterJSONObject();
        String user_name = jsonObject.getString(User.USER_NAME);
        authenticateRequest_app_idAndRequest_user_id();

        String user_id = Util.getRandomUUID();
        String supplier_id = Util.getRandomUUID();

        userService.saveByUser_idAndApp_idAndObject_idAndUser_typeAndUser_nameAndUser_accountAndUser_password(user_id,
                app_id, supplier_id, UserType.SUPPLIER.getKey(), user_name, "", "", request_user_id);
        Boolean result = supplierService.save(supplier_id, app_id, user_id, model.getSupplier_status(),
                request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.SUPPLIER_ADMIN_UPDATE)
    public void adminUpdate() {
        validateRequest_app_id();
        validate(Supplier.SUPPLIER_ID, Supplier.USER_ID, Supplier.SUPPLIER_STATUS, Supplier.SYSTEM_VERSION);

        Supplier model = getModel(Supplier.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Supplier supplier = supplierService.findBySupplier_id(model.getSupplier_id());

        authenticateApp_id(supplier.getApp_id());

        Boolean result = supplierService.updateValidateSystem_version(model.getSupplier_id(), model.getUser_id(),
                model.getSupplier_status(), request_user_id, model.getSystem_version());
        
        JSONObject jsonObject = getParameterJSONObject();
        JSONArray product_list = jsonObject.getJSONArray("product_list");
        for (int j = 0; j < product_list.size(); j++) {
            StockProductSku stockProductSku = product_list.getJSONObject(j).toJavaObject(StockProductSku.class);
            //stockProductSkuList.add(stockProductSku);
        }

        renderSuccessJson(result);
    }

    @ActionKey(Url.SUPPLIER_ADMIN_DELETE)
    public void adminDelete() {
        validateRequest_app_id();
        validate(Supplier.SUPPLIER_ID, Supplier.SYSTEM_VERSION);

        Supplier model = getModel(Supplier.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Supplier supplier = supplierService.findBySupplier_id(model.getSupplier_id());

        authenticateApp_id(supplier.getApp_id());

        Boolean result = supplierService.deleteBySupplier_idAndSystem_update_user_idValidateSystem_version(
                model.getSupplier_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.SUPPLIER_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(Supplier.APP_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Supplier model = getModel(Supplier.class);

        Integer total = supplierService.countByApp_id(model.getApp_id());
        List<Supplier> resultList = supplierService.listByApp_idAndLimit(model.getApp_id(), getM(), getN());

        for (Supplier result : resultList) {
            result.keep(Supplier.SUPPLIER_ID, Supplier.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.SUPPLIER_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(Supplier.SUPPLIER_ID);

        Supplier model = getModel(Supplier.class);

        Supplier supplier = supplierService.findBySupplier_id(model.getSupplier_id());

        supplier.keep(Supplier.SUPPLIER_ID, Supplier.SYSTEM_VERSION);

        renderSuccessJson(supplier);
    }

    @ActionKey(Url.SUPPLIER_SYSTEM_SAVE)
    public void systemSave() {
        validateRequest_app_id();
        validate(Supplier.APP_ID, Supplier.USER_ID, Supplier.SUPPLIER_STATUS);

        Supplier model = getModel(Supplier.class);
        String supplier_id = Util.getRandomUUID();
        String request_user_id = getRequest_user_id();

        Boolean result = supplierService.save(supplier_id, model.getApp_id(), model.getUser_id(),
                model.getSupplier_status(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.SUPPLIER_SYSTEM_UPDATE)
    public void systemUpdate() {
        validateRequest_app_id();
        validate(Supplier.SUPPLIER_ID, Supplier.USER_ID, Supplier.SUPPLIER_STATUS, Supplier.SYSTEM_VERSION);

        Supplier model = getModel(Supplier.class);
        String request_user_id = getRequest_user_id();

        Boolean result = supplierService.updateValidateSystem_version(model.getSupplier_id(), model.getUser_id(),
                model.getSupplier_status(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.SUPPLIER_SYSTEM_DELETE)
    public void systemDelete() {
        validateRequest_app_id();
        validate(Supplier.SUPPLIER_ID, Supplier.SYSTEM_VERSION);

        Supplier model = getModel(Supplier.class);
        String request_user_id = getRequest_user_id();

        Boolean result = supplierService.deleteBySupplier_idAndSystem_update_user_idValidateSystem_version(
                model.getSupplier_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}