package com.nowui.chuangshi.api.trade.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.trade.model.MemberPurchaseOrder;
import com.nowui.chuangshi.api.trade.service.MemberPurchaseOrderService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/member/purchase/order")
public class MemberPurchaseOrderController extends Controller {

    private final MemberPurchaseOrderService memberPurchaseOrderService = MemberPurchaseOrderService.me;

    @ActionKey("/admin/member/purchase/order/list")
    public void list() {
        validateRequest(MemberPurchaseOrder.USER_ID, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_WAREHOUSE_RECEIVE, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_PAY, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        MemberPurchaseOrder model = getModel(MemberPurchaseOrder.class);
        model.where(MemberPurchaseOrder.APP_ID).andEmpty(MemberPurchaseOrder.USER_ID).andEmpty(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_WAREHOUSE_RECEIVE).andEmpty(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_PAY);

        Integer resultCount = memberPurchaseOrderService.count(model);
        List<MemberPurchaseOrder> resultList = memberPurchaseOrderService.list(model.paginate());

        validateResponse(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_ID, MemberPurchaseOrder.USER_ID, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_PRODUCT_AMOUNT, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_EXPRESS_AMOUNT, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_DISCOUNT_AMOUNT, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_AMOUNT, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_TOTAL_QUANTITY, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_NAME, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_MOBILE, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_WAREHOUSE_RECEIVE, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_PAY, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_FLOW, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_COMPLETE, MemberPurchaseOrder.SYSTEM_VERSION);

        renderSuccessModeListlJson(resultCount, resultList);
    }

    @ActionKey("/admin/member/purchase/order/find")
    public void find() {
        validateRequest(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_ID);

        MemberPurchaseOrder model = getModel(MemberPurchaseOrder.class);
        model.where(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_ID);

        MemberPurchaseOrder result = memberPurchaseOrderService.find(model);

        validateResponse(MemberPurchaseOrder.USER_ID, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_PRODUCT_AMOUNT, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_EXPRESS_AMOUNT, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_DISCOUNT_AMOUNT, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_AMOUNT, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_TOTAL_QUANTITY, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_NAME, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_MOBILE, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_PROVINCE, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_CITY, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_AREA, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_ADDRESS, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_EXPRESS_PAY_WAY, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_EXPRESS_SHIPPER_CODE, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_WAREHOUSE_RECEIVE, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_PAY, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_FLOW, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_COMPLETE, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_MESSAGE, MemberPurchaseOrder.SYSTEM_VERSION);

        renderSuccessModelJson(result);
    }

    @ActionKey("/admin/member/purchase/order/save")
    public void save() {
        validateRequest(MemberPurchaseOrder.USER_ID, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_PRODUCT_AMOUNT, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_EXPRESS_AMOUNT, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_DISCOUNT_AMOUNT, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_AMOUNT, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_TOTAL_QUANTITY, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_NAME, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_MOBILE, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_PROVINCE, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_CITY, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_AREA, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_ADDRESS, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_EXPRESS_PAY_WAY, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_EXPRESS_SHIPPER_CODE, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_WAREHOUSE_RECEIVE, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_PAY, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_FLOW, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_COMPLETE, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_MESSAGE);

        MemberPurchaseOrder model = getModel(MemberPurchaseOrder.class);
        model.setMember_purchase_order_id(Util.getRandomUUID());

        Boolean result = memberPurchaseOrderService.save(model);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/member/purchase/order/update")
    public void update() {
        validateRequest(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_ID, MemberPurchaseOrder.USER_ID, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_PRODUCT_AMOUNT, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_EXPRESS_AMOUNT, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_DISCOUNT_AMOUNT, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_AMOUNT, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_TOTAL_QUANTITY, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_NAME, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_MOBILE, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_PROVINCE, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_CITY, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_AREA, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_ADDRESS, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_EXPRESS_PAY_WAY, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_EXPRESS_SHIPPER_CODE, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_WAREHOUSE_RECEIVE, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_PAY, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_FLOW, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_COMPLETE, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_MESSAGE, MemberPurchaseOrder.SYSTEM_VERSION);

        MemberPurchaseOrder model = getModel(MemberPurchaseOrder.class);
        model.where(model.MEMBER_PURCHASE_ORDER_ID).and(MemberPurchaseOrder.SYSTEM_VERSION);

        Boolean result = memberPurchaseOrderService.update(model);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/member/purchase/order/delete")
    public void delete() {
        validateRequest(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_ID, MemberPurchaseOrder.SYSTEM_VERSION);

        MemberPurchaseOrder model = getModel(MemberPurchaseOrder.class);
        model.where(model.MEMBER_PURCHASE_ORDER_ID).and(MemberPurchaseOrder.SYSTEM_VERSION);

        Boolean result = memberPurchaseOrderService.delete(model);

        renderSuccessJson(result);
    }

}