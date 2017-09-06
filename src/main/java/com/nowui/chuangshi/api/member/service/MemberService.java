package com.nowui.chuangshi.api.member.service;

import com.alibaba.fastjson.JSONArray;
import com.nowui.chuangshi.api.member.dao.MemberDao;
import com.nowui.chuangshi.api.member.model.Member;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.Date;
import java.util.List;

public class MemberService extends Service {

    public static final MemberService instance = new MemberService();
    private final String MEMBER_ITEM_CACHE = "member_item_cache";
    private final MemberDao memberDao = new MemberDao();

    public Integer adminCount(String app_id) {
        Cnd cnd = Cnd.where(Member.SYSTEM_STATUS, true);
        cnd.and(Member.APP_ID, app_id);

        Integer count = memberDao.count(cnd);
        return count;
    }

    public List<Member> adminList(String app_id, Integer m, Integer n) {
        Cnd cnd = Cnd.where(Member.SYSTEM_STATUS, true);
        cnd.and(Member.APP_ID, app_id);
        cnd.paginate(m, n);

        List<Member> memberList = memberDao.list(cnd);
        return memberList;
    }

    public Member find(String member_id) {
        Member member = CacheUtil.get(MEMBER_ITEM_CACHE, member_id);

        if (member == null) {
            member = memberDao.find(member_id);

            CacheUtil.put(MEMBER_ITEM_CACHE, member_id, member);
        }

        return member;
    }

    private Boolean save(String member_id, String app_id, String user_id, String member_parent_id, String from_qrcode_id, String qrcode_id, String member_level_id, JSONArray member_parent_path, Boolean member_status, String system_create_user_id) {
        Member member = new Member();
        member.setMember_id(member_id);
        member.setApp_id(app_id);
        member.setUser_id(user_id);
        member.setMember_parent_id(member_parent_id);
        member.setFrom_qrcode_id(from_qrcode_id);
        member.setQrcode_id(qrcode_id);
        member.setMember_level_id(member_level_id);
        member.setMember_parent_path(member_parent_path.toJSONString());
        member.setMember_status(member_status);

        Boolean success = memberDao.save(member, system_create_user_id);
        return success;
    }

    public Boolean update(Member member, String member_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = Cnd.where(Member.SYSTEM_STATUS, true);
        cnd.and(Member.MEMBER_ID, member_id);
        cnd.and(Member.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        cnd.and(Member.SYSTEM_VERSION, system_version);

        Boolean success = memberDao.update(member, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MEMBER_ITEM_CACHE, member_id);
        }

        return success;
    }

    public Boolean delete(String member_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = Cnd.where(Member.SYSTEM_STATUS, true);
        cnd.and(Member.MEMBER_ID, member_id);
        cnd.and(Member.SYSTEM_VERSION, system_version);

        Boolean success = memberDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MEMBER_ITEM_CACHE, member_id);
        }

        return success;
    }

//    public Member saveOrUpdate(String app_id, String wechat_open_id, String wechat_union_id, String member_parent_id, String from_qrcode_id, String member_level_id, JSONArray member_parent_path, String user_name, String user_avatar, Boolean member_status, String system_create_user_id) {
//        String member_id = "";
//
//        User user = UserService.instance.find(app_id, UserType.MEMBER.getKey(), wechat_open_id, wechat_union_id);
//        if (user == null) {
//            member_id = Util.getRandomUUID();
//            String user_id = Util.getRandomUUID();
//            String qrcode_id = "";
//
//            Boolean result = memberDao.save();
//
//        }
//
//    }

//
//    public String login(String app_id, String wechat_open_id, String wechat_union_id, String member_parent_id, String from_qrcode_id, String member_level_id, JSONArray member_parent_path, String user_name, String user_avatar, Boolean member_status, String system_create_user_id) {
//
//    }

}