package com.nowui.chuangshi.api.app.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Entity
public class AppConfig extends Model<AppConfig> {

    @Table
    public static final String TABLE_APP_CONFIG = "table_app_config";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "配置编号", updatable = false)
    public static final String CONFIG_ID = "config_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "配置分类编号")
    public static final String CONFIG_CATEGORY_ID = "config_category_id";

    @Column(type = ColumnType.VARCHAR, length = 50, comment = "键")
    public static final String CONFIG_KEY = "config_key";

    @Column(type = ColumnType.VARCHAR, length = 200, comment = "值")
    public static final String CONFIG_VALUE = "config_value";

    @Column(type = ColumnType.TINYINT, length = 1, comment = "是否禁用")
    public static final String CONFIG_IS_DISABLED = "config_is_disabled";

    @Column(type = ColumnType.VARCHAR, length = 200, comment = "描述")
    public static final String CONFIG_DESCRIPTION = "config_description";

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

    public String getConfig_id() {
        return getStr(CONFIG_ID);
    }

    public void setConfig_id(String config_id) {
        set(CONFIG_ID, config_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getConfig_category_id() {
        return getStr(CONFIG_CATEGORY_ID);
    }

    public void setConfig_category_id(String config_category_id) {
        set(CONFIG_CATEGORY_ID, config_category_id);
    }

    public String getConfig_key() {
        return getStr(CONFIG_KEY);
    }

    public void setConfig_key(String config_key) {
        set(CONFIG_KEY, config_key);
    }

    public String getConfig_value() {
        return getStr(CONFIG_VALUE);
    }

    public void setConfig_value(String config_value) {
        set(CONFIG_VALUE, config_value);
    }

    public Boolean getConfig_is_disabled() {
        return getBoolean(CONFIG_IS_DISABLED);
    }

    public void setConfig_is_disabled(Boolean config_is_disabled) {
        set(CONFIG_IS_DISABLED, config_is_disabled);
    }

    public String getConfig_description() {
        return getStr(CONFIG_DESCRIPTION);
    }

    public void setConfig_description(String config_description) {
        set(CONFIG_DESCRIPTION, config_description);
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