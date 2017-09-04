package com.nowui.chuangshi.api.trade.service;

import com.nowui.chuangshi.api.trade.dao.MemberPurchaseOrderDao;
import com.nowui.chuangshi.api.trade.model.MemberPurchaseOrder;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;

import java.util.List;

public class MemberPurchaseOrderService extends Service {

    public static final MemberPurchaseOrderService instance = new MemberPurchaseOrderService();
    private final String MEMBER_PURCHASE_ORDER_ITEM_CACHE = "member_purchase_order_item_cache";
    private final MemberPurchaseOrderDao memberPurchaseOrderDao = new MemberPurchaseOrderDao();

    public Integer userIsFirstCount(String user_id, String member_level_id) {
        Cnd cnd = Cnd.where(MemberPurchaseOrder.SYSTEM_STATUS, true);
        cnd.and(MemberPurchaseOrder.USER_ID, user_id);
        cnd.and(MemberPurchaseOrder.MEMBER_LEVEL_ID, member_level_id);
        cnd.and(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_PAY, true);

        Integer count = memberPurchaseOrderDao.count(cnd);
        return count;
    }

    public List<MemberPurchaseOrder> userList(String user_id) {
        Cnd cnd = Cnd.where(MemberPurchaseOrder.SYSTEM_STATUS, true);
        cnd.and(MemberPurchaseOrder.USER_ID, user_id);

        List<MemberPurchaseOrder> memberPurchaseOrderList = memberPurchaseOrderDao.list(cnd);
        return memberPurchaseOrderList;
    }

    public List<MemberPurchaseOrder> userIsPayList(String user_id) {
        Cnd cnd = Cnd.where(MemberPurchaseOrder.SYSTEM_STATUS, true);
        cnd.and(MemberPurchaseOrder.USER_ID, user_id);
        cnd.and(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_PAY, true);

        List<MemberPurchaseOrder> memberPurchaseOrderList = memberPurchaseOrderDao.list(cnd);
        return memberPurchaseOrderList;
    }

}