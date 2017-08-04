package com.nowui.chuangshi.api.bill.model;

import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.annotation.Primary;
import com.nowui.chuangshi.common.annotation.Table;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.type.ColumnType;

import java.math.BigDecimal;
import java.util.Date;

@Table("table_bill")
@Primary("bill_id")
public class Bill extends Model<Bill> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "账单编号", updatable = false)
    public static final String BILL_ID = "bill_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号", updatable = false)
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "用户编号")
    public static final String USER_ID = "user_id";

    @Column(type = ColumnType.VARCHAR, length = 10, comment = "账单类型")
    public static final String BILL_TYPE = "bill_type";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "账单图片")
    public static final String BILL_IMAGE = "bill_image";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "账单名称")
    public static final String BILL_NAME = "bill_name";

    @Column(type = ColumnType.DECIMAL, length = 0, comment = "账单金额")
    public static final String BILL_AMOUNT = "bill_amount";

    @Column(type = ColumnType.TINYINT, length = 1, comment = "是否收入")
    public static final String BILL_IS_INCOME = "bill_is_income";

    @Column(type = ColumnType.DATETIME, length = 0, comment = "账单时间")
    public static final String BILL_TIME = "bill_time";

    @Column(type = ColumnType.VARCHAR, length = 10, comment = "账单流程")
    public static final String BILL_FLOW = "bill_flow";

    @Column(type = ColumnType.TINYINT, length = 1, comment = "账单状态")
    public static final String BILL_STATUS = "bill_status";

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

    public String getBill_id() {
        return getStr(BILL_ID);
    }

    public void setBill_id(String bill_id) {
        set(BILL_ID, bill_id);
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

    public String getBill_type() {
        return getStr(BILL_TYPE);
    }

    public void setBill_type(String bill_type) {
        set(BILL_TYPE, bill_type);
    }

    public String getBill_image() {
        return getStr(BILL_IMAGE);
    }

    public void setBill_image(String bill_image) {
        set(BILL_IMAGE, bill_image);
    }

    public String getBill_name() {
        return getStr(BILL_NAME);
    }

    public void setBill_name(String bill_name) {
        set(BILL_NAME, bill_name);
    }

    public BigDecimal getBill_amount() {
        return getBigDecimal(BILL_AMOUNT);
    }

    public void setBill_amount(BigDecimal bill_amount) {
        set(BILL_AMOUNT, bill_amount);
    }

    public Boolean getBill_is_income() {
        return getBoolean(BILL_IS_INCOME);
    }

    public void setBill_is_income(Boolean bill_is_income) {
        set(BILL_IS_INCOME, bill_is_income);
    }

    public String getBill_time() {
        return getStr(BILL_TIME);
    }

    public void setBill_time(String bill_time) {
        set(BILL_TIME, bill_time);
    }

    public String getBill_flow() {
        return getStr(BILL_FLOW);
    }

    public void setBill_flow(String bill_flow) {
        set(BILL_FLOW, bill_flow);
    }

    public Boolean getBill_status() {
        return getBoolean(BILL_STATUS);
    }

    public void setBill_status(Boolean bill_status) {
        set(BILL_STATUS, bill_status);
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