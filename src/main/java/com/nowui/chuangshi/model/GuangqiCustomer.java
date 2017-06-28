package com.nowui.chuangshi.model;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class GuangqiCustomer extends Model<GuangqiCustomer> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "客户编号")
    public static final String CUSTOMER_ID = "customer_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号")
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "客户姓名")
    public static final String CUSTOMER_NAME = "customer_name";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "手机号码")
    public static final String CUSTOMER_PHONE = "customer_phone";

    @Column(type = ColumnType.VARCHAR, length = 30, comment = "省份")
    public static final String CUSTOMER_PROVINCE = "customer_province";

    @Column(type = ColumnType.VARCHAR, length = 30, comment = "城市")
    public static final String CUSTOMER_CITY = "customer_city";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "经销商")
    public static final String COSTOMER_DEALER = "costomer_dealer";

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

    public String getCostomer_dealer() {
        return getStr(COSTOMER_DEALER);
    }

    public void setCostomer_dealer(String costomer_dealer) {
        set(COSTOMER_DEALER, costomer_dealer);
    }

}