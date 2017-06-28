package com.nowui.chuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.GuangqiCustomerPrize;
import com.nowui.chuangshi.model.GuangqiPrize;

import java.util.Date;
import java.util.List;

public class GuangqiCustomerPrizeDao extends Dao {

    public Integer countByApp_id(String app_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(GuangqiCustomerPrize.APP_ID, app_id);
        SqlPara sqlPara = Db.getSqlPara("guangqi_customer_prize.countByApp_id", sqlMap);

        logSql(request_app_id, request_http_id, "table_guangqi_customer_prize", "countByApp_id", sqlPara, request_user_id);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByOrApp_id(String app_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(GuangqiCustomerPrize.APP_ID, app_id);
        SqlPara sqlPara = Db.getSqlPara("guangqi_customer_prize.countByOrApp_id", sqlMap);

        logSql(request_app_id, request_http_id, "table_guangqi_customer_prize", "countByOrApp_id", sqlPara, request_user_id);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByApp_idAndCustomer_id(String app_id, String customer_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(GuangqiCustomerPrize.APP_ID, app_id);
        sqlMap.put(GuangqiCustomerPrize.CUSTOMER_ID, customer_id);
        SqlPara sqlPara = Db.getSqlPara("guangqi_customer_prize.countByApp_idAndCustomer_id", sqlMap);

        logSql(request_app_id, request_http_id, "table_guangqi_customer_prize", "countByApp_idAndCustomer_id", sqlPara, request_user_id);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByApp_idAndPrize_id(String app_id, String prize_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(GuangqiCustomerPrize.APP_ID, app_id);
        sqlMap.put(GuangqiCustomerPrize.PRIZE_ID, prize_id);
        SqlPara sqlPara = Db.getSqlPara("guangqi_customer_prize.countByApp_idAndPrize_id", sqlMap);

        logSql(request_app_id, request_http_id, "table_guangqi_customer_prize", "countByApp_idAndPrize_id", sqlPara, request_user_id);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByApp_idAndPrize_idAndCustomer_prize_date(String app_id, String prize_id, String customer_prize_date, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(GuangqiCustomerPrize.APP_ID, app_id);
        sqlMap.put(GuangqiCustomerPrize.PRIZE_ID, prize_id);
        sqlMap.put(GuangqiCustomerPrize.CUSTOMER_PRIZE_DATE, customer_prize_date);
        SqlPara sqlPara = Db.getSqlPara("guangqi_customer_prize.countByApp_idAndPrize_idAndCustomer_prize_date", sqlMap);

        logSql(request_app_id, request_http_id, "table_guangqi_customer_prize", "countByApp_idAndPrize_idAndCustomer_prize_date", sqlPara, request_user_id);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<GuangqiCustomerPrize> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(GuangqiCustomerPrize.APP_ID, app_id);
        sqlMap.put(GuangqiCustomerPrize.SYSTEM_CREATE_TIME, system_create_time);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("guangqi_customer_prize.listByApp_idAndSystem_create_timeAndLimit", sqlMap);

        logSql(request_app_id, request_http_id, "table_guangqi_customer_prize", "listByApp_idAndSystem_create_timeAndLimit", sqlPara, request_user_id);

        return new GuangqiCustomerPrize().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<GuangqiCustomerPrize> listByApp_id(String app_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(GuangqiCustomerPrize.APP_ID, app_id);
        SqlPara sqlPara = Db.getSqlPara("guangqi_customer_prize.listByApp_id", sqlMap);

        logSql(request_app_id, request_http_id, "table_guangqi_customer_prize", "listByApp_id", sqlPara, request_user_id);

        return new GuangqiCustomerPrize().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<GuangqiCustomerPrize> listByApp_idAndLimit(String app_id, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(GuangqiCustomerPrize.APP_ID, app_id);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("guangqi_customer_prize.listByApp_idAndLimit", sqlMap);

        logSql(request_app_id, request_http_id, "table_guangqi_customer_prize", "listByApp_idAndLimit", sqlPara, request_user_id);

        return new GuangqiCustomerPrize().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<GuangqiCustomerPrize> listByOrApp_idAndLimit(String app_id, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(GuangqiCustomerPrize.APP_ID, app_id);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("guangqi_customer_prize.listByOrApp_idAndLimit", sqlMap);

        logSql(request_app_id, request_http_id, "table_guangqi_customer_prize", "listByOrApp_idAndLimit", sqlPara, request_user_id);

        return new GuangqiCustomerPrize().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public GuangqiCustomerPrize findByCustomer_prize_id(String customer_prize_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(GuangqiCustomerPrize.CUSTOMER_PRIZE_ID, customer_prize_id);
        SqlPara sqlPara = Db.getSqlPara("guangqi_customer_prize.findByCustomer_prize_id", sqlMap);

        logSql(request_app_id, request_http_id, "table_guangqi_customer_prize", "findByCustomer_prize_id", sqlPara, request_user_id);

        List<GuangqiCustomerPrize> guangqi_customer_prizeList = new GuangqiCustomerPrize().find(sqlPara.getSql(), sqlPara.getPara());
        if (guangqi_customer_prizeList.size() == 0) {
            return null;
        } else {
            return guangqi_customer_prizeList.get(0);
        }
    }

    public Boolean save(String customer_prize_id, String app_id, String customer_id, String prize_id, String customer_prize_date, String system_create_user_id, Integer prize_quantity, Integer prize_limit, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(GuangqiCustomerPrize.CUSTOMER_PRIZE_ID, customer_prize_id);
        sqlMap.put(GuangqiCustomerPrize.APP_ID, app_id);
        sqlMap.put(GuangqiCustomerPrize.CUSTOMER_ID, customer_id);
        sqlMap.put(GuangqiCustomerPrize.PRIZE_ID, prize_id);
        sqlMap.put(GuangqiCustomerPrize.CUSTOMER_PRIZE_DATE, customer_prize_date);
        sqlMap.put(GuangqiCustomerPrize.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(GuangqiCustomerPrize.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(GuangqiCustomerPrize.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(GuangqiCustomerPrize.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(GuangqiCustomerPrize.SYSTEM_VERSION, 0);
        sqlMap.put(GuangqiCustomerPrize.SYSTEM_STATUS, true);
        sqlMap.put(GuangqiPrize.PRIZE_QUANTITY, prize_quantity);
        sqlMap.put(GuangqiPrize.PRIZE_LIMIT, prize_limit);
        SqlPara sqlPara = Db.getSqlPara("guangqi_customer_prize.save", sqlMap);

        logSql(request_app_id, request_http_id, "table_guangqi_customer_prize", "save", sqlPara, request_user_id);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean update(String customer_prize_id, String customer_id, String prize_id, String customer_prize_date, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(GuangqiCustomerPrize.CUSTOMER_PRIZE_ID, customer_prize_id);
        sqlMap.put(GuangqiCustomerPrize.CUSTOMER_ID, customer_id);
        sqlMap.put(GuangqiCustomerPrize.PRIZE_ID, prize_id);
        sqlMap.put(GuangqiCustomerPrize.CUSTOMER_PRIZE_DATE, customer_prize_date);
        sqlMap.put(GuangqiCustomerPrize.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(GuangqiCustomerPrize.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(GuangqiCustomerPrize.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("guangqi_customer_prize.update", sqlMap);

        logSql(request_app_id, request_http_id, "table_guangqi_customer_prize", "update", sqlPara, request_user_id);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByCustomer_prize_idAndSystem_version(String customer_prize_id, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(GuangqiCustomerPrize.CUSTOMER_PRIZE_ID, customer_prize_id);
        sqlMap.put(GuangqiCustomerPrize.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(GuangqiCustomerPrize.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(GuangqiCustomerPrize.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("guangqi_customer_prize.deleteByCustomer_prize_idAndSystem_version", sqlMap);

        logSql(request_app_id, request_http_id, "table_guangqi_customer_prize", "deleteByCustomer_prize_idAndSystem_version", sqlPara, request_user_id);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}