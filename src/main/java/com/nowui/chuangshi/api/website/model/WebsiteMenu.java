package com.nowui.chuangshi.api.website.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Entity
public class WebsiteMenu extends Model<WebsiteMenu> {

    @Table
    public static final String TABLE_WEBSITE_MENU = "table_website_menu";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "菜单编号", updatable = false)
    public static final String WEBSITE_MENU_ID = "website_menu_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "上一级编号")
    public static final String WEBSITE_MENU_PARENT_ID = "website_menu_parent_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "单页编号", updatable = false)
    public static final String PAGE_ID = "page_id";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "菜单名称")
    public static final String WEBSITE_MENU_NAME = "website_menu_name";

    @Column(type = ColumnType.VARCHAR, length = 200, comment = "菜单地址")
    public static final String WEBSITE_MENU_URL = "website_menu_url";

    @Column(type = ColumnType.INT, length = 3, comment = "菜单排序")
    public static final String WEBSITE_MENU_SORT = "website_menu_sort";

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

    public String getWebsite_menu_id() {
        return getStr(WEBSITE_MENU_ID);
    }

    public void setWebsite_menu_id(String website_menu_id) {
        set(WEBSITE_MENU_ID, website_menu_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getWebsite_menu_parent_id() {
        return getStr(WEBSITE_MENU_PARENT_ID);
    }

    public void setWebsite_menu_parent_id(String website_menu_parent_id) {
        set(WEBSITE_MENU_PARENT_ID, website_menu_parent_id);
    }

    public String getPage_id() {
        return getStr(PAGE_ID);
    }

    public void setPage_id(String page_id) {
        set(PAGE_ID, page_id);
    }

    public String getWebsite_menu_name() {
        return getStr(WEBSITE_MENU_NAME);
    }

    public void setWebsite_menu_name(String website_menu_name) {
        set(WEBSITE_MENU_NAME, website_menu_name);
    }

    public String getWebsite_menu_url() {
        return getStr(WEBSITE_MENU_URL);
    }

    public void setWebsite_menu_url(String website_menu_url) {
        set(WEBSITE_MENU_URL, website_menu_url);
    }

    public Integer getWebsite_menu_sort() {
        return getInt(WEBSITE_MENU_SORT);
    }

    public void setWebsite_menu_sort(Integer website_menu_sort) {
        set(WEBSITE_MENU_SORT, website_menu_sort);
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