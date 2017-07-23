package com.nowui.chuangshi.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Config;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.File;
import com.nowui.chuangshi.model.Product;
import com.nowui.chuangshi.model.Supplier;
import com.nowui.chuangshi.model.SupplierProduct;
import com.nowui.chuangshi.model.User;
import com.nowui.chuangshi.service.FileService;
import com.nowui.chuangshi.service.ProductService;
import com.nowui.chuangshi.service.SupplierProductService;
import com.nowui.chuangshi.service.SupplierService;
import com.nowui.chuangshi.service.UserService;
import com.nowui.chuangshi.type.UserType;
import com.nowui.chuangshi.util.AesUtil;
import com.nowui.chuangshi.util.Util;

public class SupplierController extends Controller {

    private final SupplierService supplierService = new SupplierService();
    private final UserService userService = new UserService();
    private final ProductService productService = new ProductService();
    private final FileService fileService = new FileService();
    private final SupplierProductService supplierProductService = new SupplierProductService();

    @ActionKey(Url.SUPPLIER_LOGIN)
    public void login() {
        validateRequest_app_id();
        validate(User.USER_ACCOUNT, User.USER_PASSWORD);

        User model = getModel(User.class);
        String request_app_id = getRequest_app_id();

        User user = userService.findByApp_idAndUser_typeAndUser_accountAndUser_password(request_app_id,
                UserType.SUPPLIER.getKey(), model.getUser_account(), model.getUser_password());

        if (user == null) {
            throw new RuntimeException("帐号或者密码不正确");
        }

        Map<String, Object> result = new HashMap<String, Object>();
        try {
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.YEAR, 1);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put(User.USER_ID, user.getUser_id());
            jsonObject.put(Constant.EXPIRE_TIME, calendar.getTime());
            result.put(Constant.TOKEN, AesUtil.aesEncrypt(jsonObject.toJSONString(), Config.private_key));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("登录不成功");
        }

        renderSuccessJson(result);
    }

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
            result.put(User.USER_ACCOUNT, user.getUser_account());
            result.keep(Supplier.SUPPLIER_ID, Supplier.SUPPLIER_STATUS, User.USER_NAME, User.USER_ACCOUNT,
                    Supplier.SYSTEM_VERSION, Supplier.SYSTEM_CREATE_TIME);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.SUPPLIER_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        authenticateRequest_app_idAndRequest_user_id();

        Supplier model = getModel(Supplier.class);
        String app_id = getRequest_app_id();

        Supplier supplier = new Supplier();
        String user_name = "";
        String user_account = "";
        List<Product> productList = new ArrayList<>();

        if (!StringUtils.isEmpty(model.getSupplier_id())) {
            supplier = supplierService.findBySupplier_id(model.getSupplier_id());

            authenticateApp_id(supplier.getApp_id());

            User user = userService.findByUser_id(supplier.getUser_id());
            user_name = user.getUser_name();
            user_account = user.getUser_account();
            productList = productService.listByApp_id(supplier.getApp_id());
            for (Product product : productList) {
                List<SupplierProduct> list = supplierProductService.listBySupplier_id(model.getSupplier_id());
                for (SupplierProduct supplierProduct : list) {
                    product.put("product_isCheck", false);
                    if (supplierProduct.getProduct_id().equals(product.getProduct_id())) {
                        product.put("product_isCheck", true);
                    }
                }
                //TODO
                String file_path = fileService.getFile_path(product.getProduct_image());
                product.put(Product.PRODUCT_IMAGE, file_path);
                product.keep(Product.PRODUCT_ID, Product.PRODUCT_NAME, Product.PRODUCT_IMAGE, "product_isCheck");
            }
        } else {
            productList = productService.listByApp_id(app_id);
            for (Product product : productList) {
                String file_path = fileService.getFile_path(product.getProduct_image());
                product.put(Product.PRODUCT_IMAGE, file_path);
                product.keep(Product.PRODUCT_ID, Product.PRODUCT_NAME, Product.PRODUCT_IMAGE);
            }
        }

        supplier.put(User.USER_NAME, user_name);
        supplier.put(User.USER_ACCOUNT, user_account);
        supplier.put(Product.PRODUCT_LIST, productList);

        supplier.keep(Supplier.SUPPLIER_ID, Supplier.SUPPLIER_STATUS, User.USER_NAME, User.USER_ACCOUNT,
                Supplier.SYSTEM_VERSION, Product.PRODUCT_LIST);

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
        String user_account = jsonObject.getString(User.USER_ACCOUNT);
        String user_password = jsonObject.getString(User.USER_PASSWORD);
        JSONArray product_list = jsonObject.getJSONArray("product_list");

        authenticateRequest_app_idAndRequest_user_id();

        String user_id = Util.getRandomUUID();
        String supplier_id = Util.getRandomUUID();

        userService.saveByUser_idAndApp_idAndObject_idAndUser_typeAndUser_nameAndUser_accountAndUser_password(user_id,
                app_id, supplier_id, UserType.SUPPLIER.getKey(), user_name, user_account, user_password,
                request_user_id);
        Boolean result = supplierService.save(supplier_id, app_id, user_id, model.getSupplier_status(),
                request_user_id);

        List<SupplierProduct> supplierProductlist = new ArrayList<>();
        for (int j = 0; j < product_list.size(); j++) {
            SupplierProduct supplierProduct = new SupplierProduct();
            String product_id = product_list.getJSONObject(j).getString("product_id");

            if (StringUtils.isBlank(product_id)) {
                throw new RuntimeException("商品id不能为空");
            }
            if (productService.findByProduct_id(product_id) == null) {
                throw new RuntimeException("没找到该商品");
            }
            supplierProduct.setProduct_id(product_id);
            supplierProduct.setSupplier_id(supplier_id);
            supplierProduct.setSystem_create_user_id(request_user_id);
            supplierProduct.setSystem_create_time(new Date());
            supplierProduct.setSystem_update_user_id(request_user_id);
            supplierProduct.setSystem_update_time(new Date());
            supplierProduct.setSystem_version(0);
            supplierProduct.setSystem_status(true);

            supplierProductlist.add(supplierProduct);
        }
        supplierProductService.batchSave(supplierProductlist, supplier_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.SUPPLIER_ADMIN_UPDATE)
    public void adminUpdate() {
        validateRequest_app_id();
        validate(Supplier.SUPPLIER_ID, User.USER_NAME, User.USER_ACCOUNT, User.USER_PASSWORD, Supplier.SUPPLIER_STATUS,
                Supplier.SYSTEM_VERSION);

        Supplier model = getModel(Supplier.class);
        String request_user_id = getRequest_user_id();

        JSONObject jsonObject = getParameterJSONObject();
        String user_name = jsonObject.getString(User.USER_NAME);
        String user_account = jsonObject.getString(User.USER_ACCOUNT);
        String user_password = jsonObject.getString(User.USER_PASSWORD);
        JSONArray product_list = jsonObject.getJSONArray("product_list");

        authenticateRequest_app_idAndRequest_user_id();

        Supplier supplier = supplierService.findBySupplier_id(model.getSupplier_id());

        authenticateApp_id(supplier.getApp_id());

        User user = userService.findByUser_id(supplier.getUser_id());
        if (user == null) {
            throw new RuntimeException("没找到该用户");
        }
        userService.updateByUser_nameAndUser_accountAndUser_password(supplier.getUser_id(), user_name, user_account,
                user_password, request_user_id);

        Boolean result = supplierService.updateValidateSystem_version(model.getSupplier_id(), supplier.getUser_id(),
                model.getSupplier_status(), request_user_id, model.getSystem_version());

        supplierProductService.deleteBySupplier_id(model.getSupplier_id(), request_user_id);
        List<SupplierProduct> supplierProductlist = new ArrayList<>();
        for (int j = 0; j < product_list.size(); j++) {
            SupplierProduct supplierProduct = new SupplierProduct();
            String product_id = product_list.getJSONObject(j).getString("product_id");

            if (StringUtils.isBlank(product_id)) {
                throw new RuntimeException("商品id不能为空");
            }
            if (productService.findByProduct_id(product_id) == null) {
                throw new RuntimeException("没找到该商品");
            }
            supplierProduct.setProduct_id(product_id);
            supplierProduct.setSupplier_id(model.getSupplier_id());
            supplierProduct.setSystem_create_user_id(request_user_id);
            supplierProduct.setSystem_create_time(new Date());
            supplierProduct.setSystem_update_user_id(request_user_id);
            supplierProduct.setSystem_update_time(new Date());
            supplierProduct.setSystem_version(0);
            supplierProduct.setSystem_status(true);

            supplierProductlist.add(supplierProduct);
        }
        supplierProductService.batchSave(supplierProductlist, model.getSupplier_id());

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