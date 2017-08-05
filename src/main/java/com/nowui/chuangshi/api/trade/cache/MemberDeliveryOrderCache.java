package com.nowui.chuangshi.api.trade.cache;

import com.nowui.chuangshi.api.trade.dao.MemberDeliveryOrderDao;
import com.nowui.chuangshi.api.trade.model.MemberDeliveryOrder;
import com.nowui.chuangshi.common.cache.Cache;

public class MemberDeliveryOrderCache extends Cache {

    public static final String MEMBER_DELIVERY_ORDER_ITEM_CACHE = "member_delivery_order_item_cache";

    public MemberDeliveryOrderCache() {
        setDao(new MemberDeliveryOrderDao());

        setItemCache(MEMBER_DELIVERY_ORDER_ITEM_CACHE, MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_ID);
    }

}