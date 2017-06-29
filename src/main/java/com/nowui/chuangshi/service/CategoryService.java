package com.nowui.chuangshi.service;

import com.alibaba.fastjson.JSONArray;
import com.nowui.chuangshi.cache.CategoryCache;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.Category;
import com.nowui.chuangshi.util.ValidateUtil;

import java.util.*;

public class CategoryService extends Service {

    private CategoryCache categoryCache = new CategoryCache();

    public Integer countByApp_idAndParent_idOrLikeCategory_nameOrCategory_type(String app_id, String parent_id, String category_name, String category_type, String request_app_id, String request_http_id, String request_user_id) {
        return categoryCache.countByApp_idAndParent_idOrLikeCategory_nameOrCategory_type(app_id, parent_id, category_name, category_type, request_app_id, request_http_id, request_user_id);
    }

    public Integer countByOrApp_idAndParent_idOrLikeCategory_nameOrCategory_type(String app_id, String parent_id, String category_name, String category_type, String request_app_id, String request_http_id, String request_user_id) {
        return categoryCache.countByOrApp_idAndParent_idOrLikeCategory_nameOrCategory_type(app_id, parent_id, category_name, category_type, request_app_id, request_http_id, request_user_id);
    }

    public Integer countByApp_idAndNotParent_idOrLikeCategory_nameAndCategory_type(String app_id, String parent_id, String category_name, String category_type, String request_app_id, String request_http_id, String request_user_id) {
        return categoryCache.countByApp_idAndNotParent_idOrLikeCategory_nameAndCategory_type(app_id, parent_id, category_name, category_type, request_app_id, request_http_id, request_user_id);
    }

    public Integer countByOrApp_idAndNotParent_idOrLikeCategory_nameAndCategory_type(String app_id, String parent_id, String category_name, String category_type, String request_app_id, String request_http_id, String request_user_id) {
        return categoryCache.countByOrApp_idAndNotParent_idOrLikeCategory_nameAndCategory_type(app_id, parent_id, category_name, category_type, request_app_id, request_http_id, request_user_id);
    }

    public List<Category> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        return categoryCache.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n, request_app_id, request_http_id, request_user_id);
    }

    public List<Category> listByApp_idAndParent_idOrLikeCategory_nameOrCategory_typeAndLimit(String app_id, String parent_id, String category_name, String category_type, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        return categoryCache.listByApp_idAndParent_idOrLikeCategory_nameOrCategory_typeAndLimit(app_id, parent_id, category_name, category_type, m, n, request_app_id, request_http_id, request_user_id);
    }

    public List<Category> listByOrApp_idAndLimit(String app_id, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        return categoryCache.listByOrApp_idAndLimit(app_id, m, n, request_app_id, request_http_id, request_user_id);
    }

    public List<Category> listByOrApp_idAndParent_idOrLikeCategory_nameOrCategory_typeAndLimit(String app_id, String parent_id, String category_name, String category_type, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        return categoryCache.listByOrApp_idAndParent_idOrLikeCategory_nameOrCategory_typeAndLimit(app_id, parent_id, category_name, category_type, m, n, request_app_id, request_http_id, request_user_id);
    }

    public List<Category> listByApp_idAndNotParent_idOrLikeCategory_nameAndCategory_typeAndLimit(String app_id, String parent_id, String category_name, String category_type, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        return categoryCache.listByApp_idAndNotParent_idOrLikeCategory_nameAndCategory_typeAndLimit(app_id, parent_id, category_name, category_type, m, n, request_app_id, request_http_id, request_user_id);
    }

    public List<Category> listByOrApp_idAndNotParent_idOrLikeCategory_nameAndCategory_typeAndLimit(String app_id, String parent_id, String category_name, String category_type, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        return categoryCache.listByOrApp_idAndNotParent_idOrLikeCategory_nameAndCategory_typeAndLimit(app_id, parent_id, category_name, category_type, m, n, request_app_id, request_http_id, request_user_id);
    }

    public List<Map<String, Object>> treeByParent_id(String parent_id, String request_app_id, String request_http_id, String request_user_id) {
        List<Category> categoryList = categoryCache.treeByParent_id(parent_id, request_app_id, request_http_id, request_user_id);

        return getChildren(categoryList, parent_id, Category.CATEGORY_KEY, Category.CATEGORY_VALUE, Category.CATEGORY_SORT, Category.SYSTEM_VERSION);
    }

    private List<Map<String, Object>> getChildren(List<Category> categoryList, String parent_id, String... keys) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (Category category : categoryList) {
            if (category.getParent_id().equals(parent_id)) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(Category.CATEGORY_ID, category.getCategory_id());
                map.put(Category.CATEGORY_NAME, category.getCategory_name());

                for (String key : keys) {
                    map.put(key, category.get(key));
                }

                List<Map<String, Object>> childrenList = getChildren(categoryList, category.getCategory_id(), keys);
                if (childrenList.size() > 0) {
                    map.put(Constant.CHILDREN, childrenList);
                }
                list.add(map);
            }
        }
        return list;
    }

    public Category findByCategory_id(String category_id, String request_app_id, String request_http_id, String request_user_id) {
        return categoryCache.findByCategory_id(category_id, request_app_id, request_http_id, request_user_id);
    }

    public Boolean save(String category_id, String app_id, String parent_id, String category_name, String category_image, String category_key, String category_value, Integer category_sort, String category_type, String system_create_user_id, String request_app_id, String request_http_id, String request_user_id) {
        String category_path = "";

        if (ValidateUtil.isNullOrEmpty(parent_id)) {
            parent_id = Constant.PARENT_ID;

            JSONArray jsonArray = new JSONArray();
            jsonArray.add(parent_id);

            category_path = jsonArray.toJSONString();
        } else {
            Category parent = findByCategory_id(parent_id, request_app_id, request_http_id, request_user_id);

            parent_id = parent.getCategory_id();

            JSONArray jsonArray = JSONArray.parseArray(parent.getCategory_path());
            jsonArray.add(parent_id);

            category_path = jsonArray.toJSONString();
        }

        return categoryCache.save(category_id, app_id, parent_id, category_name, category_image, category_key, category_value, category_path, category_sort, category_type, system_create_user_id, request_app_id, request_http_id, request_user_id);
    }

    public Boolean updateValidateSystem_version(String category_id, String parent_id, String category_name, String category_image, String category_key, String category_value, Integer category_sort, String category_type, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        Category category = findByCategory_id(category_id, request_app_id, request_http_id, request_user_id);

        String category_path = category.getCategory_path();

        if (!category.getParent_id().equals(parent_id)) {
            Category parent = findByCategory_id(parent_id, request_app_id, request_http_id, request_user_id);

            JSONArray jsonArray = JSONArray.parseArray(parent.getCategory_path());
            jsonArray.add(parent_id);

            category_path = jsonArray.toJSONString();
        }

        return categoryCache.updateValidateSystem_version(category_id, parent_id, category_name, category_image, category_key, category_value, category_path, category_sort, category_type, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);
    }

    public Boolean deleteByCategory_idAndSystem_update_user_idValidateSystem_version(String category_id, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        Boolean result = categoryCache.deleteByCategory_idAndSystem_update_user_idValidateSystem_version(category_id, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);

        if (result) {
            categoryCache.deleteByParent_id(category_id, system_update_user_id, request_user_id, request_app_id, request_http_id);
        }

        return result;
    }

}