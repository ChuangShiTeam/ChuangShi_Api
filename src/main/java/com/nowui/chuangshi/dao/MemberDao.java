package com.nowui.chuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.Member;
import com.nowui.chuangshi.model.User;

import java.util.Date;
import java.util.List;

public class MemberDao extends Dao {

    public Integer countByApp_idOrLikeUser_name(String app_id, String user_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Member.APP_ID, app_id);
        sqlMap.put(User.USER_NAME, user_name);
        SqlPara sqlPara = Db.getSqlPara("member.countByApp_idOrLikeUser_name", sqlMap);

        logSql("member", "countByApp_idOrLikeUser_name", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByOrApp_idOrLikeUser_name(String app_id, String user_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Member.APP_ID, app_id);
        sqlMap.put(User.USER_NAME, user_name);
        SqlPara sqlPara = Db.getSqlPara("member.countByOrApp_idOrLikeUser_name", sqlMap);

        logSql("member", "countByOrApp_idOrLikeUser_name", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<Member> listByMember_parent_pathLikeMember_parent_id(String member_parent_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Member.MEMBER_PARENT_ID, member_parent_id);
        SqlPara sqlPara = Db.getSqlPara("member.listByMember_parent_pathLikeMember_parent_id", sqlMap);

        logSql("member", "listByMember_parent_pathLikeMember_parent_id", sqlPara);

        return new Member().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Member> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Member.APP_ID, app_id);
        sqlMap.put(Member.SYSTEM_CREATE_TIME, system_create_time);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("member.listByApp_idAndSystem_create_timeAndLimit", sqlMap);

        logSql("member", "listByApp_idAndSystem_create_timeAndLimit", sqlPara);

        return new Member().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Member> listByApp_idOrLikeUser_nameAndLimit(String app_id, String user_name, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Member.APP_ID, app_id);
        sqlMap.put(User.USER_NAME, user_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("member.listByApp_idOrLikeUser_nameAndLimit", sqlMap);

        logSql("member", "listByApp_idOrLikeUser_nameAndLimit", sqlPara);

        return new Member().find(sqlPara.getSql(), sqlPara.getPara());
    }
    
    public List<Member> listByApp_id(String app_id) {
    	Kv sqlMap = Kv.create();
    	sqlMap.put(Member.APP_ID, app_id);
    	SqlPara sqlPara = Db.getSqlPara("member.listByApp_id", sqlMap);
    	
    	logSql("member", "listByApp_id", sqlPara);
    	
    	return new Member().find(sqlPara.getSql(), sqlPara.getPara());
    }
    
    public List<Member> listByOrApp_id(String app_id) {
    	Kv sqlMap = Kv.create();
    	sqlMap.put(Member.APP_ID, app_id);
    	SqlPara sqlPara = Db.getSqlPara("member.listByOrApp_id", sqlMap);
    	
    	logSql("member", "listByOrApp_id", sqlPara);
    	
    	return new Member().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Member> listByOrApp_idOrLikeUser_nameAndLimit(String app_id, String user_name, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Member.APP_ID, app_id);
        sqlMap.put(User.USER_NAME, user_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("member.listByOrApp_idOrLikeUser_nameAndLimit", sqlMap);

        logSql("member", "listByOrApp_idOrLikeUser_nameAndLimit", sqlPara);

        return new Member().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public Member findByMember_id(String member_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Member.MEMBER_ID, member_id);
        SqlPara sqlPara = Db.getSqlPara("member.findByMember_id", sqlMap);

        logSql("member", "findByMember_id", sqlPara);

        List<Member> memberList = new Member().find(sqlPara.getSql(), sqlPara.getPara());
        if (memberList.size() == 0) {
            return null;
        } else {
            return memberList.get(0);
        }
    }

    public Boolean save(String member_id, String app_id, String user_id, String member_parent_id, String from_qrcode_id, String qrcode_id, String member_level_id, String member_parent_path, Boolean member_status, String system_create_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Member.MEMBER_ID, member_id);
        sqlMap.put(Member.APP_ID, app_id);
        sqlMap.put(Member.USER_ID, user_id);
        sqlMap.put(Member.MEMBER_PARENT_ID, member_parent_id);
        sqlMap.put(Member.FROM_QRCODE_ID, from_qrcode_id);
        sqlMap.put(Member.QRCODE_ID, qrcode_id);
        sqlMap.put(Member.MEMBER_LEVEL_ID, member_level_id);
        sqlMap.put(Member.MEMBER_PARENT_PATH, member_parent_path);
        sqlMap.put(Member.MEMBER_STATUS, member_status);
        sqlMap.put(Member.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(Member.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(Member.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(Member.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Member.SYSTEM_VERSION, 0);
        sqlMap.put(Member.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("member.save", sqlMap);

        logSql("member", "save", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean update(String member_id, String user_id, String member_parent_id, String from_qrcode_id, String qrcode_id, String member_level_id, String member_parent_path, Boolean member_status, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Member.MEMBER_ID, member_id);
        sqlMap.put(Member.USER_ID, user_id);
        sqlMap.put(Member.MEMBER_PARENT_ID, member_parent_id);
        sqlMap.put(Member.FROM_QRCODE_ID, from_qrcode_id);
        sqlMap.put(Member.QRCODE_ID, qrcode_id);
        sqlMap.put(Member.MEMBER_LEVEL_ID, member_level_id);
        sqlMap.put(Member.MEMBER_PARENT_PATH, member_parent_path);
        sqlMap.put(Member.MEMBER_STATUS, member_status);
        sqlMap.put(Member.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(Member.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Member.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("member.update", sqlMap);

        logSql("member", "update", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }
    
    public Boolean deleteByMember_idAndSystem_version(String member_id, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Member.MEMBER_ID, member_id);
        sqlMap.put(Member.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(Member.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Member.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("member.deleteByMember_idAndSystem_version", sqlMap);

        logSql("member", "deleteByMember_idAndSystem_version", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}