package com.nowui.chuangshi.api.renault.service;

import java.util.List;

import com.nowui.chuangshi.api.renault.dao.RenaultMemberDao;
import com.nowui.chuangshi.api.renault.model.RenaultMember;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.user.service.UserService;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.type.UserType;
import com.nowui.chuangshi.util.CacheUtil;
import com.nowui.chuangshi.util.Util;

public class RenaultMemberService extends Service {

    public static final RenaultMemberService instance = new RenaultMemberService();
    private final String RENAULT_MEMBER_ITEM_CACHE = "renault_member_item_cache";
    private final RenaultMemberDao renaultMemberDao = new RenaultMemberDao();

    public Integer adminCount(String app_id, String user_id, String member_nick_name) {
        Cnd cnd = new Cnd();
        cnd.where(RenaultMember.SYSTEM_STATUS, true);
        cnd.and(RenaultMember.APP_ID, app_id);
        cnd.andAllowEmpty(RenaultMember.USER_ID, user_id);
        cnd.andAllowEmpty(RenaultMember.MEMBER_NICK_NAME, member_nick_name);

        Integer count = renaultMemberDao.count(cnd);
        return count;
    }

    public List<RenaultMember> adminList(String app_id, String user_id, String member_nick_name, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(RenaultMember.SYSTEM_STATUS, true);
        cnd.and(RenaultMember.APP_ID, app_id);
        cnd.andAllowEmpty(RenaultMember.USER_ID, user_id);
        cnd.andAllowEmpty(RenaultMember.MEMBER_NICK_NAME, member_nick_name);
        cnd.paginate(m, n);

        List<RenaultMember> renault_memberList = renaultMemberDao.primaryKeyList(cnd);
        for (RenaultMember renault_member : renault_memberList) {
            renault_member.put(find(renault_member.getMember_id()));
        }
        return renault_memberList;
    }

    public RenaultMember find(String member_id) {
        RenaultMember renault_member = CacheUtil.get(RENAULT_MEMBER_ITEM_CACHE, member_id);

        if (renault_member == null) {
            renault_member = renaultMemberDao.find(member_id);

            CacheUtil.put(RENAULT_MEMBER_ITEM_CACHE, member_id, renault_member);
        }

        return renault_member;
    }

    public Boolean save(RenaultMember renault_member, String system_create_user_id) {
        Boolean success = renaultMemberDao.save(renault_member, system_create_user_id);
        return success;
    }
    
    public Boolean save(RenaultMember renault_member, User user, String system_create_user_id) {
        String user_id = Util.getRandomUUID();
        
        renault_member.setMember_id(Util.getRandomUUID());
        renault_member.setMember_nick_name(user.getUser_name());
        renault_member.setUser_id(user_id);
        
        Boolean success = renaultMemberDao.save(renault_member, system_create_user_id);
        
        if (success) {
            UserService.instance.userAccountAndAvatarSave(user_id, renault_member.getApp_id(), renault_member.getMember_id(), UserType.RENAULT_MEMBER.getKey(), user.getUser_name(), user.getUser_account(), user.getUser_password(), user.getUser_avatar(), user_id);
        }
        return success;
    }

    public Boolean update(RenaultMember renault_member, String member_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(RenaultMember.SYSTEM_STATUS, true);
        cnd.and(RenaultMember.MEMBER_ID, member_id);
        cnd.and(RenaultMember.SYSTEM_VERSION, system_version);

        Boolean success = renaultMemberDao.update(renault_member, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(RENAULT_MEMBER_ITEM_CACHE, member_id);
        }

        return success;
    }

    public Boolean delete(String member_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(RenaultMember.SYSTEM_STATUS, true);
        cnd.and(RenaultMember.MEMBER_ID, member_id);
        cnd.and(RenaultMember.SYSTEM_VERSION, system_version);

        Boolean success = renaultMemberDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(RENAULT_MEMBER_ITEM_CACHE, member_id);
        }

        return success;
    }

}