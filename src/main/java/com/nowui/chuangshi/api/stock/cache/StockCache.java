package com.nowui.chuangshi.api.stock.cache;

import com.nowui.chuangshi.api.stock.dao.StockDao;
import com.nowui.chuangshi.api.stock.model.Stock;
import com.nowui.chuangshi.common.cache.Cache;

public class StockCache extends Cache {

    public static final String STOCK_ITEM_CACHE = "stock_item_cache";

    public StockCache() {
        setDao(new StockDao());

        setItemCache(STOCK_ITEM_CACHE, Stock.STOCK_ID);
    }

}