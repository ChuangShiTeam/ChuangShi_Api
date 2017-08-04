package com.nowui.chuangshi.api.product.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

@Table("table_product_brand")
@Primary("product_brand_id")
public class ProductBrand extends Model<ProductBrand> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "品牌编号", updatable = false)
    public static final String PRODUCT_BRAND_ID = "product_brand_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "品牌名称")
    public static final String PRODUCT_BRAND_NAME = "product_brand_name";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "品牌图片")
    public static final String PRODUCT_BRAND_IMAGE = "product_brand_image";

    @Column(type = ColumnType.VARCHAR, length = 200, comment = "品牌内容")
    public static final String PRODUCT_BRAND_CONTENT = "product_brand_content";

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

    public static final String PRODUCT_BRAND_IMAGE_FILE = "product_brand_image_file";

    public String getProduct_brand_id() {
        return getStr(PRODUCT_BRAND_ID);
    }

    public void setProduct_brand_id(String product_brand_id) {
        set(PRODUCT_BRAND_ID, product_brand_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getProduct_brand_name() {
        return getStr(PRODUCT_BRAND_NAME);
    }

    public void setProduct_brand_name(String product_brand_name) {
        set(PRODUCT_BRAND_NAME, product_brand_name);
    }

    public String getProduct_brand_image() {
        return getStr(PRODUCT_BRAND_IMAGE);
    }

    public void setProduct_brand_image(String product_brand_image) {
        set(PRODUCT_BRAND_IMAGE, product_brand_image);
    }

    public String getProduct_brand_content() {
        return getStr(PRODUCT_BRAND_CONTENT);
    }

    public void setProduct_brand_content(String product_brand_content) {
        set(PRODUCT_BRAND_CONTENT, product_brand_content);
    }

    public String getSystem_create_user_id() {
        return getStr(SYSTEM_CREATE_USER_ID);
    }

    public void setSystem_create_user_id(String system_create_user_id) {
        set(SYSTEM_CREATE_USER_ID, system_create_user_id);
    }

    public String getSystem_create_time() {
        return getStr(SYSTEM_CREATE_TIME);
    }

    public void setSystem_create_time(String system_create_time) {
        set(SYSTEM_CREATE_TIME, system_create_time);
    }

    public String getSystem_update_user_id() {
        return getStr(SYSTEM_UPDATE_USER_ID);
    }

    public void setSystem_update_user_id(String system_update_user_id) {
        set(SYSTEM_UPDATE_USER_ID, system_update_user_id);
    }

    public String getSystem_update_time() {
        return getStr(SYSTEM_UPDATE_TIME);
    }

    public void setSystem_update_time(String system_update_time) {
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