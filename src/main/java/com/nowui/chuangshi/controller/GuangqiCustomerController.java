package com.nowui.chuangshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.GuangqiCustomer;
import com.nowui.chuangshi.service.GuangqiCustomerService;
import com.nowui.chuangshi.util.Util;
import com.nowui.chuangshi.util.ValidateUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuangqiCustomerController extends Controller {

    private final GuangqiCustomerService guangqiCustomerService = new GuangqiCustomerService();

    @ActionKey(Url.GUANGQI_CUSTOMER_LIST)
    public void list() {
        validateRequest_app_id();
        validate(Constant.PAGE_SIZE, Constant.FIRST_CREATE_TIME, Constant.LAST_CREATE_TIME);

        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();
        JSONObject jsonObject = getParameterJSONObject();

        authenticateRequest_app_idAndRequest_user_id();

        List<GuangqiCustomer> resultList = guangqiCustomerService.listByApp_idAndSystem_create_timeAndLimit(request_app_id, jsonObject.getDate(Constant.LAST_CREATE_TIME), 0, getN(), request_app_id, request_http_id, request_user_id);

        for (GuangqiCustomer result : resultList) {
            result.keep(GuangqiCustomer.CUSTOMER_ID, GuangqiCustomer.SYSTEM_VERSION);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.GUANGQI_CUSTOMER_FIND)
    public void find() {
        validateRequest_app_id();
        validate(GuangqiCustomer.CUSTOMER_ID);

        GuangqiCustomer model = getModel(GuangqiCustomer.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        GuangqiCustomer guangqi_customer = guangqiCustomerService.findByCustomer_id(model.getCustomer_id(), request_app_id, request_http_id, request_user_id);

        authenticateApp_id(guangqi_customer.getApp_id());
        authenticateSystem_create_user_id(guangqi_customer.getSystem_create_user_id());

        guangqi_customer.keep(GuangqiCustomer.CUSTOMER_ID, GuangqiCustomer.SYSTEM_VERSION);

        renderSuccessJson(guangqi_customer);
    }

    @ActionKey(Url.GUANGQI_CUSTOMER_SAVE)
    public void save() {
        validateRequest_app_id();
        validate(GuangqiCustomer.CUSTOMER_NAME, GuangqiCustomer.CUSTOMER_PHONE, GuangqiCustomer.CUSTOMER_PROVINCE, GuangqiCustomer.CUSTOMER_CITY, GuangqiCustomer.COSTOMER_DEALER);

        GuangqiCustomer model = getModel(GuangqiCustomer.class);
        String customer_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        if (!ValidateUtil.isPhone(model.getCustomer_phone())) {
            throw new RuntimeException("手机号码不对");
        }

        Integer count = guangqiCustomerService.countByCustomer_phone(model.getCustomer_phone(), request_app_id, request_http_id, request_user_id);
        if (count > 0) {
            throw new RuntimeException("该手机号码不能重复留资");
        }

        Boolean result = guangqiCustomerService.save(customer_id, request_app_id, model.getCustomer_name(), model.getCustomer_phone(), model.getCustomer_province(), model.getCustomer_city(), model.getCostomer_dealer(), request_user_id, request_app_id, request_http_id, request_user_id);

        if (!result) {
            throw new RuntimeException("留资不成功");
        }

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put(GuangqiCustomer.CUSTOMER_ID, customer_id);

        renderSuccessJson(resultMap);
    }

    @ActionKey(Url.GUANGQI_CUSTOMER_UPDATE)
    public void update() {
        validateRequest_app_id();
        validate(GuangqiCustomer.CUSTOMER_ID, GuangqiCustomer.CUSTOMER_NAME, GuangqiCustomer.CUSTOMER_PHONE, GuangqiCustomer.CUSTOMER_PROVINCE, GuangqiCustomer.CUSTOMER_CITY, GuangqiCustomer.COSTOMER_DEALER, GuangqiCustomer.SYSTEM_VERSION);

        GuangqiCustomer model = getModel(GuangqiCustomer.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        GuangqiCustomer guangqi_customer = guangqiCustomerService.findByCustomer_id(model.getCustomer_id(), request_app_id, request_http_id, request_user_id);

        authenticateApp_id(guangqi_customer.getApp_id());
        authenticateSystem_create_user_id(guangqi_customer.getSystem_create_user_id());

        Boolean result = guangqiCustomerService.updateValidateSystem_version(model.getCustomer_id(), model.getCustomer_name(), model.getCustomer_phone(), model.getCustomer_province(), model.getCustomer_city(), model.getCostomer_dealer(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.GUANGQI_CUSTOMER_DELETE)
    public void delete() {
        validateRequest_app_id();
        validate(GuangqiCustomer.CUSTOMER_ID, GuangqiCustomer.SYSTEM_VERSION);

        GuangqiCustomer model = getModel(GuangqiCustomer.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        GuangqiCustomer guangqi_customer = guangqiCustomerService.findByCustomer_id(model.getCustomer_id(), request_app_id, request_http_id, request_user_id);

        authenticateApp_id(guangqi_customer.getApp_id());
        authenticateSystem_create_user_id(guangqi_customer.getSystem_create_user_id());

        Boolean result = guangqiCustomerService.deleteByCustomer_idAndSystem_update_user_idValidateSystem_version(model.getCustomer_id(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.GUANGQI_CUSTOMER_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        GuangqiCustomer model = getModel(GuangqiCustomer.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = guangqiCustomerService.countByApp_id(request_app_id, request_app_id, request_http_id, request_user_id);
        List<GuangqiCustomer> resultList = guangqiCustomerService.listByApp_idAndCustomer_nameAndLimit(request_app_id, model.getCustomer_name(), getM(), getN(), request_app_id, request_http_id, request_user_id);

        for (GuangqiCustomer result : resultList) {
            result.keep(GuangqiCustomer.CUSTOMER_ID, GuangqiCustomer.CUSTOMER_NAME, GuangqiCustomer.CUSTOMER_PHONE, GuangqiCustomer.CUSTOMER_PROVINCE, GuangqiCustomer.CUSTOMER_CITY, GuangqiCustomer.COSTOMER_DEALER, GuangqiCustomer.SYSTEM_CREATE_TIME, GuangqiCustomer.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.GUANGQI_CUSTOMER_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(GuangqiCustomer.CUSTOMER_ID);

        GuangqiCustomer model = getModel(GuangqiCustomer.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        GuangqiCustomer guangqi_customer = guangqiCustomerService.findByCustomer_id(model.getCustomer_id(), request_app_id, request_http_id, request_user_id);

        authenticateApp_id(guangqi_customer.getApp_id());

        guangqi_customer.keep(GuangqiCustomer.CUSTOMER_ID, GuangqiCustomer.CUSTOMER_NAME, GuangqiCustomer.CUSTOMER_PHONE, GuangqiCustomer.CUSTOMER_PROVINCE, GuangqiCustomer.CUSTOMER_CITY, GuangqiCustomer.COSTOMER_DEALER, GuangqiCustomer.SYSTEM_VERSION);

        renderSuccessJson(guangqi_customer);
    }

    @ActionKey(Url.GUANGQI_CUSTOMER_ADMIN_SAVE)
    public void adminSave() {
        save();
    }

    @ActionKey(Url.GUANGQI_CUSTOMER_ADMIN_UPDATE)
    public void adminUpdate() {
        validateRequest_app_id();
        validate(GuangqiCustomer.CUSTOMER_ID, GuangqiCustomer.CUSTOMER_NAME, GuangqiCustomer.CUSTOMER_PHONE, GuangqiCustomer.CUSTOMER_PROVINCE, GuangqiCustomer.CUSTOMER_CITY, GuangqiCustomer.COSTOMER_DEALER, GuangqiCustomer.SYSTEM_VERSION);

        GuangqiCustomer model = getModel(GuangqiCustomer.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        GuangqiCustomer guangqi_customer = guangqiCustomerService.findByCustomer_id(model.getCustomer_id(), request_app_id, request_http_id, request_user_id);

        authenticateApp_id(guangqi_customer.getApp_id());

        Boolean result = guangqiCustomerService.updateValidateSystem_version(model.getCustomer_id(), model.getCustomer_name(), model.getCustomer_phone(), model.getCustomer_province(), model.getCustomer_city(), model.getCostomer_dealer(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.GUANGQI_CUSTOMER_ADMIN_DELETE)
    public void adminDelete() {
        validateRequest_app_id();
        validate(GuangqiCustomer.CUSTOMER_ID, GuangqiCustomer.SYSTEM_VERSION);

        GuangqiCustomer model = getModel(GuangqiCustomer.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        GuangqiCustomer guangqi_customer = guangqiCustomerService.findByCustomer_id(model.getCustomer_id(), request_app_id, request_http_id, request_user_id);

        authenticateApp_id(guangqi_customer.getApp_id());

        Boolean result = guangqiCustomerService.deleteByCustomer_idAndSystem_update_user_idValidateSystem_version(model.getCustomer_id(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.GUANGQI_CUSTOMER_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(GuangqiCustomer.APP_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        GuangqiCustomer model = getModel(GuangqiCustomer.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        Integer total = guangqiCustomerService.countByOrApp_id(model.getApp_id(), request_app_id, request_http_id, request_user_id);
        List<GuangqiCustomer> resultList = guangqiCustomerService.listByOrApp_idAndLimit(model.getApp_id(), getM(), getN(), request_app_id, request_http_id, request_user_id);

        for (GuangqiCustomer result : resultList) {
            result.keep(GuangqiCustomer.CUSTOMER_ID, GuangqiCustomer.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.GUANGQI_CUSTOMER_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(GuangqiCustomer.CUSTOMER_ID);

        GuangqiCustomer model = getModel(GuangqiCustomer.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        GuangqiCustomer guangqi_customer = guangqiCustomerService.findByCustomer_id(model.getCustomer_id(), request_app_id, request_http_id, request_user_id);

        guangqi_customer.keep(GuangqiCustomer.CUSTOMER_ID, GuangqiCustomer.SYSTEM_VERSION);

        renderSuccessJson(guangqi_customer);
    }

    @ActionKey(Url.GUANGQI_CUSTOMER_SYSTEM_SAVE)
    public void systemSave() {
        save();
    }

    @ActionKey(Url.GUANGQI_CUSTOMER_SYSTEM_UPDATE)
    public void systemUpdate() {
        validateRequest_app_id();
        validate(GuangqiCustomer.CUSTOMER_ID, GuangqiCustomer.CUSTOMER_NAME, GuangqiCustomer.CUSTOMER_PHONE, GuangqiCustomer.CUSTOMER_PROVINCE, GuangqiCustomer.CUSTOMER_CITY, GuangqiCustomer.COSTOMER_DEALER, GuangqiCustomer.SYSTEM_VERSION);

        GuangqiCustomer model = getModel(GuangqiCustomer.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        Boolean result = guangqiCustomerService.updateValidateSystem_version(model.getCustomer_id(), model.getCustomer_name(), model.getCustomer_phone(), model.getCustomer_province(), model.getCustomer_city(), model.getCostomer_dealer(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.GUANGQI_CUSTOMER_SYSTEM_DELETE)
    public void systemDelete() {
        validateRequest_app_id();
        validate(GuangqiCustomer.CUSTOMER_ID, GuangqiCustomer.SYSTEM_VERSION);

        GuangqiCustomer model = getModel(GuangqiCustomer.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        Boolean result = guangqiCustomerService.deleteByCustomer_idAndSystem_update_user_idValidateSystem_version(model.getCustomer_id(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

}