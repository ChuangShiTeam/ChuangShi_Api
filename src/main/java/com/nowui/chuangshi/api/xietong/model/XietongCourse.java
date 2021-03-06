package com.nowui.chuangshi.api.xietong.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Entity
public class XietongCourse extends Model<XietongCourse> {
    
    @Table
    public static final String TABLE_XIETONG_COURSE = "table_xietong_course";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "课程编号", updatable = false)
    public static final String COURSE_ID = "course_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 1000, comment = "班级编号")
    public static final String CLAZZ_ID = "clazz_id";

    @Column(type = ColumnType.VARCHAR, length = 200, comment = "课程老师")
    public static final String COURSE_TEACHER = "course_teacher";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "课程名称")
    public static final String COURSE_NAME = "course_name";

    @Column(type = ColumnType.INT, length = 2, comment = "课程时间")
    public static final String COURSE_TIME = "course_time";

    @Column(type = ColumnType.INT, length = 3, comment = "申请限制")
    public static final String COURSE_APPLY_LIMIT = "course_apply_limit";

    @Column(type = ColumnType.VARCHAR, length = 200, comment = "课程地址")
    public static final String COURSE_ADDRESS = "course_address";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "课程图片")
    public static final String COURSE_IMAGE = "course_image";

    @Column(type = ColumnType.LONGTEXT, length = 0, comment = "课程介绍")
    public static final String COURSE_CONTENT = "course_content";

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
    
    public static final String IS_APPLY = "is_apply";
    
    public static final String IS_LIMIT = "is_limit";
    
    public static final String COURSE_APPLY_COUNT = "course_apply_count";
    
    public static final String COURSE_IMAGE_FILE = "course_image_file";

    public String getCourse_id() {
        return getStr(COURSE_ID);
    }

    public void setCourse_id(String course_id) {
        set(COURSE_ID, course_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getClazz_id() {
        return getStr(CLAZZ_ID);
    }

    public void setClazz_id(String clazz_id) {
        set(CLAZZ_ID, clazz_id);
    }

    public String getCourse_teacher() {
        return getStr(COURSE_TEACHER);
    }

    public void setCourse_teacher(String course_teacher) {
        set(COURSE_TEACHER, course_teacher);
    }

    public String getCourse_name() {
        return getStr(COURSE_NAME);
    }

    public void setCourse_name(String course_name) {
        set(COURSE_NAME, course_name);
    }

    public Integer getCourse_time() {
        return getInt(COURSE_TIME);
    }

    public void setCourse_time(Integer course_time) {
        set(COURSE_TIME, course_time);
    }

    public Integer getCourse_apply_limit() {
        return getInt(COURSE_APPLY_LIMIT);
    }

    public void setCourse_apply_limit(Integer course_apply_limit) {
        set(COURSE_APPLY_LIMIT, course_apply_limit);
    }

    public String getCourse_address() {
        return getStr(COURSE_ADDRESS);
    }

    public void setCourse_address(String course_address) {
        set(COURSE_ADDRESS, course_address);
    }

    public String getCourse_image() {
        return getStr(COURSE_IMAGE);
    }

    public void setCourse_image(String course_image) {
        set(COURSE_IMAGE, course_image);
    }

    public String getCourse_content() {
        return getStr(COURSE_CONTENT);
    }

    public void setCourse_content(String course_content) {
        set(COURSE_CONTENT, course_content);
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