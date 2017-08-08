package com.nowui.chuangshi.api.stock.dao;

import com.nowui.chuangshi.api.stock.model.Stock;
import com.nowui.chuangshi.common.dao.Dao;

public class StockDao extends Dao {

    public StockDao() {
        setModel(new Stock());
    }

}