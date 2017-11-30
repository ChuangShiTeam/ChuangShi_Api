package com.nowui.chuangshi.api.guangqi.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Entity
public class GuangqiWonderfulShow extends Model<GuangqiWonderfulShow> {

    @Table
    public static final String TABLE_GUANGQI_WONDERFUL_SHOW = "table_guangqi_wonderful_show";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "精彩展示编号", updatable = false)
    public static final String WONDERFUL_SHOW_ID = "wonderful_show_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "封面图片")
    public static final String WONDERFUL_SHOW_COVER_PICTURE = "wonderful_show_cover_picture";

    @Column(type = ColumnType.VARCHAR, length = 200, comment = "视频")
    public static final String WONDERFUL_SHOW_VIDEO = "wonderful_show_video";
    
    @Column(type = ColumnType.INT, length = 11, comment = "排序")
    public static final String WONDERFUL_SHOW_SORT = "wonderful_show_sort";

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
    
    public static final String WONDERFUL_SHOW_COVER_PICTURE_FILE = "wonderful_show_cover_picture_file";

    public String getWonderful_show_id() {
        return getStr(WONDERFUL_SHOW_ID);
    }

    public void setWonderful_show_id(String wonderful_show_id) {
        set(WONDERFUL_SHOW_ID, wonderful_show_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getWonderful_show_cover_picture() {
        return getStr(WONDERFUL_SHOW_COVER_PICTURE);
    }

    public void setWonderful_show_cover_picture(String wonderful_show_cover_picture) {
        set(WONDERFUL_SHOW_COVER_PICTURE, wonderful_show_cover_picture);
    }

    public String getWonderful_show_video() {
        return getStr(WONDERFUL_SHOW_VIDEO);
    }

    public void setWonderful_show_video(String wonderful_show_video) {
        set(WONDERFUL_SHOW_VIDEO, wonderful_show_video);
    }
    
    public Integer getWonderful_show_sort() {
        return getInt(WONDERFUL_SHOW_SORT);
    }

    public void setWonderful_show_sort(Integer wonderful_show_sort) {
        set(WONDERFUL_SHOW_SORT, wonderful_show_sort);
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