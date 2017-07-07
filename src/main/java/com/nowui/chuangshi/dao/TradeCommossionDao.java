package com.nowui.chuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.TradeCommossion;

import java.util.Date;
import java.util.List;

public class TradeCommossionDao extends Dao {

    public Integer countByApp_idOrLikeTrade_commossion_name(String app_id, String trade_commossion_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(TradeCommossion.APP_ID, app_id);
        sqlMap.put(TradeCommossion.TRADE_COMMOSSION_NAME, trade_commossion_name);
        SqlPara sqlPara = Db.getSqlPara("trade_commossion.countByApp_idOrLikeTrade_commossion_name", sqlMap);

        logSql("trade_commossion", "countByApp_idOrLikeTrade_commossion_name", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByOrApp_idOrLikeTrade_commossion_name(String app_id, String trade_commossion_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(TradeCommossion.APP_ID, app_id);
        sqlMap.put(TradeCommossion.TRADE_COMMOSSION_NAME, trade_commossion_name);
        SqlPara sqlPara = Db.getSqlPara("trade_commossion.countByOrApp_idOrLikeTrade_commossion_name", sqlMap);

        logSql("trade_commossion", "countByOrApp_idOrLikeTrade_commossion_name", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<TradeCommossion> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(TradeCommossion.APP_ID, app_id);
        sqlMap.put(TradeCommossion.SYSTEM_CREATE_TIME, system_create_time);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("trade_commossion.listByApp_idAndSystem_create_timeAndLimit", sqlMap);

        logSql("trade_commossion", "listByApp_idAndSystem_create_timeAndLimit", sqlPara);

        return new TradeCommossion().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<TradeCommossion> listByApp_idOrLikeTrade_commossion_nameAndLimit(String app_id, String trade_commossion_name, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(TradeCommossion.APP_ID, app_id);
        sqlMap.put(TradeCommossion.TRADE_COMMOSSION_NAME, trade_commossion_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("trade_commossion.listByApp_idOrLikeTrade_commossion_nameAndLimit", sqlMap);

        logSql("trade_commossion", "listByApp_idOrLikeTrade_commossion_nameAndLimit", sqlPara);

        return new TradeCommossion().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<TradeCommossion> listByOrApp_idOrLikeTrade_commossion_nameAndLimit(String app_id, String trade_commossion_name, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(TradeCommossion.APP_ID, app_id);
        sqlMap.put(TradeCommossion.TRADE_COMMOSSION_NAME, trade_commossion_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("trade_commossion.listByOrApp_idOrLikeTrade_commossion_nameAndLimit", sqlMap);

        logSql("trade_commossion", "listByOrApp_idOrLikeTrade_commossion_nameAndLimit", sqlPara);

        return new TradeCommossion().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public TradeCommossion findByTrade_commossion_id(String trade_commossion_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(TradeCommossion.TRADE_COMMOSSION_ID, trade_commossion_id);
        SqlPara sqlPara = Db.getSqlPara("trade_commossion.findByTrade_commossion_id", sqlMap);

        logSql("trade_commossion", "findByTrade_commossion_id", sqlPara);

        List<TradeCommossion> trade_commossionList = new TradeCommossion().find(sqlPara.getSql(), sqlPara.getPara());
        if (trade_commossionList.size() == 0) {
            return null;
        } else {
            return trade_commossionList.get(0);
        }
    }

    public Boolean save(String trade_id, String product_sku_id, String member_id, String member_name, String member_level_id, String member_level_name, Integer product_sku_commission, BigDecimal product_sku_commission_amount, String system_create_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(TradeCommossion.TRADE_ID, trade_id);
        sqlMap.put(TradeCommossion.PRODUCT_SKU_ID, product_sku_id);
        sqlMap.put(TradeCommossion.MEMBER_ID, member_id);
        sqlMap.put(TradeCommossion.MEMBER_NAME, member_name);
        sqlMap.put(TradeCommossion.MEMBER_LEVEL_ID, member_level_id);
        sqlMap.put(TradeCommossion.MEMBER_LEVEL_NAME, member_level_name);
        sqlMap.put(TradeCommossion.PRODUCT_SKU_COMMISSION, product_sku_commission);
        sqlMap.put(TradeCommossion.PRODUCT_SKU_COMMISSION_AMOUNT, product_sku_commission_amount);
        sqlMap.put(TradeCommossion.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(TradeCommossion.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(TradeCommossion.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(TradeCommossion.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(TradeCommossion.SYSTEM_VERSION, 0);
        sqlMap.put(TradeCommossion.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("trade_commossion.save", sqlMap);

        logSql("trade_commossion", "save", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean update(String trade_commossion_id, String trade_id, String product_sku_id, String member_id, String member_name, String member_level_id, String member_level_name, Integer product_sku_commission, BigDecimal product_sku_commission_amount, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(TradeCommossion.TRADE_COMMOSSION_ID, trade_commossion_id);
        sqlMap.put(TradeCommossion.TRADE_ID, trade_id);
        sqlMap.put(TradeCommossion.PRODUCT_SKU_ID, product_sku_id);
        sqlMap.put(TradeCommossion.MEMBER_ID, member_id);
        sqlMap.put(TradeCommossion.MEMBER_NAME, member_name);
        sqlMap.put(TradeCommossion.MEMBER_LEVEL_ID, member_level_id);
        sqlMap.put(TradeCommossion.MEMBER_LEVEL_NAME, member_level_name);
        sqlMap.put(TradeCommossion.PRODUCT_SKU_COMMISSION, product_sku_commission);
        sqlMap.put(TradeCommossion.PRODUCT_SKU_COMMISSION_AMOUNT, product_sku_commission_amount);
        sqlMap.put(TradeCommossion.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(TradeCommossion.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(TradeCommossion.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("trade_commossion.update", sqlMap);

        logSql("trade_commossion", "update", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByTrade_commossion_idAndSystem_version(String trade_commossion_id, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(TradeCommossion.TRADE_COMMOSSION_ID, trade_commossion_id);
        sqlMap.put(TradeCommossion.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(TradeCommossion.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(TradeCommossion.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("trade_commossion.deleteByTrade_commossion_idAndSystem_version", sqlMap);

        logSql("trade_commossion", "deleteByTrade_commossion_idAndSystem_version", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}