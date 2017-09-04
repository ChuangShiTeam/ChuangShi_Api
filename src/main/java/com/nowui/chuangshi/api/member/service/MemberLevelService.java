package com.nowui.chuangshi.api.member.service;

import com.nowui.chuangshi.api.member.dao.MemberLevelDao;
import com.nowui.chuangshi.api.member.model.MemberLevel;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class MemberLevelService extends Service {

    public static final MemberLevelService instance = new MemberLevelService();
    private final String MEMBER_LEVEL_ITEM_CACHE = "member_level_item_cache";
    private final MemberLevelDao memberLevelDao = new MemberLevelDao();

    public Integer adminCount(String app_id, String member_level_name) {
        Cnd cnd = Cnd.where(MemberLevel.SYSTEM_STATUS, true);
        cnd.and(MemberLevel.APP_ID, app_id);
        cnd.andAllowEmpty(MemberLevel.MEMBER_LEVEL_NAME, member_level_name);

        Integer count = memberLevelDao.count(cnd);
        return count;
    }

    public List<MemberLevel> adminList(String app_id, String member_level_name, Integer m, Integer n) {
        Cnd cnd = Cnd.where(MemberLevel.SYSTEM_STATUS, true);
        cnd.and(MemberLevel.APP_ID, app_id);
        cnd.andAllowEmpty(MemberLevel.MEMBER_LEVEL_NAME, member_level_name);
        cnd.paginate(m, n);

        List<MemberLevel> memberLevelList = memberLevelDao.primaryKeyList(cnd);
        for (MemberLevel memberLevel : memberLevelList) {
            memberLevel.put(find(memberLevel.getMember_level_id()));
        }
        return memberLevelList;
    }

    public MemberLevel find(String member_level_id) {
        MemberLevel memberLevel = CacheUtil.get(MEMBER_LEVEL_ITEM_CACHE, member_level_id);

        if (memberLevel == null) {
            memberLevel = memberLevelDao.find(member_level_id);

            CacheUtil.put(MEMBER_LEVEL_ITEM_CACHE, member_level_id, memberLevel);
        }

        return memberLevel;
    }

    public Boolean save(MemberLevel memberLevel) {
        Boolean success = memberLevelDao.save(memberLevel);
        return success;
    }

    public Boolean update(MemberLevel memberLevel, String member_level_id, Integer system_version) {
        Cnd cnd = Cnd.where(MemberLevel.SYSTEM_STATUS, true);
        cnd.and(MemberLevel.MEMBER_LEVEL_ID, member_level_id);
        cnd.and(MemberLevel.SYSTEM_VERSION, system_version);

        Boolean success = memberLevelDao.update(memberLevel, cnd);

        if (success) {
            CacheUtil.remove(MEMBER_LEVEL_ITEM_CACHE, member_level_id);
        }

        return success;
    }

    public Boolean delete(String member_level_id, Integer system_version) {
        Cnd cnd = Cnd.where(MemberLevel.SYSTEM_STATUS, true);
        cnd.and(MemberLevel.MEMBER_LEVEL_ID, member_level_id);
        cnd.and(MemberLevel.SYSTEM_VERSION, system_version);

        Boolean success = memberLevelDao.delete(cnd);

        if (success) {
            CacheUtil.remove(MEMBER_LEVEL_ITEM_CACHE, member_level_id);
        }

        return success;
    }

}