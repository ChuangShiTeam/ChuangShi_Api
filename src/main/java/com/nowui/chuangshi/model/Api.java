package com.nowui.chuangshi.model;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class Api extends Model<Api> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "接口编号")
    public static final String API_ID = "api_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号")
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "接口名称")
    public static final String API_NAME = "api_name";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "接口地址")
    public static final String API_URL = "api_url";

    public String getApi_id() {
        return getStr(API_ID);
    }

    public void setApi_id(String api_id) {
        set(API_ID, api_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getApi_name() {
        return getStr(API_NAME);
    }

    public void setApi_name(String api_name) {
        set(API_NAME, api_name);
    }

    public String getApi_url() {
        return getStr(API_URL);
    }

    public void setApi_url(String api_url) {
        set(API_URL, api_url);
    }

}