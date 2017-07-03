package com.nowui.chuangshi.model;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class ProductCategory extends Model<ProductCategory> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "分类编号")
    public static final String PRODUCT_CATEGORY_ID = "product_category_id";

    @Column(type = ColumnType.INT, length = 11, comment = "应用编号")
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "上级编号")
    public static final String PRODUCT_CATEGORY_PARENT_ID = "product_category_parent_id";

    @Column(type = ColumnType.VARCHAR, length = 30, comment = "分类名称")
    public static final String PRODUCT_CATEGORY_NAME = "product_category_name";

    @Column(type = ColumnType.INT, length = 3, comment = "分类排序")
    public static final String PRODUCT_CATEGORY_SORT = "product_category_sort";

    @Column(type = ColumnType.VARCHAR, length = 1000, comment = "分类路径")
    public static final String PRODUCT_CATEGORY_PATH = "product_category_path";

    public String getProduct_category_id() {
        return getStr(PRODUCT_CATEGORY_ID);
    }

    public void setProduct_category_id(String product_category_id) {
        set(PRODUCT_CATEGORY_ID, product_category_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getProduct_category_parent_id() {
        return getStr(PRODUCT_CATEGORY_PARENT_ID);
    }

    public void setProduct_category_parent_id(String product_category_parent_id) {
        set(PRODUCT_CATEGORY_PARENT_ID, product_category_parent_id);
    }

    public String getProduct_category_name() {
        return getStr(PRODUCT_CATEGORY_NAME);
    }

    public void setProduct_category_name(String product_category_name) {
        set(PRODUCT_CATEGORY_NAME, product_category_name);
    }

    public Integer getProduct_category_sort() {
        return getInt(PRODUCT_CATEGORY_SORT);
    }

    public void setProduct_category_sort(Integer product_category_sort) {
        set(PRODUCT_CATEGORY_SORT, product_category_sort);
    }

    public String getProduct_category_path() {
        return getStr(PRODUCT_CATEGORY_PATH);
    }

    public void setProduct_category_path(String product_category_path) {
        set(PRODUCT_CATEGORY_PATH, product_category_path);
    }

}