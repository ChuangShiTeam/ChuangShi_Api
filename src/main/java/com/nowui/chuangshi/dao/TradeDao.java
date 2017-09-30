package com.nowui.chuangshi.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.Trade;
import com.nowui.chuangshi.type.TradeFlow;

public class TradeDao extends Dao {

    public Integer countByApp_idOrLikeTrade_number(String app_id, String trade_number) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Trade.APP_ID, app_id);
        sqlMap.put(Trade.TRADE_NUMBER, trade_number);
        SqlPara sqlPara = Db.getSqlPara("trade.countByApp_idOrLikeTrade_number", sqlMap);

        logSql("trade", "countByApp_idOrLikeTrade_number", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByOrApp_idOrLikeTrade_number(String app_id, String trade_number) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Trade.APP_ID, app_id);
        sqlMap.put(Trade.TRADE_NUMBER, trade_number);
        SqlPara sqlPara = Db.getSqlPara("trade.countByOrApp_idOrLikeTrade_number", sqlMap);

        logSql("trade", "countByOrApp_idOrLikeTrade_number", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<Trade> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Trade.APP_ID, app_id);
        sqlMap.put(Trade.SYSTEM_CREATE_TIME, system_create_time);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("trade.listByApp_idAndSystem_create_timeAndLimit", sqlMap);

        logSql("trade", "listByApp_idAndSystem_create_timeAndLimit", sqlPara);

        return new Trade().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Trade> listByApp_idOrLikeTrade_numberAndLimit(String app_id, String trade_number, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Trade.APP_ID, app_id);
        sqlMap.put(Trade.TRADE_NUMBER, trade_number);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("trade.listByApp_idOrLikeTrade_numberAndLimit", sqlMap);

        logSql("trade", "listByApp_idOrLikeTrade_numberAndLimit", sqlPara);

        return new Trade().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Trade> listByOrApp_idOrLikeTrade_numberAndLimit(String app_id, String trade_number, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Trade.APP_ID, app_id);
        sqlMap.put(Trade.TRADE_NUMBER, trade_number);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("trade.listByOrApp_idOrLikeTrade_numberAndLimit", sqlMap);

        logSql("trade", "listByOrApp_idOrLikeTrade_numberAndLimit", sqlPara);

        return new Trade().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Trade> listByUser_id(String user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Trade.USER_ID, user_id);
        SqlPara sqlPara = Db.getSqlPara("trade.listByUser_id", sqlMap);

        logSql("trade", "listByUser_id", sqlPara);

        return new Trade().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public Trade findByTrade_id(String trade_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Trade.TRADE_ID, trade_id);
        SqlPara sqlPara = Db.getSqlPara("trade.findByTrade_id", sqlMap);

        logSql("trade", "findByTrade_id", sqlPara);

        List<Trade> tradeList = new Trade().find(sqlPara.getSql(), sqlPara.getPara());
        if (tradeList.size() == 0) {
            return null;
        } else {
            return tradeList.get(0);
        }
    }

    public Trade findByTrade_number(String trade_number) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Trade.TRADE_NUMBER, trade_number);
        SqlPara sqlPara = Db.getSqlPara("trade.findByTrade_number", sqlMap);

        logSql("trade", "findByTrade_number", sqlPara);

        List<Trade> tradeList = new Trade().find(sqlPara.getSql(), sqlPara.getPara());
        if (tradeList.size() == 0) {
            return null;
        } else {
            return tradeList.get(0);
        }
    }

    public Boolean save(String trade_id, String app_id, String user_id, String trade_number, String trade_receiver_name, String trade_receiver_mobile, String trade_receiver_province,
            String trade_receiver_city, String trade_receiver_area, String trade_receiver_address, String trade_message, Integer trade_product_quantity, BigDecimal trade_product_amount,
            BigDecimal trade_express_amount, BigDecimal trade_discount_amount, BigDecimal trade_total_amount, Boolean trade_is_commission, Boolean trade_is_confirm, Boolean trade_is_pay, 
            String trade_flow, String trade_deliver_pattern, Boolean trade_status, 
            String trade_audit_status, String system_create_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Trade.TRADE_ID, trade_id);
        sqlMap.put(Trade.APP_ID, app_id);
        sqlMap.put(Trade.USER_ID, user_id);
        sqlMap.put(Trade.TRADE_NUMBER, trade_number);
        sqlMap.put(Trade.TRADE_RECEIVER_NAME, trade_receiver_name);
        sqlMap.put(Trade.TRADE_RECEIVER_MOBILE, trade_receiver_mobile);
        sqlMap.put(Trade.TRADE_RECEIVER_PROVINCE, trade_receiver_province);
        sqlMap.put(Trade.TRADE_RECEIVER_CITY, trade_receiver_city);
        sqlMap.put(Trade.TRADE_RECEIVER_AREA, trade_receiver_area);
        sqlMap.put(Trade.TRADE_RECEIVER_ADDRESS, trade_receiver_address);
        sqlMap.put(Trade.TRADE_MESSAGE, trade_message);
        sqlMap.put(Trade.TRADE_PRODUCT_QUANTITY, trade_product_quantity);
        sqlMap.put(Trade.TRADE_PRODUCT_AMOUNT, trade_product_amount);
        sqlMap.put(Trade.TRADE_EXPRESS_AMOUNT, trade_express_amount);
        sqlMap.put(Trade.TRADE_DISCOUNT_AMOUNT, trade_discount_amount);
        sqlMap.put(Trade.TRADE_TOTAL_AMOUNT, trade_total_amount);
        sqlMap.put(Trade.TRADE_IS_COMMISSION, trade_is_commission);
        sqlMap.put(Trade.TRADE_IS_CONFIRM, trade_is_confirm);
        sqlMap.put(Trade.TRADE_IS_PAY, trade_is_pay);
        sqlMap.put(Trade.TRADE_FLOW, trade_flow);
        sqlMap.put(Trade.TRADE_DELIVER_PATTERN, trade_deliver_pattern);
        sqlMap.put(Trade.TRADE_STATUS, trade_status);
        sqlMap.put(Trade.TRADE_AUDIT_STATUS, trade_audit_status);
        sqlMap.put(Trade.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(Trade.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(Trade.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(Trade.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Trade.SYSTEM_VERSION, 0);
        sqlMap.put(Trade.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("trade.save", sqlMap);

        logSql("trade", "save", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean update(String trade_id, String user_id, String trade_number, String trade_receiver_name, String trade_receiver_mobile, String trade_receiver_province, String trade_receiver_city,
            String trade_receiver_area, String trade_receiver_address, String trade_message, Integer trade_product_quantity, BigDecimal trade_product_amount, BigDecimal trade_express_amount,
            BigDecimal trade_discount_amount, BigDecimal trade_total_amount, Boolean trade_is_commission, Boolean trade_is_confirm, Boolean trade_is_pay, String trade_flow, String trade_deliver_pattern, Boolean trade_status, String trade_audit_status,
            String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Trade.TRADE_ID, trade_id);
        sqlMap.put(Trade.USER_ID, user_id);
        sqlMap.put(Trade.TRADE_NUMBER, trade_number);
        sqlMap.put(Trade.TRADE_RECEIVER_NAME, trade_receiver_name);
        sqlMap.put(Trade.TRADE_RECEIVER_MOBILE, trade_receiver_mobile);
        sqlMap.put(Trade.TRADE_RECEIVER_PROVINCE, trade_receiver_province);
        sqlMap.put(Trade.TRADE_RECEIVER_CITY, trade_receiver_city);
        sqlMap.put(Trade.TRADE_RECEIVER_AREA, trade_receiver_area);
        sqlMap.put(Trade.TRADE_RECEIVER_ADDRESS, trade_receiver_address);
        sqlMap.put(Trade.TRADE_MESSAGE, trade_message);
        sqlMap.put(Trade.TRADE_PRODUCT_QUANTITY, trade_product_quantity);
        sqlMap.put(Trade.TRADE_PRODUCT_AMOUNT, trade_product_amount);
        sqlMap.put(Trade.TRADE_EXPRESS_AMOUNT, trade_express_amount);
        sqlMap.put(Trade.TRADE_DISCOUNT_AMOUNT, trade_discount_amount);
        sqlMap.put(Trade.TRADE_TOTAL_AMOUNT, trade_total_amount);
        sqlMap.put(Trade.TRADE_IS_COMMISSION, trade_is_commission);
        sqlMap.put(Trade.TRADE_IS_CONFIRM, trade_is_confirm);
        sqlMap.put(Trade.TRADE_IS_PAY, trade_is_pay);
        sqlMap.put(Trade.TRADE_FLOW, trade_flow);
        sqlMap.put(Trade.TRADE_DELIVER_PATTERN, trade_deliver_pattern);
        sqlMap.put(Trade.TRADE_STATUS, trade_status);
        sqlMap.put(Trade.TRADE_AUDIT_STATUS, trade_audit_status);
        sqlMap.put(Trade.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(Trade.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Trade.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("trade.update", sqlMap);

        logSql("trade", "update", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean updateTrade_is_payAndTrade_flowAndSystem_update_user_idAndSystem_update_timeAndByTrade_idAndSystem_version(String trade_id, Boolean trade_is_pay, String trade_flow,
            String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Trade.TRADE_ID, trade_id);
        sqlMap.put(Trade.TRADE_IS_PAY, trade_is_pay);
        sqlMap.put(Trade.TRADE_FLOW, trade_flow);
        sqlMap.put(Trade.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(Trade.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Trade.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("trade.updateTrade_is_payAndTrade_flowAndSystem_update_user_idAndSystem_update_timeAndByTrade_idAndSystem_version", sqlMap);

        logSql("trade", "updateTrade_is_payAndTrade_flowAndSystem_update_user_idAndSystem_update_timeAndByTrade_idAndSystem_version", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByTrade_idAndSystem_version(String trade_id, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Trade.TRADE_ID, trade_id);
        sqlMap.put(Trade.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(Trade.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Trade.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("trade.deleteByTrade_idAndSystem_version", sqlMap);

        logSql("trade", "deleteByTrade_idAndSystem_version", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public boolean updateTrade_flowByTrade_idValidateSystem_version(String trade_id, String trade_flow, String request_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Trade.TRADE_ID, trade_id);
        sqlMap.put(Trade.TRADE_FLOW, trade_flow);
        sqlMap.put(Trade.SYSTEM_UPDATE_USER_ID, request_user_id);
        sqlMap.put(Trade.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Trade.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("trade.updateTrade_flowByTrade_idValidateSystem_version", sqlMap);

        logSql("trade", "updateTrade_flowByTrade_idValidateSystem_version", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}