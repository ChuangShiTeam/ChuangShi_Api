package com.nowui.chuangshi.dao;

import java.util.Date;
import java.util.List;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.TradePay;

public class TradePayDao extends Dao {

    public Integer countByApp_idOrLikeTrade_pay_name(String app_id, String trade_pay_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(TradePay.APP_ID, app_id);
        sqlMap.put(TradePay.TRADE_PAY_NAME, trade_pay_name);
        SqlPara sqlPara = Db.getSqlPara("trade_pay.countByApp_idOrLikeTrade_pay_name", sqlMap);

        logSql("trade_pay", "countByApp_idOrLikeTrade_pay_name", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByOrApp_idOrLikeTrade_pay_name(String app_id, String trade_pay_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(TradePay.APP_ID, app_id);
        sqlMap.put(TradePay.TRADE_PAY_NAME, trade_pay_name);
        SqlPara sqlPara = Db.getSqlPara("trade_pay.countByOrApp_idOrLikeTrade_pay_name", sqlMap);

        logSql("trade_pay", "countByOrApp_idOrLikeTrade_pay_name", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<TradePay> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(TradePay.APP_ID, app_id);
        sqlMap.put(TradePay.SYSTEM_CREATE_TIME, system_create_time);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("trade_pay.listByApp_idAndSystem_create_timeAndLimit", sqlMap);

        logSql("trade_pay", "listByApp_idAndSystem_create_timeAndLimit", sqlPara);

        return new TradePay().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<TradePay> listByApp_idOrLikeTrade_pay_nameAndLimit(String app_id, String trade_pay_name, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(TradePay.APP_ID, app_id);
        sqlMap.put(TradePay.TRADE_PAY_NAME, trade_pay_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("trade_pay.listByApp_idOrLikeTrade_pay_nameAndLimit", sqlMap);

        logSql("trade_pay", "listByApp_idOrLikeTrade_pay_nameAndLimit", sqlPara);

        return new TradePay().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<TradePay> listByOrApp_idOrLikeTrade_pay_nameAndLimit(String app_id, String trade_pay_name, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(TradePay.APP_ID, app_id);
        sqlMap.put(TradePay.TRADE_PAY_NAME, trade_pay_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("trade_pay.listByOrApp_idOrLikeTrade_pay_nameAndLimit", sqlMap);

        logSql("trade_pay", "listByOrApp_idOrLikeTrade_pay_nameAndLimit", sqlPara);

        return new TradePay().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public TradePay findByTrade_pay_id(String trade_pay_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(TradePay.TRADE_PAY_ID, trade_pay_id);
        SqlPara sqlPara = Db.getSqlPara("trade_pay.findByTrade_pay_id", sqlMap);

        logSql("trade_pay", "findByTrade_pay_id", sqlPara);

        List<TradePay> trade_payList = new TradePay().find(sqlPara.getSql(), sqlPara.getPara());
        if (trade_payList.size() == 0) {
            return null;
        } else {
            return trade_payList.get(0);
        }
    }

    public Boolean save(String trade_id, String trade_pay_type, String trade_pay_number, String trade_pay_account, String trade_pay_time, String trade_pay_result, String system_create_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(TradePay.TRADE_ID, trade_id);
        sqlMap.put(TradePay.TRADE_PAY_TYPE, trade_pay_type);
        sqlMap.put(TradePay.TRADE_PAY_NUMBER, trade_pay_number);
        sqlMap.put(TradePay.TRADE_PAY_ACCOUNT, trade_pay_account);
        sqlMap.put(TradePay.TRADE_PAY_TIME, trade_pay_time);
        sqlMap.put(TradePay.TRADE_PAY_RESULT, trade_pay_result);
        sqlMap.put(TradePay.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(TradePay.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(TradePay.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(TradePay.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(TradePay.SYSTEM_VERSION, 0);
        sqlMap.put(TradePay.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("trade_pay.save", sqlMap);

        logSql("trade_pay", "save", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean update(String trade_pay_id, String trade_id, String trade_pay_type, String trade_pay_number, String trade_pay_account, String trade_pay_time, String trade_pay_result,
            String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(TradePay.TRADE_PAY_ID, trade_pay_id);
        sqlMap.put(TradePay.TRADE_ID, trade_id);
        sqlMap.put(TradePay.TRADE_PAY_TYPE, trade_pay_type);
        sqlMap.put(TradePay.TRADE_PAY_NUMBER, trade_pay_number);
        sqlMap.put(TradePay.TRADE_PAY_ACCOUNT, trade_pay_account);
        sqlMap.put(TradePay.TRADE_PAY_TIME, trade_pay_time);
        sqlMap.put(TradePay.TRADE_PAY_RESULT, trade_pay_result);
        sqlMap.put(TradePay.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(TradePay.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(TradePay.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("trade_pay.update", sqlMap);

        logSql("trade_pay", "update", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByTrade_pay_idAndSystem_version(String trade_pay_id, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(TradePay.TRADE_PAY_ID, trade_pay_id);
        sqlMap.put(TradePay.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(TradePay.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(TradePay.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("trade_pay.deleteByTrade_pay_idAndSystem_version", sqlMap);

        logSql("trade_pay", "deleteByTrade_pay_idAndSystem_version", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}