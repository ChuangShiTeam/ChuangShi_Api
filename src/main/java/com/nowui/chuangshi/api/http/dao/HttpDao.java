package com.nowui.chuangshi.api.http.dao;

import com.nowui.chuangshi.api.http.model.Http;
import com.nowui.chuangshi.common.dao.Dao;

public class HttpDao extends Dao {

    public HttpDao() {
        setModel(new Http());
    }

}