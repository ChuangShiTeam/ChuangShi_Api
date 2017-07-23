package com.nowui.chuangshi.cache;

import java.util.Date;
import java.util.List;

import com.jfinal.plugin.redis.Cache;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.StockDao;
import com.nowui.chuangshi.model.Stock;
import com.nowui.chuangshi.util.CacheUtil;

public class StockCache extends Cache {

    public static final String STOCK_BY_STOCK_ID_CACHE = "stock_by_stock_id_cache";
    public static final String STOCK_BY_STOCK_ID_AND_STOCK_TYPE_CACHE = "stock_by_stock_id_and_stock_type_cache";

    private StockDao stockDao = new StockDao();

    public Integer countByApp_idOrWarsehouse_idOrStock_typeOrLikeProduct_nameOrLikeUser_name(String app_id, String warsehouse_id, String stock_type, String product_name, String user_name) {
        return stockDao.countByApp_idOrWarsehouse_idOrStock_typeOrLikeProduct_nameOrLikeUser_name(app_id, warsehouse_id, stock_type, product_name, user_name);
    }

    public Integer countByOrApp_idOrWarsehouse_idOrStock_typeOrLikeProduct_nameOrLikeUser_name(String app_id, String warsehouse_id, String stock_type, String product_name, String user_name) {
        return stockDao.countByOrApp_idOrWarsehouse_idOrStock_typeOrLikeProduct_nameOrLikeUser_name(app_id, warsehouse_id, stock_type, product_name, user_name);
    }

    public List<Stock> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        List<Stock> stockList = stockDao.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);

        for (Stock stock : stockList) {
            stock.put(findByStock_id(stock.getStock_id()));
        }

        return stockList;
    }

    public List<Stock> listByApp_idOrWarsehouse_idOrStock_typeOrLikeProduct_nameOrLikeUser_nameAndLimit(String app_id, String warsehouse_id, String stock_type, String product_name, String user_name, int m, int n) {
        List<Stock> stockList = stockDao.listByApp_idOrWarsehouse_idOrStock_typeOrLikeProduct_nameOrLikeUser_nameAndLimit(app_id, warsehouse_id, stock_type, product_name, user_name, m, n);

        for (Stock stock : stockList) {
            stock.put(findByStock_idAndStock_type(stock.getStock_id(), stock_type));
        }

        return stockList;
    }
    
    public List<Stock> listByOrApp_idOrWarsehouse_idOrStock_typeOrLikeProduct_nameOrLikeUser_nameAndLimit(String app_id, String warsehouse_id, String stock_type, String product_name, String user_name, int m, int n) {
        List<Stock> stockList = stockDao.listByOrApp_idOrWarsehouse_idOrStock_typeOrLikeProduct_nameOrLikeUser_nameAndLimit(app_id, warsehouse_id, stock_type, product_name, user_name, m, n);

        for (Stock stock : stockList) {
            stock.put(findByStock_idAndStock_type(stock.getStock_id(), stock_type));
        }

        return stockList;
    }

    public Stock findByStock_id(String stock_id) {
        Stock stock = CacheUtil.get(STOCK_BY_STOCK_ID_CACHE, stock_id);

        if (stock == null) {
            stock = stockDao.findByStock_id(stock_id);

            CacheUtil.put(STOCK_BY_STOCK_ID_CACHE, stock_id, stock);
        }

        return stock;
    }
    
    public Stock findByStock_idAndStock_type(String stock_id, String stock_type) {
        Stock stock = CacheUtil.get(STOCK_BY_STOCK_ID_AND_STOCK_TYPE_CACHE, stock_id + stock_type);

        if (stock == null) {
            stock = stockDao.findByStock_idAndStock_type(stock_id, stock_type);

            CacheUtil.put(STOCK_BY_STOCK_ID_AND_STOCK_TYPE_CACHE, stock_id + stock_type, stock);
        }

        return stock;
    }

    public Boolean save(String stock_id, String app_id, String warsehouse_id, String object_id, String stock_type, String product_category_id, String product_id, String product_sku_id, Integer stock_quantity, String system_create_user_id) {
        return stockDao.save(stock_id, app_id, warsehouse_id, object_id, stock_type, product_category_id, product_id, product_sku_id, stock_quantity, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String stock_id, String warsehouse_id, String object_id, String stock_type, String product_category_id, String product_id, String product_sku_id, Integer stock_quantity, String system_update_user_id, Integer system_version) {
        Stock stock = findByStock_id(stock_id);
        if (!stock.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = stockDao.update(stock_id, warsehouse_id, object_id, stock_type, product_category_id, product_id, product_sku_id, stock_quantity, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(STOCK_BY_STOCK_ID_CACHE, stock_id);
            CacheUtil.remove(STOCK_BY_STOCK_ID_AND_STOCK_TYPE_CACHE, stock_id + stock.getStock_type());
        }

        return result;
    }

    public Boolean deleteByStock_idAndSystem_update_user_idValidateSystem_version(String stock_id, String system_update_user_id, Integer system_version) {
        Stock stock = findByStock_id(stock_id);
        if (!stock.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = stockDao.deleteByStock_idAndSystem_version(stock_id, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(STOCK_BY_STOCK_ID_CACHE, stock_id);
            CacheUtil.remove(STOCK_BY_STOCK_ID_AND_STOCK_TYPE_CACHE, stock_id + stock.getStock_type());
        }

        return result;
    }

}