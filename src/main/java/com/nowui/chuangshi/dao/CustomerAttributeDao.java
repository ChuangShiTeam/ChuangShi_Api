package com.nowui.chuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.CustomerAttribute;

import java.util.Date;
import java.util.List;

public class CustomerAttributeDao extends Dao {

    public Integer countByApp_idOrLikeCustomer_attribute_name(String app_id, String customer_attribute_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(CustomerAttribute.APP_ID, app_id);
        sqlMap.put(CustomerAttribute.CUSTOMER_ATTRIBUTE_NAME, customer_attribute_name);
        SqlPara sqlPara = Db.getSqlPara("customer_attribute.countByApp_idOrLikeCustomer_attribute_name", sqlMap);

        logSql("customer_attribute", "countByApp_idOrLikeCustomer_attribute_name", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByOrApp_idOrLikeCustomer_attribute_name(String app_id, String customer_attribute_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(CustomerAttribute.APP_ID, app_id);
        sqlMap.put(CustomerAttribute.CUSTOMER_ATTRIBUTE_NAME, customer_attribute_name);
        SqlPara sqlPara = Db.getSqlPara("customer_attribute.countByOrApp_idOrLikeCustomer_attribute_name", sqlMap);

        logSql("customer_attribute", "countByOrApp_idOrLikeCustomer_attribute_name", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<CustomerAttribute> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(CustomerAttribute.APP_ID, app_id);
        sqlMap.put(CustomerAttribute.SYSTEM_CREATE_TIME, system_create_time);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("customer_attribute.listByApp_idAndSystem_create_timeAndLimit", sqlMap);

        logSql("customer_attribute", "listByApp_idAndSystem_create_timeAndLimit", sqlPara);

        return new CustomerAttribute().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<CustomerAttribute> listByApp_idOrLikeCustomer_attribute_nameAndLimit(String app_id, String customer_attribute_name, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(CustomerAttribute.APP_ID, app_id);
        sqlMap.put(CustomerAttribute.CUSTOMER_ATTRIBUTE_NAME, customer_attribute_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("customer_attribute.listByApp_idOrLikeCustomer_attribute_nameAndLimit", sqlMap);

        logSql("customer_attribute", "listByApp_idOrLikeCustomer_attribute_nameAndLimit", sqlPara);

        return new CustomerAttribute().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<CustomerAttribute> listByOrApp_idOrLikeCustomer_attribute_nameAndLimit(String app_id, String customer_attribute_name, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(CustomerAttribute.APP_ID, app_id);
        sqlMap.put(CustomerAttribute.CUSTOMER_ATTRIBUTE_NAME, customer_attribute_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("customer_attribute.listByOrApp_idOrLikeCustomer_attribute_nameAndLimit", sqlMap);

        logSql("customer_attribute", "listByOrApp_idOrLikeCustomer_attribute_nameAndLimit", sqlPara);

        return new CustomerAttribute().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public CustomerAttribute findByCustomer_attribute_id(String customer_attribute_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(CustomerAttribute.CUSTOMER_ATTRIBUTE_ID, customer_attribute_id);
        SqlPara sqlPara = Db.getSqlPara("customer_attribute.findByCustomer_attribute_id", sqlMap);

        logSql("customer_attribute", "findByCustomer_attribute_id", sqlPara);

        List<CustomerAttribute> customer_attributeList = new CustomerAttribute().find(sqlPara.getSql(), sqlPara.getPara());
        if (customer_attributeList.size() == 0) {
            return null;
        } else {
            return customer_attributeList.get(0);
        }
    }

    public Boolean save(String customer_attribute_id, String app_id, String customer_attribute_name, String customer_attribute_key, String customer_attribute_input_type, String customer_attribute_data_type, String customer_attribute_default_value, Integer customer_attribute_sort, String system_create_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(CustomerAttribute.CUSTOMER_ATTRIBUTE_ID, customer_attribute_id);
        sqlMap.put(CustomerAttribute.APP_ID, app_id);
        sqlMap.put(CustomerAttribute.CUSTOMER_ATTRIBUTE_NAME, customer_attribute_name);
        sqlMap.put(CustomerAttribute.CUSTOMER_ATTRIBUTE_KEY, customer_attribute_key);
        sqlMap.put(CustomerAttribute.CUSTOMER_ATTRIBUTE_INPUT_TYPE, customer_attribute_input_type);
        sqlMap.put(CustomerAttribute.CUSTOMER_ATTRIBUTE_DATA_TYPE, customer_attribute_data_type);
        sqlMap.put(CustomerAttribute.CUSTOMER_ATTRIBUTE_DEFAULT_VALUE, customer_attribute_default_value);
        sqlMap.put(CustomerAttribute.CUSTOMER_ATTRIBUTE_SORT, customer_attribute_sort);
        sqlMap.put(CustomerAttribute.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(CustomerAttribute.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(CustomerAttribute.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(CustomerAttribute.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(CustomerAttribute.SYSTEM_VERSION, 0);
        sqlMap.put(CustomerAttribute.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("customer_attribute.save", sqlMap);

        logSql("customer_attribute", "save", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean update(String customer_attribute_id, String customer_attribute_name, String customer_attribute_key, String customer_attribute_input_type, String customer_attribute_data_type, String customer_attribute_default_value, Integer customer_attribute_sort, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(CustomerAttribute.CUSTOMER_ATTRIBUTE_ID, customer_attribute_id);
        sqlMap.put(CustomerAttribute.CUSTOMER_ATTRIBUTE_NAME, customer_attribute_name);
        sqlMap.put(CustomerAttribute.CUSTOMER_ATTRIBUTE_KEY, customer_attribute_key);
        sqlMap.put(CustomerAttribute.CUSTOMER_ATTRIBUTE_INPUT_TYPE, customer_attribute_input_type);
        sqlMap.put(CustomerAttribute.CUSTOMER_ATTRIBUTE_DATA_TYPE, customer_attribute_data_type);
        sqlMap.put(CustomerAttribute.CUSTOMER_ATTRIBUTE_DEFAULT_VALUE, customer_attribute_default_value);
        sqlMap.put(CustomerAttribute.CUSTOMER_ATTRIBUTE_SORT, customer_attribute_sort);
        sqlMap.put(CustomerAttribute.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(CustomerAttribute.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(CustomerAttribute.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("customer_attribute.update", sqlMap);

        logSql("customer_attribute", "update", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByCustomer_attribute_idAndSystem_version(String customer_attribute_id, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(CustomerAttribute.CUSTOMER_ATTRIBUTE_ID, customer_attribute_id);
        sqlMap.put(CustomerAttribute.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(CustomerAttribute.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(CustomerAttribute.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("customer_attribute.deleteByCustomer_attribute_idAndSystem_version", sqlMap);

        logSql("customer_attribute", "deleteByCustomer_attribute_idAndSystem_version", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}