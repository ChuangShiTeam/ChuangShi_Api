package com.nowui.chuangshi.service;

import java.math.BigDecimal;
import java.util.List;

import com.nowui.chuangshi.cache.TradeProductSkuCache;
import com.nowui.chuangshi.model.TradeProductSku;

public class TradeProductSkuService extends Service {

    private TradeProductSkuCache tradeProductSkuCache = new TradeProductSkuCache();

    public List<TradeProductSku> listByTrade_id(String trade_id) {
        return tradeProductSkuCache.listByTrade_id(trade_id);
    }

    public Boolean save(String trade_id, String product_sku_id, String product_snap_id, Integer product_sku_quantity, BigDecimal product_sku_amount, String system_create_user_id) {
        return tradeProductSkuCache.save(trade_id, product_sku_id, product_snap_id, product_sku_quantity, product_sku_amount, system_create_user_id);
    }

    public Boolean deleteByTrade_idAndSystem_update_user_idValidateSystem_version(String trade_id, String system_update_user_id, Integer system_version) {
        return tradeProductSkuCache.deleteByTrade_idAndSystem_update_user_idValidateSystem_version(trade_id, system_update_user_id, system_version);
    }

}