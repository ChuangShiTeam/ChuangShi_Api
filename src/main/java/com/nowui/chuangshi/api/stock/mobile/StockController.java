package com.nowui.chuangshi.api.stock.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.stock.model.Stock;
import com.nowui.chuangshi.api.stock.service.StockService;
import com.nowui.chuangshi.api.trade.model.MemberDeliveryOrder;
import com.nowui.chuangshi.api.trade.service.MemberDeliveryOrderService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.type.MemberDeliveryOrderFlow;
import com.nowui.chuangshi.type.StockType;

import java.util.List;

@ControllerKey("/mobile/stock")
public class StockController extends Controller {

    private final StockService stockService = new StockService();

    @ActionKey("/mobile/stock/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/stock/member/list")
    public void memberList() {
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();

        Integer stock_quantity = 0;

        List<Stock> stockList = StockService.me.list(Cnd.where(Stock.APP_ID, request_app_id).and(Stock.OBJECT_ID, request_user_id).and(Stock.STOCK_TYPE, StockType.MEMBER.getKey()));

        for (Stock stock : stockList) {
            stock_quantity += stock.getStock_quantity();
        }

        List<MemberDeliveryOrder> memberDeliveryOrderList = MemberDeliveryOrderService.me.list(Cnd.where(MemberDeliveryOrder.USER_ID, request_user_id).and(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_WAREHOUSE_DELIVER, true).and(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_FLOW, MemberDeliveryOrderFlow.WAIT_WAREHOUSE_SEND.getKey()));
        for (MemberDeliveryOrder memberDeliveryOrder : memberDeliveryOrderList) {
            stock_quantity -= memberDeliveryOrder.getMember_delivery_order_total_quantity();
        }

        List<MemberDeliveryOrder> memberDeliveryOrderList2 = MemberDeliveryOrderService.me.list(Cnd.where(MemberDeliveryOrder.USER_ID, request_user_id).and(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_WAREHOUSE_DELIVER, true).and(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_FLOW, MemberDeliveryOrderFlow.WAIT_RECEIVE.getKey()));
        for (MemberDeliveryOrder memberDeliveryOrder : memberDeliveryOrderList2) {
            stock_quantity -= memberDeliveryOrder.getMember_delivery_order_total_quantity();
        }

        List<MemberDeliveryOrder> memberDeliveryOrderList3 = MemberDeliveryOrderService.me.list(Cnd.where(MemberDeliveryOrder.USER_ID, request_user_id).and(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_WAREHOUSE_DELIVER, true).and(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_FLOW, MemberDeliveryOrderFlow.COMPLETE.getKey()));
        for (MemberDeliveryOrder memberDeliveryOrder : memberDeliveryOrderList3) {
            stock_quantity -= memberDeliveryOrder.getMember_delivery_order_total_quantity();
        }

        validateResponse();

        renderSuccessJson(stock_quantity);
    }

    @ActionKey("/mobile/stock/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/stock/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/stock/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/stock/delete")
    public void delete() {

        renderSuccessJson();
    }

}