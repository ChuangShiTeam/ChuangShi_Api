package com.nowui.chuangshi.controller;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.MemberAddress;
import com.nowui.chuangshi.service.MemberAddressService;
import com.nowui.chuangshi.util.Util;

public class MemberAddressController extends Controller {

    private final MemberAddressService memberAddressService = new MemberAddressService();

    @ActionKey(Url.MEMBER_ADDRESS_LIST)
    public void list() {
        validateRequest_app_id();
        validate(Constant.PAGE_SIZE, Constant.FIRST_CREATE_TIME, Constant.LAST_CREATE_TIME);

        String request_app_id = getRequest_app_id();
        JSONObject jsonObject = getParameterJSONObject();

        authenticateRequest_app_idAndRequest_user_id();

        List<MemberAddress> resultList = memberAddressService.listByApp_idAndSystem_create_timeAndLimit(request_app_id, jsonObject.getDate(Constant.LAST_CREATE_TIME), 0, getN());

        for (MemberAddress result : resultList) {
            result.keep(MemberAddress.MEMBER_ADDRESS_ID, MemberAddress.SYSTEM_VERSION);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.MEMBER_ADDRESS_FIND)
    public void find() {
        validateRequest_app_id();
        validate(MemberAddress.MEMBER_ADDRESS_ID);

        MemberAddress model = getModel(MemberAddress.class);

        authenticateRequest_app_idAndRequest_user_id();

        MemberAddress member_address = memberAddressService.findByMember_address_id(model.getMember_address_id());

        authenticateApp_id(member_address.getApp_id());
        authenticateSystem_create_user_id(member_address.getSystem_create_user_id());

        member_address.keep(MemberAddress.MEMBER_ADDRESS_ID, MemberAddress.SYSTEM_VERSION);

        renderSuccessJson(member_address);
    }

    @ActionKey(Url.MEMBER_ADDRESS_SAVE)
    public void save() {
        validateRequest_app_id();
        validate(MemberAddress.MEMBER_ID, MemberAddress.USER_ID, MemberAddress.MEMBER_ADDRESS_NAME, MemberAddress.MEMBER_ADDRESS_TEL, MemberAddress.MEMBER_ADDRESS_MOBILE,
                MemberAddress.MEMBER_ADDRESS_POSTCODE, MemberAddress.MEMBER_ADDRESS_PROVINCE, MemberAddress.MEMBER_ADDRESS_CITY, MemberAddress.MEMBER_ADDRESS_AREA,
                MemberAddress.MEMBER_ADDRESS_ADDRESS, MemberAddress.MEMBER_DELIVERY_IS_DEFAULT);

        MemberAddress model = getModel(MemberAddress.class);
        String member_address_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Boolean result = memberAddressService.save(member_address_id, request_app_id, model.getMember_id(), model.getUser_id(), model.getMember_address_name(), model.getMember_address_tel(),
                model.getMember_address_mobile(), model.getMember_address_postcode(), model.getMember_address_province(), model.getMember_address_city(), model.getMember_address_area(),
                model.getMember_address_address(), model.getMember_delivery_is_default(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_ADDRESS_UPDATE)
    public void update() {
        validateRequest_app_id();
        validate(MemberAddress.MEMBER_ADDRESS_ID, MemberAddress.MEMBER_ID, MemberAddress.USER_ID, MemberAddress.MEMBER_ADDRESS_NAME, MemberAddress.MEMBER_ADDRESS_TEL,
                MemberAddress.MEMBER_ADDRESS_MOBILE, MemberAddress.MEMBER_ADDRESS_POSTCODE, MemberAddress.MEMBER_ADDRESS_PROVINCE, MemberAddress.MEMBER_ADDRESS_CITY, MemberAddress.MEMBER_ADDRESS_AREA,
                MemberAddress.MEMBER_ADDRESS_ADDRESS, MemberAddress.MEMBER_DELIVERY_IS_DEFAULT, MemberAddress.SYSTEM_VERSION);

        MemberAddress model = getModel(MemberAddress.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        MemberAddress member_address = memberAddressService.findByMember_address_id(model.getMember_address_id());

        authenticateApp_id(member_address.getApp_id());
        authenticateSystem_create_user_id(member_address.getSystem_create_user_id());

        Boolean result = memberAddressService.updateValidateSystem_version(model.getMember_address_id(), model.getMember_id(), model.getUser_id(), model.getMember_address_name(),
                model.getMember_address_tel(), model.getMember_address_mobile(), model.getMember_address_postcode(), model.getMember_address_province(), model.getMember_address_city(),
                model.getMember_address_area(), model.getMember_address_address(), model.getMember_delivery_is_default(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_ADDRESS_DELETE)
    public void delete() {
        validateRequest_app_id();
        validate(MemberAddress.MEMBER_ADDRESS_ID, MemberAddress.SYSTEM_VERSION);

        MemberAddress model = getModel(MemberAddress.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        MemberAddress member_address = memberAddressService.findByMember_address_id(model.getMember_address_id());

        authenticateApp_id(member_address.getApp_id());
        authenticateSystem_create_user_id(member_address.getSystem_create_user_id());

        Boolean result = memberAddressService.deleteByMember_address_idAndSystem_update_user_idValidateSystem_version(model.getMember_address_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_ADDRESS_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(MemberAddress.MEMBER_ADDRESS_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        MemberAddress model = getModel(MemberAddress.class);
        String request_app_id = getRequest_app_id();

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = memberAddressService.countByApp_idOrLikeMember_address_name(request_app_id, model.getMember_address_name());
        List<MemberAddress> resultList = memberAddressService.listByApp_idOrLikeMember_address_nameAndLimit(request_app_id, model.getMember_address_name(), getM(), getN());

        for (MemberAddress result : resultList) {
            result.keep(MemberAddress.MEMBER_ADDRESS_ID, MemberAddress.SYSTEM_VERSION, MemberAddress.MEMBER_ID, MemberAddress.USER_ID, MemberAddress.APP_ID, MemberAddress.MEMBER_ADDRESS_NAME,
                    MemberAddress.MEMBER_ADDRESS_TEL, MemberAddress.MEMBER_ADDRESS_TEL, MemberAddress.MEMBER_ADDRESS_MOBILE, MemberAddress.MEMBER_ADDRESS_POSTCODE,
                    MemberAddress.MEMBER_ADDRESS_PROVINCE, MemberAddress.MEMBER_ADDRESS_CITY, MemberAddress.MEMBER_ADDRESS_AREA, MemberAddress.MEMBER_ADDRESS_ADDRESS,
                    MemberAddress.MEMBER_DELIVERY_IS_DEFAULT);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.MEMBER_ADDRESS_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(MemberAddress.MEMBER_ADDRESS_ID);

        MemberAddress model = getModel(MemberAddress.class);

        authenticateRequest_app_idAndRequest_user_id();

        MemberAddress member_address = memberAddressService.findByMember_address_id(model.getMember_address_id());

        authenticateApp_id(member_address.getApp_id());

        member_address.keep(MemberAddress.MEMBER_ADDRESS_ID, MemberAddress.SYSTEM_VERSION, MemberAddress.MEMBER_ID, MemberAddress.USER_ID, MemberAddress.APP_ID, MemberAddress.MEMBER_ADDRESS_NAME,
                MemberAddress.MEMBER_ADDRESS_TEL, MemberAddress.MEMBER_ADDRESS_TEL, MemberAddress.MEMBER_ADDRESS_MOBILE, MemberAddress.MEMBER_ADDRESS_POSTCODE, MemberAddress.MEMBER_ADDRESS_PROVINCE,
                MemberAddress.MEMBER_ADDRESS_CITY, MemberAddress.MEMBER_ADDRESS_AREA, MemberAddress.MEMBER_ADDRESS_ADDRESS, MemberAddress.MEMBER_DELIVERY_IS_DEFAULT);

        renderSuccessJson(member_address);
    }

    @ActionKey(Url.MEMBER_ADDRESS_ADMIN_SAVE)
    public void adminSave() {
        save();
    }

    @ActionKey(Url.MEMBER_ADDRESS_ADMIN_UPDATE)
    public void adminUpdate() {
        validateRequest_app_id();
        validate(MemberAddress.MEMBER_ADDRESS_ID, MemberAddress.MEMBER_ID, MemberAddress.USER_ID, MemberAddress.MEMBER_ADDRESS_NAME, MemberAddress.MEMBER_ADDRESS_TEL,
                MemberAddress.MEMBER_ADDRESS_MOBILE, MemberAddress.MEMBER_ADDRESS_POSTCODE, MemberAddress.MEMBER_ADDRESS_PROVINCE, MemberAddress.MEMBER_ADDRESS_CITY, MemberAddress.MEMBER_ADDRESS_AREA,
                MemberAddress.MEMBER_ADDRESS_ADDRESS, MemberAddress.MEMBER_DELIVERY_IS_DEFAULT, MemberAddress.SYSTEM_VERSION);

        MemberAddress model = getModel(MemberAddress.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        MemberAddress member_address = memberAddressService.findByMember_address_id(model.getMember_address_id());

        authenticateApp_id(member_address.getApp_id());

        Boolean result = memberAddressService.updateValidateSystem_version(model.getMember_address_id(), model.getMember_id(), model.getUser_id(), model.getMember_address_name(),
                model.getMember_address_tel(), model.getMember_address_mobile(), model.getMember_address_postcode(), model.getMember_address_province(), model.getMember_address_city(),
                model.getMember_address_area(), model.getMember_address_address(), model.getMember_delivery_is_default(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_ADDRESS_ADMIN_DELETE)
    public void adminDelete() {
        validateRequest_app_id();
        validate(MemberAddress.MEMBER_ADDRESS_ID, MemberAddress.SYSTEM_VERSION);

        MemberAddress model = getModel(MemberAddress.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        MemberAddress member_address = memberAddressService.findByMember_address_id(model.getMember_address_id());

        authenticateApp_id(member_address.getApp_id());

        Boolean result = memberAddressService.deleteByMember_address_idAndSystem_update_user_idValidateSystem_version(model.getMember_address_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_ADDRESS_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(MemberAddress.APP_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        MemberAddress model = getModel(MemberAddress.class);

        Integer total = memberAddressService.countByOrApp_idOrLikeMember_address_name(model.getApp_id(), model.getMember_address_name());
        List<MemberAddress> resultList = memberAddressService.listByOrApp_idOrLikeMember_address_nameAndLimit(model.getApp_id(), model.getMember_address_name(), getM(), getN());

        for (MemberAddress result : resultList) {
            result.keep(MemberAddress.MEMBER_ADDRESS_ID, MemberAddress.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.MEMBER_ADDRESS_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(MemberAddress.MEMBER_ADDRESS_ID);

        MemberAddress model = getModel(MemberAddress.class);

        MemberAddress member_address = memberAddressService.findByMember_address_id(model.getMember_address_id());

        member_address.keep(MemberAddress.MEMBER_ADDRESS_ID, MemberAddress.SYSTEM_VERSION);

        renderSuccessJson(member_address);
    }

    @ActionKey(Url.MEMBER_ADDRESS_SYSTEM_SAVE)
    public void systemSave() {
        validateRequest_app_id();
        validate(MemberAddress.APP_ID, MemberAddress.MEMBER_ID, MemberAddress.USER_ID, MemberAddress.MEMBER_ADDRESS_NAME, MemberAddress.MEMBER_ADDRESS_TEL, MemberAddress.MEMBER_ADDRESS_MOBILE,
                MemberAddress.MEMBER_ADDRESS_POSTCODE, MemberAddress.MEMBER_ADDRESS_PROVINCE, MemberAddress.MEMBER_ADDRESS_CITY, MemberAddress.MEMBER_ADDRESS_AREA,
                MemberAddress.MEMBER_ADDRESS_ADDRESS, MemberAddress.MEMBER_DELIVERY_IS_DEFAULT);

        MemberAddress model = getModel(MemberAddress.class);
        String member_address_id = Util.getRandomUUID();
        String request_user_id = getRequest_user_id();

        Boolean result = memberAddressService.save(member_address_id, model.getApp_id(), model.getMember_id(), model.getUser_id(), model.getMember_address_name(), model.getMember_address_tel(),
                model.getMember_address_mobile(), model.getMember_address_postcode(), model.getMember_address_province(), model.getMember_address_city(), model.getMember_address_area(),
                model.getMember_address_address(), model.getMember_delivery_is_default(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_ADDRESS_SYSTEM_UPDATE)
    public void systemUpdate() {
        validateRequest_app_id();
        validate(MemberAddress.MEMBER_ADDRESS_ID, MemberAddress.MEMBER_ID, MemberAddress.USER_ID, MemberAddress.MEMBER_ADDRESS_NAME, MemberAddress.MEMBER_ADDRESS_TEL,
                MemberAddress.MEMBER_ADDRESS_MOBILE, MemberAddress.MEMBER_ADDRESS_POSTCODE, MemberAddress.MEMBER_ADDRESS_PROVINCE, MemberAddress.MEMBER_ADDRESS_CITY, MemberAddress.MEMBER_ADDRESS_AREA,
                MemberAddress.MEMBER_ADDRESS_ADDRESS, MemberAddress.MEMBER_DELIVERY_IS_DEFAULT, MemberAddress.SYSTEM_VERSION);

        MemberAddress model = getModel(MemberAddress.class);
        String request_user_id = getRequest_user_id();

        Boolean result = memberAddressService.updateValidateSystem_version(model.getMember_address_id(), model.getMember_id(), model.getUser_id(), model.getMember_address_name(),
                model.getMember_address_tel(), model.getMember_address_mobile(), model.getMember_address_postcode(), model.getMember_address_province(), model.getMember_address_city(),
                model.getMember_address_area(), model.getMember_address_address(), model.getMember_delivery_is_default(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_ADDRESS_SYSTEM_DELETE)
    public void systemDelete() {
        validateRequest_app_id();
        validate(MemberAddress.MEMBER_ADDRESS_ID, MemberAddress.SYSTEM_VERSION);

        MemberAddress model = getModel(MemberAddress.class);
        String request_user_id = getRequest_user_id();

        Boolean result = memberAddressService.deleteByMember_address_idAndSystem_update_user_idValidateSystem_version(model.getMember_address_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}