package com.nowui.chuangshi.api.xietong.dao;

import com.nowui.chuangshi.api.xietong.model.XietongAdmissions;
import com.nowui.chuangshi.common.dao.Dao;

public class XietongAdmissionsDao extends Dao {

    public XietongAdmissionsDao() {
        setModel(new XietongAdmissions());
    }

}