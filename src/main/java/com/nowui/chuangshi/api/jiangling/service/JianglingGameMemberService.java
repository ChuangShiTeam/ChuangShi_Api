package com.nowui.chuangshi.api.jiangling.service;

import com.nowui.chuangshi.api.jiangling.dao.JianglingGameMemberDao;
import com.nowui.chuangshi.api.jiangling.model.JianglingGameMember;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class JianglingGameMemberService extends Service {

    public static final JianglingGameMemberService instance = new JianglingGameMemberService();
    private final String JIANGLING_GAME_MEMBER_ITEM_CACHE = "jiangling_game_member_item_cache";
    private final JianglingGameMemberDao jianglingCustomerDao = new JianglingGameMemberDao();

    public Integer adminCount(String app_id, String game_member_name) {
        Cnd cnd = Cnd.where(JianglingGameMember.SYSTEM_STATUS, true);
        cnd.andAllowEmpty(JianglingGameMember.GAME_MEMBER_NAME, game_member_name);

        Integer count = jianglingCustomerDao.count(cnd);
        return count;
    }

    public List<JianglingGameMember> adminList(String game_member_name, Integer m, Integer n) {
        Cnd cnd = Cnd.where(JianglingGameMember.SYSTEM_STATUS, true);
        cnd.andAllowEmpty(JianglingGameMember.GAME_MEMBER_NAME, game_member_name);
        cnd.paginate(m, n);

        List<JianglingGameMember> jianglingGameMemberList = jianglingCustomerDao.primaryKeyList(cnd);
        for (JianglingGameMember jianglingGameMember : jianglingGameMemberList) {
            jianglingGameMember.put(find(jianglingGameMember.getGame_id()));
        }
        return jianglingGameMemberList;
    }

    public List<JianglingGameMember> allList() {
        Cnd cnd = Cnd.where(JianglingGameMember.SYSTEM_STATUS, true);

        List<JianglingGameMember> jianglingGameMemberList = jianglingCustomerDao.primaryKeyList(cnd);
        for (JianglingGameMember jianglingGameMember : jianglingGameMemberList) {
            jianglingGameMember.put(find(jianglingGameMember.getGame_id()));
        }
        return jianglingGameMemberList;
    }

    public JianglingGameMember find(String game_id) {
        JianglingGameMember jianglingGameMember = CacheUtil.get(JIANGLING_GAME_MEMBER_ITEM_CACHE, game_id);

        if (jianglingGameMember == null) {
            jianglingGameMember = jianglingCustomerDao.find(game_id);

            CacheUtil.put(JIANGLING_GAME_MEMBER_ITEM_CACHE, game_id, jianglingGameMember);
        }

        return jianglingGameMember;
    }

    public Boolean save(JianglingGameMember jianglingGameMember) {
        Boolean success = jianglingCustomerDao.save(jianglingGameMember);
        return success;
    }

    public Boolean update(JianglingGameMember jianglingGameMember, String game_id, Integer system_version) {
        Cnd cnd = Cnd.where(JianglingGameMember.SYSTEM_STATUS, true);
        cnd.and(JianglingGameMember.GAME_ID, game_id);
        cnd.and(JianglingGameMember.SYSTEM_VERSION, system_version);

        Boolean success = jianglingCustomerDao.update(jianglingGameMember, cnd);

        if (success) {
            CacheUtil.remove(JIANGLING_GAME_MEMBER_ITEM_CACHE, game_id);
        }

        return success;
    }

    public Boolean delete(String game_id, Integer system_version) {
        Cnd cnd = Cnd.where(JianglingGameMember.SYSTEM_STATUS, true);
        cnd.and(JianglingGameMember.GAME_ID, game_id);
        cnd.and(JianglingGameMember.SYSTEM_VERSION, system_version);

        Boolean success = jianglingCustomerDao.delete(cnd);

        if (success) {
            CacheUtil.remove(JIANGLING_GAME_MEMBER_ITEM_CACHE, game_id);
        }

        return success;
    }

}