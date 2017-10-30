package com.nowui.chuangshi.api.gezhouba.dao;

import com.nowui.chuangshi.api.gezhouba.model.GezhoubaStockinfo;
import com.nowui.chuangshi.common.dao.Dao;

public class GezhoubaStockinfoDao extends Dao {

    public GezhoubaStockinfoDao() {
        setModel(new GezhoubaStockinfo());
    }

}