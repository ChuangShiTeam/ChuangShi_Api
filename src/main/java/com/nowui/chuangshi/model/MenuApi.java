package com.nowui.chuangshi.model;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class MenuApi extends Model<MenuApi> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "菜单编号")
    public static final String MENU_ID = "menu_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "接口编号")
    public static final String API_ID = "api_id";

    @Column(type = ColumnType.INT, length = 3, comment = "菜单接口排序")
    public static final String MENU_API_SORT = "menu_api_sort";

    public String getMenu_id() {
        return getStr(MENU_ID);
    }

    public void setMenu_id(String menu_id) {
        set(MENU_ID, menu_id);
    }

    public String getApi_id() {
        return getStr(API_ID);
    }

    public void setApi_id(String api_id) {
        set(API_ID, api_id);
    }

    public Integer getMenu_api_sort() {
        return getInt(MENU_API_SORT);
    }

    public void setMenu_api_sort(Integer menu_api_sort) {
        set(MENU_API_SORT, menu_api_sort);
    }

}