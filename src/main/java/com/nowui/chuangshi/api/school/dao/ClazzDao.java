package com.nowui.chuangshi.api.school.dao;

import com.nowui.chuangshi.api.school.model.Clazz;
import com.nowui.chuangshi.common.dao.Dao;

public class ClazzDao extends Dao {

    public ClazzDao() {
        setModel(new Clazz());
    }

}