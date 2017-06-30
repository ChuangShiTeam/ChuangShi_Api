package com.nowui.chuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.Brand;

import java.util.Date;
import java.util.List;

public class BrandDao extends Dao {

    public Integer countByApp_idOrLikeBrand_name(String app_id, String brand_name, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Brand.APP_ID, app_id);
        sqlMap.put(Brand.BRAND_NAME, brand_name);
        SqlPara sqlPara = Db.getSqlPara("brand.countByApp_idOrLikeBrand_name", sqlMap);

        logSql(request_app_id, request_http_id, "table_brand", "countByApp_idOrLikeBrand_name", sqlPara, request_user_id);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByOrApp_idOrLikeBrand_name(String app_id, String brand_name, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Brand.APP_ID, app_id);
        sqlMap.put(Brand.BRAND_NAME, brand_name);
        SqlPara sqlPara = Db.getSqlPara("brand.countByOrApp_idOrLikeBrand_name", sqlMap);

        logSql(request_app_id, request_http_id, "table_brand", "countByOrApp_idOrLikeBrand_name", sqlPara, request_user_id);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<Brand> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Brand.APP_ID, app_id);
        sqlMap.put(Brand.SYSTEM_CREATE_TIME, system_create_time);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("brand.listByApp_idAndSystem_create_timeAndLimit", sqlMap);

        logSql(request_app_id, request_http_id, "table_brand", "listByApp_idAndSystem_create_timeAndLimit", sqlPara, request_user_id);

        return new Brand().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Brand> listByApp_idOrLikeBrand_nameAndLimit(String app_id, String brand_name, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Brand.APP_ID, app_id);
        sqlMap.put(Brand.BRAND_NAME, brand_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("brand.listByApp_idOrLikeBrand_nameAndLimit", sqlMap);

        logSql(request_app_id, request_http_id, "table_brand", "listByApp_idOrLikeBrand_nameAndLimit", sqlPara, request_user_id);

        return new Brand().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Brand> listByOrApp_idOrLikeBrand_nameAndLimit(String app_id, String brand_name, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Brand.APP_ID, app_id);
        sqlMap.put(Brand.BRAND_NAME, brand_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("brand.listByOrApp_idOrLikeBrand_nameAndLimit", sqlMap);

        logSql(request_app_id, request_http_id, "table_brand", "listByOrApp_idOrLikeBrand_nameAndLimit", sqlPara, request_user_id);

        return new Brand().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public Brand findByBrand_id(String brand_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Brand.BRAND_ID, brand_id);
        SqlPara sqlPara = Db.getSqlPara("brand.findByBrand_id", sqlMap);

        logSql(request_app_id, request_http_id, "table_brand", "findByBrand_id", sqlPara, request_user_id);

        List<Brand> brandList = new Brand().find(sqlPara.getSql(), sqlPara.getPara());
        if (brandList.size() == 0) {
            return null;
        } else {
            return brandList.get(0);
        }
    }

    public Boolean save(String brand_id, String app_id, String category_id, String brand_name, String brand_image, String brand_content, String system_create_user_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Brand.BRAND_ID, brand_id);
        sqlMap.put(Brand.APP_ID, app_id);
        sqlMap.put(Brand.CATEGORY_ID, category_id);
        sqlMap.put(Brand.BRAND_NAME, brand_name);
        sqlMap.put(Brand.BRAND_IMAGE, brand_image);
        sqlMap.put(Brand.BRAND_CONTENT, brand_content);
        sqlMap.put(Brand.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(Brand.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(Brand.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(Brand.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Brand.SYSTEM_VERSION, 0);
        sqlMap.put(Brand.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("brand.save", sqlMap);

        logSql(request_app_id, request_http_id, "table_brand", "save", sqlPara, request_user_id);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean update(String brand_id, String category_id, String brand_name, String brand_image, String brand_content, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Brand.BRAND_ID, brand_id);
        sqlMap.put(Brand.CATEGORY_ID, category_id);
        sqlMap.put(Brand.BRAND_NAME, brand_name);
        sqlMap.put(Brand.BRAND_IMAGE, brand_image);
        sqlMap.put(Brand.BRAND_CONTENT, brand_content);
        sqlMap.put(Brand.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(Brand.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Brand.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("brand.update", sqlMap);

        logSql(request_app_id, request_http_id, "table_brand", "update", sqlPara, request_user_id);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByBrand_idAndSystem_version(String brand_id, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Brand.BRAND_ID, brand_id);
        sqlMap.put(Brand.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(Brand.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Brand.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("brand.deleteByBrand_idAndSystem_version", sqlMap);

        logSql(request_app_id, request_http_id, "table_brand", "deleteBy", sqlPara, request_user_id);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}