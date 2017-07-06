package com.nowui.chuangshi.model;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class Customer extends Model<Customer> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String CUSTOMER_ID = "customer_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号")
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "客户名称")
    public static final String CUSTOMER_NAME = "customer_name";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "手机号码")
    public static final String CUSTOMER_PHONE = "customer_phone";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "出生日期")
    public static final String CUSTOMER_BIRTHDAY = "customer_birthday";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "所在城市")
    public static final String CUSTOMER_CITY = "customer_city";

    @Column(type = ColumnType.VARCHAR, length = 2, comment = "性别")
    public static final String CUSTOMER_SEX = "customer_sex";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "身份证号")
    public static final String CUSTOMER_ID_CARD = "customer_id_card";

    public String getCustomer_id() {
        return getStr(CUSTOMER_ID);
    }

    public void setCustomer_id(String customer_id) {
        set(CUSTOMER_ID, customer_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getCustomer_name() {
        return getStr(CUSTOMER_NAME);
    }

    public void setCustomer_name(String customer_name) {
        set(CUSTOMER_NAME, customer_name);
    }

    public String getCustomer_phone() {
        return getStr(CUSTOMER_PHONE);
    }

    public void setCustomer_phone(String customer_phone) {
        set(CUSTOMER_PHONE, customer_phone);
    }

    public String getCustomer_birthday() {
        return getStr(CUSTOMER_BIRTHDAY);
    }

    public void setCustomer_birthday(String customer_birthday) {
        set(CUSTOMER_BIRTHDAY, customer_birthday);
    }

    public String getCustomer_city() {
        return getStr(CUSTOMER_CITY);
    }

    public void setCustomer_city(String customer_city) {
        set(CUSTOMER_CITY, customer_city);
    }

    public String getCustomer_sex() {
        return getStr(CUSTOMER_SEX);
    }

    public void setCustomer_sex(String customer_sex) {
        set(CUSTOMER_SEX, customer_sex);
    }

    public String getCustomer_id_card() {
        return getStr(CUSTOMER_ID_CARD);
    }

    public void setCustomer_id_card(String customer_id_card) {
        set(CUSTOMER_ID_CARD, customer_id_card);
    }

}