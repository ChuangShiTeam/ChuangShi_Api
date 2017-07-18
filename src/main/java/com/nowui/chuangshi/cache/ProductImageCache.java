package com.nowui.chuangshi.cache;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.ProductImageDao;
import com.nowui.chuangshi.model.ProductImage;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.Date;
import java.util.List;

public class ProductImageCache extends Cache {

    public static final String PRODUCT_IMAGE_LIST_BY_PRODUCT_ID_CACHE = "product_image_list_by_product_id_cache";

    private ProductImageDao productImageDao = new ProductImageDao();

    public List<ProductImage> listByProduct_id(String product_id) {
        List<ProductImage> productImageList = CacheUtil.get(PRODUCT_IMAGE_LIST_BY_PRODUCT_ID_CACHE, product_id);

        if (productImageList == null) {
            productImageList = productImageDao.listByProduct_id(product_id);

            CacheUtil.put(PRODUCT_IMAGE_LIST_BY_PRODUCT_ID_CACHE, product_id, productImageList);
        }

        return productImageList;
    }

    public Boolean save(List<ProductImage> productImageList, String system_create_user_id) {
        if (productImageList.size() == 0) {
            return false;
        }

        return productImageDao.save(productImageList, system_create_user_id);
    }

    public Boolean update(List<ProductImage> productImageList, String product_id, String system_update_user_id) {
        if (productImageList.size() == 0) {
            return false;
        }

        boolean result = productImageDao.update(productImageList, system_update_user_id);

        if (result) {
            CacheUtil.remove(PRODUCT_IMAGE_LIST_BY_PRODUCT_ID_CACHE, product_id);
        }

        return result;
    }

    public Boolean delete(List<ProductImage> productImageList, String product_id, String system_update_user_id) {
        if (productImageList.size() == 0) {
            return false;
        }

        boolean result = productImageDao.delete(productImageList, system_update_user_id);

        if (result) {
            CacheUtil.remove(PRODUCT_IMAGE_LIST_BY_PRODUCT_ID_CACHE, product_id);
        }

        return result;
    }

}