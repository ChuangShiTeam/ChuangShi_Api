package com.nowui.chuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.Warehouse;

import java.util.Date;
import java.util.List;

public class WarehouseDao extends Dao {

    public Integer countByApp_idOrLikeWarehouse_name(String app_id, String warehouse_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Warehouse.APP_ID, app_id);
        sqlMap.put(Warehouse.WAREHOUSE_NAME, warehouse_name);
        SqlPara sqlPara = Db.getSqlPara("warehouse.countByApp_idOrLikeWarehouse_name", sqlMap);

        logSql("warehouse", "countByApp_idOrLikeWarehouse_name", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByOrApp_idOrLikeWarehouse_name(String app_id, String warehouse_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Warehouse.APP_ID, app_id);
        sqlMap.put(Warehouse.WAREHOUSE_NAME, warehouse_name);
        SqlPara sqlPara = Db.getSqlPara("warehouse.countByOrApp_idOrLikeWarehouse_name", sqlMap);

        logSql("warehouse", "countByOrApp_idOrLikeWarehouse_name", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<Warehouse> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Warehouse.APP_ID, app_id);
        sqlMap.put(Warehouse.SYSTEM_CREATE_TIME, system_create_time);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("warehouse.listByApp_idAndSystem_create_timeAndLimit", sqlMap);

        logSql("warehouse", "listByApp_idAndSystem_create_timeAndLimit", sqlPara);

        return new Warehouse().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Warehouse> listByApp_idOrLikeWarehouse_nameAndLimit(String app_id, String warehouse_name, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Warehouse.APP_ID, app_id);
        sqlMap.put(Warehouse.WAREHOUSE_NAME, warehouse_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("warehouse.listByApp_idOrLikeWarehouse_nameAndLimit", sqlMap);

        logSql("warehouse", "listByApp_idOrLikeWarehouse_nameAndLimit", sqlPara);

        return new Warehouse().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Warehouse> listByOrApp_idOrLikeWarehouse_nameAndLimit(String app_id, String warehouse_name, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Warehouse.APP_ID, app_id);
        sqlMap.put(Warehouse.WAREHOUSE_NAME, warehouse_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("warehouse.listByOrApp_idOrLikeWarehouse_nameAndLimit", sqlMap);

        logSql("warehouse", "listByOrApp_idOrLikeWarehouse_nameAndLimit", sqlPara);

        return new Warehouse().find(sqlPara.getSql(), sqlPara.getPara());
    }
    
    public List<Warehouse> listByApp_id(String app_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Warehouse.APP_ID, app_id);
        SqlPara sqlPara = Db.getSqlPara("warehouse.listByApp_id", sqlMap);
        
        logSql("warehouse", "listByApp_id", sqlPara);
        
        return new Warehouse().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public Warehouse findByWarehouse_id(String warehouse_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Warehouse.WAREHOUSE_ID, warehouse_id);
        SqlPara sqlPara = Db.getSqlPara("warehouse.findByWarehouse_id", sqlMap);

        logSql("warehouse", "findByWarehouse_id", sqlPara);

        List<Warehouse> warehouseList = new Warehouse().find(sqlPara.getSql(), sqlPara.getPara());
        if (warehouseList.size() == 0) {
            return null;
        } else {
            return warehouseList.get(0);
        }
    }

    public Boolean save(String warehouse_id, String app_id, String warehouse_code, String warehouse_name, String warehouse_status, String warehouse_remark, String system_create_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Warehouse.WAREHOUSE_ID, warehouse_id);
        sqlMap.put(Warehouse.APP_ID, app_id);
        sqlMap.put(Warehouse.WAREHOUSE_CODE, warehouse_code);
        sqlMap.put(Warehouse.WAREHOUSE_NAME, warehouse_name);
        sqlMap.put(Warehouse.WAREHOUSE_STATUS, warehouse_status);
        sqlMap.put(Warehouse.WAREHOUSE_REMARK, warehouse_remark);
        sqlMap.put(Warehouse.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(Warehouse.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(Warehouse.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(Warehouse.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Warehouse.SYSTEM_VERSION, 0);
        sqlMap.put(Warehouse.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("warehouse.save", sqlMap);

        logSql("warehouse", "save", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean update(String warehouse_id, String warehouse_code, String warehouse_name, String warehouse_status, String warehouse_remark, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Warehouse.WAREHOUSE_ID, warehouse_id);
        sqlMap.put(Warehouse.WAREHOUSE_CODE, warehouse_code);
        sqlMap.put(Warehouse.WAREHOUSE_NAME, warehouse_name);
        sqlMap.put(Warehouse.WAREHOUSE_STATUS, warehouse_status);
        sqlMap.put(Warehouse.WAREHOUSE_REMARK, warehouse_remark);
        sqlMap.put(Warehouse.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(Warehouse.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Warehouse.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("warehouse.update", sqlMap);

        logSql("warehouse", "update", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByWarehouse_idAndSystem_version(String warehouse_id, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Warehouse.WAREHOUSE_ID, warehouse_id);
        sqlMap.put(Warehouse.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(Warehouse.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Warehouse.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("warehouse.deleteByWarehouse_idAndSystem_version", sqlMap);

        logSql("warehouse", "deleteByWarehouse_idAndSystem_version", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}