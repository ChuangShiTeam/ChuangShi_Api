package com.nowui.chuangshi.cache;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.MemberLevelDao;
import com.nowui.chuangshi.model.MemberLevel;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.Date;
import java.util.List;

public class MemberLevelCache extends Cache {

    public static final String MEMBER_LEVEL_BY_MEMBER_LEVEL_ID_CACHE = "member_level_by_member_level_id_cache";

    private MemberLevelDao memberLevelDao = new MemberLevelDao();

    public Integer countByApp_idOrLikeMember_level_name(String app_id, String member_level_name) {
        return memberLevelDao.countByApp_idOrLikeMember_level_name(app_id, member_level_name);
    }

    public Integer countByOrApp_idOrLikeMember_level_name(String app_id, String member_level_name) {
        return memberLevelDao.countByOrApp_idOrLikeMember_level_name(app_id, member_level_name);
    }

    public List<MemberLevel> listByApp_id(String app_id) {
        List<MemberLevel> member_levelList = memberLevelDao.listByApp_id(app_id);

        for (MemberLevel member_level : member_levelList) {
            member_level.put(findByMember_level_id(member_level.getMember_level_id()));
        }

        return member_levelList;
    }

    public List<MemberLevel> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        List<MemberLevel> member_levelList = memberLevelDao.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);

        for (MemberLevel member_level : member_levelList) {
            member_level.put(findByMember_level_id(member_level.getMember_level_id()));
        }

        return member_levelList;
    }

    public List<MemberLevel> listByApp_idOrLikeMember_level_nameAndLimit(String app_id, String member_level_name, int m, int n) {
        List<MemberLevel> member_levelList = memberLevelDao.listByApp_idOrLikeMember_level_nameAndLimit(app_id, member_level_name, m, n);

        for (MemberLevel member_level : member_levelList) {
            member_level.put(findByMember_level_id(member_level.getMember_level_id()));
        }

        return member_levelList;
    }

    public List<MemberLevel> listByOrApp_idOrLikeMember_level_nameAndLimit(String app_id, String member_level_name, int m, int n) {
        List<MemberLevel> member_levelList = memberLevelDao.listByOrApp_idOrLikeMember_level_nameAndLimit(app_id, member_level_name, m, n);

        for (MemberLevel member_level : member_levelList) {
            member_level.put(findByMember_level_id(member_level.getMember_level_id()));
        }

        return member_levelList;
    }

    public MemberLevel findByMember_level_id(String member_level_id) {
        MemberLevel member_level = CacheUtil.get(MEMBER_LEVEL_BY_MEMBER_LEVEL_ID_CACHE, member_level_id);

        if (member_level == null) {
            member_level = memberLevelDao.findByMember_level_id(member_level_id);

            CacheUtil.put(MEMBER_LEVEL_BY_MEMBER_LEVEL_ID_CACHE, member_level_id, member_level);
        }

        return member_level;
    }

    public Boolean save(String member_level_id, String app_id, String member_level_name, Integer member_level_value, Integer member_level_sort, String system_create_user_id) {
        return memberLevelDao.save(member_level_id, app_id, member_level_name, member_level_value, member_level_sort, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String member_level_id, String member_level_name, Integer member_level_value, Integer member_level_sort, String system_update_user_id, Integer system_version) {
        MemberLevel member_level = findByMember_level_id(member_level_id);
        if (!member_level.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = memberLevelDao.update(member_level_id, member_level_name, member_level_value, member_level_sort, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(MEMBER_LEVEL_BY_MEMBER_LEVEL_ID_CACHE, member_level_id);
        }

        return result;
    }

    public Boolean deleteByMember_level_idAndSystem_update_user_idValidateSystem_version(String member_level_id, String system_update_user_id, Integer system_version) {
        MemberLevel member_level = findByMember_level_id(member_level_id);
        if (!member_level.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = memberLevelDao.deleteByMember_level_idAndSystem_version(member_level_id, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(MEMBER_LEVEL_BY_MEMBER_LEVEL_ID_CACHE, member_level_id);
        }

        return result;
    }

}