package com.nowui.chuangshi.model;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class Http extends Model<Http> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "请求编号")
    public static final String HTTP_ID = "http_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号")
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 255, comment = "请求地址")
    public static final String HTTP_URL = "http_url";

    @Column(type = ColumnType.VARCHAR, length = 3, comment = "请求状态码")
    public static final String HTTP_CODE = "http_code";

    @Column(type = ColumnType.LONGTEXT, length = 0, comment = "请求参数")
    public static final String HTTP_REQUEST = "http_request";

    @Column(type = ColumnType.LONGTEXT, length = 0, comment = "请求返回")
    public static final String HTTP_RESPONSE = "http_response";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "授权编号")
    public static final String HTTP_TOKEN = "http_token";

    @Column(type = ColumnType.VARCHAR, length = 10, comment = "请求平台")
    public static final String HTTP_PLATFORM = "http_platform";

    @Column(type = ColumnType.VARCHAR, length = 10, comment = "请求版本")
    public static final String HTTP_VERSION = "http_version";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "请求ip地址")
    public static final String HTTP_IP_ADDRESS = "http_ip_address";

    @Column(type = ColumnType.VARCHAR, length = 10, comment = "请求响应时间")
    public static final String HTTP_RUN_TIME = "http_run_time";

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

    public String getHttp_code() {
        return getStr(HTTP_CODE);
    }

    public void setHttp_code(String http_code) {
        set(HTTP_CODE, http_code);
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

}