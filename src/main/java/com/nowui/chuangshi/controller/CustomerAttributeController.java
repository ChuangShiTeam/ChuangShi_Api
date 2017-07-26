package com.nowui.chuangshi.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.CustomerAttribute;
import com.nowui.chuangshi.model.CustomerAttributeValue;
import com.nowui.chuangshi.service.CustomerAttributeService;
import com.nowui.chuangshi.service.CustomerAttributeValueService;
import com.nowui.chuangshi.util.Util;

public class CustomerAttributeController extends Controller {

    private final CustomerAttributeService customerAttributeService = new CustomerAttributeService();

    private final CustomerAttributeValueService customerAttributeValueService = new CustomerAttributeValueService();

    @ActionKey(Url.CUSTOMER_ATTRIBUTE_LIST)
    public void list() {
        validateRequest_app_id();
        validate(Constant.PAGE_SIZE, Constant.FIRST_CREATE_TIME, Constant.LAST_CREATE_TIME);

        String request_app_id = getRequest_app_id();
        JSONObject jsonObject = getParameterJSONObject();

        authenticateRequest_app_idAndRequest_user_id();

        List<CustomerAttribute> resultList = customerAttributeService.listByApp_idAndSystem_create_timeAndLimit(
                request_app_id, jsonObject.getDate(Constant.LAST_CREATE_TIME), 0, getN());

        for (CustomerAttribute result : resultList) {
            result.keep(CustomerAttribute.CUSTOMER_ATTRIBUTE_ID, CustomerAttribute.SYSTEM_VERSION);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.CUSTOMER_ATTRIBUTE_FIND)
    public void find() {
        validateRequest_app_id();
        validate(CustomerAttribute.CUSTOMER_ATTRIBUTE_ID);

        CustomerAttribute model = getModel(CustomerAttribute.class);

        authenticateRequest_app_idAndRequest_user_id();

        CustomerAttribute customer_attribute = customerAttributeService
                .findByCustomer_attribute_id(model.getCustomer_attribute_id());

        authenticateApp_id(customer_attribute.getApp_id());
        authenticateSystem_create_user_id(customer_attribute.getSystem_create_user_id());

        customer_attribute.keep(CustomerAttribute.CUSTOMER_ATTRIBUTE_ID, CustomerAttribute.SYSTEM_VERSION);

        renderSuccessJson(customer_attribute);
    }

    @ActionKey(Url.CUSTOMER_ATTRIBUTE_SAVE)
    public void save() {
        validateRequest_app_id();
        validate(CustomerAttribute.CUSTOMER_ATTRIBUTE_NAME, CustomerAttribute.CUSTOMER_ATTRIBUTE_KEY,
                CustomerAttribute.CUSTOMER_ATTRIBUTE_INPUT_TYPE, CustomerAttribute.CUSTOMER_ATTRIBUTE_DATA_TYPE,
                CustomerAttribute.CUSTOMER_ATTRIBUTE_DEFAULT_VALUE, CustomerAttribute.CUSTOMER_ATTRIBUTE_SORT);

        CustomerAttribute model = getModel(CustomerAttribute.class);
        String customer_attribute_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Boolean result = customerAttributeService.save(customer_attribute_id, request_app_id,
                model.getCustomer_attribute_name(), model.getCustomer_attribute_key(),
                model.getCustomer_attribute_input_type(), model.getCustomer_attribute_data_type(),
                model.getCustomer_attribute_default_value(), model.getCustomer_attribute_sort(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.CUSTOMER_ATTRIBUTE_UPDATE)
    public void update() {
        validateRequest_app_id();
        validate(CustomerAttribute.CUSTOMER_ATTRIBUTE_ID, CustomerAttribute.CUSTOMER_ATTRIBUTE_NAME,
                CustomerAttribute.CUSTOMER_ATTRIBUTE_KEY, CustomerAttribute.CUSTOMER_ATTRIBUTE_INPUT_TYPE,
                CustomerAttribute.CUSTOMER_ATTRIBUTE_DATA_TYPE, CustomerAttribute.CUSTOMER_ATTRIBUTE_DEFAULT_VALUE,
                CustomerAttribute.CUSTOMER_ATTRIBUTE_SORT, CustomerAttribute.SYSTEM_VERSION);

        CustomerAttribute model = getModel(CustomerAttribute.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        CustomerAttribute customer_attribute = customerAttributeService
                .findByCustomer_attribute_id(model.getCustomer_attribute_id());

        authenticateApp_id(customer_attribute.getApp_id());
        authenticateSystem_create_user_id(customer_attribute.getSystem_create_user_id());

        Boolean result = customerAttributeService.updateValidateSystem_version(model.getCustomer_attribute_id(),
                model.getCustomer_attribute_name(), model.getCustomer_attribute_key(),
                model.getCustomer_attribute_input_type(), model.getCustomer_attribute_data_type(),
                model.getCustomer_attribute_default_value(), model.getCustomer_attribute_sort(), request_user_id,
                model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.CUSTOMER_ATTRIBUTE_DELETE)
    public void delete() {
        validateRequest_app_id();
        validate(CustomerAttribute.CUSTOMER_ATTRIBUTE_ID, CustomerAttribute.SYSTEM_VERSION);

        CustomerAttribute model = getModel(CustomerAttribute.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        CustomerAttribute customer_attribute = customerAttributeService
                .findByCustomer_attribute_id(model.getCustomer_attribute_id());

        authenticateApp_id(customer_attribute.getApp_id());
        authenticateSystem_create_user_id(customer_attribute.getSystem_create_user_id());

        Boolean result = customerAttributeService
                .deleteByCustomer_attribute_idAndSystem_update_user_idValidateSystem_version(
                        model.getCustomer_attribute_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.CUSTOMER_ATTRIBUTE_ADMIN_APP_LIST)
    public void adminAppList() {
        validateRequest_app_id();
        validate();

        String request_app_id = getRequest_app_id();
        JSONObject jsonObject = getParameterJSONObject();
        String customer_id = jsonObject.getString(CustomerAttributeValue.CUSTOMER_ID);

        List<CustomerAttributeValue> list = new ArrayList<>();
        if (!StringUtils.isEmpty(customer_id)) {
            list = customerAttributeValueService.listByCustomer_id(customer_id);
        }
        List<CustomerAttribute> resultList = customerAttributeService.listByApp_id(request_app_id);

        for (CustomerAttribute result : resultList) {
            for (CustomerAttributeValue customerAttributeValue : list) {
                if (customerAttributeValue.getCustomer_attribute_id().equals(result.getCustomer_attribute_id())) {
                    result.set(CustomerAttributeValue.CUSTOMER_ATTRIBUTE_VALUE,
                            customerAttributeValue.getCustomer_attribute_value());
                    break;
                } else {
                    result.set(CustomerAttributeValue.CUSTOMER_ATTRIBUTE_VALUE, null);
                }
            }

            result.keep(CustomerAttribute.CUSTOMER_ATTRIBUTE_ID, CustomerAttribute.SYSTEM_VERSION,
                    CustomerAttribute.CUSTOMER_ATTRIBUTE_NAME, CustomerAttribute.APP_ID,
                    CustomerAttribute.CUSTOMER_ATTRIBUTE_KEY, CustomerAttribute.CUSTOMER_ATTRIBUTE_INPUT_TYPE,
                    CustomerAttribute.CUSTOMER_ATTRIBUTE_DATA_TYPE, CustomerAttribute.CUSTOMER_ATTRIBUTE_DEFAULT_VALUE,
                    CustomerAttribute.CUSTOMER_ATTRIBUTE_SORT, CustomerAttributeValue.CUSTOMER_ATTRIBUTE_VALUE);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.CUSTOMER_ATTRIBUTE_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(CustomerAttribute.CUSTOMER_ATTRIBUTE_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        CustomerAttribute model = getModel(CustomerAttribute.class);
        String request_app_id = getRequest_app_id();

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = customerAttributeService.countByApp_idOrLikeCustomer_attribute_name(request_app_id,
                model.getCustomer_attribute_name());
        List<CustomerAttribute> resultList = customerAttributeService.listByApp_idOrLikeCustomer_attribute_nameAndLimit(
                request_app_id, model.getCustomer_attribute_name(), getM(), getN());

        for (CustomerAttribute result : resultList) {
            result.keep(CustomerAttribute.CUSTOMER_ATTRIBUTE_ID, CustomerAttribute.SYSTEM_VERSION,
                    CustomerAttribute.CUSTOMER_ATTRIBUTE_NAME, CustomerAttribute.APP_ID,
                    CustomerAttribute.CUSTOMER_ATTRIBUTE_KEY, CustomerAttribute.CUSTOMER_ATTRIBUTE_INPUT_TYPE,
                    CustomerAttribute.CUSTOMER_ATTRIBUTE_DATA_TYPE, CustomerAttribute.CUSTOMER_ATTRIBUTE_DEFAULT_VALUE,
                    CustomerAttribute.CUSTOMER_ATTRIBUTE_SORT);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.CUSTOMER_ATTRIBUTE_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(CustomerAttribute.CUSTOMER_ATTRIBUTE_ID);

        CustomerAttribute model = getModel(CustomerAttribute.class);

        authenticateRequest_app_idAndRequest_user_id();

        CustomerAttribute customer_attribute = customerAttributeService
                .findByCustomer_attribute_id(model.getCustomer_attribute_id());

        authenticateApp_id(customer_attribute.getApp_id());

        customer_attribute.keep(CustomerAttribute.CUSTOMER_ATTRIBUTE_ID, CustomerAttribute.SYSTEM_VERSION,
                CustomerAttribute.CUSTOMER_ATTRIBUTE_NAME, CustomerAttribute.APP_ID,
                CustomerAttribute.CUSTOMER_ATTRIBUTE_KEY, CustomerAttribute.CUSTOMER_ATTRIBUTE_INPUT_TYPE,
                CustomerAttribute.CUSTOMER_ATTRIBUTE_DATA_TYPE, CustomerAttribute.CUSTOMER_ATTRIBUTE_DEFAULT_VALUE,
                CustomerAttribute.CUSTOMER_ATTRIBUTE_SORT);

        renderSuccessJson(customer_attribute);
    }

    @ActionKey(Url.CUSTOMER_ATTRIBUTE_ADMIN_SAVE)
    public void adminSave() {
        save();
    }

    @ActionKey(Url.CUSTOMER_ATTRIBUTE_ADMIN_UPDATE)
    public void adminUpdate() {
        validateRequest_app_id();
        validate(CustomerAttribute.CUSTOMER_ATTRIBUTE_ID, CustomerAttribute.CUSTOMER_ATTRIBUTE_NAME,
                CustomerAttribute.CUSTOMER_ATTRIBUTE_KEY, CustomerAttribute.CUSTOMER_ATTRIBUTE_INPUT_TYPE,
                CustomerAttribute.CUSTOMER_ATTRIBUTE_DATA_TYPE, CustomerAttribute.CUSTOMER_ATTRIBUTE_DEFAULT_VALUE,
                CustomerAttribute.CUSTOMER_ATTRIBUTE_SORT, CustomerAttribute.SYSTEM_VERSION);

        CustomerAttribute model = getModel(CustomerAttribute.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        CustomerAttribute customer_attribute = customerAttributeService
                .findByCustomer_attribute_id(model.getCustomer_attribute_id());

        authenticateApp_id(customer_attribute.getApp_id());

        Boolean result = customerAttributeService.updateValidateSystem_version(model.getCustomer_attribute_id(),
                model.getCustomer_attribute_name(), model.getCustomer_attribute_key(),
                model.getCustomer_attribute_input_type(), model.getCustomer_attribute_data_type(),
                model.getCustomer_attribute_default_value(), model.getCustomer_attribute_sort(), request_user_id,
                model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.CUSTOMER_ATTRIBUTE_ADMIN_DELETE)
    public void adminDelete() {
        validateRequest_app_id();
        validate(CustomerAttribute.CUSTOMER_ATTRIBUTE_ID, CustomerAttribute.SYSTEM_VERSION);

        CustomerAttribute model = getModel(CustomerAttribute.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        CustomerAttribute customer_attribute = customerAttributeService
                .findByCustomer_attribute_id(model.getCustomer_attribute_id());

        authenticateApp_id(customer_attribute.getApp_id());

        Boolean result = customerAttributeService
                .deleteByCustomer_attribute_idAndSystem_update_user_idValidateSystem_version(
                        model.getCustomer_attribute_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.CUSTOMER_ATTRIBUTE_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(CustomerAttribute.APP_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        CustomerAttribute model = getModel(CustomerAttribute.class);

        Integer total = customerAttributeService.countByOrApp_idOrLikeCustomer_attribute_name(model.getApp_id(),
                model.getCustomer_attribute_name());
        List<CustomerAttribute> resultList = customerAttributeService
                .listByOrApp_idOrLikeCustomer_attribute_nameAndLimit(model.getApp_id(),
                        model.getCustomer_attribute_name(), getM(), getN());

        for (CustomerAttribute result : resultList) {
            result.keep(CustomerAttribute.CUSTOMER_ATTRIBUTE_ID, CustomerAttribute.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.CUSTOMER_ATTRIBUTE_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(CustomerAttribute.CUSTOMER_ATTRIBUTE_ID);

        CustomerAttribute model = getModel(CustomerAttribute.class);

        CustomerAttribute customer_attribute = customerAttributeService
                .findByCustomer_attribute_id(model.getCustomer_attribute_id());

        customer_attribute.keep(CustomerAttribute.CUSTOMER_ATTRIBUTE_ID, CustomerAttribute.SYSTEM_VERSION);

        renderSuccessJson(customer_attribute);
    }

    @ActionKey(Url.CUSTOMER_ATTRIBUTE_SYSTEM_SAVE)
    public void systemSave() {
        validateRequest_app_id();
        validate(CustomerAttribute.APP_ID, CustomerAttribute.CUSTOMER_ATTRIBUTE_NAME,
                CustomerAttribute.CUSTOMER_ATTRIBUTE_KEY, CustomerAttribute.CUSTOMER_ATTRIBUTE_INPUT_TYPE,
                CustomerAttribute.CUSTOMER_ATTRIBUTE_DATA_TYPE, CustomerAttribute.CUSTOMER_ATTRIBUTE_DEFAULT_VALUE,
                CustomerAttribute.CUSTOMER_ATTRIBUTE_SORT);

        CustomerAttribute model = getModel(CustomerAttribute.class);
        String customer_attribute_id = Util.getRandomUUID();
        String request_user_id = getRequest_user_id();

        Boolean result = customerAttributeService.save(customer_attribute_id, model.getApp_id(),
                model.getCustomer_attribute_name(), model.getCustomer_attribute_key(),
                model.getCustomer_attribute_input_type(), model.getCustomer_attribute_data_type(),
                model.getCustomer_attribute_default_value(), model.getCustomer_attribute_sort(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.CUSTOMER_ATTRIBUTE_SYSTEM_UPDATE)
    public void systemUpdate() {
        validateRequest_app_id();
        validate(CustomerAttribute.CUSTOMER_ATTRIBUTE_ID, CustomerAttribute.CUSTOMER_ATTRIBUTE_NAME,
                CustomerAttribute.CUSTOMER_ATTRIBUTE_KEY, CustomerAttribute.CUSTOMER_ATTRIBUTE_INPUT_TYPE,
                CustomerAttribute.CUSTOMER_ATTRIBUTE_DATA_TYPE, CustomerAttribute.CUSTOMER_ATTRIBUTE_DEFAULT_VALUE,
                CustomerAttribute.CUSTOMER_ATTRIBUTE_SORT, CustomerAttribute.SYSTEM_VERSION);

        CustomerAttribute model = getModel(CustomerAttribute.class);
        String request_user_id = getRequest_user_id();

        Boolean result = customerAttributeService.updateValidateSystem_version(model.getCustomer_attribute_id(),
                model.getCustomer_attribute_name(), model.getCustomer_attribute_key(),
                model.getCustomer_attribute_input_type(), model.getCustomer_attribute_data_type(),
                model.getCustomer_attribute_default_value(), model.getCustomer_attribute_sort(), request_user_id,
                model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.CUSTOMER_ATTRIBUTE_SYSTEM_DELETE)
    public void systemDelete() {
        validateRequest_app_id();
        validate(CustomerAttribute.CUSTOMER_ATTRIBUTE_ID, CustomerAttribute.SYSTEM_VERSION);

        CustomerAttribute model = getModel(CustomerAttribute.class);
        String request_user_id = getRequest_user_id();

        Boolean result = customerAttributeService
                .deleteByCustomer_attribute_idAndSystem_update_user_idValidateSystem_version(
                        model.getCustomer_attribute_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}