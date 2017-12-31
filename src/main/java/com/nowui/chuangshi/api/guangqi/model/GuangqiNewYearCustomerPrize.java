package com.nowui.chuangshi.api.guangqi.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Entity
public class GuangqiNewYearCustomerPrize extends Model<GuangqiNewYearCustomerPrize> {

    @Table
    public static final String TABLE_GUANGQI_NEW_YEAR_CUSTOMER_PRIZE = "table_guangqi_new_year_customer_prize";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "客户奖品编号", updatable = false)
    public static final String NEW_YEAR_CUSTOMER_PRIZE_ID = "new_year_customer_prize_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "客户编号")
    public static final String NEW_YEAR_CUSTOMER_ID = "new_year_customer_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "奖品编号")
    public static final String NEW_YEAR_PRIZE_ID = "new_year_prize_id";

    @Column(type = ColumnType.VARCHAR, length = 10, comment = "客户奖品日期")
    public static final String NEW_YEAR_CUSTOMER_PRIZE_DATE = "new_year_customer_prize_date";

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

    public String getNew_year_customer_prize_id() {
        return getStr(NEW_YEAR_CUSTOMER_PRIZE_ID);
    }

    public void setNew_year_customer_prize_id(String new_year_customer_prize_id) {
        set(NEW_YEAR_CUSTOMER_PRIZE_ID, new_year_customer_prize_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getNew_year_customer_id() {
        return getStr(NEW_YEAR_CUSTOMER_ID);
    }

    public void setNew_year_customer_id(String new_year_customer_id) {
        set(NEW_YEAR_CUSTOMER_ID, new_year_customer_id);
    }

    public String getNew_year_prize_id() {
        return getStr(NEW_YEAR_PRIZE_ID);
    }

    public void setNew_year_prize_id(String new_year_prize_id) {
        set(NEW_YEAR_PRIZE_ID, new_year_prize_id);
    }

    public String getNew_year_customer_prize_date() {
        return getStr(NEW_YEAR_CUSTOMER_PRIZE_DATE);
    }

    public void setNew_year_customer_prize_date(String new_year_customer_prize_date) {
        set(NEW_YEAR_CUSTOMER_PRIZE_DATE, new_year_customer_prize_date);
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