package com.nowui.chuangshi.api.bill.cache;

import com.nowui.chuangshi.api.bill.dao.BillDao;
import com.nowui.chuangshi.api.bill.model.Bill;
import com.nowui.chuangshi.common.cache.Cache;

public class BillCache extends Cache {

    public static final String BILL_ITEM_CACHE = "bill_item_cache";

    public BillCache() {
        setDao(new BillDao());

        setItemCache(BILL_ITEM_CACHE, Bill.BILL_ID);
    }

}