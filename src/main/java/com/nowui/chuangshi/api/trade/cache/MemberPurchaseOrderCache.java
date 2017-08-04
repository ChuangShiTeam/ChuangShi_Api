package com.nowui.chuangshi.api.trade.cache;

import com.nowui.chuangshi.api.trade.dao.MemberPurchaseOrderDao;
import com.nowui.chuangshi.api.trade.model.MemberPurchaseOrder;
import com.nowui.chuangshi.common.cache.Cache;

public class MemberPurchaseOrderCache extends Cache {

    public static final String MEMBER_PURCHASE_ORDER_ITEM_CACHE = "member_purchase_order_item_cache";

    public MemberPurchaseOrderCache() {
        setDao(new MemberPurchaseOrderDao());

        setItemCache(MEMBER_PURCHASE_ORDER_ITEM_CACHE, MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_ID);
    }

}