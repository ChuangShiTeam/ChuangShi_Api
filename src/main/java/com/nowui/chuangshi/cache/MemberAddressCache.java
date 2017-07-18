package com.nowui.chuangshi.cache;

import java.util.Date;
import java.util.List;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.MemberAddressDao;
import com.nowui.chuangshi.model.MemberAddress;
import com.nowui.chuangshi.util.CacheUtil;

public class MemberAddressCache extends Cache {

    public static final String MEMBER_ADDRESS_BY_MEMBER_ADDRESS_ID_CACHE = "member_address_by_member_address_id_cache";

    public static final String MEMBER_ADDRESS_LIST_BY_MEMBER_ID_CACHE = "member_address_list_by_member_id_cache";

    private MemberAddressDao memberAddressDao = new MemberAddressDao();

    public Integer countByApp_idOrLikeMember_address_name(String app_id, String member_address_name) {
        return memberAddressDao.countByApp_idOrLikeMember_address_name(app_id, member_address_name);
    }

    public Integer countByOrApp_idOrLikeMember_address_name(String app_id, String member_address_name) {
        return memberAddressDao.countByOrApp_idOrLikeMember_address_name(app_id, member_address_name);
    }

    public List<MemberAddress> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m,
            int n) {
        List<MemberAddress> member_addressList = memberAddressDao.listByApp_idAndSystem_create_timeAndLimit(app_id,
                system_create_time, m, n);

        for (MemberAddress member_address : member_addressList) {
            member_address.put(findByMember_address_id(member_address.getMember_address_id()));
        }

        return member_addressList;
    }

    public List<MemberAddress> listByApp_idOrLikeMember_address_nameAndLimit(String app_id, String member_address_name,
            int m, int n) {
        List<MemberAddress> member_addressList = memberAddressDao.listByApp_idOrLikeMember_address_nameAndLimit(app_id,
                member_address_name, m, n);

        for (MemberAddress member_address : member_addressList) {
            member_address.put(findByMember_address_id(member_address.getMember_address_id()));
        }

        return member_addressList;
    }

    public List<MemberAddress> listByOrApp_idOrLikeMember_address_nameAndLimit(String app_id,
            String member_address_name, int m, int n) {
        List<MemberAddress> member_addressList = memberAddressDao
                .listByOrApp_idOrLikeMember_address_nameAndLimit(app_id, member_address_name, m, n);

        for (MemberAddress member_address : member_addressList) {
            member_address.put(findByMember_address_id(member_address.getMember_address_id()));
        }

        return member_addressList;
    }

    public MemberAddress findByMember_address_id(String member_address_id) {
        MemberAddress member_address = CacheUtil.get(MEMBER_ADDRESS_BY_MEMBER_ADDRESS_ID_CACHE, member_address_id);

        if (member_address == null) {
            member_address = memberAddressDao.findByMember_address_id(member_address_id);

            CacheUtil.put(MEMBER_ADDRESS_BY_MEMBER_ADDRESS_ID_CACHE, member_address_id, member_address);
        }

        return member_address;
    }

    public List<MemberAddress> findByMember_id(String member_id) {
        List<MemberAddress> member_addressList = CacheUtil.get(MEMBER_ADDRESS_LIST_BY_MEMBER_ID_CACHE, member_id);

        if (member_addressList == null || member_addressList.size() == 0) {
            member_addressList = memberAddressDao.findByMember_id(member_id);

            CacheUtil.put(MEMBER_ADDRESS_LIST_BY_MEMBER_ID_CACHE, member_id, member_addressList);
        }

        return member_addressList;
    }

    public Boolean save(String member_address_id, String app_id, String member_id, String user_id,
            String member_address_name, String member_address_tel, String member_address_mobile,
            String member_address_postcode, String member_address_province, String member_address_city,
            String member_address_area, String member_address_address, Boolean address_is_default,
            String system_create_user_id) {
        Boolean result = memberAddressDao.save(member_address_id, app_id, member_id, user_id, member_address_name,
                member_address_tel, member_address_mobile, member_address_postcode, member_address_province,
                member_address_city, member_address_area, member_address_address, address_is_default,
                system_create_user_id);
        if (result) {
            CacheUtil.remove(MEMBER_ADDRESS_LIST_BY_MEMBER_ID_CACHE, member_id);
        }
        return result;
    }

    public Boolean updateValidateSystem_version(String member_address_id, String member_id, String user_id,
            String member_address_name, String member_address_tel, String member_address_mobile,
            String member_address_postcode, String member_address_province, String member_address_city,
            String member_address_area, String member_address_address, Boolean address_is_default,
            String system_update_user_id, Integer system_version) {
        MemberAddress member_address = findByMember_address_id(member_address_id);
        if (!member_address.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = memberAddressDao.update(member_address_id, member_id, user_id, member_address_name,
                member_address_tel, member_address_mobile, member_address_postcode, member_address_province,
                member_address_city, member_address_area, member_address_address, address_is_default,
                system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(MEMBER_ADDRESS_BY_MEMBER_ADDRESS_ID_CACHE, member_address_id);
            CacheUtil.remove(MEMBER_ADDRESS_LIST_BY_MEMBER_ID_CACHE, member_id);
        }

        return result;
    }

    public Boolean deleteByMember_address_idAndSystem_update_user_idValidateSystem_version(String member_address_id,
            String system_update_user_id, Integer system_version) {
        MemberAddress member_address = findByMember_address_id(member_address_id);
        if (!member_address.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = memberAddressDao.deleteByMember_address_idAndSystem_version(member_address_id,
                system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(MEMBER_ADDRESS_BY_MEMBER_ADDRESS_ID_CACHE, member_address_id);
        }

        return result;
    }

    public Boolean batchUpdate(List<MemberAddress> memberAddressList, String member_id) {
        boolean result = memberAddressDao.batchUpdate(memberAddressList);

        if (result) {
            CacheUtil.remove(MEMBER_ADDRESS_LIST_BY_MEMBER_ID_CACHE, member_id);

            for (MemberAddress memberAddress : memberAddressList) {
                CacheUtil.remove(MEMBER_ADDRESS_BY_MEMBER_ADDRESS_ID_CACHE, memberAddress.getMember_address_id());
            }
        }

        return result;
    }

}