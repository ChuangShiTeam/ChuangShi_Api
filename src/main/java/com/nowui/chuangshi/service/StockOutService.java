package com.nowui.chuangshi.service;

import com.nowui.chuangshi.cache.StockOutCache;
import com.nowui.chuangshi.model.StockOut;

import java.util.Date;
import java.util.List;

public class StockOutService extends Service {

    private StockOutCache stockOutCache = new StockOutCache();

    public Integer countByApp_idOrWarehouse_idAndStock_out_typeOrLikeUser_name(String app_id, String warehouse_id, String stock_out_type, String user_name) {
        return stockOutCache.countByApp_idOrWarehouse_idAndStock_out_typeOrLikeUser_name(app_id, warehouse_id, stock_out_type, user_name);
    }

    public Integer countByOrApp_idOrWarehouse_idAndStock_out_typeOrLikeUser_name(String app_id, String warehouse_id, String stock_out_type, String user_name) {
        return stockOutCache.countByOrApp_idOrWarehouse_idAndStock_out_typeOrLikeUser_name(app_id, warehouse_id, stock_out_type, user_name);
    }

    public List<StockOut> listByApp_idAndStock_out_typeAndSystem_create_timeAndLimit(String app_id, String stock_out_type, Date system_create_time, int m, int n) {
        return stockOutCache.listByApp_idAndStock_out_typeAndSystem_create_timeAndLimit(app_id, stock_out_type, system_create_time, m, n);
    }

    public List<StockOut> listByApp_idOrWarehouse_idAndStock_out_typeOrLikeUser_nameAndLimit(String app_id, String warehouse_id, String stock_out_type, String user_name, int m, int n) {
        return stockOutCache.listByApp_idOrWarehouse_idAndStock_out_typeOrLikeUser_nameAndLimit(app_id, warehouse_id, stock_out_type, user_name, m, n);
    }

    public List<StockOut> listByOrApp_idOrWarehouse_idAndStock_out_typeOrLikeUser_nameAndLimit(String app_id, String warehouse_id, String stock_out_type, String user_name, int m, int n) {
        return stockOutCache.listByOrApp_idOrWarehouse_idAndStock_out_typeOrLikeUser_nameAndLimit(app_id, warehouse_id, stock_out_type, user_name, m, n);
    }

    public StockOut findByStock_out_id(String stock_out_id) {
        return stockOutCache.findByStock_out_id(stock_out_id);
    }
    
    public StockOut findByStock_out_idAndStock_out_type(String stock_out_id, String stock_out_type) {
        return stockOutCache.findByStock_out_idAndStock_out_type(stock_out_id, stock_out_type);
    }

    public Boolean save(String stock_out_id, String app_id, String warehouse_id, String delivery_order_id, String object_id, String stock_out_type, Integer stock_out_quantity, String stock_out_status, String system_create_user_id) {
        return stockOutCache.save(stock_out_id, app_id, warehouse_id, delivery_order_id, object_id, stock_out_type, stock_out_quantity, stock_out_status, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String stock_out_id, String warehouse_id, String delivery_order_id, String object_id, String stock_out_type, Integer stock_out_quantity, String stock_out_status, String system_update_user_id, Integer system_version) {
        return stockOutCache.updateValidateSystem_version(stock_out_id, warehouse_id, delivery_order_id, object_id, stock_out_type, stock_out_quantity, stock_out_status, system_update_user_id, system_version);
    }

    public Boolean deleteByStock_out_idAndSystem_update_user_idValidateSystem_version(String stock_out_id, String system_update_user_id, Integer system_version) {
        return stockOutCache.deleteByStock_out_idAndSystem_update_user_idValidateSystem_version(stock_out_id, system_update_user_id, system_version);
    }

}