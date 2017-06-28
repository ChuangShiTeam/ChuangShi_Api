package com.nowui.chuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.FeijiuRecommendProduct;

import java.util.Date;
import java.util.List;

public class FeijiuRecommendProductDao extends Dao {

    public Integer countByApp_id(String app_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(FeijiuRecommendProduct.APP_ID, app_id);
        SqlPara sqlPara = Db.getSqlPara("feijiu_recommend_product.countByApp_id", sqlMap);

        logSql(request_app_id, request_http_id, "table_feijiu_recommend_product", "countByApp_id", sqlPara, request_user_id);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByOrApp_id(String app_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(FeijiuRecommendProduct.APP_ID, app_id);
        SqlPara sqlPara = Db.getSqlPara("feijiu_recommend_product.countByOrApp_id", sqlMap);

        logSql(request_app_id, request_http_id, "table_feijiu_recommend_product", "countByOrApp_id", sqlPara, request_user_id);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<FeijiuRecommendProduct> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(FeijiuRecommendProduct.APP_ID, app_id);
        sqlMap.put(FeijiuRecommendProduct.SYSTEM_CREATE_TIME, system_create_time);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("feijiu_recommend_product.listByApp_idAndSystem_create_timeAndLimit", sqlMap);

        logSql(request_app_id, request_http_id, "table_feijiu_recommend_product", "listByApp_idAndSystem_create_timeAndLimit", sqlPara, request_user_id);

        return new FeijiuRecommendProduct().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<FeijiuRecommendProduct> listByApp_id(String app_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(FeijiuRecommendProduct.APP_ID, app_id);
        SqlPara sqlPara = Db.getSqlPara("feijiu_recommend_product.listByApp_id", sqlMap);

        logSql(request_app_id, request_http_id, "table_feijiu_recommend_product", "listByApp_id", sqlPara, request_user_id);

        return new FeijiuRecommendProduct().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<FeijiuRecommendProduct> listByApp_idAndLimit(String app_id, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(FeijiuRecommendProduct.APP_ID, app_id);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("feijiu_recommend_product.listByApp_idAndLimit", sqlMap);

        logSql(request_app_id, request_http_id, "table_feijiu_recommend_product", "listByApp_idAndLimit", sqlPara, request_user_id);

        return new FeijiuRecommendProduct().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<FeijiuRecommendProduct> listByOrApp_idAndLimit(String app_id, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(FeijiuRecommendProduct.APP_ID, app_id);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("feijiu_recommend_product.listByOrApp_idAndLimit", sqlMap);

        logSql(request_app_id, request_http_id, "table_feijiu_recommend_product", "listByOrApp_idAndLimit", sqlPara, request_user_id);

        return new FeijiuRecommendProduct().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public FeijiuRecommendProduct findByProduct_id(String product_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(FeijiuRecommendProduct.PRODUCT_ID, product_id);
        SqlPara sqlPara = Db.getSqlPara("feijiu_recommend_product.findByProduct_id", sqlMap);

        logSql(request_app_id, request_http_id, "table_feijiu_recommend_product", "findByProduct_id", sqlPara, request_user_id);

        List<FeijiuRecommendProduct> feijiu_recommend_productList = new FeijiuRecommendProduct().find(sqlPara.getSql(), sqlPara.getPara());
        if (feijiu_recommend_productList.size() == 0) {
            return null;
        } else {
            return feijiu_recommend_productList.get(0);
        }
    }

    public Boolean save(String product_id, String app_id, String product_name, String product_image, String product_content, String system_create_user_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(FeijiuRecommendProduct.PRODUCT_ID, product_id);
        sqlMap.put(FeijiuRecommendProduct.APP_ID, app_id);
        sqlMap.put(FeijiuRecommendProduct.PRODUCT_NAME, product_name);
        sqlMap.put(FeijiuRecommendProduct.PRODUCT_IMAGE, product_image);
        sqlMap.put(FeijiuRecommendProduct.PRODUCT_CONTENT, product_content);
        sqlMap.put(FeijiuRecommendProduct.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(FeijiuRecommendProduct.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(FeijiuRecommendProduct.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(FeijiuRecommendProduct.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(FeijiuRecommendProduct.SYSTEM_VERSION, 0);
        sqlMap.put(FeijiuRecommendProduct.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("feijiu_recommend_product.save", sqlMap);

        logSql(request_app_id, request_http_id, "table_feijiu_recommend_product", "save", sqlPara, request_user_id);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean update(String product_id, String product_name, String product_image, String product_content, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(FeijiuRecommendProduct.PRODUCT_ID, product_id);
        sqlMap.put(FeijiuRecommendProduct.PRODUCT_NAME, product_name);
        sqlMap.put(FeijiuRecommendProduct.PRODUCT_IMAGE, product_image);
        sqlMap.put(FeijiuRecommendProduct.PRODUCT_CONTENT, product_content);
        sqlMap.put(FeijiuRecommendProduct.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(FeijiuRecommendProduct.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(FeijiuRecommendProduct.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("feijiu_recommend_product.update", sqlMap);

        logSql(request_app_id, request_http_id, "table_feijiu_recommend_product", "update", sqlPara, request_user_id);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByProduct_idAndSystem_version(String product_id, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(FeijiuRecommendProduct.PRODUCT_ID, product_id);
        sqlMap.put(FeijiuRecommendProduct.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(FeijiuRecommendProduct.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(FeijiuRecommendProduct.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("feijiu_recommend_product.deleteByProduct_idAndSystem_version", sqlMap);

        logSql(request_app_id, request_http_id, "table_feijiu_recommend_product", "deleteBy", sqlPara, request_user_id);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}