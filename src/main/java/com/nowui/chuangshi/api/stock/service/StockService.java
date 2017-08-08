package com.nowui.chuangshi.api.stock.service;

import com.nowui.chuangshi.api.stock.cache.StockCache;
import com.nowui.chuangshi.common.service.Service;

public class StockService extends Service {

    public static final StockService me = new StockService();

    public StockService() {
        setCache(new StockCache());
    }

}