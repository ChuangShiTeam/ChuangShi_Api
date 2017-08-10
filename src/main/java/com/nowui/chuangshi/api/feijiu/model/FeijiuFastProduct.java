package com.nowui.chuangshi.api.feijiu.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Table("table_feijiu_fast_product")
@Primary("product_id")
public class FeijiuFastProduct extends Model<FeijiuFastProduct> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "商品编号", updatable = false)
    public static final String PRODUCT_ID = "product_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "商品分类id")
    public static final String PRODUCT_CATEGORY_ID = "product_category_id";

    @Column(type = ColumnType.VARCHAR, length = 30, comment = "商品名称")
    public static final String PRODUCT_NAME = "product_name";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "商品图片")
    public static final String PRODUCT_IMAGE = "product_image";

    @Column(type = ColumnType.VARCHAR, length = 250, comment = "商品链接")
    public static final String PRODUCT_LINK = "product_link";

    @Column(type = ColumnType.VARCHAR, length = 200, comment = "商品介绍")
    public static final String PRODUCT_CONTENT = "product_content";

    @Column(type = ColumnType.INT, length = 11, comment = "申请人数")
    public static final String PRODUCT_APPLICANT_QUANTITY = "product_applicant_quantity";

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

    public String getProduct_category_id() {
        return getStr(PRODUCT_CATEGORY_ID);
    }

    public void setProduct_category_id(String product_category_id) {
        set(PRODUCT_CATEGORY_ID, product_category_id);
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

    public String getProduct_link() {
        return getStr(PRODUCT_LINK);
    }

    public void setProduct_link(String product_link) {
        set(PRODUCT_LINK, product_link);
    }

    public String getProduct_content() {
        return getStr(PRODUCT_CONTENT);
    }

    public void setProduct_content(String product_content) {
        set(PRODUCT_CONTENT, product_content);
    }

    public Integer getProduct_applicant_quantity() {
        return getInt(PRODUCT_APPLICANT_QUANTITY);
    }

    public void setProduct_applicant_quantity(Integer product_applicant_quantity) {
        set(PRODUCT_APPLICANT_QUANTITY, product_applicant_quantity);
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