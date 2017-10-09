package com.nowui.chuangshi.api.minhang.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Entity
public class MinhangTimelineEvent extends Model<MinhangTimelineEvent> {

    @Table
    public static final String TABLE_MINHANG_TIMELINE_EVENT = "table_minhang_timeline_event";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "时间轴事件编号", updatable = false)
    public static final String TIMELINE_EVENT_ID = "timeline_event_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "时间轴编号")
    public static final String TIMELINE_ID = "timeline_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "任务编号")
    public static final String TASK_ID = "task_id";

    @Column(type = ColumnType.DATE, length = 0, comment = "时间")
    public static final String TIMELINE_EVENT_TIME = "timeline_event_time";

    @Column(type = ColumnType.VARCHAR, length = 250, comment = "事件标题")
    public static final String TIMELINE_EVENT_TITLE = "timeline_event_title";

    @Column(type = ColumnType.LONGTEXT, length = 0, comment = "事件内容")
    public static final String TIMELINE_EVENT_CONTENT = "timeline_event_content";

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

    public String getTimeline_event_id() {
        return getStr(TIMELINE_EVENT_ID);
    }

    public void setTimeline_event_id(String timeline_event_id) {
        set(TIMELINE_EVENT_ID, timeline_event_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getTimeline_id() {
        return getStr(TIMELINE_ID);
    }

    public void setTimeline_id(String timeline_id) {
        set(TIMELINE_ID, timeline_id);
    }

    public String getTask_id() {
        return getStr(TASK_ID);
    }

    public void setTask_id(String task_id) {
        set(TASK_ID, task_id);
    }

    public Date getTimeline_event_time() {
        return getDate(TIMELINE_EVENT_TIME);
    }

    public void setTimeline_event_time(Date timeline_event_time) {
        set(TIMELINE_EVENT_TIME, timeline_event_time);
    }

    public String getTimeline_event_title() {
        return getStr(TIMELINE_EVENT_TITLE);
    }

    public void setTimeline_event_title(String timeline_event_title) {
        set(TIMELINE_EVENT_TITLE, timeline_event_title);
    }

    public String getTimeline_event_content() {
        return getStr(TIMELINE_EVENT_CONTENT);
    }

    public void setTimeline_event_content(String timeline_event_content) {
        set(TIMELINE_EVENT_CONTENT, timeline_event_content);
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