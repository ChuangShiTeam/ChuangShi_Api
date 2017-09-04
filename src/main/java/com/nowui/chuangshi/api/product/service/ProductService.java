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
        Cnd cnd = Cnd.where(Product.SYSTEM_STATUS, true);
        cnd.and(Product.APP_ID, app_id);
        cnd.andAllowEmpty(Product.PRODUCT_NAME, product_name);

        Integer count = productDao.count(cnd);
        return count;
    }

    public List<Product> adminList(String app_id, String product_name, Integer m, Integer n) {
        Cnd cnd = Cnd.where(Product.SYSTEM_STATUS, true);
        cnd.and(Product.APP_ID, app_id);
        cnd.andAllowEmpty(Product.PRODUCT_NAME, product_name);
        cnd.paginate(m, n);

        List<Product> productList = productDao.primaryKeyList(cnd);
        for (Product product : productList) {
            product.put(find(product.getProduct_id()));
        }
        return productList;
    }

    public List<Product> productBrandList(String product_brand_id) {
        Cnd cnd = Cnd.where(Product.SYSTEM_STATUS, true);
        cnd.and(Product.PRODUCT_BRAND_ID, product_brand_id);

        List<Product> productList = productDao.primaryKeyList(cnd);
        for (Product product : productList) {
            product.put(find(product.getProduct_id()));
        }
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
        Boolean success = productDao.save(product);
        return success;
    }

    public Boolean update(Product product, String product_id, Integer system_version) {
        Cnd cnd = Cnd.where(Product.SYSTEM_STATUS, true);
        cnd.and(Product.PRODUCT_ID, product_id);
        cnd.and(Product.SYSTEM_VERSION, system_version);

        Boolean success = productDao.update(product, cnd);

        if (success) {
            CacheUtil.remove(PRODUCT_ITEM_CACHE, product_id);
        }

        return success;
    }

    public Boolean delete(String product_id, Integer system_version) {
        Cnd cnd = Cnd.where(Product.SYSTEM_STATUS, true);
        cnd.and(Product.PRODUCT_ID, product_id);
        cnd.and(Product.SYSTEM_VERSION, system_version);

        Boolean success = productDao.delete(cnd);

        if (success) {
            CacheUtil.remove(PRODUCT_ITEM_CACHE, product_id);
        }

        return success;
    }

}