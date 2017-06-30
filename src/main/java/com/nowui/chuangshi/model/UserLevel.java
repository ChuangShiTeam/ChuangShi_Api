package com.nowui.chuangshi.model;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class UserLevel extends Model<UserLevel> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "用户等级编号")
    public static final String USER_LEVEL_ID = "user_level_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号")
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 10, comment = "用户等级名称")
    public static final String USER_LEVEL_NAME = "user_level_name";

    @Column(type = ColumnType.INT, length = 5, comment = "用户等级数值")
    public static final String USER_LEVEL_VALUE = "user_level_value";

    @Column(type = ColumnType.INT, length = 3, comment = "用户等级排序")
    public static final String USER_LEVEL_SORT = "user_level_sort";

    public String getUser_level_id() {
        return getStr(USER_LEVEL_ID);
    }

    public void setUser_level_id(String user_level_id) {
        set(USER_LEVEL_ID, user_level_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getUser_level_name() {
        return getStr(USER_LEVEL_NAME);
    }

    public void setUser_level_name(String user_level_name) {
        set(USER_LEVEL_NAME, user_level_name);
    }

    public Integer getUser_level_value() {
        return getInt(USER_LEVEL_VALUE);
    }

    public void setUser_level_value(Integer user_level_value) {
        set(USER_LEVEL_VALUE, user_level_value);
    }

    public Integer getUser_level_sort() {
        return getInt(USER_LEVEL_SORT);
    }

    public void setUser_level_sort(Integer user_level_sort) {
        set(USER_LEVEL_SORT, user_level_sort);
    }

}