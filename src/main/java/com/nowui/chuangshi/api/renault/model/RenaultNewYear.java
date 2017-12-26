package com.nowui.chuangshi.api.renault.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Entity
public class RenaultNewYear extends Model<RenaultNewYear> {

    @Table
    public static final String TABLE_RENAULT_NEW_YEAR = "table_renault_new_year";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "", updatable = false)
    public static final String NEW_YEAR_ID = "new_year_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 50, comment = "")
    public static final String NEW_YEAR_NAME = "new_year_name";

    @Column(type = ColumnType.VARCHAR, length = 11, comment = "")
    public static final String NEW_YEAR_PHONE = "new_year_phone";

    @Column(type = ColumnType.VARCHAR, length = 200, comment = "新年总结")
    public static final String NEW_YEAR_SUMMARY = "new_year_summary";

    @Column(type = ColumnType.VARCHAR, length = 200, comment = "")
    public static final String NEW_YEAR_WISH = "new_year_wish";

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

    public String getNew_year_id() {
        return getStr(NEW_YEAR_ID);
    }

    public void setNew_year_id(String new_year_id) {
        set(NEW_YEAR_ID, new_year_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getNew_year_name() {
        return getStr(NEW_YEAR_NAME);
    }

    public void setNew_year_name(String new_year_name) {
        set(NEW_YEAR_NAME, new_year_name);
    }

    public String getNew_year_phone() {
        return getStr(NEW_YEAR_PHONE);
    }

    public void setNew_year_phone(String new_year_phone) {
        set(NEW_YEAR_PHONE, new_year_phone);
    }

    public String getNew_year_summary() {
        return getStr(NEW_YEAR_SUMMARY);
    }

    public void setNew_year_summary(String new_year_summary) {
        set(NEW_YEAR_SUMMARY, new_year_summary);
    }

    public String getNew_year_wish() {
        return getStr(NEW_YEAR_WISH);
    }

    public void setNew_year_wish(String new_year_wish) {
        set(NEW_YEAR_WISH, new_year_wish);
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