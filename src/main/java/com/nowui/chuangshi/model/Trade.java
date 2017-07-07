package com.nowui.chuangshi.model;

import java.math.BigDecimal;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class Trade extends Model<Trade> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String TRADE_ID = "trade_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String USER_ID = "user_id";

    @Column(type = ColumnType.VARCHAR, length = 15, comment = "")
    public static final String TRADE_NUMBER = "trade_number";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "")
    public static final String TRADE_RECEIVER_NAME = "trade_receiver_name";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "")
    public static final String TRADE_RECEIVER_MOBILE = "trade_receiver_mobile";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "")
    public static final String TRADE_RECEIVER_PROVINCE = "trade_receiver_province";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "")
    public static final String TRADE_RECEIVER_CITY = "trade_receiver_city";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "")
    public static final String TRADE_RECEIVER_AREA = "trade_receiver_area";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "")
    public static final String TRADE_RECEIVER_ADDRESS = "trade_receiver_address";

    @Column(type = ColumnType.VARCHAR, length = 200, comment = "")
    public static final String TRADE_MESSAGE = "trade_message";

    @Column(type = ColumnType.INT, length = 5, comment = "")
    public static final String TRADE_PRODUCT_QUANTITY = "trade_product_quantity";

    @Column(type = ColumnType.DECIMAL, length = 0, comment = "")
    public static final String TRADE_PRODUCT_AMOUNT = "trade_product_amount";

    @Column(type = ColumnType.DECIMAL, length = 0, comment = "")
    public static final String TRADE_EXPRESS_AMOUNT = "trade_express_amount";

    @Column(type = ColumnType.DECIMAL, length = 0, comment = "")
    public static final String TRADE_DISCOUNT_AMOUNT = "trade_discount_amount";

    @Column(type = ColumnType.TINYINT, length = 1, comment = "")
    public static final String TRADE_IS_COMMISSION = "trade_is_commission";

    @Column(type = ColumnType.TINYINT, length = 1, comment = "")
    public static final String TRADE_IS_CONFIRM = "trade_is_confirm";

    @Column(type = ColumnType.TINYINT, length = 1, comment = "")
    public static final String TRADE_IS_PAY = "trade_is_pay";

    @Column(type = ColumnType.VARCHAR, length = 10, comment = "")
    public static final String TRADE_FLOW = "trade_flow";

    @Column(type = ColumnType.VARCHAR, length = 10, comment = "")
    public static final String TRADE_STATUS = "trade_status";

    @Column(type = ColumnType.VARCHAR, length = 10, comment = "")
    public static final String TRADE_AUDIT_STATUS = "trade_audit_status";
    
    public static final String TRADE_PRODUCT_SKU_LIST = "trade_product_sku_list";

    public String getTrade_id() {
        return getStr(TRADE_ID);
    }

    public void setTrade_id(String trade_id) {
        set(TRADE_ID, trade_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getUser_id() {
        return getStr(USER_ID);
    }

    public void setUser_id(String user_id) {
        set(USER_ID, user_id);
    }

    public String getTrade_number() {
        return getStr(TRADE_NUMBER);
    }

    public void setTrade_number(String trade_number) {
        set(TRADE_NUMBER, trade_number);
    }

    public String getTrade_receiver_name() {
        return getStr(TRADE_RECEIVER_NAME);
    }

    public void setTrade_receiver_name(String trade_receiver_name) {
        set(TRADE_RECEIVER_NAME, trade_receiver_name);
    }

    public String getTrade_receiver_mobile() {
        return getStr(TRADE_RECEIVER_MOBILE);
    }

    public void setTrade_receiver_mobile(String trade_receiver_mobile) {
        set(TRADE_RECEIVER_MOBILE, trade_receiver_mobile);
    }

    public String getTrade_receiver_province() {
        return getStr(TRADE_RECEIVER_PROVINCE);
    }

    public void setTrade_receiver_province(String trade_receiver_province) {
        set(TRADE_RECEIVER_PROVINCE, trade_receiver_province);
    }

    public String getTrade_receiver_city() {
        return getStr(TRADE_RECEIVER_CITY);
    }

    public void setTrade_receiver_city(String trade_receiver_city) {
        set(TRADE_RECEIVER_CITY, trade_receiver_city);
    }

    public String getTrade_receiver_area() {
        return getStr(TRADE_RECEIVER_AREA);
    }

    public void setTrade_receiver_area(String trade_receiver_area) {
        set(TRADE_RECEIVER_AREA, trade_receiver_area);
    }

    public String getTrade_receiver_address() {
        return getStr(TRADE_RECEIVER_ADDRESS);
    }

    public void setTrade_receiver_address(String trade_receiver_address) {
        set(TRADE_RECEIVER_ADDRESS, trade_receiver_address);
    }

    public String getTrade_message() {
        return getStr(TRADE_MESSAGE);
    }

    public void setTrade_message(String trade_message) {
        set(TRADE_MESSAGE, trade_message);
    }

    public Integer getTrade_product_quantity() {
        return getInt(TRADE_PRODUCT_QUANTITY);
    }

    public void setTrade_product_quantity(Integer trade_product_quantity) {
        set(TRADE_PRODUCT_QUANTITY, trade_product_quantity);
    }

    public BigDecimal getTrade_product_amount() {
        return getBigDecimal(TRADE_PRODUCT_AMOUNT);
    }

    public void setTrade_product_amount(BigDecimal trade_product_amount) {
        set(TRADE_PRODUCT_AMOUNT, trade_product_amount);
    }

    public BigDecimal getTrade_express_amount() {
        return getBigDecimal(TRADE_EXPRESS_AMOUNT);
    }

    public void setTrade_express_amount(BigDecimal trade_express_amount) {
        set(TRADE_EXPRESS_AMOUNT, trade_express_amount);
    }

    public BigDecimal getTrade_discount_amount() {
        return getBigDecimal(TRADE_DISCOUNT_AMOUNT);
    }

    public void setTrade_discount_amount(BigDecimal trade_discount_amount) {
        set(TRADE_DISCOUNT_AMOUNT, trade_discount_amount);
    }

    public Boolean getTrade_is_commission() {
        return getBoolean(TRADE_IS_COMMISSION);
    }

    public void setTrade_is_commission(Boolean trade_is_commission) {
        set(TRADE_IS_COMMISSION, trade_is_commission);
    }

    public Boolean getTrade_is_confirm() {
        return getBoolean(TRADE_IS_CONFIRM);
    }

    public void setTrade_is_confirm(Boolean trade_is_confirm) {
        set(TRADE_IS_CONFIRM, trade_is_confirm);
    }

    public Boolean getTrade_is_pay() {
        return getBoolean(TRADE_IS_PAY);
    }

    public void setTrade_is_pay(Boolean trade_is_pay) {
        set(TRADE_IS_PAY, trade_is_pay);
    }

    public String getTrade_flow() {
        return getStr(TRADE_FLOW);
    }

    public void setTrade_flow(String trade_flow) {
        set(TRADE_FLOW, trade_flow);
    }

    public String getTrade_status() {
        return getStr(TRADE_STATUS);
    }

    public void setTrade_status(String trade_status) {
        set(TRADE_STATUS, trade_status);
    }

    public String getTrade_audit_status() {
        return getStr(TRADE_AUDIT_STATUS);
    }

    public void setTrade_audit_status(String trade_audit_status) {
        set(TRADE_AUDIT_STATUS, trade_audit_status);
    }

}