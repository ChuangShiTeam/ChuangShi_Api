package com.nowui.chuangshi.service;

import com.nowui.chuangshi.cache.SqlCache;
import com.nowui.chuangshi.model.Sql;

import java.util.Date;
import java.util.List;

public class SqlService extends Service {

    private SqlCache sqlCache = new SqlCache();

    public Integer countByApp_id(String app_id) {
        return sqlCache.countByApp_id(app_id);
    }

    public Integer countByOrApp_id(String app_id) {
        return sqlCache.countByOrApp_id(app_id);
    }

    public List<Sql> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        return sqlCache.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);
    }

    public List<Sql> listByApp_idAndLimit(String app_id, int m, int n) {
        return sqlCache.listByApp_idAndLimit(app_id, m, n);
    }

    public List<Sql> listByOrApp_idAndLimit(String app_id, int m, int n) {
        return sqlCache.listByOrApp_idAndLimit(app_id, m, n);
    }

    public Sql findBySql_id(String sql_id) {
        return sqlCache.findBySql_id(sql_id);
    }

    public Boolean save(String sql_id, String app_id, String http_id, String sql_table, String sql_action, String sql_content, String system_create_user_id) {
        return sqlCache.save(sql_id, app_id, http_id, sql_table, sql_action, sql_content, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String sql_id, String sql_table, String sql_action, String sql_content, String system_update_user_id, Integer system_version) {
        return sqlCache.updateValidateSystem_version(sql_id, sql_table, sql_action, sql_content, system_update_user_id, system_version);
    }

    public Boolean deleteBySql_idAndSystem_update_user_idValidateSystem_version(String sql_id, String system_update_user_id, Integer system_version) {
        return sqlCache.deleteBySql_idAndSystem_update_user_idValidateSystem_version(sql_id, system_update_user_id, system_version);
    }

}