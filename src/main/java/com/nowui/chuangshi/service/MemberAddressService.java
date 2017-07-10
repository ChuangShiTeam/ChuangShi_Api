package com.nowui.chuangshi.service;

import java.util.Date;
import java.util.List;

import com.nowui.chuangshi.cache.MemberAddressCache;
import com.nowui.chuangshi.model.MemberAddress;

public class MemberAddressService extends Service {

    private MemberAddressCache memberAddressCache = new MemberAddressCache();

    public Integer countByApp_idOrLikeMember_address_name(String app_id, String member_address_name) {
        return memberAddressCache.countByApp_idOrLikeMember_address_name(app_id, member_address_name);
    }

    public Integer countByOrApp_idOrLikeMember_address_name(String app_id, String member_address_name) {
        return memberAddressCache.countByOrApp_idOrLikeMember_address_name(app_id, member_address_name);
    }

    public List<MemberAddress> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        return memberAddressCache.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);
    }

    public List<MemberAddress> listByApp_idOrLikeMember_address_nameAndLimit(String app_id, String member_address_name, int m, int n) {
        return memberAddressCache.listByApp_idOrLikeMember_address_nameAndLimit(app_id, member_address_name, m, n);
    }

    public List<MemberAddress> listByOrApp_idOrLikeMember_address_nameAndLimit(String app_id, String member_address_name, int m, int n) {
        return memberAddressCache.listByOrApp_idOrLikeMember_address_nameAndLimit(app_id, member_address_name, m, n);
    }

    public MemberAddress findByMember_address_id(String member_address_id) {
        return memberAddressCache.findByMember_address_id(member_address_id);
    }

    /**
     * 根据会员id获取默认地址
     * 
     * @param member_address_id
     * @return
     */
    public MemberAddress findByMember_id(String member_id) {
        MemberAddress memberAddress = new MemberAddress();

        List<MemberAddress> memberAddressList = memberAddressCache.findByMember_id(member_id);
        for (MemberAddress address : memberAddressList) {
            if (address.getMember_delivery_is_default()) {
                memberAddress = address;
            }
        }

        return memberAddress;
    }

    public Boolean save(String member_address_id, String app_id, String member_id, String user_id, String member_address_name, String member_address_tel, String member_address_mobile,
            String member_address_postcode, String member_address_province, String member_address_city, String member_address_area, String member_address_address, Boolean member_delivery_is_default,
            String system_create_user_id) {
        return memberAddressCache.save(member_address_id, app_id, member_id, user_id, member_address_name, member_address_tel, member_address_mobile, member_address_postcode, member_address_province,
                member_address_city, member_address_area, member_address_address, member_delivery_is_default, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String member_address_id, String member_id, String user_id, String member_address_name, String member_address_tel, String member_address_mobile,
            String member_address_postcode, String member_address_province, String member_address_city, String member_address_area, String member_address_address, Boolean member_delivery_is_default,
            String system_update_user_id, Integer system_version) {
        return memberAddressCache.updateValidateSystem_version(member_address_id, member_id, user_id, member_address_name, member_address_tel, member_address_mobile, member_address_postcode,
                member_address_province, member_address_city, member_address_area, member_address_address, member_delivery_is_default, system_update_user_id, system_version);
    }

    public Boolean deleteByMember_address_idAndSystem_update_user_idValidateSystem_version(String member_address_id, String system_update_user_id, Integer system_version) {
        return memberAddressCache.deleteByMember_address_idAndSystem_update_user_idValidateSystem_version(member_address_id, system_update_user_id, system_version);
    }

}