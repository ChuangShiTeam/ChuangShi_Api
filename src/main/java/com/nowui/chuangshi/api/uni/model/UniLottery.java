package com.nowui.chuangshi.api.uni.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Entity
public class UniLottery extends Model<UniLottery> {

    @Table
    public static final String TABLE_UNI_LOTTERY = "table_uni_lottery";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "用户编号", updatable = false)
    public static final String USER_ID = "user_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 10, comment = "抽签号码")
    public static final String LOTTERY_NUMBER = "lottery_number";

    @Column(type = ColumnType.TINYINT, length = 1, comment = "抽签用户性别")
    public static final String LOTTERY_USER_SEX = "lottery_user_sex";

    @Column(type = ColumnType.VARCHAR, length = 11, comment = "抽签用户手机号码")
    public static final String LOTTERY_USER_MOBILE = "lottery_user_mobile";

    @Column(type = ColumnType.INT, length = 3, comment = "抽签次数")
    public static final String LOTTERY_TIME = "lottery_time";

    @Column(type = ColumnType.TINYINT, length = 1, comment = "是否抽签")
    public static final String LOTTERY_STATUS = "lottery_status";

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

    public String getUser_id() {
        return getStr(USER_ID);
    }

    public void setUser_id(String user_id) {
        set(USER_ID, user_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getLottery_number() {
        return getStr(LOTTERY_NUMBER);
    }

    public void setLottery_number(String lottery_number) {
        set(LOTTERY_NUMBER, lottery_number);
    }

    public Boolean getLottery_user_sex() {
        return getBoolean(LOTTERY_USER_SEX);
    }

    public void setLottery_user_sex(Boolean lottery_user_sex) {
        set(LOTTERY_USER_SEX, lottery_user_sex);
    }

    public String getLottery_user_mobile() {
        return getStr(LOTTERY_USER_MOBILE);
    }

    public void setLottery_user_mobile(String lottery_user_mobile) {
        set(LOTTERY_USER_MOBILE, lottery_user_mobile);
    }

    public Integer getLottery_time() {
        return getInt(LOTTERY_TIME);
    }

    public void setLottery_time(Integer lottery_time) {
        set(LOTTERY_TIME, lottery_time);
    }

    public Boolean getLottery_status() {
        return getBoolean(LOTTERY_STATUS);
    }

    public void setLottery_status(Boolean lottery_status) {
        set(LOTTERY_STATUS, lottery_status);
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