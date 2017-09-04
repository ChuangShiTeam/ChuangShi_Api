package com.nowui.chuangshi.api.product.service;

import com.nowui.chuangshi.api.product.dao.ProductSkuDao;
import com.nowui.chuangshi.api.product.model.ProductSku;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class ProductSkuService extends Service {

    public static final ProductSkuService instance = new ProductSkuService();
    private final String PRODUCT_SKU_ITEM_CACHE = "product_sku_item_cache";
    private final ProductSkuDao productSkuDao = new ProductSkuDao();

    public List<ProductSku> productList(String product_id) {
        Cnd cnd = Cnd.where(ProductSku.SYSTEM_STATUS, true);
        cnd.and(ProductSku.PRODUCT_ID, product_id);

        List<ProductSku> productSkuList = productSkuDao.primaryKeyList(cnd);
        for (ProductSku productSku : productSkuList) {
            productSku.put(find(productSku.getProduct_sku_id()));
        }
        return productSkuList;
    }

    public ProductSku find(String product_sku_id) {
        ProductSku product = CacheUtil.get(PRODUCT_SKU_ITEM_CACHE, product_sku_id);

        if (product == null) {
            product = productSkuDao.find(product_sku_id);

            CacheUtil.put(PRODUCT_SKU_ITEM_CACHE, product_sku_id, product);
        }

        return product;
    }

    public Boolean save(ProductSku product) {
        Boolean success = productSkuDao.save(product);
        return success;
    }

    public Boolean update(ProductSku product, String product_sku_id, Integer system_version) {
        Cnd cnd = Cnd.where(ProductSku.SYSTEM_STATUS, true);
        cnd.and(ProductSku.PRODUCT_SKU_ID, product_sku_id);
        cnd.and(ProductSku.SYSTEM_VERSION, system_version);

        Boolean success = productSkuDao.update(product, cnd);

        if (success) {
            CacheUtil.remove(PRODUCT_SKU_ITEM_CACHE, product_sku_id);
        }

        return success;
    }

    public Boolean delete(String product_sku_id, Integer system_version) {
        Cnd cnd = Cnd.where(ProductSku.SYSTEM_STATUS, true);
        cnd.and(ProductSku.PRODUCT_SKU_ID, product_sku_id);
        cnd.and(ProductSku.SYSTEM_VERSION, system_version);

        Boolean success = productSkuDao.delete(cnd);

        if (success) {
            CacheUtil.remove(PRODUCT_SKU_ITEM_CACHE, product_sku_id);
        }

        return success;
    }

}