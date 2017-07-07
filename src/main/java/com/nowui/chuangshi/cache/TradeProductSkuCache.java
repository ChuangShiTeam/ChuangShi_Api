package com.nowui.chuangshi.cache;

import java.math.BigDecimal;
import java.util.List;

import com.nowui.chuangshi.dao.TradeProductSkuDao;
import com.nowui.chuangshi.model.TradeProductSku;
import com.nowui.chuangshi.util.CacheUtil;

public class TradeProductSkuCache extends Cache {

    public static final String TRADE_PRODUCT_SKU_LIST_BY_TRADE_ID_CACHE = "trade_product_sku_list_by_trade_id_cache";

    private TradeProductSkuDao tradeProductSkuDao = new TradeProductSkuDao();

    public List<TradeProductSku> listByTrade_id(String tarde_id) {
        List<TradeProductSku> trade_product_skuList = CacheUtil.get(TRADE_PRODUCT_SKU_LIST_BY_TRADE_ID_CACHE, tarde_id);

        if (trade_product_skuList == null) {
            trade_product_skuList = tradeProductSkuDao.listByTrade_id(tarde_id);

            CacheUtil.put(TRADE_PRODUCT_SKU_LIST_BY_TRADE_ID_CACHE, tarde_id, trade_product_skuList);
        }

        return trade_product_skuList;
    }
    
    public Boolean save(String trade_id, String product_sku_id, String product_snap_id, Integer product_sku_quantity, BigDecimal product_sku_amount, String system_create_user_id) {
        return tradeProductSkuDao.save(trade_id, product_sku_id, product_snap_id, product_sku_quantity, product_sku_amount, system_create_user_id);
    }

    public Boolean deleteByTrade_idAndSystem_update_user_idValidateSystem_version(String trade_id, String system_update_user_id, Integer system_version) {

        boolean result = tradeProductSkuDao.deleteByTrade_idAndSystem_version(trade_id, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(TRADE_PRODUCT_SKU_LIST_BY_TRADE_ID_CACHE, trade_id);
        }
        
        return result;
    }

}