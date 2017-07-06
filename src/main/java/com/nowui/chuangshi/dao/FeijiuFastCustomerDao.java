package com.nowui.chuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.FeijiuFastCustomer;

import java.util.Date;
import java.util.List;

public class FeijiuFastCustomerDao extends Dao {

    public Integer countByApp_idAndCustomer_name(String app_id, String customer_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(FeijiuFastCustomer.APP_ID, app_id);
        sqlMap.put(FeijiuFastCustomer.CUSTOMER_NAME, customer_name);
        SqlPara sqlPara = Db.getSqlPara("feijiu_fast_customer.countByApp_idAndCustomer_name", sqlMap);

        logSql("feijiu_fast_customer", "countByApp_idAndCustomer_name", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByOrApp_id(String app_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(FeijiuFastCustomer.APP_ID, app_id);
        SqlPara sqlPara = Db.getSqlPara("feijiu_fast_customer.countByOrApp_id", sqlMap);

        logSql("feijiu_fast_customer", "countByOrApp_id", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<FeijiuFastCustomer> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(FeijiuFastCustomer.APP_ID, app_id);
        sqlMap.put(FeijiuFastCustomer.SYSTEM_CREATE_TIME, system_create_time);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("feijiu_fast_customer.listByApp_idAndSystem_create_timeAndLimit", sqlMap);

        logSql("feijiu_fast_customer", "listByApp_idAndSystem_create_timeAndLimit", sqlPara);

        return new FeijiuFastCustomer().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<FeijiuFastCustomer> listByApp_id(String app_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(FeijiuFastCustomer.APP_ID, app_id);
        SqlPara sqlPara = Db.getSqlPara("feijiu_fast_customer.listByApp_id", sqlMap);

        logSql("feijiu_fast_customer", "listByApp_id", sqlPara);

        return new FeijiuFastCustomer().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<FeijiuFastCustomer> listByApp_idAndCustomer_nameAndLimit(String app_id, String customer_name, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(FeijiuFastCustomer.APP_ID, app_id);
        sqlMap.put(FeijiuFastCustomer.CUSTOMER_NAME, customer_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("feijiu_fast_customer.listByApp_idAndCustomer_nameAndLimit", sqlMap);

        logSql("feijiu_fast_customer", "listByApp_idAndCustomer_nameAndLimit", sqlPara);

        return new FeijiuFastCustomer().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<FeijiuFastCustomer> listByOrApp_idAndLimit(String app_id, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(FeijiuFastCustomer.APP_ID, app_id);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("feijiu_fast_customer.listByOrApp_idAndLimit", sqlMap);

        logSql("feijiu_fast_customer", "listByOrApp_idAndLimit", sqlPara);

        return new FeijiuFastCustomer().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public FeijiuFastCustomer findByCustomer_id(String customer_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(FeijiuFastCustomer.CUSTOMER_ID, customer_id);
        SqlPara sqlPara = Db.getSqlPara("feijiu_fast_customer.findByCustomer_id", sqlMap);

        logSql("feijiu_fast_customer", "findByCustomer_id", sqlPara);

        List<FeijiuFastCustomer> feijiu_fast_customerList = new FeijiuFastCustomer().find(sqlPara.getSql(), sqlPara.getPara());
        if (feijiu_fast_customerList.size() == 0) {
            return null;
        } else {
            return feijiu_fast_customerList.get(0);
        }
    }

    public Boolean save(String customer_id, String app_id, String customer_name, String customer_phone, String customer_birthday, String customer_city, String customer_sex, String customer_id_card, String customer_money, String customer_fang, String customer_che, String customer_xin, String customer_shou, String customer_dai, String customer_gong, String system_create_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(FeijiuFastCustomer.CUSTOMER_ID, customer_id);
        sqlMap.put(FeijiuFastCustomer.APP_ID, app_id);
        sqlMap.put(FeijiuFastCustomer.CUSTOMER_NAME, customer_name);
        sqlMap.put(FeijiuFastCustomer.CUSTOMER_PHONE, customer_phone);
        sqlMap.put(FeijiuFastCustomer.CUSTOMER_BIRTHDAY, customer_birthday);
        sqlMap.put(FeijiuFastCustomer.CUSTOMER_CITY, customer_city);
        sqlMap.put(FeijiuFastCustomer.CUSTOMER_SEX, customer_sex);
        sqlMap.put(FeijiuFastCustomer.CUSTOMER_ID_CARD, customer_id_card);
        sqlMap.put(FeijiuFastCustomer.CUSTOMER_MONEY, customer_money);
        sqlMap.put(FeijiuFastCustomer.CUSTOMER_FANG, customer_fang);
        sqlMap.put(FeijiuFastCustomer.CUSTOMER_CHE, customer_che);
        sqlMap.put(FeijiuFastCustomer.CUSTOMER_XIN, customer_xin);
        sqlMap.put(FeijiuFastCustomer.CUSTOMER_SHOU, customer_shou);
        sqlMap.put(FeijiuFastCustomer.CUSTOMER_DAI, customer_dai);
        sqlMap.put(FeijiuFastCustomer.CUSTOMER_GONG, customer_gong);
        sqlMap.put(FeijiuFastCustomer.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(FeijiuFastCustomer.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(FeijiuFastCustomer.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(FeijiuFastCustomer.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(FeijiuFastCustomer.SYSTEM_VERSION, 0);
        sqlMap.put(FeijiuFastCustomer.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("feijiu_fast_customer.save", sqlMap);

        logSql("feijiu_fast_customer", "save", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean update(String customer_id, String customer_name, String customer_phone, String customer_birthday, String customer_city, String customer_sex, String customer_id_card, String customer_money, String customer_fang, String customer_che, String customer_xin, String customer_shou, String customer_dai, String customer_gong, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(FeijiuFastCustomer.CUSTOMER_ID, customer_id);
        sqlMap.put(FeijiuFastCustomer.CUSTOMER_NAME, customer_name);
        sqlMap.put(FeijiuFastCustomer.CUSTOMER_PHONE, customer_phone);
        sqlMap.put(FeijiuFastCustomer.CUSTOMER_BIRTHDAY, customer_birthday);
        sqlMap.put(FeijiuFastCustomer.CUSTOMER_CITY, customer_city);
        sqlMap.put(FeijiuFastCustomer.CUSTOMER_SEX, customer_sex);
        sqlMap.put(FeijiuFastCustomer.CUSTOMER_ID_CARD, customer_id_card);
        sqlMap.put(FeijiuFastCustomer.CUSTOMER_MONEY, customer_money);
        sqlMap.put(FeijiuFastCustomer.CUSTOMER_FANG, customer_fang);
        sqlMap.put(FeijiuFastCustomer.CUSTOMER_CHE, customer_che);
        sqlMap.put(FeijiuFastCustomer.CUSTOMER_XIN, customer_xin);
        sqlMap.put(FeijiuFastCustomer.CUSTOMER_SHOU, customer_shou);
        sqlMap.put(FeijiuFastCustomer.CUSTOMER_DAI, customer_dai);
        sqlMap.put(FeijiuFastCustomer.CUSTOMER_GONG, customer_gong);
        sqlMap.put(FeijiuFastCustomer.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(FeijiuFastCustomer.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(FeijiuFastCustomer.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("feijiu_fast_customer.update", sqlMap);

        logSql("feijiu_fast_customer", "update", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByCustomer_idAndSystem_version(String customer_id, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(FeijiuFastCustomer.CUSTOMER_ID, customer_id);
        sqlMap.put(FeijiuFastCustomer.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(FeijiuFastCustomer.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(FeijiuFastCustomer.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("feijiu_fast_customer.deleteByCustomer_idAndSystem_version", sqlMap);

        logSql("feijiu_fast_customer", "deleteByCustomer_idAndSystem_version", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}