package com.nowui.chuangshi.api.jiangling.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.api.jiangling.model.JianglingMember;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.common.dao.Dao;
import com.nowui.chuangshi.constant.Constant;

import java.util.List;

public class JianglingMemberDao extends Dao {

    private final JianglingMember jianglingMember = new JianglingMember();

    public JianglingMemberDao() {
        setModel(jianglingMember);
    }

    public Integer adminCount(String app_id, String user_type, String user_name, String member_redeem_code) {
        Kv sqlMap = Kv.create();
        sqlMap.put(User.APP_ID, app_id);
        sqlMap.put(User.USER_TYPE, user_type);
        sqlMap.put(User.USER_NAME, user_name);
        sqlMap.put(JianglingMember.MEMBER_REDEEM_CODE, member_redeem_code);
        SqlPara sqlPara = Db.getSqlPara("jiangling_member.adminCount", sqlMap);

        logSql("jiangling_member", "adminCount", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<JianglingMember> adminList(String app_id, String user_type, String user_name, String member_redeem_code, Integer m, Integer n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(User.APP_ID, app_id);
        sqlMap.put(User.USER_TYPE, user_type);
        sqlMap.put(User.USER_NAME, user_name);
        sqlMap.put(JianglingMember.MEMBER_REDEEM_CODE, member_redeem_code);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("jiangling_member.adminList", sqlMap);

        logSql("jiangling_member", "adminList", sqlPara);

        return jianglingMember.find(sqlPara.getSql(), sqlPara.getPara());
    }

}