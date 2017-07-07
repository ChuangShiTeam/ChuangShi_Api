package com.nowui.chuangshi.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.model.TradeProductSku;

public class TradeProductSkuDao extends Dao {

    public List<TradeProductSku> listByTrade_id(String trade_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(TradeProductSku.TRADE_ID, trade_id);
        SqlPara sqlPara = Db.getSqlPara("trade_product_sku.listByTrade_id", sqlMap);

        logSql("trade_product_sku", "listByTrade_id", sqlPara);

        return new TradeProductSku().find(sqlPara.getSql(), sqlPara.getPara());
    }
    
    public Boolean save(String trade_id, String product_sku_id, String product_snap_id, Integer product_sku_quantity, BigDecimal product_sku_amount, String system_create_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(TradeProductSku.TRADE_ID, trade_id);
        sqlMap.put(TradeProductSku.PRODUCT_SKU_ID, product_sku_id);
        sqlMap.put(TradeProductSku.PRODUCT_SNAP_ID, product_snap_id);
        sqlMap.put(TradeProductSku.PRODUCT_SKU_QUANTITY, product_sku_quantity);
        sqlMap.put(TradeProductSku.PRODUCT_SKU_AMOUNT, product_sku_amount);
        sqlMap.put(TradeProductSku.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(TradeProductSku.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(TradeProductSku.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(TradeProductSku.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(TradeProductSku.SYSTEM_VERSION, 0);
        sqlMap.put(TradeProductSku.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("trade_product_sku.save", sqlMap);

        logSql("trade_product_sku", "save", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByTrade_idAndSystem_version(String trade_id, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(TradeProductSku.TRADE_ID, trade_id);
        sqlMap.put(TradeProductSku.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(TradeProductSku.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(TradeProductSku.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("trade_product_sku.deleteByTrade_idAndSystem_version", sqlMap);

        logSql("trade_product_sku", "deleteByTrade_product_sku_idAndSystem_version", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}