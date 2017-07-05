package com.nowui.chuangshi.service;

import com.nowui.chuangshi.cache.MemberAddressCache;
import com.nowui.chuangshi.model.MemberAddress;

import java.util.Date;
import java.util.List;

public class MemberAddressService extends Service {

    private MemberAddressCache memberAddressCache = new MemberAddressCache();

    public Integer countByApp_idOrLikeMember_address_name(String app_id, String member_address_name, String request_app_id, String request_http_id, String request_user_id) {
        return memberAddressCache.countByApp_idOrLikeMember_address_name(app_id, member_address_name, request_app_id, request_http_id, request_user_id);
    }

    public Integer countByOrApp_idOrLikeMember_address_name(String app_id, String member_address_name, String request_app_id, String request_http_id, String request_user_id) {
        return memberAddressCache.countByOrApp_idOrLikeMember_address_name(app_id, member_address_name, request_app_id, request_http_id, request_user_id);
    }

    public List<MemberAddress> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        return memberAddressCache.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n, request_app_id, request_http_id, request_user_id);
    }

    public List<MemberAddress> listByApp_idOrLikeMember_address_nameAndLimit(String app_id, String member_address_name, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        return memberAddressCache.listByApp_idOrLikeMember_address_nameAndLimit(app_id, member_address_name, m, n, request_app_id, request_http_id, request_user_id);
    }

    public List<MemberAddress> listByOrApp_idOrLikeMember_address_nameAndLimit(String app_id, String member_address_name, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        return memberAddressCache.listByOrApp_idOrLikeMember_address_nameAndLimit(app_id, member_address_name, m, n, request_app_id, request_http_id, request_user_id);
    }

    public MemberAddress findByMember_address_id(String member_address_id, String request_app_id, String request_http_id, String request_user_id) {
        return memberAddressCache.findByMember_address_id(member_address_id, request_app_id, request_http_id, request_user_id);
    }

    public Boolean save(String member_address_id, String app_id, String member_id, String user_id, String member_address_name, String member_address_phone, String member_address_province, String member_address_city, String member_address_area, String member_address_street, Boolean member_delivery_is_default, String system_create_user_id, String request_app_id, String request_http_id, String request_user_id) {
        return memberAddressCache.save(member_address_id, app_id, member_id, user_id, member_address_name, member_address_phone, member_address_province, member_address_city, member_address_area, member_address_street, member_delivery_is_default, system_create_user_id, request_app_id, request_http_id, request_user_id);
    }

    public Boolean updateValidateSystem_version(String member_address_id, String member_id, String user_id, String member_address_name, String member_address_phone, String member_address_province, String member_address_city, String member_address_area, String member_address_street, Boolean member_delivery_is_default, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        return memberAddressCache.updateValidateSystem_version(member_address_id, member_id, user_id, member_address_name, member_address_phone, member_address_province, member_address_city, member_address_area, member_address_street, member_delivery_is_default, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);
    }

    public Boolean deleteByMember_address_idAndSystem_update_user_idValidateSystem_version(String member_address_id, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        return memberAddressCache.deleteByMember_address_idAndSystem_update_user_idValidateSystem_version(member_address_id, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);
    }

}