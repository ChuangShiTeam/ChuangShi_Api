package com.nowui.chuangshi.api.minhang.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Entity
public class MinhangMemberLocation extends Model<MinhangMemberLocation> {

    @Table
    public static final String TABLE_MINHANG_MEMBER_LOCATION = "table_minhang_member_location";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "", updatable = false)
    public static final String MEMBER_LOCATION_ID = "member_location_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "会员编号")
    public static final String MEMBER_ID = "member_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "用户编号")
    public static final String USER_ID = "user_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "任务编号")
    public static final String TASK_ID = "task_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "任务位置编号")
    public static final String LOCATION_ID = "location_id";

    @Column(type = ColumnType.VARCHAR, length = 200, comment = "位置名称")
    public static final String LOCATION_NAME = "location_name";

    @Column(type = ColumnType.VARCHAR, length = 200, comment = "详细地址")
    public static final String LOCATION_ADDRESS = "location_address";

    @Column(type = ColumnType.VARCHAR, length = 50, comment = "纬度")
    public static final String LOCATION_LATITUDE = "location_latitude";

    @Column(type = ColumnType.VARCHAR, length = 50, comment = "经度")
    public static final String LOCATION_LONGITUDE = "location_longitude";

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

    public String getMember_location_id() {
        return getStr(MEMBER_LOCATION_ID);
    }

    public void setMember_location_id(String member_location_id) {
        set(MEMBER_LOCATION_ID, member_location_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getMember_id() {
        return getStr(MEMBER_ID);
    }

    public void setMember_id(String member_id) {
        set(MEMBER_ID, member_id);
    }

    public String getUser_id() {
        return getStr(USER_ID);
    }

    public void setUser_id(String user_id) {
        set(USER_ID, user_id);
    }

    public String getTask_id() {
        return getStr(TASK_ID);
    }

    public void setTask_id(String task_id) {
        set(TASK_ID, task_id);
    }

    public String getLocation_id() {
        return getStr(LOCATION_ID);
    }

    public void setLocation_id(String location_id) {
        set(LOCATION_ID, location_id);
    }

    public String getLocation_name() {
        return getStr(LOCATION_NAME);
    }

    public void setLocation_name(String location_name) {
        set(LOCATION_NAME, location_name);
    }

    public String getLocation_address() {
        return getStr(LOCATION_ADDRESS);
    }

    public void setLocation_address(String location_address) {
        set(LOCATION_ADDRESS, location_address);
    }

    public String getLocation_latitude() {
        return getStr(LOCATION_LATITUDE);
    }

    public void setLocation_latitude(String location_latitude) {
        set(LOCATION_LATITUDE, location_latitude);
    }

    public String getLocation_longitude() {
        return getStr(LOCATION_LONGITUDE);
    }

    public void setLocation_longitude(String location_longitude) {
        set(LOCATION_LONGITUDE, location_longitude);
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