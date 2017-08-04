package com.nowui.chuangshi.api.bill.dao;

import com.nowui.chuangshi.api.bill.model.Bill;
import com.nowui.chuangshi.common.dao.Dao;

public class BillDao extends Dao {

    public BillDao() {
        setClazz(Bill.class);
    }

}