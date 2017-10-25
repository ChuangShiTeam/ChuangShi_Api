package com.nowui.chuangshi.api.uni.dao;

import com.nowui.chuangshi.api.uni.model.UniApply;
import com.nowui.chuangshi.common.dao.Dao;

public class UniApplyDao extends Dao {

    public UniApplyDao() {
        setModel(new UniApply());
    }

}