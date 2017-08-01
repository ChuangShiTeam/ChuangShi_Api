package com.nowui.chuangshi.service;

import com.nowui.chuangshi.cache.StockCache;
import com.nowui.chuangshi.model.Stock;

import java.util.Date;
import java.util.List;

public class StockService extends Service {

    private StockCache stockCache = new StockCache();

    public Integer countByApp_idOrWarehouse_idOrStock_typeOrLikeStock_batchOrLikeProduct_nameOrLikeUser_name(String app_id, String warehouse_id, String stock_type, String stock_batch, String product_name, String user_name) {
        return stockCache.countByApp_idOrWarehouse_idOrStock_typeOrLikeStock_batchOrLikeProduct_nameOrLikeUser_name(app_id, warehouse_id, stock_type, stock_batch, product_name, user_name);
    }

    public Integer countByOrApp_idOrWarehouse_idOrStock_typeOrLikeStock_batchOrLikeProduct_nameOrLikeUser_name(String app_id, String warehouse_id, String stock_type, String stock_batch, String product_name, String user_name) {
        return stockCache.countByOrApp_idOrWarehouse_idOrStock_typeOrLikeStock_batchOrLikeProduct_nameOrLikeUser_name(app_id, warehouse_id, stock_type, stock_batch, product_name, user_name);
    }
    
    public Integer sumQuantityByApp_idOrWarehouse_idAndObject_idAndProduct_sku_id(String app_id, String warehouse_id, String object_id, String product_sku_id) {
    	return stockCache.sumQuantityByApp_idOrWarehouse_idAndObject_idAndProduct_sku_id(app_id, warehouse_id, object_id, product_sku_id);
    }
    
    public Integer sumQuantityByObject_id(String object_id) {
    	return stockCache.sumQuantityByObject_id(object_id);
    }

    public List<Stock> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        return stockCache.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);
    }

    public List<Stock> listByApp_idOrWarehouse_idOrStock_typeOrLikeStock_batchOrLikeProduct_nameOrLikeUser_nameAndLimit(String app_id, String warehouse_id, String stock_type, String stock_batch, String product_name, String user_name, int m, int n) {
        return stockCache.listByApp_idOrWarehouse_idOrStock_typeOrLikeStock_batchOrLikeProduct_nameOrLikeUser_nameAndLimit(app_id, warehouse_id, stock_type, stock_batch, product_name, user_name, m, n);
    }

    public List<Stock> listByOrApp_idOrWarehouse_idOrStock_typeOrLikeStock_batchOrLikeProduct_nameOrLikeUser_nameAndLimit(String app_id, String warehouse_id, String stock_type, String stock_batch, String product_name, String user_name, int m, int n) {
        return stockCache.listByOrApp_idOrWarehouse_idOrStock_typeOrLikeStock_batchOrLikeProduct_nameOrLikeUser_nameAndLimit(app_id, warehouse_id, stock_type, stock_batch, product_name, user_name, m, n);
    }

    public Stock findByStock_id(String stock_id) {
        return stockCache.findByStock_id(stock_id);
    }
    
    public Stock findByWarehouse_idAndObject_idAndProduct_sku_id(String warehouse_id, String object_id, String product_sku_id) {
        return stockCache.findByWarehouse_idAndObject_idAndProduct_sku_id(warehouse_id, object_id, product_sku_id);
    }

    public Boolean save(String stock_id, String app_id, String warehouse_id, String object_id, String stock_batch, String stock_type, String product_category_id, String product_id, String product_sku_id, Integer stock_quantity, String system_create_user_id) {
        return stockCache.save(stock_id, app_id, warehouse_id, object_id, stock_batch, stock_type, product_category_id, product_id, product_sku_id, stock_quantity, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String stock_id, String warehouse_id, String object_id, String stock_batch, String stock_type, String product_category_id, String product_id, String product_sku_id, Integer stock_quantity, String system_update_user_id, Integer system_version) {
        return stockCache.updateValidateSystem_version(stock_id, warehouse_id, object_id, stock_batch, stock_type, product_category_id, product_id, product_sku_id, stock_quantity, system_update_user_id, system_version);
    }

    public Boolean deleteByStock_idAndSystem_update_user_idValidateSystem_version(String stock_id, String system_update_user_id, Integer system_version) {
        return stockCache.deleteByStock_idAndSystem_update_user_idValidateSystem_version(stock_id, system_update_user_id, system_version);
    }
    
    public Boolean batchUpdate(List<Stock> stockList) {
    	return stockCache.batchUpdate(stockList);
    }
    
    public Boolean batchSave(List<Stock> stockList) {
    	return stockCache.batchSave(stockList);
    }

}