package com.nowui.chuangshi.api.jiangling.model;

import java.util.Date;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

@Entity
public class JianglingNewCustomer extends Model<JianglingCustomer> {

    @Table
    public static final String TABLE_JIANGLING_NEW_CUSTOMER = "table_jiangling_new_customer";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "用户编号", updatable = false)
    public static final String USER_ID = "user_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "客户名称")
    public static final String NEW_CUSTOMER_NAME = "customer_name";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "客户手机号")
    public static final String NEW_CUSTOMER_MOBILE = "customer_mobile";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "意向车型")
    public static final String NEW_CUSTOMER_CAR = "customer_car";

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

    public String getNew_customer_name() {
        return getStr(NEW_CUSTOMER_NAME);
    }

    public void setNew_customer_name(String new_customer_name) {
        set(NEW_CUSTOMER_NAME, new_customer_name);
    }

    public String getNew_customer_mobile() {
        return getStr(NEW_CUSTOMER_MOBILE);
    }

    public void setNew_customer_mobile(String new_customer_mobile) {
        set(NEW_CUSTOMER_MOBILE, new_customer_mobile);
    }

    public String getNew_customer_car() {
        return getStr(NEW_CUSTOMER_CAR);
    }

    public void setNew_customer_car(String new_customer_car) {
        set(NEW_CUSTOMER_CAR, new_customer_car);
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