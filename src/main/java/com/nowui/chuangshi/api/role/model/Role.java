package com.nowui.chuangshi.api.role.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Entity
public class Role extends Model<Role> {

    @Table
    public static final String TABLE_ROLE = "table_role";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "角色编号", updatable = false)
    public static final String ROLE_ID = "role_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 50, comment = "名称")
    public static final String ROLE_NAME = "role_name";

    @Column(type = ColumnType.VARCHAR, length = 25, comment = "编码")
    public static final String ROLE_CODE = "role_code";

    @Column(type = ColumnType.VARCHAR, length = 200, comment = "描述")
    public static final String ROLE_DESCRIPTION = "role_description";

    @Column(type = ColumnType.INT, length = 5, comment = "排序")
    public static final String ROLE_SORT = "role_sort";

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

    public String getRole_id() {
        return getStr(ROLE_ID);
    }

    public void setRole_id(String role_id) {
        set(ROLE_ID, role_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getRole_name() {
        return getStr(ROLE_NAME);
    }

    public void setRole_name(String role_name) {
        set(ROLE_NAME, role_name);
    }

    public String getRole_code() {
        return getStr(ROLE_CODE);
    }

    public void setRole_code(String role_code) {
        set(ROLE_CODE, role_code);
    }

    public String getRole_description() {
        return getStr(ROLE_DESCRIPTION);
    }

    public void setRole_description(String role_description) {
        set(ROLE_DESCRIPTION, role_description);
    }

    public Integer getRole_sort() {
        return getInt(ROLE_SORT);
    }

    public void setRole_sort(Integer role_sort) {
        set(ROLE_SORT, role_sort);
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