package com.nowui.chuangshi.api.jiangling.dao;

import com.nowui.chuangshi.api.jiangling.model.JianglingNewCustomer;
import com.nowui.chuangshi.common.dao.Dao;

public class JianglingNewCustomerDao extends Dao {

    public JianglingNewCustomerDao() {
        setModel(new JianglingNewCustomer());
    }

}