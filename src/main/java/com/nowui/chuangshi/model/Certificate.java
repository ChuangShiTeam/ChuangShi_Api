package com.nowui.chuangshi.model;

import java.util.Date;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class Certificate extends Model<Certificate> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "授权编号")
    public static final String CERTIFICATE_ID = "certificate_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号")
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "用户编号")
    public static final String USER_ID = "user_id";

    @Column(type = ColumnType.VARCHAR, length = 19, comment = "授权编号")
    public static final String CERTIFICATE_NUMBER = "certificate_number";

    @Column(type = ColumnType.DATETIME, length = 0, comment = "授权开始日期")
    public static final String CERTIFICATE_START_DATE = "certificate_start_date";

    @Column(type = ColumnType.DATETIME, length = 0, comment = "授权结束日期")
    public static final String CERTIFICATE_END_DATE = "certificate_end_date";

    @Column(type = ColumnType.TINYINT, length = 1, comment = "")
    public static final String CERTIFICATE_IS_PAY = "certificate_is_pay";

    public String getCertificate_id() {
        return getStr(CERTIFICATE_ID);
    }

    public void setCertificate_id(String certificate_id) {
        set(CERTIFICATE_ID, certificate_id);
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

    public String getCertificate_number() {
        return getStr(CERTIFICATE_NUMBER);
    }

    public void setCertificate_number(String certificate_number) {
        set(CERTIFICATE_NUMBER, certificate_number);
    }

    public Date getCertificate_start_date() {
        return getDate(CERTIFICATE_START_DATE);
    }

    public void setCertificate_start_date(Date certificate_start_date) {
        set(CERTIFICATE_START_DATE, certificate_start_date);
    }

    public Date getCertificate_end_date() {
        return getDate(CERTIFICATE_END_DATE);
    }

    public void setCertificate_end_date(Date certificate_end_date) {
        set(CERTIFICATE_END_DATE, certificate_end_date);
    }

    public Boolean getCertificate_is_pay() {
        return getBoolean(CERTIFICATE_IS_PAY);
    }

    public void setCertificate_is_pay(Boolean certificate_is_pay) {
        set(CERTIFICATE_IS_PAY, certificate_is_pay);
    }

}