package com.nowui.chuangshi.api.minhang.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Entity
public class MinhangKey extends Model<MinhangKey> {

    @Table
    public static final String TABLE_MINHANG_KEY = "table_minhang_key";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "钥匙编号", updatable = false)
    public static final String KEY_ID = "key_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 30, comment = "钥匙名称")
    public static final String KEY_NAME = "key_name";
    
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "钥匙图片")
    public static final String KEY_IMAGE = "key_image";

    @Column(type = ColumnType.INT, length = 5, comment = "钥匙激活需完成任务数")
    public static final String KEY_ACTIVATED_TASK_QUANTITY = "key_activated_task_quantity";
    
    @Column(type = ColumnType.INT, length = 5, comment = "排序")
    public static final String KEY_SORT = "key_sort";

    @Column(type = ColumnType.VARCHAR, length = 2000, comment = "钥匙简介")
    public static final String KEY_DESCRIPTION = "key_description";

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
    
    public static final String KEY_IMAGE_FILE = "key_image_file";

    public String getKey_id() {
        return getStr(KEY_ID);
    }

    public void setKey_id(String key_id) {
        set(KEY_ID, key_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getKey_name() {
        return getStr(KEY_NAME);
    }

    public void setKey_name(String key_name) {
        set(KEY_NAME, key_name);
    }
    
    public String getKey_image() {
        return getStr(KEY_IMAGE);
    }
    
    public void setKey_image(String key_image) {
        set(KEY_IMAGE, key_image);
    }

    public Integer getKey_activated_task_quantity() {
        return getInt(KEY_ACTIVATED_TASK_QUANTITY);
    }

    public void setKey_activated_task_quantity(String key_activated_task_quantity) {
        set(KEY_ACTIVATED_TASK_QUANTITY, key_activated_task_quantity);
    }
    
    public Integer getKey_sort() {
        return getInt(KEY_SORT);
    }
    
    public void setKey_sort(String key_sort) {
        set(KEY_SORT, key_sort);
    }

    public String getKey_description() {
        return getStr(KEY_DESCRIPTION);
    }

    public void setKey_description(String key_description) {
        set(KEY_DESCRIPTION, key_description);
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