package com.nowui.chuangshi.api.stock.service;

import com.nowui.chuangshi.api.stock.dao.StockDao;
import com.nowui.chuangshi.api.stock.model.Stock;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.type.StockType;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class StockService extends Service {

    public static final StockService instance = new StockService();
    private final String STOCK_ITEM_CACHE = "stock_item_cache";
    private final StockDao stockDao = new StockDao();

    public Integer adminCount(String app_id) {
        Cnd cnd = Cnd.where(Stock.SYSTEM_STATUS, true);
        cnd.and(Stock.APP_ID, app_id);

        Integer count = stockDao.count(cnd);
        return count;
    }

    public List<Stock> adminList(String app_id, Integer m, Integer n) {
        Cnd cnd = Cnd.where(Stock.SYSTEM_STATUS, true);
        cnd.and(Stock.APP_ID, app_id).paginate(m, n);

        List<Stock> stockList = stockDao.primaryKeyList(cnd);
        for (Stock stock : stockList) {
            stock.put(find(stock.getStock_id()));
        }
        return stockList;
    }

    public List<Stock> userList(String user_id) {
        Cnd cnd = Cnd.where(Stock.SYSTEM_STATUS, true);
        cnd.and(Stock.OBJECT_ID, user_id);
        cnd.and(Stock.STOCK_TYPE, StockType.MEMBER.getKey());

        List<Stock> stockList = stockDao.primaryKeyList(cnd);
        for (Stock stock : stockList) {
            stock.put(find(stock.getStock_id()));
        }
        return stockList;
    }

    public Stock find(String stock_id) {
        Stock stock = CacheUtil.get(STOCK_ITEM_CACHE, stock_id);

        if (stock == null) {
            stock = stockDao.find(stock_id);

            CacheUtil.put(STOCK_ITEM_CACHE, stock_id, stock);
        }

        return stock;
    }

    public Boolean save(Stock stock) {
        Boolean success = stockDao.save(stock);
        return success;
    }

    public Boolean update(Stock stock, String stock_id, Integer system_version) {
        Cnd cnd = Cnd.where(Stock.SYSTEM_STATUS, true);
        cnd.and(Stock.STOCK_ID, stock_id);
        cnd.and(Stock.SYSTEM_VERSION, system_version);

        Boolean success = stockDao.update(stock, cnd);

        if (success) {
            CacheUtil.remove(STOCK_ITEM_CACHE, stock_id);
        }

        return success;
    }

    public Boolean delete(String stock_id, Integer system_version) {
        Cnd cnd = Cnd.where(Stock.SYSTEM_STATUS, true);
        cnd.and(Stock.STOCK_ID, stock_id);
        cnd.and(Stock.SYSTEM_VERSION, system_version);

        Boolean success = stockDao.delete(cnd);

        if (success) {
            CacheUtil.remove(STOCK_ITEM_CACHE, stock_id);
        }

        return success;
    }

}