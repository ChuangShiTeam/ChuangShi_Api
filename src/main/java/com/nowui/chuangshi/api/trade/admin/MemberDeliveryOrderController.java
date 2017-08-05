package com.nowui.chuangshi.api.trade.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.trade.model.MemberDeliveryOrder;
import com.nowui.chuangshi.api.trade.service.MemberDeliveryOrderService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/member/delivery/order")
public class MemberDeliveryOrderController extends Controller {

    private final MemberDeliveryOrderService memberDeliveryOrderService = MemberDeliveryOrderService.me;

    @ActionKey("/admin/member/delivery/order/list")
    public void list() {
        validateRequest(MemberDeliveryOrder.USER_ID, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_NAME, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_MOBILE, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        MemberDeliveryOrder model = getModel(MemberDeliveryOrder.class);
        model.where(MemberDeliveryOrder.APP_ID).andEmpty(MemberDeliveryOrder.USER_ID).andEmpty(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_NAME).andEmpty(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_MOBILE);

        Integer resultCount = memberDeliveryOrderService.count(model);
        List<MemberDeliveryOrder> resultList = memberDeliveryOrderService.list(model.paginate());

        validateResponse(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, MemberDeliveryOrder.USER_ID, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_AMOUNT, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_TOTAL_QUANTITY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_NAME, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_MOBILE, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_PROVINCE, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_CITY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_AREA, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_ADDRESS, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_EXPRESS_PAY_WAY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_PAY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_FLOW, MemberDeliveryOrder.SYSTEM_VERSION);

        renderSuccessModeListlJson(resultCount, resultList);
    }

    @ActionKey("/admin/member/delivery/order/find")
    public void find() {
        validateRequest(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID);

        MemberDeliveryOrder model = getModel(MemberDeliveryOrder.class);
        model.where(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID);

        MemberDeliveryOrder result = memberDeliveryOrderService.find(model);

        validateResponse(MemberDeliveryOrder.MEMBER_PURCHASE_ORDER_ID, MemberDeliveryOrder.USER_ID, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_AMOUNT, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_TOTAL_QUANTITY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_NAME, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_MOBILE, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_PROVINCE, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_CITY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_AREA, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_ADDRESS, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_EXPRESS_PAY_WAY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_EXPRESS_SHIPPER_CODE, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_PAY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_WAREHOUSE_DELIVER, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_FLOW, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_COMPLETE, MemberDeliveryOrder.SYSTEM_VERSION);

        renderSuccessModelJson(result);
    }

    @ActionKey("/admin/member/delivery/order/save")
    public void save() {
        validateRequest(MemberDeliveryOrder.MEMBER_PURCHASE_ORDER_ID, MemberDeliveryOrder.USER_ID, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_AMOUNT, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_TOTAL_QUANTITY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_NAME, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_MOBILE, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_PROVINCE, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_CITY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_AREA, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_ADDRESS, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_EXPRESS_PAY_WAY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_EXPRESS_SHIPPER_CODE, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_PAY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_WAREHOUSE_DELIVER, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_FLOW, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_COMPLETE);

        MemberDeliveryOrder model = getModel(MemberDeliveryOrder.class);
        model.setMember_delivery_order_id(Util.getRandomUUID());

        Boolean result = memberDeliveryOrderService.save(model);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/member/delivery/order/update")
    public void update() {
        validateRequest(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, MemberDeliveryOrder.MEMBER_PURCHASE_ORDER_ID, MemberDeliveryOrder.USER_ID, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_AMOUNT, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_TOTAL_QUANTITY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_NAME, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_MOBILE, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_PROVINCE, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_CITY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_AREA, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_RECEIVER_ADDRESS, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_EXPRESS_PAY_WAY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_EXPRESS_SHIPPER_CODE, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_PAY, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_WAREHOUSE_DELIVER, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_FLOW, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_COMPLETE, MemberDeliveryOrder.SYSTEM_VERSION);

        MemberDeliveryOrder model = getModel(MemberDeliveryOrder.class);
        model.where(model.MEMBER_DELIVERY_ORDER_ID).and(MemberDeliveryOrder.SYSTEM_VERSION);

        Boolean result = memberDeliveryOrderService.update(model);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/member/delivery/order/delete")
    public void delete() {
        validateRequest(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID, MemberDeliveryOrder.SYSTEM_VERSION);

        MemberDeliveryOrder model = getModel(MemberDeliveryOrder.class);
        model.where(model.MEMBER_DELIVERY_ORDER_ID).and(MemberDeliveryOrder.SYSTEM_VERSION);

        Boolean result = memberDeliveryOrderService.delete(model);

        renderSuccessJson(result);
    }

}