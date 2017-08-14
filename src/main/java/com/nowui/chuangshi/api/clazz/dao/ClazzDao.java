package com.nowui.chuangshi.api.clazz.dao;

import com.nowui.chuangshi.api.clazz.model.Clazz;
import com.nowui.chuangshi.common.dao.Dao;

public class ClazzDao extends Dao {

    public ClazzDao() {
        setModel(new Clazz());
    }

}