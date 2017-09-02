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
        Integer count = stockDao.count(Cnd.where(Stock.APP_ID, app_id));
        return count;
    }

    public List<Stock> adminList(String app_id, Integer m, Integer n) {
        List<Stock> stockList = stockDao.list(Cnd.where(Stock.APP_ID, app_id).paginate(m, n));
        return stockList;
    }

    public List<Stock> userList(String user_id) {
        List<Stock> stockList = stockDao.list(Cnd.where(Stock.OBJECT_ID, user_id).and(Stock.STOCK_TYPE, StockType.MEMBER.getKey()));
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
        Boolean result = stockDao.save(stock);
        return result;
    }

    public Boolean update(Stock stock, String stock_id, Integer system_version) {
        Boolean result = stockDao.update(stock, Cnd.where(Stock.STOCK_ID, stock_id).and(Stock.SYSTEM_VERSION, system_version));

        if (result) {
            CacheUtil.remove(STOCK_ITEM_CACHE, stock_id);
        }

        return result;
    }

    public Boolean delete(String stock_id, Integer system_version) {
        Boolean result = stockDao.delete(Cnd.where(Stock.STOCK_ID, stock_id).and(Stock.SYSTEM_VERSION, system_version));

        if (result) {
            CacheUtil.remove(STOCK_ITEM_CACHE, stock_id);
        }

        return result;
    }

}