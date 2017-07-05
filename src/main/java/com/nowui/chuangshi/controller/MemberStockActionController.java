package com.nowui.chuangshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.MemberStockAction;
import com.nowui.chuangshi.service.MemberStockActionService;
import com.nowui.chuangshi.util.Util;

import java.util.List;

public class MemberStockActionController extends Controller {

    private final MemberStockActionService memberStockActionService = new MemberStockActionService();

    @ActionKey(Url.MEMBER_STOCK_ACTION_LIST)
    public void list() {
        validateRequest_app_id();
        validate(Constant.PAGE_SIZE, Constant.FIRST_CREATE_TIME, Constant.LAST_CREATE_TIME);

        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();
        JSONObject jsonObject = getParameterJSONObject();

        authenticateRequest_app_idAndRequest_user_id();

        List<MemberStockAction> resultList = memberStockActionService.listByApp_idAndSystem_create_timeAndLimit(request_app_id, jsonObject.getDate(Constant.LAST_CREATE_TIME), 0, getN(), request_app_id, request_http_id, request_user_id);

        for (MemberStockAction result : resultList) {
            result.keep(MemberStockAction.MEMBER_STOCK_ACTION_ID, MemberStockAction.SYSTEM_VERSION);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.MEMBER_STOCK_ACTION_FIND)
    public void find() {
        validateRequest_app_id();
        validate(MemberStockAction.MEMBER_STOCK_ACTION_ID);

        MemberStockAction model = getModel(MemberStockAction.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        MemberStockAction member_stock_action = memberStockActionService.findByMember_stock_action_id(model.getMember_stock_action_id(), request_app_id, request_http_id, request_user_id);

        authenticateApp_id(member_stock_action.getApp_id());
        authenticateSystem_create_user_id(member_stock_action.getSystem_create_user_id());

        member_stock_action.keep(MemberStockAction.MEMBER_STOCK_ACTION_ID, MemberStockAction.SYSTEM_VERSION);

        renderSuccessJson(member_stock_action);
    }

    @ActionKey(Url.MEMBER_STOCK_ACTION_SAVE)
    public void save() {
        validateRequest_app_id();
        validate(MemberStockAction.MEMBER_ID, MemberStockAction.USER_ID, MemberStockAction.PRODUCT_SKU_ID, MemberStockAction.MEMBER_STOCK_ACTION_NAME, MemberStockAction.MEMBER_STOCK_QUANTITY);

        MemberStockAction model = getModel(MemberStockAction.class);
        String member_stock_action_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Boolean result = memberStockActionService.save(member_stock_action_id, request_app_id, model.getMember_id(), model.getUser_id(), model.getProduct_sku_id(), model.getMember_stock_action_name(), model.getMember_stock_quantity(), request_user_id, request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_STOCK_ACTION_UPDATE)
    public void update() {
        validateRequest_app_id();
        validate(MemberStockAction.MEMBER_STOCK_ACTION_ID, MemberStockAction.MEMBER_ID, MemberStockAction.USER_ID, MemberStockAction.PRODUCT_SKU_ID, MemberStockAction.MEMBER_STOCK_ACTION_NAME, MemberStockAction.MEMBER_STOCK_QUANTITY, MemberStockAction.SYSTEM_VERSION);

        MemberStockAction model = getModel(MemberStockAction.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        MemberStockAction member_stock_action = memberStockActionService.findByMember_stock_action_id(model.getMember_stock_action_id(), request_app_id, request_http_id, request_user_id);

        authenticateApp_id(member_stock_action.getApp_id());
        authenticateSystem_create_user_id(member_stock_action.getSystem_create_user_id());

        Boolean result = memberStockActionService.updateValidateSystem_version(model.getMember_stock_action_id(), model.getMember_id(), model.getUser_id(), model.getProduct_sku_id(), model.getMember_stock_action_name(), model.getMember_stock_quantity(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_STOCK_ACTION_DELETE)
    public void delete() {
        validateRequest_app_id();
        validate(MemberStockAction.MEMBER_STOCK_ACTION_ID, MemberStockAction.SYSTEM_VERSION);

        MemberStockAction model = getModel(MemberStockAction.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        MemberStockAction member_stock_action = memberStockActionService.findByMember_stock_action_id(model.getMember_stock_action_id(), request_app_id, request_http_id, request_user_id);

        authenticateApp_id(member_stock_action.getApp_id());
        authenticateSystem_create_user_id(member_stock_action.getSystem_create_user_id());

        Boolean result = memberStockActionService.deleteByMember_stock_action_idAndSystem_update_user_idValidateSystem_version(model.getMember_stock_action_id(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_STOCK_ACTION_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(MemberStockAction.MEMBER_STOCK_ACTION_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        MemberStockAction model = getModel(MemberStockAction.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = memberStockActionService.countByApp_idOrLikeMember_stock_action_name(request_app_id, model.getMember_stock_action_name(), request_app_id, request_http_id, request_user_id);
        List<MemberStockAction> resultList = memberStockActionService.listByApp_idOrLikeMember_stock_action_nameAndLimit(request_app_id, model.getMember_stock_action_name(), getM(), getN(), request_app_id, request_http_id, request_user_id);

        for (MemberStockAction result : resultList) {
            result.keep(MemberStockAction.MEMBER_STOCK_ACTION_ID, MemberStockAction.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.MEMBER_STOCK_ACTION_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(MemberStockAction.MEMBER_STOCK_ACTION_ID);

        MemberStockAction model = getModel(MemberStockAction.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        MemberStockAction member_stock_action = memberStockActionService.findByMember_stock_action_id(model.getMember_stock_action_id(), request_app_id, request_http_id, request_user_id);

        authenticateApp_id(member_stock_action.getApp_id());

        member_stock_action.keep(MemberStockAction.MEMBER_STOCK_ACTION_ID, MemberStockAction.SYSTEM_VERSION);

        renderSuccessJson(member_stock_action);
    }

    @ActionKey(Url.MEMBER_STOCK_ACTION_ADMIN_SAVE)
    public void adminSave() {
        save();
    }

    @ActionKey(Url.MEMBER_STOCK_ACTION_ADMIN_UPDATE)
    public void adminUpdate() {
        validateRequest_app_id();
        validate(MemberStockAction.MEMBER_STOCK_ACTION_ID, MemberStockAction.MEMBER_ID, MemberStockAction.USER_ID, MemberStockAction.PRODUCT_SKU_ID, MemberStockAction.MEMBER_STOCK_ACTION_NAME, MemberStockAction.MEMBER_STOCK_QUANTITY, MemberStockAction.SYSTEM_VERSION);

        MemberStockAction model = getModel(MemberStockAction.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        MemberStockAction member_stock_action = memberStockActionService.findByMember_stock_action_id(model.getMember_stock_action_id(), request_app_id, request_http_id, request_user_id);

        authenticateApp_id(member_stock_action.getApp_id());

        Boolean result = memberStockActionService.updateValidateSystem_version(model.getMember_stock_action_id(), model.getMember_id(), model.getUser_id(), model.getProduct_sku_id(), model.getMember_stock_action_name(), model.getMember_stock_quantity(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_STOCK_ACTION_ADMIN_DELETE)
    public void adminDelete() {
        validateRequest_app_id();
        validate(MemberStockAction.MEMBER_STOCK_ACTION_ID, MemberStockAction.SYSTEM_VERSION);

        MemberStockAction model = getModel(MemberStockAction.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        MemberStockAction member_stock_action = memberStockActionService.findByMember_stock_action_id(model.getMember_stock_action_id(), request_app_id, request_http_id, request_user_id);

        authenticateApp_id(member_stock_action.getApp_id());

        Boolean result = memberStockActionService.deleteByMember_stock_action_idAndSystem_update_user_idValidateSystem_version(model.getMember_stock_action_id(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_STOCK_ACTION_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(MemberStockAction.APP_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        MemberStockAction model = getModel(MemberStockAction.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        Integer total = memberStockActionService.countByOrApp_idOrLikeMember_stock_action_name(model.getApp_id(), model.getMember_stock_action_name(), request_app_id, request_http_id, request_user_id);
        List<MemberStockAction> resultList = memberStockActionService.listByOrApp_idOrLikeMember_stock_action_nameAndLimit(model.getApp_id(), model.getMember_stock_action_name(), getM(), getN(), request_app_id, request_http_id, request_user_id);

        for (MemberStockAction result : resultList) {
            result.keep(MemberStockAction.MEMBER_STOCK_ACTION_ID, MemberStockAction.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.MEMBER_STOCK_ACTION_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(MemberStockAction.MEMBER_STOCK_ACTION_ID);

        MemberStockAction model = getModel(MemberStockAction.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        MemberStockAction member_stock_action = memberStockActionService.findByMember_stock_action_id(model.getMember_stock_action_id(), request_app_id, request_http_id, request_user_id);

        member_stock_action.keep(MemberStockAction.MEMBER_STOCK_ACTION_ID, MemberStockAction.SYSTEM_VERSION);

        renderSuccessJson(member_stock_action);
    }

    @ActionKey(Url.MEMBER_STOCK_ACTION_SYSTEM_SAVE)
    public void systemSave() {
        validateRequest_app_id();
        validate(MemberStockAction.APP_ID, MemberStockAction.MEMBER_ID, MemberStockAction.USER_ID, MemberStockAction.PRODUCT_SKU_ID, MemberStockAction.MEMBER_STOCK_ACTION_NAME, MemberStockAction.MEMBER_STOCK_QUANTITY);

        MemberStockAction model = getModel(MemberStockAction.class);
        String member_stock_action_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        Boolean result = memberStockActionService.save(member_stock_action_id, model.getApp_id(), model.getMember_id(), model.getUser_id(), model.getProduct_sku_id(), model.getMember_stock_action_name(), model.getMember_stock_quantity(), request_user_id, request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_STOCK_ACTION_SYSTEM_UPDATE)
    public void systemUpdate() {
        validateRequest_app_id();
        validate(MemberStockAction.MEMBER_STOCK_ACTION_ID, MemberStockAction.MEMBER_ID, MemberStockAction.USER_ID, MemberStockAction.PRODUCT_SKU_ID, MemberStockAction.MEMBER_STOCK_ACTION_NAME, MemberStockAction.MEMBER_STOCK_QUANTITY, MemberStockAction.SYSTEM_VERSION);

        MemberStockAction model = getModel(MemberStockAction.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        Boolean result = memberStockActionService.updateValidateSystem_version(model.getMember_stock_action_id(), model.getMember_id(), model.getUser_id(), model.getProduct_sku_id(), model.getMember_stock_action_name(), model.getMember_stock_quantity(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_STOCK_ACTION_SYSTEM_DELETE)
    public void systemDelete() {
        validateRequest_app_id();
        validate(MemberStockAction.MEMBER_STOCK_ACTION_ID, MemberStockAction.SYSTEM_VERSION);

        MemberStockAction model = getModel(MemberStockAction.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        Boolean result = memberStockActionService.deleteByMember_stock_action_idAndSystem_update_user_idValidateSystem_version(model.getMember_stock_action_id(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

}