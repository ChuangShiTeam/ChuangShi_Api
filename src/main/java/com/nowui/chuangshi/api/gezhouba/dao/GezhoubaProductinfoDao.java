package com.nowui.chuangshi.api.gezhouba.dao;

import com.nowui.chuangshi.api.gezhouba.model.GezhoubaProductinfo;
import com.nowui.chuangshi.common.dao.Dao;

public class GezhoubaProductinfoDao extends Dao {

    public GezhoubaProductinfoDao() {
        setModel(new GezhoubaProductinfo());
    }

}