package com.nowui.chuangshi.api.minhang.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Entity
public class MinhangPartySong extends Model<MinhangPartySong> {

    @Table
    public static final String TABLE_MINHANG_PARTY_SONG = "table_minhang_party_song";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "党歌编号", updatable = false)
    public static final String PARTY_SONG_ID = "party_song_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "任务编号")
    public static final String TASK_ID = "task_id";

    @Column(type = ColumnType.LONGTEXT, length = 0, comment = "内容")
    public static final String PARTY_SONG_CONTENT = "party_song_content";

    @Column(type = ColumnType.VARCHAR, length = 200, comment = "语音地址")
    public static final String PARTY_SONG_URL = "party_song_url";

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

    public String getParty_song_id() {
        return getStr(PARTY_SONG_ID);
    }

    public void setParty_song_id(String party_song_id) {
        set(PARTY_SONG_ID, party_song_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getTask_id() {
        return getStr(TASK_ID);
    }

    public void setTask_id(String task_id) {
        set(TASK_ID, task_id);
    }

    public String getParty_song_content() {
        return getStr(PARTY_SONG_CONTENT);
    }

    public void setParty_song_content(String party_song_content) {
        set(PARTY_SONG_CONTENT, party_song_content);
    }

    public String getParty_song_url() {
        return getStr(PARTY_SONG_URL);
    }

    public void setParty_song_url(String party_song_url) {
        set(PARTY_SONG_URL, party_song_url);
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