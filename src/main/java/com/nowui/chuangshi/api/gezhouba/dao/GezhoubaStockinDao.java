package com.nowui.chuangshi.api.gezhouba.dao;

import com.nowui.chuangshi.api.gezhouba.model.GezhoubaStockin;
import com.nowui.chuangshi.common.dao.Dao;

public class GezhoubaStockinDao extends Dao {

    public GezhoubaStockinDao() {
        setModel(new GezhoubaStockin());
    }

}