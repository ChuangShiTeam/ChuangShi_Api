package com.nowui.chuangshi.api.xietong.dao;

import com.nowui.chuangshi.api.xietong.model.XietongOrganization;
import com.nowui.chuangshi.common.dao.Dao;

public class XietongOrganizationDao extends Dao {

    public XietongOrganizationDao() {
        setModel(new XietongOrganization());
    }

}