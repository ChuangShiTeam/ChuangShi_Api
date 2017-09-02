package com.nowui.chuangshi.api.stock.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.stock.model.Stock;
import com.nowui.chuangshi.api.stock.service.StockService;
import com.nowui.chuangshi.api.trade.model.MemberDeliveryOrder;
import com.nowui.chuangshi.api.trade.service.MemberDeliveryOrderService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.type.MemberDeliveryOrderFlow;

import java.util.List;

@ControllerKey("/mobile/stock")
public class StockController extends Controller {

    @ActionKey("/mobile/stock/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/stock/member/list")
    public void memberList() {
        String request_user_id = getRequest_user_id();

        Integer stock_quantity = 0;

        List<Stock> stockList = StockService.instance.userList(request_user_id);

        for (Stock stock : stockList) {
            stock_quantity += stock.getStock_quantity();
        }

        List<MemberDeliveryOrder> memberDeliveryOrderList = MemberDeliveryOrderService.instance.userIsWarehouseDeliverList(request_user_id, MemberDeliveryOrderFlow.WAIT_WAREHOUSE_SEND.getKey());
        for (MemberDeliveryOrder memberDeliveryOrder : memberDeliveryOrderList) {
            stock_quantity -= memberDeliveryOrder.getMember_delivery_order_total_quantity();
        }

        List<MemberDeliveryOrder> memberDeliveryOrderList2 = MemberDeliveryOrderService.instance.userIsWarehouseDeliverList(request_user_id, MemberDeliveryOrderFlow.WAIT_RECEIVE.getKey());
        for (MemberDeliveryOrder memberDeliveryOrder : memberDeliveryOrderList2) {
            stock_quantity -= memberDeliveryOrder.getMember_delivery_order_total_quantity();
        }

        List<MemberDeliveryOrder> memberDeliveryOrderList3 = MemberDeliveryOrderService.instance.userIsWarehouseDeliverList(request_user_id, MemberDeliveryOrderFlow.COMPLETE.getKey());
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