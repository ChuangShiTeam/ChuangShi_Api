package com.nowui.chuangshi.api.trade.cache;

import com.nowui.chuangshi.api.trade.dao.MemberDeliveryOrderProductSkuDao;
import com.nowui.chuangshi.common.cache.Cache;

public class MemberDeliveryOrderProductSkuCache extends Cache {

    public static final String MEMBER_DELIVERY_ORDER_PRODUCT_SKU_ITEM_CACHE = "member_delivery_order_product_sku_item_cache";

    public MemberDeliveryOrderProductSkuCache() {
        setDao(new MemberDeliveryOrderProductSkuDao());

    }

}