package com.nowui.chuangshi.service;

import com.nowui.chuangshi.cache.ProductCache;
import com.nowui.chuangshi.model.Product;

import java.util.Date;
import java.util.List;

public class ProductService extends Service {

    private ProductCache productCache = new ProductCache();

    public Integer countByApp_idOrLikeProduct_name(String app_id, String product_name) {
        return productCache.countByApp_idOrLikeProduct_name(app_id, product_name);
    }

    public Integer countByOrApp_idOrLikeProduct_name(String app_id, String product_name) {
        return productCache.countByOrApp_idOrLikeProduct_name(app_id, product_name);
    }

    public List<Product> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        return productCache.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);
    }

    public List<Product> listByApp_idOrLikeProduct_nameAndLimit(String app_id, String product_name, int m, int n) {
        return productCache.listByApp_idOrLikeProduct_nameAndLimit(app_id, product_name, m, n);
    }

    public List<Product> listByOrApp_idOrLikeProduct_nameAndLimit(String app_id, String product_name, int m, int n) {
        return productCache.listByOrApp_idOrLikeProduct_nameAndLimit(app_id, product_name, m, n);
    }

    public Product findByProduct_id(String product_id) {
        return productCache.findByProduct_id(product_id);
    }

    public Boolean save(String product_id, String app_id, String category_id, String brand_id, String product_name, String product_image, Boolean product_is_new, Boolean product_is_recommend, Boolean product_is_bargain, Boolean product_is_hot, Boolean product_is_sold_out, Boolean product_is_virtual, String product_content, Boolean product_status, String system_create_user_id) {
        return productCache.save(product_id, app_id, category_id, brand_id, product_name, product_image, product_is_new, product_is_recommend, product_is_bargain, product_is_hot, product_is_sold_out, product_is_virtual, product_content, product_status, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String product_id, String category_id, String brand_id, String product_name, String product_image, Boolean product_is_new, Boolean product_is_recommend, Boolean product_is_bargain, Boolean product_is_hot, Boolean product_is_sold_out, Boolean product_is_virtual, String product_content, Boolean product_status, String system_update_user_id, Integer system_version) {
        return productCache.updateValidateSystem_version(product_id, category_id, brand_id, product_name, product_image, product_is_new, product_is_recommend, product_is_bargain, product_is_hot, product_is_sold_out, product_is_virtual, product_content, product_status, system_update_user_id, system_version);
    }

    public Boolean deleteByProduct_idAndSystem_update_user_idValidateSystem_version(String product_id, String system_update_user_id, Integer system_version) {
        return productCache.deleteByProduct_idAndSystem_update_user_idValidateSystem_version(product_id, system_update_user_id, system_version);
    }

}