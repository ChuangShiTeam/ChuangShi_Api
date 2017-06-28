package com.nowui.chuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.Sql;

import java.util.Date;
import java.util.List;

public class SqlDao extends Dao {

    public Integer countByApp_id(String app_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Sql.APP_ID, app_id);
        SqlPara sqlPara = Db.getSqlPara("sql.countByApp_id", sqlMap);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByOrApp_id(String app_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Sql.APP_ID, app_id);
        SqlPara sqlPara = Db.getSqlPara("sql.countByOrApp_id", sqlMap);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<Sql> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Sql.APP_ID, app_id);
        sqlMap.put(Sql.SYSTEM_CREATE_TIME, system_create_time);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("sql.listByApp_idAndSystem_create_timeAndLimit", sqlMap);

        return new Sql().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Sql> listByApp_idAndLimit(String app_id, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Sql.APP_ID, app_id);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("sql.listByApp_idAndLimit", sqlMap);

        return new Sql().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Sql> listByOrApp_idAndLimit(String app_id, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Sql.APP_ID, app_id);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("sql.listByOrApp_idAndLimit", sqlMap);

        return new Sql().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public Sql findBySql_id(String sql_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Sql.SQL_ID, sql_id);
        SqlPara sqlPara = Db.getSqlPara("sql.findBySql_id", sqlMap);

        List<Sql> sqlList = new Sql().find(sqlPara.getSql(), sqlPara.getPara());
        if (sqlList.size() == 0) {
            return null;
        } else {
            return sqlList.get(0);
        }
    }

    public Boolean save(String sql_id, String app_id, String http_id, String sql_table, String sql_action, String sql_content, String system_create_user_id) {
        sql_content = sql_content.replaceAll("\\n", "");
        sql_content = sql_content.replaceAll("  ", " ");
        sql_content = sql_content.replaceAll("  ", " ");
        sql_content = sql_content.replaceAll("  ", " ");
        sql_content = sql_content.replaceAll("  ", " ");
        sql_content = sql_content.replaceAll("\\( ", "(");
        sql_content = sql_content.replaceAll(" \\)", ")");
        if (sql_content.startsWith(" ")) {
            sql_content = sql_content.substring(1);
        }

        Kv sqlMap = Kv.create();
        sqlMap.put(Sql.SQL_ID, sql_id);
        sqlMap.put(Sql.APP_ID, app_id);
        sqlMap.put(Sql.HTTP_ID, http_id);
        sqlMap.put(Sql.SQL_TABLE, sql_table);
        sqlMap.put(Sql.SQL_ACTION, sql_action);
        sqlMap.put(Sql.SQL_CONTENT, sql_content);
        sqlMap.put(Sql.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(Sql.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(Sql.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(Sql.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Sql.SYSTEM_VERSION, 0);
        sqlMap.put(Sql.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("sql.save", sqlMap);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean update(String sql_table, String sql_action, String sql_content, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Sql.SQL_TABLE, sql_table);
        sqlMap.put(Sql.SQL_ACTION, sql_action);
        sqlMap.put(Sql.SQL_CONTENT, sql_content);
        sqlMap.put(Sql.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(Sql.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Sql.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("sql.update", sqlMap);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteBySql_idAndSystem_version(String sql_id, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Sql.SQL_ID, sql_id);
        sqlMap.put(Sql.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(Sql.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Sql.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("sql.deleteBySql_idAndSystem_version", sqlMap);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}