package com.nowui.chuangshi.api.guangqi.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Entity
public class GuangqiGameArea extends Model<GuangqiGameArea> {

    @Table
    public static final String TABLE_GUANGQI_GAME_AREA = "table_guangqi_game_area";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "赛区编号", updatable = false)
    public static final String GAME_AREA_ID = "game_area_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 200, comment = "名称")
    public static final String GAME_AREA_NAME = "game_area_name";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "封面图片")
    public static final String GAME_AREA_COVER_PICTURE = "game_area_cover_picture";

    @Column(type = ColumnType.INT, length = 11, comment = "排序")
    public static final String GAME_AREA_SORT = "game_area_sort";

    @Column(type = ColumnType.VARCHAR, length = 500, comment = "描述")
    public static final String GAME_AREA_DESCRIPTION = "game_area_description";

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
    
    public static final String GAME_AREA_COVER_PICTURE_FILE = "game_area_cover_picture_file";

    public String getGame_area_id() {
        return getStr(GAME_AREA_ID);
    }

    public void setGame_area_id(String game_area_id) {
        set(GAME_AREA_ID, game_area_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getGame_area_name() {
        return getStr(GAME_AREA_NAME);
    }

    public void setGame_area_name(String game_area_name) {
        set(GAME_AREA_NAME, game_area_name);
    }

    public String getGame_area_cover_picture() {
        return getStr(GAME_AREA_COVER_PICTURE);
    }

    public void setGame_area_cover_picture(String game_area_cover_picture) {
        set(GAME_AREA_COVER_PICTURE, game_area_cover_picture);
    }

    public Integer getGame_area_sort() {
        return getInt(GAME_AREA_SORT);
    }

    public void setGame_area_sort(Integer game_area_sort) {
        set(GAME_AREA_SORT, game_area_sort);
    }

    public String getGame_area_description() {
        return getStr(GAME_AREA_DESCRIPTION);
    }

    public void setGame_area_description(String game_area_description) {
        set(GAME_AREA_DESCRIPTION, game_area_description);
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