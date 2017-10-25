package com.nowui.chuangshi.api.uni.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Entity
public class UniApply extends Model<UniApply> {

    @Table
    public static final String TABLE_UNI_APPLY = "table_uni_apply";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "报名编号", updatable = false)
    public static final String APPLY_ID = "apply_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "姓名")
    public static final String APPLY_NAME = "apply_name";

    @Column(type = ColumnType.VARCHAR, length = 11, comment = "手机号码")
    public static final String APPLY_MOBILE = "apply_mobile";

    @Column(type = ColumnType.VARCHAR, length = 50, comment = "公司")
    public static final String APPLY_COMPANY = "apply_company";

    @Column(type = ColumnType.VARCHAR, length = 50, comment = "职务")
    public static final String APPLY_JOB = "apply_job";

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

    public String getApply_id() {
        return getStr(APPLY_ID);
    }

    public void setApply_id(String apply_id) {
        set(APPLY_ID, apply_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getApply_name() {
        return getStr(APPLY_NAME);
    }

    public void setApply_name(String apply_name) {
        set(APPLY_NAME, apply_name);
    }

    public String getApply_mobile() {
        return getStr(APPLY_MOBILE);
    }

    public void setApply_mobile(String apply_mobile) {
        set(APPLY_MOBILE, apply_mobile);
    }

    public String getApply_company() {
        return getStr(APPLY_COMPANY);
    }

    public void setApply_company(String apply_company) {
        set(APPLY_COMPANY, apply_company);
    }

    public String getApply_job() {
        return getStr(APPLY_JOB);
    }

    public void setApply_job(String apply_job) {
        set(APPLY_JOB, apply_job);
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