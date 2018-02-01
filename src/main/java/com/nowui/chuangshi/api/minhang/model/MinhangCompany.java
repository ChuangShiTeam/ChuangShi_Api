package com.nowui.chuangshi.api.minhang.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Entity
public class MinhangCompany extends Model<MinhangCompany> {

    @Table
    public static final String TABLE_MINHANG_COMPANY = "table_minhang_company";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "公司编号", updatable = false)
    public static final String COMPANY_ID = "company_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "公司名称")
    public static final String COMPANY_NAME = "company_name";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "公司logo")
    public static final String COMPANY_LOGO = "company_logo";

    @Column(type = ColumnType.INT, length = 11, comment = "公司展示宽度")
    public static final String COMPANY_VIEW_WIDTH = "company_view_width";

    @Column(type = ColumnType.LONGTEXT, length = 0, comment = "公司简介")
    public static final String COMPNAY_DESCRIPTION = "compnay_description";

    @Column(type = ColumnType.INT, length = 11, comment = "公司排序")
    public static final String COMPNAY_SORT = "compnay_sort";

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
    
    public static final String COMPANY_LOGO_FILE = "company_logo_file";

    public String getCompany_id() {
        return getStr(COMPANY_ID);
    }

    public void setCompany_id(String company_id) {
        set(COMPANY_ID, company_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getCompany_name() {
        return getStr(COMPANY_NAME);
    }

    public void setCompany_name(String company_name) {
        set(COMPANY_NAME, company_name);
    }

    public String getCompany_logo() {
        return getStr(COMPANY_LOGO);
    }

    public void setCompany_logo(String company_logo) {
        set(COMPANY_LOGO, company_logo);
    }

    public Integer getCompany_view_width() {
        return getInt(COMPANY_VIEW_WIDTH);
    }

    public void setCompany_view_width(Integer company_view_width) {
        set(COMPANY_VIEW_WIDTH, company_view_width);
    }

    public String getCompnay_description() {
        return getStr(COMPNAY_DESCRIPTION);
    }

    public void setCompnay_description(String compnay_description) {
        set(COMPNAY_DESCRIPTION, compnay_description);
    }

    public Integer getCompnay_sort() {
        return getInt(COMPNAY_SORT);
    }

    public void setCompnay_sort(Integer compnay_sort) {
        set(COMPNAY_SORT, compnay_sort);
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