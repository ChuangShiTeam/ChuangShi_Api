package com.nowui.chuangshi.api.exception.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Entity
public class Exception extends Model<Exception> {

    @Table
    public static final String TABLE_EXCEPTION = "table_exception";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "异常编号", updatable = false)
    public static final String EXCEPTION_ID = "exception_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "请求编号")
    public static final String HTTP_ID = "http_id";

    @Column(type = ColumnType.TEXT, length = 65535, comment = "异常内容")
    public static final String EXCEPTION_CONTENT = "exception_content";

    @Column(type = ColumnType.TINYINT, length = 1, comment = "异常是否确认")
    public static final String EXCEPTION_IS_CONFIRM = "exception_is_confirm";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "异常描述")
    public static final String EXCEPTION_REMARK = "exception_remark";

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

    public String getException_id() {
        return getStr(EXCEPTION_ID);
    }

    public void setException_id(String exception_id) {
        set(EXCEPTION_ID, exception_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getHttp_id() {
        return getStr(HTTP_ID);
    }

    public void setHttp_id(String http_id) {
        set(HTTP_ID, http_id);
    }

    public String getException_content() {
        return getStr(EXCEPTION_CONTENT);
    }

    public void setException_content(String exception_content) {
        set(EXCEPTION_CONTENT, exception_content);
    }

    public Boolean getException_is_confirm() {
        return getBoolean(EXCEPTION_IS_CONFIRM);
    }

    public void setException_is_confirm(Boolean exception_is_confirm) {
        set(EXCEPTION_IS_CONFIRM, exception_is_confirm);
    }

    public String getException_remark() {
        return getStr(EXCEPTION_REMARK);
    }

    public void setException_remark(String exception_remark) {
        set(EXCEPTION_REMARK, exception_remark);
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