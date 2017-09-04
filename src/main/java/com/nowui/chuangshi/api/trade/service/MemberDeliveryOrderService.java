package com.nowui.chuangshi.api.trade.service;

import com.nowui.chuangshi.api.trade.dao.MemberDeliveryOrderDao;
import com.nowui.chuangshi.api.trade.model.MemberDeliveryOrder;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;

import java.util.List;

public class MemberDeliveryOrderService extends Service {

    public static final MemberDeliveryOrderService instance = new MemberDeliveryOrderService();
    private final String MEMBER_DELIVERY_ORDER_ITEM_CACHE = "member_delivery_order_item_cache";
    private final MemberDeliveryOrderDao memberDeliveryOrderDao = new MemberDeliveryOrderDao();

    public List<MemberDeliveryOrder> userIsPayList(String user_id) {
        Cnd cnd = Cnd.where(MemberDeliveryOrder.SYSTEM_STATUS, true);
        cnd.and(MemberDeliveryOrder.USER_ID, user_id);
        cnd.and(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_PAY, true);

        List<MemberDeliveryOrder> memberDeliveryOrderList = memberDeliveryOrderDao.list(cnd);
        return memberDeliveryOrderList;
    }

    public List<MemberDeliveryOrder> userIsWarehouseDeliverList(String user_id, String member_delivery_order_flow) {
        Cnd cnd = Cnd.where(MemberDeliveryOrder.SYSTEM_STATUS, true);
        cnd.and(MemberDeliveryOrder.USER_ID, user_id);
        cnd.and(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_WAREHOUSE_DELIVER, true);
        cnd.and(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_FLOW, member_delivery_order_flow);

        List<MemberDeliveryOrder> memberDeliveryOrderList = memberDeliveryOrderDao.list(cnd);
        return memberDeliveryOrderList;
    }

    public MemberDeliveryOrder purchaseOrderFind(String member_purchase_order_id) {
        Cnd cnd = Cnd.where(MemberDeliveryOrder.SYSTEM_STATUS, true);
        cnd.and(MemberDeliveryOrder.MEMBER_PURCHASE_ORDER_ID, member_purchase_order_id);

        MemberDeliveryOrder memberDeliveryOrder = memberDeliveryOrderDao.find(cnd);
        return memberDeliveryOrder;
    }

}