package com.nowui.chuangshi.cache;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.SupplierProductDao;
import com.nowui.chuangshi.model.SupplierProduct;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.Date;
import java.util.List;

public class SupplierProductCache extends Cache {

    public static final String SUPPLIER_PRODUCT_BY_SUPPLIER_PRODUCT_ID_CACHE = "supplier_product_by_supplier_product_id_cache";

    private SupplierProductDao supplierProductDao = new SupplierProductDao();

    public Integer countByApp_idOrLikeSupplier_product_name(String app_id, String supplier_product_name) {
        return supplierProductDao.countByApp_idOrLikeSupplier_product_name(app_id, supplier_product_name);
    }

    public Integer countByOrApp_idOrLikeSupplier_product_name(String app_id, String supplier_product_name) {
        return supplierProductDao.countByOrApp_idOrLikeSupplier_product_name(app_id, supplier_product_name);
    }

    public List<SupplierProduct> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        List<SupplierProduct> supplier_productList = supplierProductDao.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);

        for (SupplierProduct supplier_product : supplier_productList) {
            supplier_product.put(findBySupplier_product_id(""));
        }

        return supplier_productList;
    }

    public List<SupplierProduct> listByApp_idOrLikeSupplier_product_nameAndLimit(String app_id, String supplier_product_name, int m, int n) {
        List<SupplierProduct> supplier_productList = supplierProductDao.listByApp_idOrLikeSupplier_product_nameAndLimit(app_id, supplier_product_name, m, n);

        for (SupplierProduct supplier_product : supplier_productList) {
            supplier_product.put(findBySupplier_product_id(""));
        }

        return supplier_productList;
    }

    public List<SupplierProduct> listByOrApp_idOrLikeSupplier_product_nameAndLimit(String app_id, String supplier_product_name, int m, int n) {
        List<SupplierProduct> supplier_productList = supplierProductDao.listByOrApp_idOrLikeSupplier_product_nameAndLimit(app_id, supplier_product_name, m, n);

        for (SupplierProduct supplier_product : supplier_productList) {
            supplier_product.put(findBySupplier_product_id(""));
        }

        return supplier_productList;
    }

    public SupplierProduct findBySupplier_product_id(String supplier_product_id) {
        SupplierProduct supplier_product = CacheUtil.get(SUPPLIER_PRODUCT_BY_SUPPLIER_PRODUCT_ID_CACHE, supplier_product_id);

        if (supplier_product == null) {
            supplier_product = supplierProductDao.findBySupplier_product_id(supplier_product_id);

            CacheUtil.put(SUPPLIER_PRODUCT_BY_SUPPLIER_PRODUCT_ID_CACHE, supplier_product_id, supplier_product);
        }

        return supplier_product;
    }

    public Boolean save(String supplier_id, String product_id, String system_create_user_id) {
        return supplierProductDao.save(supplier_id, product_id, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String supplier_product_id, String supplier_id, String product_id, String system_update_user_id, Integer system_version) {
        SupplierProduct supplier_product = findBySupplier_product_id(supplier_product_id);
        if (!supplier_product.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = supplierProductDao.update(supplier_product_id, supplier_id, product_id, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(SUPPLIER_PRODUCT_BY_SUPPLIER_PRODUCT_ID_CACHE, supplier_product_id);
        }

        return result;
    }

    public Boolean deleteBySupplier_product_idAndSystem_update_user_idValidateSystem_version(String supplier_product_id, String system_update_user_id, Integer system_version) {
        SupplierProduct supplier_product = findBySupplier_product_id(supplier_product_id);
        if (!supplier_product.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = supplierProductDao.deleteBySupplier_product_idAndSystem_version(supplier_product_id, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(SUPPLIER_PRODUCT_BY_SUPPLIER_PRODUCT_ID_CACHE, supplier_product_id);
        }

        return result;
    }

}