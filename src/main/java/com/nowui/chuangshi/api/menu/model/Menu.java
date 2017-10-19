package com.nowui.chuangshi.api.menu.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Entity
public class Menu extends Model<Menu> {

    @Table
    public static final String TABLE_MENU = "table_menu";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "菜单编号", updatable = false)
    public static final String MENU_ID = "menu_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "菜单上一级编号")
    public static final String MENU_PARENT_ID = "menu_parent_id";

    @Column(type = ColumnType.VARCHAR, length = 30, comment = "菜单名称")
    public static final String MENU_NAME = "menu_name";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "菜单图片")
    public static final String MENU_IMAGE = "menu_image";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "菜单地址")
    public static final String MENU_URL = "menu_url";

    @Column(type = ColumnType.INT, length = 3, comment = "菜单排序")
    public static final String MENU_SORT = "menu_sort";

    @Column(type = ColumnType.VARCHAR, length = 1000, comment = "菜单路径")
    public static final String MENU_PARENT_PATH = "menu_parent_path";

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

    public String getMenu_id() {
        return getStr(MENU_ID);
    }

    public void setMenu_id(String menu_id) {
        set(MENU_ID, menu_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getMenu_parent_id() {
        return getStr(MENU_PARENT_ID);
    }

    public void setMenu_parent_id(String menu_parent_id) {
        set(MENU_PARENT_ID, menu_parent_id);
    }

    public String getMenu_name() {
        return getStr(MENU_NAME);
    }

    public void setMenu_name(String menu_name) {
        set(MENU_NAME, menu_name);
    }

    public String getMenu_image() {
        return getStr(MENU_IMAGE);
    }

    public void setMenu_image(String menu_image) {
        set(MENU_IMAGE, menu_image);
    }

    public String getMenu_url() {
        return getStr(MENU_URL);
    }

    public void setMenu_url(String menu_url) {
        set(MENU_URL, menu_url);
    }

    public Integer getMenu_sort() {
        return getInt(MENU_SORT);
    }

    public void setMenu_sort(Integer menu_sort) {
        set(MENU_SORT, menu_sort);
    }

    public String getMenu_parent_path() {
        return getStr(MENU_PARENT_PATH);
    }

    public void setMenu_parent_path(String menu_parent_path) {
        set(MENU_PARENT_PATH, menu_parent_path);
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