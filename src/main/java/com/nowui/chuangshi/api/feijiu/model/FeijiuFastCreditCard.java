package com.nowui.chuangshi.api.feijiu.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Entity
public class FeijiuFastCreditCard extends Model<FeijiuFastCreditCard> {

    @Table
    public static final String TABLE_FEIJIU_CREDIT_CARD = "table_feijiu_fast_credit_card";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "信用卡编号", updatable = false)
    public static final String CREDIT_CARD_ID = "credit_card_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 30, comment = "信用卡名称")
    public static final String CREDIT_CARD_NAME = "credit_card_name";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "信用卡图片")
    public static final String CREDIT_CARD_IMAGE = "credit_card_image";

    @Column(type = ColumnType.VARCHAR, length = 250, comment = "信用卡链接")
    public static final String CREDIT_CARD_LINK = "credit_card_link";

    @Column(type = ColumnType.VARCHAR, length = 200, comment = "信用卡介绍")
    public static final String CREDIT_CARD_CONTENT = "credit_card_content";

    @Column(type = ColumnType.INT, length = 3, comment = "信用卡排序")
    public static final String CREDIT_CARD_SORT = "credit_card_sort";

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

    public static final String CREDIT_CARD_IMAGE_FILE = "credit_card_image_file";

    public String getCredit_card_id() {
        return getStr(CREDIT_CARD_ID);
    }

    public void setCredit_card_id(String credit_card_id) {
        set(CREDIT_CARD_ID, credit_card_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getCredit_card_name() {
        return getStr(CREDIT_CARD_NAME);
    }

    public void setCredit_card_name(String credit_card_name) {
        set(CREDIT_CARD_NAME, credit_card_name);
    }

    public String getCredit_card_image() {
        return getStr(CREDIT_CARD_IMAGE);
    }

    public void setCredit_card_image(String credit_card_image) {
        set(CREDIT_CARD_IMAGE, credit_card_image);
    }

    public String getCredit_card_link() {
        return getStr(CREDIT_CARD_LINK);
    }

    public void setCredit_card_link(String credit_card_link) {
        set(CREDIT_CARD_LINK, credit_card_link);
    }

    public String getCredit_card_content() {
        return getStr(CREDIT_CARD_CONTENT);
    }

    public void setCredit_card_content(String credit_card_content) {
        set(CREDIT_CARD_CONTENT, credit_card_content);
    }

    public Integer getCredit_card_sort() {
        return getInt(CREDIT_CARD_SORT);
    }

    public void setCredit_card_sort(Integer credit_card_sort) {
        set(CREDIT_CARD_SORT, credit_card_sort);
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