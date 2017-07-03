package com.nowui.chuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.ProductBrand;

import java.util.Date;
import java.util.List;

public class ProductBrandDao extends Dao {

    public Integer countByApp_idOrLikeProduct_brand_name(String app_id, String product_brand_name, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(ProductBrand.APP_ID, app_id);
        sqlMap.put(ProductBrand.PRODUCT_BRAND_NAME, product_brand_name);
        SqlPara sqlPara = Db.getSqlPara("product_brand.countByApp_idOrLikeProduct_brand_name", sqlMap);

        logSql(request_app_id, request_http_id, "table_product_brand", "countByApp_idOrLikeProduct_brand_name", sqlPara, request_user_id);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByOrApp_idOrLikeProduct_brand_name(String app_id, String product_brand_name, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(ProductBrand.APP_ID, app_id);
        sqlMap.put(ProductBrand.PRODUCT_BRAND_NAME, product_brand_name);
        SqlPara sqlPara = Db.getSqlPara("product_brand.countByOrApp_idOrLikeProduct_brand_name", sqlMap);

        logSql(request_app_id, request_http_id, "table_product_brand", "countByOrApp_idOrLikeProduct_brand_name", sqlPara, request_user_id);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<ProductBrand> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(ProductBrand.APP_ID, app_id);
        sqlMap.put(ProductBrand.SYSTEM_CREATE_TIME, system_create_time);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("product_brand.listByApp_idAndSystem_create_timeAndLimit", sqlMap);

        logSql(request_app_id, request_http_id, "table_product_brand", "listByApp_idAndSystem_create_timeAndLimit", sqlPara, request_user_id);

        return new ProductBrand().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<ProductBrand> listByApp_idOrLikeProduct_brand_nameAndLimit(String app_id, String product_brand_name, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(ProductBrand.APP_ID, app_id);
        sqlMap.put(ProductBrand.PRODUCT_BRAND_NAME, product_brand_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("product_brand.listByApp_idOrLikeProduct_brand_nameAndLimit", sqlMap);

        logSql(request_app_id, request_http_id, "table_product_brand", "listByApp_idOrLikeProduct_brand_nameAndLimit", sqlPara, request_user_id);

        return new ProductBrand().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<ProductBrand> listByOrApp_idOrLikeProduct_brand_nameAndLimit(String app_id, String product_brand_name, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(ProductBrand.APP_ID, app_id);
        sqlMap.put(ProductBrand.PRODUCT_BRAND_NAME, product_brand_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("product_brand.listByOrApp_idOrLikeProduct_brand_nameAndLimit", sqlMap);

        logSql(request_app_id, request_http_id, "table_product_brand", "listByOrApp_idOrLikeProduct_brand_nameAndLimit", sqlPara, request_user_id);

        return new ProductBrand().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public ProductBrand findByProduct_brand_id(String product_brand_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(ProductBrand.PRODUCT_BRAND_ID, product_brand_id);
        SqlPara sqlPara = Db.getSqlPara("product_brand.findByProduct_brand_id", sqlMap);

        logSql(request_app_id, request_http_id, "table_product_brand", "findByProduct_brand_id", sqlPara, request_user_id);

        List<ProductBrand> product_brandList = new ProductBrand().find(sqlPara.getSql(), sqlPara.getPara());
        if (product_brandList.size() == 0) {
            return null;
        } else {
            return product_brandList.get(0);
        }
    }

    public Boolean save(String product_brand_id, String app_id, String product_brand_name, String product_brand_image, String product_brand_content, String system_create_user_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(ProductBrand.PRODUCT_BRAND_ID, product_brand_id);
        sqlMap.put(ProductBrand.APP_ID, app_id);
        sqlMap.put(ProductBrand.PRODUCT_BRAND_NAME, product_brand_name);
        sqlMap.put(ProductBrand.PRODUCT_BRAND_IMAGE, product_brand_image);
        sqlMap.put(ProductBrand.PRODUCT_BRAND_CONTENT, product_brand_content);
        sqlMap.put(ProductBrand.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(ProductBrand.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(ProductBrand.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(ProductBrand.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(ProductBrand.SYSTEM_VERSION, 0);
        sqlMap.put(ProductBrand.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("product_brand.save", sqlMap);

        logSql(request_app_id, request_http_id, "table_product_brand", "save", sqlPara, request_user_id);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean update(String product_brand_id, String product_brand_name, String product_brand_image, String product_brand_content, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(ProductBrand.PRODUCT_BRAND_ID, product_brand_id);
        sqlMap.put(ProductBrand.PRODUCT_BRAND_NAME, product_brand_name);
        sqlMap.put(ProductBrand.PRODUCT_BRAND_IMAGE, product_brand_image);
        sqlMap.put(ProductBrand.PRODUCT_BRAND_CONTENT, product_brand_content);
        sqlMap.put(ProductBrand.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(ProductBrand.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(ProductBrand.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("product_brand.update", sqlMap);

        logSql(request_app_id, request_http_id, "table_product_brand", "update", sqlPara, request_user_id);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByProduct_brand_idAndSystem_version(String product_brand_id, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(ProductBrand.PRODUCT_BRAND_ID, product_brand_id);
        sqlMap.put(ProductBrand.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(ProductBrand.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(ProductBrand.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("product_brand.deleteByProduct_brand_idAndSystem_version", sqlMap);

        logSql(request_app_id, request_http_id, "table_product_brand", "deleteByProduct_brand_idAndSystem_version", sqlPara, request_user_id);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}