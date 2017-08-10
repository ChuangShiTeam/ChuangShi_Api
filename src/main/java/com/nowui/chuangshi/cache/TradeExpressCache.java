package com.nowui.chuangshi.cache;

import java.util.ArrayList;
import java.util.List;

import com.nowui.chuangshi.dao.TradeExpressDao;
import com.nowui.chuangshi.model.Express;
import com.nowui.chuangshi.model.TradeExpress;

public class TradeExpressCache extends Cache {

    private TradeExpressDao tradeExpressDao = new TradeExpressDao();
    private ExpressCache expressCache = new ExpressCache();

    public List<Express> listByTrade_id(String trade_id) {
        List<TradeExpress> trade_expressList = tradeExpressDao.listByTrade_id(trade_id);
        
        List<Express> express_list = new ArrayList<Express>();
        
        for(TradeExpress tradeExpress : trade_expressList) {
            
            express_list.add(expressCache.findByExpress_id(tradeExpress.getExpress_id()));
        }
        
        return express_list;
    }
    
    public TradeExpress findByExpress_id(String express_id) {
    	return tradeExpressDao.findByExpress_id(express_id);
    }

    public Boolean save(String trade_id, String express_id, String system_create_user_id) {
        return tradeExpressDao.save(trade_id, express_id, system_create_user_id);
    }

    public Boolean deleteByTrade_idAndSystem_update_user_id(String trade_id, String system_update_user_id) {
        return tradeExpressDao.deleteByTrade_id(trade_id, system_update_user_id);
    }
    
    public Boolean deleteByTrade_idAndExpress_idAndSystem_update_user_id(String trade_id, String express_id, String system_update_user_id) {
        return tradeExpressDao.deleteByTrade_idAndExpress_id(trade_id, express_id, system_update_user_id);
    }

}