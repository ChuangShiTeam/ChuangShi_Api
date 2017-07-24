package com.nowui.chuangshi.cache;

import com.jfinal.plugin.activerecord.Record;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.DeliveryOrderProductSkuDao;
import com.nowui.chuangshi.model.DeliveryOrderProductSku;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.Date;
import java.util.List;

public class DeliveryOrderProductSkuCache extends Cache {

    public static final String DELIVERY_ORDER_PRODUCT_SKU_LIST_BY_DELIVERY_ORDER_ID_CACHE = "delivery_order_product_sku_list_by_delivery_order_id_cache";

    private DeliveryOrderProductSkuDao deliveryOrderProductSkuDao = new DeliveryOrderProductSkuDao();

    public List<Record> listByDelivery_order_id(String delivery_order_id) {
        List<Record> delivery_order_product_skuList = CacheUtil.get(DELIVERY_ORDER_PRODUCT_SKU_LIST_BY_DELIVERY_ORDER_ID_CACHE, delivery_order_id);

        if (delivery_order_product_skuList == null) {
            delivery_order_product_skuList = deliveryOrderProductSkuDao.listByDelivery_order_id(delivery_order_id);

            CacheUtil.put(DELIVERY_ORDER_PRODUCT_SKU_LIST_BY_DELIVERY_ORDER_ID_CACHE, delivery_order_id, delivery_order_product_skuList);
        }

        return delivery_order_product_skuList;
    }

    public Boolean save(String delivery_order_id, String product_sku_id, Integer product_sku_quantity, String system_create_user_id) {
        return deliveryOrderProductSkuDao.save(delivery_order_id, product_sku_id, product_sku_quantity, system_create_user_id);
    }

    public Boolean deleteByDelivery_order_idAndSystem_update_user_id(String delivery_order_id, String system_update_user_id) {
        boolean result = deliveryOrderProductSkuDao.deleteByDelivery_order_id(delivery_order_id, system_update_user_id);

        if (result) {
            CacheUtil.remove(DELIVERY_ORDER_PRODUCT_SKU_LIST_BY_DELIVERY_ORDER_ID_CACHE, delivery_order_id);
        }

        return result;
    }

    public Object batchSave(List<DeliveryOrderProductSku> list) {
        return deliveryOrderProductSkuDao.batchSave(list);
    }

}