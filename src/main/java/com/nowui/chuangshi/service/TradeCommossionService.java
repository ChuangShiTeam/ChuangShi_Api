package com.nowui.chuangshi.service;

import java.math.BigDecimal;
import java.util.List;

import com.nowui.chuangshi.cache.TradeCommossionCache;
import com.nowui.chuangshi.model.TradeCommossion;

public class TradeCommossionService extends Service {

    private TradeCommossionCache tradeCommossionCache = new TradeCommossionCache();

    public List<TradeCommossion> listByTrade_id(String trade_id) {
        return tradeCommossionCache.listByTrade_id(trade_id);
    }

    public Boolean save(String trade_id, String product_sku_id, String member_id, String member_name, String member_level_id, String member_level_name, Integer product_sku_commission, BigDecimal product_sku_commission_amount, String system_create_user_id) {
        return tradeCommossionCache.save(trade_id, product_sku_id, member_id, member_name, member_level_id, member_level_name, product_sku_commission, product_sku_commission_amount, system_create_user_id);
    }

    public Boolean deleteByTrade_idAndSystem_update_user_idValidateSystem_version(String trade_id, String system_update_user_id, Integer system_version) {
        return tradeCommossionCache.deleteByTrade_idAndSystem_update_user_idValidateSystem_version(trade_id, system_update_user_id, system_version);
    }

}