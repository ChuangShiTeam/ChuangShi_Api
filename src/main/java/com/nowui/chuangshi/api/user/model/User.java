package com.nowui.chuangshi.api.user.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Entity
public class User extends Model<User> {

    @Table
    public static final String TABLE_USER = "table_user";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "用户编号", updatable = false)
    public static final String USER_ID = "user_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "外键编号")
    public static final String OBJECT_ID = "object_id";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "用户类型")
    public static final String USER_TYPE = "user_type";

    @Column(type = ColumnType.VARCHAR, length = 30, comment = "用户帐号")
    public static final String USER_ACCOUNT = "user_account";

    @Column(type = ColumnType.VARCHAR, length = 11, comment = "用户电话")
    public static final String USER_MOBILE = "user_mobile";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "用户邮箱")
    public static final String USER_EMAIL = "user_email";

    @Column(type = ColumnType.VARCHAR, length = 128, comment = "用户密码")
    public static final String USER_PASSWORD = "user_password";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "用户名称")
    public static final String USER_NAME = "user_name";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "用户头像")
    public static final String USER_AVATAR = "user_avatar";

    @Column(type = ColumnType.VARCHAR, length = 50, comment = "wechat_open_id")
    public static final String WECHAT_OPEN_ID = "wechat_open_id";

    @Column(type = ColumnType.VARCHAR, length = 50, comment = "wechat_union_id")
    public static final String WECHAT_UNION_ID = "wechat_union_id";

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
    
    public static final String ORIGINAL_USER_PASSWORD = "original_user_password";

    public String getUser_id() {
        return getStr(USER_ID);
    }

    public void setUser_id(String user_id) {
        set(USER_ID, user_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getObject_id() {
        return getStr(OBJECT_ID);
    }

    public void setObject_id(String object_id) {
        set(OBJECT_ID, object_id);
    }

    public String getUser_type() {
        return getStr(USER_TYPE);
    }

    public void setUser_type(String user_type) {
        set(USER_TYPE, user_type);
    }

    public String getUser_account() {
        return getStr(USER_ACCOUNT);
    }

    public void setUser_account(String user_account) {
        set(USER_ACCOUNT, user_account);
    }

    public String getUser_mobile() {
        return getStr(USER_MOBILE);
    }

    public void setUser_mobile(String user_mobile) {
        set(USER_MOBILE, user_mobile);
    }

    public String getUser_email() {
        return getStr(USER_EMAIL);
    }

    public void setUser_email(String user_email) {
        set(USER_EMAIL, user_email);
    }

    public String getUser_password() {
        return getStr(USER_PASSWORD);
    }

    public void setUser_password(String user_password) {
        set(USER_PASSWORD, user_password);
    }

    public String getUser_name() {
        return getStr(USER_NAME);
    }

    public void setUser_name(String user_name) {
        set(USER_NAME, user_name);
    }

    public String getUser_avatar() {
        return getStr(USER_AVATAR);
    }

    public void setUser_avatar(String user_avatar) {
        set(USER_AVATAR, user_avatar);
    }

    public String getWechat_open_id() {
        return getStr(WECHAT_OPEN_ID);
    }

    public void setWechat_open_id(String wechat_open_id) {
        set(WECHAT_OPEN_ID, wechat_open_id);
    }

    public String getWechat_union_id() {
        return getStr(WECHAT_UNION_ID);
    }

    public void setWechat_union_id(String wechat_union_id) {
        set(WECHAT_UNION_ID, wechat_union_id);
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