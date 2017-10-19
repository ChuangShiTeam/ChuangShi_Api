package com.nowui.chuangshi.api.xietong.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Entity
public class XietongTeacher extends Model<XietongTeacher> {

    @Table
    public static final String TABLE_XIETONG_TEACHER = "table_xietong_teacher";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "老师编号", updatable = false)
    public static final String TEACHER_ID = "teacher_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "用户编号")
    public static final String USER_ID = "user_id";

    @Column(type = ColumnType.VARCHAR, length = 1000, comment = "班级编号")
    public static final String CLAZZ_ID = "clazz_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "组织机构编号")
    public static final String ORGANIZATION_ID = "organization_id";

    @Column(type = ColumnType.VARCHAR, length = 10, comment = "老师姓名")
    public static final String TEACHER_NAME = "teacher_name";

    @Column(type = ColumnType.VARCHAR, length = 10, comment = "老师工号")
    public static final String TEACHER_NUMBER = "teacher_number";

    @Column(type = ColumnType.VARCHAR, length = 200, comment = "老师分类")
    public static final String TEACHER_CATEGORY = "teacher_category";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "老师照片")
    public static final String TEACHER_IMAGE = "teacher_image";

    @Column(type = ColumnType.VARCHAR, length = 500, comment = "老师职称")
    public static final String TEACHER_TITLE = "teacher_title";

    @Column(type = ColumnType.LONGTEXT, length = 0, comment = "老师简介")
    public static final String TEACHER_DESCRIPTION = "teacher_description";

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

    public String getTeacher_id() {
        return getStr(TEACHER_ID);
    }

    public void setTeacher_id(String teacher_id) {
        set(TEACHER_ID, teacher_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getUser_id() {
        return getStr(USER_ID);
    }

    public void setUser_id(String user_id) {
        set(USER_ID, user_id);
    }

    public String getClazz_id() {
        return getStr(CLAZZ_ID);
    }

    public void setClazz_id(String clazz_id) {
        set(CLAZZ_ID, clazz_id);
    }

    public String getOrganization_id() {
        return getStr(ORGANIZATION_ID);
    }

    public void setOrganization_id(String organization_id) {
        set(ORGANIZATION_ID, organization_id);
    }

    public String getTeacher_name() {
        return getStr(TEACHER_NAME);
    }

    public void setTeacher_name(String teacher_name) {
        set(TEACHER_NAME, teacher_name);
    }

    public String getTeacher_number() {
        return getStr(TEACHER_NUMBER);
    }

    public void setTeacher_number(String teacher_number) {
        set(TEACHER_NUMBER, teacher_number);
    }

    public String getTeacher_category() {
        return getStr(TEACHER_CATEGORY);
    }

    public void setTeacher_category(String teacher_category) {
        set(TEACHER_CATEGORY, teacher_category);
    }

    public String getTeacher_image() {
        return getStr(TEACHER_IMAGE);
    }

    public void setTeacher_image(String teacher_image) {
        set(TEACHER_IMAGE, teacher_image);
    }

    public String getTeacher_title() {
        return getStr(TEACHER_TITLE);
    }

    public void setTeacher_title(String teacher_title) {
        set(TEACHER_TITLE, teacher_title);
    }

    public String getTeacher_description() {
        return getStr(TEACHER_DESCRIPTION);
    }

    public void setTeacher_description(String teacher_description) {
        set(TEACHER_DESCRIPTION, teacher_description);
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