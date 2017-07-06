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

    public Integer countByApp_idOrStock_typeOrStock_actionOrLikeProduct_name(String app_id, String stock_type, String stock_action, String product_name) {
        return stockDao.countByApp_idOrStock_typeOrStock_actionOrLikeProduct_name(app_id, stock_type, stock_action, product_name);
    }

    public Integer countByOrApp_idOrStock_typeOrStock_actionOrLikeProduct_name(String app_id, String stock_type, String stock_action, String product_name) {
        return stockDao.countByOrApp_idOrStock_typeOrStock_actionOrLikeProduct_name(app_id, stock_type, stock_action, product_name);
    }

    public List<Stock> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        List<Stock> stockList = stockDao.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);

        for (Stock stock : stockList) {
            stock.put(findByStock_id(stock.getStock_id()));
        }

        return stockList;
    }

    public List<Stock> listByApp_idOrStock_typeOrStock_actionOrLikeProduct_nameAndLimit(String app_id, String stock_type, String stock_action, String product_name, int m, int n) {
        List<Stock> stockList = stockDao.listByApp_idOrStock_typeOrStock_actionOrLikeProduct_nameAndLimit(app_id, stock_type, stock_action, product_name, m, n);

        for (Stock stock : stockList) {
            stock.put(findByStock_id(stock.getStock_id()));
        }

        return stockList;
    }

    public List<Stock> listByOrApp_idOrStock_typeOrStock_actionOrLikeProduct_nameAndLimit(String app_id, String stock_type, String stock_action, String product_name, int m, int n) {
        List<Stock> stockList = stockDao.listByOrApp_idOrStock_typeOrStock_actionOrLikeProduct_nameAndLimit(app_id, stock_type, stock_action, product_name, m, n);

        for (Stock stock : stockList) {
            stock.put(findByStock_id(stock.getStock_id()));
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

    public Boolean save(String stock_id, String app_id, String product_sku_id, String object_id, String product_name, String product_image, String stock_type, Integer stock_quantity, String stock_action, String stock_status, String system_create_user_id) {
        return stockDao.save(stock_id, app_id, product_sku_id, object_id, product_name, product_image, stock_type, stock_quantity, stock_action, stock_status, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String stock_id, String product_sku_id, String object_id, String product_name, String product_image, String stock_type, Integer stock_quantity, String stock_action, String stock_status, String system_update_user_id, Integer system_version) {
        Stock stock = findByStock_id(stock_id);
        if (!stock.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = stockDao.update(stock_id, product_sku_id, object_id, product_name, product_image, stock_type, stock_quantity, stock_action, stock_status, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(STOCK_BY_STOCK_ID_CACHE, stock_id);
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
        }

        return result;
    }

}