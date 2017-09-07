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
        Cnd cnd = new Cnd();
        cnd.where(MemberAddress.SYSTEM_STATUS, true);
        cnd.and(MemberAddress.APP_ID, app_id);
        cnd.andAllowEmpty(MemberAddress.MEMBER_ADDRESS_NAME, member_address_name);

        Integer count = memberAddressDao.count(cnd);
        return count;
    }

    public List<MemberAddress> adminList(String app_id, String member_address_name, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(MemberAddress.SYSTEM_STATUS, true);
        cnd.and(MemberAddress.APP_ID, app_id);
        cnd.andAllowEmpty(MemberAddress.MEMBER_ADDRESS_NAME, member_address_name);
        cnd.paginate(m, n);

        List<MemberAddress> memberAddressList = memberAddressDao.primaryKeyList(cnd);
        for (MemberAddress memberAddress : memberAddressList) {
            memberAddress.put(find(memberAddress.getMember_address_id()));
        }
        return memberAddressList;
    }

    public List<MemberAddress> memberList(String member_id) {
        Cnd cnd = new Cnd();
        cnd.where(MemberAddress.SYSTEM_STATUS, true);
        cnd.and(MemberAddress.MEMBER_ID, member_id);

        List<MemberAddress> memberAddressList = memberAddressDao.primaryKeyList(cnd);
        for (MemberAddress memberAddress : memberAddressList) {
            memberAddress.put(find(memberAddress.getMember_address_id()));
        }
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

    public Boolean save(MemberAddress memberAddress, String system_create_user_id) {
        Boolean success = memberAddressDao.save(memberAddress, system_create_user_id);
        return success;
    }

    public Boolean update(MemberAddress memberAddress, String member_address_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MemberAddress.SYSTEM_STATUS, true);
        cnd.and(MemberAddress.MEMBER_ADDRESS_ID, member_address_id);
        cnd.and(MemberAddress.SYSTEM_VERSION, system_version);

        Boolean success = memberAddressDao.update(memberAddress, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MEMBER_ADDRESS_ITEM_CACHE, member_address_id);
        }

        return success;
    }

    public Boolean delete(String member_address_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MemberAddress.SYSTEM_STATUS, true);
        cnd.and(MemberAddress.MEMBER_ADDRESS_ID, member_address_id);
        cnd.and(MemberAddress.SYSTEM_VERSION, system_version);

        Boolean success = memberAddressDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MEMBER_ADDRESS_ITEM_CACHE, member_address_id);
        }

        return success;
    }

}