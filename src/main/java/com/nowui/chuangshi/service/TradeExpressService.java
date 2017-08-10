package com.nowui.chuangshi.service;

import java.util.List;

import com.nowui.chuangshi.cache.TradeExpressCache;
import com.nowui.chuangshi.model.Express;
import com.nowui.chuangshi.model.TradeExpress;

public class TradeExpressService extends Service {

    private TradeExpressCache tradeExpressCache = new TradeExpressCache();

    public List<Express> listByTrade_id(String trade_id) {
        return tradeExpressCache.listByTrade_id(trade_id);
    }
    
    public TradeExpress findByExpress_id(String express_id) {
    	return tradeExpressCache.findByExpress_id(express_id);
    }

    public Boolean save(String trade_id, String express_id, String system_create_user_id) {
        return tradeExpressCache.save(trade_id, express_id, system_create_user_id);
    }

    public Boolean deleteByTrade_idAndSystem_update_user_id(String trade_id, String system_update_user_id) {
        return tradeExpressCache.deleteByTrade_idAndSystem_update_user_id(trade_id, system_update_user_id);
    }
    
    public Boolean deleteByTrade_idAndExpress_idAndSystem_update_user_id(String trade_id, String express_id,
            String system_update_user_id) {
        return tradeExpressCache.deleteByTrade_idAndExpress_idAndSystem_update_user_id(trade_id, express_id, system_update_user_id);
    }

}