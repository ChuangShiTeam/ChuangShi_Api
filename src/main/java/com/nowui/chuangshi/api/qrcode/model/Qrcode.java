package com.nowui.chuangshi.api.qrcode.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

public class Qrcode extends Model<Qrcode> {

    @Table()
    public static final String TABLE_QRCODE = "table_qrcode";

    @Primary()
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "二维码编号", updatable = false)
    public static final String QRCODE_ID = "qrcode_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "外键编号")
    public static final String OBJECT_ID = "object_id";

    @Column(type = ColumnType.VARCHAR, length = 10, comment = "二维码类型")
    public static final String QRCODE_TYPE = "qrcode_type";

    @Column(type = ColumnType.VARCHAR, length = 200, comment = "二维码地址")
    public static final String QRCODE_URL = "qrcode_url";

    @Column(type = ColumnType.INT, length = 5, comment = "关注人数")
    public static final String QRCODE_ADD = "qrcode_add";

    @Column(type = ColumnType.INT, length = 5, comment = "取消人数")
    public static final String QRCODE_CANCEL = "qrcode_cancel";

    @Column(type = ColumnType.TINYINT, length = 1, comment = "二维码状态")
    public static final String QRCODE_STATUS = "qrcode_status";

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

    public String getQrcode_id() {
        return getStr(QRCODE_ID);
    }

    public void setQrcode_id(String qrcode_id) {
        set(QRCODE_ID, qrcode_id);
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

    public String getQrcode_type() {
        return getStr(QRCODE_TYPE);
    }

    public void setQrcode_type(String qrcode_type) {
        set(QRCODE_TYPE, qrcode_type);
    }

    public String getQrcode_url() {
        return getStr(QRCODE_URL);
    }

    public void setQrcode_url(String qrcode_url) {
        set(QRCODE_URL, qrcode_url);
    }

    public Integer getQrcode_add() {
        return getInt(QRCODE_ADD);
    }

    public void setQrcode_add(Integer qrcode_add) {
        set(QRCODE_ADD, qrcode_add);
    }

    public Integer getQrcode_cancel() {
        return getInt(QRCODE_CANCEL);
    }

    public void setQrcode_cancel(Integer qrcode_cancel) {
        set(QRCODE_CANCEL, qrcode_cancel);
    }

    public Boolean getQrcode_status() {
        return getBoolean(QRCODE_STATUS);
    }

    public void setQrcode_status(Boolean qrcode_status) {
        set(QRCODE_STATUS, qrcode_status);
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