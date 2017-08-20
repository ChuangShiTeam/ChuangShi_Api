package com.nowui.chuangshi.api.jiangling.dao;

import com.nowui.chuangshi.api.jiangling.model.JianglingCustomer;
import com.nowui.chuangshi.common.dao.Dao;

public class JianglingCustomerDao extends Dao {

    public JianglingCustomerDao() {
        setModel(new JianglingCustomer());
    }

}