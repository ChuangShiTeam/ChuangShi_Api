package com.nowui.chuangshi.api.sql.dao;

import com.nowui.chuangshi.api.sql.model.Sql;
import com.nowui.chuangshi.common.dao.Dao;

public class SqlDao extends Dao {

    public SqlDao() {
        setModel(new Sql());
    }

}