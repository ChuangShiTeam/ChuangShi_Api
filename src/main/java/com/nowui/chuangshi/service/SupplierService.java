package com.nowui.chuangshi.service;

import com.nowui.chuangshi.cache.SupplierCache;
import com.nowui.chuangshi.model.Supplier;

import java.util.Date;
import java.util.List;

public class SupplierService extends Service {

    private SupplierCache supplierCache = new SupplierCache();

    public Integer countByApp_id(String app_id) {
        return supplierCache.countByApp_id(app_id);
    }

    public Integer countByOrApp_idOrLikeSupplier_name(String app_id, String supplier_name) {
        return supplierCache.countByOrApp_idOrLikeSupplier_name(app_id, supplier_name);
    }

    public List<Supplier> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        return supplierCache.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);
    }

    public List<Supplier> listByApp_idAndLimit(String app_id, int m, int n) {
        return supplierCache.listByApp_idAndLimit(app_id, m, n);
    }

    public List<Supplier> listByOrApp_idOrLikeSupplier_nameAndLimit(String app_id, String supplier_name, int m, int n) {
        return supplierCache.listByOrApp_idOrLikeSupplier_nameAndLimit(app_id, supplier_name, m, n);
    }

    public Supplier findBySupplier_id(String supplier_id) {
        return supplierCache.findBySupplier_id(supplier_id);
    }

    public Boolean save(String supplier_id, String app_id, String user_id, Boolean supplier_status, String system_create_user_id) {
        return supplierCache.save(supplier_id, app_id, user_id, supplier_status, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String supplier_id, String user_id, Boolean supplier_status, String system_update_user_id, Integer system_version) {
        return supplierCache.updateValidateSystem_version(supplier_id, user_id, supplier_status, system_update_user_id, system_version);
    }

    public Boolean deleteBySupplier_idAndSystem_update_user_idValidateSystem_version(String supplier_id, String system_update_user_id, Integer system_version) {
        return supplierCache.deleteBySupplier_idAndSystem_update_user_idValidateSystem_version(supplier_id, system_update_user_id, system_version);
    }

}