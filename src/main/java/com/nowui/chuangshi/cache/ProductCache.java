package com.nowui.chuangshi.cache;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.ProductDao;
import com.nowui.chuangshi.model.Product;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.Date;
import java.util.List;

public class ProductCache extends Cache {

    public static final String PRODUCT_BY_PRODUCT_ID_CACHE = "product_by_product_id_cache";

    private ProductDao productDao = new ProductDao();

    public Integer countByApp_idOrLikeProduct_name(String app_id, String product_name) {
        return productDao.countByApp_idOrLikeProduct_name(app_id, product_name);
    }

    public Integer countByOrApp_idOrLikeProduct_name(String app_id, String product_name) {
        return productDao.countByOrApp_idOrLikeProduct_name(app_id, product_name);
    }

    public List<Product> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        List<Product> productList = productDao.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);

        for (Product product : productList) {
            product.put(findByProduct_id(product.getProduct_id()));
        }

        return productList;
    }

    public List<Product> listByApp_idOrLikeProduct_nameAndLimit(String app_id, String product_name, int m, int n) {
        List<Product> productList = productDao.listByApp_idOrLikeProduct_nameAndLimit(app_id, product_name, m, n);

        for (Product product : productList) {
            product.put(findByProduct_id(product.getProduct_id()));
        }

        return productList;
    }

    public List<Product> listByOrApp_idOrLikeProduct_nameAndLimit(String app_id, String product_name, int m, int n) {
        List<Product> productList = productDao.listByOrApp_idOrLikeProduct_nameAndLimit(app_id, product_name, m, n);

        for (Product product : productList) {
            product.put(findByProduct_id(product.getProduct_id()));
        }

        return productList;
    }

    public Product findByProduct_id(String product_id) {
        Product product = CacheUtil.get(PRODUCT_BY_PRODUCT_ID_CACHE, product_id);

        if (product == null) {
            product = productDao.findByProduct_id(product_id);

            CacheUtil.put(PRODUCT_BY_PRODUCT_ID_CACHE, product_id, product);
        }

        return product;
    }

    public Boolean save(String product_id, String app_id, String product_snap_id, String product_category_id, String product_brand_id, String product_name, String product_image, Boolean product_is_new, Boolean product_is_recommend, Boolean product_is_bargain, Boolean product_is_hot, Boolean product_is_sold_out, Boolean product_is_virtual, String product_content, Boolean product_status, String system_create_user_id) {
        return productDao.save(product_id, app_id, product_snap_id, product_category_id, product_brand_id, product_name, product_image, product_is_new, product_is_recommend, product_is_bargain, product_is_hot, product_is_sold_out, product_is_virtual, product_content, product_status, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String product_id, String product_category_id, String product_brand_id, String product_name, String product_image, Boolean product_is_new, Boolean product_is_recommend, Boolean product_is_bargain, Boolean product_is_hot, Boolean product_is_sold_out, Boolean product_is_virtual, String product_content, Boolean product_status, String system_update_user_id, Integer system_version) {
        Product product = findByProduct_id(product_id);
        if (!product.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = productDao.update(product_id, product_category_id, product_brand_id, product_name, product_image, product_is_new, product_is_recommend, product_is_bargain, product_is_hot, product_is_sold_out, product_is_virtual, product_content, product_status, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(PRODUCT_BY_PRODUCT_ID_CACHE, product_id);
        }

        return result;
    }

    public Boolean deleteByProduct_idAndSystem_update_user_idValidateSystem_version(String product_id, String system_update_user_id, Integer system_version) {
        Product product = findByProduct_id(product_id);
        if (!product.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = productDao.deleteByProduct_idAndSystem_version(product_id, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(PRODUCT_BY_PRODUCT_ID_CACHE, product_id);
        }

        return result;
    }

}