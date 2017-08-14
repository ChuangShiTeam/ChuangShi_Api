package com.nowui.chuangshi.api.clazz.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Table("table_clazz")
@Primary("clazz_id")
public class Clazz extends Model<Clazz> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "", updatable = false)
    public static final String CLAZZ_ID = "clazz_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "班级名称")
    public static final String CLAZZ_NAME = "clazz_name";

    @Column(type = ColumnType.INT, length = 3, comment = "选课限制人数")
    public static final String CLAZZ_COURSE_APPLY_LIMIT = "clazz_course_apply_limit";

    @Column(type = ColumnType.VARCHAR, length = 19, comment = "选课开始时间")
    public static final String CLAZZ_COURSE_APPLY_START_TIME = "clazz_course_apply_start_time";

    @Column(type = ColumnType.VARCHAR, length = 19, comment = "选课结束时间")
    public static final String CLAZZ_COURSE_APPLY_END_TIME = "clazz_course_apply_end_time";

    @Column(type = ColumnType.INT, length = 3, comment = "班级排序")
    public static final String CLAZZ_SORT = "clazz_sort";

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

    public String getClazz_id() {
        return getStr(CLAZZ_ID);
    }

    public void setClazz_id(String clazz_id) {
        set(CLAZZ_ID, clazz_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getClazz_name() {
        return getStr(CLAZZ_NAME);
    }

    public void setClazz_name(String clazz_name) {
        set(CLAZZ_NAME, clazz_name);
    }

    public Integer getClazz_course_apply_limit() {
        return getInt(CLAZZ_COURSE_APPLY_LIMIT);
    }

    public void setClazz_course_apply_limit(Integer clazz_course_apply_limit) {
        set(CLAZZ_COURSE_APPLY_LIMIT, clazz_course_apply_limit);
    }

    public String getClazz_course_apply_start_time() {
        return getStr(CLAZZ_COURSE_APPLY_START_TIME);
    }

    public void setClazz_course_apply_start_time(String clazz_course_apply_start_time) {
        set(CLAZZ_COURSE_APPLY_START_TIME, clazz_course_apply_start_time);
    }

    public String getClazz_course_apply_end_time() {
        return getStr(CLAZZ_COURSE_APPLY_END_TIME);
    }

    public void setClazz_course_apply_end_time(String clazz_course_apply_end_time) {
        set(CLAZZ_COURSE_APPLY_END_TIME, clazz_course_apply_end_time);
    }

    public Integer getClazz_sort() {
        return getInt(CLAZZ_SORT);
    }

    public void setClazz_sort(Integer clazz_sort) {
        set(CLAZZ_SORT, clazz_sort);
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