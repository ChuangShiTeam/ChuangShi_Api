package com.nowui.chuangshi.service;

import com.nowui.chuangshi.cache.StockCache;
import com.nowui.chuangshi.model.Stock;

import java.util.Date;
import java.util.List;

public class StockService extends Service {

    private StockCache stockCache = new StockCache();

    public Integer countByApp_idOrStock_typeOrStock_actionOrLikeProduct_name(String app_id, String stock_type, String stock_action, String product_name) {
        return stockCache.countByApp_idOrStock_typeOrStock_actionOrLikeProduct_name(app_id, stock_type, stock_action, product_name);
    }

    public Integer countByOrApp_idOrStock_typeOrStock_actionOrLikeProduct_name(String app_id, String stock_type, String stock_action, String product_name) {
        return stockCache.countByOrApp_idOrStock_typeOrStock_actionOrLikeProduct_name(app_id, stock_type, stock_action, product_name);
    }

    public List<Stock> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        return stockCache.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);
    }

    public List<Stock> listByApp_idOrStock_typeOrStock_actionOrLikeProduct_nameAndLimit(String app_id, String stock_type, String stock_action, String product_name, int m, int n) {
        return stockCache.listByApp_idOrStock_typeOrStock_actionOrLikeProduct_nameAndLimit(app_id, stock_type, stock_action, product_name, m, n);
    }

    public List<Stock> listByOrApp_idOrStock_typeOrStock_actionOrLikeProduct_nameAndLimit(String app_id, String stock_type, String stock_action, String product_name, int m, int n) {
        return stockCache.listByOrApp_idOrStock_typeOrStock_actionOrLikeProduct_nameAndLimit(app_id, stock_type, stock_action, product_name, m, n);
    }

    public Stock findByStock_id(String stock_id) {
        return stockCache.findByStock_id(stock_id);
    }

    public Boolean save(String stock_id, String app_id, String product_sku_id, String object_id, String product_name, String product_image, String stock_type, Integer stock_quantity, String stock_action, String stock_status, String system_create_user_id) {
        return stockCache.save(stock_id, app_id, product_sku_id, object_id, product_name, product_image, stock_type, stock_quantity, stock_action, stock_status, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String stock_id, String product_sku_id, String object_id, String product_name, String product_image, String stock_type, Integer stock_quantity, String stock_action, String stock_status, String system_update_user_id, Integer system_version) {
        return stockCache.updateValidateSystem_version(stock_id, product_sku_id, object_id, product_name, product_image, stock_type, stock_quantity, stock_action, stock_status, system_update_user_id, system_version);
    }

    public Boolean deleteByStock_idAndSystem_update_user_idValidateSystem_version(String stock_id, String system_update_user_id, Integer system_version) {
        return stockCache.deleteByStock_idAndSystem_update_user_idValidateSystem_version(stock_id, system_update_user_id, system_version);
    }

}