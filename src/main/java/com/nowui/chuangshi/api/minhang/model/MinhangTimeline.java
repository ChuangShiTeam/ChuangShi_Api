package com.nowui.chuangshi.api.minhang.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Entity
public class MinhangTimeline extends Model<MinhangTimeline> {

    @Table
    public static final String TABLE_MINHANG_TIMELINE = "table_minhang_timeline";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "时间轴编号", updatable = false)
    public static final String TIMELINE_ID = "timeline_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "年份")
    public static final String TIMELINE_YEAR = "timeline_year";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "图片")
    public static final String TIMELINE_IMAGE = "timeline_image";

    @Column(type = ColumnType.VARCHAR, length = 500, comment = "描述")
    public static final String TIMELINE_DESCRIPTION = "timeline_description";

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
    
    public static final String TIMELINE_IMAGE_FILE = "timeline_image_file";

    public String getTimeline_id() {
        return getStr(TIMELINE_ID);
    }

    public void setTimeline_id(String timeline_id) {
        set(TIMELINE_ID, timeline_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getTimeline_year() {
        return getStr(TIMELINE_YEAR);
    }

    public void setTimeline_year(String timeline_year) {
        set(TIMELINE_YEAR, timeline_year);
    }

    public String getTimeline_image() {
        return getStr(TIMELINE_IMAGE);
    }

    public void setTimeline_image(String timeline_image) {
        set(TIMELINE_IMAGE, timeline_image);
    }

    public String getTimeline_description() {
        return getStr(TIMELINE_DESCRIPTION);
    }

    public void setTimeline_description(String timeline_description) {
        set(TIMELINE_DESCRIPTION, timeline_description);
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