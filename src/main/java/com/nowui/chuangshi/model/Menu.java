package com.nowui.chuangshi.model;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class Menu extends Model<Menu> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "菜单编号")
    public static final String MENU_ID = "menu_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号")
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
    public static final String MENU_PATH = "menu_path";

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

    public String getMenu_path() {
        return getStr(MENU_PATH);
    }

    public void setMenu_path(String menu_path) {
        set(MENU_PATH, menu_path);
    }

}