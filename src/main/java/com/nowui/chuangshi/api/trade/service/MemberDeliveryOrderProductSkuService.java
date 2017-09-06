package com.nowui.chuangshi.api.trade.service;

import com.nowui.chuangshi.api.trade.dao.MemberDeliveryOrderProductSkuDao;
import com.nowui.chuangshi.api.trade.model.MemberDeliveryOrderProductSku;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.util.CacheUtil;

public class MemberDeliveryOrderProductSkuService extends Service {

    public static final MemberDeliveryOrderProductSkuService instance = new MemberDeliveryOrderProductSkuService();
    private final String MEMBER_DELIVERY_ORDER_PRODUCT_SKU_ITEM_CACHE = "member_delivery_order_product_sku_item_cache";
    private final MemberDeliveryOrderProductSkuDao memberDeliveryOrderProductSkuDao = new MemberDeliveryOrderProductSkuDao();

    public MemberDeliveryOrderProductSku find(String stock_id) {
        MemberDeliveryOrderProductSku stock = CacheUtil.get(MEMBER_DELIVERY_ORDER_PRODUCT_SKU_ITEM_CACHE, stock_id);

        if (stock == null) {
            stock = memberDeliveryOrderProductSkuDao.find(stock_id);

            CacheUtil.put(MEMBER_DELIVERY_ORDER_PRODUCT_SKU_ITEM_CACHE, stock_id, stock);
        }

        return stock;
    }

    public Boolean save(MemberDeliveryOrderProductSku memberDeliveryOrderProductSku, String system_create_user_id) {
        Boolean success = memberDeliveryOrderProductSkuDao.save(memberDeliveryOrderProductSku, system_create_user_id);
        return success;
    }

}