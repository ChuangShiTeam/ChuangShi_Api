package com.nowui.chuangshi.api.member.service;

import com.nowui.chuangshi.api.member.dao.MemberAddressDao;
import com.nowui.chuangshi.api.member.model.MemberAddress;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class MemberAddressService extends Service {

    public static final MemberAddressService instance = new MemberAddressService();
    private final String MEMBER_ADDRESS_ITEM_CACHE = "member_address_item_cache";
    private final MemberAddressDao memberAddressDao = new MemberAddressDao();

    public Integer adminCount(String app_id, String member_address_name) {
        Integer count = memberAddressDao.count(Cnd.where(MemberAddress.APP_ID, app_id).andAllowEmpty(MemberAddress.MEMBER_ADDRESS_NAME, member_address_name));
        return count;
    }

    public List<MemberAddress> adminList(String app_id, String member_address_name, Integer m, Integer n) {
        List<MemberAddress> memberAddressList = memberAddressDao.list(Cnd.where(MemberAddress.APP_ID, app_id).andAllowEmpty(MemberAddress.MEMBER_ADDRESS_NAME, member_address_name).paginate(m, n));
        return memberAddressList;
    }

    public List<MemberAddress> memberList(String member_id) {
        List<MemberAddress> memberAddressList = memberAddressDao.list(Cnd.where(MemberAddress.MEMBER_ID, member_id));
        return memberAddressList;
    }

    public MemberAddress find(String member_address_id) {
        MemberAddress memberAddress = CacheUtil.get(MEMBER_ADDRESS_ITEM_CACHE, member_address_id);

        if (memberAddress == null) {
            memberAddress = memberAddressDao.find(member_address_id);

            CacheUtil.put(MEMBER_ADDRESS_ITEM_CACHE, member_address_id, memberAddress);
        }

        return memberAddress;
    }

    public Boolean save(MemberAddress memberAddress) {
        Boolean result = memberAddressDao.save(memberAddress);
        return result;
    }

    public Boolean update(MemberAddress memberAddress, String member_address_id, Integer system_version) {
        Boolean result = memberAddressDao.update(memberAddress, Cnd.where(MemberAddress.MEMBER_ADDRESS_ID, member_address_id).and(MemberAddress.SYSTEM_VERSION, system_version));

        if (result) {
            CacheUtil.remove(MEMBER_ADDRESS_ITEM_CACHE, member_address_id);
        }

        return result;
    }

    public Boolean delete(String member_address_id, Integer system_version) {
        Boolean result = memberAddressDao.delete(Cnd.where(MemberAddress.MEMBER_ADDRESS_ID, member_address_id).and(MemberAddress.SYSTEM_VERSION, system_version));

        if (result) {
            CacheUtil.remove(MEMBER_ADDRESS_ITEM_CACHE, member_address_id);
        }

        return result;
    }

}