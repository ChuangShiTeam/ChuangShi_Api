package com.nowui.chuangshi.service;

import java.util.List;

import com.nowui.chuangshi.cache.StockProductSkuCache;
import com.nowui.chuangshi.model.StockProductSku;

public class StockProductSkuService extends Service {

    private StockProductSkuCache stockProductSkuCache = new StockProductSkuCache();

    public List<StockProductSku> listAndProduct_nameByStock_id(String stock_id) {
        return stockProductSkuCache.listAndProduct_nameByStock_id(stock_id);
    }

    public Boolean save(String stock_id, String product_sku_id, Integer product_sku_quantity, String system_create_user_id) {
        return stockProductSkuCache.save(stock_id, product_sku_id, product_sku_quantity, system_create_user_id);
    }
    
    public Boolean batchSave(List<StockProductSku> stockProductSkuList) {
        return stockProductSkuCache.batchSave(stockProductSkuList);
    }
    
    public Boolean deleteByStock_idAndSystem_update_user_idValidateSystem_version(String stock_id, String system_update_user_id, Integer system_version) {
        return stockProductSkuCache.deleteByStock_idAndSystem_update_user_idValidateSystem_version(stock_id, system_update_user_id, system_version);
    }

}