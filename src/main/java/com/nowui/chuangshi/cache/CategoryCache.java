package com.nowui.chuangshi.cache;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.CategoryDao;
import com.nowui.chuangshi.model.Category;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.Date;
import java.util.List;

public class CategoryCache extends Cache {

    public static final String CATEGORY_BY_CATEGORY_ID_CACHE = "category_by_category_id_cache";

    private CategoryDao categoryDao = new CategoryDao();

    public Integer countByApp_idAndParent_idOrLikeCategory_nameOrCategory_type(String app_id, String parent_id, String category_name, String category_type) {
        return categoryDao.countByApp_idAndParent_idOrLikeCategory_nameOrCategory_type(app_id, parent_id, category_name, category_type);
    }

    public Integer countByOrApp_idAndParent_idOrLikeCategory_nameOrCategory_type(String app_id, String parent_id, String category_name, String category_type) {
        return categoryDao.countByOrApp_idAndParent_idOrLikeCategory_nameOrCategory_type(app_id, parent_id, category_name, category_type);
    }

    public Integer countByApp_idAndNotParent_idOrLikeCategory_nameAndCategory_type(String app_id, String parent_id, String category_name, String category_type) {
        return categoryDao.countByApp_idAndNotParent_idOrLikeCategory_nameAndCategory_type(app_id, parent_id, category_name, category_type);
    }

    public Integer countByOrApp_idAndNotParent_idOrLikeCategory_nameAndCategory_type(String app_id, String parent_id, String category_name, String category_type) {
        return categoryDao.countByOrApp_idAndNotParent_idOrLikeCategory_nameAndCategory_type(app_id, parent_id, category_name, category_type);
    }

    public List<Category> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        List<Category> categoryList = categoryDao.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);

        for (Category category : categoryList) {
            category.put(findByCategory_id(category.getCategory_id()));
        }

        return categoryList;
    }

    public List<Category> listByApp_idAndParent_idOrLikeCategory_nameOrCategory_typeAndLimit(String app_id, String parent_id, String category_name, String category_type, int m, int n) {
        List<Category> categoryList = categoryDao.listByApp_idAndParent_idOrLikeCategory_nameOrCategory_typeAndLimit(app_id, parent_id, category_name, category_type, m, n);

        for (Category category : categoryList) {
            category.put(findByCategory_id(category.getCategory_id()));
        }

        return categoryList;
    }

    public List<Category> listByOrApp_idAndLimit(String app_id, int m, int n) {
        List<Category> categoryList = categoryDao.listByOrApp_idAndLimit(app_id, m, n);

        for (Category category : categoryList) {
            category.put(findByCategory_id(category.getCategory_id()));
        }

        return categoryList;
    }

    public List<Category> listByOrApp_idAndParent_idOrLikeCategory_nameOrCategory_typeAndLimit(String app_id, String parent_id, String category_name, String category_type, int m, int n) {
        List<Category> categoryList = categoryDao.listByOrApp_idAndParent_idOrLikeCategory_nameOrCategory_typeAndLimit(app_id, parent_id, category_name, category_type, m, n);

        for (Category category : categoryList) {
            category.put(findByCategory_id(category.getCategory_id()));
        }

        return categoryList;
    }

    public List<Category> listByApp_idAndNotParent_idOrLikeCategory_nameAndCategory_typeAndLimit(String app_id, String parent_id, String category_name, String category_type, int m, int n) {
        List<Category> categoryList = categoryDao.listByApp_idAndNotParent_idOrLikeCategory_nameAndCategory_typeAndLimit(app_id, parent_id, category_name, category_type, m, n);

        for (Category category : categoryList) {
            category.put(findByCategory_id(category.getCategory_id()));
        }

        return categoryList;
    }

    public List<Category> listByOrApp_idAndNotParent_idOrLikeCategory_nameAndCategory_typeAndLimit(String app_id, String parent_id, String category_name, String category_type, int m, int n) {
        List<Category> categoryList = categoryDao.listByOrApp_idAndNotParent_idOrLikeCategory_nameAndCategory_typeAndLimit(app_id, parent_id, category_name, category_type, m, n);

        for (Category category : categoryList) {
            category.put(findByCategory_id(category.getCategory_id()));
        }

        return categoryList;
    }

    public List<Category> treeByParent_id(String parent_id) {
        List<Category> categoryList = categoryDao.treeByParent_id(parent_id);

        for (Category category : categoryList) {
            category.put(findByCategory_id(category.getCategory_id()));
        }

        return categoryList;
    }

    public Category findByCategory_id(String category_id) {
        Category category = CacheUtil.get(CATEGORY_BY_CATEGORY_ID_CACHE, category_id);

        if (category == null) {
            category = categoryDao.findByCategory_id(category_id);

            CacheUtil.put(CATEGORY_BY_CATEGORY_ID_CACHE, category_id, category);
        }

        return category;
    }

    public Boolean save(String category_id, String app_id, String parent_id, String category_name, String category_image, String category_key, String category_value, String category_path, Integer category_sort, String category_type, String system_create_user_id) {
        return categoryDao.save(category_id, app_id, parent_id, category_name, category_image, category_key, category_value, category_path, category_sort, category_type, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String category_id, String parent_id, String category_name, String category_image, String category_key, String category_value, String category_path, Integer category_sort, String category_type, String system_update_user_id, Integer system_version) {
        Category category = findByCategory_id(category_id);
        if (!category.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = categoryDao.update(category_id, parent_id, category_name, category_image, category_key, category_value, category_path, category_sort, category_type, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(CATEGORY_BY_CATEGORY_ID_CACHE, category_id);
        }

        return result;
    }

    public Boolean deleteByCategory_idAndSystem_update_user_idValidateSystem_version(String category_id, String system_update_user_id, Integer system_version) {
        Category category = findByCategory_id(category_id);
        if (!category.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = categoryDao.deleteByCategory_idAndSystem_version(category_id, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(CATEGORY_BY_CATEGORY_ID_CACHE, category_id);
        }

        return result;
    }

    public Boolean deleteByParent_id(String parent_id, String system_update_user_id) {
        List<Category> categoryList = categoryDao.treeByParent_id(parent_id);

        boolean result = categoryDao.deleteByParent_id(parent_id, system_update_user_id);

        if (result) {
            for (Category category : categoryList) {
                CacheUtil.remove(CATEGORY_BY_CATEGORY_ID_CACHE, category.getCategory_id());
            }
        }

        return result;
    }

}