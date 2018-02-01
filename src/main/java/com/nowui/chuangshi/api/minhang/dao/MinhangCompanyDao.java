package com.nowui.chuangshi.api.minhang.dao;

import com.nowui.chuangshi.api.minhang.model.MinhangCompany;
import com.nowui.chuangshi.common.dao.Dao;

public class MinhangCompanyDao extends Dao {

    public MinhangCompanyDao() {
        setModel(new MinhangCompany());
    }

}