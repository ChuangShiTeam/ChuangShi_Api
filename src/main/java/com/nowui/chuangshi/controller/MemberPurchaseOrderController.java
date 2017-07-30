package com.nowui.chuangshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.MemberPurchaseOrder;
import com.nowui.chuangshi.model.User;
import com.nowui.chuangshi.service.MemberPurchaseOrderService;
import com.nowui.chuangshi.util.Util;

import java.util.List;

public class MemberPurchaseOrderController extends Controller {

    private final MemberPurchaseOrderService memberPurchaseOrderService = new MemberPurchaseOrderService();

    @ActionKey(Url.MEMBER_PURCHASE_ORDER_LIST)
    public void list() {
        validateRequest_app_id();
        validate(Constant.PAGE_SIZE, Constant.FIRST_CREATE_TIME, Constant.LAST_CREATE_TIME);

        String request_app_id = getRequest_app_id();
        JSONObject jsonObject = getParameterJSONObject();

        authenticateRequest_app_idAndRequest_user_id();

        List<MemberPurchaseOrder> resultList = memberPurchaseOrderService.listByApp_idAndSystem_create_timeAndLimit(request_app_id, jsonObject.getDate(Constant.LAST_CREATE_TIME), 0, getN());

        for (MemberPurchaseOrder result : resultList) {
            result.keep(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_ID, MemberPurchaseOrder.SYSTEM_VERSION);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.MEMBER_PURCHASE_ORDER_FIND)
    public void find() {
        validateRequest_app_id();
        validate(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_ID);

        MemberPurchaseOrder model = getModel(MemberPurchaseOrder.class);

        authenticateRequest_app_idAndRequest_user_id();

        MemberPurchaseOrder member_purchase_order = memberPurchaseOrderService.findByMember_purchase_order_id(model.getMember_purchase_order_id());

        authenticateApp_id(member_purchase_order.getApp_id());
        authenticateSystem_create_user_id(member_purchase_order.getSystem_create_user_id());

        member_purchase_order.keep(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_ID, MemberPurchaseOrder.SYSTEM_VERSION);

        renderSuccessJson(member_purchase_order);
    }

    @ActionKey(Url.MEMBER_PURCHASE_ORDER_SAVE)
    public void save() {
        validateRequest_app_id();
        validate(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_USER_ID, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_AMOUNT, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_TOTAL_QUANTITY, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_NAME, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_MOBILE, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_PROVINCE, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_CITY, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_AREA, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_ADDRESS, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_EXPRESS_PAY_WAY, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_EXPRESS_SHIPPER_CODE, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_WAREHOUSE_RECEIVE, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_PAY, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_FLOW, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_COMPLETE);

        MemberPurchaseOrder model = getModel(MemberPurchaseOrder.class);
        String member_purchase_order_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Boolean result = memberPurchaseOrderService.save(member_purchase_order_id, request_app_id, model.getMember_purchase_order_user_id(), model.getMember_purchase_order_amount(), model.getMember_purchase_order_total_quantity(), model.getMember_purchase_order_receiver_name(), model.getMember_purchase_order_receiver_mobile(), model.getMember_purchase_order_receiver_province(), model.getMember_purchase_order_receiver_city(), model.getMember_purchase_order_receiver_area(), model.getMember_purchase_order_receiver_address(), model.getMember_purchase_order_express_pay_way(), model.getMember_purchase_order_express_shipper_code(), model.getMember_purchase_order_is_warehouse_receive(), model.getMember_purchase_order_is_pay(), model.getMember_purchase_order_flow(), model.getMember_purchase_order_is_complete(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_PURCHASE_ORDER_UPDATE)
    public void update() {
        validateRequest_app_id();
        validate(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_ID, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_USER_ID, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_AMOUNT, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_TOTAL_QUANTITY, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_NAME, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_MOBILE, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_PROVINCE, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_CITY, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_AREA, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_ADDRESS, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_EXPRESS_PAY_WAY, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_EXPRESS_SHIPPER_CODE, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_WAREHOUSE_RECEIVE, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_PAY, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_FLOW, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_COMPLETE, MemberPurchaseOrder.SYSTEM_VERSION);

        MemberPurchaseOrder model = getModel(MemberPurchaseOrder.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        MemberPurchaseOrder member_purchase_order = memberPurchaseOrderService.findByMember_purchase_order_id(model.getMember_purchase_order_id());

        authenticateApp_id(member_purchase_order.getApp_id());
        authenticateSystem_create_user_id(member_purchase_order.getSystem_create_user_id());

        Boolean result = memberPurchaseOrderService.updateValidateSystem_version(model.getMember_purchase_order_id(), model.getMember_purchase_order_user_id(), model.getMember_purchase_order_amount(), model.getMember_purchase_order_total_quantity(), model.getMember_purchase_order_receiver_name(), model.getMember_purchase_order_receiver_mobile(), model.getMember_purchase_order_receiver_province(), model.getMember_purchase_order_receiver_city(), model.getMember_purchase_order_receiver_area(), model.getMember_purchase_order_receiver_address(), model.getMember_purchase_order_express_pay_way(), model.getMember_purchase_order_express_shipper_code(), model.getMember_purchase_order_is_warehouse_receive(), model.getMember_purchase_order_is_pay(), model.getMember_purchase_order_flow(), model.getMember_purchase_order_is_complete(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_PURCHASE_ORDER_DELETE)
    public void delete() {
        validateRequest_app_id();
        validate(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_ID, MemberPurchaseOrder.SYSTEM_VERSION);

        MemberPurchaseOrder model = getModel(MemberPurchaseOrder.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        MemberPurchaseOrder member_purchase_order = memberPurchaseOrderService.findByMember_purchase_order_id(model.getMember_purchase_order_id());

        authenticateApp_id(member_purchase_order.getApp_id());
        authenticateSystem_create_user_id(member_purchase_order.getSystem_create_user_id());

        Boolean result = memberPurchaseOrderService.deleteByMember_purchase_order_idAndSystem_update_user_idValidateSystem_version(model.getMember_purchase_order_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_PURCHASE_ORDER_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        MemberPurchaseOrder model = getModel(MemberPurchaseOrder.class);
        String request_app_id = getRequest_app_id();
        JSONObject jsonObject = getParameterJSONObject();
        String user_name = jsonObject.getString(User.USER_NAME);

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = memberPurchaseOrderService.countByApp_idOrLikeUser_name(request_app_id, user_name);
        List<MemberPurchaseOrder> resultList = memberPurchaseOrderService.listByApp_idOrLikeUser_nameAndLimit(request_app_id, user_name, getM(), getN());

        for (MemberPurchaseOrder result : resultList) {
            result.keep(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_ID, MemberPurchaseOrder.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.MEMBER_PURCHASE_ORDER_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_ID);

        MemberPurchaseOrder model = getModel(MemberPurchaseOrder.class);

        authenticateRequest_app_idAndRequest_user_id();

        MemberPurchaseOrder member_purchase_order = memberPurchaseOrderService.findByMember_purchase_order_id(model.getMember_purchase_order_id());

        authenticateApp_id(member_purchase_order.getApp_id());

        member_purchase_order.keep(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_ID, MemberPurchaseOrder.SYSTEM_VERSION);

        renderSuccessJson(member_purchase_order);
    }

    @ActionKey(Url.MEMBER_PURCHASE_ORDER_ADMIN_SAVE)
    public void adminSave() {
        save();
    }

    @ActionKey(Url.MEMBER_PURCHASE_ORDER_ADMIN_UPDATE)
    public void adminUpdate() {
        validateRequest_app_id();
        validate(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_ID, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_USER_ID, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_AMOUNT, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_TOTAL_QUANTITY, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_NAME, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_MOBILE, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_PROVINCE, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_CITY, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_AREA, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_ADDRESS, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_EXPRESS_PAY_WAY, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_EXPRESS_SHIPPER_CODE, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_WAREHOUSE_RECEIVE, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_PAY, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_FLOW, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_COMPLETE, MemberPurchaseOrder.SYSTEM_VERSION);

        MemberPurchaseOrder model = getModel(MemberPurchaseOrder.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        MemberPurchaseOrder member_purchase_order = memberPurchaseOrderService.findByMember_purchase_order_id(model.getMember_purchase_order_id());

        authenticateApp_id(member_purchase_order.getApp_id());

        Boolean result = memberPurchaseOrderService.updateValidateSystem_version(model.getMember_purchase_order_id(), model.getMember_purchase_order_user_id(), model.getMember_purchase_order_amount(), model.getMember_purchase_order_total_quantity(), model.getMember_purchase_order_receiver_name(), model.getMember_purchase_order_receiver_mobile(), model.getMember_purchase_order_receiver_province(), model.getMember_purchase_order_receiver_city(), model.getMember_purchase_order_receiver_area(), model.getMember_purchase_order_receiver_address(), model.getMember_purchase_order_express_pay_way(), model.getMember_purchase_order_express_shipper_code(), model.getMember_purchase_order_is_warehouse_receive(), model.getMember_purchase_order_is_pay(), model.getMember_purchase_order_flow(), model.getMember_purchase_order_is_complete(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_PURCHASE_ORDER_ADMIN_DELETE)
    public void adminDelete() {
        validateRequest_app_id();
        validate(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_ID, MemberPurchaseOrder.SYSTEM_VERSION);

        MemberPurchaseOrder model = getModel(MemberPurchaseOrder.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        MemberPurchaseOrder member_purchase_order = memberPurchaseOrderService.findByMember_purchase_order_id(model.getMember_purchase_order_id());

        authenticateApp_id(member_purchase_order.getApp_id());

        Boolean result = memberPurchaseOrderService.deleteByMember_purchase_order_idAndSystem_update_user_idValidateSystem_version(model.getMember_purchase_order_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_PURCHASE_ORDER_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(MemberPurchaseOrder.APP_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        MemberPurchaseOrder model = getModel(MemberPurchaseOrder.class);
        JSONObject jsonObject = getParameterJSONObject();
        String user_name = jsonObject.getString(User.USER_NAME);        

        Integer total = memberPurchaseOrderService.countByOrApp_idOrLikeUser_name(model.getApp_id(), user_name);
        List<MemberPurchaseOrder> resultList = memberPurchaseOrderService.listByOrApp_idOrLikeUser_nameAndLimit(model.getApp_id(), user_name, getM(), getN());

        for (MemberPurchaseOrder result : resultList) {
            result.keep(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_ID, MemberPurchaseOrder.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.MEMBER_PURCHASE_ORDER_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_ID);

        MemberPurchaseOrder model = getModel(MemberPurchaseOrder.class);

        MemberPurchaseOrder member_purchase_order = memberPurchaseOrderService.findByMember_purchase_order_id(model.getMember_purchase_order_id());

        member_purchase_order.keep(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_ID, MemberPurchaseOrder.SYSTEM_VERSION);

        renderSuccessJson(member_purchase_order);
    }

    @ActionKey(Url.MEMBER_PURCHASE_ORDER_SYSTEM_SAVE)
    public void systemSave() {
        validateRequest_app_id();
        validate(MemberPurchaseOrder.APP_ID, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_USER_ID, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_AMOUNT, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_TOTAL_QUANTITY, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_NAME, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_MOBILE, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_PROVINCE, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_CITY, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_AREA, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_ADDRESS, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_EXPRESS_PAY_WAY, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_EXPRESS_SHIPPER_CODE, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_WAREHOUSE_RECEIVE, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_PAY, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_FLOW, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_COMPLETE);

        MemberPurchaseOrder model = getModel(MemberPurchaseOrder.class);
        String member_purchase_order_id = Util.getRandomUUID();
        String request_user_id = getRequest_user_id();

        Boolean result = memberPurchaseOrderService.save(member_purchase_order_id, model.getApp_id(), model.getMember_purchase_order_user_id(), model.getMember_purchase_order_amount(), model.getMember_purchase_order_total_quantity(), model.getMember_purchase_order_receiver_name(), model.getMember_purchase_order_receiver_mobile(), model.getMember_purchase_order_receiver_province(), model.getMember_purchase_order_receiver_city(), model.getMember_purchase_order_receiver_area(), model.getMember_purchase_order_receiver_address(), model.getMember_purchase_order_express_pay_way(), model.getMember_purchase_order_express_shipper_code(), model.getMember_purchase_order_is_warehouse_receive(), model.getMember_purchase_order_is_pay(), model.getMember_purchase_order_flow(), model.getMember_purchase_order_is_complete(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_PURCHASE_ORDER_SYSTEM_UPDATE)
    public void systemUpdate() {
        validateRequest_app_id();
        validate(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_ID, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_USER_ID, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_AMOUNT, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_TOTAL_QUANTITY, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_NAME, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_MOBILE, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_PROVINCE, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_CITY, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_AREA, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_ADDRESS, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_EXPRESS_PAY_WAY, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_EXPRESS_SHIPPER_CODE, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_WAREHOUSE_RECEIVE, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_PAY, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_FLOW, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_COMPLETE, MemberPurchaseOrder.SYSTEM_VERSION);

        MemberPurchaseOrder model = getModel(MemberPurchaseOrder.class);
        String request_user_id = getRequest_user_id();

        Boolean result = memberPurchaseOrderService.updateValidateSystem_version(model.getMember_purchase_order_id(), model.getMember_purchase_order_user_id(), model.getMember_purchase_order_amount(), model.getMember_purchase_order_total_quantity(), model.getMember_purchase_order_receiver_name(), model.getMember_purchase_order_receiver_mobile(), model.getMember_purchase_order_receiver_province(), model.getMember_purchase_order_receiver_city(), model.getMember_purchase_order_receiver_area(), model.getMember_purchase_order_receiver_address(), model.getMember_purchase_order_express_pay_way(), model.getMember_purchase_order_express_shipper_code(), model.getMember_purchase_order_is_warehouse_receive(), model.getMember_purchase_order_is_pay(), model.getMember_purchase_order_flow(), model.getMember_purchase_order_is_complete(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_PURCHASE_ORDER_SYSTEM_DELETE)
    public void systemDelete() {
        validateRequest_app_id();
        validate(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_ID, MemberPurchaseOrder.SYSTEM_VERSION);

        MemberPurchaseOrder model = getModel(MemberPurchaseOrder.class);
        String request_user_id = getRequest_user_id();

        Boolean result = memberPurchaseOrderService.deleteByMember_purchase_order_idAndSystem_update_user_idValidateSystem_version(model.getMember_purchase_order_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}