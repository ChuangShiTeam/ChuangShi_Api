package com.nowui.chuangshi.service;

import com.nowui.chuangshi.cache.MemberCache;
import com.nowui.chuangshi.model.Member;

import java.util.Date;
import java.util.List;

public class MemberService extends Service {

    private MemberCache memberCache = new MemberCache();

    public Integer countByApp_idOrLikeUser_name(String app_id, String user_name) {
        return memberCache.countByApp_idOrLikeUser_name(app_id, user_name);
    }

    public Integer countByOrApp_idOrLikeUser_name(String app_id, String user_name) {
        return memberCache.countByOrApp_idOrLikeUser_name(app_id, user_name);
    }

    public List<Member> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        return memberCache.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);
    }

    public List<Member> listByApp_idOrLikeUser_nameAndLimit(String app_id, String user_name, int m, int n) {
        return memberCache.listByApp_idOrLikeUser_nameAndLimit(app_id, user_name, m, n);
    }
    
    public List<Member> listByApp_id(String app_id) {
    	return memberCache.listByApp_id(app_id);
    }
    
    public List<Member> listByOrApp_id(String app_id) {
    	return memberCache.listByOrApp_id(app_id);
    }

    public List<Member> listByOrApp_idOrLikeUser_nameAndLimit(String app_id, String user_name, int m, int n) {
        return memberCache.listByOrApp_idOrLikeUser_nameAndLimit(app_id, user_name, m, n);
    }

    public Member findByMember_id(String member_id) {
        return memberCache.findByMember_id(member_id);
    }

    public Boolean save(String member_id, String app_id, String user_id, String member_parent_id, String from_qrcode_id, String qrcode_id, String member_level_id, String member_path, Boolean member_status, String system_create_user_id) {
        return memberCache.save(member_id, app_id, user_id, member_parent_id, from_qrcode_id, qrcode_id, member_level_id, member_path, member_status, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String member_id, String user_id, String member_parent_id, String from_qrcode_id, String qrcode_id, String member_level_id, String member_path, Boolean member_status, String system_update_user_id, Integer system_version) {
        return memberCache.updateValidateSystem_version(member_id, user_id, member_parent_id, from_qrcode_id, qrcode_id, member_level_id, member_path, member_status, system_update_user_id, system_version);
    }

    public Boolean deleteByMember_idAndSystem_update_user_idValidateSystem_version(String member_id, String system_update_user_id, Integer system_version) {
        return memberCache.deleteByMember_idAndSystem_update_user_idValidateSystem_version(member_id, system_update_user_id, system_version);
    }

}