package com.nowui.chuangshi.model;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class Customer extends Model<Customer> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "客户编号")
    public static final String CUSTOMER_ID = "customer_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号")
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "客户名称")
    public static final String CUSTOMER_NAME = "customer_name";

    @Column(type = ColumnType.VARCHAR, length = 2, comment = "性别")
    public static final String CUSTOMER_SEX = "customer_sex";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "出生日期")
    public static final String CUSTOMER_BIRTHDAY = "customer_birthday";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "客户电话号码")
    public static final String CUSTOMER_TEL = "customer_tel";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "客户手机号码")
    public static final String CUSTOMER_MOBILE = "customer_mobile";

    @Column(type = ColumnType.VARCHAR, length = 50, comment = "客户邮编")
    public static final String CUSTOMER_POSTCODE = "customer_postcode";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "身份证号")
    public static final String CUSTOMER_ID_CARD = "customer_id_card";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "省份")
    public static final String CUSTOMER_PROVINCE = "customer_province";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "城市")
    public static final String CUSTOMER_CITY = "customer_city";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "区域")
    public static final String CUSTOMER_AREA = "customer_area";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "详细地址")
    public static final String CUSTOMER_ADDRESS = "customer_address";

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

    public String getCustomer_sex() {
        return getStr(CUSTOMER_SEX);
    }

    public void setCustomer_sex(String customer_sex) {
        set(CUSTOMER_SEX, customer_sex);
    }

    public String getCustomer_birthday() {
        return getStr(CUSTOMER_BIRTHDAY);
    }

    public void setCustomer_birthday(String customer_birthday) {
        set(CUSTOMER_BIRTHDAY, customer_birthday);
    }

    public String getCustomer_tel() {
        return getStr(CUSTOMER_TEL);
    }

    public void setCustomer_tel(String customer_tel) {
        set(CUSTOMER_TEL, customer_tel);
    }

    public String getCustomer_mobile() {
        return getStr(CUSTOMER_MOBILE);
    }

    public void setCustomer_mobile(String customer_mobile) {
        set(CUSTOMER_MOBILE, customer_mobile);
    }

    public String getCustomer_postcode() {
        return getStr(CUSTOMER_POSTCODE);
    }

    public void setCustomer_postcode(String customer_postcode) {
        set(CUSTOMER_POSTCODE, customer_postcode);
    }

    public String getCustomer_id_card() {
        return getStr(CUSTOMER_ID_CARD);
    }

    public void setCustomer_id_card(String customer_id_card) {
        set(CUSTOMER_ID_CARD, customer_id_card);
    }

    public String getCustomer_province() {
        return getStr(CUSTOMER_PROVINCE);
    }

    public void setCustomer_province(String customer_province) {
        set(CUSTOMER_PROVINCE, customer_province);
    }

    public String getCustomer_city() {
        return getStr(CUSTOMER_CITY);
    }

    public void setCustomer_city(String customer_city) {
        set(CUSTOMER_CITY, customer_city);
    }

    public String getCustomer_area() {
        return getStr(CUSTOMER_AREA);
    }

    public void setCustomer_area(String customer_area) {
        set(CUSTOMER_AREA, customer_area);
    }

    public String getCustomer_address() {
        return getStr(CUSTOMER_ADDRESS);
    }

    public void setCustomer_address(String customer_address) {
        set(CUSTOMER_ADDRESS, customer_address);
    }

}