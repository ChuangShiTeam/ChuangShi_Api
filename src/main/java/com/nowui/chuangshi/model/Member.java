package com.nowui.chuangshi.model;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class Member extends Model<Member> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "会员编号")
    public static final String MEMBER_ID = "member_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号")
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

}