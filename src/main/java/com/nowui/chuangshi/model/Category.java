package com.nowui.chuangshi.model;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class Category extends Model<Category> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "分类编号")
    public static final String CATEGORY_ID = "category_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号")
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "上一级编号")
    public static final String PARENT_ID = "parent_id";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "分类名称")
    public static final String CATEGORY_NAME = "category_name";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "分类图片")
    public static final String CATEGORY_IMAGE = "category_image";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "分类键值")
    public static final String CATEGORY_KEY = "category_key";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "分类数值")
    public static final String CATEGORY_VALUE = "category_value";

    @Column(type = ColumnType.VARCHAR, length = 1000, comment = "分类路径")
    public static final String CATEGORY_PATH = "category_path";

    @Column(type = ColumnType.INT, length = 3, comment = "分类排序")
    public static final String CATEGORY_SORT = "category_sort";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "分类类型")
    public static final String CATEGORY_TYPE = "category_type";

    public String getCategory_id() {
        return getStr(CATEGORY_ID);
    }

    public void setCategory_id(String category_id) {
        set(CATEGORY_ID, category_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getParent_id() {
        return getStr(PARENT_ID);
    }

    public void setParent_id(String parent_id) {
        set(PARENT_ID, parent_id);
    }

    public String getCategory_name() {
        return getStr(CATEGORY_NAME);
    }

    public void setCategory_name(String category_name) {
        set(CATEGORY_NAME, category_name);
    }

    public String getCategory_image() {
        return getStr(CATEGORY_IMAGE);
    }

    public void setCategory_image(String category_image) {
        set(CATEGORY_IMAGE, category_image);
    }

    public String getCategory_key() {
        return getStr(CATEGORY_KEY);
    }

    public void setCategory_key(String category_key) {
        set(CATEGORY_KEY, category_key);
    }

    public String getCategory_value() {
        return getStr(CATEGORY_VALUE);
    }

    public void setCategory_value(String category_value) {
        set(CATEGORY_VALUE, category_value);
    }

    public String getCategory_path() {
        return getStr(CATEGORY_PATH);
    }

    public void setCategory_path(String category_path) {
        set(CATEGORY_PATH, category_path);
    }

    public Integer getCategory_sort() {
        return getInt(CATEGORY_SORT);
    }

    public void setCategory_sort(Integer category_sort) {
        set(CATEGORY_SORT, category_sort);
    }

    public String getCategory_type() {
        return getStr(CATEGORY_TYPE);
    }

    public void setCategory_type(String category_type) {
        set(CATEGORY_TYPE, category_type);
    }

}