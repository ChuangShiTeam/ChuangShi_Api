package com.nowui.chuangshi.api.page.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Entity
public class Page extends Model<Page> {

    @Table
    public static final String TABLE_PAGE = "table_page";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "单页编号", updatable = false)
    public static final String PAGE_ID = "page_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "菜单编号", updatable = false)
    public static final String WEBSITE_MENU_ID = "website_menu_id";

    @Column(type = ColumnType.VARCHAR, length = 30, comment = "单页名称")
    public static final String PAGE_NAME = "page_name";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "单页模板")
    public static final String PAGE_TEMPLATE = "page_template";

    @Column(type = ColumnType.VARCHAR, length = 50, comment = "单页地址")
    public static final String PAGE_URL = "page_url";

    @Column(type = ColumnType.LONGTEXT, length = 0, comment = "单页内容")
    public static final String PAGE_CONTENT = "page_content";

    @Column(type = ColumnType.INT, length = 3, comment = "单页排序")
    public static final String PAGE_SORT = "page_sort";

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

    public String getPage_id() {
        return getStr(PAGE_ID);
    }

    public void setPage_id(String page_id) {
        set(PAGE_ID, page_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getWebsite_menu_id() {
        return getStr(WEBSITE_MENU_ID);
    }

    public void setWebsite_menu_id(String website_menu_id) {
        set(WEBSITE_MENU_ID, website_menu_id);
    }

    public String getPage_name() {
        return getStr(PAGE_NAME);
    }

    public void setPage_name(String page_name) {
        set(PAGE_NAME, page_name);
    }

    public String getPage_template() {
        return getStr(PAGE_TEMPLATE);
    }

    public void setPage_template(String page_template) {
        set(PAGE_TEMPLATE, page_template);
    }

    public String getPage_url() {
        return getStr(PAGE_URL);
    }

    public void setPage_url(String page_url) {
        set(PAGE_URL, page_url);
    }

    public String getPage_content() {
        return getStr(PAGE_CONTENT);
    }

    public void setPage_content(String page_content) {
        set(PAGE_CONTENT, page_content);
    }

    public Integer getPage_sort() {
        return getInt(PAGE_SORT);
    }

    public void setPage_sort(Integer page_sort) {
        set(PAGE_SORT, page_sort);
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