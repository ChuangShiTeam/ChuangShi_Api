package com.nowui.chuangshi.api.xietong.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Entity
public class XietongCourseConfig extends Model<XietongCourseConfig> {
    
    @Table
    public static final String TABLE_XIETONG_COURSE_CONFIG = "table_xietong_course_config";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "配置编号", updatable = false)
    public static final String COURSE_CONFIG_ID = "course_config_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 19, comment = "开始时间")
    public static final String COURSE_CONFIG_APPLY_START_TIME = "course_config_apply_start_time";

    @Column(type = ColumnType.VARCHAR, length = 19, comment = "结束时间")
    public static final String COURSE_CONFIG_APPLY_END_TIME = "course_config_apply_end_time";

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

    public String getCourse_config_id() {
        return getStr(COURSE_CONFIG_ID);
    }

    public void setCourse_config_id(String course_config_id) {
        set(COURSE_CONFIG_ID, course_config_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getCourse_config_apply_start_time() {
        return getStr(COURSE_CONFIG_APPLY_START_TIME);
    }

    public void setCourse_config_apply_start_time(String course_config_apply_start_time) {
        set(COURSE_CONFIG_APPLY_START_TIME, course_config_apply_start_time);
    }

    public String getCourse_config_apply_end_time() {
        return getStr(COURSE_CONFIG_APPLY_END_TIME);
    }

    public void setCourse_config_apply_end_time(String course_config_apply_end_time) {
        set(COURSE_CONFIG_APPLY_END_TIME, course_config_apply_end_time);
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