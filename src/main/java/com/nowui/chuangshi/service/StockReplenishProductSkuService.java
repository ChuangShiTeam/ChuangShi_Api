package com.nowui.chuangshi.service;

import java.util.List;

import com.nowui.chuangshi.cache.StockReplenishProductSkuCache;
import com.nowui.chuangshi.model.StockReplenishProductSku;

public class StockReplenishProductSkuService extends Service {

    private StockReplenishProductSkuCache stockReplenishProductSkuCache = new StockReplenishProductSkuCache();

    public List<StockReplenishProductSku> listByStock_replenish_id(String stock_replenish_id) {
        return stockReplenishProductSkuCache.listByStock_replenish_id(stock_replenish_id);
    }

    public Boolean save(String stock_replenish_id, String product_sku_id, Integer product_sku_quantity, String system_create_user_id) {
        return stockReplenishProductSkuCache.save(stock_replenish_id, product_sku_id, product_sku_quantity, system_create_user_id);
    }

    public Boolean deleteByStock_replenish_idAndSystem_update_user_id(String stock_replenish_id, String system_update_user_id) {
        return stockReplenishProductSkuCache.deleteByStock_replenish_idAndSystem_update_user_id(stock_replenish_id, system_update_user_id);
    }

}