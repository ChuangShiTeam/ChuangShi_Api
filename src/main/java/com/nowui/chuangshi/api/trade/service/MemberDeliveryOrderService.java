package com.nowui.chuangshi.api.trade.service;

import com.nowui.chuangshi.api.trade.cache.MemberDeliveryOrderCache;
import com.nowui.chuangshi.common.service.Service;

public class MemberDeliveryOrderService extends Service {

    public static final MemberDeliveryOrderService me = new MemberDeliveryOrderService();

    public MemberDeliveryOrderService() {
        setCache(new MemberDeliveryOrderCache());
    }

}