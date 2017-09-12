package com.nowui.chuangshi.api.sql.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Entity
public class Sql extends Model<Sql> {

    @Table
    public static final String TABLE_SQL = "table_sql";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "SQL编号", updatable = false)
    public static final String SQL_ID = "sql_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "请求编号")
    public static final String HTTP_ID = "http_id";

    @Column(type = ColumnType.VARCHAR, length = 50, comment = "SQL数据表")
    public static final String SQL_TABLE = "sql_table";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "SQL动作")
    public static final String SQL_ACTION = "sql_action";

    @Column(type = ColumnType.TEXT, length = 65535, comment = "SQL内容")
    public static final String SQL_CONTENT = "sql_content";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "", updatable = false)
    public static final String SYSTEM_CREATE_USER_ID = "system_create_user_id";

    @Column(type = ColumnType.DATETIME, length = 0, comment = "", updatable = false)
    public static final String SYSTEM_CREATE_TIME = "system_create_time";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "", updatable = false)
    public static final String SYSTEM_UPDATE_USER_ID = "system_update_user_id";

    @Column(type = ColumnType.DATETIME, length = 0, comment = "", updatable = false)
    public static final String SYSTEM_UPDATE_TIME = "system_update_time";

    @Column(type = ColumnType.INT, length = 5, comment = "", updatable = false)
    public static final String SYSTEM_VERSION = "system_version";

    @Column(type = ColumnType.TINYINT, length = 1, comment = "", updatable = false)
    public static final String SYSTEM_STATUS = "system_status";

    public String getSql_id() {
        return getStr(SQL_ID);
    }

    public void setSql_id(String sql_id) {
        set(SQL_ID, sql_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getHttp_id() {
        return getStr(HTTP_ID);
    }

    public void setHttp_id(String http_id) {
        set(HTTP_ID, http_id);
    }

    public String getSql_table() {
        return getStr(SQL_TABLE);
    }

    public void setSql_table(String sql_table) {
        set(SQL_TABLE, sql_table);
    }

    public String getSql_action() {
        return getStr(SQL_ACTION);
    }

    public void setSql_action(String sql_action) {
        set(SQL_ACTION, sql_action);
    }

    public String getSql_content() {
        return getStr(SQL_CONTENT);
    }

    public void setSql_content(String sql_content) {
        set(SQL_CONTENT, sql_content);
    }

    public String getSystem_create_user_id() {
        return getStr(SYSTEM_CREATE_USER_ID);
    }

    public void setSystem_create_user_id(String system_create_user_id) {
        set(SYSTEM_CREATE_USER_ID, system_create_user_id);
    }

    public Date getSystem_create_time() {
        return getDate(SYSTEM_CREATE_TIME);
    }

    public void setSystem_create_time(Date system_create_time) {
        set(SYSTEM_CREATE_TIME, system_create_time);
    }


    public String getSystem_update_user_id() {
        return getStr(SYSTEM_UPDATE_USER_ID);
    }

    public void setSystem_update_user_id(String system_update_user_id) {
        set(SYSTEM_UPDATE_USER_ID, system_update_user_id);
    }

    public Date getSystem_update_time() {
        return getDate(SYSTEM_UPDATE_TIME);
    }

    public void setSystem_update_time(Date system_update_time) {
        set(SYSTEM_UPDATE_TIME, system_update_time);
    }


    public Integer getSystem_version() {
        return getInt(SYSTEM_VERSION);
    }

    public void setSystem_version(Integer system_version) {
        set(SYSTEM_VERSION, system_version);
    }

    public Boolean getSystem_status() {
        return getBoolean(SYSTEM_STATUS);
    }

    public void setSystem_status(Boolean system_status) {
        set(SYSTEM_STATUS, system_status);
    }

}