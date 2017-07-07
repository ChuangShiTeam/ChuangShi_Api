package com.nowui.chuangshi.model;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class Qrcode extends Model<Qrcode> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String QRCODE_ID = "qrcode_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String OBJECT_ID = "object_id";

    @Column(type = ColumnType.VARCHAR, length = 10, comment = "")
    public static final String QRCODE_TYPE = "qrcode_type";

    @Column(type = ColumnType.VARCHAR, length = 250, comment = "")
    public static final String QRCODE_URL = "qrcode_url";

    @Column(type = ColumnType.INT, length = 5, comment = "")
    public static final String QRCODE_ADD = "qrcode_add";

    @Column(type = ColumnType.INT, length = 5, comment = "")
    public static final String QRCODE_CANCEL = "qrcode_cancel";

    @Column(type = ColumnType.TINYINT, length = 1, comment = "")
    public static final String QRCODE_STATUS = "qrcode_status";

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

}