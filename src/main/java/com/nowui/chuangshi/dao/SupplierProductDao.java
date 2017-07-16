package com.nowui.chuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.SupplierProduct;

import java.util.Date;
import java.util.List;

public class SupplierProductDao extends Dao {

    public Integer countByApp_idOrLikeSupplier_product_name(String app_id, String supplier_product_name) {
        Kv sqlMap = Kv.create();
        SqlPara sqlPara = Db.getSqlPara("supplier_product.countByApp_idOrLikeSupplier_product_name", sqlMap);

        logSql("supplier_product", "countByApp_idOrLikeSupplier_product_name", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByOrApp_idOrLikeSupplier_product_name(String app_id, String supplier_product_name) {
        Kv sqlMap = Kv.create();
        SqlPara sqlPara = Db.getSqlPara("supplier_product.countByOrApp_idOrLikeSupplier_product_name", sqlMap);

        logSql("supplier_product", "countByOrApp_idOrLikeSupplier_product_name", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<SupplierProduct> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(SupplierProduct.SYSTEM_CREATE_TIME, system_create_time);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("supplier_product.listByApp_idAndSystem_create_timeAndLimit", sqlMap);

        logSql("supplier_product", "listByApp_idAndSystem_create_timeAndLimit", sqlPara);

        return new SupplierProduct().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<SupplierProduct> listByApp_idOrLikeSupplier_product_nameAndLimit(String app_id, String supplier_product_name, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("supplier_product.listByApp_idOrLikeSupplier_product_nameAndLimit", sqlMap);

        logSql("supplier_product", "listByApp_idOrLikeSupplier_product_nameAndLimit", sqlPara);

        return new SupplierProduct().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<SupplierProduct> listByOrApp_idOrLikeSupplier_product_nameAndLimit(String app_id, String supplier_product_name, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("supplier_product.listByOrApp_idOrLikeSupplier_product_nameAndLimit", sqlMap);

        logSql("supplier_product", "listByOrApp_idOrLikeSupplier_product_nameAndLimit", sqlPara);

        return new SupplierProduct().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public SupplierProduct findBySupplier_product_id(String supplier_product_id) {
        Kv sqlMap = Kv.create();
        SqlPara sqlPara = Db.getSqlPara("supplier_product.findBySupplier_product_id", sqlMap);

        logSql("supplier_product", "findBySupplier_product_id", sqlPara);

        List<SupplierProduct> supplier_productList = new SupplierProduct().find(sqlPara.getSql(), sqlPara.getPara());
        if (supplier_productList.size() == 0) {
            return null;
        } else {
            return supplier_productList.get(0);
        }
    }

    public Boolean save(String supplier_id, String product_id, String system_create_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(SupplierProduct.SUPPLIER_ID, supplier_id);
        sqlMap.put(SupplierProduct.PRODUCT_ID, product_id);
        sqlMap.put(SupplierProduct.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(SupplierProduct.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(SupplierProduct.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(SupplierProduct.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(SupplierProduct.SYSTEM_VERSION, 0);
        sqlMap.put(SupplierProduct.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("supplier_product.save", sqlMap);

        logSql("supplier_product", "save", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean update(String supplier_product_id, String supplier_id, String product_id, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(SupplierProduct.SUPPLIER_ID, supplier_id);
        sqlMap.put(SupplierProduct.PRODUCT_ID, product_id);
        sqlMap.put(SupplierProduct.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(SupplierProduct.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(SupplierProduct.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("supplier_product.update", sqlMap);

        logSql("supplier_product", "update", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteBySupplier_product_idAndSystem_version(String supplier_product_id, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(SupplierProduct.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(SupplierProduct.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(SupplierProduct.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("supplier_product.deleteBySupplier_product_idAndSystem_version", sqlMap);

        logSql("supplier_product", "deleteBySupplier_product_idAndSystem_version", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}