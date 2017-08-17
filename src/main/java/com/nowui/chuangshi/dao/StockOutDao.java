package com.nowui.chuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.StockOut;

import java.util.Date;
import java.util.List;

public class StockOutDao extends Dao {

    public Integer countByApp_idOrWarehouse_idAndStock_out_typeOrLikeStock_out_batchOrLikeUser_name(String app_id, String warehouse_id, String stock_out_type, String stock_out_batch, String user_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockOut.APP_ID, app_id);
        sqlMap.put(StockOut.WAREHOUSE_ID, warehouse_id);
        sqlMap.put(StockOut.STOCK_OUT_TYPE, stock_out_type);
        sqlMap.put(StockOut.STOCK_OUT_BATCH, stock_out_batch);
        sqlMap.put(StockOut.USER_NAME, user_name);
        SqlPara sqlPara = Db.getSqlPara("stock_out.countByApp_idOrWarehouse_idAndStock_out_typeOrLikeStock_out_batchOrLikeUser_name", sqlMap);

        logSql("stock_out", "countByApp_idOrWarehouse_idAndStock_out_typeOrLikeStock_out_batchOrLikeUser_name", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByOrApp_idOrWarehouse_idAndStock_out_typeOrLikeStock_out_batchOrLikeUser_name(String app_id, String warehouse_id, String stock_out_type, String stock_out_batch, String user_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockOut.APP_ID, app_id);
        sqlMap.put(StockOut.WAREHOUSE_ID, warehouse_id);
        sqlMap.put(StockOut.STOCK_OUT_TYPE, stock_out_type);
        sqlMap.put(StockOut.STOCK_OUT_BATCH, stock_out_batch);
        sqlMap.put(StockOut.USER_NAME, user_name);
        SqlPara sqlPara = Db.getSqlPara("stock_out.countByOrApp_idOrWarehouse_idAndStock_out_typeOrLikeStock_out_batchOrLikeUser_name", sqlMap);

        logSql("stock_out", "countByOrApp_idOrWarehouse_idAndStock_out_typeOrLikeStock_out_batchOrLikeUser_name", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<StockOut> listByApp_idAndStock_out_typeAndSystem_create_timeAndLimit(String app_id, String stock_out_type, Date system_create_time, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockOut.APP_ID, app_id);
        sqlMap.put(StockOut.STOCK_OUT_TYPE, stock_out_type);
        sqlMap.put(StockOut.SYSTEM_CREATE_TIME, system_create_time);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("stock_out.listByApp_idAndStock_out_typeAndSystem_create_timeAndLimit", sqlMap);

        logSql("stock_out", "listByApp_idAndStock_out_typeAndSystem_create_timeAndLimit", sqlPara);

        return new StockOut().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<StockOut> listByApp_idOrWarehouse_idAndStock_out_typeOrLikeStock_out_batchOrLikeUser_nameAndLimit(String app_id, String warehouse_id, String stock_out_type, String stock_out_batch, String user_name, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockOut.APP_ID, app_id);
        sqlMap.put(StockOut.WAREHOUSE_ID, warehouse_id);
        sqlMap.put(StockOut.STOCK_OUT_TYPE, stock_out_type);
        sqlMap.put(StockOut.STOCK_OUT_BATCH, stock_out_batch);
        sqlMap.put(StockOut.USER_NAME, user_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("stock_out.listByApp_idOrWarehouse_idAndStock_out_typeOrLikeStock_out_batchOrLikeUser_nameAndLimit", sqlMap);

        logSql("stock_out", "listByApp_idOrWarehouse_idAndStock_out_typeOrLikeStock_out_batchOrLikeUser_nameAndLimit", sqlPara);

        return new StockOut().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<StockOut> listByOrApp_idOrWarehouse_idAndStock_out_typeOrLikeStock_out_batchOrLikeUser_nameAndLimit(String app_id, String warehouse_id, String stock_out_type, String stock_out_batch, String user_name, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockOut.APP_ID, app_id);
        sqlMap.put(StockOut.WAREHOUSE_ID, warehouse_id);
        sqlMap.put(StockOut.STOCK_OUT_TYPE, stock_out_type);
        sqlMap.put(StockOut.STOCK_OUT_BATCH, stock_out_batch);
        sqlMap.put(StockOut.USER_NAME, user_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("stock_out.listByOrApp_idOrWarehouse_idAndStock_out_typeOrLikeStock_out_batchOrLikeUser_nameAndLimit", sqlMap);

        logSql("stock_out", "listByOrApp_idOrWarehouse_idAndStock_out_typeOrLikeStock_out_batchOrLikeUser_nameAndLimit", sqlPara);

        return new StockOut().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public StockOut findByStock_out_id(String stock_out_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockOut.STOCK_OUT_ID, stock_out_id);
        SqlPara sqlPara = Db.getSqlPara("stock_out.findByStock_out_id", sqlMap);

        logSql("stock_out", "findByStock_out_id", sqlPara);

        List<StockOut> stock_outList = new StockOut().find(sqlPara.getSql(), sqlPara.getPara());
        if (stock_outList.size() == 0) {
            return null;
        } else {
            return stock_outList.get(0);
        }
    }
    
    public StockOut findByStock_out_idAndStock_out_type(String stock_out_id, String stock_out_type) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockOut.STOCK_OUT_ID, stock_out_id);
        sqlMap.put(StockOut.STOCK_OUT_TYPE, stock_out_type);
        SqlPara sqlPara = Db.getSqlPara("stock_out.findByStock_out_idAndStock_out_type", sqlMap);

        logSql("stock_out", "findByStock_out_idAndStock_out_type", sqlPara);

        List<StockOut> stock_outList = new StockOut().find(sqlPara.getSql(), sqlPara.getPara());
        if (stock_outList.size() == 0) {
            return null;
        } else {
            return stock_outList.get(0);
        }
    }

    public Boolean save(String stock_out_id, String app_id, String warehouse_id, String delivery_order_id, String object_id, String stock_out_batch, String stock_out_type, Integer stock_out_quantity, String stock_out_status, String system_create_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockOut.STOCK_OUT_ID, stock_out_id);
        sqlMap.put(StockOut.APP_ID, app_id);
        sqlMap.put(StockOut.WAREHOUSE_ID, warehouse_id);
        sqlMap.put(StockOut.DELIVERY_ORDER_ID, delivery_order_id);
        sqlMap.put(StockOut.OBJECT_ID, object_id);
        sqlMap.put(StockOut.STOCK_OUT_BATCH, stock_out_batch);
        sqlMap.put(StockOut.STOCK_OUT_TYPE, stock_out_type);
        sqlMap.put(StockOut.STOCK_OUT_QUANTITY, stock_out_quantity);
        sqlMap.put(StockOut.STOCK_OUT_STATUS, stock_out_status);
        sqlMap.put(StockOut.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(StockOut.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(StockOut.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(StockOut.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(StockOut.SYSTEM_VERSION, 0);
        sqlMap.put(StockOut.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("stock_out.save", sqlMap);

        logSql("stock_out", "save", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean update(String stock_out_id, String warehouse_id, String delivery_order_id, String object_id, String stock_out_batch, String stock_out_type, Integer stock_out_quantity, String stock_out_status, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockOut.STOCK_OUT_ID, stock_out_id);
        sqlMap.put(StockOut.WAREHOUSE_ID, warehouse_id);
        sqlMap.put(StockOut.DELIVERY_ORDER_ID, delivery_order_id);
        sqlMap.put(StockOut.OBJECT_ID, object_id);
        sqlMap.put(StockOut.STOCK_OUT_BATCH, stock_out_batch);
        sqlMap.put(StockOut.STOCK_OUT_TYPE, stock_out_type);
        sqlMap.put(StockOut.STOCK_OUT_QUANTITY, stock_out_quantity);
        sqlMap.put(StockOut.STOCK_OUT_STATUS, stock_out_status);
        sqlMap.put(StockOut.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(StockOut.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(StockOut.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("stock_out.update", sqlMap);

        logSql("stock_out", "update", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByStock_out_idAndSystem_version(String stock_out_id, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockOut.STOCK_OUT_ID, stock_out_id);
        sqlMap.put(StockOut.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(StockOut.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(StockOut.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("stock_out.deleteByStock_out_idAndSystem_version", sqlMap);

        logSql("stock_out", "deleteByStock_out_idAndSystem_version", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public List<StockOut> listByDelivery_order_id(String delivery_order_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(StockOut.DELIVERY_ORDER_ID, delivery_order_id);
        SqlPara sqlPara = Db.getSqlPara("stock_out.listByDelivery_order_id", sqlMap);

        logSql("stock_out", "listByDelivery_order_id", sqlPara);

        return new StockOut().find(sqlPara.getSql(), sqlPara.getPara());
    }

}