package com.nowui.chuangshi.dao;

import java.util.Date;
import java.util.List;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.model.CustomerAttributeValue;

public class CustomerAttributeValueDao extends Dao {

    public List<CustomerAttributeValue> listByCustomer_id(String customer_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(CustomerAttributeValue.CUSTOMER_ID, customer_id);
        SqlPara sqlPara = Db.getSqlPara("customer_attribute_value.listByCustomer_id", sqlMap);

        logSql("customer_attribute_value", "listByCustomer_id", sqlPara);

        return new CustomerAttributeValue().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public Boolean save(String customer_attribute_id, String customer_id, String customer_attribute_value,
            String system_create_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(CustomerAttributeValue.CUSTOMER_ATTRIBUTE_ID, customer_attribute_id);
        sqlMap.put(CustomerAttributeValue.CUSTOMER_ID, customer_id);
        sqlMap.put(CustomerAttributeValue.CUSTOMER_ATTRIBUTE_VALUE, customer_attribute_value);
        sqlMap.put(CustomerAttributeValue.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(CustomerAttributeValue.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(CustomerAttributeValue.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(CustomerAttributeValue.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(CustomerAttributeValue.SYSTEM_VERSION, 0);
        sqlMap.put(CustomerAttributeValue.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("customer_attribute_value.save", sqlMap);

        logSql("customer_attribute_value", "save", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByCustomer_idAndSystem_update_user_id(String customer_id,
            String system_update_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(CustomerAttributeValue.CUSTOMER_ID, customer_id);
        sqlMap.put(CustomerAttributeValue.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(CustomerAttributeValue.SYSTEM_UPDATE_TIME, new Date());
        SqlPara sqlPara = Db.getSqlPara("customer_attribute_value.deleteByCustomer_idAndSystem_update_user_id",
                sqlMap);

        logSql("customer_attribute_value", "deleteByCustomer_idAndSystem_update_user_id", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}