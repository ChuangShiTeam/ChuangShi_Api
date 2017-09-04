package com.nowui.chuangshi.api.product.service;

import com.nowui.chuangshi.api.product.dao.ProductBrandDao;
import com.nowui.chuangshi.api.product.model.ProductBrand;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class ProductBrandService extends Service {

    public static final ProductBrandService instance = new ProductBrandService();
    private final String PRODUCT_BRAND_ITEM_CACHE = "product_brand_item_cache";
    private final ProductBrandDao productBrandDao = new ProductBrandDao();

    public Integer adminCount(String app_id, String product_brand_name) {
        Cnd cnd = Cnd.where(ProductBrand.SYSTEM_STATUS, true);
        cnd.and(ProductBrand.APP_ID, app_id);
        cnd.andAllowEmpty(ProductBrand.PRODUCT_BRAND_NAME, product_brand_name);

        Integer count = productBrandDao.count(cnd);
        return count;
    }

    public List<ProductBrand> adminList(String app_id, String product_brand_name, Integer m, Integer n) {
        Cnd cnd = Cnd.where(ProductBrand.SYSTEM_STATUS, true);
        cnd.and(ProductBrand.APP_ID, app_id);
        cnd.andAllowEmpty(ProductBrand.PRODUCT_BRAND_NAME, product_brand_name);
        cnd.paginate(m, n);

        List<ProductBrand> productBrandList = productBrandDao.primaryKeyList(cnd);
        for (ProductBrand productBrand : productBrandList) {
            productBrand.put(find(productBrand.getProduct_brand_id()));
        }
        return productBrandList;
    }

    public List<ProductBrand> mobileList(String app_id) {
        Cnd cnd = Cnd.where(ProductBrand.SYSTEM_STATUS, true);
        cnd.and(ProductBrand.APP_ID, app_id);

        List<ProductBrand> productBrandList = productBrandDao.primaryKeyList(cnd);
        for (ProductBrand productBrand : productBrandList) {
            productBrand.put(find(productBrand.getProduct_brand_id()));
        }
        return productBrandList;
    }

    public ProductBrand find(String product_brand_id) {
        ProductBrand productBrand = CacheUtil.get(PRODUCT_BRAND_ITEM_CACHE, product_brand_id);

        if (productBrand == null) {
            productBrand = productBrandDao.find(product_brand_id);

            CacheUtil.put(PRODUCT_BRAND_ITEM_CACHE, product_brand_id, productBrand);
        }

        return productBrand;
    }

    public Boolean save(ProductBrand productBrand) {
        Boolean success = productBrandDao.save(productBrand);
        return success;
    }

    public Boolean update(ProductBrand productBrand, String product_brand_id, Integer system_version) {
        Cnd cnd = Cnd.where(ProductBrand.SYSTEM_STATUS, true);
        cnd.and(ProductBrand.PRODUCT_BRAND_ID, product_brand_id);
        cnd.and(ProductBrand.SYSTEM_VERSION, system_version);

        Boolean success = productBrandDao.update(productBrand, cnd);

        if (success) {
            CacheUtil.remove(PRODUCT_BRAND_ITEM_CACHE, product_brand_id);
        }

        return success;
    }

    public Boolean delete(String product_brand_id, Integer system_version) {
        Cnd cnd = Cnd.where(ProductBrand.SYSTEM_STATUS, true);
        cnd.and(ProductBrand.PRODUCT_BRAND_ID, product_brand_id);
        cnd.and(ProductBrand.SYSTEM_VERSION, system_version);

        Boolean success = productBrandDao.delete(cnd);

        if (success) {
            CacheUtil.remove(PRODUCT_BRAND_ITEM_CACHE, product_brand_id);
        }

        return success;
    }

}