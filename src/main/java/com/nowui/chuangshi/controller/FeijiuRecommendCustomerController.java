package com.nowui.chuangshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.FeijiuRecommendCustomer;
import com.nowui.chuangshi.service.FeijiuRecommendCustomerService;
import com.nowui.chuangshi.util.Util;

import java.util.List;

public class FeijiuRecommendCustomerController extends Controller {

    private final FeijiuRecommendCustomerService feijiuRecommendCustomerService = new FeijiuRecommendCustomerService();

    @ActionKey(Url.FEIJIU_RECOMMEND_CUSTOMER_LIST)
    public void list() {
        validateRequest_app_id();
        validate(Constant.PAGE_SIZE, Constant.FIRST_CREATE_TIME, Constant.LAST_CREATE_TIME);

        String request_app_id = getRequest_app_id();
        JSONObject jsonObject = getParameterJSONObject();

        authenticateRequest_app_idAndRequest_user_id();

        List<FeijiuRecommendCustomer> resultList = feijiuRecommendCustomerService.listByApp_idAndSystem_create_timeAndLimit(request_app_id, jsonObject.getDate(Constant.LAST_CREATE_TIME), 0, getN());

        for (FeijiuRecommendCustomer result : resultList) {
            result.keep(FeijiuRecommendCustomer.CUSTOMER_ID, FeijiuRecommendCustomer.SYSTEM_VERSION);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.FEIJIU_RECOMMEND_CUSTOMER_FIND)
    public void find() {
        validateRequest_app_id();
        validate(FeijiuRecommendCustomer.CUSTOMER_ID);

        FeijiuRecommendCustomer model = getModel(FeijiuRecommendCustomer.class);

        authenticateRequest_app_idAndRequest_user_id();

        FeijiuRecommendCustomer feijiu_recommend_customer = feijiuRecommendCustomerService.findByCustomer_id(model.getCustomer_id());

        authenticateApp_id(feijiu_recommend_customer.getApp_id());
        authenticateSystem_create_user_id(feijiu_recommend_customer.getSystem_create_user_id());

        feijiu_recommend_customer.keep(FeijiuRecommendCustomer.CUSTOMER_ID, FeijiuRecommendCustomer.SYSTEM_VERSION);

        renderSuccessJson(feijiu_recommend_customer);
    }

    @ActionKey(Url.FEIJIU_RECOMMEND_CUSTOMER_SAVE)
    public void save() {
        validateRequest_app_id();
        validate(FeijiuRecommendCustomer.CUSTOMER_NAME, FeijiuRecommendCustomer.CUSTOMER_PHONE, FeijiuRecommendCustomer.CUSTOMER_CITY);

        FeijiuRecommendCustomer model = getModel(FeijiuRecommendCustomer.class);
        String customer_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Boolean result = feijiuRecommendCustomerService.save(customer_id, request_app_id, model.getCustomer_name(), model.getCustomer_phone(), model.getCustomer_city(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.FEIJIU_RECOMMEND_CUSTOMER_UPDATE)
    public void update() {
        validateRequest_app_id();
        validate(FeijiuRecommendCustomer.CUSTOMER_ID, FeijiuRecommendCustomer.CUSTOMER_NAME, FeijiuRecommendCustomer.CUSTOMER_PHONE, FeijiuRecommendCustomer.CUSTOMER_CITY, FeijiuRecommendCustomer.SYSTEM_VERSION);

        FeijiuRecommendCustomer model = getModel(FeijiuRecommendCustomer.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        FeijiuRecommendCustomer feijiu_recommend_customer = feijiuRecommendCustomerService.findByCustomer_id(model.getCustomer_id());

        authenticateApp_id(feijiu_recommend_customer.getApp_id());
        authenticateSystem_create_user_id(feijiu_recommend_customer.getSystem_create_user_id());

        Boolean result = feijiuRecommendCustomerService.updateValidateSystem_version(model.getCustomer_id(), model.getCustomer_name(), model.getCustomer_phone(), model.getCustomer_city(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.FEIJIU_RECOMMEND_CUSTOMER_DELETE)
    public void delete() {
        validateRequest_app_id();
        validate(FeijiuRecommendCustomer.CUSTOMER_ID, FeijiuRecommendCustomer.SYSTEM_VERSION);

        FeijiuRecommendCustomer model = getModel(FeijiuRecommendCustomer.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        FeijiuRecommendCustomer feijiu_recommend_customer = feijiuRecommendCustomerService.findByCustomer_id(model.getCustomer_id());

        authenticateApp_id(feijiu_recommend_customer.getApp_id());
        authenticateSystem_create_user_id(feijiu_recommend_customer.getSystem_create_user_id());

        Boolean result = feijiuRecommendCustomerService.deleteByCustomer_idAndSystem_update_user_idValidateSystem_version(model.getCustomer_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.FEIJIU_RECOMMEND_CUSTOMER_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        FeijiuRecommendCustomer model = getModel(FeijiuRecommendCustomer.class);
        String request_app_id = getRequest_app_id();

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = feijiuRecommendCustomerService.countByApp_idAndCustomer_name(request_app_id, model.getCustomer_name());
        List<FeijiuRecommendCustomer> resultList = feijiuRecommendCustomerService.listByApp_idAndCustomer_nameAndLimit(request_app_id, model.getCustomer_name(), getM(), getN());

        for (FeijiuRecommendCustomer result : resultList) {
            result.keep(FeijiuRecommendCustomer.CUSTOMER_ID, FeijiuRecommendCustomer.CUSTOMER_NAME, FeijiuRecommendCustomer.CUSTOMER_PHONE, FeijiuRecommendCustomer.CUSTOMER_CITY, FeijiuRecommendCustomer.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.FEIJIU_RECOMMEND_CUSTOMER_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(FeijiuRecommendCustomer.CUSTOMER_ID);

        FeijiuRecommendCustomer model = getModel(FeijiuRecommendCustomer.class);

        authenticateRequest_app_idAndRequest_user_id();

        FeijiuRecommendCustomer feijiu_recommend_customer = feijiuRecommendCustomerService.findByCustomer_id(model.getCustomer_id());

        authenticateApp_id(feijiu_recommend_customer.getApp_id());

        feijiu_recommend_customer.keep(FeijiuRecommendCustomer.CUSTOMER_ID, FeijiuRecommendCustomer.CUSTOMER_NAME, FeijiuRecommendCustomer.CUSTOMER_PHONE, FeijiuRecommendCustomer.CUSTOMER_CITY, FeijiuRecommendCustomer.SYSTEM_VERSION);

        renderSuccessJson(feijiu_recommend_customer);
    }

    @ActionKey(Url.FEIJIU_RECOMMEND_CUSTOMER_ADMIN_SAVE)
    public void adminSave() {
        save();
    }

    @ActionKey(Url.FEIJIU_RECOMMEND_CUSTOMER_ADMIN_UPDATE)
    public void adminUpdate() {
        validateRequest_app_id();
        validate(FeijiuRecommendCustomer.CUSTOMER_ID, FeijiuRecommendCustomer.CUSTOMER_NAME, FeijiuRecommendCustomer.CUSTOMER_PHONE, FeijiuRecommendCustomer.CUSTOMER_CITY, FeijiuRecommendCustomer.SYSTEM_VERSION);

        FeijiuRecommendCustomer model = getModel(FeijiuRecommendCustomer.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        FeijiuRecommendCustomer feijiu_recommend_customer = feijiuRecommendCustomerService.findByCustomer_id(model.getCustomer_id());

        authenticateApp_id(feijiu_recommend_customer.getApp_id());

        Boolean result = feijiuRecommendCustomerService.updateValidateSystem_version(model.getCustomer_id(), model.getCustomer_name(), model.getCustomer_phone(), model.getCustomer_city(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.FEIJIU_RECOMMEND_CUSTOMER_ADMIN_DELETE)
    public void adminDelete() {
        validateRequest_app_id();
        validate(FeijiuRecommendCustomer.CUSTOMER_ID, FeijiuRecommendCustomer.SYSTEM_VERSION);

        FeijiuRecommendCustomer model = getModel(FeijiuRecommendCustomer.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        FeijiuRecommendCustomer feijiu_recommend_customer = feijiuRecommendCustomerService.findByCustomer_id(model.getCustomer_id());

        authenticateApp_id(feijiu_recommend_customer.getApp_id());

        Boolean result = feijiuRecommendCustomerService.deleteByCustomer_idAndSystem_update_user_idValidateSystem_version(model.getCustomer_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.FEIJIU_RECOMMEND_CUSTOMER_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(FeijiuRecommendCustomer.APP_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        FeijiuRecommendCustomer model = getModel(FeijiuRecommendCustomer.class);

        Integer total = feijiuRecommendCustomerService.countByOrApp_id(model.getApp_id());
        List<FeijiuRecommendCustomer> resultList = feijiuRecommendCustomerService.listByOrApp_idAndLimit(model.getApp_id(), getM(), getN());

        for (FeijiuRecommendCustomer result : resultList) {
            result.keep(FeijiuRecommendCustomer.CUSTOMER_ID, FeijiuRecommendCustomer.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.FEIJIU_RECOMMEND_CUSTOMER_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(FeijiuRecommendCustomer.CUSTOMER_ID);

        FeijiuRecommendCustomer model = getModel(FeijiuRecommendCustomer.class);

        FeijiuRecommendCustomer feijiu_recommend_customer = feijiuRecommendCustomerService.findByCustomer_id(model.getCustomer_id());

        feijiu_recommend_customer.keep(FeijiuRecommendCustomer.CUSTOMER_ID, FeijiuRecommendCustomer.SYSTEM_VERSION);

        renderSuccessJson(feijiu_recommend_customer);
    }

    @ActionKey(Url.FEIJIU_RECOMMEND_CUSTOMER_SYSTEM_SAVE)
    public void systemSave() {
        validateRequest_app_id();
        validate(FeijiuRecommendCustomer.APP_ID, FeijiuRecommendCustomer.CUSTOMER_NAME, FeijiuRecommendCustomer.CUSTOMER_PHONE, FeijiuRecommendCustomer.CUSTOMER_CITY);

        FeijiuRecommendCustomer model = getModel(FeijiuRecommendCustomer.class);
        String customer_id = Util.getRandomUUID();
        String request_user_id = getRequest_user_id();

        Boolean result = feijiuRecommendCustomerService.save(customer_id, model.getApp_id(), model.getCustomer_name(), model.getCustomer_phone(), model.getCustomer_city(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.FEIJIU_RECOMMEND_CUSTOMER_SYSTEM_UPDATE)
    public void systemUpdate() {
        validateRequest_app_id();
        validate(FeijiuRecommendCustomer.CUSTOMER_ID, FeijiuRecommendCustomer.CUSTOMER_NAME, FeijiuRecommendCustomer.CUSTOMER_PHONE, FeijiuRecommendCustomer.CUSTOMER_CITY, FeijiuRecommendCustomer.SYSTEM_VERSION);

        FeijiuRecommendCustomer model = getModel(FeijiuRecommendCustomer.class);
        String request_user_id = getRequest_user_id();

        Boolean result = feijiuRecommendCustomerService.updateValidateSystem_version(model.getCustomer_id(), model.getCustomer_name(), model.getCustomer_phone(), model.getCustomer_city(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.FEIJIU_RECOMMEND_CUSTOMER_SYSTEM_DELETE)
    public void systemDelete() {
        validateRequest_app_id();
        validate(FeijiuRecommendCustomer.CUSTOMER_ID, FeijiuRecommendCustomer.SYSTEM_VERSION);

        FeijiuRecommendCustomer model = getModel(FeijiuRecommendCustomer.class);
        String request_user_id = getRequest_user_id();

        Boolean result = feijiuRecommendCustomerService.deleteByCustomer_idAndSystem_update_user_idValidateSystem_version(model.getCustomer_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}