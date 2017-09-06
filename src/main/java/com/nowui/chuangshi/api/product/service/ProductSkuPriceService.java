package com.nowui.chuangshi.api.product.service;

import com.nowui.chuangshi.api.product.dao.ProductSkuPriceDao;
import com.nowui.chuangshi.api.product.model.ProductSkuPrice;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class ProductSkuPriceService extends Service {

    public static final ProductSkuPriceService instance = new ProductSkuPriceService();
    private final String PRODUCT_SKU_PRIZE_LIST_CACHE = "product_sku_prize_list_cache";
    private final ProductSkuPriceDao productSkuPriceDao = new ProductSkuPriceDao();

    public List<ProductSkuPrice> productSkuList(String product_sku_id) {
        List<ProductSkuPrice> productSkuPriceList = CacheUtil.get(PRODUCT_SKU_PRIZE_LIST_CACHE, product_sku_id);

        if (productSkuPriceList == null) {
            Cnd cnd = Cnd.where(ProductSkuPrice.SYSTEM_STATUS, true);
            cnd.and(ProductSkuPrice.PRODUCT_SKU_ID, product_sku_id);
            productSkuPriceList = productSkuPriceDao.list(cnd);

            CacheUtil.put(PRODUCT_SKU_PRIZE_LIST_CACHE, product_sku_id, productSkuPriceList);
        }

        return productSkuPriceList;
    }

    public ProductSkuPrice find(String product_sku_id) {
        ProductSkuPrice product = productSkuPriceDao.find(product_sku_id);
        return product;
    }

    public Boolean save(ProductSkuPrice product, String system_create_user_id) {
        Boolean success = productSkuPriceDao.save(product, system_create_user_id);
        return success;
    }

    public Boolean update(ProductSkuPrice product, String product_sku_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = Cnd.where(ProductSkuPrice.SYSTEM_STATUS, true);
        cnd.and(ProductSkuPrice.PRODUCT_SKU_ID, product_sku_id);
        cnd.and(ProductSkuPrice.SYSTEM_VERSION, system_version);

        Boolean success = productSkuPriceDao.update(product, system_update_user_id, system_version, cnd);
        return success;
    }

    public Boolean delete(String product_sku_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = Cnd.where(ProductSkuPrice.SYSTEM_STATUS, true);
        cnd.and(ProductSkuPrice.PRODUCT_SKU_ID, product_sku_id);
        cnd.and(ProductSkuPrice.SYSTEM_VERSION, system_version);

        Boolean success = productSkuPriceDao.delete(system_update_user_id, system_version, cnd);
        return success;
    }

}