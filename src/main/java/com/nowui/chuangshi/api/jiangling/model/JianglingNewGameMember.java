package com.nowui.chuangshi.api.jiangling.model;

import java.util.Date;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

@Entity
public class JianglingNewGameMember extends Model<JianglingNewGameMember> {

    @Table
    public static final String TABLE_JIANGLING_NEW_GAME_MEMBER = "table_jiangling_new_game_member";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String NEW_GAME_ID = "new_game_id";

    @Column(type = ColumnType.VARCHAR, length = 150, comment = "")
    public static final String NEW_GAME_MEMBER_TOKEN = "new_game_member_token";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "")
    public static final String NEW_GAME_MEMBER_NAME = "new_game_member_name";

    @Column(type = ColumnType.VARCHAR, length = 200, comment = "")
    public static final String NEW_GAME_MEMBER_AVATAR = "new_game_member_avatar";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "")
    public static final String NEW_GAME_MEMBER_SCORE = "new_game_member_score";

    @Column(type = ColumnType.INT, length = 5, comment = "")
    public static final String NEW_GAME_MEMBER_RANK = "new_game_member_rank";

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

    public String getNew_game_id() {
        return getStr(NEW_GAME_ID);
    }

    public void setNew_game_id(String new_game_id) {
        set(NEW_GAME_ID, new_game_id);
    }

    public String getNew_game_member_token() {
        return getStr(NEW_GAME_MEMBER_TOKEN);
    }

    public void setNew_game_member_token(String new_game_member_token) {
        set(NEW_GAME_MEMBER_TOKEN, new_game_member_token);
    }

    public String getNew_game_member_name() {
        return getStr(NEW_GAME_MEMBER_NAME);
    }

    public void setNew_game_member_name(String new_game_member_name) {
        set(NEW_GAME_MEMBER_NAME, new_game_member_name);
    }

    public String getNew_game_member_avatar() {
        return getStr(NEW_GAME_MEMBER_AVATAR);
    }

    public void setNew_game_member_avatar(String new_game_member_avatar) {
        set(NEW_GAME_MEMBER_AVATAR, new_game_member_avatar);
    }

    public String getNew_game_member_score() {
        return getStr(NEW_GAME_MEMBER_SCORE);
    }

    public void setNew_game_member_score(String new_game_member_score) {
        set(NEW_GAME_MEMBER_SCORE, new_game_member_score);
    }

    public Integer getNew_game_member_rank() {
        return getInt(NEW_GAME_MEMBER_RANK);
    }

    public void setNew_game_member_rank(Integer new_game_member_rank) {
        set(NEW_GAME_MEMBER_RANK, new_game_member_rank);
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