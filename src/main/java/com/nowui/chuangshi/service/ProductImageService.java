package com.nowui.chuangshi.service;

import com.nowui.chuangshi.cache.ProductImageCache;
import com.nowui.chuangshi.model.ProductImage;

import java.util.List;

public class ProductImageService extends Service {

    private ProductImageCache productImageCache = new ProductImageCache();

    public List<ProductImage> listByProduct_id(String product_id) {
        return productImageCache.listByProduct_id(product_id);
    }

    public Boolean save(List<ProductImage> productImageList, String system_create_user_id) {
        return productImageCache.save(productImageList, system_create_user_id);
    }

    public Boolean update(List<ProductImage> productImageList, String product_id, String system_update_user_id) {
        return productImageCache.update(productImageList, product_id, system_update_user_id);
    }

    public Boolean delete(List<ProductImage> productImageList, String product_id, String system_update_user_id) {
        return productImageCache.delete(productImageList, product_id, system_update_user_id);
    }

}