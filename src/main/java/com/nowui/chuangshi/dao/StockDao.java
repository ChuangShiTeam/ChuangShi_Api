package com.nowui.chuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.Stock;

import java.util.Date;
import java.util.List;

public class StockDao extends Dao {

    public Integer countByApp_idOrStock_typeOrStock_actionOrLikeProduct_name(String app_id, String stock_type, String stock_action, String product_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Stock.APP_ID, app_id);
        sqlMap.put(Stock.STOCK_TYPE, stock_type);
        sqlMap.put(Stock.STOCK_ACTION, stock_action);
        sqlMap.put(Stock.PRODUCT_NAME, product_name);
        SqlPara sqlPara = Db.getSqlPara("stock.countByApp_idOrStock_typeOrStock_actionOrLikeProduct_name", sqlMap);

        logSql("stock", "countByApp_idOrStock_typeOrStock_actionOrLikeProduct_name", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByOrApp_idOrStock_typeOrStock_actionOrLikeProduct_name(String app_id, String stock_type, String stock_action, String product_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Stock.APP_ID, app_id);
        sqlMap.put(Stock.STOCK_TYPE, stock_type);
        sqlMap.put(Stock.STOCK_ACTION, stock_action);
        sqlMap.put(Stock.PRODUCT_NAME, product_name);
        SqlPara sqlPara = Db.getSqlPara("stock.countByOrApp_idOrStock_typeOrStock_actionOrLikeProduct_name", sqlMap);

        logSql("stock", "countByOrApp_idOrStock_typeOrStock_actionOrLikeProduct_name", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<Stock> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Stock.APP_ID, app_id);
        sqlMap.put(Stock.SYSTEM_CREATE_TIME, system_create_time);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("stock.listByApp_idAndSystem_create_timeAndLimit", sqlMap);

        logSql("stock", "listByApp_idAndSystem_create_timeAndLimit", sqlPara);

        return new Stock().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Stock> listByApp_idOrStock_typeOrStock_actionOrLikeProduct_nameAndLimit(String app_id, String stock_type, String stock_action, String product_name, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Stock.APP_ID, app_id);
        sqlMap.put(Stock.STOCK_TYPE, stock_type);
        sqlMap.put(Stock.STOCK_ACTION, stock_action);
        sqlMap.put(Stock.PRODUCT_NAME, product_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("stock.listByApp_idOrStock_typeOrStock_actionOrLikeProduct_nameAndLimit", sqlMap);

        logSql("stock", "listByApp_idOrStock_typeOrStock_actionOrLikeProduct_nameAndLimit", sqlPara);

        return new Stock().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Stock> listByOrApp_idOrStock_typeOrStock_actionOrLikeProduct_nameAndLimit(String app_id, String stock_type, String stock_action, String product_name, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Stock.APP_ID, app_id);
        sqlMap.put(Stock.STOCK_TYPE, stock_type);
        sqlMap.put(Stock.STOCK_ACTION, stock_action);
        sqlMap.put(Stock.PRODUCT_NAME, product_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("stock.listByOrApp_idOrStock_typeOrStock_actionOrLikeProduct_nameAndLimit", sqlMap);

        logSql("stock", "listByOrApp_idOrStock_typeOrStock_actionOrLikeProduct_nameAndLimit", sqlPara);

        return new Stock().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public Stock findByStock_id(String stock_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Stock.STOCK_ID, stock_id);
        SqlPara sqlPara = Db.getSqlPara("stock.findByStock_id", sqlMap);

        logSql("stock", "findByStock_id", sqlPara);

        List<Stock> stockList = new Stock().find(sqlPara.getSql(), sqlPara.getPara());
        if (stockList.size() == 0) {
            return null;
        } else {
            return stockList.get(0);
        }
    }

    public Boolean save(String stock_id, String app_id, String product_sku_id, String object_id, String product_name, String product_image, String stock_type, Integer stock_quantity, String stock_action, String stock_status, String system_create_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Stock.STOCK_ID, stock_id);
        sqlMap.put(Stock.APP_ID, app_id);
        sqlMap.put(Stock.PRODUCT_SKU_ID, product_sku_id);
        sqlMap.put(Stock.OBJECT_ID, object_id);
        sqlMap.put(Stock.PRODUCT_NAME, product_name);
        sqlMap.put(Stock.PRODUCT_IMAGE, product_image);
        sqlMap.put(Stock.STOCK_TYPE, stock_type);
        sqlMap.put(Stock.STOCK_QUANTITY, stock_quantity);
        sqlMap.put(Stock.STOCK_ACTION, stock_action);
        sqlMap.put(Stock.STOCK_STATUS, stock_status);
        sqlMap.put(Stock.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(Stock.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(Stock.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(Stock.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Stock.SYSTEM_VERSION, 0);
        sqlMap.put(Stock.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("stock.save", sqlMap);

        logSql("stock", "save", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean update(String stock_id, String product_sku_id, String object_id, String product_name, String product_image, String stock_type, Integer stock_quantity, String stock_action, String stock_status, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Stock.STOCK_ID, stock_id);
        sqlMap.put(Stock.PRODUCT_SKU_ID, product_sku_id);
        sqlMap.put(Stock.OBJECT_ID, object_id);
        sqlMap.put(Stock.PRODUCT_NAME, product_name);
        sqlMap.put(Stock.PRODUCT_IMAGE, product_image);
        sqlMap.put(Stock.STOCK_TYPE, stock_type);
        sqlMap.put(Stock.STOCK_QUANTITY, stock_quantity);
        sqlMap.put(Stock.STOCK_ACTION, stock_action);
        sqlMap.put(Stock.STOCK_STATUS, stock_status);
        sqlMap.put(Stock.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(Stock.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Stock.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("stock.update", sqlMap);

        logSql("stock", "update", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByStock_idAndSystem_version(String stock_id, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Stock.STOCK_ID, stock_id);
        sqlMap.put(Stock.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(Stock.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Stock.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("stock.deleteByStock_idAndSystem_version", sqlMap);

        logSql("stock", "deleteByStock_idAndSystem_version", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}