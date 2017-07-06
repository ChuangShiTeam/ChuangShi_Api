package com.nowui.chuangshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.Customer;
import com.nowui.chuangshi.service.CustomerService;
import com.nowui.chuangshi.util.Util;

import java.util.List;

public class CustomerController extends Controller {

    private final CustomerService customerService = new CustomerService();

    @ActionKey(Url.CUSTOMER_LIST)
    public void list() {
        validateRequest_app_id();
        validate(Constant.PAGE_SIZE, Constant.FIRST_CREATE_TIME, Constant.LAST_CREATE_TIME);

        String request_app_id = getRequest_app_id();
        JSONObject jsonObject = getParameterJSONObject();

        authenticateRequest_app_idAndRequest_user_id();

        List<Customer> resultList = customerService.listByApp_idAndSystem_create_timeAndLimit(request_app_id, jsonObject.getDate(Constant.LAST_CREATE_TIME), 0, getN());

        for (Customer result : resultList) {
            result.keep(Customer.CUSTOMER_ID, Customer.SYSTEM_VERSION);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.CUSTOMER_FIND)
    public void find() {
        validateRequest_app_id();
        validate(Customer.CUSTOMER_ID);

        Customer model = getModel(Customer.class);

        authenticateRequest_app_idAndRequest_user_id();

        Customer customer = customerService.findByCustomer_id(model.getCustomer_id());

        authenticateApp_id(customer.getApp_id());
        authenticateSystem_create_user_id(customer.getSystem_create_user_id());

        customer.keep(Customer.CUSTOMER_ID, Customer.SYSTEM_VERSION);

        renderSuccessJson(customer);
    }

    @ActionKey(Url.CUSTOMER_SAVE)
    public void save() {
        validateRequest_app_id();
        validate(Customer.CUSTOMER_NAME, Customer.CUSTOMER_SEX, Customer.CUSTOMER_BIRTHDAY, Customer.CUSTOMER_TEL, Customer.CUSTOMER_MOBILE, Customer.CUSTOMER_POSTCODE, Customer.CUSTOMER_ID_CARD, Customer.CUSTOMER_PROVINCE, Customer.CUSTOMER_CITY, Customer.CUSTOMER_AREA, Customer.CUSTOMER_ADDRESS);

        Customer model = getModel(Customer.class);
        String customer_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Boolean result = customerService.save(customer_id, request_app_id, model.getCustomer_name(), model.getCustomer_sex(), model.getCustomer_birthday(), model.getCustomer_tel(), model.getCustomer_mobile(), model.getCustomer_postcode(), model.getCustomer_id_card(), model.getCustomer_province(), model.getCustomer_city(), model.getCustomer_area(), model.getCustomer_address(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.CUSTOMER_UPDATE)
    public void update() {
        validateRequest_app_id();
        validate(Customer.CUSTOMER_ID, Customer.CUSTOMER_NAME, Customer.CUSTOMER_SEX, Customer.CUSTOMER_BIRTHDAY, Customer.CUSTOMER_TEL, Customer.CUSTOMER_MOBILE, Customer.CUSTOMER_POSTCODE, Customer.CUSTOMER_ID_CARD, Customer.CUSTOMER_PROVINCE, Customer.CUSTOMER_CITY, Customer.CUSTOMER_AREA, Customer.CUSTOMER_ADDRESS, Customer.SYSTEM_VERSION);

        Customer model = getModel(Customer.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Customer customer = customerService.findByCustomer_id(model.getCustomer_id());

        authenticateApp_id(customer.getApp_id());
        authenticateSystem_create_user_id(customer.getSystem_create_user_id());

        Boolean result = customerService.updateValidateSystem_version(model.getCustomer_id(), model.getCustomer_name(), model.getCustomer_sex(), model.getCustomer_birthday(), model.getCustomer_tel(), model.getCustomer_mobile(), model.getCustomer_postcode(), model.getCustomer_id_card(), model.getCustomer_province(), model.getCustomer_city(), model.getCustomer_area(), model.getCustomer_address(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.CUSTOMER_DELETE)
    public void delete() {
        validateRequest_app_id();
        validate(Customer.CUSTOMER_ID, Customer.SYSTEM_VERSION);

        Customer model = getModel(Customer.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Customer customer = customerService.findByCustomer_id(model.getCustomer_id());

        authenticateApp_id(customer.getApp_id());
        authenticateSystem_create_user_id(customer.getSystem_create_user_id());

        Boolean result = customerService.deleteByCustomer_idAndSystem_update_user_idValidateSystem_version(model.getCustomer_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.CUSTOMER_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(Customer.CUSTOMER_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Customer model = getModel(Customer.class);
        String request_app_id = getRequest_app_id();

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = customerService.countByApp_idOrLikeCustomer_name(request_app_id, model.getCustomer_name());
        List<Customer> resultList = customerService.listByApp_idOrLikeCustomer_nameAndLimit(request_app_id, model.getCustomer_name(), getM(), getN());

        for (Customer result : resultList) {
            result.keep(Customer.CUSTOMER_ID, Customer.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.CUSTOMER_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(Customer.CUSTOMER_ID);

        Customer model = getModel(Customer.class);

        authenticateRequest_app_idAndRequest_user_id();

        Customer customer = customerService.findByCustomer_id(model.getCustomer_id());

        authenticateApp_id(customer.getApp_id());

        customer.keep(Customer.CUSTOMER_ID, Customer.SYSTEM_VERSION);

        renderSuccessJson(customer);
    }

    @ActionKey(Url.CUSTOMER_ADMIN_SAVE)
    public void adminSave() {
        save();
    }

    @ActionKey(Url.CUSTOMER_ADMIN_UPDATE)
    public void adminUpdate() {
        validateRequest_app_id();
        validate(Customer.CUSTOMER_ID, Customer.CUSTOMER_NAME, Customer.CUSTOMER_SEX, Customer.CUSTOMER_BIRTHDAY, Customer.CUSTOMER_TEL, Customer.CUSTOMER_MOBILE, Customer.CUSTOMER_POSTCODE, Customer.CUSTOMER_ID_CARD, Customer.CUSTOMER_PROVINCE, Customer.CUSTOMER_CITY, Customer.CUSTOMER_AREA, Customer.CUSTOMER_ADDRESS, Customer.SYSTEM_VERSION);

        Customer model = getModel(Customer.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Customer customer = customerService.findByCustomer_id(model.getCustomer_id());

        authenticateApp_id(customer.getApp_id());

        Boolean result = customerService.updateValidateSystem_version(model.getCustomer_id(), model.getCustomer_name(), model.getCustomer_sex(), model.getCustomer_birthday(), model.getCustomer_tel(), model.getCustomer_mobile(), model.getCustomer_postcode(), model.getCustomer_id_card(), model.getCustomer_province(), model.getCustomer_city(), model.getCustomer_area(), model.getCustomer_address(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.CUSTOMER_ADMIN_DELETE)
    public void adminDelete() {
        validateRequest_app_id();
        validate(Customer.CUSTOMER_ID, Customer.SYSTEM_VERSION);

        Customer model = getModel(Customer.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Customer customer = customerService.findByCustomer_id(model.getCustomer_id());

        authenticateApp_id(customer.getApp_id());

        Boolean result = customerService.deleteByCustomer_idAndSystem_update_user_idValidateSystem_version(model.getCustomer_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.CUSTOMER_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(Customer.APP_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Customer model = getModel(Customer.class);

        Integer total = customerService.countByOrApp_idOrLikeCustomer_name(model.getApp_id(), model.getCustomer_name());
        List<Customer> resultList = customerService.listByOrApp_idOrLikeCustomer_nameAndLimit(model.getApp_id(), model.getCustomer_name(), getM(), getN());

        for (Customer result : resultList) {
            result.keep(Customer.CUSTOMER_ID, Customer.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.CUSTOMER_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(Customer.CUSTOMER_ID);

        Customer model = getModel(Customer.class);

        Customer customer = customerService.findByCustomer_id(model.getCustomer_id());

        customer.keep(Customer.CUSTOMER_ID, Customer.SYSTEM_VERSION);

        renderSuccessJson(customer);
    }

    @ActionKey(Url.CUSTOMER_SYSTEM_SAVE)
    public void systemSave() {
        validateRequest_app_id();
        validate(Customer.APP_ID, Customer.CUSTOMER_NAME, Customer.CUSTOMER_SEX, Customer.CUSTOMER_BIRTHDAY, Customer.CUSTOMER_TEL, Customer.CUSTOMER_MOBILE, Customer.CUSTOMER_POSTCODE, Customer.CUSTOMER_ID_CARD, Customer.CUSTOMER_PROVINCE, Customer.CUSTOMER_CITY, Customer.CUSTOMER_AREA, Customer.CUSTOMER_ADDRESS);

        Customer model = getModel(Customer.class);
        String customer_id = Util.getRandomUUID();
        String request_user_id = getRequest_user_id();

        Boolean result = customerService.save(customer_id, model.getApp_id(), model.getCustomer_name(), model.getCustomer_sex(), model.getCustomer_birthday(), model.getCustomer_tel(), model.getCustomer_mobile(), model.getCustomer_postcode(), model.getCustomer_id_card(), model.getCustomer_province(), model.getCustomer_city(), model.getCustomer_area(), model.getCustomer_address(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.CUSTOMER_SYSTEM_UPDATE)
    public void systemUpdate() {
        validateRequest_app_id();
        validate(Customer.CUSTOMER_ID, Customer.CUSTOMER_NAME, Customer.CUSTOMER_SEX, Customer.CUSTOMER_BIRTHDAY, Customer.CUSTOMER_TEL, Customer.CUSTOMER_MOBILE, Customer.CUSTOMER_POSTCODE, Customer.CUSTOMER_ID_CARD, Customer.CUSTOMER_PROVINCE, Customer.CUSTOMER_CITY, Customer.CUSTOMER_AREA, Customer.CUSTOMER_ADDRESS, Customer.SYSTEM_VERSION);

        Customer model = getModel(Customer.class);
        String request_user_id = getRequest_user_id();

        Boolean result = customerService.updateValidateSystem_version(model.getCustomer_id(), model.getCustomer_name(), model.getCustomer_sex(), model.getCustomer_birthday(), model.getCustomer_tel(), model.getCustomer_mobile(), model.getCustomer_postcode(), model.getCustomer_id_card(), model.getCustomer_province(), model.getCustomer_city(), model.getCustomer_area(), model.getCustomer_address(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.CUSTOMER_SYSTEM_DELETE)
    public void systemDelete() {
        validateRequest_app_id();
        validate(Customer.CUSTOMER_ID, Customer.SYSTEM_VERSION);

        Customer model = getModel(Customer.class);
        String request_user_id = getRequest_user_id();

        Boolean result = customerService.deleteByCustomer_idAndSystem_update_user_idValidateSystem_version(model.getCustomer_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}