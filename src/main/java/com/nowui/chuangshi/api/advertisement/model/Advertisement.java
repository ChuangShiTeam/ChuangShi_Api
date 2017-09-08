package com.nowui.chuangshi.api.advertisement.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Entity
public class Advertisement extends Model<Advertisement> {
    
    @Table
    public static final String TABLE_ADVERTISEMENT = "table_advertisement";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "", updatable = false)
    public static final String ADVERTISEMENT_ID = "advertisement_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 50, comment = "广告分类编码")
    public static final String ADVERTISEMENT_CATEGORY_CODE = "advertisement_category_code";

    @Column(type = ColumnType.VARCHAR, length = 200, comment = "广告标题")
    public static final String ADVERTISEMENT_TITLE = "advertisement_title";

    @Column(type = ColumnType.VARCHAR, length = 200, comment = "广告位置")
    public static final String ADVERTISEMENT_POSITION = "advertisement_position";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "广告图片")
    public static final String ADVERTISEMENT_IMAGE = "advertisement_image";

    @Column(type = ColumnType.VARCHAR, length = 200, comment = "广告链接")
    public static final String ADVERTISEMENT_LINK = "advertisement_link";

    @Column(type = ColumnType.TINYINT, length = 1, comment = "是否浮动广告")
    public static final String ADVERTISEMENT_IS_FLOAT = "advertisement_is_float";

    @Column(type = ColumnType.INT, length = 3, comment = "排序")
    public static final String ADVERTISEMENT_SORT = "advertisement_sort";

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
    
    public static final String ADVERTISEMENT_IMAGE_FILE = "advertisement_image_file";

    public String getAdvertisement_id() {
        return getStr(ADVERTISEMENT_ID);
    }

    public void setAdvertisement_id(String advertisement_id) {
        set(ADVERTISEMENT_ID, advertisement_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getAdvertisement_category_code() {
        return getStr(ADVERTISEMENT_CATEGORY_CODE);
    }

    public void setAdvertisement_category_code(String advertisement_category_code) {
        set(ADVERTISEMENT_CATEGORY_CODE, advertisement_category_code);
    }

    public String getAdvertisement_title() {
        return getStr(ADVERTISEMENT_TITLE);
    }

    public void setAdvertisement_title(String advertisement_title) {
        set(ADVERTISEMENT_TITLE, advertisement_title);
    }

    public String getAdvertisement_position() {
        return getStr(ADVERTISEMENT_POSITION);
    }

    public void setAdvertisement_position(String advertisement_position) {
        set(ADVERTISEMENT_POSITION, advertisement_position);
    }

    public String getAdvertisement_image() {
        return getStr(ADVERTISEMENT_IMAGE);
    }

    public void setAdvertisement_image(String advertisement_image) {
        set(ADVERTISEMENT_IMAGE, advertisement_image);
    }

    public String getAdvertisement_link() {
        return getStr(ADVERTISEMENT_LINK);
    }

    public void setAdvertisement_link(String advertisement_link) {
        set(ADVERTISEMENT_LINK, advertisement_link);
    }

    public Boolean getAdvertisement_is_float() {
        return getBoolean(ADVERTISEMENT_IS_FLOAT);
    }

    public void setAdvertisement_is_float(Boolean advertisement_is_float) {
        set(ADVERTISEMENT_IS_FLOAT, advertisement_is_float);
    }

    public Integer getAdvertisement_sort() {
        return getInt(ADVERTISEMENT_SORT);
    }

    public void setAdvertisement_sort(Integer advertisement_sort) {
        set(ADVERTISEMENT_SORT, advertisement_sort);
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