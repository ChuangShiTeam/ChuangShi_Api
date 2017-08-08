package com.nowui.chuangshi.api.trade.service;

import com.nowui.chuangshi.api.trade.cache.MemberDeliveryOrderProductSkuCache;
import com.nowui.chuangshi.common.service.Service;

public class MemberDeliveryOrderProductSkuService extends Service {

    public static final MemberDeliveryOrderProductSkuService me = new MemberDeliveryOrderProductSkuService();

    public MemberDeliveryOrderProductSkuService() {
        setCache(new MemberDeliveryOrderProductSkuCache());
    }

}