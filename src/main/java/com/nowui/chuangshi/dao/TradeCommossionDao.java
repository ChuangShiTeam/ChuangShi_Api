package com.nowui.chuangshi.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.TradeCommossion;

public class TradeCommossionDao extends Dao {

    public List<TradeCommossion> listByTrade_id(String trade_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(TradeCommossion.TRADE_ID, trade_id);
        SqlPara sqlPara = Db.getSqlPara("trade_commossion.listByTrade_id", sqlMap);

        logSql("trade_commossion", "listByTrade_id", sqlPara);

        return new TradeCommossion().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public Boolean save(String trade_id, String product_sku_id, String member_id, String member_name, String member_level_id, String member_level_name, Integer product_sku_commission,
            BigDecimal product_sku_commission_amount, String system_create_user_id) {
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

    public Boolean deleteByTrade_idAndSystem_version(String trade_id, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(TradeCommossion.TRADE_ID, trade_id);
        sqlMap.put(TradeCommossion.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(TradeCommossion.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(TradeCommossion.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("trade_commossion.deleteByTrade_idAndSystem_version", sqlMap);

        logSql("trade_commossion", "deleteByTrade_idAndSystem_version", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public boolean batchSave(List<TradeCommossion> tradeCommossionList) {
        int[] result = Db.batchSave(tradeCommossionList, Constant.BATCH_SIZE);

        for (int i : result) {
            if (i == 0) {
                throw new RuntimeException("订单分成保存不成功");
            }
        }
        return true;
    }

}