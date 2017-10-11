package com.nowui.chuangshi.api.xietong.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Entity
public class XietongOrganization extends Model<XietongOrganization> {

    @Table
    public static final String TABLE_XIETONG_ORGANIZATION = "table_xietong_organization";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "组织机构编号", updatable = false)
    public static final String ORGANIZATION_ID = "organization_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "名称")
    public static final String ORGANIZATION_NAME = "organization_name";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "编码")
    public static final String ORGANIZATION_CODE = "organization_code";

    @Column(type = ColumnType.INT, length = 5, comment = "排序")
    public static final String ORGANIZAITON_SORT = "organizaiton_sort";

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

    public String getOrganization_id() {
        return getStr(ORGANIZATION_ID);
    }

    public void setOrganization_id(String organization_id) {
        set(ORGANIZATION_ID, organization_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getOrganization_name() {
        return getStr(ORGANIZATION_NAME);
    }

    public void setOrganization_name(String organization_name) {
        set(ORGANIZATION_NAME, organization_name);
    }

    public String getOrganization_code() {
        return getStr(ORGANIZATION_CODE);
    }

    public void setOrganization_code(String organization_code) {
        set(ORGANIZATION_CODE, organization_code);
    }

    public Integer getOrganizaiton_sort() {
        return getInt(ORGANIZAITON_SORT);
    }

    public void setOrganizaiton_sort(Integer organizaiton_sort) {
        set(ORGANIZAITON_SORT, organizaiton_sort);
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