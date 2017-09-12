package com.nowui.chuangshi.api.http.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Entity
public class Http extends Model<Http> {

    @Table
    public static final String TABLE_HTTP = "table_http";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "请求编号", updatable = false)
    public static final String HTTP_ID = "http_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "请求地址")
    public static final String HTTP_URL = "http_url";

    @Column(type = ColumnType.VARCHAR, length = 3, comment = "请求状态码")
    public static final String HTTP_CODE = "http_code";

    @Column(type = ColumnType.TEXT, length = 65535, comment = "请求参数")
    public static final String HTTP_REQUEST = "http_request";

    @Column(type = ColumnType.LONGTEXT, length = 0, comment = "请求返回")
    public static final String HTTP_RESPONSE = "http_response";

    @Column(type = ColumnType.VARCHAR, length = 120, comment = "请求Token")
    public static final String HTTP_TOKEN = "http_token";

    @Column(type = ColumnType.VARCHAR, length = 10, comment = "请求平台")
    public static final String HTTP_PLATFORM = "http_platform";

    @Column(type = ColumnType.VARCHAR, length = 10, comment = "请求版本")
    public static final String HTTP_VERSION = "http_version";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "请求IP地址")
    public static final String HTTP_IP_ADDRESS = "http_ip_address";

    @Column(type = ColumnType.VARCHAR, length = 10, comment = "请求响应时间")
    public static final String HTTP_RUN_TIME = "http_run_time";

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

    public String getHttp_id() {
        return getStr(HTTP_ID);
    }

    public void setHttp_id(String http_id) {
        set(HTTP_ID, http_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getHttp_url() {
        return getStr(HTTP_URL);
    }

    public void setHttp_url(String http_url) {
        set(HTTP_URL, http_url);
    }

    public String getHttp_code() {
        return getStr(HTTP_CODE);
    }

    public void setHttp_code(String http_code) {
        set(HTTP_CODE, http_code);
    }

    public String getHttp_request() {
        return getStr(HTTP_REQUEST);
    }

    public void setHttp_request(String http_request) {
        set(HTTP_REQUEST, http_request);
    }

    public String getHttp_response() {
        return getStr(HTTP_RESPONSE);
    }

    public void setHttp_response(String http_response) {
        set(HTTP_RESPONSE, http_response);
    }

    public String getHttp_token() {
        return getStr(HTTP_TOKEN);
    }

    public void setHttp_token(String http_token) {
        set(HTTP_TOKEN, http_token);
    }

    public String getHttp_platform() {
        return getStr(HTTP_PLATFORM);
    }

    public void setHttp_platform(String http_platform) {
        set(HTTP_PLATFORM, http_platform);
    }

    public String getHttp_version() {
        return getStr(HTTP_VERSION);
    }

    public void setHttp_version(String http_version) {
        set(HTTP_VERSION, http_version);
    }

    public String getHttp_ip_address() {
        return getStr(HTTP_IP_ADDRESS);
    }

    public void setHttp_ip_address(String http_ip_address) {
        set(HTTP_IP_ADDRESS, http_ip_address);
    }

    public String getHttp_run_time() {
        return getStr(HTTP_RUN_TIME);
    }

    public void setHttp_run_time(String http_run_time) {
        set(HTTP_RUN_TIME, http_run_time);
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