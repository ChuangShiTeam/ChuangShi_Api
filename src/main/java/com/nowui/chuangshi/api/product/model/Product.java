package com.nowui.chuangshi.api.product.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Entity
public class Product extends Model<Product> {

    @Table
    public static final String TABLE_PRODUCT = "table_product";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "商品编号", updatable = false)
    public static final String PRODUCT_ID = "product_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "快照编号")
    public static final String PRODUCT_SNAP_ID = "product_snap_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "分类编号")
    public static final String PRODUCT_CATEGORY_ID = "product_category_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "品牌编号")
    public static final String PRODUCT_BRAND_ID = "product_brand_id";

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

    @Column(type = ColumnType.TEXT, length = 65535, comment = "商品介绍")
    public static final String PRODUCT_CONTENT = "product_content";

    @Column(type = ColumnType.TINYINT, length = 1, comment = "商品状态")
    public static final String PRODUCT_STATUS = "product_status";

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

    public static final String PRODUCT_IMAGE_FILE = "product_image_file";
    public static final String PRODUCT_SKU_LIST = "product_sku_list";

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

    public String getProduct_snap_id() {
        return getStr(PRODUCT_SNAP_ID);
    }

    public void setProduct_snap_id(String product_snap_id) {
        set(PRODUCT_SNAP_ID, product_snap_id);
    }

    public String getProduct_category_id() {
        return getStr(PRODUCT_CATEGORY_ID);
    }

    public void setProduct_category_id(String product_category_id) {
        set(PRODUCT_CATEGORY_ID, product_category_id);
    }

    public String getProduct_brand_id() {
        return getStr(PRODUCT_BRAND_ID);
    }

    public void setProduct_brand_id(String product_brand_id) {
        set(PRODUCT_BRAND_ID, product_brand_id);
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