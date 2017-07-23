package com.nowui.chuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.StockInProductSku;

import java.util.Date;
import java.util.List;

public class StockInProductSkuDao extends Dao {

    public Integer countByApp_idOrLikeStock_in_product_sku_name(String app_id, String stock_in_product_sku_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockInProductSku.APP_ID, app_id);
        sqlMap.put(StockInProductSku.STOCK_IN_PRODUCT_SKU_NAME, stock_in_product_sku_name);
        SqlPara sqlPara = Db.getSqlPara("stock_in_product_sku.countByApp_idOrLikeStock_in_product_sku_name", sqlMap);

        logSql("stock_in_product_sku", "countByApp_idOrLikeStock_in_product_sku_name", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByOrApp_idOrLikeStock_in_product_sku_name(String app_id, String stock_in_product_sku_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockInProductSku.APP_ID, app_id);
        sqlMap.put(StockInProductSku.STOCK_IN_PRODUCT_SKU_NAME, stock_in_product_sku_name);
        SqlPara sqlPara = Db.getSqlPara("stock_in_product_sku.countByOrApp_idOrLikeStock_in_product_sku_name", sqlMap);

        logSql("stock_in_product_sku", "countByOrApp_idOrLikeStock_in_product_sku_name", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<StockInProductSku> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockInProductSku.APP_ID, app_id);
        sqlMap.put(StockInProductSku.SYSTEM_CREATE_TIME, system_create_time);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("stock_in_product_sku.listByApp_idAndSystem_create_timeAndLimit", sqlMap);

        logSql("stock_in_product_sku", "listByApp_idAndSystem_create_timeAndLimit", sqlPara);

        return new StockInProductSku().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<StockInProductSku> listByApp_idOrLikeStock_in_product_sku_nameAndLimit(String app_id, String stock_in_product_sku_name, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockInProductSku.APP_ID, app_id);
        sqlMap.put(StockInProductSku.STOCK_IN_PRODUCT_SKU_NAME, stock_in_product_sku_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("stock_in_product_sku.listByApp_idOrLikeStock_in_product_sku_nameAndLimit", sqlMap);

        logSql("stock_in_product_sku", "listByApp_idOrLikeStock_in_product_sku_nameAndLimit", sqlPara);

        return new StockInProductSku().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<StockInProductSku> listByOrApp_idOrLikeStock_in_product_sku_nameAndLimit(String app_id, String stock_in_product_sku_name, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockInProductSku.APP_ID, app_id);
        sqlMap.put(StockInProductSku.STOCK_IN_PRODUCT_SKU_NAME, stock_in_product_sku_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("stock_in_product_sku.listByOrApp_idOrLikeStock_in_product_sku_nameAndLimit", sqlMap);

        logSql("stock_in_product_sku", "listByOrApp_idOrLikeStock_in_product_sku_nameAndLimit", sqlPara);

        return new StockInProductSku().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public StockInProductSku findByStock_in_product_sku_id(String stock_in_product_sku_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockInProductSku.STOCK_IN_PRODUCT_SKU_ID, stock_in_product_sku_id);
        SqlPara sqlPara = Db.getSqlPara("stock_in_product_sku.findByStock_in_product_sku_id", sqlMap);

        logSql("stock_in_product_sku", "findByStock_in_product_sku_id", sqlPara);

        List<StockInProductSku> stock_in_product_skuList = new StockInProductSku().find(sqlPara.getSql(), sqlPara.getPara());
        if (stock_in_product_skuList.size() == 0) {
            return null;
        } else {
            return stock_in_product_skuList.get(0);
        }
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

    public Boolean update(String stock_in_product_sku_id, String stock_in_id, String product_sku_id, Integer product_sku_quantity, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockInProductSku.STOCK_IN_PRODUCT_SKU_ID, stock_in_product_sku_id);
        sqlMap.put(StockInProductSku.STOCK_IN_ID, stock_in_id);
        sqlMap.put(StockInProductSku.PRODUCT_SKU_ID, product_sku_id);
        sqlMap.put(StockInProductSku.PRODUCT_SKU_QUANTITY, product_sku_quantity);
        sqlMap.put(StockInProductSku.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(StockInProductSku.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(StockInProductSku.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("stock_in_product_sku.update", sqlMap);

        logSql("stock_in_product_sku", "update", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByStock_in_product_sku_idAndSystem_version(String stock_in_product_sku_id, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockInProductSku.STOCK_IN_PRODUCT_SKU_ID, stock_in_product_sku_id);
        sqlMap.put(StockInProductSku.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(StockInProductSku.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(StockInProductSku.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("stock_in_product_sku.deleteByStock_in_product_sku_idAndSystem_version", sqlMap);

        logSql("stock_in_product_sku", "deleteByStock_in_product_sku_idAndSystem_version", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}