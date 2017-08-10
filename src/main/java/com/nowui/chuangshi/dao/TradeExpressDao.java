package com.nowui.chuangshi.dao;

import java.util.Date;
import java.util.List;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.model.MemberDeliveryOrderExpress;
import com.nowui.chuangshi.model.TradeExpress;

public class TradeExpressDao extends Dao {

    public List<TradeExpress> listByTrade_id(String trade_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(TradeExpress.TRADE_ID, trade_id);
        SqlPara sqlPara = Db.getSqlPara("trade_express.listByTrade_id", sqlMap);

        logSql("trade_express", "listByTrade_id", sqlPara);

        return new TradeExpress().find(sqlPara.getSql(), sqlPara.getPara());
    }
    
    public TradeExpress findByExpress_id(String express_id) {
    	Kv sqlMap = Kv.create();
        sqlMap.put(TradeExpress.EXPRESS_ID, express_id);
        SqlPara sqlPara = Db.getSqlPara("trade_express.findByExpress_id", sqlMap);

        logSql("trade_express", "findByExpress_id", sqlPara);
        
        List<TradeExpress> tradeExpressList = new TradeExpress().find(sqlPara.getSql(), sqlPara.getPara());
        if (tradeExpressList.size() == 0) {
            return null;
        } else {
            return tradeExpressList.get(0);
        }
    }

    public Boolean save(String trade_id, String express_id, String system_create_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(TradeExpress.TRADE_ID, trade_id);
        sqlMap.put(TradeExpress.EXPRESS_ID, express_id);
        sqlMap.put(TradeExpress.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(TradeExpress.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(TradeExpress.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(TradeExpress.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(TradeExpress.SYSTEM_VERSION, 0);
        sqlMap.put(TradeExpress.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("trade_express.save", sqlMap);

        logSql("trade_express", "save", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByTrade_id(String trade_id, String system_update_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(TradeExpress.TRADE_ID, trade_id);
        sqlMap.put(TradeExpress.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(TradeExpress.SYSTEM_UPDATE_TIME, new Date());
        SqlPara sqlPara = Db.getSqlPara("trade_express.deleteByTrade_id", sqlMap);

        logSql("trade_express", "deleteByTrade_id", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }
    
    public Boolean deleteByTrade_idAndExpress_id(String trade_id, String express_id, String system_update_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(TradeExpress.TRADE_ID, trade_id);
        sqlMap.put(TradeExpress.EXPRESS_ID, express_id);
        sqlMap.put(TradeExpress.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(TradeExpress.SYSTEM_UPDATE_TIME, new Date());
        SqlPara sqlPara = Db.getSqlPara("trade_express.deleteByTrade_idAndExpress_id", sqlMap);
        
        logSql("trade_express", "deleteByTrade_idAndExpress_id", sqlPara);
        
        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}