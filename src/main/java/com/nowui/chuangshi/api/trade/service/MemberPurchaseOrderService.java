package com.nowui.chuangshi.api.trade.service;

import com.nowui.chuangshi.api.trade.cache.MemberPurchaseOrderCache;
import com.nowui.chuangshi.common.service.Service;

public class MemberPurchaseOrderService extends Service {

    public static final MemberPurchaseOrderService me = new MemberPurchaseOrderService();

    public MemberPurchaseOrderService() {
        setCache(new MemberPurchaseOrderCache());
    }

}