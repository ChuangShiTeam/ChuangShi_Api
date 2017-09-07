package com.nowui.chuangshi.api.app.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Entity
public class App extends Model<App> {

    @Table
    public static final String TABLE_APP = "table_app";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号", updatable = false)
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

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "wechat_token")
    public static final String WECHAT_TOKEN = "wechat_token";

    @Column(type = ColumnType.VARCHAR, length = 43, comment = "wechat_encoding_aes_key")
    public static final String WECHAT_ENCODING_AES_KEY = "wechat_encoding_aes_key";

    @Column(type = ColumnType.TINYINT, length = 1, comment = "是否建仓库")
    public static final String APP_IS_CREATE_WAREHOUSE = "app_is_create_warehouse";

    @Column(type = ColumnType.TINYINT, length = 1, comment = "是否发货")
    public static final String APP_IS_DELIVERY = "app_is_delivery";

    @Column(type = ColumnType.TINYINT, length = 1, comment = "是否审核会员")
    public static final String APP_IS_AUDIT_MEMBER = "app_is_audit_member";

    @Column(type = ColumnType.TINYINT, length = 1, comment = "是否分成")
    public static final String APP_IS_COMMISSION = "app_is_commission";

    @Column(type = ColumnType.INT, length = 5, comment = "参与分成的上级层数")
    public static final String APP_COMMISSION_LEVEL = "app_commission_level";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "网站路径")
    public static final String APP_WEBSITE_PATH = "app_website_path";

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

    public String getWechat_token() {
        return getStr(WECHAT_TOKEN);
    }

    public void setWechat_token(String wechat_token) {
        set(WECHAT_TOKEN, wechat_token);
    }

    public String getWechat_encoding_aes_key() {
        return getStr(WECHAT_ENCODING_AES_KEY);
    }

    public void setWechat_encoding_aes_key(String wechat_encoding_aes_key) {
        set(WECHAT_ENCODING_AES_KEY, wechat_encoding_aes_key);
    }

    public Boolean getApp_is_create_warehouse() {
        return getBoolean(APP_IS_CREATE_WAREHOUSE);
    }

    public void setApp_is_create_warehouse(Boolean app_is_create_warehouse) {
        set(APP_IS_CREATE_WAREHOUSE, app_is_create_warehouse);
    }

    public Boolean getApp_is_delivery() {
        return getBoolean(APP_IS_DELIVERY);
    }

    public void setApp_is_delivery(Boolean app_is_delivery) {
        set(APP_IS_DELIVERY, app_is_delivery);
    }

    public Boolean getApp_is_audit_member() {
        return getBoolean(APP_IS_AUDIT_MEMBER);
    }

    public void setApp_is_audit_member(Boolean app_is_audit_member) {
        set(APP_IS_AUDIT_MEMBER, app_is_audit_member);
    }

    public Boolean getApp_is_commission() {
        return getBoolean(APP_IS_COMMISSION);
    }

    public void setApp_is_commission(Boolean app_is_commission) {
        set(APP_IS_COMMISSION, app_is_commission);
    }

    public Integer getApp_commission_level() {
        return getInt(APP_COMMISSION_LEVEL);
    }

    public void setApp_commission_level(Integer app_commission_level) {
        set(APP_COMMISSION_LEVEL, app_commission_level);
    }

    public String getApp_website_path() {
        return getStr(APP_WEBSITE_PATH);
    }

    public void setApp_website_path(String app_website_path) {
        set(APP_WEBSITE_PATH, app_website_path);
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