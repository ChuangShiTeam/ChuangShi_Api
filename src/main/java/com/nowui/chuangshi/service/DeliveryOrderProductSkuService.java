package com.nowui.chuangshi.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.nowui.chuangshi.cache.DeliveryOrderProductSkuCache;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.DeliveryOrderProductSku;

public class DeliveryOrderProductSkuService extends Service {

    private DeliveryOrderProductSkuCache deliveryOrderProductSkuCache = new DeliveryOrderProductSkuCache();

    public List<Record> listByDelivery_order_id(String delivery_order_id) {
        return deliveryOrderProductSkuCache.listByDelivery_order_id(delivery_order_id);
    }

    public Boolean save(String delivery_order_id, String product_sku_id, Integer product_sku_quantity, String system_create_user_id) {
        return deliveryOrderProductSkuCache.save(delivery_order_id, product_sku_id, product_sku_quantity, system_create_user_id);
    }

    public Boolean deleteByDelivery_order_idAndSystem_update_user_id(String delivery_order_id, String system_update_user_id) {
        return deliveryOrderProductSkuCache.deleteByDelivery_order_idAndSystem_update_user_id(delivery_order_id, system_update_user_id);
    }
    
    public Boolean batchSave(List<DeliveryOrderProductSku> list) {
        int[] result = Db.batchSave(list, Constant.BATCH_SIZE);

        for (int i : result) {
            if (i == 0) {
                throw new RuntimeException("发货明细记录保存不成功");
            }
        }
        return true;
    }

}