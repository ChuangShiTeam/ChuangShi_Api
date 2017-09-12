package com.nowui.chuangshi.api.exception.dao;

import com.nowui.chuangshi.api.exception.model.Exception;
import com.nowui.chuangshi.common.dao.Dao;

public class ExceptionDao extends Dao {

    public ExceptionDao() {
        setModel(new Exception());
    }

}