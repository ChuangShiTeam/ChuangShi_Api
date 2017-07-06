package com.nowui.chuangshi.cache;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.MemberDao;
import com.nowui.chuangshi.model.Member;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.Date;
import java.util.List;

public class MemberCache extends Cache {

    public static final String MEMBER_BY_MEMBER_ID_CACHE = "member_by_member_id_cache";

    private MemberDao memberDao = new MemberDao();

    public Integer countByApp_idOrLikeUser_name(String app_id, String user_name) {
        return memberDao.countByApp_idOrLikeUser_name(app_id, user_name);
    }

    public Integer countByOrApp_idOrLikeUser_name(String app_id, String user_name) {
        return memberDao.countByOrApp_idOrLikeUser_name(app_id, user_name);
    }

    public List<Member> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        List<Member> memberList = memberDao.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);

        for (Member member : memberList) {
            member.put(findByMember_id(member.getMember_id()));
        }

        return memberList;
    }

    public List<Member> listByApp_idOrLikeUser_nameAndLimit(String app_id, String user_name, int m, int n) {
        List<Member> memberList = memberDao.listByApp_idOrLikeUser_nameAndLimit(app_id, user_name, m, n);

        for (Member member : memberList) {
            member.put(findByMember_id(member.getMember_id()));
        }

        return memberList;
    }

    public List<Member> listByOrApp_idOrLikeUser_nameAndLimit(String app_id, String user_name, int m, int n) {
        List<Member> memberList = memberDao.listByOrApp_idOrLikeUser_nameAndLimit(app_id, user_name, m, n);

        for (Member member : memberList) {
            member.put(findByMember_id(member.getMember_id()));
        }

        return memberList;
    }

    public Member findByMember_id(String member_id) {
        Member member = CacheUtil.get(MEMBER_BY_MEMBER_ID_CACHE, member_id);

        if (member == null) {
            member = memberDao.findByMember_id(member_id);

            CacheUtil.put(MEMBER_BY_MEMBER_ID_CACHE, member_id, member);
        }

        return member;
    }

    public Boolean save(String member_id, String app_id, String user_id, String member_parent_id, String from_qrcode_id, String qrcode_id, String member_level_id, String member_path, Boolean member_status, String system_create_user_id) {
        return memberDao.save(member_id, app_id, user_id, member_parent_id, from_qrcode_id, qrcode_id, member_level_id, member_path, member_status, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String member_id, String user_id, String member_parent_id, String from_qrcode_id, String qrcode_id, String member_level_id, String member_path, Boolean member_status, String system_update_user_id, Integer system_version) {
        Member member = findByMember_id(member_id);
        if (!member.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = memberDao.update(member_id, user_id, member_parent_id, from_qrcode_id, qrcode_id, member_level_id, member_path, member_status, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(MEMBER_BY_MEMBER_ID_CACHE, member_id);
        }

        return result;
    }

    public Boolean deleteByMember_idAndSystem_update_user_idValidateSystem_version(String member_id, String system_update_user_id, Integer system_version) {
        Member member = findByMember_id(member_id);
        if (!member.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = memberDao.deleteByMember_idAndSystem_version(member_id, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(MEMBER_BY_MEMBER_ID_CACHE, member_id);
        }

        return result;
    }

}