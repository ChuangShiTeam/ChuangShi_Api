package com.nowui.chuangshi.api.member.cache;

import com.nowui.chuangshi.api.member.dao.MemberAddressDao;
import com.nowui.chuangshi.api.member.model.MemberAddress;
import com.nowui.chuangshi.common.cache.Cache;

public class MemberAddressCache extends Cache {

    public static final String MEMBER_ADDRESS_ITEM_CACHE = "member_address_item_cache";

    public MemberAddressCache() {
        setDao(new MemberAddressDao());

        setItemCache(MEMBER_ADDRESS_ITEM_CACHE, MemberAddress.MEMBER_ADDRESS_ID);
    }

}