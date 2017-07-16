package com.nowui.chuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.Supplier;

import java.util.Date;
import java.util.List;

public class SupplierDao extends Dao {

    public Integer countByApp_id(String app_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Supplier.APP_ID, app_id);
        //sqlMap.put(Supplier.SUPPLIER_NAME, supplier_name);
        SqlPara sqlPara = Db.getSqlPara("supplier.countByApp_id", sqlMap);

        logSql("supplier", "countByApp_id", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByOrApp_idOrLikeSupplier_name(String app_id, String supplier_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Supplier.APP_ID, app_id);
        SqlPara sqlPara = Db.getSqlPara("supplier.countByOrApp_idOrLikeSupplier_name", sqlMap);

        logSql("supplier", "countByOrApp_idOrLikeSupplier_name", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<Supplier> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Supplier.APP_ID, app_id);
        sqlMap.put(Supplier.SYSTEM_CREATE_TIME, system_create_time);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("supplier.listByApp_idAndSystem_create_timeAndLimit", sqlMap);

        logSql("supplier", "listByApp_idAndSystem_create_timeAndLimit", sqlPara);

        return new Supplier().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Supplier> listByApp_idAndLimit(String app_id, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Supplier.APP_ID, app_id);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("supplier.listByApp_idAndLimit", sqlMap);

        logSql("supplier", "listByApp_idAndLimit", sqlPara);

        return new Supplier().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Supplier> listByOrApp_idOrLikeSupplier_nameAndLimit(String app_id, String supplier_name, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Supplier.APP_ID, app_id);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("supplier.listByOrApp_idOrLikeSupplier_nameAndLimit", sqlMap);

        logSql("supplier", "listByOrApp_idOrLikeSupplier_nameAndLimit", sqlPara);

        return new Supplier().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public Supplier findBySupplier_id(String supplier_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Supplier.SUPPLIER_ID, supplier_id);
        SqlPara sqlPara = Db.getSqlPara("supplier.findBySupplier_id", sqlMap);

        logSql("supplier", "findBySupplier_id", sqlPara);

        List<Supplier> supplierList = new Supplier().find(sqlPara.getSql(), sqlPara.getPara());
        if (supplierList.size() == 0) {
            return null;
        } else {
            return supplierList.get(0);
        }
    }

    public Boolean save(String supplier_id, String app_id, String user_id, Boolean supplier_status, String system_create_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Supplier.SUPPLIER_ID, supplier_id);
        sqlMap.put(Supplier.APP_ID, app_id);
        sqlMap.put(Supplier.USER_ID, user_id);
        sqlMap.put(Supplier.SUPPLIER_STATUS, supplier_status);
        sqlMap.put(Supplier.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(Supplier.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(Supplier.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(Supplier.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Supplier.SYSTEM_VERSION, 0);
        sqlMap.put(Supplier.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("supplier.save", sqlMap);

        logSql("supplier", "save", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean update(String supplier_id, String user_id, Boolean supplier_status, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Supplier.SUPPLIER_ID, supplier_id);
        sqlMap.put(Supplier.USER_ID, user_id);
        sqlMap.put(Supplier.SUPPLIER_STATUS, supplier_status);
        sqlMap.put(Supplier.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(Supplier.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Supplier.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("supplier.update", sqlMap);

        logSql("supplier", "update", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteBySupplier_idAndSystem_version(String supplier_id, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Supplier.SUPPLIER_ID, supplier_id);
        sqlMap.put(Supplier.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(Supplier.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Supplier.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("supplier.deleteBySupplier_idAndSystem_version", sqlMap);

        logSql("supplier", "deleteBySupplier_idAndSystem_version", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}