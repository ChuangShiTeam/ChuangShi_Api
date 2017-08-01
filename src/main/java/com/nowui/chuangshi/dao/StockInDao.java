package com.nowui.chuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.StockIn;

import java.util.Date;
import java.util.List;

public class StockInDao extends Dao {

    public Integer countByApp_idOrWarehouse_idAndStock_in_typeOrLikeStock_in_batchOrLikeUser_name(String app_id, String warehouse_id, String stock_in_type, String stock_in_batch, String user_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockIn.APP_ID, app_id);
        sqlMap.put(StockIn.WAREHOUSE_ID, warehouse_id);
        sqlMap.put(StockIn.STOCK_IN_TYPE, stock_in_type);
        sqlMap.put(StockIn.STOCK_IN_BATCH, stock_in_batch);
        sqlMap.put(StockIn.USER_NAME, user_name);
        SqlPara sqlPara = Db.getSqlPara("stock_in.countByApp_idOrWarehouse_idAndStock_in_typeOrLikeStock_in_batchOrLikeUser_name", sqlMap);

        logSql("stock_in", "countByApp_idOrWarehouse_idAndStock_in_typeOrLikeStock_in_batchOrLikeUser_name", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByOrApp_idOrWarehouse_idAndStock_in_typeOrLikeStock_in_batchOrLikeUser_name(String app_id, String warehouse_id, String stock_in_type, String stock_in_batch, String user_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockIn.APP_ID, app_id);
        sqlMap.put(StockIn.WAREHOUSE_ID, warehouse_id);
        sqlMap.put(StockIn.STOCK_IN_TYPE, stock_in_type);
        sqlMap.put(StockIn.STOCK_IN_BATCH, stock_in_batch);
        sqlMap.put(StockIn.USER_NAME, user_name);
        SqlPara sqlPara = Db.getSqlPara("stock_in.countByOrApp_idOrWarehouse_idAndStock_in_typeOrLikeStock_in_batchOrLikeUser_name", sqlMap);

        logSql("stock_in", "countByOrApp_idOrWarehouse_idAndStock_in_typeOrLikeStock_in_batchOrLikeUser_name", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<StockIn> listByApp_idAndStock_in_typeAndSystem_create_timeAndLimit(String app_id, String stock_in_type, Date system_create_time, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockIn.APP_ID, app_id);
        sqlMap.put(StockIn.STOCK_IN_TYPE, stock_in_type);
        sqlMap.put(StockIn.SYSTEM_CREATE_TIME, system_create_time);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("stock_in.listByApp_idAndStock_in_typeAndSystem_create_timeAndLimit", sqlMap);

        logSql("stock_in", "listByApp_idAndStock_in_typeAndSystem_create_timeAndLimit", sqlPara);

        return new StockIn().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<StockIn> listByApp_idOrWarehouse_idAndStock_in_typeOrLikeStock_in_batchOrLikeUser_nameAndLimit(String app_id, String warehouse_id, String stock_in_type, String stock_in_batch, String user_name, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockIn.APP_ID, app_id);
        sqlMap.put(StockIn.WAREHOUSE_ID, warehouse_id);
        sqlMap.put(StockIn.STOCK_IN_TYPE, stock_in_type);
        sqlMap.put(StockIn.STOCK_IN_BATCH, stock_in_batch);
        sqlMap.put(StockIn.USER_NAME, user_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("stock_in.listByApp_idOrWarehouse_idAndStock_in_typeOrLikeStock_in_batchOrLikeUser_nameAndLimit", sqlMap);

        logSql("stock_in", "listByApp_idOrWarehouse_idAndStock_in_typeOrLikeStock_in_batchOrLikeUser_nameAndLimit", sqlPara);

        return new StockIn().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<StockIn> listByOrApp_idOrWarehouse_idAndStock_in_typeOrLikeStock_in_batchOrLikeUser_nameAndLimit(String app_id, String warehouse_id, String stock_in_type, String stock_in_batch, String user_name, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockIn.APP_ID, app_id);
        sqlMap.put(StockIn.WAREHOUSE_ID, warehouse_id);
        sqlMap.put(StockIn.STOCK_IN_TYPE, stock_in_type);
        sqlMap.put(StockIn.STOCK_IN_BATCH, stock_in_batch);
        sqlMap.put(StockIn.USER_NAME, user_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("stock_in.listByOrApp_idOrWarehouse_idAndStock_in_typeOrLikeStock_in_batchOrLikeUser_nameAndLimit", sqlMap);

        logSql("stock_in", "listByOrApp_idOrWarehouse_idAndStock_in_typeOrLikeStock_in_batchOrLikeUser_nameAndLimit", sqlPara);

        return new StockIn().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public StockIn findByStock_in_id(String stock_in_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockIn.STOCK_IN_ID, stock_in_id);
        SqlPara sqlPara = Db.getSqlPara("stock_in.findByStock_in_id", sqlMap);

        logSql("stock_in", "findByStock_in_id", sqlPara);

        List<StockIn> stock_inList = new StockIn().find(sqlPara.getSql(), sqlPara.getPara());
        if (stock_inList.size() == 0) {
            return null;
        } else {
            return stock_inList.get(0);
        }
    }
    
    public StockIn findByStock_in_idAndStock_in_type(String stock_in_id, String stock_in_type) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockIn.STOCK_IN_ID, stock_in_id);
        sqlMap.put(StockIn.STOCK_IN_TYPE, stock_in_type);
        SqlPara sqlPara = Db.getSqlPara("stock_in.findByStock_in_idAndStock_in_type", sqlMap);

        logSql("stock_in", "findByStock_in_idAndStock_in_type", sqlPara);

        List<StockIn> stock_inList = new StockIn().find(sqlPara.getSql(), sqlPara.getPara());
        if (stock_inList.size() == 0) {
            return null;
        } else {
            return stock_inList.get(0);
        }
    }

    public Boolean save(String stock_in_id, String app_id, String warehouse_id, String purchase_order_id, String object_id, String stock_in_batch, String stock_in_type, Integer stock_in_quantity, String stock_in_status, String system_create_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockIn.STOCK_IN_ID, stock_in_id);
        sqlMap.put(StockIn.APP_ID, app_id);
        sqlMap.put(StockIn.WAREHOUSE_ID, warehouse_id);
        sqlMap.put(StockIn.PURCHASE_ORDER_ID, purchase_order_id);
        sqlMap.put(StockIn.OBJECT_ID, object_id);
        sqlMap.put(StockIn.STOCK_IN_BATCH, stock_in_batch);
        sqlMap.put(StockIn.STOCK_IN_TYPE, stock_in_type);
        sqlMap.put(StockIn.STOCK_IN_QUANTITY, stock_in_quantity);
        sqlMap.put(StockIn.STOCK_IN_STATUS, stock_in_status);
        sqlMap.put(StockIn.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(StockIn.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(StockIn.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(StockIn.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(StockIn.SYSTEM_VERSION, 0);
        sqlMap.put(StockIn.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("stock_in.save", sqlMap);

        logSql("stock_in", "save", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean update(String stock_in_id, String warehouse_id, String purchase_order_id, String object_id, String stock_in_batch, String stock_in_type, Integer stock_in_quantity, String stock_in_status, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockIn.STOCK_IN_ID, stock_in_id);
        sqlMap.put(StockIn.WAREHOUSE_ID, warehouse_id);
        sqlMap.put(StockIn.PURCHASE_ORDER_ID, purchase_order_id);
        sqlMap.put(StockIn.OBJECT_ID, object_id);
        sqlMap.put(StockIn.STOCK_IN_BATCH, stock_in_batch);
        sqlMap.put(StockIn.STOCK_IN_TYPE, stock_in_type);
        sqlMap.put(StockIn.STOCK_IN_QUANTITY, stock_in_quantity);
        sqlMap.put(StockIn.STOCK_IN_STATUS, stock_in_status);
        sqlMap.put(StockIn.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(StockIn.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(StockIn.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("stock_in.update", sqlMap);

        logSql("stock_in", "update", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByStock_in_idAndSystem_version(String stock_in_id, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockIn.STOCK_IN_ID, stock_in_id);
        sqlMap.put(StockIn.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(StockIn.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(StockIn.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("stock_in.deleteByStock_in_idAndSystem_version", sqlMap);

        logSql("stock_in", "deleteByStock_in_idAndSystem_version", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}