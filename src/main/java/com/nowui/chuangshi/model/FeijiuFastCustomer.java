package com.nowui.chuangshi.model;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class FeijiuFastCustomer extends Model<FeijiuFastCustomer> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "客户编号")
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

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "借款金额")
    public static final String CUSTOMER_MONEY = "customer_money";

    @Column(type = ColumnType.VARCHAR, length = 2, comment = "是否名下有房")
    public static final String CUSTOMER_FANG = "customer_fang";

    @Column(type = ColumnType.VARCHAR, length = 2, comment = "是否名下有车")
    public static final String CUSTOMER_CHE = "customer_che";

    @Column(type = ColumnType.VARCHAR, length = 2, comment = "是否有信用卡")
    public static final String CUSTOMER_XIN = "customer_xin";

    @Column(type = ColumnType.VARCHAR, length = 2, comment = "是否有寿险保单")
    public static final String CUSTOMER_SHOU = "customer_shou";

    @Column(type = ColumnType.VARCHAR, length = 2, comment = "是否有微粒贷")
    public static final String CUSTOMER_DAI = "customer_dai";

    @Column(type = ColumnType.VARCHAR, length = 2, comment = "是否有公积金")
    public static final String CUSTOMER_GONG = "customer_gong";

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

    public String getCustomer_money() {
        return getStr(CUSTOMER_MONEY);
    }

    public void setCustomer_money(String customer_money) {
        set(CUSTOMER_MONEY, customer_money);
    }

    public String getCustomer_fang() {
        return getStr(CUSTOMER_FANG);
    }

    public void setCustomer_fang(String customer_fang) {
        set(CUSTOMER_FANG, customer_fang);
    }

    public String getCustomer_che() {
        return getStr(CUSTOMER_CHE);
    }

    public void setCustomer_che(String customer_che) {
        set(CUSTOMER_CHE, customer_che);
    }

    public String getCustomer_xin() {
        return getStr(CUSTOMER_XIN);
    }

    public void setCustomer_xin(String customer_xin) {
        set(CUSTOMER_XIN, customer_xin);
    }

    public String getCustomer_shou() {
        return getStr(CUSTOMER_SHOU);
    }

    public void setCustomer_shou(String customer_shou) {
        set(CUSTOMER_SHOU, customer_shou);
    }

    public String getCustomer_dai() {
        return getStr(CUSTOMER_DAI);
    }

    public void setCustomer_dai(String customer_dai) {
        set(CUSTOMER_DAI, customer_dai);
    }

    public String getCustomer_gong() {
        return getStr(CUSTOMER_GONG);
    }

    public void setCustomer_gong(String customer_gong) {
        set(CUSTOMER_GONG, customer_gong);
    }

}