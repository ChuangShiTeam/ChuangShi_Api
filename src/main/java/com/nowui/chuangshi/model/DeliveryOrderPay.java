package com.nowui.chuangshi.model;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class DeliveryOrderPay extends Model<DeliveryOrderPay> {

    @Column(type = ColumnType.VARCHAR, length = 255, comment = "")
    public static final String DELIVERY_ORDER_ID = "delivery_order_id";

    @Column(type = ColumnType.VARCHAR, length = 10, comment = "")
    public static final String DELIVERY_ORDER_PAY_TYPE = "delivery_order_pay_type";

    @Column(type = ColumnType.VARCHAR, length = 50, comment = "")
    public static final String DELIVERY_ORDER_PAY_NUMBER = "delivery_order_pay_number";

    @Column(type = ColumnType.VARCHAR, length = 50, comment = "")
    public static final String DELIVERY_ORDER_PAY_ACCOUNT = "delivery_order_pay_account";

    @Column(type = ColumnType.VARCHAR, length = 19, comment = "")
    public static final String DELIVERY_ORDER_PAY_TIME = "delivery_order_pay_time";

    @Column(type = ColumnType.VARCHAR, length = 1000, comment = "")
    public static final String DELIVERY_ORDER_PAY_RESULT = "delivery_order_pay_result";

    public String getDelivery_order_id() {
        return getStr(DELIVERY_ORDER_ID);
    }

    public void setDelivery_order_id(String delivery_order_id) {
        set(DELIVERY_ORDER_ID, delivery_order_id);
    }

    public String getDelivery_order_pay_type() {
        return getStr(DELIVERY_ORDER_PAY_TYPE);
    }

    public void setDelivery_order_pay_type(String delivery_order_pay_type) {
        set(DELIVERY_ORDER_PAY_TYPE, delivery_order_pay_type);
    }

    public String getDelivery_order_pay_number() {
        return getStr(DELIVERY_ORDER_PAY_NUMBER);
    }

    public void setDelivery_order_pay_number(String delivery_order_pay_number) {
        set(DELIVERY_ORDER_PAY_NUMBER, delivery_order_pay_number);
    }

    public String getDelivery_order_pay_account() {
        return getStr(DELIVERY_ORDER_PAY_ACCOUNT);
    }

    public void setDelivery_order_pay_account(String delivery_order_pay_account) {
        set(DELIVERY_ORDER_PAY_ACCOUNT, delivery_order_pay_account);
    }

    public String getDelivery_order_pay_time() {
        return getStr(DELIVERY_ORDER_PAY_TIME);
    }

    public void setDelivery_order_pay_time(String delivery_order_pay_time) {
        set(DELIVERY_ORDER_PAY_TIME, delivery_order_pay_time);
    }

    public String getDelivery_order_pay_result() {
        return getStr(DELIVERY_ORDER_PAY_RESULT);
    }

    public void setDelivery_order_pay_result(String delivery_order_pay_result) {
        set(DELIVERY_ORDER_PAY_RESULT, delivery_order_pay_result);
    }

}