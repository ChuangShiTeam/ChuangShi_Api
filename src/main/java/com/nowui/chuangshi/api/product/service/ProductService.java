package com.nowui.chuangshi.api.product.service;

import com.nowui.chuangshi.api.product.dao.ProductDao;
import com.nowui.chuangshi.api.product.model.Product;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class ProductService extends Service {

    public static final ProductService instance = new ProductService();
    private final String PRODUCT_ITEM_CACHE = "product_item_cache";
    private final ProductDao productDao = new ProductDao();

    public Integer adminCount(String app_id, String product_name) {
        Integer count = productDao.count(Cnd.where(Product.APP_ID, app_id).andAllowEmpty(Product.PRODUCT_NAME, product_name));
        return count;
    }

    public List<Product> adminList(String app_id, String product_name, Integer m, Integer n) {
        List<Product> productList = productDao.list(Cnd.where(Product.APP_ID, app_id).andAllowEmpty(Product.PRODUCT_NAME, product_name).paginate(m, n));
        return productList;
    }

    public List<Product> productBrandList(String product_brand_id) {
        List<Product> productList = productDao.list(Cnd.where(Product.PRODUCT_BRAND_ID, product_brand_id));
        return productList;
    }

    public Product find(String product_id) {
        Product product = CacheUtil.get(PRODUCT_ITEM_CACHE, product_id);

        if (product == null) {
            product = productDao.find(product_id);

            CacheUtil.put(PRODUCT_ITEM_CACHE, product_id, product);
        }

        return product;
    }

    public Boolean save(Product product) {
        Boolean result = productDao.save(product);
        return result;
    }

    public Boolean update(Product product, String product_id, Integer system_version) {
        Boolean result = productDao.update(product, Cnd.where(Product.PRODUCT_ID, product_id).and(Product.SYSTEM_VERSION, system_version));

        if (result) {
            CacheUtil.remove(PRODUCT_ITEM_CACHE, product_id);
        }

        return result;
    }

    public Boolean delete(String product_id, Integer system_version) {
        Boolean result = productDao.delete(Cnd.where(Product.PRODUCT_ID, product_id).and(Product.SYSTEM_VERSION, system_version));

        if (result) {
            CacheUtil.remove(PRODUCT_ITEM_CACHE, product_id);
        }

        return result;
    }

}