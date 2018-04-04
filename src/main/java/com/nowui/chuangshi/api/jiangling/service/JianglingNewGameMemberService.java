package com.nowui.chuangshi.api.jiangling.service;

import java.util.List;

import com.nowui.chuangshi.api.jiangling.dao.JianglingNewGameMemberDao;
import com.nowui.chuangshi.api.jiangling.model.JianglingNewGameMember;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

public class JianglingNewGameMemberService extends Service {

    public static final JianglingNewGameMemberService instance = new JianglingNewGameMemberService();
    private final String JIANGLING_NEW_GAME_MEMBER_ITEM_CACHE = "jiangling_new_game_member_item_cache";
    private final JianglingNewGameMemberDao jianglingNewCustomerDao = new JianglingNewGameMemberDao();

    public Integer adminCount(String app_id, String game_member_name) {
        Cnd cnd = new Cnd();
        cnd.where(JianglingNewGameMember.SYSTEM_STATUS, true);
        cnd.andAllowEmpty(JianglingNewGameMember.NEW_GAME_MEMBER_NAME, game_member_name);

        Integer count = jianglingNewCustomerDao.count(cnd);
        return count;
    }

    public List<JianglingNewGameMember> adminList(String new_game_member_name, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(JianglingNewGameMember.SYSTEM_STATUS, true);
        cnd.andAllowEmpty(JianglingNewGameMember.NEW_GAME_MEMBER_NAME, new_game_member_name);
        cnd.paginate(m, n);

        List<JianglingNewGameMember> jianglingNewGameMemberList = jianglingNewCustomerDao.primaryKeyList(cnd);
        for (JianglingNewGameMember jianglingNewGameMember : jianglingNewGameMemberList) {
            jianglingNewGameMember.put(find(jianglingNewGameMember.getNew_game_id()));
        }
        return jianglingNewGameMemberList;
    }

    public List<JianglingNewGameMember> allList() {
        Cnd cnd = new Cnd();
        cnd.where(JianglingNewGameMember.SYSTEM_STATUS, true);

        List<JianglingNewGameMember> jianglingNewGameMemberList = jianglingNewCustomerDao.primaryKeyList(cnd);
        for (JianglingNewGameMember jianglingNewGameMember : jianglingNewGameMemberList) {
            jianglingNewGameMember.put(find(jianglingNewGameMember.getNew_game_id()));
        }
        return jianglingNewGameMemberList;
    }

    public JianglingNewGameMember find(String new_game_id) {
        JianglingNewGameMember jianglingNewGameMember = CacheUtil.get(JIANGLING_NEW_GAME_MEMBER_ITEM_CACHE, new_game_id);

        if (jianglingNewGameMember == null) {
            jianglingNewGameMember = jianglingNewCustomerDao.find(new_game_id);

            CacheUtil.put(JIANGLING_NEW_GAME_MEMBER_ITEM_CACHE, new_game_id, jianglingNewGameMember);
        }

        return jianglingNewGameMember;
    }

    public Boolean save(JianglingNewGameMember jianglingNewGameMember, String system_create_user_id) {
        Boolean success = jianglingNewCustomerDao.save(jianglingNewGameMember, system_create_user_id);
        return success;
    }

    public Boolean update(JianglingNewGameMember jianglingNewGameMember, String new_game_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(JianglingNewGameMember.SYSTEM_STATUS, true);
        cnd.and(JianglingNewGameMember.NEW_GAME_ID, new_game_id);
        cnd.and(JianglingNewGameMember.SYSTEM_VERSION, system_version);

        Boolean success = jianglingNewCustomerDao.update(jianglingNewGameMember, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(JIANGLING_NEW_GAME_MEMBER_ITEM_CACHE, new_game_id);
        }

        return success;
    }

    public Boolean delete(String new_game_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(JianglingNewGameMember.SYSTEM_STATUS, true);
        cnd.and(JianglingNewGameMember.NEW_GAME_ID, new_game_id);
        cnd.and(JianglingNewGameMember.SYSTEM_VERSION, system_version);

        Boolean success = jianglingNewCustomerDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(JIANGLING_NEW_GAME_MEMBER_ITEM_CACHE, new_game_id);
        }

        return success;
    }

}