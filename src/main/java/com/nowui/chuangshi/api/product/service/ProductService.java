package com.nowui.chuangshi.api.product.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nowui.chuangshi.api.file.model.File;
import com.nowui.chuangshi.api.product.dao.ProductDao;
import com.nowui.chuangshi.api.product.model.*;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;
import com.nowui.chuangshi.util.Util;

import java.util.ArrayList;
import java.util.List;

public class ProductService extends Service {

    public static final ProductService instance = new ProductService();
    private final String PRODUCT_ITEM_CACHE = "product_item_cache";
    private final ProductDao productDao = new ProductDao();

    public Integer adminCount(String app_id, String product_name) {
        Cnd cnd = new Cnd();
        cnd.where(Product.SYSTEM_STATUS, true);
        cnd.and(Product.APP_ID, app_id);
        cnd.andAllowEmpty(Product.PRODUCT_NAME, product_name);

        Integer count = productDao.count(cnd);
        return count;
    }

    public List<Product> adminList(String app_id, String product_name, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(Product.SYSTEM_STATUS, true);
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
        Cnd cnd = new Cnd();
        cnd.where(Product.SYSTEM_STATUS, true);
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
            Cnd cnd = new Cnd();
            cnd.selectIfNull(File.TABLE_FILE + "." + File.FILE_ID, "", File.FILE_ID);
            cnd.selectIfNull(File.TABLE_FILE + "." + File.FILE_PATH, "", File.FILE_PATH);
            cnd.leftJoin(File.TABLE_FILE, File.FILE_ID, Product.TABLE_PRODUCT, Product.PRODUCT_IMAGE);
            cnd.where(Product.TABLE_PRODUCT + "." + Product.SYSTEM_STATUS, true);
            cnd.and(Product.TABLE_PRODUCT + "." + Product.PRODUCT_ID, product_id);

            product = productDao.find(cnd);

            CacheUtil.put(PRODUCT_ITEM_CACHE, product_id, product);
        }

        return product;
    }

    public Boolean save(Product product, String system_create_user_id) {
        Boolean success = productDao.save(product, system_create_user_id);
        return success;
    }

    public Boolean update(Product product, String product_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(Product.SYSTEM_STATUS, true);
        cnd.and(Product.PRODUCT_ID, product_id);
        cnd.and(Product.SYSTEM_VERSION, system_version);

        Boolean success = productDao.update(product, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(PRODUCT_ITEM_CACHE, product_id);
        }

        return success;
    }

    public void skuSaveOrUpdate(String product_id, JSONArray productSkuJSONArray, List<ProductSku> productSkuList, String request_user_id) {
        List<ProductSku> productSkuSaveList = new ArrayList<ProductSku>();
        List<String> productSkuIdDeleteList = new ArrayList<String>();
        List<ProductSkuCommission> productSkuCommissionSaveList = new ArrayList<ProductSkuCommission>();
        List<String> productSkuCommissionDeleteList = new ArrayList<String>();

        // 新增SKU
        for (int i = 0; i < productSkuJSONArray.size(); i++) {
            Boolean isAttribute = false;

            JSONObject productSkuJSONObject = productSkuJSONArray.getJSONObject(i);
            Boolean product_sku_is_default = productSkuJSONObject.getBoolean(ProductSku.PRODUCT_SKU_IS_DEFAULT);

            JSONArray productSkuAttributeJSONArray = productSkuJSONObject.getJSONArray(ProductSku.PRODUCT_SKU_ATTRIBUTE_LIST);
            JSONArray productSkuPriceJSONArray = productSkuJSONObject.getJSONArray(ProductSku.PRODUCT_SKU_PRICE_LIST);

            for (ProductSku productSku : productSkuList) {
                // 对比SKU属性
                isAttribute = compareAttribute(productSku.getProduct_sku_id(), productSkuAttributeJSONArray);
            }

            if (isAttribute) {

            } else {
                String product_sku_id = Util.getRandomUUID();
                ProductSku productSku = new ProductSku();
                productSku.setProduct_sku_id(product_sku_id);
                productSku.setProduct_id(product_id);
                productSku.setProduct_sku_is_default(product_sku_is_default);
                productSkuSaveList.add(productSku);
            }
        }

        // 删除SKU
        for (ProductSku productSku : productSkuList) {
            Boolean isAttribute = false;

            for (int i = 0; i < productSkuJSONArray.size(); i++) {
                JSONObject productSkuJSONObject = productSkuJSONArray.getJSONObject(i);

                JSONArray productSkuAttributeJSONArray = productSkuJSONObject.getJSONArray(ProductSku.PRODUCT_SKU_ATTRIBUTE_LIST);

                // 对比SKU属性
                isAttribute = compareAttribute(productSku.getProduct_sku_id(), productSkuAttributeJSONArray);
            }

            if (isAttribute) {

            } else {
                productSkuIdDeleteList.add(productSku.getProduct_sku_id());
            }
        }

        ProductSkuService.instance.save(productSkuSaveList, request_user_id);

//        productSkuService.save(product_id, productSkuSaveList, request_user_id);
//        productSkuService.delete(product_id, productSkuIdDeleteList, request_user_id);
//        productSkuPriceService.save(productSkuPriceSaveList, request_user_id);
//        productSkuPriceService.delete(productSkuIdDeleteList, request_user_id);
//        productSkuCommissionService.save(productSkuCommissionSaveList, request_user_id);
//        productSkuCommissionService.delete(productSkuIdDeleteList, request_user_id);
    }

    public void skuPriceSaveOrUpdate(String product_id, JSONArray productSkuJSONArray, List<ProductSku> productSkuList, String request_user_id) {
        List<ProductSkuPrice> productSkuPriceSaveList = new ArrayList<ProductSkuPrice>();
        List<String> productSkuPriceDeleteList = new ArrayList<String>();

        for (int i = 0; i < productSkuJSONArray.size(); i++) {
            JSONObject productSkuJSONObject = productSkuJSONArray.getJSONObject(i);

        }
    }

    private Boolean compareAttribute(String product_sku_id, JSONArray productSkuAttributeJSONArray) {
        Boolean result = false;

        int count = 0;
        List<ProductSkuAttribute> productSkuAttributeList = ProductSkuAttributeService.instance.productSkuList(product_sku_id);

        if (productSkuAttributeList.size() != productSkuAttributeJSONArray.size()) {
            return false;
        }

        for (ProductSkuAttribute productSkuAttribute : productSkuAttributeList) {
            Boolean isExit = false;

            for (int j = 0; j < productSkuAttributeJSONArray.size(); j++) {
                JSONObject productSkuAttributeJSONObject = productSkuAttributeJSONArray.getJSONObject(j);

                if (productSkuAttribute.getProduct_sku_attribute_name()
                        .equals(productSkuAttributeJSONObject.getString(ProductSkuAttribute.PRODUCT_SKU_ATTRIBUTE_NAME))
                        && productSkuAttribute.getProduct_sku_attribute_value().equals(productSkuAttributeJSONObject
                        .getString(ProductSkuAttribute.PRODUCT_SKU_ATTRIBUTE_VALUE))) {
                    isExit = true;

                    break;
                }
            }

            if (isExit) {
                count++;
            }
        }
        if (count == productSkuAttributeList.size()) {
            result = true;
        }

        return result;
    }

    public Boolean delete(String product_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(Product.SYSTEM_STATUS, true);
        cnd.and(Product.PRODUCT_ID, product_id);
        cnd.and(Product.SYSTEM_VERSION, system_version);

        Boolean success = productDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(PRODUCT_ITEM_CACHE, product_id);
        }

        return success;
    }

}