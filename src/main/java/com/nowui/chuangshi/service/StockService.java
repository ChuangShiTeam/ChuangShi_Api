package com.nowui.chuangshi.service;

import com.nowui.chuangshi.cache.StockCache;
import com.nowui.chuangshi.model.Stock;

import java.util.Date;
import java.util.List;

public class StockService extends Service {

    private StockCache stockCache = new StockCache();

    public Integer countByApp_idOrWarsehouse_idOrStock_typeOrLikeProduct_nameOrLikeUser_name(String app_id, String warsehouse_id, String stock_type, String product_name, String user_name) {
        return stockCache.countByApp_idOrWarsehouse_idOrStock_typeOrLikeProduct_nameOrLikeUser_name(app_id, warsehouse_id, stock_type, product_name, user_name);
    }

    public Integer countByOrApp_idOrWarsehouse_idOrStock_typeOrLikeProduct_nameOrLikeUser_name(String app_id, String warsehouse_id, String stock_type, String product_name, String user_name) {
        return stockCache.countByOrApp_idOrWarsehouse_idOrStock_typeOrLikeProduct_nameOrLikeUser_name(app_id, warsehouse_id, stock_type, product_name, user_name);
    }

    public List<Stock> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        return stockCache.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);
    }

    public List<Stock> listByApp_idOrWarsehouse_idOrStock_typeOrLikeProduct_nameOrLikeUser_nameAndLimit(String app_id, String warsehouse_id, String stock_type, String product_name, String user_name, int m, int n) {
        return stockCache.listByApp_idOrWarsehouse_idOrStock_typeOrLikeProduct_nameOrLikeUser_nameAndLimit(app_id, warsehouse_id, stock_type, product_name, user_name, m, n);
    }

    public List<Stock> listByOrApp_idOrWarsehouse_idOrStock_typeOrLikeProduct_nameOrLikeUser_nameAndLimit(String app_id, String warsehouse_id, String stock_type, String product_name, String user_name, int m, int n) {
        return stockCache.listByOrApp_idOrWarsehouse_idOrStock_typeOrLikeProduct_nameOrLikeUser_nameAndLimit(app_id, warsehouse_id, stock_type, product_name, user_name, m, n);
    }

    public Stock findByStock_id(String stock_id) {
        return stockCache.findByStock_id(stock_id);
    }

    public Boolean save(String stock_id, String app_id, String warsehouse_id, String object_id, String stock_type, String product_category_id, String product_id, String product_sku_id, Integer stock_quantity, String system_create_user_id) {
        return stockCache.save(stock_id, app_id, warsehouse_id, object_id, stock_type, product_category_id, product_id, product_sku_id, stock_quantity, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String stock_id, String warsehouse_id, String object_id, String stock_type, String product_category_id, String product_id, String product_sku_id, Integer stock_quantity, String system_update_user_id, Integer system_version) {
        return stockCache.updateValidateSystem_version(stock_id, warsehouse_id, object_id, stock_type, product_category_id, product_id, product_sku_id, stock_quantity, system_update_user_id, system_version);
    }

    public Boolean deleteByStock_idAndSystem_update_user_idValidateSystem_version(String stock_id, String system_update_user_id, Integer system_version) {
        return stockCache.deleteByStock_idAndSystem_update_user_idValidateSystem_version(stock_id, system_update_user_id, system_version);
    }

}