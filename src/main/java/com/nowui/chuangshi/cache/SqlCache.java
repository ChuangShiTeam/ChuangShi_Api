package com.nowui.chuangshi.cache;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.SqlDao;
import com.nowui.chuangshi.model.Sql;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.Date;
import java.util.List;

public class SqlCache extends Cache {

    public static final String SQL_BY_SQL_ID_CACHE = "sql_by_sql_id_cache";

    private SqlDao sqlDao = new SqlDao();

    public Integer countByApp_id(String app_id) {
        return sqlDao.countByApp_id(app_id);
    }

    public Integer countByOrApp_id(String app_id) {
        return sqlDao.countByOrApp_id(app_id);
    }

    public List<Sql> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        List<Sql> sqlList = sqlDao.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);

        for (Sql sql : sqlList) {
            sql.put(findBySql_id(sql.getSql_id()));
        }

        return sqlList;
    }

    public List<Sql> listByApp_idAndLimit(String app_id, int m, int n) {
        List<Sql> sqlList = sqlDao.listByApp_idAndLimit(app_id, m, n);

        for (Sql sql : sqlList) {
            sql.put(findBySql_id(sql.getSql_id()));
        }

        return sqlList;
    }

    public List<Sql> listByOrApp_idAndLimit(String app_id, int m, int n) {
        List<Sql> sqlList = sqlDao.listByOrApp_idAndLimit(app_id, m, n);

        for (Sql sql : sqlList) {
            sql.put(findBySql_id(sql.getSql_id()));
        }

        return sqlList;
    }

    public Sql findBySql_id(String sql_id) {
        Sql sql = CacheUtil.get(SQL_BY_SQL_ID_CACHE, sql_id);

        if (sql == null) {
            sql = sqlDao.findBySql_id(sql_id);

            CacheUtil.put(SQL_BY_SQL_ID_CACHE, sql_id, sql);
        }

        return sql;
    }

    public Boolean save(String sql_id, String app_id, String http_id, String sql_table, String sql_action, String sql_content, String system_create_user_id) {
        return sqlDao.save(sql_id, app_id, http_id, sql_table, sql_action, sql_content, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String sql_id, String sql_table, String sql_action, String sql_content, String system_update_user_id, Integer system_version) {
        Sql sql = findBySql_id(sql_id);
        if (!sql.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = sqlDao.update(sql_table, sql_action, sql_content, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(SQL_BY_SQL_ID_CACHE, sql_id);
        }

        return result;
    }

    public Boolean deleteBySql_idAndSystem_update_user_idValidateSystem_version(String sql_id, String system_update_user_id, Integer system_version) {
        Sql sql = findBySql_id(sql_id);
        if (!sql.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = sqlDao.deleteBySql_idAndSystem_version(sql_id, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(SQL_BY_SQL_ID_CACHE, sql_id);
        }

        return result;
    }

}