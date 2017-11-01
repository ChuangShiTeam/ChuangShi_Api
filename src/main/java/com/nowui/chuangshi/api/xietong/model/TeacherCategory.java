package com.nowui.chuangshi.api.xietong.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Entity
public class TeacherCategory extends Model<TeacherCategory> {

    @Table
    public static final String TABLE_TEACHER_CATEGORY = "table_xietong_teacher_category";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "分类编号", updatable = false)
    public static final String TEACHER_CATEGORY_ID = "teacher_category_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "上级编号")
    public static final String TEACHER_CATEGORY_PARENT_ID = "teacher_category_parent_id";

    @Column(type = ColumnType.VARCHAR, length = 30, comment = "分类名称")
    public static final String TEACHER_CATEGORY_NAME = "teacher_category_name";

    @Column(type = ColumnType.INT, length = 3, comment = "分类排序")
    public static final String TEACHER_CATEGORY_SORT = "teacher_category_sort";

    @Column(type = ColumnType.VARCHAR, length = 1000, comment = "分类路径")
    public static final String TEACHER_CATEGORY_PATH = "teacher_category_path";

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

    public String getTeacher_category_id() {
        return getStr(TEACHER_CATEGORY_ID);
    }

    public void setTeacher_category_id(String teacher_category_id) {
        set(TEACHER_CATEGORY_ID, teacher_category_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getTeacher_category_parent_id() {
        return getStr(TEACHER_CATEGORY_PARENT_ID);
    }

    public void setTeacher_category_parent_id(String teacher_category_parent_id) {
        set(TEACHER_CATEGORY_PARENT_ID, teacher_category_parent_id);
    }

    public String getTeacher_category_name() {
        return getStr(TEACHER_CATEGORY_NAME);
    }

    public void setTeacher_category_name(String teacher_category_name) {
        set(TEACHER_CATEGORY_NAME, teacher_category_name);
    }

    public Integer getTeacher_category_sort() {
        return getInt(TEACHER_CATEGORY_SORT);
    }

    public void setTeacher_category_sort(Integer teacher_category_sort) {
        set(TEACHER_CATEGORY_SORT, teacher_category_sort);
    }

    public String getTeacher_category_path() {
        return getStr(TEACHER_CATEGORY_PATH);
    }

    public void setTeacher_category_path(String teacher_category_path) {
        set(TEACHER_CATEGORY_PATH, teacher_category_path);
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