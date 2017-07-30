package com.nowui.chuangshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.MemberDeliveryOrder;
import com.nowui.chuangshi.model.User;
import com.nowui.chuangshi.service.MemberDeliveryOrderService;
import com.nowui.chuangshi.util.Util;

import java.util.List;

public class MemberDeliveryOrderController extends Controller {

    private final MemberDeliveryOrderService memberDeliveryOrderService = new MemberDeliveryOrderService();

    @ActionKey(Url.MEMBER_DELIVERY_ORDER_LIST)
    public void list() {
        validateRequest_app_id();
        validate(Constant.PAGE_SIZE, Constant.FIRST_CREATE_TIME, Constant.LAST_CREATE_TIME);

        String request_app_id = getRequest_app_id();
        JSONObject jsonObject = getParameterJSONObject();

        authenticateRequest_app_idAndRequest_user_id();

        List<MemberDeliveryOrder> resultList = memberDeliveryOrderService.listByApp_idAndSystem_create_timeAndLimit(request_app_id, jsonObject.getDate(Constant.LAST_CREATE_TIME), 0, getN());

        for (MemberDeliveryOrder result : resultList) {
            result.keep(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, MemberDeliveryOrder.SYSTEM_VERSION);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.MEMBER_DELIVERY_ORDER_FIND)
    public void find() {
        validateRequest_app_id();
        validate(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID);

        MemberDeliveryOrder model = getModel(MemberDeliveryOrder.class);

        authenticateRequest_app_idAndRequest_user_id();

        MemberDeliveryOrder member_delivery_order = memberDeliveryOrderService.findByMember_delivery_order_id(model.getMember_delivery_order_id());

        authenticateApp_id(member_delivery_order.getApp_id());
        authenticateSystem_create_user_id(member_delivery_order.getSystem_create_user_id());

        member_delivery_order.keep(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, MemberDeliveryOrder.SYSTEM_VERSION);

        renderSuccessJson(member_delivery_order);
    }

    @ActionKey(Url.MEMBER_DELIVERY_ORDER_SAVE)
    public void save() {
        validateRequest_app_id();
        validate(MemberDeliveryOrder.MEMBER_PURCHASE_ORDER_ID, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_USER_ID, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_AMOUNT, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_TOTAL_QUANTITY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_NAME, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_MOBILE, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_PROVINCE, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_CITY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_AREA, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_ADDRESS, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_EXPRESS_PAY_WAY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_EXPRESS_SHIPPER_CODE, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_PAY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_WAREHOUSE_DELIVER, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_FLOW, MemberDeliveryOrder.MEMBER_DELIVERY_IS_COMPLETE);

        MemberDeliveryOrder model = getModel(MemberDeliveryOrder.class);
        String member_delivery_order_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Boolean result = memberDeliveryOrderService.save(member_delivery_order_id, request_app_id, model.getMember_purchase_order_id(), model.getMember_delivery_order_user_id(), model.getMember_delivery_order_amount(), model.getMember_delivery_order_total_quantity(), model.getMember_delivery_order_receiver_name(), model.getMember_delivery_order_receiver_mobile(), model.getMember_delivery_order_receiver_province(), model.getMember_delivery_order_receiver_city(), model.getMember_delivery_order_receiver_area(), model.getMember_delivery_order_receiver_address(), model.getMember_delivery_order_express_pay_way(), model.getMember_delivery_order_express_shipper_code(), model.getMember_delivery_order_is_pay(), model.getMember_delivery_order_is_warehouse_deliver(), model.getMember_delivery_order_flow(), model.getMember_delivery_is_complete(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_DELIVERY_ORDER_UPDATE)
    public void update() {
        validateRequest_app_id();
        validate(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, MemberDeliveryOrder.MEMBER_PURCHASE_ORDER_ID, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_USER_ID, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_AMOUNT, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_TOTAL_QUANTITY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_NAME, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_MOBILE, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_PROVINCE, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_CITY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_AREA, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_ADDRESS, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_EXPRESS_PAY_WAY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_EXPRESS_SHIPPER_CODE, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_PAY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_WAREHOUSE_DELIVER, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_FLOW, MemberDeliveryOrder.MEMBER_DELIVERY_IS_COMPLETE, MemberDeliveryOrder.SYSTEM_VERSION);

        MemberDeliveryOrder model = getModel(MemberDeliveryOrder.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        MemberDeliveryOrder member_delivery_order = memberDeliveryOrderService.findByMember_delivery_order_id(model.getMember_delivery_order_id());

        authenticateApp_id(member_delivery_order.getApp_id());
        authenticateSystem_create_user_id(member_delivery_order.getSystem_create_user_id());

        Boolean result = memberDeliveryOrderService.updateValidateSystem_version(model.getMember_delivery_order_id(), model.getMember_purchase_order_id(), model.getMember_delivery_order_user_id(), model.getMember_delivery_order_amount(), model.getMember_delivery_order_total_quantity(), model.getMember_delivery_order_receiver_name(), model.getMember_delivery_order_receiver_mobile(), model.getMember_delivery_order_receiver_province(), model.getMember_delivery_order_receiver_city(), model.getMember_delivery_order_receiver_area(), model.getMember_delivery_order_receiver_address(), model.getMember_delivery_order_express_pay_way(), model.getMember_delivery_order_express_shipper_code(), model.getMember_delivery_order_is_pay(), model.getMember_delivery_order_is_warehouse_deliver(), model.getMember_delivery_order_flow(), model.getMember_delivery_is_complete(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_DELIVERY_ORDER_DELETE)
    public void delete() {
        validateRequest_app_id();
        validate(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, MemberDeliveryOrder.SYSTEM_VERSION);

        MemberDeliveryOrder model = getModel(MemberDeliveryOrder.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        MemberDeliveryOrder member_delivery_order = memberDeliveryOrderService.findByMember_delivery_order_id(model.getMember_delivery_order_id());

        authenticateApp_id(member_delivery_order.getApp_id());
        authenticateSystem_create_user_id(member_delivery_order.getSystem_create_user_id());

        Boolean result = memberDeliveryOrderService.deleteByMember_delivery_order_idAndSystem_update_user_idValidateSystem_version(model.getMember_delivery_order_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_DELIVERY_ORDER_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        MemberDeliveryOrder model = getModel(MemberDeliveryOrder.class);
        String request_app_id = getRequest_app_id();
        JSONObject jsonObject = getParameterJSONObject();
        String user_name = jsonObject.getString(User.USER_NAME);

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = memberDeliveryOrderService.countByApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_name(request_app_id, user_name, model.getMember_delivery_order_receiver_name());
        List<MemberDeliveryOrder> resultList = memberDeliveryOrderService.listByApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_nameAndLimit(request_app_id, user_name, model.getMember_delivery_order_receiver_name(), getM(), getN());

        for (MemberDeliveryOrder result : resultList) {
            result.keep(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, MemberDeliveryOrder.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.MEMBER_DELIVERY_ORDER_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID);

        MemberDeliveryOrder model = getModel(MemberDeliveryOrder.class);

        authenticateRequest_app_idAndRequest_user_id();

        MemberDeliveryOrder member_delivery_order = memberDeliveryOrderService.findByMember_delivery_order_id(model.getMember_delivery_order_id());

        authenticateApp_id(member_delivery_order.getApp_id());

        member_delivery_order.keep(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, MemberDeliveryOrder.SYSTEM_VERSION);

        renderSuccessJson(member_delivery_order);
    }

    @ActionKey(Url.MEMBER_DELIVERY_ORDER_ADMIN_SAVE)
    public void adminSave() {
        save();
    }

    @ActionKey(Url.MEMBER_DELIVERY_ORDER_ADMIN_UPDATE)
    public void adminUpdate() {
        validateRequest_app_id();
        validate(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, MemberDeliveryOrder.MEMBER_PURCHASE_ORDER_ID, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_USER_ID, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_AMOUNT, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_TOTAL_QUANTITY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_NAME, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_MOBILE, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_PROVINCE, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_CITY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_AREA, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_ADDRESS, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_EXPRESS_PAY_WAY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_EXPRESS_SHIPPER_CODE, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_PAY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_WAREHOUSE_DELIVER, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_FLOW, MemberDeliveryOrder.MEMBER_DELIVERY_IS_COMPLETE, MemberDeliveryOrder.SYSTEM_VERSION);

        MemberDeliveryOrder model = getModel(MemberDeliveryOrder.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        MemberDeliveryOrder member_delivery_order = memberDeliveryOrderService.findByMember_delivery_order_id(model.getMember_delivery_order_id());

        authenticateApp_id(member_delivery_order.getApp_id());

        Boolean result = memberDeliveryOrderService.updateValidateSystem_version(model.getMember_delivery_order_id(), model.getMember_purchase_order_id(), model.getMember_delivery_order_user_id(), model.getMember_delivery_order_amount(), model.getMember_delivery_order_total_quantity(), model.getMember_delivery_order_receiver_name(), model.getMember_delivery_order_receiver_mobile(), model.getMember_delivery_order_receiver_province(), model.getMember_delivery_order_receiver_city(), model.getMember_delivery_order_receiver_area(), model.getMember_delivery_order_receiver_address(), model.getMember_delivery_order_express_pay_way(), model.getMember_delivery_order_express_shipper_code(), model.getMember_delivery_order_is_pay(), model.getMember_delivery_order_is_warehouse_deliver(), model.getMember_delivery_order_flow(), model.getMember_delivery_is_complete(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_DELIVERY_ORDER_ADMIN_DELETE)
    public void adminDelete() {
        validateRequest_app_id();
        validate(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, MemberDeliveryOrder.SYSTEM_VERSION);

        MemberDeliveryOrder model = getModel(MemberDeliveryOrder.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        MemberDeliveryOrder member_delivery_order = memberDeliveryOrderService.findByMember_delivery_order_id(model.getMember_delivery_order_id());

        authenticateApp_id(member_delivery_order.getApp_id());

        Boolean result = memberDeliveryOrderService.deleteByMember_delivery_order_idAndSystem_update_user_idValidateSystem_version(model.getMember_delivery_order_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_DELIVERY_ORDER_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(MemberDeliveryOrder.APP_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        MemberDeliveryOrder model = getModel(MemberDeliveryOrder.class);
        JSONObject jsonObject = getParameterJSONObject();
        String user_name = jsonObject.getString(User.USER_NAME);

        Integer total = memberDeliveryOrderService.countByOrApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_name(model.getApp_id(), user_name, model.getMember_delivery_order_receiver_name());
        List<MemberDeliveryOrder> resultList = memberDeliveryOrderService.listByOrApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_nameAndLimit(model.getApp_id(), user_name, model.getMember_delivery_order_receiver_name(), getM(), getN());

        for (MemberDeliveryOrder result : resultList) {
            result.keep(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, MemberDeliveryOrder.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.MEMBER_DELIVERY_ORDER_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID);

        MemberDeliveryOrder model = getModel(MemberDeliveryOrder.class);

        MemberDeliveryOrder member_delivery_order = memberDeliveryOrderService.findByMember_delivery_order_id(model.getMember_delivery_order_id());

        member_delivery_order.keep(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, MemberDeliveryOrder.SYSTEM_VERSION);

        renderSuccessJson(member_delivery_order);
    }

    @ActionKey(Url.MEMBER_DELIVERY_ORDER_SYSTEM_SAVE)
    public void systemSave() {
        validateRequest_app_id();
        validate(MemberDeliveryOrder.APP_ID, MemberDeliveryOrder.MEMBER_PURCHASE_ORDER_ID, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_USER_ID, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_AMOUNT, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_TOTAL_QUANTITY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_NAME, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_MOBILE, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_PROVINCE, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_CITY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_AREA, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_ADDRESS, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_EXPRESS_PAY_WAY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_EXPRESS_SHIPPER_CODE, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_PAY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_WAREHOUSE_DELIVER, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_FLOW, MemberDeliveryOrder.MEMBER_DELIVERY_IS_COMPLETE);

        MemberDeliveryOrder model = getModel(MemberDeliveryOrder.class);
        String member_delivery_order_id = Util.getRandomUUID();
        String request_user_id = getRequest_user_id();

        Boolean result = memberDeliveryOrderService.save(member_delivery_order_id, model.getApp_id(), model.getMember_purchase_order_id(), model.getMember_delivery_order_user_id(), model.getMember_delivery_order_amount(), model.getMember_delivery_order_total_quantity(), model.getMember_delivery_order_receiver_name(), model.getMember_delivery_order_receiver_mobile(), model.getMember_delivery_order_receiver_province(), model.getMember_delivery_order_receiver_city(), model.getMember_delivery_order_receiver_area(), model.getMember_delivery_order_receiver_address(), model.getMember_delivery_order_express_pay_way(), model.getMember_delivery_order_express_shipper_code(), model.getMember_delivery_order_is_pay(), model.getMember_delivery_order_is_warehouse_deliver(), model.getMember_delivery_order_flow(), model.getMember_delivery_is_complete(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_DELIVERY_ORDER_SYSTEM_UPDATE)
    public void systemUpdate() {
        validateRequest_app_id();
        validate(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, MemberDeliveryOrder.MEMBER_PURCHASE_ORDER_ID, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_USER_ID, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_AMOUNT, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_TOTAL_QUANTITY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_NAME, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_MOBILE, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_PROVINCE, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_CITY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_AREA, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_ADDRESS, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_EXPRESS_PAY_WAY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_EXPRESS_SHIPPER_CODE, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_PAY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_WAREHOUSE_DELIVER, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_FLOW, MemberDeliveryOrder.MEMBER_DELIVERY_IS_COMPLETE, MemberDeliveryOrder.SYSTEM_VERSION);

        MemberDeliveryOrder model = getModel(MemberDeliveryOrder.class);
        String request_user_id = getRequest_user_id();

        Boolean result = memberDeliveryOrderService.updateValidateSystem_version(model.getMember_delivery_order_id(), model.getMember_purchase_order_id(), model.getMember_delivery_order_user_id(), model.getMember_delivery_order_amount(), model.getMember_delivery_order_total_quantity(), model.getMember_delivery_order_receiver_name(), model.getMember_delivery_order_receiver_mobile(), model.getMember_delivery_order_receiver_province(), model.getMember_delivery_order_receiver_city(), model.getMember_delivery_order_receiver_area(), model.getMember_delivery_order_receiver_address(), model.getMember_delivery_order_express_pay_way(), model.getMember_delivery_order_express_shipper_code(), model.getMember_delivery_order_is_pay(), model.getMember_delivery_order_is_warehouse_deliver(), model.getMember_delivery_order_flow(), model.getMember_delivery_is_complete(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_DELIVERY_ORDER_SYSTEM_DELETE)
    public void systemDelete() {
        validateRequest_app_id();
        validate(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, MemberDeliveryOrder.SYSTEM_VERSION);

        MemberDeliveryOrder model = getModel(MemberDeliveryOrder.class);
        String request_user_id = getRequest_user_id();

        Boolean result = memberDeliveryOrderService.deleteByMember_delivery_order_idAndSystem_update_user_idValidateSystem_version(model.getMember_delivery_order_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}