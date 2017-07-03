package com.nowui.chuangshi.service;

import com.nowui.chuangshi.cache.MemberLevelCache;
import com.nowui.chuangshi.model.MemberLevel;

import java.util.Date;
import java.util.List;

public class MemberLevelService extends Service {

    private MemberLevelCache memberLevelCache = new MemberLevelCache();

    public Integer countByApp_idOrLikeMember_level_name(String app_id, String member_level_name, String request_app_id, String request_http_id, String request_user_id) {
        return memberLevelCache.countByApp_idOrLikeMember_level_name(app_id, member_level_name, request_app_id, request_http_id, request_user_id);
    }

    public Integer countByOrApp_idOrLikeMember_level_name(String app_id, String member_level_name, String request_app_id, String request_http_id, String request_user_id) {
        return memberLevelCache.countByOrApp_idOrLikeMember_level_name(app_id, member_level_name, request_app_id, request_http_id, request_user_id);
    }

    public List<MemberLevel> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        return memberLevelCache.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n, request_app_id, request_http_id, request_user_id);
    }

    public List<MemberLevel> listByApp_idOrLikeMember_level_nameAndLimit(String app_id, String member_level_name, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        return memberLevelCache.listByApp_idOrLikeMember_level_nameAndLimit(app_id, member_level_name, m, n, request_app_id, request_http_id, request_user_id);
    }

    public List<MemberLevel> listByOrApp_idOrLikeMember_level_nameAndLimit(String app_id, String member_level_name, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        return memberLevelCache.listByOrApp_idOrLikeMember_level_nameAndLimit(app_id, member_level_name, m, n, request_app_id, request_http_id, request_user_id);
    }

    public MemberLevel findByMember_level_id(String member_level_id, String request_app_id, String request_http_id, String request_user_id) {
        return memberLevelCache.findByMember_level_id(member_level_id, request_app_id, request_http_id, request_user_id);
    }

    public Boolean save(String member_level_id, String app_id, String member_level_name, Integer member_level_value, Integer member_level_sort, String system_create_user_id, String request_app_id, String request_http_id, String request_user_id) {
        return memberLevelCache.save(member_level_id, app_id, member_level_name, member_level_value, member_level_sort, system_create_user_id, request_app_id, request_http_id, request_user_id);
    }

    public Boolean updateValidateSystem_version(String member_level_id, String member_level_name, Integer member_level_value, Integer member_level_sort, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        return memberLevelCache.updateValidateSystem_version(member_level_id, member_level_name, member_level_value, member_level_sort, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);
    }

    public Boolean deleteByMember_level_idAndSystem_update_user_idValidateSystem_version(String member_level_id, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        return memberLevelCache.deleteByMember_level_idAndSystem_update_user_idValidateSystem_version(member_level_id, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);
    }

}