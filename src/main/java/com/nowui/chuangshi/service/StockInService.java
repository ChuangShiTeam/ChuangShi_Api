package com.nowui.chuangshi.service;

import com.nowui.chuangshi.cache.StockInCache;
import com.nowui.chuangshi.model.StockIn;

import java.util.Date;
import java.util.List;

public class StockInService extends Service {

    private StockInCache stockInCache = new StockInCache();

    public Integer countByApp_idOrWarehouse_idAndStock_in_typeOrLikeUser_name(String app_id, String warehouse_id, String stock_in_type, String user_name) {
        return stockInCache.countByApp_idOrWarehouse_idAndStock_in_typeOrLikeUser_name(app_id, warehouse_id, stock_in_type, user_name);
    }

    public Integer countByOrApp_idOrWarehouse_idAndStock_in_typeOrLikeUser_name(String app_id, String warehouse_id, String stock_in_type, String user_name) {
        return stockInCache.countByOrApp_idOrWarehouse_idAndStock_in_typeOrLikeUser_name(app_id, warehouse_id, stock_in_type, user_name);
    }

    public List<StockIn> listByApp_idAndStock_in_typeAndSystem_create_timeAndLimit(String app_id, String stock_in_type, Date system_create_time, int m, int n) {
        return stockInCache.listByApp_idAndStock_in_typeAndSystem_create_timeAndLimit(app_id, stock_in_type, system_create_time, m, n);
    }

    public List<StockIn> listByApp_idOrWarehouse_idAndStock_in_typeOrLikeUser_nameAndLimit(String app_id, String warehouse_id, String stock_in_type, String user_name, int m, int n) {
        return stockInCache.listByApp_idOrWarehouse_idAndStock_in_typeOrLikeUser_nameAndLimit(app_id, warehouse_id, stock_in_type, user_name, m, n);
    }

    public List<StockIn> listByOrApp_idOrWarehouse_idAndStock_in_typeOrLikeUser_nameAndLimit(String app_id, String warehouse_id, String stock_in_type, String user_name, int m, int n) {
        return stockInCache.listByOrApp_idOrWarehouse_idAndStock_in_typeOrLikeUser_nameAndLimit(app_id, warehouse_id, stock_in_type, user_name, m, n);
    }

    public StockIn findByStock_in_id(String stock_in_id) {
        return stockInCache.findByStock_in_id(stock_in_id);
    }

    public Boolean save(String stock_in_id, String app_id, String warehouse_id, String trade_id, String object_id, String stock_in_type, Integer stock_in_quantity, String stock_in_status, String system_create_user_id) {
        return stockInCache.save(stock_in_id, app_id, warehouse_id, trade_id, object_id, stock_in_type, stock_in_quantity, stock_in_status, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String stock_in_id, String warehouse_id, String trade_id, String object_id, String stock_in_type, Integer stock_in_quantity, String stock_in_status, String system_update_user_id, Integer system_version) {
        return stockInCache.updateValidateSystem_version(stock_in_id, warehouse_id, trade_id, object_id, stock_in_type, stock_in_quantity, stock_in_status, system_update_user_id, system_version);
    }

    public Boolean deleteByStock_in_idAndSystem_update_user_idValidateSystem_version(String stock_in_id, String system_update_user_id, Integer system_version) {
        return stockInCache.deleteByStock_in_idAndSystem_update_user_idValidateSystem_version(stock_in_id, system_update_user_id, system_version);
    }

}