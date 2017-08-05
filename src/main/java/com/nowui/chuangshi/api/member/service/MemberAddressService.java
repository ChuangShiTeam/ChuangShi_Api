package com.nowui.chuangshi.api.member.service;

import com.nowui.chuangshi.api.member.cache.MemberAddressCache;
import com.nowui.chuangshi.common.service.Service;

public class MemberAddressService extends Service {

    public static final MemberAddressService me = new MemberAddressService();

    public MemberAddressService() {
        setCache(new MemberAddressCache());
    }

}