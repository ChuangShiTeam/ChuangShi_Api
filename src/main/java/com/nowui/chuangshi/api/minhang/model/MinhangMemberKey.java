package com.nowui.chuangshi.api.minhang.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Entity
public class MinhangMemberKey extends Model<MinhangMemberKey> {

    @Table
    public static final String TABLE_MINHANG_MEMBER_KEY = "table_minhang_member_key";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "", updatable = false)
    public static final String MEMBER_KEY_ID = "member_key_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "会员编号")
    public static final String MEMBER_ID = "member_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "用户编号")
    public static final String USER_ID = "user_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "钥匙编号")
    public static final String KEY_ID = "key_id";

    @Column(type = ColumnType.INT, length = 5, comment = "任务完成数量")
    public static final String TASK_COMPLETE_QUANTITY = "task_complete_quantity";

    @Column(type = ColumnType.INT, length = 5, comment = "任务数量")
    public static final String TASK_QUANTITY = "task_quantity";

    @Column(type = ColumnType.TINYINT, length = 1, comment = "是否激活")
    public static final String KEY_IS_ACTIVATED = "key_is_activated";

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

    public String getMember_key_id() {
        return getStr(MEMBER_KEY_ID);
    }

    public void setMember_key_id(String member_key_id) {
        set(MEMBER_KEY_ID, member_key_id);
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

    public String getKey_id() {
        return getStr(KEY_ID);
    }

    public void setKey_id(String key_id) {
        set(KEY_ID, key_id);
    }

    public Integer getTask_complete_quantity() {
        return getInt(TASK_COMPLETE_QUANTITY);
    }

    public void setTask_complete_quantity(Integer task_complete_quantity) {
        set(TASK_COMPLETE_QUANTITY, task_complete_quantity);
    }

    public Integer getTask_quantity() {
        return getInt(TASK_QUANTITY);
    }

    public void setTask_quantity(Integer task_quantity) {
        set(TASK_QUANTITY, task_quantity);
    }

    public Boolean getKey_is_activated() {
        return getBoolean(KEY_IS_ACTIVATED);
    }

    public void setKey_is_activated(Boolean key_is_activated) {
        set(KEY_IS_ACTIVATED, key_is_activated);
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