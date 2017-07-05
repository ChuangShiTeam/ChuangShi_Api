package com.nowui.chuangshi.cache;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.StockDao;
import com.nowui.chuangshi.model.Stock;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.Date;
import java.util.List;

public class StockCache extends Cache {

    public static final String STOCK_BY_STOCK_ID_CACHE = "stock_by_stock_id_cache";

    private StockDao stockDao = new StockDao();

    public Integer countByApp_idOrLikeStock_type(String app_id, String stock_type, String request_app_id, String request_http_id, String request_user_id) {
        return stockDao.countByApp_idOrLikeStock_type(app_id, stock_type, request_app_id, request_http_id, request_user_id);
    }

    public Integer countByOrApp_idOrLikeStock_type(String app_id, String stock_type, String request_app_id, String request_http_id, String request_user_id) {
        return stockDao.countByOrApp_idOrLikeStock_type(app_id, stock_type, request_app_id, request_http_id, request_user_id);
    }

    public List<Stock> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        List<Stock> stockList = stockDao.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n, request_app_id, request_http_id, request_user_id);

        for (Stock stock : stockList) {
            stock.put(findByStock_id(stock.getStock_id(), request_app_id, request_http_id, request_user_id));
        }

        return stockList;
    }

    public List<Stock> listByApp_idOrLikeStock_typeAndLimit(String app_id, String stock_type, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        List<Stock> stockList = stockDao.listByApp_idOrLikeStock_typeAndLimit(app_id, stock_type, m, n, request_app_id, request_http_id, request_user_id);

        for (Stock stock : stockList) {
            stock.put(findByStock_id(stock.getStock_id(), request_app_id, request_http_id, request_user_id));
        }

        return stockList;
    }

    public List<Stock> listByOrApp_idOrLikeStock_typeAndLimit(String app_id, String stock_type, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        List<Stock> stockList = stockDao.listByOrApp_idOrLikeStock_typeAndLimit(app_id, stock_type, m, n, request_app_id, request_http_id, request_user_id);

        for (Stock stock : stockList) {
            stock.put(findByStock_id(stock.getStock_id(), request_app_id, request_http_id, request_user_id));
        }

        return stockList;
    }

    public Stock findByStock_id(String stock_id, String request_app_id, String request_http_id, String request_user_id) {
        Stock stock = CacheUtil.get(STOCK_BY_STOCK_ID_CACHE, stock_id);

        if (stock == null) {
            stock = stockDao.findByStock_id(stock_id, request_app_id, request_http_id, request_user_id);

            CacheUtil.put(STOCK_BY_STOCK_ID_CACHE, stock_id, stock);
        }

        return stock;
    }

    public Boolean save(String stock_id, String app_id, String product_sku_id, String object_id, String stock_type, Integer stock_quantity, String system_create_user_id, String request_app_id, String request_http_id, String request_user_id) {
        return stockDao.save(stock_id, app_id, product_sku_id, object_id, stock_type, stock_quantity, system_create_user_id, request_app_id, request_http_id, request_user_id);
    }

    public Boolean updateValidateSystem_version(String stock_id, String product_sku_id, String object_id, String stock_type, Integer stock_quantity, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        Stock stock = findByStock_id(stock_id, request_app_id, request_http_id, request_user_id);
        if (!stock.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = stockDao.update(stock_id, product_sku_id, object_id, stock_type, stock_quantity, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);

        if (result) {
            CacheUtil.remove(STOCK_BY_STOCK_ID_CACHE, stock_id);
        }

        return result;
    }

    public Boolean deleteByStock_idAndSystem_update_user_idValidateSystem_version(String stock_id, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        Stock stock = findByStock_id(stock_id, request_app_id, request_http_id, request_user_id);
        if (!stock.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = stockDao.deleteByStock_idAndSystem_version(stock_id, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);

        if (result) {
            CacheUtil.remove(STOCK_BY_STOCK_ID_CACHE, stock_id);
        }

        return result;
    }

}