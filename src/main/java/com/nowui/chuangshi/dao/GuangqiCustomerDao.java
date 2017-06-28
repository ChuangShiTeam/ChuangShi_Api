package com.nowui.chuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.GuangqiCustomer;

import java.util.Date;
import java.util.List;

public class GuangqiCustomerDao extends Dao {

    public Integer countByApp_id(String app_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(GuangqiCustomer.APP_ID, app_id);
        SqlPara sqlPara = Db.getSqlPara("guangqi_customer.countByApp_id", sqlMap);

        logSql(request_app_id, request_http_id, "table_guangqi_customer", "countByApp_id", sqlPara, request_user_id);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByOrApp_id(String app_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(GuangqiCustomer.APP_ID, app_id);
        SqlPara sqlPara = Db.getSqlPara("guangqi_customer.countByOrApp_id", sqlMap);

        logSql(request_app_id, request_http_id, "table_guangqi_customer", "countByOrApp_id", sqlPara, request_user_id);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByCustomer_phone(String customer_phone, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(GuangqiCustomer.CUSTOMER_PHONE, customer_phone);
        SqlPara sqlPara = Db.getSqlPara("guangqi_customer.countByCustomer_phone", sqlMap);

        logSql(request_app_id, request_http_id, "table_guangqi_customer", "countByCustomer_phone", sqlPara, request_user_id);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<GuangqiCustomer> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(GuangqiCustomer.APP_ID, app_id);
        sqlMap.put(GuangqiCustomer.SYSTEM_CREATE_TIME, system_create_time);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("guangqi_customer.listByApp_idAndSystem_create_timeAndLimit", sqlMap);

        logSql(request_app_id, request_http_id, "table_guangqi_customer", "listByApp_idAndSystem_create_timeAndLimit", sqlPara, request_user_id);

        return new GuangqiCustomer().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<GuangqiCustomer> listByApp_idAndCustomer_nameAndLimit(String app_id, String customer_name, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(GuangqiCustomer.APP_ID, app_id);
        sqlMap.put(GuangqiCustomer.CUSTOMER_NAME, customer_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("guangqi_customer.listByApp_idAndCustomer_nameAndLimit", sqlMap);

        logSql(request_app_id, request_http_id, "table_guangqi_customer", "listByApp_idAndCustomer_nameAndLimit", sqlPara, request_user_id);

        return new GuangqiCustomer().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<GuangqiCustomer> listByOrApp_idAndLimit(String app_id, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(GuangqiCustomer.APP_ID, app_id);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("guangqi_customer.listByOrApp_idAndLimit", sqlMap);

        logSql(request_app_id, request_http_id, "table_guangqi_customer", "listByOrApp_idAndLimit", sqlPara, request_user_id);

        return new GuangqiCustomer().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public GuangqiCustomer findByCustomer_id(String customer_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(GuangqiCustomer.CUSTOMER_ID, customer_id);
        SqlPara sqlPara = Db.getSqlPara("guangqi_customer.findByCustomer_id", sqlMap);

        logSql(request_app_id, request_http_id, "table_guangqi_customer", "findByCustomer_id", sqlPara, request_user_id);

        List<GuangqiCustomer> guangqi_customerList = new GuangqiCustomer().find(sqlPara.getSql(), sqlPara.getPara());
        if (guangqi_customerList.size() == 0) {
            return null;
        } else {
            return guangqi_customerList.get(0);
        }
    }

    public Boolean save(String customer_id, String app_id, String customer_name, String customer_phone, String customer_province, String customer_city, String costomer_dealer, String system_create_user_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(GuangqiCustomer.CUSTOMER_ID, customer_id);
        sqlMap.put(GuangqiCustomer.APP_ID, app_id);
        sqlMap.put(GuangqiCustomer.CUSTOMER_NAME, customer_name);
        sqlMap.put(GuangqiCustomer.CUSTOMER_PHONE, customer_phone);
        sqlMap.put(GuangqiCustomer.CUSTOMER_PROVINCE, customer_province);
        sqlMap.put(GuangqiCustomer.CUSTOMER_CITY, customer_city);
        sqlMap.put(GuangqiCustomer.COSTOMER_DEALER, costomer_dealer);
        sqlMap.put(GuangqiCustomer.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(GuangqiCustomer.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(GuangqiCustomer.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(GuangqiCustomer.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(GuangqiCustomer.SYSTEM_VERSION, 0);
        sqlMap.put(GuangqiCustomer.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("guangqi_customer.save", sqlMap);

        logSql(request_app_id, request_http_id, "table_guangqi_customer", "save", sqlPara, request_user_id);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean update(String customer_id, String customer_name, String customer_phone, String customer_province, String customer_city, String costomer_dealer, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(GuangqiCustomer.CUSTOMER_ID, customer_id);
        sqlMap.put(GuangqiCustomer.CUSTOMER_NAME, customer_name);
        sqlMap.put(GuangqiCustomer.CUSTOMER_PHONE, customer_phone);
        sqlMap.put(GuangqiCustomer.CUSTOMER_PROVINCE, customer_province);
        sqlMap.put(GuangqiCustomer.CUSTOMER_CITY, customer_city);
        sqlMap.put(GuangqiCustomer.COSTOMER_DEALER, costomer_dealer);
        sqlMap.put(GuangqiCustomer.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(GuangqiCustomer.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(GuangqiCustomer.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("guangqi_customer.update", sqlMap);

        logSql(request_app_id, request_http_id, "table_guangqi_customer", "update", sqlPara, request_user_id);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByCustomer_idAndSystem_version(String customer_id, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(GuangqiCustomer.CUSTOMER_ID, customer_id);
        sqlMap.put(GuangqiCustomer.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(GuangqiCustomer.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(GuangqiCustomer.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("guangqi_customer.deleteByCustomer_idAndSystem_version", sqlMap);

        logSql(request_app_id, request_http_id, "table_guangqi_customer", "deleteByCustomer_idAndSystem_version", sqlPara, request_user_id);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}