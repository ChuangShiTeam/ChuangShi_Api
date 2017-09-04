package com.nowui.chuangshi.api.product.service;

import com.nowui.chuangshi.api.product.dao.ProductSkuPriceDao;
import com.nowui.chuangshi.api.product.model.ProductSkuPrice;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class ProductSkuPriceService extends Service {

    public static final ProductSkuPriceService instance = new ProductSkuPriceService();
    private final String PRODUCT_SKU_PRIZE_ITEM_CACHE = "product_sku_prize_item_cache";
    private final ProductSkuPriceDao productSkuPriceDao = new ProductSkuPriceDao();

    public List<ProductSkuPrice> productSkuList(String product_sku_id) {
        Cnd cnd = Cnd.where(ProductSkuPrice.SYSTEM_STATUS, true);
        cnd.and(ProductSkuPrice.PRODUCT_SKU_ID, product_sku_id);

        List<ProductSkuPrice> productSkuPriceList = productSkuPriceDao.primaryKeyList(cnd);
        for (ProductSkuPrice productSkuPrice : productSkuPriceList) {
            productSkuPrice.put(find(productSkuPrice.getProduct_sku_id()));
        }
        return productSkuPriceList;
    }

    public ProductSkuPrice find(String product_sku_id) {
        ProductSkuPrice product = CacheUtil.get(PRODUCT_SKU_PRIZE_ITEM_CACHE, product_sku_id);

        if (product == null) {
            product = productSkuPriceDao.find(product_sku_id);

            CacheUtil.put(PRODUCT_SKU_PRIZE_ITEM_CACHE, product_sku_id, product);
        }

        return product;
    }

    public Boolean save(ProductSkuPrice product) {
        Boolean success = productSkuPriceDao.save(product);
        return success;
    }

    public Boolean update(ProductSkuPrice product, String product_sku_id, Integer system_version) {
        Cnd cnd = Cnd.where(ProductSkuPrice.SYSTEM_STATUS, true);
        cnd.and(ProductSkuPrice.PRODUCT_SKU_ID, product_sku_id);
        cnd.and(ProductSkuPrice.SYSTEM_VERSION, system_version);

        Boolean success = productSkuPriceDao.update(product, cnd);

        if (success) {
            CacheUtil.remove(PRODUCT_SKU_PRIZE_ITEM_CACHE, product_sku_id);
        }

        return success;
    }

    public Boolean delete(String product_sku_id, Integer system_version) {
        Cnd cnd = Cnd.where(ProductSkuPrice.SYSTEM_STATUS, true);
        cnd.and(ProductSkuPrice.PRODUCT_SKU_ID, product_sku_id);
        cnd.and(ProductSkuPrice.SYSTEM_VERSION, system_version);

        Boolean success = productSkuPriceDao.delete(cnd);

        if (success) {
            CacheUtil.remove(PRODUCT_SKU_PRIZE_ITEM_CACHE, product_sku_id);
        }

        return success;
    }

}