package com.nowui.chuangshi.model;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class MemberDeliveryOrderPay extends Model<MemberDeliveryOrderPay> {

    @Column(type = ColumnType.VARCHAR, length = 255, comment = "")
    public static final String MEMBER_DELIVERY_ORDER_ID = "member_delivery_order_id";

    @Column(type = ColumnType.VARCHAR, length = 10, comment = "")
    public static final String MEMBER_DELIVERY_ORDER_PAY_TYPE = "member_delivery_order_pay_type";

    @Column(type = ColumnType.VARCHAR, length = 50, comment = "")
    public static final String MEMBER_DELIVERY_ORDER_PAY_NUMBER = "member_delivery_order_pay_number";

    @Column(type = ColumnType.VARCHAR, length = 50, comment = "")
    public static final String MEMBER_DELIVERY_ORDER_PAY_ACCOUNT = "member_delivery_order_pay_account";

    @Column(type = ColumnType.VARCHAR, length = 19, comment = "")
    public static final String MEMBER_DELIVERY_ORDER_PAY_TIME = "member_delivery_order_pay_time";

    @Column(type = ColumnType.VARCHAR, length = 1000, comment = "")
    public static final String MEMBER_DELIVERY_ORDER_PAY_RESULT = "member_delivery_order_pay_result";

    public String getMember_delivery_order_id() {
        return getStr(MEMBER_DELIVERY_ORDER_ID);
    }

    public void setMember_delivery_order_id(String member_delivery_order_id) {
        set(MEMBER_DELIVERY_ORDER_ID, member_delivery_order_id);
    }

    public String getMember_delivery_order_pay_type() {
        return getStr(MEMBER_DELIVERY_ORDER_PAY_TYPE);
    }

    public void setMember_delivery_order_pay_type(String member_delivery_order_pay_type) {
        set(MEMBER_DELIVERY_ORDER_PAY_TYPE, member_delivery_order_pay_type);
    }

    public String getMember_delivery_order_pay_number() {
        return getStr(MEMBER_DELIVERY_ORDER_PAY_NUMBER);
    }

    public void setMember_delivery_order_pay_number(String member_delivery_order_pay_number) {
        set(MEMBER_DELIVERY_ORDER_PAY_NUMBER, member_delivery_order_pay_number);
    }

    public String getMember_delivery_order_pay_account() {
        return getStr(MEMBER_DELIVERY_ORDER_PAY_ACCOUNT);
    }

    public void setMember_delivery_order_pay_account(String member_delivery_order_pay_account) {
        set(MEMBER_DELIVERY_ORDER_PAY_ACCOUNT, member_delivery_order_pay_account);
    }

    public String getMember_delivery_order_pay_time() {
        return getStr(MEMBER_DELIVERY_ORDER_PAY_TIME);
    }

    public void setMember_delivery_order_pay_time(String member_delivery_order_pay_time) {
        set(MEMBER_DELIVERY_ORDER_PAY_TIME, member_delivery_order_pay_time);
    }

    public String getMember_delivery_order_pay_result() {
        return getStr(MEMBER_DELIVERY_ORDER_PAY_RESULT);
    }

    public void setMember_delivery_order_pay_result(String member_delivery_order_pay_result) {
        set(MEMBER_DELIVERY_ORDER_PAY_RESULT, member_delivery_order_pay_result);
    }

}