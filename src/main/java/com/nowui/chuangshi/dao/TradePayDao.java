package com.nowui.chuangshi.dao;

import java.util.Date;
import java.util.List;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.model.TradePay;

public class TradePayDao extends Dao {

    public List<TradePay> listByTrade_id(String trade_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(TradePay.TRADE_ID, trade_id);
        SqlPara sqlPara = Db.getSqlPara("trade_pay.listByTrade_id", sqlMap);

        logSql("trade_pay", "listByTrade_id", sqlPara);

        return new TradePay().find(sqlPara.getSql(), sqlPara.getPara());
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

    public Boolean deleteByTrade_idAndSystem_version(String trade_id, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(TradePay.TRADE_ID, trade_id);
        sqlMap.put(TradePay.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(TradePay.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(TradePay.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("trade_pay.deleteByTrade_idAndSystem_version", sqlMap);

        logSql("trade_pay", "deleteByTrade_idAndSystem_version", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}