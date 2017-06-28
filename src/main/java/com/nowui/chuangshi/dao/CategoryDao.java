package com.nowui.chuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.Category;

import java.util.Date;
import java.util.List;

public class CategoryDao extends Dao {

    public Integer countByApp_id(String app_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Category.APP_ID, app_id);
        SqlPara sqlPara = Db.getSqlPara("category.countByApp_id", sqlMap);

        logSql(request_app_id, request_http_id, "table_category", "countByApp_id", sqlPara, request_user_id);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByOrApp_id(String app_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Category.APP_ID, app_id);
        SqlPara sqlPara = Db.getSqlPara("category.countByOrApp_id", sqlMap);

        logSql(request_app_id, request_http_id, "table_category", "countByOrApp_id", sqlPara, request_user_id);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByOrApp_idAndParent_id(String app_id, String parent_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Category.APP_ID, app_id);
        sqlMap.put(Category.PARENT_ID, parent_id);
        SqlPara sqlPara = Db.getSqlPara("category.countByOrApp_idAndParent_id", sqlMap);

        logSql(request_app_id, request_http_id, "table_category", "countByOrApp_idAndParent_id", sqlPara, request_user_id);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByOrApp_idAndNotParent_idAndCategory_nameAndCategory_type(String app_id, String parent_id, String category_name, String category_type, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Category.APP_ID, app_id);
        sqlMap.put(Category.PARENT_ID, parent_id);
        sqlMap.put(Category.CATEGORY_NAME, category_name);
        sqlMap.put(Category.CATEGORY_TYPE, category_type);
        SqlPara sqlPara = Db.getSqlPara("category.countByOrApp_idAndNotParent_idAndCategory_nameAndCategory_type", sqlMap);

        logSql(request_app_id, request_http_id, "table_category", "countByOrApp_idAndNotParent_idAndCategory_nameAndCategory_type", sqlPara, request_user_id);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<Category> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Category.APP_ID, app_id);
        sqlMap.put(Category.SYSTEM_CREATE_TIME, system_create_time);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("category.listByApp_idAndSystem_create_timeAndLimit", sqlMap);

        logSql(request_app_id, request_http_id, "table_category", "listByApp_idAndSystem_create_timeAndLimit", sqlPara, request_user_id);

        return new Category().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Category> listByApp_idAndLimit(String app_id, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Category.APP_ID, app_id);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("category.listByApp_idAndLimit", sqlMap);

        logSql(request_app_id, request_http_id, "table_category", "listByApp_idAndLimit", sqlPara, request_user_id);

        return new Category().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Category> listByOrApp_idAndLimit(String app_id, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Category.APP_ID, app_id);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("category.listByOrApp_idAndLimit", sqlMap);

        logSql(request_app_id, request_http_id, "table_category", "listByOrApp_idAndLimit", sqlPara, request_user_id);

        return new Category().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Category> listByOrApp_idAndParent_idAndLimit(String app_id, String parent_id, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Category.APP_ID, app_id);
        sqlMap.put(Category.PARENT_ID, parent_id);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("category.listByOrApp_idAndParent_idAndLimit", sqlMap);

        logSql(request_app_id, request_http_id, "table_category", "listByOrApp_idAndParent_idAndLimit", sqlPara, request_user_id);

        return new Category().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Category> listByOrApp_idAndNotParent_idAndCategory_nameAndCategory_typeAndLimit(String app_id, String parent_id, String category_name, String category_type, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Category.APP_ID, app_id);
        sqlMap.put(Category.PARENT_ID, parent_id);
        sqlMap.put(Category.CATEGORY_NAME, category_name);
        sqlMap.put(Category.CATEGORY_TYPE, category_type);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("category.listByOrApp_idAndNotParent_idAndCategory_nameAndCategory_typeAndLimit", sqlMap);

        logSql(request_app_id, request_http_id, "table_category", "listByOrApp_idAndNotParent_idAndCategory_nameAndCategory_typeAndLimit", sqlPara, request_user_id);

        return new Category().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Category> treeByParent_id(String parent_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Category.PARENT_ID, parent_id);
        SqlPara sqlPara = Db.getSqlPara("category.treeByParent_id", sqlMap);

        logSql(request_app_id, request_http_id, "table_category", "treeByParent_id", sqlPara, request_user_id);

        return new Category().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public Category findByCategory_id(String category_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Category.CATEGORY_ID, category_id);
        SqlPara sqlPara = Db.getSqlPara("category.findByCategory_id", sqlMap);

        logSql(request_app_id, request_http_id, "table_category", "findByCategory_id", sqlPara, request_user_id);

        List<Category> categoryList = new Category().find(sqlPara.getSql(), sqlPara.getPara());
        if (categoryList.size() == 0) {
            return null;
        } else {
            return categoryList.get(0);
        }
    }

    public Boolean save(String category_id, String app_id, String parent_id, String category_name, String category_image, String category_key, String category_value, String category_path, Integer category_sort, String category_type, String system_create_user_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Category.CATEGORY_ID, category_id);
        sqlMap.put(Category.APP_ID, app_id);
        sqlMap.put(Category.PARENT_ID, parent_id);
        sqlMap.put(Category.CATEGORY_NAME, category_name);
        sqlMap.put(Category.CATEGORY_IMAGE, category_image);
        sqlMap.put(Category.CATEGORY_KEY, category_key);
        sqlMap.put(Category.CATEGORY_VALUE, category_value);
        sqlMap.put(Category.CATEGORY_PATH, category_path);
        sqlMap.put(Category.CATEGORY_SORT, category_sort);
        sqlMap.put(Category.CATEGORY_TYPE, category_type);
        sqlMap.put(Category.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(Category.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(Category.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(Category.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Category.SYSTEM_VERSION, 0);
        sqlMap.put(Category.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("category.save", sqlMap);

        logSql(request_app_id, request_http_id, "table_category", "save", sqlPara, request_user_id);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean update(String category_id, String parent_id, String category_name, String category_image, String category_key, String category_value, String category_path, Integer category_sort, String category_type, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Category.CATEGORY_ID, category_id);
        sqlMap.put(Category.PARENT_ID, parent_id);
        sqlMap.put(Category.CATEGORY_NAME, category_name);
        sqlMap.put(Category.CATEGORY_IMAGE, category_image);
        sqlMap.put(Category.CATEGORY_KEY, category_key);
        sqlMap.put(Category.CATEGORY_VALUE, category_value);
        sqlMap.put(Category.CATEGORY_PATH, category_path);
        sqlMap.put(Category.CATEGORY_SORT, category_sort);
        sqlMap.put(Category.CATEGORY_TYPE, category_type);
        sqlMap.put(Category.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(Category.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Category.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("category.update", sqlMap);

        logSql(request_app_id, request_http_id, "table_category", "update", sqlPara, request_user_id);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByCategory_idAndSystem_version(String category_id, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Category.CATEGORY_ID, category_id);
        sqlMap.put(Category.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(Category.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Category.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("category.deleteByCategory_idAndSystem_version", sqlMap);

        logSql(request_app_id, request_http_id, "table_category", "deleteByCategory_idAndSystem_version", sqlPara, request_user_id);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByParent_id(String parent_id, String system_update_user_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Category.PARENT_ID, parent_id);
        sqlMap.put(Category.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(Category.SYSTEM_UPDATE_TIME, new Date());
        SqlPara sqlPara = Db.getSqlPara("category.deleteByParent_id", sqlMap);

        logSql(request_app_id, request_http_id, "table_category", "deleteByParent_id", sqlPara, request_user_id);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}