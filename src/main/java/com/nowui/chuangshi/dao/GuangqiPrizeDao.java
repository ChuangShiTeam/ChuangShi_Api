package com.nowui.chuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.GuangqiPrize;

import java.util.Date;
import java.util.List;

public class GuangqiPrizeDao extends Dao {

    public Integer countByApp_id(String app_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(GuangqiPrize.APP_ID, app_id);
        SqlPara sqlPara = Db.getSqlPara("guangqi_prize.countByApp_id", sqlMap);

        logSql(request_app_id, request_http_id, "table_guangqi_prize", "countByApp_id", sqlPara, request_user_id);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByOrApp_id(String app_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(GuangqiPrize.APP_ID, app_id);
        SqlPara sqlPara = Db.getSqlPara("guangqi_prize.countByOrApp_id", sqlMap);

        logSql(request_app_id, request_http_id, "table_guangqi_prize", "countByOrApp_id", sqlPara, request_user_id);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<GuangqiPrize> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(GuangqiPrize.APP_ID, app_id);
        sqlMap.put(GuangqiPrize.SYSTEM_CREATE_TIME, system_create_time);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("guangqi_prize.listByApp_idAndSystem_create_timeAndLimit", sqlMap);

        logSql(request_app_id, request_http_id, "table_guangqi_prize", "listByApp_idAndSystem_create_timeAndLimit", sqlPara, request_user_id);

        return new GuangqiPrize().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<GuangqiPrize> listByApp_idAndPrize_nameAndLimit(String app_id, String prize_name, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(GuangqiPrize.APP_ID, app_id);
        sqlMap.put(GuangqiPrize.PRIZE_NAME, prize_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("guangqi_prize.listByApp_idAndPrize_nameAndLimit", sqlMap);

        logSql(request_app_id, request_http_id, "table_guangqi_prize", "listByApp_idAndPrize_nameAndLimit", sqlPara, request_user_id);

        return new GuangqiPrize().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<GuangqiPrize> listByApp_id(String app_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(GuangqiPrize.APP_ID, app_id);
        SqlPara sqlPara = Db.getSqlPara("guangqi_prize.listByApp_id", sqlMap);

        logSql(request_app_id, request_http_id, "table_guangqi_prize", "listByApp_id", sqlPara, request_user_id);

        return new GuangqiPrize().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<GuangqiPrize> listByOrApp_idAndLimit(String app_id, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(GuangqiPrize.APP_ID, app_id);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("guangqi_prize.listByOrApp_idAndLimit", sqlMap);

        logSql(request_app_id, request_http_id, "table_guangqi_prize", "listByOrApp_idAndLimit", sqlPara, request_user_id);

        return new GuangqiPrize().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public GuangqiPrize findByPrize_id(String prize_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(GuangqiPrize.PRIZE_ID, prize_id);
        SqlPara sqlPara = Db.getSqlPara("guangqi_prize.findByPrize_id", sqlMap);

        logSql(request_app_id, request_http_id, "table_guangqi_prize", "findByPrize_id", sqlPara, request_user_id);

        List<GuangqiPrize> guangqi_prizeList = new GuangqiPrize().find(sqlPara.getSql(), sqlPara.getPara());
        if (guangqi_prizeList.size() == 0) {
            return null;
        } else {
            return guangqi_prizeList.get(0);
        }
    }

    public Boolean save(String prize_id, String app_id, String prize_name, Integer prize_probability, Integer prize_quantity, Integer prize_limit, Integer prize_sort, Boolean prize_is_default, String system_create_user_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(GuangqiPrize.PRIZE_ID, prize_id);
        sqlMap.put(GuangqiPrize.APP_ID, app_id);
        sqlMap.put(GuangqiPrize.PRIZE_NAME, prize_name);
        sqlMap.put(GuangqiPrize.PRIZE_PROBABILITY, prize_probability);
        sqlMap.put(GuangqiPrize.PRIZE_QUANTITY, prize_quantity);
        sqlMap.put(GuangqiPrize.PRIZE_LIMIT, prize_limit);
        sqlMap.put(GuangqiPrize.PRIZE_SORT, prize_sort);
        sqlMap.put(GuangqiPrize.PRIZE_IS_DEFAULT, prize_is_default);
        sqlMap.put(GuangqiPrize.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(GuangqiPrize.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(GuangqiPrize.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(GuangqiPrize.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(GuangqiPrize.SYSTEM_VERSION, 0);
        sqlMap.put(GuangqiPrize.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("guangqi_prize.save", sqlMap);

        logSql(request_app_id, request_http_id, "table_guangqi_prize", "save", sqlPara, request_user_id);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean update(String prize_id, String prize_name, Integer prize_probability, Integer prize_quantity, Integer prize_limit, Integer prize_sort, Boolean prize_is_default, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(GuangqiPrize.PRIZE_ID, prize_id);
        sqlMap.put(GuangqiPrize.PRIZE_NAME, prize_name);
        sqlMap.put(GuangqiPrize.PRIZE_PROBABILITY, prize_probability);
        sqlMap.put(GuangqiPrize.PRIZE_QUANTITY, prize_quantity);
        sqlMap.put(GuangqiPrize.PRIZE_LIMIT, prize_limit);
        sqlMap.put(GuangqiPrize.PRIZE_SORT, prize_sort);
        sqlMap.put(GuangqiPrize.PRIZE_IS_DEFAULT, prize_is_default);
        sqlMap.put(GuangqiPrize.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(GuangqiPrize.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(GuangqiPrize.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("guangqi_prize.update", sqlMap);

        logSql(request_app_id, request_http_id, "table_guangqi_prize", "update", sqlPara, request_user_id);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByPrize_idAndSystem_version(String prize_id, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(GuangqiPrize.PRIZE_ID, prize_id);
        sqlMap.put(GuangqiPrize.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(GuangqiPrize.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(GuangqiPrize.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("guangqi_prize.deleteByPrize_idAndSystem_version", sqlMap);

        logSql(request_app_id, request_http_id, "table_guangqi_prize", "deleteByPrize_idAndSystem_version", sqlPara, request_user_id);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}