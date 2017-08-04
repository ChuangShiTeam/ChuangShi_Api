package com.nowui.chuangshi.api.enchashment.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.bill.model.Bill;
import com.nowui.chuangshi.api.enchashment.model.Enchashment;
import com.nowui.chuangshi.api.enchashment.service.EnchashmentService;
import com.nowui.chuangshi.api.trade.model.MemberPurchaseOrder;
import com.nowui.chuangshi.api.trade.service.MemberPurchaseOrderService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerKey("/mobile/enchashment")
public class EnchashmentController extends Controller {

    private final EnchashmentService enchashmentService = new EnchashmentService();

    @ActionKey("/mobile/enchashment/list")
    public void list() {
        String request_user_id = getRequest_user_id();

        Map<String, Object> result = new HashMap<String, Object>();

        BigDecimal bill_amount = BigDecimal.ZERO;
        List<MemberPurchaseOrder> memberPurchaseOrderList = MemberPurchaseOrderService.me.list(new MemberPurchaseOrder().where(MemberPurchaseOrder.USER_ID, request_user_id).and(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_PAY, true));
        for (MemberPurchaseOrder memberPurchaseOrder : memberPurchaseOrderList) {
            bill_amount = bill_amount.add(memberPurchaseOrder.getMember_purchase_order_amount());
        }

        List<Enchashment> enchashmentList = EnchashmentService.me.list(new Enchashment().where(Enchashment.USER_ID, request_user_id));
        for (Enchashment enchashment : enchashmentList) {
            bill_amount = bill_amount.subtract(enchashment.getEnchashment_amount());

            enchashment.keep(Enchashment.ENCHASHMENT_ID, Enchashment.ENCHASHMENT_AMOUNT, Enchashment.SYSTEM_STATUS, Enchashment.SYSTEM_CREATE_TIME);
        }
        result.put(Bill.BILL_AMOUNT, bill_amount);

        result.put(Constant.LIST, enchashmentList);

        renderSuccessJson(result);
    }

    @ActionKey("/mobile/enchashment/find")
    public void find() {
        String request_user_id = getRequest_user_id();

        Map<String, Object> result = new HashMap<String, Object>();
        BigDecimal bill_amount = BigDecimal.ZERO;
        List<MemberPurchaseOrder> memberPurchaseOrderList = MemberPurchaseOrderService.me.list(new MemberPurchaseOrder().where(MemberPurchaseOrder.USER_ID, request_user_id).and(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_PAY, true));
        for (MemberPurchaseOrder memberPurchaseOrder : memberPurchaseOrderList) {
            bill_amount = bill_amount.add(memberPurchaseOrder.getMember_purchase_order_amount());
        }

        List<Enchashment> enchashmentList = EnchashmentService.me.list(new Enchashment().where(Enchashment.USER_ID, request_user_id));
        for (Enchashment enchashment : enchashmentList) {
            bill_amount = bill_amount.subtract(enchashment.getEnchashment_amount());
        }
        result.put(Bill.BILL_AMOUNT, bill_amount);

        renderSuccessJson(result);
    }

    @ActionKey("/mobile/enchashment/save")
    public void save() {
        validateRequest(Enchashment.ENCHASHMENT_AMOUNT);

        String request_user_id = getRequest_user_id();

        Enchashment model = getModel(Enchashment.class);
        model.setEnchashment_id(Util.getRandomUUID());
        model.setUser_id(request_user_id);
        model.setEnchashment_status(false);

        Boolean result = enchashmentService.save(model);

        renderSuccessJson(result);
    }

    @ActionKey("/mobile/enchashment/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/enchashment/delete")
    public void delete() {

        renderSuccessJson();
    }

}