package com.nowui.chuangshi.model;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class User extends Model<User> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "用户编号")
    public static final String USER_ID = "user_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号")
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "组织架构编号")
    public static final String ORGANIZATION_ID = "organization_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "角色编号")
    public static final String ROLE_ID = "role_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "用户等级编号")
    public static final String USER_LEVEL_ID = "user_level_id";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "用户类型")
    public static final String USER_TYPE = "user_type";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "用户帐号")
    public static final String USER_ACCOUNT = "user_account";

    @Column(type = ColumnType.VARCHAR, length = 11, comment = "用户电话")
    public static final String USER_PHONE = "user_phone";

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

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "扩展编号")
    public static final String EXTEND_ID = "extend_id";

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

    public String getOrganization_id() {
        return getStr(ORGANIZATION_ID);
    }

    public void setOrganization_id(String organization_id) {
        set(ORGANIZATION_ID, organization_id);
    }

    public String getRole_id() {
        return getStr(ROLE_ID);
    }

    public void setRole_id(String role_id) {
        set(ROLE_ID, role_id);
    }

    public String getUser_level_id() {
        return getStr(USER_LEVEL_ID);
    }

    public void setUser_level_id(String user_level_id) {
        set(USER_LEVEL_ID, user_level_id);
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

    public String getUser_phone() {
        return getStr(USER_PHONE);
    }

    public void setUser_phone(String user_phone) {
        set(USER_PHONE, user_phone);
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

    public String getExtend_id() {
        return getStr(EXTEND_ID);
    }

    public void SetExtend_id(String extend_id) {
        set(EXTEND_ID, extend_id);
    }

}