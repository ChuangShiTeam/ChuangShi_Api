package com.nowui.chuangshi.service;

import com.alibaba.fastjson.JSONArray;
import com.nowui.chuangshi.cache.ProductCategoryCache;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.ProductCategory;
import com.nowui.chuangshi.util.ValidateUtil;

import java.util.*;

public class ProductCategoryService extends Service {

    private ProductCategoryCache productCategoryCache = new ProductCategoryCache();

    private List<Map<String, Object>> getChildren(List<ProductCategory> productCategoryList, String product_category_parent_id, String... keys) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (ProductCategory productCategory : productCategoryList) {
            if (productCategory.getProduct_category_parent_id().equals(product_category_parent_id)) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(ProductCategory.PRODUCT_CATEGORY_ID, productCategory.getProduct_category_id());
                map.put(ProductCategory.PRODUCT_CATEGORY_NAME, productCategory.getProduct_category_name());

                for (String key : keys) {
                    map.put(key, productCategory.get(key));
                }

                List<Map<String, Object>> childrenList = getChildren(productCategoryList, productCategory.getProduct_category_id(), keys);
                if (childrenList.size() > 0) {
                    map.put(Constant.CHILDREN, childrenList);
                }
                list.add(map);
            }
        }
        return list;
    }

    public List<Map<String, Object>> treeByApp_idOrLikeProduct_category_name(String app_id, String product_category_name) {
        List<ProductCategory> productCategoryList = productCategoryCache.listByApp_idOrLikeProduct_category_name(app_id, product_category_name);

        return getChildren(productCategoryList, Constant.PARENT_ID, ProductCategory.PRODUCT_CATEGORY_NAME, ProductCategory.PRODUCT_CATEGORY_SORT, ProductCategory.SYSTEM_VERSION);
    }

    public List<Map<String, Object>> treeByOrApp_idOrLikeProduct_category_name(String app_id, String product_category_name) {
        List<ProductCategory> productCategoryList = productCategoryCache.listByOrApp_idOrLikeProduct_category_name(app_id, product_category_name);

        return getChildren(productCategoryList, Constant.PARENT_ID, ProductCategory.PRODUCT_CATEGORY_NAME, ProductCategory.PRODUCT_CATEGORY_SORT, ProductCategory.SYSTEM_VERSION);
    }

    public ProductCategory findByProduct_category_id(String product_category_id) {
        return productCategoryCache.findByProduct_category_id(product_category_id);
    }

    public Boolean save(String product_category_id, String app_id, String product_category_parent_id, String product_category_name, Integer product_category_sort, String system_create_user_id) {
        String product_category_path = "";

        if (ValidateUtil.isNullOrEmpty(product_category_parent_id)) {
            product_category_parent_id = Constant.PARENT_ID;

            JSONArray jsonArray = new JSONArray();
            jsonArray.add(product_category_parent_id);

            product_category_path = jsonArray.toJSONString();
        } else {
            ProductCategory parent = productCategoryCache.findByProduct_category_id(product_category_parent_id);

            JSONArray jsonArray = JSONArray.parseArray(parent.getProduct_category_path());
            jsonArray.add(product_category_parent_id);

            product_category_path = jsonArray.toJSONString();
        }

        return productCategoryCache.save(product_category_id, app_id, product_category_parent_id, product_category_name, product_category_sort, product_category_path, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String product_category_id, String product_category_parent_id, String product_category_name, Integer product_category_sort, String system_update_user_id, Integer system_version) {
        ProductCategory productCategory = productCategoryCache.findByProduct_category_id(product_category_id);

        String product_category_path = productCategory.getProduct_category_path();

        if (!productCategory.getProduct_category_parent_id().equals(product_category_parent_id)) {
            ProductCategory parent = productCategoryCache.findByProduct_category_id(product_category_parent_id);

            JSONArray jsonArray = JSONArray.parseArray(parent.getProduct_category_path());
            jsonArray.add(product_category_parent_id);

            product_category_path = jsonArray.toJSONString();
        }

        return productCategoryCache.updateValidateSystem_version(product_category_id, product_category_parent_id, product_category_name, product_category_sort, product_category_path, system_update_user_id, system_version);
    }

    public Boolean deleteByProduct_category_idAndSystem_update_user_idValidateSystem_version(String product_category_id, String system_update_user_id, Integer system_version) {
        Boolean result = productCategoryCache.deleteByProduct_category_idAndSystem_update_user_idValidateSystem_version(product_category_id, system_update_user_id, system_version);

        if (result) {
            productCategoryCache.deleteByProduct_category_parent_id(product_category_id, system_update_user_id);
        }

        return result;
    }

}