package com.nowui.chuangshi.service;

import java.util.List;

import com.nowui.chuangshi.cache.TradePayCache;
import com.nowui.chuangshi.model.TradePay;

public class TradePayService extends Service {

    private TradePayCache tradePayCache = new TradePayCache();
    
    public List<TradePay> listByTrade_id(String trade_id) {
        return tradePayCache.listByTrade_id(trade_id);
    }

    public Boolean save(String trade_id, String trade_pay_type, String trade_pay_number, String trade_pay_account, String trade_pay_time, String trade_pay_result, String system_create_user_id) {
        return tradePayCache.save(trade_id, trade_pay_type, trade_pay_number, trade_pay_account, trade_pay_time, trade_pay_result, system_create_user_id);
    }

    public Boolean deleteByTrade_idAndSystem_update_user_idValidateSystem_version(String trade_id, String system_update_user_id, Integer system_version) {
        return tradePayCache.deleteByTrade_idAndSystem_update_user_idValidateSystem_version(trade_id, system_update_user_id, system_version);
    }

}