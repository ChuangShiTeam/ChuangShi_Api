package com.nowui.chuangshi.model;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class GuangqiCustomerPrize extends Model<GuangqiCustomerPrize> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "客户奖品编号")
    public static final String CUSTOMER_PRIZE_ID = "customer_prize_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号")
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "客户编号")
    public static final String CUSTOMER_ID = "customer_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "奖品编号")
    public static final String PRIZE_ID = "prize_id";

    @Column(type = ColumnType.VARCHAR, length = 10, comment = "客户奖品日期")
    public static final String CUSTOMER_PRIZE_DATE = "customer_prize_date";

    public String getCustomer_prize_id() {
        return getStr(CUSTOMER_PRIZE_ID);
    }

    public void setCustomer_prize_id(String customer_prize_id) {
        set(CUSTOMER_PRIZE_ID, customer_prize_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getCustomer_id() {
        return getStr(CUSTOMER_ID);
    }

    public void setCustomer_id(String customer_id) {
        set(CUSTOMER_ID, customer_id);
    }

    public String getPrize_id() {
        return getStr(PRIZE_ID);
    }

    public void setPrize_id(String prize_id) {
        set(PRIZE_ID, prize_id);
    }

    public String getCustomer_prize_date() {
        return getStr(CUSTOMER_PRIZE_DATE);
    }

    public void setCustomer_prize_date(String customer_prize_date) {
        set(CUSTOMER_PRIZE_DATE, customer_prize_date);
    }

}