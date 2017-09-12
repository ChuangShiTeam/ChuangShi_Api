package com.nowui.chuangshi.api.sql.service;

import com.nowui.chuangshi.api.sql.dao.SqlDao;
import com.nowui.chuangshi.api.sql.model.Sql;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class SqlService extends Service {

    public static final SqlService instance = new SqlService();
    private final String SQL_ITEM_CACHE = "sql_item_cache";
    private final SqlDao sqlDao = new SqlDao();

    public Integer adminCount(String app_id, String sql_table, String sql_action) {
        Cnd cnd = new Cnd();
        cnd.where(Sql.SYSTEM_STATUS, true);
        cnd.and(Sql.APP_ID, app_id);
        cnd.andAllowEmpty(Sql.SQL_TABLE, sql_table);
        cnd.andAllowEmpty(Sql.SQL_ACTION, sql_action);

        Integer count = sqlDao.count(cnd);
        return count;
    }

    public List<Sql> adminList(String app_id, String sql_table, String sql_action, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(Sql.SYSTEM_STATUS, true);
        cnd.and(Sql.APP_ID, app_id);
        cnd.andAllowEmpty(Sql.SQL_TABLE, sql_table);
        cnd.andAllowEmpty(Sql.SQL_ACTION, sql_action);
        cnd.paginate(m, n);

        List<Sql> sqlList = sqlDao.primaryKeyList(cnd);
        for (Sql sql : sqlList) {
            sql.put(find(sql.getSql_id()));
        }
        return sqlList;
    }

    public Sql find(String sql_id) {
        Sql sql = CacheUtil.get(SQL_ITEM_CACHE, sql_id);

        if (sql == null) {
            sql = sqlDao.find(sql_id);

            CacheUtil.put(SQL_ITEM_CACHE, sql_id, sql);
        }

        return sql;
    }

    public Boolean save(Sql sql, String system_create_user_id) {
        Boolean success = sqlDao.save(sql, system_create_user_id);
        return success;
    }

    public Boolean update(Sql sql, String sql_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(Sql.SYSTEM_STATUS, true);
        cnd.and(Sql.SQL_ID, sql_id);
        cnd.and(Sql.SYSTEM_VERSION, system_version);

        Boolean success = sqlDao.update(sql, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(SQL_ITEM_CACHE, sql_id);
        }

        return success;
    }

    public Boolean delete(String sql_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(Sql.SYSTEM_STATUS, true);
        cnd.and(Sql.SQL_ID, sql_id);
        cnd.and(Sql.SYSTEM_VERSION, system_version);

        Boolean success = sqlDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(SQL_ITEM_CACHE, sql_id);
        }

        return success;
    }

}