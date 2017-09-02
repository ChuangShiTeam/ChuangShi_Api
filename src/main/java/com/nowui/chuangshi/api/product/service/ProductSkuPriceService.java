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
        List<ProductSkuPrice> productSkuPriceList = productSkuPriceDao.list(Cnd.where(ProductSkuPrice.PRODUCT_SKU_ID, product_sku_id));
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
        Boolean result = productSkuPriceDao.save(product);
        return result;
    }

    public Boolean update(ProductSkuPrice product, String product_sku_id, Integer system_version) {
        Boolean result = productSkuPriceDao.update(product, Cnd.where(ProductSkuPrice.PRODUCT_SKU_ID, product_sku_id).and(ProductSkuPrice.SYSTEM_VERSION, system_version));

        if (result) {
            CacheUtil.remove(PRODUCT_SKU_PRIZE_ITEM_CACHE, product_sku_id);
        }

        return result;
    }

    public Boolean delete(String product_sku_id, Integer system_version) {
        Boolean result = productSkuPriceDao.delete(Cnd.where(ProductSkuPrice.PRODUCT_SKU_ID, product_sku_id).and(ProductSkuPrice.SYSTEM_VERSION, system_version));

        if (result) {
            CacheUtil.remove(PRODUCT_SKU_PRIZE_ITEM_CACHE, product_sku_id);
        }

        return result;
    }

}