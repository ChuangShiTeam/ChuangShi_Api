package com.nowui.chuangshi.api.minhang.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Entity
public class MinhangAffiant extends Model<MinhangAffiant> {

    @Table
    public static final String TABLE_MINHANG_AFFIANT = "table_minhang_affiant";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "宣誓人编号", updatable = false)
    public static final String AFFIANT_ID = "affiant_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 25, comment = "姓名")
    public static final String AFFIANT_NAME = "affiant_name";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "头像")
    public static final String AFFIANT_AVATAR = "affiant_avatar";

    @Column(type = ColumnType.VARCHAR, length = 2, comment = "性别")
    public static final String AFFIANT_SEX = "affiant_sex";

    @Column(type = ColumnType.VARCHAR, length = 10, comment = "出身年月")
    public static final String AFFIANT_BIRTHDAY = "affiant_birthday";

    @Column(type = ColumnType.VARCHAR, length = 200, comment = "摘要")
    public static final String AFFIANT_SUMMARY = "affiant_summary";

    @Column(type = ColumnType.LONGTEXT, length = 0, comment = "简介")
    public static final String AFFIANT_DESCRIPTION = "affiant_description";

    @Column(type = ColumnType.INT, length = 11, comment = "排序")
    public static final String AFFIANT_SORT = "affiant_sort";

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
    
    public static final String AFFIANT_AVATAR_FILE = "affiant_avatar_file";

    public String getAffiant_id() {
        return getStr(AFFIANT_ID);
    }

    public void setAffiant_id(String affiant_id) {
        set(AFFIANT_ID, affiant_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getAffiant_name() {
        return getStr(AFFIANT_NAME);
    }

    public void setAffiant_name(String affiant_name) {
        set(AFFIANT_NAME, affiant_name);
    }

    public String getAffiant_avatar() {
        return getStr(AFFIANT_AVATAR);
    }

    public void setAffiant_avatar(String affiant_avatar) {
        set(AFFIANT_AVATAR, affiant_avatar);
    }

    public String getAffiant_sex() {
        return getStr(AFFIANT_SEX);
    }

    public void setAffiant_sex(String affiant_sex) {
        set(AFFIANT_SEX, affiant_sex);
    }

    public String getAffiant_birthday() {
        return getStr(AFFIANT_BIRTHDAY);
    }

    public void setAffiant_birthday(String affiant_birthday) {
        set(AFFIANT_BIRTHDAY, affiant_birthday);
    }

    public String getAffiant_summary() {
        return getStr(AFFIANT_SUMMARY);
    }

    public void setAffiant_summary(String affiant_summary) {
        set(AFFIANT_SUMMARY, affiant_summary);
    }

    public String getAffiant_description() {
        return getStr(AFFIANT_DESCRIPTION);
    }

    public void setAffiant_description(String affiant_description) {
        set(AFFIANT_DESCRIPTION, affiant_description);
    }

    public Integer getAffiant_sort() {
        return getInt(AFFIANT_SORT);
    }

    public void setAffiant_sort(Integer affiant_sort) {
        set(AFFIANT_SORT, affiant_sort);
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