package com.nowui.chuangshi.api.guangqi.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Entity
public class GuangqiGameAreaDetail extends Model<GuangqiGameAreaDetail> {

    @Table
    public static final String TABLE_GUANGQI_GAME_AREA_DETAIL = "table_guangqi_game_area_detail";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "赛区资料编号", updatable = false)
    public static final String GAME_AREA_DETAIL_ID = "game_area_detail_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "赛区编号")
    public static final String GAME_AREA_ID = "game_area_id";

    @Column(type = ColumnType.VARCHAR, length = 25, comment = "类型")
    public static final String GAME_AREA_DETAIL_TYPE = "game_area_detail_type";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "图片")
    public static final String GAME_AREA_DETAIL_IMAGE = "game_area_detail_image";

    @Column(type = ColumnType.VARCHAR, length = 200, comment = "视频")
    public static final String GAME_AREA_DETAIL_VIDEO = "game_area_detail_video";

    @Column(type = ColumnType.INT, length = 11, comment = "")
    public static final String GAME_AREA_DETAIL_SORT = "game_area_detail_sort";

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
    
    public static final String GAME_AREA_DETAIL_IMAGE_FILE = "game_area_detail_image_file";

    public String getGame_area_detail_id() {
        return getStr(GAME_AREA_DETAIL_ID);
    }

    public void setGame_area_detail_id(String game_area_detail_id) {
        set(GAME_AREA_DETAIL_ID, game_area_detail_id);
    }

    public String getGame_area_id() {
        return getStr(GAME_AREA_ID);
    }

    public void setGame_area_id(String game_area_id) {
        set(GAME_AREA_ID, game_area_id);
    }

    public String getGame_area_detail_type() {
        return getStr(GAME_AREA_DETAIL_TYPE);
    }

    public void setGame_area_detail_type(String game_area_detail_type) {
        set(GAME_AREA_DETAIL_TYPE, game_area_detail_type);
    }

    public String getGame_area_detail_image() {
        return getStr(GAME_AREA_DETAIL_IMAGE);
    }

    public void setGame_area_detail_image(String game_area_detail_image) {
        set(GAME_AREA_DETAIL_IMAGE, game_area_detail_image);
    }

    public String getGame_area_detail_video() {
        return getStr(GAME_AREA_DETAIL_VIDEO);
    }

    public void setGame_area_detail_video(String game_area_detail_video) {
        set(GAME_AREA_DETAIL_VIDEO, game_area_detail_video);
    }

    public Integer getGame_area_detail_sort() {
        return getInt(GAME_AREA_DETAIL_SORT);
    }

    public void setGame_area_detail_sort(Integer game_area_detail_sort) {
        set(GAME_AREA_DETAIL_SORT, game_area_detail_sort);
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