package com.nowui.chuangshi.dao;

import java.util.Date;
import java.util.List;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.model.MemberPurchaseOrderProductSku;

public class MemberPurchaseOrderProductSkuDao extends Dao {

    public List<Record> listByMember_purchase_order_id(String member_purchase_order_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberPurchaseOrderProductSku.MEMBER_PURCHASE_ORDER_ID, member_purchase_order_id);
        SqlPara sqlPara = Db.getSqlPara("member_purchase_order_product_sku.listByMember_purchase_order_id", sqlMap);

        logSql("member_purchase_order_product_sku", "listByMember_purchase_order_id", sqlPara);

        return Db.find(sqlPara.getSql(), sqlPara.getPara());
    }

    public Boolean save(String member_purchase_order_id, String product_sku_id, Integer product_sku_quantity, String system_create_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberPurchaseOrderProductSku.MEMBER_PURCHASE_ORDER_ID, member_purchase_order_id);
        sqlMap.put(MemberPurchaseOrderProductSku.PRODUCT_SKU_ID, product_sku_id);
        sqlMap.put(MemberPurchaseOrderProductSku.PRODUCT_SKU_QUANTITY, product_sku_quantity);
        sqlMap.put(MemberPurchaseOrderProductSku.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(MemberPurchaseOrderProductSku.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(MemberPurchaseOrderProductSku.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(MemberPurchaseOrderProductSku.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(MemberPurchaseOrderProductSku.SYSTEM_VERSION, 0);
        sqlMap.put(MemberPurchaseOrderProductSku.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("member_purchase_order_product_sku.save", sqlMap);

        logSql("member_purchase_order_product_sku", "save", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByMember_purchase_order_id(String member_purchase_order_id, String system_update_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberPurchaseOrderProductSku.MEMBER_PURCHASE_ORDER_ID, member_purchase_order_id);
        sqlMap.put(MemberPurchaseOrderProductSku.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(MemberPurchaseOrderProductSku.SYSTEM_UPDATE_TIME, new Date());
        SqlPara sqlPara = Db.getSqlPara("member_purchase_order_product_sku.deleteByMember_purchase_order_id", sqlMap);

        logSql("member_purchase_order_product_sku", "deleteByMember_purchase_order_id", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}