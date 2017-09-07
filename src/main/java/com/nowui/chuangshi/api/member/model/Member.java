package com.nowui.chuangshi.api.member.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

public class Member extends Model<Member> {

    @Table()
    public static final String TABLE_MEMBER = "table_member";

    @Primary()
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "会员编号", updatable = false)
    public static final String MEMBER_ID = "member_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "用户编号")
    public static final String USER_ID = "user_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "上级编号")
    public static final String MEMBER_PARENT_ID = "member_parent_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "扫码二维码编号")
    public static final String FROM_QRCODE_ID = "from_qrcode_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "二维码编号")
    public static final String QRCODE_ID = "qrcode_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "等级编号")
    public static final String MEMBER_LEVEL_ID = "member_level_id";

    @Column(type = ColumnType.VARCHAR, length = 1000, comment = "会员路径")
    public static final String MEMBER_PARENT_PATH = "member_parent_path";

    @Column(type = ColumnType.TINYINT, length = 1, comment = "会员状态")
    public static final String MEMBER_STATUS = "member_status";

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

    public String getMember_id() {
        return getStr(MEMBER_ID);
    }

    public void setMember_id(String member_id) {
        set(MEMBER_ID, member_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getUser_id() {
        return getStr(USER_ID);
    }

    public void setUser_id(String user_id) {
        set(USER_ID, user_id);
    }

    public String getMember_parent_id() {
        return getStr(MEMBER_PARENT_ID);
    }

    public void setMember_parent_id(String member_parent_id) {
        set(MEMBER_PARENT_ID, member_parent_id);
    }

    public String getFrom_qrcode_id() {
        return getStr(FROM_QRCODE_ID);
    }

    public void setFrom_qrcode_id(String from_qrcode_id) {
        set(FROM_QRCODE_ID, from_qrcode_id);
    }

    public String getQrcode_id() {
        return getStr(QRCODE_ID);
    }

    public void setQrcode_id(String qrcode_id) {
        set(QRCODE_ID, qrcode_id);
    }

    public String getMember_level_id() {
        return getStr(MEMBER_LEVEL_ID);
    }

    public void setMember_level_id(String member_level_id) {
        set(MEMBER_LEVEL_ID, member_level_id);
    }

    public String getMember_parent_path() {
        return getStr(MEMBER_PARENT_PATH);
    }

    public void setMember_parent_path(String member_parent_path) {
        set(MEMBER_PARENT_PATH, member_parent_path);
    }

    public Boolean getMember_status() {
        return getBoolean(MEMBER_STATUS);
    }

    public void setMember_status(Boolean member_status) {
        set(MEMBER_STATUS, member_status);
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