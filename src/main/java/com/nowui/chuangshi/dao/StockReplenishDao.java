package com.nowui.chuangshi.dao;

import java.util.Date;
import java.util.List;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.StockReplenish;

public class StockReplenishDao extends Dao {

    public Integer countByApp_idOrWarehouse_idAndStock_replenish_typeOrLikeUser_name(String app_id, String warehouse_id, String stock_replenish_type, String user_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockReplenish.APP_ID, app_id);
        sqlMap.put(StockReplenish.WAREHOUSE_ID, warehouse_id);
        sqlMap.put(StockReplenish.STOCK_REPLENISH_TYPE, stock_replenish_type);
        sqlMap.put(StockReplenish.USER_NAME, user_name);
        SqlPara sqlPara = Db.getSqlPara("stock_replenish.countByApp_idOrWarehouse_idAndStock_replenish_typeOrLikeUser_name", sqlMap);

        logSql("stock_replenish", "countByApp_idOrWarehouse_idAndStock_replenish_typeOrLikeUser_name", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByOrApp_idOrWarehouse_idAndStock_replenish_typeOrLikeUser_name(String app_id, String warehouse_id, String stock_replenish_type, String user_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockReplenish.APP_ID, app_id);
        sqlMap.put(StockReplenish.WAREHOUSE_ID, warehouse_id);
        sqlMap.put(StockReplenish.STOCK_REPLENISH_TYPE, stock_replenish_type);
        sqlMap.put(StockReplenish.USER_NAME, user_name);
        SqlPara sqlPara = Db.getSqlPara("stock_replenish.countByOrApp_idOrWarehouse_idAndStock_replenish_typeOrLikeUser_name", sqlMap);

        logSql("stock_replenish", "countByOrApp_idOrWarehouse_idAndStock_replenish_typeOrLikeUser_name", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<StockReplenish> listByApp_idAndStock_replenish_typeAndSystem_create_timeAndLimit(String app_id, String stock_replenish_type, Date system_create_time, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockReplenish.APP_ID, app_id);
        sqlMap.put(StockReplenish.STOCK_REPLENISH_TYPE, stock_replenish_type);
        sqlMap.put(StockReplenish.SYSTEM_CREATE_TIME, system_create_time);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("stock_replenish.listByApp_idAndStock_replenish_typeAndSystem_create_timeAndLimit", sqlMap);

        logSql("stock_replenish", "listByApp_idAndStock_replenish_typeAndSystem_create_timeAndLimit", sqlPara);

        return new StockReplenish().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<StockReplenish> listByApp_idOrWarehouse_idAndStock_replenish_typeOrLikeUser_nameAndLimit(String app_id, String warehouse_id, String stock_replenish_type, String user_name, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockReplenish.APP_ID, app_id);
        sqlMap.put(StockReplenish.WAREHOUSE_ID, warehouse_id);
        sqlMap.put(StockReplenish.STOCK_REPLENISH_TYPE, stock_replenish_type);
        sqlMap.put(StockReplenish.USER_NAME, user_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("stock_replenish.listByApp_idOrWarehouse_idAndStock_replenish_typeOrLikeUser_nameAndLimit", sqlMap);

        logSql("stock_replenish", "listByApp_idOrWarehouse_idAndStock_replenish_typeOrLikeUser_nameAndLimit", sqlPara);

        return new StockReplenish().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<StockReplenish> listByOrApp_idOrWarehouse_idAndStock_replenish_typeOrLikeUser_nameAndLimit(String app_id, String warehouse_id, String stock_replenish_type, String user_name, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockReplenish.APP_ID, app_id);
        sqlMap.put(StockReplenish.WAREHOUSE_ID, warehouse_id);
        sqlMap.put(StockReplenish.STOCK_REPLENISH_TYPE, stock_replenish_type);
        sqlMap.put(StockReplenish.USER_NAME, user_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("stock_replenish.listByOrApp_idOrWarehouse_idAndStock_replenish_typeOrLikeUser_nameAndLimit", sqlMap);

        logSql("stock_replenish", "listByOrApp_idOrWarehouse_idAndStock_replenish_typeOrLikeUser_nameAndLimit", sqlPara);

        return new StockReplenish().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public StockReplenish findByStock_replenish_id(String stock_replenish_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockReplenish.STOCK_REPLENISH_ID, stock_replenish_id);
        SqlPara sqlPara = Db.getSqlPara("stock_replenish.findByStock_replenish_id", sqlMap);

        logSql("stock_replenish", "findByStock_replenish_id", sqlPara);

        List<StockReplenish> stock_replenishList = new StockReplenish().find(sqlPara.getSql(), sqlPara.getPara());
        if (stock_replenishList.size() == 0) {
            return null;
        } else {
            return stock_replenishList.get(0);
        }
    }
    
    public StockReplenish findByStock_replenish_idAndStock_replenish_type(String stock_replenish_id, String stock_replenish_type) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockReplenish.STOCK_REPLENISH_ID, stock_replenish_id);
        sqlMap.put(StockReplenish.STOCK_REPLENISH_TYPE, stock_replenish_type);
        SqlPara sqlPara = Db.getSqlPara("stock_replenish.findByStock_replenish_idAndStock_replenish_type", sqlMap);

        logSql("stock_replenish", "findByStock_replenish_idAndStock_replenish_type", sqlPara);

        List<StockReplenish> stock_replenishList = new StockReplenish().find(sqlPara.getSql(), sqlPara.getPara());
        if (stock_replenishList.size() == 0) {
            return null;
        } else {
            return stock_replenishList.get(0);
        }
    }

    public Boolean save(String stock_replenish_id, String app_id, String warehouse_id, String object_id, String stock_replenish_type, Integer stock_replenish_quantity, String stock_replenish_action, String stock_replenish_status, String system_create_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockReplenish.STOCK_REPLENISH_ID, stock_replenish_id);
        sqlMap.put(StockReplenish.APP_ID, app_id);
        sqlMap.put(StockReplenish.WAREHOUSE_ID, warehouse_id);
        sqlMap.put(StockReplenish.OBJECT_ID, object_id);
        sqlMap.put(StockReplenish.STOCK_REPLENISH_TYPE, stock_replenish_type);
        sqlMap.put(StockReplenish.STOCK_REPLENISH_QUANTITY, stock_replenish_quantity);
        sqlMap.put(StockReplenish.STOCK_REPLENISH_ACTION, stock_replenish_action);
        sqlMap.put(StockReplenish.STOCK_REPLENISH_STATUS, stock_replenish_status);
        sqlMap.put(StockReplenish.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(StockReplenish.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(StockReplenish.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(StockReplenish.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(StockReplenish.SYSTEM_VERSION, 0);
        sqlMap.put(StockReplenish.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("stock_replenish.save", sqlMap);

        logSql("stock_replenish", "save", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean update(String stock_replenish_id, String warehouse_id, String object_id, String stock_replenish_type, Integer stock_replenish_quantity, String stock_replenish_action, String stock_replenish_status, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockReplenish.STOCK_REPLENISH_ID, stock_replenish_id);
        sqlMap.put(StockReplenish.WAREHOUSE_ID, warehouse_id);
        sqlMap.put(StockReplenish.OBJECT_ID, object_id);
        sqlMap.put(StockReplenish.STOCK_REPLENISH_TYPE, stock_replenish_type);
        sqlMap.put(StockReplenish.STOCK_REPLENISH_QUANTITY, stock_replenish_quantity);
        sqlMap.put(StockReplenish.STOCK_REPLENISH_ACTION, stock_replenish_action);
        sqlMap.put(StockReplenish.STOCK_REPLENISH_STATUS, stock_replenish_status);
        sqlMap.put(StockReplenish.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(StockReplenish.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(StockReplenish.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("stock_replenish.update", sqlMap);

        logSql("stock_replenish", "update", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByStock_replenish_idAndSystem_version(String stock_replenish_id, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockReplenish.STOCK_REPLENISH_ID, stock_replenish_id);
        sqlMap.put(StockReplenish.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(StockReplenish.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(StockReplenish.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("stock_replenish.deleteByStock_replenish_idAndSystem_version", sqlMap);

        logSql("stock_replenish", "deleteByStock_replenish_idAndSystem_version", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}