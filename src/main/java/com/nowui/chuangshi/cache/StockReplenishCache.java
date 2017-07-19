package com.nowui.chuangshi.cache;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.StockReplenishDao;
import com.nowui.chuangshi.model.StockReplenish;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.Date;
import java.util.List;

public class StockReplenishCache extends Cache {

    public static final String STOCK_REPLENISH_BY_STOCK_REPLENISH_ID_CACHE = "stock_replenish_by_stock_replenish_id_cache";
    public static final String STOCK_REPLENISH_BY_STOCK_REPLENISH_ID_AND_STOCK_REPLENISH_TYPE_CACHE = "stock_replenish_by_stock_replenish_id_and_stock_replenish_type_cache";

    private StockReplenishDao stockReplenishDao = new StockReplenishDao();

    public Integer countByApp_idAndStock_replenish_typeOrLikeUser_name(String app_id, String stock_replenish_type, String user_name) {
        return stockReplenishDao.countByApp_idAndStock_replenish_typeOrLikeUser_name(app_id, stock_replenish_type, user_name);
    }

    public Integer countByOrApp_idAndStock_replenish_typeOrLikeUser_name(String app_id, String stock_replenish_type, String user_name) {
        return stockReplenishDao.countByOrApp_idAndStock_replenish_typeOrLikeUser_name(app_id, stock_replenish_type, user_name);
    }

    public List<StockReplenish> listByApp_idAndStock_replenish_typeAndSystem_create_timeAndLimit(String app_id, String stock_replenish_type, Date system_create_time, int m, int n) {
        List<StockReplenish> stock_replenishList = stockReplenishDao.listByApp_idAndStock_replenish_typeAndSystem_create_timeAndLimit(app_id, stock_replenish_type, system_create_time, m, n);

        for (StockReplenish stock_replenish : stock_replenishList) {
            stock_replenish.put(findByStock_replenish_idAndStock_replenish_type(stock_replenish.getStock_replenish_id(), stock_replenish_type));
        }

        return stock_replenishList; 
    }

    public List<StockReplenish> listByApp_idAndStock_replenish_typeOrLikeUser_nameAndLimit(String app_id, String stock_replenish_type, String user_name, int m, int n) {
        List<StockReplenish> stock_replenishList = stockReplenishDao.listByApp_idAndStock_replenish_typeOrLikeUser_nameAndLimit(app_id, stock_replenish_type, user_name, m, n);

        for (StockReplenish stock_replenish : stock_replenishList) {
        	stock_replenish.put(findByStock_replenish_idAndStock_replenish_type(stock_replenish.getStock_replenish_id(), stock_replenish_type));
        }

        return stock_replenishList;
    }

    public List<StockReplenish> listByOrApp_idAndStock_replenish_typeOrLikeUser_nameAndLimit(String app_id, String stock_replenish_type, String user_name, int m, int n) {
        List<StockReplenish> stock_replenishList = stockReplenishDao.listByOrApp_idAndStock_replenish_typeOrLikeUser_nameAndLimit(app_id, stock_replenish_type, user_name, m, n);

        for (StockReplenish stock_replenish : stock_replenishList) {
        	stock_replenish.put(findByStock_replenish_idAndStock_replenish_type(stock_replenish.getStock_replenish_id(), stock_replenish_type));
        }

        return stock_replenishList;
    }

    public StockReplenish findByStock_replenish_id(String stock_replenish_id) {
        StockReplenish stock_replenish = CacheUtil.get(STOCK_REPLENISH_BY_STOCK_REPLENISH_ID_CACHE, stock_replenish_id);

        if (stock_replenish == null) {
            stock_replenish = stockReplenishDao.findByStock_replenish_id(stock_replenish_id);

            CacheUtil.put(STOCK_REPLENISH_BY_STOCK_REPLENISH_ID_CACHE, stock_replenish_id, stock_replenish);
        }

        return stock_replenish;
    }
    
    public StockReplenish findByStock_replenish_idAndStock_replenish_type(String stock_replenish_id, String stock_replenish_type) {
        StockReplenish stock_replenish = CacheUtil.get(STOCK_REPLENISH_BY_STOCK_REPLENISH_ID_AND_STOCK_REPLENISH_TYPE_CACHE, stock_replenish_id);

        if (stock_replenish == null) {
            stock_replenish = stockReplenishDao.findByStock_replenish_idAndStock_replenish_type(stock_replenish_id, stock_replenish_type);

            CacheUtil.put(STOCK_REPLENISH_BY_STOCK_REPLENISH_ID_AND_STOCK_REPLENISH_TYPE_CACHE, stock_replenish_id, stock_replenish);
        }

        return stock_replenish;
    }

    public Boolean save(String stock_replenish_id, String app_id, String object_id, String stock_replenish_type, Integer stock_replenish_quantity, String stock_replenish_status, String system_create_user_id) {
        return stockReplenishDao.save(stock_replenish_id, app_id, object_id, stock_replenish_type, stock_replenish_quantity, stock_replenish_status, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String stock_replenish_id, String object_id, String stock_replenish_type, Integer stock_replenish_quantity, String stock_replenish_status, String system_update_user_id, Integer system_version) {
        StockReplenish stock_replenish = findByStock_replenish_id(stock_replenish_id);
        if (!stock_replenish.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = stockReplenishDao.update(stock_replenish_id, object_id, stock_replenish_type, stock_replenish_quantity, stock_replenish_status, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(STOCK_REPLENISH_BY_STOCK_REPLENISH_ID_CACHE, stock_replenish_id);
            CacheUtil.remove(STOCK_REPLENISH_BY_STOCK_REPLENISH_ID_AND_STOCK_REPLENISH_TYPE_CACHE, stock_replenish_id);
        }

        return result;
    }

    public Boolean deleteByStock_replenish_idAndSystem_update_user_idValidateSystem_version(String stock_replenish_id, String system_update_user_id, Integer system_version) {
        StockReplenish stock_replenish = findByStock_replenish_id(stock_replenish_id);
        if (!stock_replenish.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = stockReplenishDao.deleteByStock_replenish_idAndSystem_version(stock_replenish_id, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(STOCK_REPLENISH_BY_STOCK_REPLENISH_ID_CACHE, stock_replenish_id);
            CacheUtil.remove(STOCK_REPLENISH_BY_STOCK_REPLENISH_ID_AND_STOCK_REPLENISH_TYPE_CACHE, stock_replenish_id);
        }

        return result;
    }

}