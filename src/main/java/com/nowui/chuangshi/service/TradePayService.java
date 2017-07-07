package com.nowui.chuangshi.service;

import com.nowui.chuangshi.cache.TradePayCache;
import com.nowui.chuangshi.model.TradePay;

import java.util.Date;
import java.util.List;

public class TradePayService extends Service {

    private TradePayCache tradePayCache = new TradePayCache();

    public Integer countByApp_idOrLikeTrade_pay_name(String app_id, String trade_pay_name) {
        return tradePayCache.countByApp_idOrLikeTrade_pay_name(app_id, trade_pay_name);
    }

    public Integer countByOrApp_idOrLikeTrade_pay_name(String app_id, String trade_pay_name) {
        return tradePayCache.countByOrApp_idOrLikeTrade_pay_name(app_id, trade_pay_name);
    }

    public List<TradePay> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        return tradePayCache.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);
    }

    public List<TradePay> listByApp_idOrLikeTrade_pay_nameAndLimit(String app_id, String trade_pay_name, int m, int n) {
        return tradePayCache.listByApp_idOrLikeTrade_pay_nameAndLimit(app_id, trade_pay_name, m, n);
    }

    public List<TradePay> listByOrApp_idOrLikeTrade_pay_nameAndLimit(String app_id, String trade_pay_name, int m, int n) {
        return tradePayCache.listByOrApp_idOrLikeTrade_pay_nameAndLimit(app_id, trade_pay_name, m, n);
    }

    public TradePay findByTrade_pay_id(String trade_pay_id) {
        return tradePayCache.findByTrade_pay_id(trade_pay_id);
    }

    public Boolean save(String trade_id, String trade_pay_type, String trade_pay_number, String trade_pay_account, String trade_pay_time, String trade_pay_result, String system_create_user_id) {
        return tradePayCache.save(trade_id, trade_pay_type, trade_pay_number, trade_pay_account, trade_pay_time, trade_pay_result, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String trade_pay_id, String trade_id, String trade_pay_type, String trade_pay_number, String trade_pay_account, String trade_pay_time, String trade_pay_result, String system_update_user_id, Integer system_version) {
        return tradePayCache.updateValidateSystem_version(trade_pay_id, trade_id, trade_pay_type, trade_pay_number, trade_pay_account, trade_pay_time, trade_pay_result, system_update_user_id, system_version);
    }

    public Boolean deleteByTrade_pay_idAndSystem_update_user_idValidateSystem_version(String trade_pay_id, String system_update_user_id, Integer system_version) {
        return tradePayCache.deleteByTrade_pay_idAndSystem_update_user_idValidateSystem_version(trade_pay_id, system_update_user_id, system_version);
    }

}