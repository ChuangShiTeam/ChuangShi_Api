package com.nowui.chuangshi.api.member.service;

import com.nowui.chuangshi.api.member.dao.MemberDao;
import com.nowui.chuangshi.api.member.model.Member;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

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

    public Boolean save(Member member) {
        Boolean result = memberDao.save(member);
        return result;
    }

    public Boolean update(Member member, String member_id, Integer system_version) {
        Cnd cnd = Cnd.where(Member.SYSTEM_STATUS, true);
        cnd.and(Member.MEMBER_ID, member_id);
        cnd.and(Member.SYSTEM_VERSION, system_version);

        Boolean success = memberDao.update(member, cnd);

        if (success) {
            CacheUtil.remove(MEMBER_ITEM_CACHE, member_id);
        }

        return success;
    }

    public Boolean delete(String member_id, Integer system_version) {
        Cnd cnd = Cnd.where(Member.SYSTEM_STATUS, true);
        cnd.and(Member.MEMBER_ID, member_id);
        cnd.and(Member.SYSTEM_VERSION, system_version);

        Boolean success = memberDao.delete(cnd);

        if (success) {
            CacheUtil.remove(MEMBER_ITEM_CACHE, member_id);
        }

        return success;
    }

}