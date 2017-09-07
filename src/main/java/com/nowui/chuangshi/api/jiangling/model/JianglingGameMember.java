package com.nowui.chuangshi.api.jiangling.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Entity
public class JianglingGameMember extends Model<JianglingGameMember> {

    @Table
    public static final String TABLE_JIANGLING_GAME_MEMBER = "table_jiangling_game_member";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String GAME_ID = "game_id";

    @Column(type = ColumnType.VARCHAR, length = 150, comment = "")
    public static final String GAME_MEMBER_TOKEN = "game_member_token";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "")
    public static final String GAME_MEMBER_NAME = "game_member_name";

    @Column(type = ColumnType.VARCHAR, length = 200, comment = "")
    public static final String GAME_MEMBER_AVATAR = "game_member_avatar";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "")
    public static final String GAME_MEMBER_SCORE = "game_member_score";

    @Column(type = ColumnType.INT, length = 5, comment = "")
    public static final String GAME_MEMBER_RANK = "game_member_rank";

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

    public String getGame_id() {
        return getStr(GAME_ID);
    }

    public void setGame_id(String game_id) {
        set(GAME_ID, game_id);
    }

    public String getGame_member_token() {
        return getStr(GAME_MEMBER_TOKEN);
    }

    public void setGame_member_token(String game_member_token) {
        set(GAME_MEMBER_TOKEN, game_member_token);
    }

    public String getGame_member_name() {
        return getStr(GAME_MEMBER_NAME);
    }

    public void setGame_member_name(String game_member_name) {
        set(GAME_MEMBER_NAME, game_member_name);
    }

    public String getGame_member_avatar() {
        return getStr(GAME_MEMBER_AVATAR);
    }

    public void setGame_member_avatar(String game_member_avatar) {
        set(GAME_MEMBER_AVATAR, game_member_avatar);
    }

    public String getGame_member_score() {
        return getStr(GAME_MEMBER_SCORE);
    }

    public void setGame_member_score(String game_member_score) {
        set(GAME_MEMBER_SCORE, game_member_score);
    }

    public Integer getGame_member_rank() {
        return getInt(GAME_MEMBER_RANK);
    }

    public void setGame_member_rank(Integer game_member_rank) {
        set(GAME_MEMBER_RANK, game_member_rank);
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