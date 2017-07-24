package com.nowui.chuangshi.dao;

import java.util.Date;
import java.util.List;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.model.StockInProductSku;

public class StockInProductSkuDao extends Dao {

    public List<StockInProductSku> listByStock_in_id(String stock_in_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockInProductSku.STOCK_IN_ID, stock_in_id);
        SqlPara sqlPara = Db.getSqlPara("stock_in_product_sku.listByStock_in_id", sqlMap);

        logSql("stock_in_product_sku", "listByStock_in_id", sqlPara);

        return new StockInProductSku().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public Boolean save(String stock_in_id, String product_sku_id, Integer product_sku_quantity, String system_create_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockInProductSku.STOCK_IN_ID, stock_in_id);
        sqlMap.put(StockInProductSku.PRODUCT_SKU_ID, product_sku_id);
        sqlMap.put(StockInProductSku.PRODUCT_SKU_QUANTITY, product_sku_quantity);
        sqlMap.put(StockInProductSku.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(StockInProductSku.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(StockInProductSku.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(StockInProductSku.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(StockInProductSku.SYSTEM_VERSION, 0);
        sqlMap.put(StockInProductSku.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("stock_in_product_sku.save", sqlMap);

        logSql("stock_in_product_sku", "save", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByStock_in_idAndSystem_version(String stock_in_id, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockInProductSku.STOCK_IN_ID, stock_in_id);
        sqlMap.put(StockInProductSku.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(StockInProductSku.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(StockInProductSku.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("stock_in_product_sku.deleteByStock_in_idAndSystem_version", sqlMap);

        logSql("stock_in_product_sku", "deleteByStock_in_idAndSystem_version", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}