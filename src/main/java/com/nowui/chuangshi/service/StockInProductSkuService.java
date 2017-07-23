package com.nowui.chuangshi.service;

import com.nowui.chuangshi.cache.StockInProductSkuCache;
import com.nowui.chuangshi.model.StockInProductSku;

import java.util.Date;
import java.util.List;

public class StockInProductSkuService extends Service {

    private StockInProductSkuCache stockInProductSkuCache = new StockInProductSkuCache();

    public Integer countByApp_idOrLikeStock_in_product_sku_name(String app_id, String stock_in_product_sku_name) {
        return stockInProductSkuCache.countByApp_idOrLikeStock_in_product_sku_name(app_id, stock_in_product_sku_name);
    }

    public Integer countByOrApp_idOrLikeStock_in_product_sku_name(String app_id, String stock_in_product_sku_name) {
        return stockInProductSkuCache.countByOrApp_idOrLikeStock_in_product_sku_name(app_id, stock_in_product_sku_name);
    }

    public List<StockInProductSku> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        return stockInProductSkuCache.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);
    }

    public List<StockInProductSku> listByApp_idOrLikeStock_in_product_sku_nameAndLimit(String app_id, String stock_in_product_sku_name, int m, int n) {
        return stockInProductSkuCache.listByApp_idOrLikeStock_in_product_sku_nameAndLimit(app_id, stock_in_product_sku_name, m, n);
    }

    public List<StockInProductSku> listByOrApp_idOrLikeStock_in_product_sku_nameAndLimit(String app_id, String stock_in_product_sku_name, int m, int n) {
        return stockInProductSkuCache.listByOrApp_idOrLikeStock_in_product_sku_nameAndLimit(app_id, stock_in_product_sku_name, m, n);
    }

    public StockInProductSku findByStock_in_product_sku_id(String stock_in_product_sku_id) {
        return stockInProductSkuCache.findByStock_in_product_sku_id(stock_in_product_sku_id);
    }

    public Boolean save(String stock_in_id, String product_sku_id, Integer product_sku_quantity, String system_create_user_id) {
        return stockInProductSkuCache.save(stock_in_id, product_sku_id, product_sku_quantity, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String stock_in_product_sku_id, String stock_in_id, String product_sku_id, Integer product_sku_quantity, String system_update_user_id, Integer system_version) {
        return stockInProductSkuCache.updateValidateSystem_version(stock_in_product_sku_id, stock_in_id, product_sku_id, product_sku_quantity, system_update_user_id, system_version);
    }

    public Boolean deleteByStock_in_product_sku_idAndSystem_update_user_idValidateSystem_version(String stock_in_product_sku_id, String system_update_user_id, Integer system_version) {
        return stockInProductSkuCache.deleteByStock_in_product_sku_idAndSystem_update_user_idValidateSystem_version(stock_in_product_sku_id, system_update_user_id, system_version);
    }

}