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
        Integer count = jianglingCustomerDao.count(Cnd.whereAllowEmpty(JianglingGameMember.GAME_MEMBER_NAME, game_member_name));
        return count;
    }

    public List<JianglingGameMember> adminList(String app_id, String game_member_name, Integer m, Integer n) {
        List<JianglingGameMember> jianglingGameMemberList = jianglingCustomerDao.list(Cnd.whereAllowEmpty(JianglingGameMember.GAME_MEMBER_NAME, game_member_name).paginate(m, n));
        return jianglingGameMemberList;
    }

    public List<JianglingGameMember> allList() {
        List<JianglingGameMember> jianglingGameMemberList = jianglingCustomerDao.list(Cnd.where("1", 1));
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
        Boolean result = jianglingCustomerDao.save(jianglingGameMember);
        return result;
    }

    public Boolean update(JianglingGameMember jianglingGameMember, String game_id, Integer system_version) {
        Boolean result = jianglingCustomerDao.update(jianglingGameMember, Cnd.where(JianglingGameMember.GAME_ID, game_id).and(JianglingGameMember.SYSTEM_VERSION, system_version));

        if (result) {
            CacheUtil.remove(JIANGLING_GAME_MEMBER_ITEM_CACHE, game_id);
        }

        return result;
    }

    public Boolean delete(String game_id, Integer system_version) {
        Boolean result = jianglingCustomerDao.delete(Cnd.where(JianglingGameMember.GAME_ID, game_id).and(JianglingGameMember.SYSTEM_VERSION, system_version));

        if (result) {
            CacheUtil.remove(JIANGLING_GAME_MEMBER_ITEM_CACHE, game_id);
        }

        return result;
    }

}