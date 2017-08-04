package com.nowui.chuangshi.api.bill.service;

import com.nowui.chuangshi.api.bill.cache.BillCache;
import com.nowui.chuangshi.common.service.Service;

public class BillService extends Service {

    public static final BillService me = new BillService();

    public BillService() {
        setCache(new BillCache());
    }

}