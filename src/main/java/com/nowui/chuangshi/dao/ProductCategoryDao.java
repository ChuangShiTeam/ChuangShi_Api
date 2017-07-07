package com.nowui.chuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.model.ProductCategory;

import java.util.Date;
import java.util.List;

public class ProductCategoryDao extends Dao {

    public List<ProductCategory> listByApp_id(String app_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(ProductCategory.APP_ID, app_id);
        SqlPara sqlPara = Db.getSqlPara("product_category.listByApp_id", sqlMap);

        logSql("product_category", "listByApp_id", sqlPara);

        return new ProductCategory().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<ProductCategory> listByProduct_category_pathLikeProduct_category_parent_id(String product_category_parent_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(ProductCategory.PRODUCT_CATEGORY_PARENT_ID, product_category_parent_id);
        SqlPara sqlPara = Db.getSqlPara("product_category.listByProduct_category_pathLikeProduct_category_parent_id", sqlMap);

        logSql("product_category", "listByProduct_category_pathLikeProduct_category_parent_id", sqlPara);

        return new ProductCategory().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<ProductCategory> listByApp_idOrLikeProduct_category_name(String app_id, String product_category_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(ProductCategory.APP_ID, app_id);
        sqlMap.put(ProductCategory.PRODUCT_CATEGORY_NAME, product_category_name);
        SqlPara sqlPara = Db.getSqlPara("product_category.listByApp_idOrLikeProduct_category_name", sqlMap);

        logSql("product_category", "listByApp_idOrLikeProduct_category_name", sqlPara);

        return new ProductCategory().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<ProductCategory> listByOrApp_idOrLikeProduct_category_name(String app_id, String product_category_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(ProductCategory.APP_ID, app_id);
        sqlMap.put(ProductCategory.PRODUCT_CATEGORY_NAME, product_category_name);
        SqlPara sqlPara = Db.getSqlPara("product_category.listByOrApp_idOrLikeProduct_category_name", sqlMap);

        logSql("product_category", "listByOrApp_idOrLikeProduct_category_name", sqlPara);

        return new ProductCategory().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public ProductCategory findByProduct_category_id(String product_category_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(ProductCategory.PRODUCT_CATEGORY_ID, product_category_id);
        SqlPara sqlPara = Db.getSqlPara("product_category.findByProduct_category_id", sqlMap);

        logSql("product_category", "findByProduct_category_id", sqlPara);

        List<ProductCategory> product_categoryList = new ProductCategory().find(sqlPara.getSql(), sqlPara.getPara());
        if (product_categoryList.size() == 0) {
            return null;
        } else {
            return product_categoryList.get(0);
        }
    }

    public Boolean save(String product_category_id, String app_id, String product_category_parent_id, String product_category_name, Integer product_category_sort, String product_category_path, String system_create_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(ProductCategory.PRODUCT_CATEGORY_ID, product_category_id);
        sqlMap.put(ProductCategory.APP_ID, app_id);
        sqlMap.put(ProductCategory.PRODUCT_CATEGORY_PARENT_ID, product_category_parent_id);
        sqlMap.put(ProductCategory.PRODUCT_CATEGORY_NAME, product_category_name);
        sqlMap.put(ProductCategory.PRODUCT_CATEGORY_SORT, product_category_sort);
        sqlMap.put(ProductCategory.PRODUCT_CATEGORY_PATH, product_category_path);
        sqlMap.put(ProductCategory.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(ProductCategory.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(ProductCategory.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(ProductCategory.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(ProductCategory.SYSTEM_VERSION, 0);
        sqlMap.put(ProductCategory.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("product_category.save", sqlMap);

        logSql("product_category", "save", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean update(String product_category_id, String product_category_parent_id, String product_category_name, Integer product_category_sort, String product_category_path, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(ProductCategory.PRODUCT_CATEGORY_ID, product_category_id);
        sqlMap.put(ProductCategory.PRODUCT_CATEGORY_PARENT_ID, product_category_parent_id);
        sqlMap.put(ProductCategory.PRODUCT_CATEGORY_NAME, product_category_name);
        sqlMap.put(ProductCategory.PRODUCT_CATEGORY_SORT, product_category_sort);
        sqlMap.put(ProductCategory.PRODUCT_CATEGORY_PATH, product_category_path);
        sqlMap.put(ProductCategory.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(ProductCategory.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(ProductCategory.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("product_category.update", sqlMap);

        logSql("product_category", "update", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByProduct_category_idAndSystem_version(String product_category_id, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(ProductCategory.PRODUCT_CATEGORY_ID, product_category_id);
        sqlMap.put(ProductCategory.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(ProductCategory.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(ProductCategory.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("product_category.deleteByProduct_category_idAndSystem_version", sqlMap);

        logSql("product_category", "deleteByProduct_category_idAndSystem_version", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByProduct_category_parent_id(String product_category_parent_id, String system_update_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(ProductCategory.PRODUCT_CATEGORY_PARENT_ID, product_category_parent_id);
        sqlMap.put(ProductCategory.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(ProductCategory.SYSTEM_UPDATE_TIME, new Date());
        SqlPara sqlPara = Db.getSqlPara("product_category.deleteByProduct_category_parent_id", sqlMap);

        logSql("product_category", "deleteByProduct_category_parent_id", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}