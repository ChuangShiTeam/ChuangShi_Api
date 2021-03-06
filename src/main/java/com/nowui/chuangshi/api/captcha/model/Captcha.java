package com.nowui.chuangshi.api.captcha.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Entity
public class Captcha extends Model<Captcha> {

    @Table
    public static final String TABLE_CAPTCHA = "table_captcha";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "验证码编号", updatable = false)
    public static final String CAPTCHA_ID = "captcha_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "验证码类型")
    public static final String CAPTCHA_TYPE = "captcha_type";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "验证码手机")
    public static final String CAPTCHA_MOBILE = "captcha_mobile";

    @Column(type = ColumnType.VARCHAR, length = 6, comment = "验证码")
    public static final String CAPTCHA_CODE = "captcha_code";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "验证码IP地址")
    public static final String CAPTCHA_IP_ADDRESS = "captcha_ip_address";

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

    public String getCaptcha_id() {
        return getStr(CAPTCHA_ID);
    }

    public void setCaptcha_id(String captcha_id) {
        set(CAPTCHA_ID, captcha_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getCaptcha_type() {
        return getStr(CAPTCHA_TYPE);
    }

    public void setCaptcha_type(String captcha_type) {
        set(CAPTCHA_TYPE, captcha_type);
    }

    public String getCaptcha_mobile() {
        return getStr(CAPTCHA_MOBILE);
    }

    public void setCaptcha_mobile(String captcha_mobile) {
        set(CAPTCHA_MOBILE, captcha_mobile);
    }

    public String getCaptcha_code() {
        return getStr(CAPTCHA_CODE);
    }

    public void setCaptcha_code(String captcha_code) {
        set(CAPTCHA_CODE, captcha_code);
    }

    public String getCaptcha_ip_address() {
        return getStr(CAPTCHA_IP_ADDRESS);
    }

    public void setCaptcha_ip_address(String captcha_ip_address) {
        set(CAPTCHA_IP_ADDRESS, captcha_ip_address);
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