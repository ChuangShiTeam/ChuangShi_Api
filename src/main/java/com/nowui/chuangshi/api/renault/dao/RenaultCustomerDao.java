package com.nowui.chuangshi.api.renault.dao;

import com.nowui.chuangshi.api.renault.model.RenaultCustomer;
import com.nowui.chuangshi.common.dao.Dao;

public class RenaultCustomerDao extends Dao {

    public RenaultCustomerDao() {
        setModel(new RenaultCustomer());
    }

}