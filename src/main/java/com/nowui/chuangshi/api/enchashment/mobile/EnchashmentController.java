package com.nowui.chuangshi.api.enchashment.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.bill.model.Bill;
import com.nowui.chuangshi.api.enchashment.model.Enchashment;
import com.nowui.chuangshi.api.enchashment.service.EnchashmentService;
import com.nowui.chuangshi.api.trade.model.MemberDeliveryOrder;
import com.nowui.chuangshi.api.trade.model.MemberPurchaseOrder;
import com.nowui.chuangshi.api.trade.service.MemberDeliveryOrderService;
import com.nowui.chuangshi.api.trade.service.MemberPurchaseOrderService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerKey("/mobile/enchashment")
public class EnchashmentController extends Controller {

    @ActionKey("/mobile/enchashment/list")
    public void list() {
        String request_user_id = getRequest_user_id();

        Map<String, Object> result = new HashMap<String, Object>();

        BigDecimal bill_amount = BigDecimal.ZERO;
        List<MemberPurchaseOrder> memberPurchaseOrderList = MemberPurchaseOrderService.instance.userIsPayList(request_user_id);
        for (MemberPurchaseOrder memberPurchaseOrder : memberPurchaseOrderList) {
            bill_amount = bill_amount.add(memberPurchaseOrder.getMember_purchase_order_amount());
        }

        List<Enchashment> enchashmentList = EnchashmentService.instance.userList(request_user_id);
        for (Enchashment enchashment : enchashmentList) {
            bill_amount = bill_amount.subtract(enchashment.getEnchashment_amount());

            enchashment.keep(Enchashment.ENCHASHMENT_ID, Enchashment.ENCHASHMENT_AMOUNT, Enchashment.SYSTEM_STATUS, Enchashment.SYSTEM_CREATE_TIME);
        }
        result.put(Bill.BILL_AMOUNT, bill_amount);

        result.put(Constant.LIST, enchashmentList);

        validateResponse(Bill.BILL_AMOUNT, Constant.LIST);

        renderSuccessJson(result);
    }

    @ActionKey("/mobile/enchashment/find")
    public void find() {
        String request_user_id = getRequest_user_id();

        Map<String, Object> result = new HashMap<String, Object>();
        BigDecimal bill_amount = BigDecimal.ZERO;
        List<MemberDeliveryOrder> memberPurchaseOrderList = MemberDeliveryOrderService.instance.userIsPayList(request_user_id);
        for (MemberDeliveryOrder memberDeliveryOrder : memberPurchaseOrderList) {
            bill_amount = bill_amount.add(memberDeliveryOrder.getMember_delivery_order_amount());
        }

        List<Enchashment> enchashmentList = EnchashmentService.instance.userList(request_user_id);
        for (Enchashment enchashment : enchashmentList) {
            bill_amount = bill_amount.subtract(enchashment.getEnchashment_amount());
        }
        result.put(Bill.BILL_AMOUNT, bill_amount);

        validateResponse(Bill.BILL_AMOUNT);

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

        Boolean result = EnchashmentService.instance.save(model, request_user_id);

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