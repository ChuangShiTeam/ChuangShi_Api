package com.nowui.chuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.Customer;

import java.util.Date;
import java.util.List;

public class CustomerDao extends Dao {

    public Integer countByApp_idOrLikeCustomer_name(String app_id, String customer_name, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Customer.APP_ID, app_id);
        sqlMap.put(Customer.CUSTOMER_NAME, customer_name);
        SqlPara sqlPara = Db.getSqlPara("customer.countByApp_idOrLikeCustomer_name", sqlMap);

        logSql(request_app_id, request_http_id, "table_customer", "countByApp_idOrLikeCustomer_name", sqlPara, request_user_id);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByOrApp_idOrLikeCustomer_name(String app_id, String customer_name, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Customer.APP_ID, app_id);
        sqlMap.put(Customer.CUSTOMER_NAME, customer_name);
        SqlPara sqlPara = Db.getSqlPara("customer.countByOrApp_idOrLikeCustomer_name", sqlMap);

        logSql(request_app_id, request_http_id, "table_customer", "countByOrApp_idOrLikeCustomer_name", sqlPara, request_user_id);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<Customer> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Customer.APP_ID, app_id);
        sqlMap.put(Customer.SYSTEM_CREATE_TIME, system_create_time);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("customer.listByApp_idAndSystem_create_timeAndLimit", sqlMap);

        logSql(request_app_id, request_http_id, "table_customer", "listByApp_idAndSystem_create_timeAndLimit", sqlPara, request_user_id);

        return new Customer().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Customer> listByApp_idOrLikeCustomer_nameAndLimit(String app_id, String customer_name, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Customer.APP_ID, app_id);
        sqlMap.put(Customer.CUSTOMER_NAME, customer_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("customer.listByApp_idOrLikeCustomer_nameAndLimit", sqlMap);

        logSql(request_app_id, request_http_id, "table_customer", "listByApp_idOrLikeCustomer_nameAndLimit", sqlPara, request_user_id);

        return new Customer().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Customer> listByOrApp_idOrLikeCustomer_nameAndLimit(String app_id, String customer_name, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Customer.APP_ID, app_id);
        sqlMap.put(Customer.CUSTOMER_NAME, customer_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("customer.listByOrApp_idOrLikeCustomer_nameAndLimit", sqlMap);

        logSql(request_app_id, request_http_id, "table_customer", "listByOrApp_idOrLikeCustomer_nameAndLimit", sqlPara, request_user_id);

        return new Customer().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public Customer findByCustomer_id(String customer_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Customer.CUSTOMER_ID, customer_id);
        SqlPara sqlPara = Db.getSqlPara("customer.findByCustomer_id", sqlMap);

        logSql(request_app_id, request_http_id, "table_customer", "findByCustomer_id", sqlPara, request_user_id);

        List<Customer> customerList = new Customer().find(sqlPara.getSql(), sqlPara.getPara());
        if (customerList.size() == 0) {
            return null;
        } else {
            return customerList.get(0);
        }
    }

    public Boolean save(String customer_id, String app_id, String customer_name, String customer_phone, String customer_birthday, String customer_city, String customer_sex, String customer_id_card, String system_create_user_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Customer.CUSTOMER_ID, customer_id);
        sqlMap.put(Customer.APP_ID, app_id);
        sqlMap.put(Customer.CUSTOMER_NAME, customer_name);
        sqlMap.put(Customer.CUSTOMER_PHONE, customer_phone);
        sqlMap.put(Customer.CUSTOMER_BIRTHDAY, customer_birthday);
        sqlMap.put(Customer.CUSTOMER_CITY, customer_city);
        sqlMap.put(Customer.CUSTOMER_SEX, customer_sex);
        sqlMap.put(Customer.CUSTOMER_ID_CARD, customer_id_card);
        sqlMap.put(Customer.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(Customer.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(Customer.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(Customer.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Customer.SYSTEM_VERSION, 0);
        sqlMap.put(Customer.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("customer.save", sqlMap);

        logSql(request_app_id, request_http_id, "table_customer", "save", sqlPara, request_user_id);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean update(String customer_id, String customer_name, String customer_phone, String customer_birthday, String customer_city, String customer_sex, String customer_id_card, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Customer.CUSTOMER_ID, customer_id);
        sqlMap.put(Customer.CUSTOMER_NAME, customer_name);
        sqlMap.put(Customer.CUSTOMER_PHONE, customer_phone);
        sqlMap.put(Customer.CUSTOMER_BIRTHDAY, customer_birthday);
        sqlMap.put(Customer.CUSTOMER_CITY, customer_city);
        sqlMap.put(Customer.CUSTOMER_SEX, customer_sex);
        sqlMap.put(Customer.CUSTOMER_ID_CARD, customer_id_card);
        sqlMap.put(Customer.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(Customer.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Customer.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("customer.update", sqlMap);

        logSql(request_app_id, request_http_id, "table_customer", "update", sqlPara, request_user_id);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByCustomer_idAndSystem_version(String customer_id, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Customer.CUSTOMER_ID, customer_id);
        sqlMap.put(Customer.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(Customer.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Customer.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("customer.deleteByCustomer_idAndSystem_version", sqlMap);

        logSql(request_app_id, request_http_id, "table_customer", "deleteByCustomer_idAndSystem_version", sqlPara, request_user_id);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}