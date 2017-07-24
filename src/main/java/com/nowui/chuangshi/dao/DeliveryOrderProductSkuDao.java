package com.nowui.chuangshi.dao;

import java.util.Date;
import java.util.List;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.model.DeliveryOrder;
import com.nowui.chuangshi.model.DeliveryOrderProductSku;

public class DeliveryOrderProductSkuDao extends Dao {

    public List<Record> listByDelivery_order_id(String delivery_order_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(DeliveryOrder.DELIVERY_ORDER_ID, delivery_order_id);
        SqlPara sqlPara = Db.getSqlPara("delivery_order_product_sku.listByDelivery_order_id", sqlMap);

        logSql("delivery_order_product_sku", "listByDelivery_order_id", sqlPara);

        return Db.find(sqlPara.getSql(), sqlPara.getPara());
    }

    public Boolean save(String delivery_order_id, String product_sku_id, Integer product_sku_quantity, String system_create_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(DeliveryOrderProductSku.DELIVERY_ORDER_ID, delivery_order_id);
        sqlMap.put(DeliveryOrderProductSku.PRODUCT_SKU_ID, product_sku_id);
        sqlMap.put(DeliveryOrderProductSku.PRODUCT_SKU_QUANTITY, product_sku_quantity);
        sqlMap.put(DeliveryOrderProductSku.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(DeliveryOrderProductSku.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(DeliveryOrderProductSku.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(DeliveryOrderProductSku.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(DeliveryOrderProductSku.SYSTEM_VERSION, 0);
        sqlMap.put(DeliveryOrderProductSku.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("delivery_order_product_sku.save", sqlMap);

        logSql("delivery_order_product_sku", "save", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByDelivery_order_id(String delivery_order_id, String system_update_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(DeliveryOrder.DELIVERY_ORDER_ID, delivery_order_id);
        sqlMap.put(DeliveryOrderProductSku.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(DeliveryOrderProductSku.SYSTEM_UPDATE_TIME, new Date());
        SqlPara sqlPara = Db.getSqlPara("delivery_order_product_sku.deleteByDelivery_order_id", sqlMap);

        logSql("delivery_order_product_sku", "deleteByDelivery_order_id", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Object batchSave(List<DeliveryOrderProductSku> list) {
        // TODO Auto-generated method stub
        return null;
    }

}