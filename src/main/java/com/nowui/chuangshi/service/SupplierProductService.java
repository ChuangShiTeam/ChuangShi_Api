package com.nowui.chuangshi.service;

import java.util.Date;
import java.util.List;

import com.nowui.chuangshi.cache.SupplierProductCache;
import com.nowui.chuangshi.model.SupplierProduct;

public class SupplierProductService extends Service {

    private SupplierProductCache supplierProductCache = new SupplierProductCache();

    public List<SupplierProduct> listBySupplier_id(String supplier_id) {
        return supplierProductCache.listBySupplier_id(supplier_id);
    }

    public Integer countByApp_idOrLikeSupplier_product_name(String app_id, String supplier_product_name) {
        return supplierProductCache.countByApp_idOrLikeSupplier_product_name(app_id, supplier_product_name);
    }

    public Integer countByOrApp_idOrLikeSupplier_product_name(String app_id, String supplier_product_name) {
        return supplierProductCache.countByOrApp_idOrLikeSupplier_product_name(app_id, supplier_product_name);
    }

    public List<SupplierProduct> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time,
            int m, int n) {
        return supplierProductCache.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);
    }

    public List<SupplierProduct> listByApp_idOrLikeSupplier_product_nameAndLimit(String app_id,
            String supplier_product_name, int m, int n) {
        return supplierProductCache.listByApp_idOrLikeSupplier_product_nameAndLimit(app_id, supplier_product_name, m,
                n);
    }

    public List<SupplierProduct> listByOrApp_idOrLikeSupplier_product_nameAndLimit(String app_id,
            String supplier_product_name, int m, int n) {
        return supplierProductCache.listByOrApp_idOrLikeSupplier_product_nameAndLimit(app_id, supplier_product_name, m,
                n);
    }

    public SupplierProduct findBySupplier_product_id(String supplier_product_id) {
        return supplierProductCache.findBySupplier_product_id(supplier_product_id);
    }

    public Boolean save(String supplier_id, String product_id, String system_create_user_id) {
        return supplierProductCache.save(supplier_id, product_id, system_create_user_id);
    }

    public Boolean batchSave(List<SupplierProduct> supplierProductlist, String supplier_id) {
        return supplierProductCache.batchSave(supplierProductlist, supplier_id);
    }

    public Boolean updateValidateSystem_version(String supplier_product_id, String supplier_id, String product_id,
            String system_update_user_id, Integer system_version) {
        return supplierProductCache.updateValidateSystem_version(supplier_product_id, supplier_id, product_id,
                system_update_user_id, system_version);
    }

    public Boolean deleteBySupplier_product_idAndSystem_update_user_idValidateSystem_version(String supplier_product_id,
            String system_update_user_id, Integer system_version) {
        return supplierProductCache.deleteBySupplier_product_idAndSystem_update_user_idValidateSystem_version(
                supplier_product_id, system_update_user_id, system_version);
    }

    public boolean deleteBySupplier_id(String supplier_id, String system_update_user_id) {
        return supplierProductCache.deleteBySupplier_id(supplier_id, system_update_user_id);
    }

}