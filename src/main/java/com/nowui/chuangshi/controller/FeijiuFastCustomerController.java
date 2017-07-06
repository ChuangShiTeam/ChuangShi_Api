package com.nowui.chuangshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.FeijiuFastCustomer;
import com.nowui.chuangshi.service.FeijiuFastCustomerService;
import com.nowui.chuangshi.util.Util;

import java.util.List;

public class FeijiuFastCustomerController extends Controller {

    private final FeijiuFastCustomerService feijiuFastCustomerService = new FeijiuFastCustomerService();

    @ActionKey(Url.FEIJIU_FAST_CUSTOMER_LIST)
    public void list() {
        validateRequest_app_id();
        validate(Constant.PAGE_SIZE, Constant.FIRST_CREATE_TIME, Constant.LAST_CREATE_TIME);

        String request_app_id = getRequest_app_id();
        JSONObject jsonObject = getParameterJSONObject();

        authenticateRequest_app_idAndRequest_user_id();

        List<FeijiuFastCustomer> resultList = feijiuFastCustomerService.listByApp_idAndSystem_create_timeAndLimit(request_app_id, jsonObject.getDate(Constant.LAST_CREATE_TIME), 0, getN());

        for (FeijiuFastCustomer result : resultList) {
            result.keep(FeijiuFastCustomer.CUSTOMER_ID, FeijiuFastCustomer.SYSTEM_VERSION);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.FEIJIU_FAST_CUSTOMER_FIND)
    public void find() {
        validateRequest_app_id();
        validate(FeijiuFastCustomer.CUSTOMER_ID);

        FeijiuFastCustomer model = getModel(FeijiuFastCustomer.class);

        authenticateRequest_app_idAndRequest_user_id();

        FeijiuFastCustomer feijiu_fast_customer = feijiuFastCustomerService.findByCustomer_id(model.getCustomer_id());

        authenticateApp_id(feijiu_fast_customer.getApp_id());
        authenticateSystem_create_user_id(feijiu_fast_customer.getSystem_create_user_id());

        feijiu_fast_customer.keep(FeijiuFastCustomer.CUSTOMER_ID, FeijiuFastCustomer.SYSTEM_VERSION);

        renderSuccessJson(feijiu_fast_customer);
    }

    @ActionKey(Url.FEIJIU_FAST_CUSTOMER_SAVE)
    public void save() {
        validateRequest_app_id();
        validate(FeijiuFastCustomer.CUSTOMER_NAME, FeijiuFastCustomer.CUSTOMER_PHONE, FeijiuFastCustomer.CUSTOMER_BIRTHDAY, FeijiuFastCustomer.CUSTOMER_CITY, FeijiuFastCustomer.CUSTOMER_SEX, FeijiuFastCustomer.CUSTOMER_ID_CARD, FeijiuFastCustomer.CUSTOMER_MONEY, FeijiuFastCustomer.CUSTOMER_FANG, FeijiuFastCustomer.CUSTOMER_CHE, FeijiuFastCustomer.CUSTOMER_XIN, FeijiuFastCustomer.CUSTOMER_SHOU, FeijiuFastCustomer.CUSTOMER_DAI, FeijiuFastCustomer.CUSTOMER_GONG);

        FeijiuFastCustomer model = getModel(FeijiuFastCustomer.class);
        String customer_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Boolean result = feijiuFastCustomerService.save(customer_id, request_app_id, model.getCustomer_name(), model.getCustomer_phone(), model.getCustomer_birthday(), model.getCustomer_city(), model.getCustomer_sex(), model.getCustomer_id_card(), model.getCustomer_money(), model.getCustomer_fang(), model.getCustomer_che(), model.getCustomer_xin(), model.getCustomer_shou(), model.getCustomer_dai(), model.getCustomer_gong(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.FEIJIU_FAST_CUSTOMER_UPDATE)
    public void update() {
        validateRequest_app_id();
        validate(FeijiuFastCustomer.CUSTOMER_ID, FeijiuFastCustomer.CUSTOMER_NAME, FeijiuFastCustomer.CUSTOMER_PHONE, FeijiuFastCustomer.CUSTOMER_BIRTHDAY, FeijiuFastCustomer.CUSTOMER_CITY, FeijiuFastCustomer.CUSTOMER_SEX, FeijiuFastCustomer.CUSTOMER_ID_CARD, FeijiuFastCustomer.CUSTOMER_MONEY, FeijiuFastCustomer.CUSTOMER_FANG, FeijiuFastCustomer.CUSTOMER_CHE, FeijiuFastCustomer.CUSTOMER_XIN, FeijiuFastCustomer.CUSTOMER_SHOU, FeijiuFastCustomer.CUSTOMER_DAI, FeijiuFastCustomer.CUSTOMER_GONG, FeijiuFastCustomer.SYSTEM_VERSION);

        FeijiuFastCustomer model = getModel(FeijiuFastCustomer.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        FeijiuFastCustomer feijiu_fast_customer = feijiuFastCustomerService.findByCustomer_id(model.getCustomer_id());

        authenticateApp_id(feijiu_fast_customer.getApp_id());
        authenticateSystem_create_user_id(feijiu_fast_customer.getSystem_create_user_id());

        Boolean result = feijiuFastCustomerService.updateValidateSystem_version(model.getCustomer_id(), model.getCustomer_name(), model.getCustomer_phone(), model.getCustomer_birthday(), model.getCustomer_city(), model.getCustomer_sex(), model.getCustomer_id_card(), model.getCustomer_money(), model.getCustomer_fang(), model.getCustomer_che(), model.getCustomer_xin(), model.getCustomer_shou(), model.getCustomer_dai(), model.getCustomer_gong(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.FEIJIU_FAST_CUSTOMER_DELETE)
    public void delete() {
        validateRequest_app_id();
        validate(FeijiuFastCustomer.CUSTOMER_ID, FeijiuFastCustomer.SYSTEM_VERSION);

        FeijiuFastCustomer model = getModel(FeijiuFastCustomer.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        FeijiuFastCustomer feijiu_fast_customer = feijiuFastCustomerService.findByCustomer_id(model.getCustomer_id());

        authenticateApp_id(feijiu_fast_customer.getApp_id());
        authenticateSystem_create_user_id(feijiu_fast_customer.getSystem_create_user_id());

        Boolean result = feijiuFastCustomerService.deleteByCustomer_idAndSystem_update_user_idValidateSystem_version(model.getCustomer_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.FEIJIU_FAST_CUSTOMER_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        FeijiuFastCustomer model = getModel(FeijiuFastCustomer.class);
        String request_app_id = getRequest_app_id();

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = feijiuFastCustomerService.countByApp_idAndCustomer_name(request_app_id, model.getCustomer_name());
        List<FeijiuFastCustomer> resultList = feijiuFastCustomerService.listByApp_idAndCustomer_nameAndLimit(request_app_id, model.getCustomer_name(), getM(), getN());

        for (FeijiuFastCustomer result : resultList) {
            result.keep(FeijiuFastCustomer.CUSTOMER_ID, FeijiuFastCustomer.CUSTOMER_NAME, FeijiuFastCustomer.CUSTOMER_PHONE, FeijiuFastCustomer.CUSTOMER_ID_CARD, FeijiuFastCustomer.CUSTOMER_MONEY, FeijiuFastCustomer.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.FEIJIU_FAST_CUSTOMER_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(FeijiuFastCustomer.CUSTOMER_ID);

        FeijiuFastCustomer model = getModel(FeijiuFastCustomer.class);

        authenticateRequest_app_idAndRequest_user_id();

        FeijiuFastCustomer feijiu_fast_customer = feijiuFastCustomerService.findByCustomer_id(model.getCustomer_id());

        authenticateApp_id(feijiu_fast_customer.getApp_id());

        feijiu_fast_customer.keep(FeijiuFastCustomer.CUSTOMER_ID, FeijiuFastCustomer.CUSTOMER_NAME, FeijiuFastCustomer.CUSTOMER_PHONE, FeijiuFastCustomer.CUSTOMER_BIRTHDAY, FeijiuFastCustomer.CUSTOMER_CITY, FeijiuFastCustomer.CUSTOMER_SEX, FeijiuFastCustomer.CUSTOMER_ID_CARD, FeijiuFastCustomer.CUSTOMER_MONEY, FeijiuFastCustomer.CUSTOMER_FANG, FeijiuFastCustomer.CUSTOMER_CHE, FeijiuFastCustomer.CUSTOMER_XIN, FeijiuFastCustomer.CUSTOMER_SHOU, FeijiuFastCustomer.CUSTOMER_DAI, FeijiuFastCustomer.CUSTOMER_GONG, FeijiuFastCustomer.SYSTEM_VERSION);

        renderSuccessJson(feijiu_fast_customer);
    }

    @ActionKey(Url.FEIJIU_FAST_CUSTOMER_ADMIN_SAVE)
    public void adminSave() {
        save();
    }

    @ActionKey(Url.FEIJIU_FAST_CUSTOMER_ADMIN_UPDATE)
    public void adminUpdate() {
        validateRequest_app_id();
        validate(FeijiuFastCustomer.CUSTOMER_ID, FeijiuFastCustomer.CUSTOMER_NAME, FeijiuFastCustomer.CUSTOMER_PHONE, FeijiuFastCustomer.CUSTOMER_BIRTHDAY, FeijiuFastCustomer.CUSTOMER_CITY, FeijiuFastCustomer.CUSTOMER_SEX, FeijiuFastCustomer.CUSTOMER_ID_CARD, FeijiuFastCustomer.CUSTOMER_MONEY, FeijiuFastCustomer.CUSTOMER_FANG, FeijiuFastCustomer.CUSTOMER_CHE, FeijiuFastCustomer.CUSTOMER_XIN, FeijiuFastCustomer.CUSTOMER_SHOU, FeijiuFastCustomer.CUSTOMER_DAI, FeijiuFastCustomer.CUSTOMER_GONG, FeijiuFastCustomer.SYSTEM_VERSION);

        FeijiuFastCustomer model = getModel(FeijiuFastCustomer.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        FeijiuFastCustomer feijiu_fast_customer = feijiuFastCustomerService.findByCustomer_id(model.getCustomer_id());

        authenticateApp_id(feijiu_fast_customer.getApp_id());

        Boolean result = feijiuFastCustomerService.updateValidateSystem_version(model.getCustomer_id(), model.getCustomer_name(), model.getCustomer_phone(), model.getCustomer_birthday(), model.getCustomer_city(), model.getCustomer_sex(), model.getCustomer_id_card(), model.getCustomer_money(), model.getCustomer_fang(), model.getCustomer_che(), model.getCustomer_xin(), model.getCustomer_shou(), model.getCustomer_dai(), model.getCustomer_gong(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.FEIJIU_FAST_CUSTOMER_ADMIN_DELETE)
    public void adminDelete() {
        validateRequest_app_id();
        validate(FeijiuFastCustomer.CUSTOMER_ID, FeijiuFastCustomer.SYSTEM_VERSION);

        FeijiuFastCustomer model = getModel(FeijiuFastCustomer.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        FeijiuFastCustomer feijiu_fast_customer = feijiuFastCustomerService.findByCustomer_id(model.getCustomer_id());

        authenticateApp_id(feijiu_fast_customer.getApp_id());

        Boolean result = feijiuFastCustomerService.deleteByCustomer_idAndSystem_update_user_idValidateSystem_version(model.getCustomer_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.FEIJIU_FAST_CUSTOMER_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(FeijiuFastCustomer.APP_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        FeijiuFastCustomer model = getModel(FeijiuFastCustomer.class);

        Integer total = feijiuFastCustomerService.countByOrApp_id(model.getApp_id());
        List<FeijiuFastCustomer> resultList = feijiuFastCustomerService.listByOrApp_idAndLimit(model.getApp_id(), getM(), getN());

        for (FeijiuFastCustomer result : resultList) {
            result.keep(FeijiuFastCustomer.CUSTOMER_ID, FeijiuFastCustomer.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.FEIJIU_FAST_CUSTOMER_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(FeijiuFastCustomer.CUSTOMER_ID);

        FeijiuFastCustomer model = getModel(FeijiuFastCustomer.class);

        FeijiuFastCustomer feijiu_fast_customer = feijiuFastCustomerService.findByCustomer_id(model.getCustomer_id());

        feijiu_fast_customer.keep(FeijiuFastCustomer.CUSTOMER_ID, FeijiuFastCustomer.SYSTEM_VERSION);

        renderSuccessJson(feijiu_fast_customer);
    }

    @ActionKey(Url.FEIJIU_FAST_CUSTOMER_SYSTEM_SAVE)
    public void systemSave() {
        validateRequest_app_id();
        validate(FeijiuFastCustomer.APP_ID, FeijiuFastCustomer.CUSTOMER_NAME, FeijiuFastCustomer.CUSTOMER_PHONE, FeijiuFastCustomer.CUSTOMER_BIRTHDAY, FeijiuFastCustomer.CUSTOMER_CITY, FeijiuFastCustomer.CUSTOMER_SEX, FeijiuFastCustomer.CUSTOMER_ID_CARD, FeijiuFastCustomer.CUSTOMER_MONEY, FeijiuFastCustomer.CUSTOMER_FANG, FeijiuFastCustomer.CUSTOMER_CHE, FeijiuFastCustomer.CUSTOMER_XIN, FeijiuFastCustomer.CUSTOMER_SHOU, FeijiuFastCustomer.CUSTOMER_DAI, FeijiuFastCustomer.CUSTOMER_GONG);

        authenticateRequest_app_idAndRequest_user_id();

        FeijiuFastCustomer model = getModel(FeijiuFastCustomer.class);
        String customer_id = Util.getRandomUUID();
        String request_user_id = getRequest_user_id();

        Boolean result = feijiuFastCustomerService.save(customer_id, model.getApp_id(), model.getCustomer_name(), model.getCustomer_phone(), model.getCustomer_birthday(), model.getCustomer_city(), model.getCustomer_sex(), model.getCustomer_id_card(), model.getCustomer_money(), model.getCustomer_fang(), model.getCustomer_che(), model.getCustomer_xin(), model.getCustomer_shou(), model.getCustomer_dai(), model.getCustomer_gong(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.FEIJIU_FAST_CUSTOMER_SYSTEM_UPDATE)
    public void systemUpdate() {
        validateRequest_app_id();
        validate(FeijiuFastCustomer.CUSTOMER_ID, FeijiuFastCustomer.CUSTOMER_NAME, FeijiuFastCustomer.CUSTOMER_PHONE, FeijiuFastCustomer.CUSTOMER_BIRTHDAY, FeijiuFastCustomer.CUSTOMER_CITY, FeijiuFastCustomer.CUSTOMER_SEX, FeijiuFastCustomer.CUSTOMER_ID_CARD, FeijiuFastCustomer.CUSTOMER_MONEY, FeijiuFastCustomer.CUSTOMER_FANG, FeijiuFastCustomer.CUSTOMER_CHE, FeijiuFastCustomer.CUSTOMER_XIN, FeijiuFastCustomer.CUSTOMER_SHOU, FeijiuFastCustomer.CUSTOMER_DAI, FeijiuFastCustomer.CUSTOMER_GONG, FeijiuFastCustomer.SYSTEM_VERSION);

        FeijiuFastCustomer model = getModel(FeijiuFastCustomer.class);
        String request_user_id = getRequest_user_id();

        Boolean result = feijiuFastCustomerService.updateValidateSystem_version(model.getCustomer_id(), model.getCustomer_name(), model.getCustomer_phone(), model.getCustomer_birthday(), model.getCustomer_city(), model.getCustomer_sex(), model.getCustomer_id_card(), model.getCustomer_money(), model.getCustomer_fang(), model.getCustomer_che(), model.getCustomer_xin(), model.getCustomer_shou(), model.getCustomer_dai(), model.getCustomer_gong(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.FEIJIU_FAST_CUSTOMER_SYSTEM_DELETE)
    public void systemDelete() {
        validateRequest_app_id();
        validate(FeijiuFastCustomer.CUSTOMER_ID, FeijiuFastCustomer.SYSTEM_VERSION);

        FeijiuFastCustomer model = getModel(FeijiuFastCustomer.class);
        String request_user_id = getRequest_user_id();

        Boolean result = feijiuFastCustomerService.deleteByCustomer_idAndSystem_update_user_idValidateSystem_version(model.getCustomer_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}