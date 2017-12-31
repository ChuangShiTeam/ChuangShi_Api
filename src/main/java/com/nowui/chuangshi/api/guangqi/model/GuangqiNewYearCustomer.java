package com.nowui.chuangshi.api.guangqi.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.util.Date;

@Entity
public class GuangqiNewYearCustomer extends Model<GuangqiNewYearCustomer> {

    @Table
    public static final String TABLE_GUANGQI_NEW_YEAR_CUSTOMER = "table_guangqi_new_year_customer";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "客户编号", updatable = false)
    public static final String NEW_YEAR_CUSTOMER_ID = "new_year_customer_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 200, comment = "车型")
    public static final String NEW_YEAR_CUSTOMER_CAR_MODEL = "new_year_customer_car_model";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "客户姓名")
    public static final String NEW_YEAR_CUSTOMER_NAME = "new_year_customer_name";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "手机号码")
    public static final String NEW_YEAR_CUSTOMER_PHONE = "new_year_customer_phone";

    @Column(type = ColumnType.VARCHAR, length = 30, comment = "省份")
    public static final String NEW_YEAR_CUSTOMER_PROVINCE = "new_year_customer_province";

    @Column(type = ColumnType.VARCHAR, length = 30, comment = "城市")
    public static final String NEW_YEAR_CUSTOMER_CITY = "new_year_customer_city";

    @Column(type = ColumnType.VARCHAR, length = 200, comment = "门店")
    public static final String NEW_YEAR_CUSTOMER_DEALER = "new_year_customer_dealer";

    @Column(type = ColumnType.VARCHAR, length = 50, comment = "来源")
    public static final String NEW_YEAR_CUSTOMER_FROM = "new_year_customer_from";

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

    public String getNew_year_customer_id() {
        return getStr(NEW_YEAR_CUSTOMER_ID);
    }

    public void setNew_year_customer_id(String new_year_customer_id) {
        set(NEW_YEAR_CUSTOMER_ID, new_year_customer_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getNew_year_customer_car_model() {
        return getStr(NEW_YEAR_CUSTOMER_CAR_MODEL);
    }

    public void setNew_year_customer_car_model(String new_year_customer_car_model) {
        set(NEW_YEAR_CUSTOMER_CAR_MODEL, new_year_customer_car_model);
    }

    public String getNew_year_customer_name() {
        return getStr(NEW_YEAR_CUSTOMER_NAME);
    }

    public void setNew_year_customer_name(String new_year_customer_name) {
        set(NEW_YEAR_CUSTOMER_NAME, new_year_customer_name);
    }

    public String getNew_year_customer_phone() {
        return getStr(NEW_YEAR_CUSTOMER_PHONE);
    }

    public void setNew_year_customer_phone(String new_year_customer_phone) {
        set(NEW_YEAR_CUSTOMER_PHONE, new_year_customer_phone);
    }

    public String getNew_year_customer_province() {
        return getStr(NEW_YEAR_CUSTOMER_PROVINCE);
    }

    public void setNew_year_customer_province(String new_year_customer_province) {
        set(NEW_YEAR_CUSTOMER_PROVINCE, new_year_customer_province);
    }

    public String getNew_year_customer_city() {
        return getStr(NEW_YEAR_CUSTOMER_CITY);
    }

    public void setNew_year_customer_city(String new_year_customer_city) {
        set(NEW_YEAR_CUSTOMER_CITY, new_year_customer_city);
    }

    public String getNew_year_customer_dealer() {
        return getStr(NEW_YEAR_CUSTOMER_DEALER);
    }

    public void setNew_year_customer_dealer(String new_year_customer_dealer) {
        set(NEW_YEAR_CUSTOMER_DEALER, new_year_customer_dealer);
    }

    public String getNew_year_customer_from() {
        return getStr(NEW_YEAR_CUSTOMER_FROM);
    }

    public void setNew_year_customer_from(String new_year_customer_from) {
        set(NEW_YEAR_CUSTOMER_FROM, new_year_customer_from);
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