package com.nowui.chuangshi.api.product.service;

import com.nowui.chuangshi.api.product.dao.ProductSkuCommissionDao;
import com.nowui.chuangshi.api.product.model.ProductSkuCommission;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class ProductSkuCommissionService extends Service {

    public static final ProductSkuCommissionService instance = new ProductSkuCommissionService();
    private final String PRODUCT_SKU_COMMISSION_ITEM_CACHE = "product_sku_commission_item_cache";
    private final ProductSkuCommissionDao productSkuCommissionDao = new ProductSkuCommissionDao();

    public List<ProductSkuCommission> allList( ) {
        Cnd cnd = new Cnd();
        cnd.where(ProductSkuCommission.SYSTEM_STATUS, true);

        List<ProductSkuCommission> product_sku_commissionList = productSkuCommissionDao.primaryKeyList(cnd);
        return product_sku_commissionList;
    }

    public List<ProductSkuCommission> productSkuList( String product_sku_id) {
        Cnd cnd = new Cnd();
        cnd.where(ProductSkuCommission.SYSTEM_STATUS, true);
        cnd.and(ProductSkuCommission.PRODUCT_SKU_ID, product_sku_id);

        List<ProductSkuCommission> product_sku_commissionList = productSkuCommissionDao.primaryKeyList(cnd);
        for (ProductSkuCommission product_sku_commission : product_sku_commissionList) {
            product_sku_commission.put(find(product_sku_commission.getProduct_sku_commission_id()));
        }
        return product_sku_commissionList;
    }

    public ProductSkuCommission find(String product_sku_commission_id) {
        ProductSkuCommission product_sku_commission = CacheUtil.get(PRODUCT_SKU_COMMISSION_ITEM_CACHE, product_sku_commission_id);

        if (product_sku_commission == null) {
            product_sku_commission = productSkuCommissionDao.find(product_sku_commission_id);

            CacheUtil.put(PRODUCT_SKU_COMMISSION_ITEM_CACHE, product_sku_commission_id, product_sku_commission);
        }

        return product_sku_commission;
    }

    public Boolean save(ProductSkuCommission product_sku_commission, String system_create_user_id) {
        Boolean success = productSkuCommissionDao.save(product_sku_commission, system_create_user_id);
        return success;
    }

    public Boolean update(ProductSkuCommission product_sku_commission, String product_sku_commission_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(ProductSkuCommission.SYSTEM_STATUS, true);
        cnd.and(ProductSkuCommission.PRODUCT_SKU_COMMISSION_ID, product_sku_commission_id);
        cnd.and(ProductSkuCommission.SYSTEM_VERSION, system_version);

        Boolean success = productSkuCommissionDao.update(product_sku_commission, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(PRODUCT_SKU_COMMISSION_ITEM_CACHE, product_sku_commission_id);
        }

        return success;
    }

    public Boolean delete(String product_sku_commission_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(ProductSkuCommission.SYSTEM_STATUS, true);
        cnd.and(ProductSkuCommission.PRODUCT_SKU_COMMISSION_ID, product_sku_commission_id);
        cnd.and(ProductSkuCommission.SYSTEM_VERSION, system_version);

        Boolean success = productSkuCommissionDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(PRODUCT_SKU_COMMISSION_ITEM_CACHE, product_sku_commission_id);
        }

        return success;
    }

}