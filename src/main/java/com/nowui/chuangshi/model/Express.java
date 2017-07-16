package com.nowui.chuangshi.model;

import java.math.BigDecimal;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class Express extends Model<Express> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String EXPRESS_ID = "express_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用id")
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "库存出库id")
    public static final String STOCK_ID = "stock_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "收货用户id")
    public static final String EXPRESS_RECEIVER_USER_ID = "express_receiver_user_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "发货用户id")
    public static final String EXPRESS_SENDER_USER_ID = "express_sender_user_id";

    @Column(type = ColumnType.VARCHAR, length = 50, comment = "快递公司编码")
    public static final String EXPRESS_SHIPPER_CODE = "express_shipper_code";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "快递单号")
    public static final String EXPRESS_NO = "express_no";

    @Column(type = ColumnType.VARCHAR, length = 25, comment = "快递类型")
    public static final String EXPRESS_TYPE = "express_type";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "收货公司")
    public static final String EXPRESS_RECEIVER_COMPANY = "express_receiver_company";

    @Column(type = ColumnType.VARCHAR, length = 50, comment = "收货人")
    public static final String EXPRESS_RECEIVER_NAME = "express_receiver_name";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "收货人电话")
    public static final String EXPRESS_RECEIVER_TEL = "express_receiver_tel";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "收货人手机")
    public static final String EXPRESS_RECEIVER_MOBILE = "express_receiver_mobile";

    @Column(type = ColumnType.VARCHAR, length = 50, comment = "收货人邮编")
    public static final String EXPRESS_RECEIVER_POSTCODE = "express_receiver_postcode";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "收货人省")
    public static final String EXPRESS_RECEIVER_PROVINCE = "express_receiver_province";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "收货人市")
    public static final String EXPRESS_RECEIVER_CITY = "express_receiver_city";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "收货人区")
    public static final String EXPRESS_RECEIVER_AREA = "express_receiver_area";

    @Column(type = ColumnType.VARCHAR, length = 200, comment = "收货详细地址")
    public static final String EXPRESS_RECEIVER_ADDRESS = "express_receiver_address";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "发货人公司")
    public static final String EXPRESS_SENDER_COMPANY = "express_sender_company";

    @Column(type = ColumnType.VARCHAR, length = 50, comment = "发货人")
    public static final String EXPRESS_SENDER_NAME = "express_sender_name";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "发货人电话")
    public static final String EXPRESS_SENDER_TEL = "express_sender_tel";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "发货人手机")
    public static final String EXPRESS_SENDER_MOBILE = "express_sender_mobile";

    @Column(type = ColumnType.VARCHAR, length = 50, comment = "发货人邮编")
    public static final String EXPRESS_SENDER_POSTCODE = "express_sender_postcode";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "发货人省")
    public static final String EXPRESS_SENDER_PROVINCE = "express_sender_province";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "发货人市")
    public static final String EXPRESS_SENDER_CITY = "express_sender_city";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "发货人区")
    public static final String EXPRESS_SENDER_AREA = "express_sender_area";

    @Column(type = ColumnType.VARCHAR, length = 200, comment = "发货人详细地址")
    public static final String EXPRESS_SENDER_ADDRESS = "express_sender_address";

    @Column(type = ColumnType.DECIMAL, length = 0, comment = "寄件费（运费）")
    public static final String EXPRESS_COST = "express_cost";

    @Column(type = ColumnType.TINYINT, length = 1, comment = "运费是否支付")
    public static final String EXPRESS_IS_PAY = "express_is_pay";

    @Column(type = ColumnType.VARCHAR, length = 50, comment = "运费支付方式")
    public static final String EXPRESS_PAY_WAY = "express_pay_way";

    @Column(type = ColumnType.DATETIME, length = 0, comment = "快递发货时间")
    public static final String EXPRESS_START_DATE = "express_start_date";

    @Column(type = ColumnType.DATETIME, length = 0, comment = "快递取货时间")
    public static final String EXPRESS_END_DATE = "express_end_date";

    @Column(type = ColumnType.LONGTEXT, length = 429496729, comment = "物流信息")
    public static final String EXPRESS_LOGISTICS = "express_logistics";

    @Column(type = ColumnType.VARCHAR, length = 25, comment = "物流流程")
    public static final String EXPRESS_FLOW = "express_flow";
    
    @Column(type = ColumnType.TINYINT, length = 1, comment = "")
    public static final String EXPRESS_STATUS = "express_status";

    @Column(type = ColumnType.VARCHAR, length = 200, comment = "备注")
    public static final String EXPRESS_REMARK = "express_remark";

    public String getExpress_id() {
        return getStr(EXPRESS_ID);
    }

    public void setExpress_id(String express_id) {
        set(EXPRESS_ID, express_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getStock_id() {
        return getStr(STOCK_ID);
    }

    public void setStock_id(String stock_id) {
        set(STOCK_ID, stock_id);
    }

    public String getExpress_receiver_user_id() {
        return getStr(EXPRESS_RECEIVER_USER_ID);
    }

    public void setExpress_receiver_user_id(String express_receiver_user_id) {
        set(EXPRESS_RECEIVER_USER_ID, express_receiver_user_id);
    }

    public String getExpress_sender_user_id() {
        return getStr(EXPRESS_SENDER_USER_ID);
    }

    public void setExpress_sender_user_id(String express_sender_user_id) {
        set(EXPRESS_SENDER_USER_ID, express_sender_user_id);
    }

    public String getExpress_shipper_code() {
        return getStr(EXPRESS_SHIPPER_CODE);
    }

    public void setExpress_shipper_code(String express_shipper_code) {
        set(EXPRESS_SHIPPER_CODE, express_shipper_code);
    }

    public String getExpress_no() {
        return getStr(EXPRESS_NO);
    }

    public void setExpress_no(String express_no) {
        set(EXPRESS_NO, express_no);
    }

    public String getExpress_type() {
        return getStr(EXPRESS_TYPE);
    }

    public void setExpress_type(String express_type) {
        set(EXPRESS_TYPE, express_type);
    }

    public String getExpress_receiver_company() {
        return getStr(EXPRESS_RECEIVER_COMPANY);
    }

    public void setExpress_receiver_company(String express_receiver_company) {
        set(EXPRESS_RECEIVER_COMPANY, express_receiver_company);
    }

    public String getExpress_receiver_name() {
        return getStr(EXPRESS_RECEIVER_NAME);
    }

    public void setExpress_receiver_name(String express_receiver_name) {
        set(EXPRESS_RECEIVER_NAME, express_receiver_name);
    }

    public String getExpress_receiver_tel() {
        return getStr(EXPRESS_RECEIVER_TEL);
    }

    public void setExpress_receiver_tel(String express_receiver_tel) {
        set(EXPRESS_RECEIVER_TEL, express_receiver_tel);
    }

    public String getExpress_receiver_mobile() {
        return getStr(EXPRESS_RECEIVER_MOBILE);
    }

    public void setExpress_receiver_mobile(String express_receiver_mobile) {
        set(EXPRESS_RECEIVER_MOBILE, express_receiver_mobile);
    }

    public String getExpress_receiver_postcode() {
        return getStr(EXPRESS_RECEIVER_POSTCODE);
    }

    public void setExpress_receiver_postcode(String express_receiver_postcode) {
        set(EXPRESS_RECEIVER_POSTCODE, express_receiver_postcode);
    }

    public String getExpress_receiver_province() {
        return getStr(EXPRESS_RECEIVER_PROVINCE);
    }

    public void setExpress_receiver_province(String express_receiver_province) {
        set(EXPRESS_RECEIVER_PROVINCE, express_receiver_province);
    }

    public String getExpress_receiver_city() {
        return getStr(EXPRESS_RECEIVER_CITY);
    }

    public void setExpress_receiver_city(String express_receiver_city) {
        set(EXPRESS_RECEIVER_CITY, express_receiver_city);
    }

    public String getExpress_receiver_area() {
        return getStr(EXPRESS_RECEIVER_AREA);
    }

    public void setExpress_receiver_area(String express_receiver_area) {
        set(EXPRESS_RECEIVER_AREA, express_receiver_area);
    }

    public String getExpress_receiver_address() {
        return getStr(EXPRESS_RECEIVER_ADDRESS);
    }

    public void setExpress_receiver_address(String express_receiver_address) {
        set(EXPRESS_RECEIVER_ADDRESS, express_receiver_address);
    }

    public String getExpress_sender_company() {
        return getStr(EXPRESS_SENDER_COMPANY);
    }

    public void setExpress_sender_company(String express_sender_company) {
        set(EXPRESS_SENDER_COMPANY, express_sender_company);
    }

    public String getExpress_sender_name() {
        return getStr(EXPRESS_SENDER_NAME);
    }

    public void setExpress_sender_name(String express_sender_name) {
        set(EXPRESS_SENDER_NAME, express_sender_name);
    }

    public String getExpress_sender_tel() {
        return getStr(EXPRESS_SENDER_TEL);
    }

    public void setExpress_sender_tel(String express_sender_tel) {
        set(EXPRESS_SENDER_TEL, express_sender_tel);
    }

    public String getExpress_sender_mobile() {
        return getStr(EXPRESS_SENDER_MOBILE);
    }

    public void setExpress_sender_mobile(String express_sender_mobile) {
        set(EXPRESS_SENDER_MOBILE, express_sender_mobile);
    }

    public String getExpress_sender_postcode() {
        return getStr(EXPRESS_SENDER_POSTCODE);
    }

    public void setExpress_sender_postcode(String express_sender_postcode) {
        set(EXPRESS_SENDER_POSTCODE, express_sender_postcode);
    }

    public String getExpress_sender_province() {
        return getStr(EXPRESS_SENDER_PROVINCE);
    }

    public void setExpress_sender_province(String express_sender_province) {
        set(EXPRESS_SENDER_PROVINCE, express_sender_province);
    }

    public String getExpress_sender_city() {
        return getStr(EXPRESS_SENDER_CITY);
    }

    public void setExpress_sender_city(String express_sender_city) {
        set(EXPRESS_SENDER_CITY, express_sender_city);
    }

    public String getExpress_sender_area() {
        return getStr(EXPRESS_SENDER_AREA);
    }

    public void setExpress_sender_area(String express_sender_area) {
        set(EXPRESS_SENDER_AREA, express_sender_area);
    }

    public String getExpress_sender_address() {
        return getStr(EXPRESS_SENDER_ADDRESS);
    }

    public void setExpress_sender_address(String express_sender_address) {
        set(EXPRESS_SENDER_ADDRESS, express_sender_address);
    }

    public BigDecimal getExpress_cost() {
        return getBigDecimal(EXPRESS_COST);
    }

    public void setExpress_cost(BigDecimal express_cost) {
        set(EXPRESS_COST, express_cost);
    }

    public Boolean getExpress_is_pay() {
        return getBoolean(EXPRESS_IS_PAY);
    }

    public void setExpress_is_pay(Boolean express_is_pay) {
        set(EXPRESS_IS_PAY, express_is_pay);
    }

    public String getExpress_pay_way() {
        return getStr(EXPRESS_PAY_WAY);
    }

    public void setExpress_pay_way(String express_pay_way) {
        set(EXPRESS_PAY_WAY, express_pay_way);
    }

    public String getExpress_start_date() {
        return getStr(EXPRESS_START_DATE);
    }

    public void setExpress_start_date(String express_start_date) {
        set(EXPRESS_START_DATE, express_start_date);
    }

    public String getExpress_end_date() {
        return getStr(EXPRESS_END_DATE);
    }

    public void setExpress_end_date(String express_end_date) {
        set(EXPRESS_END_DATE, express_end_date);
    }

    public String getExpress_logistics() {
        return getStr(EXPRESS_LOGISTICS);
    }

    public void setExpress_logistics(String express_logistics) {
        set(EXPRESS_LOGISTICS, express_logistics);
    }

    public String getExpress_flow() {
        return getStr(EXPRESS_FLOW);
    }

    public void setExpress_flow(String express_flow) {
        set(EXPRESS_FLOW, express_flow);
    }
    
    public Boolean getExpress_status() {
    	return getBoolean(EXPRESS_STATUS);
    }
    
    public void setExpress_status(Boolean express_status) {
    	set(EXPRESS_STATUS, express_status);
    }

    public String getExpress_remark() {
        return getStr(EXPRESS_REMARK);
    }

    public void setExpress_remark(String express_remark) {
        set(EXPRESS_REMARK, express_remark);
    }

}