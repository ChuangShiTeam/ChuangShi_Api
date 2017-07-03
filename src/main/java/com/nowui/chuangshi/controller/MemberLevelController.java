package com.nowui.chuangshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.MemberLevel;
import com.nowui.chuangshi.service.MemberLevelService;
import com.nowui.chuangshi.util.Util;

import java.util.List;

public class MemberLevelController extends Controller {

    private final MemberLevelService memberLevelService = new MemberLevelService();

    @ActionKey(Url.MEMBER_LEVEL_LIST)
    public void list() {
        validateRequest_app_id();
        validate(Constant.PAGE_SIZE, Constant.FIRST_CREATE_TIME, Constant.LAST_CREATE_TIME);

        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();
        JSONObject jsonObject = getParameterJSONObject();

        authenticateRequest_app_idAndRequest_user_id();

        List<MemberLevel> resultList = memberLevelService.listByApp_idAndSystem_create_timeAndLimit(request_app_id, jsonObject.getDate(Constant.LAST_CREATE_TIME), 0, getN(), request_app_id, request_http_id, request_user_id);

        for (MemberLevel result : resultList) {
            result.keep(MemberLevel.MEMBER_LEVEL_ID, MemberLevel.MEMBER_LEVEL_NAME, MemberLevel.SYSTEM_VERSION);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.MEMBER_LEVEL_FIND)
    public void find() {
        validateRequest_app_id();
        validate(MemberLevel.MEMBER_LEVEL_ID);

        MemberLevel model = getModel(MemberLevel.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        MemberLevel member_level = memberLevelService.findByMember_level_id(model.getMember_level_id(), request_app_id, request_http_id, request_user_id);

        authenticateApp_id(member_level.getApp_id());
        authenticateSystem_create_user_id(member_level.getSystem_create_user_id());

        member_level.keep(MemberLevel.MEMBER_LEVEL_ID, MemberLevel.SYSTEM_VERSION);

        renderSuccessJson(member_level);
    }

    @ActionKey(Url.MEMBER_LEVEL_SAVE)
    public void save() {
        validateRequest_app_id();
        validate(MemberLevel.MEMBER_LEVEL_NAME, MemberLevel.MEMBER_LEVEL_VALUE, MemberLevel.MEMBER_LEVEL_SORT);

        MemberLevel model = getModel(MemberLevel.class);
        String member_level_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Boolean result = memberLevelService.save(member_level_id, request_app_id, model.getMember_level_name(), model.getMember_level_value(), model.getMember_level_sort(), request_user_id, request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_LEVEL_UPDATE)
    public void update() {
        validateRequest_app_id();
        validate(MemberLevel.MEMBER_LEVEL_ID, MemberLevel.MEMBER_LEVEL_NAME, MemberLevel.MEMBER_LEVEL_VALUE, MemberLevel.MEMBER_LEVEL_SORT, MemberLevel.SYSTEM_VERSION);

        MemberLevel model = getModel(MemberLevel.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        MemberLevel member_level = memberLevelService.findByMember_level_id(model.getMember_level_id(), request_app_id, request_http_id, request_user_id);

        authenticateApp_id(member_level.getApp_id());
        authenticateSystem_create_user_id(member_level.getSystem_create_user_id());

        Boolean result = memberLevelService.updateValidateSystem_version(model.getMember_level_id(), model.getMember_level_name(), model.getMember_level_value(), model.getMember_level_sort(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_LEVEL_DELETE)
    public void delete() {
        validateRequest_app_id();
        validate(MemberLevel.MEMBER_LEVEL_ID, MemberLevel.SYSTEM_VERSION);

        MemberLevel model = getModel(MemberLevel.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        MemberLevel member_level = memberLevelService.findByMember_level_id(model.getMember_level_id(), request_app_id, request_http_id, request_user_id);

        authenticateApp_id(member_level.getApp_id());
        authenticateSystem_create_user_id(member_level.getSystem_create_user_id());

        Boolean result = memberLevelService.deleteByMember_level_idAndSystem_update_user_idValidateSystem_version(model.getMember_level_id(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_LEVEL_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(MemberLevel.MEMBER_LEVEL_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        MemberLevel model = getModel(MemberLevel.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = memberLevelService.countByApp_idOrLikeMember_level_name(request_app_id, model.getMember_level_name(), request_app_id, request_http_id, request_user_id);
        List<MemberLevel> resultList = memberLevelService.listByApp_idOrLikeMember_level_nameAndLimit(request_app_id, model.getMember_level_name(), getM(), getN(), request_app_id, request_http_id, request_user_id);

        for (MemberLevel result : resultList) {
            result.keep(MemberLevel.MEMBER_LEVEL_ID, MemberLevel.MEMBER_LEVEL_NAME, MemberLevel.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.MEMBER_LEVEL_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(MemberLevel.MEMBER_LEVEL_ID);

        MemberLevel model = getModel(MemberLevel.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        MemberLevel member_level = memberLevelService.findByMember_level_id(model.getMember_level_id(), request_app_id, request_http_id, request_user_id);

        authenticateApp_id(member_level.getApp_id());

        member_level.keep(MemberLevel.MEMBER_LEVEL_ID, MemberLevel.MEMBER_LEVEL_NAME, MemberLevel.MEMBER_LEVEL_VALUE, MemberLevel.MEMBER_LEVEL_SORT, MemberLevel.SYSTEM_VERSION);

        renderSuccessJson(member_level);
    }

    @ActionKey(Url.MEMBER_LEVEL_ADMIN_SAVE)
    public void adminSave() {
        save();
    }

    @ActionKey(Url.MEMBER_LEVEL_ADMIN_UPDATE)
    public void adminUpdate() {
        validateRequest_app_id();
        validate(MemberLevel.MEMBER_LEVEL_ID, MemberLevel.MEMBER_LEVEL_NAME, MemberLevel.MEMBER_LEVEL_VALUE, MemberLevel.MEMBER_LEVEL_SORT, MemberLevel.SYSTEM_VERSION);

        MemberLevel model = getModel(MemberLevel.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        MemberLevel member_level = memberLevelService.findByMember_level_id(model.getMember_level_id(), request_app_id, request_http_id, request_user_id);

        authenticateApp_id(member_level.getApp_id());

        Boolean result = memberLevelService.updateValidateSystem_version(model.getMember_level_id(), model.getMember_level_name(), model.getMember_level_value(), model.getMember_level_sort(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_LEVEL_ADMIN_DELETE)
    public void adminDelete() {
        validateRequest_app_id();
        validate(MemberLevel.MEMBER_LEVEL_ID, MemberLevel.SYSTEM_VERSION);

        MemberLevel model = getModel(MemberLevel.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        MemberLevel member_level = memberLevelService.findByMember_level_id(model.getMember_level_id(), request_app_id, request_http_id, request_user_id);

        authenticateApp_id(member_level.getApp_id());

        Boolean result = memberLevelService.deleteByMember_level_idAndSystem_update_user_idValidateSystem_version(model.getMember_level_id(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_LEVEL_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(MemberLevel.APP_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        MemberLevel model = getModel(MemberLevel.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        Integer total = memberLevelService.countByOrApp_idOrLikeMember_level_name(model.getApp_id(), model.getMember_level_name(), request_app_id, request_http_id, request_user_id);
        List<MemberLevel> resultList = memberLevelService.listByOrApp_idOrLikeMember_level_nameAndLimit(model.getApp_id(), model.getMember_level_name(), getM(), getN(), request_app_id, request_http_id, request_user_id);

        for (MemberLevel result : resultList) {
            result.keep(MemberLevel.MEMBER_LEVEL_ID, MemberLevel.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.MEMBER_LEVEL_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(MemberLevel.MEMBER_LEVEL_ID);

        MemberLevel model = getModel(MemberLevel.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        MemberLevel member_level = memberLevelService.findByMember_level_id(model.getMember_level_id(), request_app_id, request_http_id, request_user_id);

        member_level.keep(MemberLevel.MEMBER_LEVEL_ID, MemberLevel.SYSTEM_VERSION);

        renderSuccessJson(member_level);
    }

    @ActionKey(Url.MEMBER_LEVEL_SYSTEM_SAVE)
    public void systemSave() {
        validateRequest_app_id();
        validate(MemberLevel.APP_ID, MemberLevel.MEMBER_LEVEL_NAME, MemberLevel.MEMBER_LEVEL_VALUE, MemberLevel.MEMBER_LEVEL_SORT);

        MemberLevel model = getModel(MemberLevel.class);
        String member_level_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        Boolean result = memberLevelService.save(member_level_id, model.getApp_id(), model.getMember_level_name(), model.getMember_level_value(), model.getMember_level_sort(), request_user_id, request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_LEVEL_SYSTEM_UPDATE)
    public void systemUpdate() {
        validateRequest_app_id();
        validate(MemberLevel.MEMBER_LEVEL_ID, MemberLevel.MEMBER_LEVEL_NAME, MemberLevel.MEMBER_LEVEL_VALUE, MemberLevel.MEMBER_LEVEL_SORT, MemberLevel.SYSTEM_VERSION);

        MemberLevel model = getModel(MemberLevel.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        Boolean result = memberLevelService.updateValidateSystem_version(model.getMember_level_id(), model.getMember_level_name(), model.getMember_level_value(), model.getMember_level_sort(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_LEVEL_SYSTEM_DELETE)
    public void systemDelete() {
        validateRequest_app_id();
        validate(MemberLevel.MEMBER_LEVEL_ID, MemberLevel.SYSTEM_VERSION);

        MemberLevel model = getModel(MemberLevel.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        Boolean result = memberLevelService.deleteByMember_level_idAndSystem_update_user_idValidateSystem_version(model.getMember_level_id(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

}