package com.nowui.chuangshi.api.certificate.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Entity
public class Certificate extends Model<Certificate> {

    @Table
    public static final String TABLE_CERTIFICATE = "table_certificate";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "授权记录编号", updatable = false)
    public static final String CERTIFICATE_ID = "certificate_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "用户编号")
    public static final String USER_ID = "user_id";

    @Column(type = ColumnType.VARCHAR, length = 19, comment = "授权编号")
    public static final String CERTIFICATE_NUMBER = "certificate_number";

    @Column(type = ColumnType.DATE, length = 0, comment = "授权开始日期")
    public static final String CERTIFICATE_START_DATE = "certificate_start_date";

    @Column(type = ColumnType.DATE, length = 0, comment = "授权结束日期")
    public static final String CERTIFICATE_END_DATE = "certificate_end_date";

    @Column(type = ColumnType.TINYINT, length = 1, comment = "授权是否支付")
    public static final String CERTIFICATE_IS_PAY = "certificate_is_pay";

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