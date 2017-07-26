package com.nowui.chuangshi.model;

import java.math.BigDecimal;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class DeliveryOrder extends Model<DeliveryOrder> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String DELIVERY_ORDER_ID = "delivery_order_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用id")
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "订单id")
    public static final String TRADE_ID = "trade_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "用户id")
    public static final String DELIVERY_ORDER_USER_ID = "delivery_order_user_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "发货人id")
    public static final String DELIVERY_ORDER_SENDER_USER_ID = "delivery_order_sender_user_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "收货人id")
    public static final String DELIVERY_ORDER_RECIEVER_USER_ID = "delivery_order_reciever_user_id";
    
    @Column(type = ColumnType.DECIMAL, length = 0, comment = "发货金额")
    public static final String DELIVERY_ORDER_AMOUNT = "delivery_order_amount";
    
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "发货数量")
    public static final String DELIVERY_ORDER_TOTAL_QUANTITY = "delivery_order_total_quantity";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "收货人")
    public static final String DELIVERY_ORDER_RECEIVER_NAME = "delivery_order_receiver_name";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "收货人手机号码")
    public static final String DELIVERY_ORDER_RECEIVER_MOBILE = "delivery_order_receiver_mobile";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "收货人省")
    public static final String DELIVERY_ORDER_RECEIVER_PROVINCE = "delivery_order_receiver_province";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "收货人市")
    public static final String DELIVERY_ORDER_RECEIVER_CITY = "delivery_order_receiver_city";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "收货人区")
    public static final String DELIVERY_ORDER_RECEIVER_AREA = "delivery_order_receiver_area";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "收货人地址")
    public static final String DELIVERY_ORDER_RECEIVER_ADDRESS = "delivery_order_receiver_address";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "快递支付方式")
    public static final String DELIVERY_ORDER_EXPRESS_PAY_WAY = "delivery_order_express_pay_way";

    @Column(type = ColumnType.VARCHAR, length = 10, comment = "快递公司编码")
    public static final String DELIVERY_ORDER_EXPRESS_SHIPPER_CODE = "delivery_order_express_shipper_code";

    @Column(type = ColumnType.TINYINT, length = 1, comment = "")
    public static final String DELIVERY_ORDER_IS_PAY = "delivery_order_is_pay";

    @Column(type = ColumnType.VARCHAR, length = 25, comment = "发货流程")
    public static final String DELIVERY_ORDER_FLOW = "delivery_order_flow";

    @Column(type = ColumnType.TINYINT, length = 1, comment = "")
    public static final String DELIVERY_IS_COMPLETE = "delivery_is_complete";
    
    public static final String DELIVERY_ORDER_PRODUCT_SKU_LIST = "delivery_order_product_sku_list";
    
    public static final String USER_NAME = "user_name";
    
    private String user_name;

    public String getDelivery_order_id() {
        return getStr(DELIVERY_ORDER_ID);
    }

    public void setDelivery_order_id(String delivery_order_id) {
        set(DELIVERY_ORDER_ID, delivery_order_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getTrade_id() {
        return getStr(TRADE_ID);
    }

    public void setTrade_id(String trade_id) {
        set(TRADE_ID, trade_id);
    }

    public String getDelivery_order_user_id() {
        return getStr(DELIVERY_ORDER_USER_ID);
    }

    public void setDelivery_order_user_id(String delivery_order_user_id) {
        set(DELIVERY_ORDER_USER_ID, delivery_order_user_id);
    }

    public String getDelivery_order_sender_user_id() {
        return getStr(DELIVERY_ORDER_SENDER_USER_ID);
    }

    public void setDelivery_order_sender_user_id(String delivery_order_sender_user_id) {
        set(DELIVERY_ORDER_SENDER_USER_ID, delivery_order_sender_user_id);
    }

    public String getDelivery_order_reciever_user_id() {
        return getStr(DELIVERY_ORDER_RECIEVER_USER_ID);
    }
    
    public BigDecimal getDelivery_order_amount() {
        return getBigDecimal(DELIVERY_ORDER_AMOUNT);
    }

    public void setDelivery_order_amount(BigDecimal delivery_order_amount) {
        set(DELIVERY_ORDER_AMOUNT, delivery_order_amount);
    }

    public void setDelivery_order_reciever_user_id(String delivery_order_reciever_user_id) {
        set(DELIVERY_ORDER_RECIEVER_USER_ID, delivery_order_reciever_user_id);
    }
    
    public Integer getDelivery_order_total_quantity() {
        return getInt(DELIVERY_ORDER_TOTAL_QUANTITY);
    }
    
    public void setDelivery_order_total_quantity(Integer delivery_order_total_quantity) {
        set(DELIVERY_ORDER_TOTAL_QUANTITY, delivery_order_total_quantity);
    }

    public String getDelivery_order_receiver_name() {
        return getStr(DELIVERY_ORDER_RECEIVER_NAME);
    }

    public void setDelivery_order_receiver_name(String delivery_order_receiver_name) {
        set(DELIVERY_ORDER_RECEIVER_NAME, delivery_order_receiver_name);
    }

    public String getDelivery_order_receiver_mobile() {
        return getStr(DELIVERY_ORDER_RECEIVER_MOBILE);
    }

    public void setDelivery_order_receiver_mobile(String delivery_order_receiver_mobile) {
        set(DELIVERY_ORDER_RECEIVER_MOBILE, delivery_order_receiver_mobile);
    }

    public String getDelivery_order_receiver_province() {
        return getStr(DELIVERY_ORDER_RECEIVER_PROVINCE);
    }

    public void setDelivery_order_receiver_province(String delivery_order_receiver_province) {
        set(DELIVERY_ORDER_RECEIVER_PROVINCE, delivery_order_receiver_province);
    }

    public String getDelivery_order_receiver_city() {
        return getStr(DELIVERY_ORDER_RECEIVER_CITY);
    }

    public void setDelivery_order_receiver_city(String delivery_order_receiver_city) {
        set(DELIVERY_ORDER_RECEIVER_CITY, delivery_order_receiver_city);
    }

    public String getDelivery_order_receiver_area() {
        return getStr(DELIVERY_ORDER_RECEIVER_AREA);
    }

    public void setDelivery_order_receiver_area(String delivery_order_receiver_area) {
        set(DELIVERY_ORDER_RECEIVER_AREA, delivery_order_receiver_area);
    }

    public String getDelivery_order_receiver_address() {
        return getStr(DELIVERY_ORDER_RECEIVER_ADDRESS);
    }

    public void setDelivery_order_receiver_address(String delivery_order_receiver_address) {
        set(DELIVERY_ORDER_RECEIVER_ADDRESS, delivery_order_receiver_address);
    }

    public String getDelivery_order_express_pay_way() {
        return getStr(DELIVERY_ORDER_EXPRESS_PAY_WAY);
    }

    public void setDelivery_order_express_pay_way(String delivery_order_express_pay_way) {
        set(DELIVERY_ORDER_EXPRESS_PAY_WAY, delivery_order_express_pay_way);
    }

    public String getDelivery_order_express_shipper_code() {
        return getStr(DELIVERY_ORDER_EXPRESS_SHIPPER_CODE);
    }

    public void setDelivery_order_express_shipper_code(String delivery_order_express_shipper_code) {
        set(DELIVERY_ORDER_EXPRESS_SHIPPER_CODE, delivery_order_express_shipper_code);
    }

    public Boolean getDelivery_order_is_pay() {
        return getBoolean(DELIVERY_ORDER_IS_PAY);
    }

    public void setDelivery_order_is_pay(Boolean delivery_order_is_pay) {
        set(DELIVERY_ORDER_IS_PAY, delivery_order_is_pay);
    }

    public String getDelivery_order_flow() {
        return getStr(DELIVERY_ORDER_FLOW);
    }

    public void setDelivery_order_flow(String delivery_order_flow) {
        set(DELIVERY_ORDER_FLOW, delivery_order_flow);
    }

    public Boolean getDelivery_is_complete() {
        return getBoolean(DELIVERY_IS_COMPLETE);
    }

    public void setDelivery_is_complete(Boolean delivery_is_complete) {
        set(DELIVERY_IS_COMPLETE, delivery_is_complete);
    }
    
    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

}