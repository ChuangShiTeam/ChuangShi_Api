package com.nowui.chuangshi.cache;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.SupplierDao;
import com.nowui.chuangshi.model.Supplier;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.Date;
import java.util.List;

public class SupplierCache extends Cache {

    public static final String SUPPLIER_BY_SUPPLIER_ID_CACHE = "supplier_by_supplier_id_cache";

    private SupplierDao supplierDao = new SupplierDao();

    public Integer countByApp_id(String app_id) {
        return supplierDao.countByApp_id(app_id);
    }

    public Integer countByOrApp_idOrLikeSupplier_name(String app_id, String supplier_name) {
        return supplierDao.countByOrApp_idOrLikeSupplier_name(app_id, supplier_name);
    }

    public List<Supplier> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        List<Supplier> supplierList = supplierDao.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);

        for (Supplier supplier : supplierList) {
            supplier.put(findBySupplier_id(supplier.getSupplier_id()));
        }

        return supplierList;
    }

    public List<Supplier> listByApp_idAndLimit(String app_id, int m, int n) {
        List<Supplier> supplierList = supplierDao.listByApp_idAndLimit(app_id, m, n);

        for (Supplier supplier : supplierList) {
            supplier.put(findBySupplier_id(supplier.getSupplier_id()));
        }

        return supplierList;
    }

    public List<Supplier> listByOrApp_idOrLikeSupplier_nameAndLimit(String app_id, String supplier_name, int m, int n) {
        List<Supplier> supplierList = supplierDao.listByOrApp_idOrLikeSupplier_nameAndLimit(app_id, supplier_name, m, n);

        for (Supplier supplier : supplierList) {
            supplier.put(findBySupplier_id(supplier.getSupplier_id()));
        }

        return supplierList;
    }

    public Supplier findBySupplier_id(String supplier_id) {
        Supplier supplier = CacheUtil.get(SUPPLIER_BY_SUPPLIER_ID_CACHE, supplier_id);

        if (supplier == null) {
            supplier = supplierDao.findBySupplier_id(supplier_id);

            CacheUtil.put(SUPPLIER_BY_SUPPLIER_ID_CACHE, supplier_id, supplier);
        }

        return supplier;
    }

    public Boolean save(String supplier_id, String app_id, String user_id, Boolean supplier_status, String system_create_user_id) {
        return supplierDao.save(supplier_id, app_id, user_id, supplier_status, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String supplier_id, String user_id, Boolean supplier_status, String system_update_user_id, Integer system_version) {
        Supplier supplier = findBySupplier_id(supplier_id);
        if (!supplier.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = supplierDao.update(supplier_id, user_id, supplier_status, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(SUPPLIER_BY_SUPPLIER_ID_CACHE, supplier_id);
        }

        return result;
    }

    public Boolean deleteBySupplier_idAndSystem_update_user_idValidateSystem_version(String supplier_id, String system_update_user_id, Integer system_version) {
        Supplier supplier = findBySupplier_id(supplier_id);
        if (!supplier.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = supplierDao.deleteBySupplier_idAndSystem_version(supplier_id, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(SUPPLIER_BY_SUPPLIER_ID_CACHE, supplier_id);
        }

        return result;
    }

}