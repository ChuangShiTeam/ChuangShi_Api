package com.nowui.chuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.MemberLevel;

import java.util.Date;
import java.util.List;

public class MemberLevelDao extends Dao {

    public Integer countByApp_idOrLikeMember_level_name(String app_id, String member_level_name, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberLevel.APP_ID, app_id);
        sqlMap.put(MemberLevel.MEMBER_LEVEL_NAME, member_level_name);
        SqlPara sqlPara = Db.getSqlPara("member_level.countByApp_idOrLikeMember_level_name", sqlMap);

        logSql(request_app_id, request_http_id, "table_member_level", "countByApp_idOrLikeMember_level_name", sqlPara, request_user_id);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByOrApp_idOrLikeMember_level_name(String app_id, String member_level_name, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberLevel.APP_ID, app_id);
        sqlMap.put(MemberLevel.MEMBER_LEVEL_NAME, member_level_name);
        SqlPara sqlPara = Db.getSqlPara("member_level.countByOrApp_idOrLikeMember_level_name", sqlMap);

        logSql(request_app_id, request_http_id, "table_member_level", "countByOrApp_idOrLikeMember_level_name", sqlPara, request_user_id);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<MemberLevel> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberLevel.APP_ID, app_id);
        sqlMap.put(MemberLevel.SYSTEM_CREATE_TIME, system_create_time);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("member_level.listByApp_idAndSystem_create_timeAndLimit", sqlMap);

        logSql(request_app_id, request_http_id, "table_member_level", "listByApp_idAndSystem_create_timeAndLimit", sqlPara, request_user_id);

        return new MemberLevel().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<MemberLevel> listByApp_idOrLikeMember_level_nameAndLimit(String app_id, String member_level_name, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberLevel.APP_ID, app_id);
        sqlMap.put(MemberLevel.MEMBER_LEVEL_NAME, member_level_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("member_level.listByApp_idOrLikeMember_level_nameAndLimit", sqlMap);

        logSql(request_app_id, request_http_id, "table_member_level", "listByApp_idOrLikeMember_level_nameAndLimit", sqlPara, request_user_id);

        return new MemberLevel().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<MemberLevel> listByOrApp_idOrLikeMember_level_nameAndLimit(String app_id, String member_level_name, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberLevel.APP_ID, app_id);
        sqlMap.put(MemberLevel.MEMBER_LEVEL_NAME, member_level_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("member_level.listByOrApp_idOrLikeMember_level_nameAndLimit", sqlMap);

        logSql(request_app_id, request_http_id, "table_member_level", "listByOrApp_idOrLikeMember_level_nameAndLimit", sqlPara, request_user_id);

        return new MemberLevel().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public MemberLevel findByMember_level_id(String member_level_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberLevel.MEMBER_LEVEL_ID, member_level_id);
        SqlPara sqlPara = Db.getSqlPara("member_level.findByMember_level_id", sqlMap);

        logSql(request_app_id, request_http_id, "table_member_level", "findByMember_level_id", sqlPara, request_user_id);

        List<MemberLevel> member_levelList = new MemberLevel().find(sqlPara.getSql(), sqlPara.getPara());
        if (member_levelList.size() == 0) {
            return null;
        } else {
            return member_levelList.get(0);
        }
    }

    public Boolean save(String member_level_id, String app_id, String member_level_name, Integer member_level_value, Integer member_level_sort, String system_create_user_id, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberLevel.MEMBER_LEVEL_ID, member_level_id);
        sqlMap.put(MemberLevel.APP_ID, app_id);
        sqlMap.put(MemberLevel.MEMBER_LEVEL_NAME, member_level_name);
        sqlMap.put(MemberLevel.MEMBER_LEVEL_VALUE, member_level_value);
        sqlMap.put(MemberLevel.MEMBER_LEVEL_SORT, member_level_sort);
        sqlMap.put(MemberLevel.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(MemberLevel.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(MemberLevel.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(MemberLevel.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(MemberLevel.SYSTEM_VERSION, 0);
        sqlMap.put(MemberLevel.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("member_level.save", sqlMap);

        logSql(request_app_id, request_http_id, "table_member_level", "save", sqlPara, request_user_id);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean update(String member_level_id, String member_level_name, Integer member_level_value, Integer member_level_sort, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberLevel.MEMBER_LEVEL_ID, member_level_id);
        sqlMap.put(MemberLevel.MEMBER_LEVEL_NAME, member_level_name);
        sqlMap.put(MemberLevel.MEMBER_LEVEL_VALUE, member_level_value);
        sqlMap.put(MemberLevel.MEMBER_LEVEL_SORT, member_level_sort);
        sqlMap.put(MemberLevel.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(MemberLevel.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(MemberLevel.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("member_level.update", sqlMap);

        logSql(request_app_id, request_http_id, "table_member_level", "update", sqlPara, request_user_id);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByMember_level_idAndSystem_version(String member_level_id, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberLevel.MEMBER_LEVEL_ID, member_level_id);
        sqlMap.put(MemberLevel.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(MemberLevel.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(MemberLevel.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("member_level.deleteByMember_level_idAndSystem_version", sqlMap);

        logSql(request_app_id, request_http_id, "table_member_level", "deleteByMember_level_idAndSystem_version", sqlPara, request_user_id);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}