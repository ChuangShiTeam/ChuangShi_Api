package com.nowui.chuangshi.api.jiangling.service;

import com.nowui.chuangshi.api.jiangling.dao.JianglingMemberDao;
import com.nowui.chuangshi.api.jiangling.model.JianglingMember;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.type.UserType;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class JianglingMemberService extends Service {

    public static final JianglingMemberService instance = new JianglingMemberService();
    private final String JIANGLING_MEMBER__ITEM_CACHE = "jiangling_member_item_cache";
    private final JianglingMemberDao jianglingMemberDao = new JianglingMemberDao();

    public Integer userCount(String user_id) {
        Integer count = jianglingMemberDao.count(Cnd.where(JianglingMember.USER_ID, user_id));
        return count;
    }

    public Integer redeemCodeCount(String member_redeem_code) {
        Integer count = jianglingMemberDao.count(Cnd.where(JianglingMember.MEMBER_REDEEM_CODE, member_redeem_code));
        return count;
    }

    public Integer adminCount(String app_id, String user_name, String member_redeem_code) {
        Integer count = jianglingMemberDao.adminCount(app_id, UserType.MEMBER.getKey(), user_name, member_redeem_code);
        return count;
    }

    public List<JianglingMember> adminList(String app_id, String user_name, String member_redeem_code, Integer m, Integer n) {
        List<JianglingMember> jianglingMemberList = jianglingMemberDao.adminList(app_id, UserType.MEMBER.getKey(), user_name, member_redeem_code, m, n);
        return jianglingMemberList;
    }

    public JianglingMember find(String user_id) {
        JianglingMember jianglingMember = CacheUtil.get(JIANGLING_MEMBER__ITEM_CACHE, user_id);

        if (jianglingMember == null) {
            jianglingMember = jianglingMemberDao.find(user_id);

            CacheUtil.put(JIANGLING_MEMBER__ITEM_CACHE, user_id, jianglingMember);
        }

        return jianglingMember;
    }

    public Boolean save(JianglingMember jianglingMember) {
        Boolean result = jianglingMemberDao.save(jianglingMember);
        return result;
    }

    public Boolean update(JianglingMember jianglingMember, String user_id) {
        Boolean result = jianglingMemberDao.update(jianglingMember, Cnd.where(JianglingMember.USER_ID, user_id));

        if (result) {
            CacheUtil.remove(JIANGLING_MEMBER__ITEM_CACHE, user_id);
        }

        return result;
    }

    public Boolean delete(String user_id, Integer system_version) {
        Boolean result = jianglingMemberDao.delete(Cnd.where(JianglingMember.USER_ID, user_id).and(JianglingMember.SYSTEM_VERSION, system_version));

        if (result) {
            CacheUtil.remove(JIANGLING_MEMBER__ITEM_CACHE, user_id);
        }

        return result;
    }

}