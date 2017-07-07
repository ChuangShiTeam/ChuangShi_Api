package com.nowui.chuangshi.cache;

import java.util.Date;
import java.util.List;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.TradePayDao;
import com.nowui.chuangshi.model.TradePay;
import com.nowui.chuangshi.util.CacheUtil;

public class TradePayCache extends Cache {

    public static final String TRADE_PAY_BY_TRADE_PAY_ID_CACHE = "trade_pay_by_trade_pay_id_cache";

    private TradePayDao tradePayDao = new TradePayDao();

    public Integer countByApp_idOrLikeTrade_pay_name(String app_id, String trade_pay_name) {
        return tradePayDao.countByApp_idOrLikeTrade_pay_name(app_id, trade_pay_name);
    }

    public Integer countByOrApp_idOrLikeTrade_pay_name(String app_id, String trade_pay_name) {
        return tradePayDao.countByOrApp_idOrLikeTrade_pay_name(app_id, trade_pay_name);
    }

    public List<TradePay> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        List<TradePay> trade_payList = tradePayDao.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);

        for (TradePay trade_pay : trade_payList) {
            trade_pay.put(findByTrade_pay_id(trade_pay.getTrade_pay_id()));
        }

        return trade_payList;
    }

    public List<TradePay> listByApp_idOrLikeTrade_pay_nameAndLimit(String app_id, String trade_pay_name, int m, int n) {
        List<TradePay> trade_payList = tradePayDao.listByApp_idOrLikeTrade_pay_nameAndLimit(app_id, trade_pay_name, m, n);

        for (TradePay trade_pay : trade_payList) {
            trade_pay.put(findByTrade_pay_id(trade_pay.getTrade_pay_id()));
        }

        return trade_payList;
    }

    public List<TradePay> listByOrApp_idOrLikeTrade_pay_nameAndLimit(String app_id, String trade_pay_name, int m, int n) {
        List<TradePay> trade_payList = tradePayDao.listByOrApp_idOrLikeTrade_pay_nameAndLimit(app_id, trade_pay_name, m, n);

        for (TradePay trade_pay : trade_payList) {
            trade_pay.put(findByTrade_pay_id(trade_pay.getTrade_pay_id()));
        }

        return trade_payList;
    }

    public TradePay findByTrade_pay_id(String trade_pay_id) {
        TradePay trade_pay = CacheUtil.get(TRADE_PAY_BY_TRADE_PAY_ID_CACHE, trade_pay_id);

        if (trade_pay == null) {
            trade_pay = tradePayDao.findByTrade_pay_id(trade_pay_id);

            CacheUtil.put(TRADE_PAY_BY_TRADE_PAY_ID_CACHE, trade_pay_id, trade_pay);
        }

        return trade_pay;
    }

    public Boolean save(String trade_id, String trade_pay_type, String trade_pay_number, String trade_pay_account, String trade_pay_time, String trade_pay_result, String system_create_user_id) {
        return tradePayDao.save(trade_id, trade_pay_type, trade_pay_number, trade_pay_account, trade_pay_time, trade_pay_result, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String trade_pay_id, String trade_id, String trade_pay_type, String trade_pay_number, String trade_pay_account, String trade_pay_time,
            String trade_pay_result, String system_update_user_id, Integer system_version) {
        TradePay trade_pay = findByTrade_pay_id(trade_pay_id);
        if (!trade_pay.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = tradePayDao.update(trade_pay_id, trade_id, trade_pay_type, trade_pay_number, trade_pay_account, trade_pay_time, trade_pay_result, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(TRADE_PAY_BY_TRADE_PAY_ID_CACHE, trade_pay_id);
        }

        return result;
    }

    public Boolean deleteByTrade_pay_idAndSystem_update_user_idValidateSystem_version(String trade_pay_id, String system_update_user_id, Integer system_version) {
        TradePay trade_pay = findByTrade_pay_id(trade_pay_id);
        if (!trade_pay.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = tradePayDao.deleteByTrade_pay_idAndSystem_version(trade_pay_id, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(TRADE_PAY_BY_TRADE_PAY_ID_CACHE, trade_pay_id);
        }

        return result;
    }

}