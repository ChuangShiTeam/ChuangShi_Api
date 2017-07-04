package com.nowui.chuangshi.model;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class Product extends Model<Product> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "商品编号")
    public static final String PRODUCT_ID = "product_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号")
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "分类编号")
    public static final String CATEGORY_ID = "category_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "品牌编号")
    public static final String BRAND_ID = "brand_id";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "商品名称")
    public static final String PRODUCT_NAME = "product_name";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "商品图片")
    public static final String PRODUCT_IMAGE = "product_image";

    @Column(type = ColumnType.TINYINT, length = 1, comment = "是否新品")
    public static final String PRODUCT_IS_NEW = "product_is_new";

    @Column(type = ColumnType.TINYINT, length = 1, comment = "是否推荐")
    public static final String PRODUCT_IS_RECOMMEND = "product_is_recommend";

    @Column(type = ColumnType.TINYINT, length = 1, comment = "是否特价")
    public static final String PRODUCT_IS_BARGAIN = "product_is_bargain";

    @Column(type = ColumnType.TINYINT, length = 1, comment = "是否热销")
    public static final String PRODUCT_IS_HOT = "product_is_hot";

    @Column(type = ColumnType.TINYINT, length = 1, comment = "是否销售")
    public static final String PRODUCT_IS_SOLD_OUT = "product_is_sold_out";

    @Column(type = ColumnType.TINYINT, length = 1, comment = "是否虚拟")
    public static final String PRODUCT_IS_VIRTUAL = "product_is_virtual";

    @Column(type = ColumnType.LONGTEXT, length = 0, comment = "商品介绍")
    public static final String PRODUCT_CONTENT = "product_content";

    @Column(type = ColumnType.TINYINT, length = 1, comment = "商品状态")
    public static final String PRODUCT_STATUS = "product_status";

    public static final String PRODUCT_IMAGE_FILE = "product_image_file";
    public static final String PRODUCT_SKU_LIST = "product_sku_list";
    public static final String PRODUCT_SKU_COMMISSION_LIST = "product_sku_commission_list";
    public static final String PRODUCT_SKU_PRICE_LIST = "product_sku_price_list";

    public String getProduct_id() {
        return getStr(PRODUCT_ID);
    }

    public void setProduct_id(String product_id) {
        set(PRODUCT_ID, product_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getCategory_id() {
        return getStr(CATEGORY_ID);
    }

    public void setCategory_id(String category_id) {
        set(CATEGORY_ID, category_id);
    }

    public String getBrand_id() {
        return getStr(BRAND_ID);
    }

    public void setBrand_id(String brand_id) {
        set(BRAND_ID, brand_id);
    }

    public String getProduct_name() {
        return getStr(PRODUCT_NAME);
    }

    public void setProduct_name(String product_name) {
        set(PRODUCT_NAME, product_name);
    }

    public String getProduct_image() {
        return getStr(PRODUCT_IMAGE);
    }

    public void setProduct_image(String product_image) {
        set(PRODUCT_IMAGE, product_image);
    }

    public Boolean getProduct_is_new() {
        return getBoolean(PRODUCT_IS_NEW);
    }

    public void setProduct_is_new(Boolean product_is_new) {
        set(PRODUCT_IS_NEW, product_is_new);
    }

    public Boolean getProduct_is_recommend() {
        return getBoolean(PRODUCT_IS_RECOMMEND);
    }

    public void setProduct_is_recommend(Boolean product_is_recommend) {
        set(PRODUCT_IS_RECOMMEND, product_is_recommend);
    }

    public Boolean getProduct_is_bargain() {
        return getBoolean(PRODUCT_IS_BARGAIN);
    }

    public void setProduct_is_bargain(Boolean product_is_bargain) {
        set(PRODUCT_IS_BARGAIN, product_is_bargain);
    }

    public Boolean getProduct_is_hot() {
        return getBoolean(PRODUCT_IS_HOT);
    }

    public void setProduct_is_hot(Boolean product_is_hot) {
        set(PRODUCT_IS_HOT, product_is_hot);
    }

    public Boolean getProduct_is_sold_out() {
        return getBoolean(PRODUCT_IS_SOLD_OUT);
    }

    public void setProduct_is_sold_out(Boolean product_is_sold_out) {
        set(PRODUCT_IS_SOLD_OUT, product_is_sold_out);
    }

    public Boolean getProduct_is_virtual() {
        return getBoolean(PRODUCT_IS_VIRTUAL);
    }

    public void setProduct_is_virtual(Boolean product_is_virtual) {
        set(PRODUCT_IS_VIRTUAL, product_is_virtual);
    }

    public String getProduct_content() {
        return getStr(PRODUCT_CONTENT);
    }

    public void setProduct_content(String product_content) {
        set(PRODUCT_CONTENT, product_content);
    }

    public Boolean getProduct_status() {
        return getBoolean(PRODUCT_STATUS);
    }

    public void setProduct_status(Boolean product_status) {
        set(PRODUCT_STATUS, product_status);
    }

}