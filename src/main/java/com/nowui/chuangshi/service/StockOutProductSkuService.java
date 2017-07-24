package com.nowui.chuangshi.service;

import java.util.List;

import com.nowui.chuangshi.cache.StockOutProductSkuCache;
import com.nowui.chuangshi.model.StockOutProductSku;

public class StockOutProductSkuService extends Service {

    private StockOutProductSkuCache stockOutProductSkuCache = new StockOutProductSkuCache();


    public List<StockOutProductSku> listByStock_out_id(String stock_out_id) {
        return stockOutProductSkuCache.listByStock_out_id(stock_out_id);
    }

    public Boolean save(String stock_out_id, String product_sku_id, Integer product_sku_quantity, String system_create_user_id) {
        return stockOutProductSkuCache.save(stock_out_id, product_sku_id, product_sku_quantity, system_create_user_id);
    }

    public Boolean deleteByStock_out_idAndSystem_update_user_idValidateSystem_version(String stock_out_id, String system_update_user_id, Integer system_version) {
        return stockOutProductSkuCache.deleteByStock_out_idAndSystem_update_user_idValidateSystem_version(stock_out_id, system_update_user_id, system_version);
    }

}