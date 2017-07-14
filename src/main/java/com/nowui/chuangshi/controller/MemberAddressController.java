package com.nowui.chuangshi.controller;

import java.util.Date;
import java.util.List;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.MemberAddress;
import com.nowui.chuangshi.model.User;
import com.nowui.chuangshi.service.MemberAddressService;
import com.nowui.chuangshi.service.UserService;
import com.nowui.chuangshi.util.Util;

public class MemberAddressController extends Controller {

    private final MemberAddressService memberAddressService = new MemberAddressService();
    private final UserService userService = new UserService();

    @ActionKey(Url.MEMBER_ADDRESS_LIST)
    public void list() {
        validateRequest_app_id();

        authenticateRequest_app_idAndRequest_user_id();

        String request_user_id = getRequest_user_id();

        User user = userService.findByUser_id(request_user_id);

        List<MemberAddress> resultList = memberAddressService.listByMember_id(user.getObject_Id());

        for (MemberAddress result : resultList) {
            result.keep(MemberAddress.MEMBER_ADDRESS_ID, MemberAddress.MEMBER_ADDRESS_NAME,
                    MemberAddress.MEMBER_ADDRESS_TEL, MemberAddress.MEMBER_ADDRESS_MOBILE,
                    MemberAddress.MEMBER_ADDRESS_POSTCODE, MemberAddress.MEMBER_ADDRESS_PROVINCE,
                    MemberAddress.MEMBER_ADDRESS_CITY, MemberAddress.MEMBER_ADDRESS_AREA,
                    MemberAddress.MEMBER_ADDRESS_ADDRESS, MemberAddress.ADDRESS_IS_DEFAULT,
                    MemberAddress.SYSTEM_VERSION);
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
        validate(MemberAddress.MEMBER_ADDRESS_NAME, MemberAddress.MEMBER_ADDRESS_MOBILE,
                MemberAddress.MEMBER_ADDRESS_PROVINCE, MemberAddress.MEMBER_ADDRESS_CITY,
                MemberAddress.MEMBER_ADDRESS_AREA, MemberAddress.MEMBER_ADDRESS_ADDRESS,
                MemberAddress.ADDRESS_IS_DEFAULT);

        MemberAddress model = getModel(MemberAddress.class);
        String member_address_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();

        User user = userService.findByUser_id(request_user_id);

        authenticateRequest_app_idAndRequest_user_id();

        List<MemberAddress> memberAddressList = memberAddressService.listByMember_id(user.getObject_Id());
        if (memberAddressList != null && memberAddressList.size() > 0) {
            if (model.getAddress_is_default()) {
                for (MemberAddress memberAddress : memberAddressList) {
                    memberAddress.setAddress_is_default(false);
                    memberAddress.setSystem_update_time(new Date());
                    memberAddress.setSystem_update_user_id(request_user_id);
                    memberAddress.setSystem_version(memberAddress.getSystem_version() + 1);
                }
                Boolean b = memberAddressService.batchUpdate(memberAddressList, user.getObject_Id());
                if (!b) {
                    throw new RuntimeException("修改地址不成功");
                }
            }
        } else {
            model.setAddress_is_default(true);
        }

        Boolean result = memberAddressService.save(member_address_id, request_app_id, user.getObject_Id(),
                request_user_id, model.getMember_address_name(), "",
                model.getMember_address_mobile(), "",
                model.getMember_address_province(), model.getMember_address_city(), model.getMember_address_area(),
                model.getMember_address_address(), model.getAddress_is_default(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_ADDRESS_UPDATE)
    public void update() {
        validateRequest_app_id();
        validate(MemberAddress.MEMBER_ADDRESS_ID, MemberAddress.MEMBER_ID, MemberAddress.USER_ID,
                MemberAddress.MEMBER_ADDRESS_NAME, MemberAddress.MEMBER_ADDRESS_TEL,
                MemberAddress.MEMBER_ADDRESS_MOBILE, MemberAddress.MEMBER_ADDRESS_POSTCODE,
                MemberAddress.MEMBER_ADDRESS_PROVINCE, MemberAddress.MEMBER_ADDRESS_CITY,
                MemberAddress.MEMBER_ADDRESS_AREA, MemberAddress.MEMBER_ADDRESS_ADDRESS,
                MemberAddress.ADDRESS_IS_DEFAULT, MemberAddress.SYSTEM_VERSION);

        MemberAddress model = getModel(MemberAddress.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        MemberAddress member_address = memberAddressService.findByMember_address_id(model.getMember_address_id());

        authenticateApp_id(member_address.getApp_id());
        authenticateSystem_create_user_id(member_address.getSystem_create_user_id());

        Boolean result = memberAddressService.updateValidateSystem_version(model.getMember_address_id(),
                model.getMember_id(), model.getUser_id(), model.getMember_address_name(), model.getMember_address_tel(),
                model.getMember_address_mobile(), model.getMember_address_postcode(),
                model.getMember_address_province(), model.getMember_address_city(), model.getMember_address_area(),
                model.getMember_address_address(), model.getAddress_is_default(), request_user_id,
                model.getSystem_version());

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

        Boolean result = memberAddressService.deleteByMember_address_idAndSystem_update_user_idValidateSystem_version(
                model.getMember_address_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_ADDRESS_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(MemberAddress.MEMBER_ADDRESS_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        MemberAddress model = getModel(MemberAddress.class);
        String request_app_id = getRequest_app_id();

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = memberAddressService.countByApp_idOrLikeMember_address_name(request_app_id,
                model.getMember_address_name());
        List<MemberAddress> resultList = memberAddressService.listByApp_idOrLikeMember_address_nameAndLimit(
                request_app_id, model.getMember_address_name(), getM(), getN());

        for (MemberAddress result : resultList) {
            User user = userService.findByUser_id(result.getUser_id());
            if (user != null) {
                result.put(User.USER_NAME, user.getUser_name());
            }
            result.keep(MemberAddress.MEMBER_ADDRESS_ID, MemberAddress.SYSTEM_VERSION, MemberAddress.MEMBER_ID,
                    MemberAddress.USER_ID, User.USER_NAME, MemberAddress.APP_ID, MemberAddress.MEMBER_ADDRESS_NAME,
                    MemberAddress.MEMBER_ADDRESS_TEL, MemberAddress.MEMBER_ADDRESS_TEL,
                    MemberAddress.MEMBER_ADDRESS_MOBILE, MemberAddress.MEMBER_ADDRESS_POSTCODE,
                    MemberAddress.MEMBER_ADDRESS_PROVINCE, MemberAddress.MEMBER_ADDRESS_CITY,
                    MemberAddress.MEMBER_ADDRESS_AREA, MemberAddress.MEMBER_ADDRESS_ADDRESS,
                    MemberAddress.ADDRESS_IS_DEFAULT);
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

        User user = userService.findByUser_id(member_address.getUser_id());
        if (user != null) {
            member_address.put(User.USER_NAME, user.getUser_name());
        }

        authenticateApp_id(member_address.getApp_id());

        member_address.keep(MemberAddress.MEMBER_ADDRESS_ID, MemberAddress.SYSTEM_VERSION, MemberAddress.MEMBER_ID,
                MemberAddress.USER_ID, User.USER_NAME, MemberAddress.APP_ID, MemberAddress.MEMBER_ADDRESS_NAME,
                MemberAddress.MEMBER_ADDRESS_TEL, MemberAddress.MEMBER_ADDRESS_TEL, MemberAddress.MEMBER_ADDRESS_MOBILE,
                MemberAddress.MEMBER_ADDRESS_POSTCODE, MemberAddress.MEMBER_ADDRESS_PROVINCE,
                MemberAddress.MEMBER_ADDRESS_CITY, MemberAddress.MEMBER_ADDRESS_AREA,
                MemberAddress.MEMBER_ADDRESS_ADDRESS, MemberAddress.ADDRESS_IS_DEFAULT);

        renderSuccessJson(member_address);
    }

    @ActionKey(Url.MEMBER_ADDRESS_ADMIN_SAVE)
    public void adminSave() {
        save();
    }

    @ActionKey(Url.MEMBER_ADDRESS_ADMIN_UPDATE)
    public void adminUpdate() {
        validateRequest_app_id();
        validate(MemberAddress.MEMBER_ADDRESS_ID, MemberAddress.MEMBER_ID, MemberAddress.USER_ID,
                MemberAddress.MEMBER_ADDRESS_NAME, MemberAddress.MEMBER_ADDRESS_TEL,
                MemberAddress.MEMBER_ADDRESS_MOBILE, MemberAddress.MEMBER_ADDRESS_POSTCODE,
                MemberAddress.MEMBER_ADDRESS_PROVINCE, MemberAddress.MEMBER_ADDRESS_CITY,
                MemberAddress.MEMBER_ADDRESS_AREA, MemberAddress.MEMBER_ADDRESS_ADDRESS,
                MemberAddress.ADDRESS_IS_DEFAULT, MemberAddress.SYSTEM_VERSION);

        MemberAddress model = getModel(MemberAddress.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        MemberAddress member_address = memberAddressService.findByMember_address_id(model.getMember_address_id());

        authenticateApp_id(member_address.getApp_id());

        Boolean result = memberAddressService.updateValidateSystem_version(model.getMember_address_id(),
                model.getMember_id(), model.getUser_id(), model.getMember_address_name(), model.getMember_address_tel(),
                model.getMember_address_mobile(), model.getMember_address_postcode(),
                model.getMember_address_province(), model.getMember_address_city(), model.getMember_address_area(),
                model.getMember_address_address(), model.getAddress_is_default(), request_user_id,
                model.getSystem_version());

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

        Boolean result = memberAddressService.deleteByMember_address_idAndSystem_update_user_idValidateSystem_version(
                model.getMember_address_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_ADDRESS_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(MemberAddress.APP_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        MemberAddress model = getModel(MemberAddress.class);

        Integer total = memberAddressService.countByOrApp_idOrLikeMember_address_name(model.getApp_id(),
                model.getMember_address_name());
        List<MemberAddress> resultList = memberAddressService.listByOrApp_idOrLikeMember_address_nameAndLimit(
                model.getApp_id(), model.getMember_address_name(), getM(), getN());

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
        validate(MemberAddress.APP_ID, MemberAddress.MEMBER_ID, MemberAddress.USER_ID,
                MemberAddress.MEMBER_ADDRESS_NAME, MemberAddress.MEMBER_ADDRESS_TEL,
                MemberAddress.MEMBER_ADDRESS_MOBILE, MemberAddress.MEMBER_ADDRESS_POSTCODE,
                MemberAddress.MEMBER_ADDRESS_PROVINCE, MemberAddress.MEMBER_ADDRESS_CITY,
                MemberAddress.MEMBER_ADDRESS_AREA, MemberAddress.MEMBER_ADDRESS_ADDRESS,
                MemberAddress.ADDRESS_IS_DEFAULT);

        MemberAddress model = getModel(MemberAddress.class);
        String member_address_id = Util.getRandomUUID();
        String request_user_id = getRequest_user_id();

        Boolean result = memberAddressService.save(member_address_id, model.getApp_id(), model.getMember_id(),
                model.getUser_id(), model.getMember_address_name(), model.getMember_address_tel(),
                model.getMember_address_mobile(), model.getMember_address_postcode(),
                model.getMember_address_province(), model.getMember_address_city(), model.getMember_address_area(),
                model.getMember_address_address(), model.getAddress_is_default(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_ADDRESS_SYSTEM_UPDATE)
    public void systemUpdate() {
        validateRequest_app_id();
        validate(MemberAddress.MEMBER_ADDRESS_ID, MemberAddress.MEMBER_ID, MemberAddress.USER_ID,
                MemberAddress.MEMBER_ADDRESS_NAME, MemberAddress.MEMBER_ADDRESS_TEL,
                MemberAddress.MEMBER_ADDRESS_MOBILE, MemberAddress.MEMBER_ADDRESS_POSTCODE,
                MemberAddress.MEMBER_ADDRESS_PROVINCE, MemberAddress.MEMBER_ADDRESS_CITY,
                MemberAddress.MEMBER_ADDRESS_AREA, MemberAddress.MEMBER_ADDRESS_ADDRESS,
                MemberAddress.ADDRESS_IS_DEFAULT, MemberAddress.SYSTEM_VERSION);

        MemberAddress model = getModel(MemberAddress.class);
        String request_user_id = getRequest_user_id();

        Boolean result = memberAddressService.updateValidateSystem_version(model.getMember_address_id(),
                model.getMember_id(), model.getUser_id(), model.getMember_address_name(), model.getMember_address_tel(),
                model.getMember_address_mobile(), model.getMember_address_postcode(),
                model.getMember_address_province(), model.getMember_address_city(), model.getMember_address_area(),
                model.getMember_address_address(), model.getAddress_is_default(), request_user_id,
                model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_ADDRESS_SYSTEM_DELETE)
    public void systemDelete() {
        validateRequest_app_id();
        validate(MemberAddress.MEMBER_ADDRESS_ID, MemberAddress.SYSTEM_VERSION);

        MemberAddress model = getModel(MemberAddress.class);
        String request_user_id = getRequest_user_id();

        Boolean result = memberAddressService.deleteByMember_address_idAndSystem_update_user_idValidateSystem_version(
                model.getMember_address_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}