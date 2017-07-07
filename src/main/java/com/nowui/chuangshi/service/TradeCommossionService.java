package com.nowui.chuangshi.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.nowui.chuangshi.cache.TradeCommossionCache;
import com.nowui.chuangshi.model.TradeCommossion;

public class TradeCommossionService extends Service {

    private TradeCommossionCache tradeCommossionCache = new TradeCommossionCache();

    public Integer countByApp_idOrLikeTrade_commossion_name(String app_id, String trade_commossion_name) {
        return tradeCommossionCache.countByApp_idOrLikeTrade_commossion_name(app_id, trade_commossion_name);
    }

    public Integer countByOrApp_idOrLikeTrade_commossion_name(String app_id, String trade_commossion_name) {
        return tradeCommossionCache.countByOrApp_idOrLikeTrade_commossion_name(app_id, trade_commossion_name);
    }

    public List<TradeCommossion> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        return tradeCommossionCache.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);
    }

    public List<TradeCommossion> listByApp_idOrLikeTrade_commossion_nameAndLimit(String app_id, String trade_commossion_name, int m, int n) {
        return tradeCommossionCache.listByApp_idOrLikeTrade_commossion_nameAndLimit(app_id, trade_commossion_name, m, n);
    }

    public List<TradeCommossion> listByOrApp_idOrLikeTrade_commossion_nameAndLimit(String app_id, String trade_commossion_name, int m, int n) {
        return tradeCommossionCache.listByOrApp_idOrLikeTrade_commossion_nameAndLimit(app_id, trade_commossion_name, m, n);
    }

    public TradeCommossion findByTrade_commossion_id(String trade_commossion_id) {
        return tradeCommossionCache.findByTrade_commossion_id(trade_commossion_id);
    }

    public Boolean save(String trade_id, String product_sku_id, String member_id, String member_name, String member_level_id, String member_level_name, Integer product_sku_commission, BigDecimal product_sku_commission_amount, String system_create_user_id) {
        return tradeCommossionCache.save(trade_id, product_sku_id, member_id, member_name, member_level_id, member_level_name, product_sku_commission, product_sku_commission_amount, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String trade_commossion_id, String trade_id, String product_sku_id, String member_id, String member_name, String member_level_id, String member_level_name, Integer product_sku_commission, BigDecimal product_sku_commission_amount, String system_update_user_id, Integer system_version) {
        return tradeCommossionCache.updateValidateSystem_version(trade_commossion_id, trade_id, product_sku_id, member_id, member_name, member_level_id, member_level_name, product_sku_commission, product_sku_commission_amount, system_update_user_id, system_version);
    }

    public Boolean deleteByTrade_commossion_idAndSystem_update_user_idValidateSystem_version(String trade_commossion_id, String system_update_user_id, Integer system_version) {
        return tradeCommossionCache.deleteByTrade_commossion_idAndSystem_update_user_idValidateSystem_version(trade_commossion_id, system_update_user_id, system_version);
    }

}