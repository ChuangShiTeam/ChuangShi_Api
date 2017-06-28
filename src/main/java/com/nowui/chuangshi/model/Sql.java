package com.nowui.chuangshi.model;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class Sql extends Model<Sql> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "SQL编号")
    public static final String SQL_ID = "sql_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号")
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "请求编号")
    public static final String HTTP_ID = "http_id";

    @Column(type = ColumnType.VARCHAR, length = 50, comment = "SQL表格")
    public static final String SQL_TABLE = "sql_table";

    @Column(type = ColumnType.VARCHAR, length = 10, comment = "SQL动作")
    public static final String SQL_ACTION = "sql_action";

    @Column(type = ColumnType.VARCHAR, length = 65535, comment = "SQL内容")
    public static final String SQL_CONTENT = "sql_content";

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

}