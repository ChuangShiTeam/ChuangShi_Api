package com.nowui.chuangshi.cache;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.StockInDao;
import com.nowui.chuangshi.model.StockIn;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.Date;
import java.util.List;

public class StockInCache extends Cache {

    public static final String STOCK_IN_BY_STOCK_IN_ID_CACHE = "stock_in_by_stock_in_id_cache";
    public static final String STOCK_IN_BY_STOCK_IN_ID_AND_STOCK_IN_TYPE_CACHE = "stock_in_by_stock_in_id_and_stock_in_type_cache";

    private StockInDao stockInDao = new StockInDao();

    public Integer countByApp_idOrWarehouse_idAndStock_in_typeOrLikeStock_in_batchOrLikeUser_name(String app_id, String warehouse_id, String stock_in_type, String stock_in_batch, String user_name) {
        return stockInDao.countByApp_idOrWarehouse_idAndStock_in_typeOrLikeStock_in_batchOrLikeUser_name(app_id, warehouse_id, stock_in_type, stock_in_batch, user_name);
    }

    public Integer countByOrApp_idOrWarehouse_idAndStock_in_typeOrLikeStock_in_batchOrLikeUser_name(String app_id, String warehouse_id, String stock_in_type, String stock_in_batch, String user_name) {
        return stockInDao.countByOrApp_idOrWarehouse_idAndStock_in_typeOrLikeStock_in_batchOrLikeUser_name(app_id, warehouse_id, stock_in_type, stock_in_batch, user_name);
    }

    public List<StockIn> listByApp_idAndStock_in_typeAndSystem_create_timeAndLimit(String app_id, String stock_in_type, Date system_create_time, int m, int n) {
        List<StockIn> stock_inList = stockInDao.listByApp_idAndStock_in_typeAndSystem_create_timeAndLimit(app_id, stock_in_type, system_create_time, m, n);

        for (StockIn stock_in : stock_inList) {
            stock_in.put(findByStock_in_idAndStock_in_type(stock_in.getStock_in_id(), stock_in_type));
        }

        return stock_inList;
    }

    public List<StockIn> listByApp_idOrWarehouse_idAndStock_in_typeOrLikeStock_in_batchOrLikeUser_nameAndLimit(String app_id, String warehouse_id, String stock_in_type, String stock_in_batch, String user_name, int m, int n) {
        List<StockIn> stock_inList = stockInDao.listByApp_idOrWarehouse_idAndStock_in_typeOrLikeStock_in_batchOrLikeUser_nameAndLimit(app_id, warehouse_id, stock_in_type, stock_in_batch, user_name, m, n);

        for (StockIn stock_in : stock_inList) {
            stock_in.put(findByStock_in_idAndStock_in_type(stock_in.getStock_in_id(), stock_in_type));
        }

        return stock_inList;
    }

    public List<StockIn> listByOrApp_idOrWarehouse_idAndStock_in_typeOrLikeStock_in_batchOrLikeUser_nameAndLimit(String app_id, String warehouse_id, String stock_in_type, String stock_in_batch, String user_name, int m, int n) {
        List<StockIn> stock_inList = stockInDao.listByOrApp_idOrWarehouse_idAndStock_in_typeOrLikeStock_in_batchOrLikeUser_nameAndLimit(app_id, warehouse_id, stock_in_type, stock_in_batch, user_name, m, n);

        for (StockIn stock_in : stock_inList) {
            stock_in.put(findByStock_in_idAndStock_in_type(stock_in.getStock_in_id(), stock_in_type));
        }

        return stock_inList;
    }

    public StockIn findByStock_in_id(String stock_in_id) {
        StockIn stock_in = CacheUtil.get(STOCK_IN_BY_STOCK_IN_ID_CACHE, stock_in_id);

        if (stock_in == null) {
            stock_in = stockInDao.findByStock_in_id(stock_in_id);

            CacheUtil.put(STOCK_IN_BY_STOCK_IN_ID_CACHE, stock_in_id, stock_in);
        }

        return stock_in;
    }
    
    public StockIn findByStock_in_idAndStock_in_type(String stock_in_id, String stock_in_type) {
        StockIn stock_in = CacheUtil.get(STOCK_IN_BY_STOCK_IN_ID_AND_STOCK_IN_TYPE_CACHE, stock_in_id + stock_in_type);
        
        if (stock_in == null) {
            stock_in = stockInDao.findByStock_in_idAndStock_in_type(stock_in_id, stock_in_type);
            
            CacheUtil.put(STOCK_IN_BY_STOCK_IN_ID_AND_STOCK_IN_TYPE_CACHE, stock_in_id + stock_in_type, stock_in);
        }
        
        return stock_in;
    }

    public Boolean save(String stock_in_id, String app_id, String warehouse_id, String purchase_order_id, String object_id, String stock_in_batch, String stock_in_type, Integer stock_in_quantity, String stock_in_status, String system_create_user_id) {
        return stockInDao.save(stock_in_id, app_id, warehouse_id, purchase_order_id, object_id, stock_in_batch, stock_in_type, stock_in_quantity, stock_in_status, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String stock_in_id, String warehouse_id, String purchase_order_id, String object_id, String stock_in_batch, String stock_in_type, Integer stock_in_quantity, String stock_in_status, String system_update_user_id, Integer system_version) {
        StockIn stock_in = findByStock_in_id(stock_in_id);
        if (!stock_in.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = stockInDao.update(stock_in_id, warehouse_id, purchase_order_id, object_id, stock_in_batch, stock_in_type, stock_in_quantity, stock_in_status, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(STOCK_IN_BY_STOCK_IN_ID_CACHE, stock_in_id);
            CacheUtil.remove(STOCK_IN_BY_STOCK_IN_ID_AND_STOCK_IN_TYPE_CACHE, stock_in_id + stock_in.getStock_in_type());
        }

        return result;
    }

    public Boolean deleteByStock_in_idAndSystem_update_user_idValidateSystem_version(String stock_in_id, String system_update_user_id, Integer system_version) {
        StockIn stock_in = findByStock_in_id(stock_in_id);
        if (!stock_in.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = stockInDao.deleteByStock_in_idAndSystem_version(stock_in_id, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(STOCK_IN_BY_STOCK_IN_ID_CACHE, stock_in_id);
            CacheUtil.remove(STOCK_IN_BY_STOCK_IN_ID_AND_STOCK_IN_TYPE_CACHE, stock_in_id + stock_in.getStock_in_type());
        }

        return result;
    }

}