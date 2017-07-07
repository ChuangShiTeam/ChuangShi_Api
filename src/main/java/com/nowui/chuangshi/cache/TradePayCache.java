package com.nowui.chuangshi.cache;

import java.util.List;

import com.nowui.chuangshi.dao.TradePayDao;
import com.nowui.chuangshi.model.TradePay;
import com.nowui.chuangshi.util.CacheUtil;

public class TradePayCache extends Cache {

    public static final String TRADE_PAY_LIST_BY_TRADE_ID_CACHE = "trade_pay_list_by_trade_id_cache";

    private TradePayDao tradePayDao = new TradePayDao();

    public List<TradePay> listByTrade_id(String tarde_id) {
        List<TradePay> trade_payList = CacheUtil.get(TRADE_PAY_LIST_BY_TRADE_ID_CACHE, tarde_id);

        if (trade_payList == null) {
            trade_payList = tradePayDao.listByTrade_id(tarde_id);

            CacheUtil.put(TRADE_PAY_LIST_BY_TRADE_ID_CACHE, tarde_id, trade_payList);
        }

        return trade_payList;
    }

    public Boolean save(String trade_id, String trade_pay_type, String trade_pay_number, String trade_pay_account, String trade_pay_time, String trade_pay_result, String system_create_user_id) {
        return tradePayDao.save(trade_id, trade_pay_type, trade_pay_number, trade_pay_account, trade_pay_time, trade_pay_result, system_create_user_id);
    }

    public Boolean deleteByTrade_idAndSystem_update_user_idValidateSystem_version(String trade_id, String system_update_user_id, Integer system_version) {

        boolean result = tradePayDao.deleteByTrade_idAndSystem_version(trade_id, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(TRADE_PAY_LIST_BY_TRADE_ID_CACHE, trade_id);
        }

        return result;
    }

}