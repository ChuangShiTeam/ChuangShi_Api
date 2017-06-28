package com.nowui.chuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.FeijiuRecommendCustomer;

import java.util.Date;
import java.util.List;

public class FeijiuRecommendCustomerDao extends Dao {

    public Integer countByApp_idAndCustomer_name(String app_id, String customer_name, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(FeijiuRecommendCustomer.APP_ID, app_id);
        sqlMap.put(FeijiuRecommendCustomer.CUSTOMER_NAME, customer_name);
        SqlPara sqlPara = Db.getSqlPara("feijiu_recommend_customer.countByApp_idAndCustomer_name", sqlMap);

        logSql(request_app_id, request_http_id, "table_feijiu_recommend_customer", "countByApp_idAndCustomer_name", sqlPara, request_user_id);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByOrApp_id(String app_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(FeijiuRecommendCustomer.APP_ID, app_id);
        SqlPara sqlPara = Db.getSqlPara("feijiu_recommend_customer.countByOrApp_id", sqlMap);

        logSql(request_app_id, request_http_id, "table_feijiu_recommend_customer", "countByOrApp_id", sqlPara, request_user_id);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<FeijiuRecommendCustomer> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(FeijiuRecommendCustomer.APP_ID, app_id);
        sqlMap.put(FeijiuRecommendCustomer.SYSTEM_CREATE_TIME, system_create_time);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("feijiu_recommend_customer.listByApp_idAndSystem_create_timeAndLimit", sqlMap);

        logSql(request_app_id, request_http_id, "table_feijiu_recommend_customer", "listByApp_idAndSystem_create_timeAndLimit", sqlPara, request_user_id);

        return new FeijiuRecommendCustomer().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<FeijiuRecommendCustomer> listByApp_id(String app_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(FeijiuRecommendCustomer.APP_ID, app_id);
        SqlPara sqlPara = Db.getSqlPara("feijiu_recommend_customer.listByApp_id", sqlMap);

        logSql(request_app_id, request_http_id, "table_feijiu_recommend_customer", "listByApp_id", sqlPara, request_user_id);

        return new FeijiuRecommendCustomer().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<FeijiuRecommendCustomer> listByApp_idAndCustomer_nameAndLimit(String app_id, String customer_name, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(FeijiuRecommendCustomer.APP_ID, app_id);
        sqlMap.put(FeijiuRecommendCustomer.CUSTOMER_NAME, customer_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("feijiu_recommend_customer.listByApp_idAndCustomer_nameAndLimit", sqlMap);

        logSql(request_app_id, request_http_id, "table_feijiu_recommend_customer", "listByApp_idAndCustomer_nameAndLimit", sqlPara, request_user_id);

        return new FeijiuRecommendCustomer().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<FeijiuRecommendCustomer> listByOrApp_idAndLimit(String app_id, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(FeijiuRecommendCustomer.APP_ID, app_id);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("feijiu_recommend_customer.listByOrApp_idAndLimit", sqlMap);

        logSql(request_app_id, request_http_id, "table_feijiu_recommend_customer", "listByOrApp_idAndLimit", sqlPara, request_user_id);

        return new FeijiuRecommendCustomer().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public FeijiuRecommendCustomer findByCustomer_id(String customer_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(FeijiuRecommendCustomer.CUSTOMER_ID, customer_id);
        SqlPara sqlPara = Db.getSqlPara("feijiu_recommend_customer.findByCustomer_id", sqlMap);

        logSql(request_app_id, request_http_id, "table_feijiu_recommend_customer", "findByCustomer_id", sqlPara, request_user_id);

        List<FeijiuRecommendCustomer> feijiu_recommend_customerList = new FeijiuRecommendCustomer().find(sqlPara.getSql(), sqlPara.getPara());
        if (feijiu_recommend_customerList.size() == 0) {
            return null;
        } else {
            return feijiu_recommend_customerList.get(0);
        }
    }

    public Boolean save(String customer_id, String app_id, String customer_name, String customer_phone, String customer_city, String system_create_user_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(FeijiuRecommendCustomer.CUSTOMER_ID, customer_id);
        sqlMap.put(FeijiuRecommendCustomer.APP_ID, app_id);
        sqlMap.put(FeijiuRecommendCustomer.CUSTOMER_NAME, customer_name);
        sqlMap.put(FeijiuRecommendCustomer.CUSTOMER_PHONE, customer_phone);
        sqlMap.put(FeijiuRecommendCustomer.CUSTOMER_CITY, customer_city);
        sqlMap.put(FeijiuRecommendCustomer.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(FeijiuRecommendCustomer.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(FeijiuRecommendCustomer.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(FeijiuRecommendCustomer.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(FeijiuRecommendCustomer.SYSTEM_VERSION, 0);
        sqlMap.put(FeijiuRecommendCustomer.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("feijiu_recommend_customer.save", sqlMap);

        logSql(request_app_id, request_http_id, "table_feijiu_recommend_customer", "save", sqlPara, request_user_id);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean update(String customer_id, String customer_name, String customer_phone, String customer_city, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(FeijiuRecommendCustomer.CUSTOMER_ID, customer_id);
        sqlMap.put(FeijiuRecommendCustomer.CUSTOMER_NAME, customer_name);
        sqlMap.put(FeijiuRecommendCustomer.CUSTOMER_PHONE, customer_phone);
        sqlMap.put(FeijiuRecommendCustomer.CUSTOMER_CITY, customer_city);
        sqlMap.put(FeijiuRecommendCustomer.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(FeijiuRecommendCustomer.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(FeijiuRecommendCustomer.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("feijiu_recommend_customer.update", sqlMap);

        logSql(request_app_id, request_http_id, "table_feijiu_recommend_customer", "update", sqlPara, request_user_id);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByCustomer_idAndSystem_version(String customer_id, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(FeijiuRecommendCustomer.CUSTOMER_ID, customer_id);
        sqlMap.put(FeijiuRecommendCustomer.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(FeijiuRecommendCustomer.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(FeijiuRecommendCustomer.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("feijiu_recommend_customer.deleteByCustomer_idAndSystem_version", sqlMap);

        logSql(request_app_id, request_http_id, "table_feijiu_recommend_customer", "deleteBy", sqlPara, request_user_id);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}