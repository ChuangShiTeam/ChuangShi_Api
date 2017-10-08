package com.nowui.chuangshi.api.minhang.service;

import com.nowui.chuangshi.api.minhang.dao.MinhangMemberDao;
import com.nowui.chuangshi.api.minhang.model.MinhangMember;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class MinhangMemberService extends Service {

    public static final MinhangMemberService instance = new MinhangMemberService();
    private final String MINHANG_MEMBER_ITEM_CACHE = "minhang_member_item_cache";
    private final MinhangMemberDao minhangMemberDao = new MinhangMemberDao();

    public Integer adminCount(String app_id, String member_name) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangMember.SYSTEM_STATUS, true);
        cnd.and(MinhangMember.APP_ID, app_id);
        cnd.andAllowEmpty(MinhangMember.MEMBER_NAME, member_name);

        Integer count = minhangMemberDao.count(cnd);
        return count;
    }

    public List<MinhangMember> adminList(String app_id, String member_name, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangMember.SYSTEM_STATUS, true);
        cnd.and(MinhangMember.APP_ID, app_id);
        cnd.andAllowEmpty(MinhangMember.MEMBER_NAME, member_name);
        cnd.paginate(m, n);

        List<MinhangMember> minhang_memberList = minhangMemberDao.primaryKeyList(cnd);
        for (MinhangMember minhang_member : minhang_memberList) {
            minhang_member.put(find(minhang_member.getMember_id()));
        }
        return minhang_memberList;
    }

    public MinhangMember find(String member_id) {
        MinhangMember minhang_member = CacheUtil.get(MINHANG_MEMBER_ITEM_CACHE, member_id);

        if (minhang_member == null) {
            minhang_member = minhangMemberDao.find(member_id);

            CacheUtil.put(MINHANG_MEMBER_ITEM_CACHE, member_id, minhang_member);
        }

        return minhang_member;
    }

    public Boolean save(MinhangMember minhang_member, String system_create_user_id) {
        Boolean success = minhangMemberDao.save(minhang_member, system_create_user_id);
        return success;
    }

    public Boolean update(MinhangMember minhang_member, String member_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangMember.SYSTEM_STATUS, true);
        cnd.and(MinhangMember.MEMBER_ID, member_id);
        cnd.and(MinhangMember.SYSTEM_VERSION, system_version);

        Boolean success = minhangMemberDao.update(minhang_member, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_MEMBER_ITEM_CACHE, member_id);
        }

        return success;
    }

    public Boolean delete(String member_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangMember.SYSTEM_STATUS, true);
        cnd.and(MinhangMember.MEMBER_ID, member_id);
        cnd.and(MinhangMember.SYSTEM_VERSION, system_version);

        Boolean success = minhangMemberDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_MEMBER_ITEM_CACHE, member_id);
        }

        return success;
    }

}