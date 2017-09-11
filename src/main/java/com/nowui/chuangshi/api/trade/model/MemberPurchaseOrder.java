package com.nowui.chuangshi.api.trade.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Entity;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.math.BigDecimal;
import java.util.Date;

@Entity
public class MemberPurchaseOrder extends Model<MemberPurchaseOrder> {

    @Table
    public static final String TABLE_MEMBER_PURCHASE_ORDER = "table_member_purchase_order";

    @Primary
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "", updatable = false)
    public static final String MEMBER_PURCHASE_ORDER_ID = "member_purchase_order_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用id", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "用户id")
    public static final String USER_ID = "user_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "会员等级id")
    public static final String MEMBER_LEVEL_ID = "member_level_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String MEMBER_DELIVER_USER_ID = "member_deliver_user_id";

    @Column(type = ColumnType.VARCHAR, length = 15, comment = "")
    public static final String MEMBER_PURCHASE_ORDER_NUMBER = "member_purchase_order_number";

    @Column(type = ColumnType.DECIMAL, length = 0, comment = "商品总金额")
    public static final String MEMBER_PURCHASE_ORDER_PRODUCT_AMOUNT = "member_purchase_order_product_amount";

    @Column(type = ColumnType.DECIMAL, length = 0, comment = "快递费")
    public static final String MEMBER_PURCHASE_ORDER_EXPRESS_AMOUNT = "member_purchase_order_express_amount";

    @Column(type = ColumnType.DECIMAL, length = 0, comment = "折扣金额")
    public static final String MEMBER_PURCHASE_ORDER_DISCOUNT_AMOUNT = "member_purchase_order_discount_amount";

    @Column(type = ColumnType.DECIMAL, length = 0, comment = "进货总金额")
    public static final String MEMBER_PURCHASE_ORDER_AMOUNT = "member_purchase_order_amount";

    @Column(type = ColumnType.INT, length = 11, comment = "进货总数量")
    public static final String MEMBER_PURCHASE_ORDER_TOTAL_QUANTITY = "member_purchase_order_total_quantity";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "收货人")
    public static final String MEMBER_PURCHASE_ORDER_RECEIVER_NAME = "member_purchase_order_receiver_name";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "收货人手机号码")
    public static final String MEMBER_PURCHASE_ORDER_RECEIVER_MOBILE = "member_purchase_order_receiver_mobile";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "收货人省")
    public static final String MEMBER_PURCHASE_ORDER_RECEIVER_PROVINCE = "member_purchase_order_receiver_province";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "收货人市")
    public static final String MEMBER_PURCHASE_ORDER_RECEIVER_CITY = "member_purchase_order_receiver_city";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "进货人区")
    public static final String MEMBER_PURCHASE_ORDER_RECEIVER_AREA = "member_purchase_order_receiver_area";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "进货人地址")
    public static final String MEMBER_PURCHASE_ORDER_RECEIVER_ADDRESS = "member_purchase_order_receiver_address";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "快递支付方式")
    public static final String MEMBER_PURCHASE_ORDER_EXPRESS_PAY_WAY = "member_purchase_order_express_pay_way";

    @Column(type = ColumnType.VARCHAR, length = 10, comment = "快递公司编码")
    public static final String MEMBER_PURCHASE_ORDER_EXPRESS_SHIPPER_CODE = "member_purchase_order_express_shipper_code";

    @Column(type = ColumnType.TINYINT, length = 1, comment = "是否仓库代收")
    public static final String MEMBER_PURCHASE_ORDER_IS_WAREHOUSE_RECEIVE = "member_purchase_order_is_warehouse_receive";

    @Column(type = ColumnType.TINYINT, length = 1, comment = "是否支付")
    public static final String MEMBER_PURCHASE_ORDER_IS_PAY = "member_purchase_order_is_pay";

    @Column(type = ColumnType.VARCHAR, length = 25, comment = "进货流程")
    public static final String MEMBER_PURCHASE_ORDER_FLOW = "member_purchase_order_flow";

    @Column(type = ColumnType.TINYINT, length = 1, comment = "是否完成")
    public static final String MEMBER_PURCHASE_ORDER_IS_COMPLETE = "member_purchase_order_is_complete";

    @Column(type = ColumnType.VARCHAR, length = 200, comment = "进货留言")
    public static final String MEMBER_PURCHASE_ORDER_MESSAGE = "member_purchase_order_message";

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

    public static final String MEMBER_PURCHASE_ORDER_PRODUCT_SKU_LIST = "member_purchase_order_product_sku_list";

    public String getMember_purchase_order_id() {
        return getStr(MEMBER_PURCHASE_ORDER_ID);
    }

    public void setMember_purchase_order_id(String member_purchase_order_id) {
        set(MEMBER_PURCHASE_ORDER_ID, member_purchase_order_id);
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

    public String getMember_level_id() {
        return getStr(MEMBER_LEVEL_ID);
    }

    public void setMember_level_id(String member_level_id) {
        set(MEMBER_LEVEL_ID, member_level_id);
    }

    public String getMember_deliver_user_id() {
        return getStr(MEMBER_DELIVER_USER_ID);
    }

    public void setMember_deliver_user_id(String member_deliver_user_id) {
        set(MEMBER_DELIVER_USER_ID, member_deliver_user_id);
    }

    public String getMember_purchase_order_number() {
        return getStr(MEMBER_PURCHASE_ORDER_NUMBER);
    }

    public void setMember_purchase_order_number(String member_purchase_order_number) {
        set(MEMBER_PURCHASE_ORDER_NUMBER, member_purchase_order_number);
    }

    public BigDecimal getMember_purchase_order_product_amount() {
        return getBigDecimal(MEMBER_PURCHASE_ORDER_PRODUCT_AMOUNT);
    }

    public void setMember_purchase_order_product_amount(BigDecimal member_purchase_order_product_amount) {
        set(MEMBER_PURCHASE_ORDER_PRODUCT_AMOUNT, member_purchase_order_product_amount);
    }

    public BigDecimal getMember_purchase_order_express_amount() {
        return getBigDecimal(MEMBER_PURCHASE_ORDER_EXPRESS_AMOUNT);
    }

    public void setMember_purchase_order_express_amount(BigDecimal member_purchase_order_express_amount) {
        set(MEMBER_PURCHASE_ORDER_EXPRESS_AMOUNT, member_purchase_order_express_amount);
    }

    public BigDecimal getMember_purchase_order_discount_amount() {
        return getBigDecimal(MEMBER_PURCHASE_ORDER_DISCOUNT_AMOUNT);
    }

    public void setMember_purchase_order_discount_amount(BigDecimal member_purchase_order_discount_amount) {
        set(MEMBER_PURCHASE_ORDER_DISCOUNT_AMOUNT, member_purchase_order_discount_amount);
    }

    public BigDecimal getMember_purchase_order_amount() {
        return getBigDecimal(MEMBER_PURCHASE_ORDER_AMOUNT);
    }

    public void setMember_purchase_order_amount(BigDecimal member_purchase_order_amount) {
        set(MEMBER_PURCHASE_ORDER_AMOUNT, member_purchase_order_amount);
    }

    public Integer getMember_purchase_order_total_quantity() {
        return getInt(MEMBER_PURCHASE_ORDER_TOTAL_QUANTITY);
    }

    public void setMember_purchase_order_total_quantity(Integer member_purchase_order_total_quantity) {
        set(MEMBER_PURCHASE_ORDER_TOTAL_QUANTITY, member_purchase_order_total_quantity);
    }

    public String getMember_purchase_order_receiver_name() {
        return getStr(MEMBER_PURCHASE_ORDER_RECEIVER_NAME);
    }

    public void setMember_purchase_order_receiver_name(String member_purchase_order_receiver_name) {
        set(MEMBER_PURCHASE_ORDER_RECEIVER_NAME, member_purchase_order_receiver_name);
    }

    public String getMember_purchase_order_receiver_mobile() {
        return getStr(MEMBER_PURCHASE_ORDER_RECEIVER_MOBILE);
    }

    public void setMember_purchase_order_receiver_mobile(String member_purchase_order_receiver_mobile) {
        set(MEMBER_PURCHASE_ORDER_RECEIVER_MOBILE, member_purchase_order_receiver_mobile);
    }

    public String getMember_purchase_order_receiver_province() {
        return getStr(MEMBER_PURCHASE_ORDER_RECEIVER_PROVINCE);
    }

    public void setMember_purchase_order_receiver_province(String member_purchase_order_receiver_province) {
        set(MEMBER_PURCHASE_ORDER_RECEIVER_PROVINCE, member_purchase_order_receiver_province);
    }

    public String getMember_purchase_order_receiver_city() {
        return getStr(MEMBER_PURCHASE_ORDER_RECEIVER_CITY);
    }

    public void setMember_purchase_order_receiver_city(String member_purchase_order_receiver_city) {
        set(MEMBER_PURCHASE_ORDER_RECEIVER_CITY, member_purchase_order_receiver_city);
    }

    public String getMember_purchase_order_receiver_area() {
        return getStr(MEMBER_PURCHASE_ORDER_RECEIVER_AREA);
    }

    public void setMember_purchase_order_receiver_area(String member_purchase_order_receiver_area) {
        set(MEMBER_PURCHASE_ORDER_RECEIVER_AREA, member_purchase_order_receiver_area);
    }

    public String getMember_purchase_order_receiver_address() {
        return getStr(MEMBER_PURCHASE_ORDER_RECEIVER_ADDRESS);
    }

    public void setMember_purchase_order_receiver_address(String member_purchase_order_receiver_address) {
        set(MEMBER_PURCHASE_ORDER_RECEIVER_ADDRESS, member_purchase_order_receiver_address);
    }

    public String getMember_purchase_order_express_pay_way() {
        return getStr(MEMBER_PURCHASE_ORDER_EXPRESS_PAY_WAY);
    }

    public void setMember_purchase_order_express_pay_way(String member_purchase_order_express_pay_way) {
        set(MEMBER_PURCHASE_ORDER_EXPRESS_PAY_WAY, member_purchase_order_express_pay_way);
    }

    public String getMember_purchase_order_express_shipper_code() {
        return getStr(MEMBER_PURCHASE_ORDER_EXPRESS_SHIPPER_CODE);
    }

    public void setMember_purchase_order_express_shipper_code(String member_purchase_order_express_shipper_code) {
        set(MEMBER_PURCHASE_ORDER_EXPRESS_SHIPPER_CODE, member_purchase_order_express_shipper_code);
    }

    public Boolean getMember_purchase_order_is_warehouse_receive() {
        return getBoolean(MEMBER_PURCHASE_ORDER_IS_WAREHOUSE_RECEIVE);
    }

    public void setMember_purchase_order_is_warehouse_receive(Boolean member_purchase_order_is_warehouse_receive) {
        set(MEMBER_PURCHASE_ORDER_IS_WAREHOUSE_RECEIVE, member_purchase_order_is_warehouse_receive);
    }

    public Boolean getMember_purchase_order_is_pay() {
        return getBoolean(MEMBER_PURCHASE_ORDER_IS_PAY);
    }

    public void setMember_purchase_order_is_pay(Boolean member_purchase_order_is_pay) {
        set(MEMBER_PURCHASE_ORDER_IS_PAY, member_purchase_order_is_pay);
    }

    public String getMember_purchase_order_flow() {
        return getStr(MEMBER_PURCHASE_ORDER_FLOW);
    }

    public void setMember_purchase_order_flow(String member_purchase_order_flow) {
        set(MEMBER_PURCHASE_ORDER_FLOW, member_purchase_order_flow);
    }

    public Boolean getMember_purchase_order_is_complete() {
        return getBoolean(MEMBER_PURCHASE_ORDER_IS_COMPLETE);
    }

    public void setMember_purchase_order_is_complete(Boolean member_purchase_order_is_complete) {
        set(MEMBER_PURCHASE_ORDER_IS_COMPLETE, member_purchase_order_is_complete);
    }

    public String getMember_purchase_order_message() {
        return getStr(MEMBER_PURCHASE_ORDER_MESSAGE);
    }

    public void setMember_purchase_order_message(String member_purchase_order_message) {
        set(MEMBER_PURCHASE_ORDER_MESSAGE, member_purchase_order_message);
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