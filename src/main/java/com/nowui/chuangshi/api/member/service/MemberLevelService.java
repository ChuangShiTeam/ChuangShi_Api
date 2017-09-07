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
    private final String MEMBER_LEVEL_LIST_CACHE = "member_level_list_cache";
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

    public List<MemberLevel> appList(String app_id) {
        List<MemberLevel> memberLevelList = CacheUtil.get(MEMBER_LEVEL_LIST_CACHE, app_id);

        if (memberLevelList == null) {
            Cnd cnd = Cnd.where(MemberLevel.SYSTEM_STATUS, true);
            cnd.and(MemberLevel.APP_ID, app_id);

            memberLevelList = memberLevelDao.primaryKeyList(cnd);
            for (MemberLevel memberLevel : memberLevelList) {
                memberLevel.put(find(memberLevel.getMember_level_id()));
            }

            CacheUtil.put(MEMBER_LEVEL_LIST_CACHE, app_id, memberLevelList);
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

    public MemberLevel memberLevelSortFind(String app_id, Integer member_level_sort) {
        List<MemberLevel> memberLevelList = appList(app_id);

        for (MemberLevel memberLevel : memberLevelList) {
            if (memberLevel.getMember_level_sort().equals(member_level_sort)) {
                return memberLevel;
            }
        }

        throw new RuntimeException("找不到等级");
    }

    public Boolean save(MemberLevel memberLevel, String system_create_user_id) {
        Boolean success = memberLevelDao.save(memberLevel, system_create_user_id);

        if (success) {
            CacheUtil.remove(MEMBER_LEVEL_LIST_CACHE, memberLevel.getApp_id());
        }

        return success;
    }

    public Boolean update(MemberLevel memberLevel, String member_level_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = Cnd.where(MemberLevel.SYSTEM_STATUS, true);
        cnd.and(MemberLevel.MEMBER_LEVEL_ID, member_level_id);
        cnd.and(MemberLevel.SYSTEM_VERSION, system_version);

        Boolean success = memberLevelDao.update(memberLevel, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MEMBER_LEVEL_ITEM_CACHE, member_level_id);

            memberLevel = find(member_level_id);
            CacheUtil.remove(MEMBER_LEVEL_LIST_CACHE, memberLevel.getApp_id());
        }

        return success;
    }

    public Boolean delete(String member_level_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = Cnd.where(MemberLevel.SYSTEM_STATUS, true);
        cnd.and(MemberLevel.MEMBER_LEVEL_ID, member_level_id);
        cnd.and(MemberLevel.SYSTEM_VERSION, system_version);

        Boolean success = memberLevelDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MEMBER_LEVEL_ITEM_CACHE, member_level_id);

            MemberLevel memberLevel = find(member_level_id);
            CacheUtil.remove(MEMBER_LEVEL_LIST_CACHE, memberLevel.getApp_id());
        }

        return success;
    }

}