package com.nowui.chuangshi.model;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class App extends Model<App> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号")
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "应用名称")
    public static final String APP_NAME = "app_name";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "应用密钥")
    public static final String APP_SECRET = "app_secret";

    @Column(type = ColumnType.VARCHAR, length = 18, comment = "wechat_app_id")
    public static final String WECHAT_APP_ID = "wechat_app_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "wechat_app_secret")
    public static final String WECHAT_APP_SECRET = "wechat_app_secret";

    @Column(type = ColumnType.VARCHAR, length = 10, comment = "wechat_mch_id")
    public static final String WECHAT_MCH_ID = "wechat_mch_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "wechat_mch_key")
    public static final String WECHAT_MCH_KEY = "wechat_mch_key";

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getApp_name() {
        return getStr(APP_NAME);
    }

    public void setApp_name(String app_name) {
        set(APP_NAME, app_name);
    }

    public String getApp_secret() {
        return getStr(APP_SECRET);
    }

    public void setApp_secret(String app_secret) {
        set(APP_SECRET, app_secret);
    }

    public String getWechat_app_id() {
        return getStr(WECHAT_APP_ID);
    }

    public void setWechat_app_id(String wechat_app_id) {
        set(WECHAT_APP_ID, wechat_app_id);
    }

    public String getWechat_app_secret() {
        return getStr(WECHAT_APP_SECRET);
    }

    public void setWechat_app_secret(String wechat_app_secret) {
        set(WECHAT_APP_SECRET, wechat_app_secret);
    }

    public String getWechat_mch_id() {
        return getStr(WECHAT_MCH_ID);
    }

    public void setWechat_mch_id(String wechat_mch_id) {
        set(WECHAT_MCH_ID, wechat_mch_id);
    }

    public String getWechat_mch_key() {
        return getStr(WECHAT_MCH_KEY);
    }

    public void setWechat_mch_key(String wechat_mch_key) {
        set(WECHAT_MCH_KEY, wechat_mch_key);
    }

}