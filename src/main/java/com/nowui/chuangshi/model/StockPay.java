package com.nowui.chuangshi.model;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class StockPay extends Model<StockPay> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String STOCK_ID = "stock_id";

    @Column(type = ColumnType.VARCHAR, length = 10, comment = "")
    public static final String STOCK_PAY_TYPE = "stock_pay_type";

    @Column(type = ColumnType.VARCHAR, length = 50, comment = "")
    public static final String STOCK_PAY_NUMBER = "stock_pay_number";

    @Column(type = ColumnType.VARCHAR, length = 50, comment = "")
    public static final String STOCK_PAY_ACCOUNT = "stock_pay_account";

    @Column(type = ColumnType.VARCHAR, length = 19, comment = "")
    public static final String STOCK_PAY_TIME = "stock_pay_time";

    @Column(type = ColumnType.VARCHAR, length = 1000, comment = "")
    public static final String STOCK_PAY_RESULT = "stock_pay_result";

    public String getStock_id() {
        return getStr(STOCK_ID);
    }

    public void setStock_id(String stock_id) {
        set(STOCK_ID, stock_id);
    }

    public String getStock_pay_type() {
        return getStr(STOCK_PAY_TYPE);
    }

    public void setStock_pay_type(String stock_pay_type) {
        set(STOCK_PAY_TYPE, stock_pay_type);
    }

    public String getStock_pay_number() {
        return getStr(STOCK_PAY_NUMBER);
    }

    public void setStock_pay_number(String stock_pay_number) {
        set(STOCK_PAY_NUMBER, stock_pay_number);
    }

    public String getStock_pay_account() {
        return getStr(STOCK_PAY_ACCOUNT);
    }

    public void setStock_pay_account(String stock_pay_account) {
        set(STOCK_PAY_ACCOUNT, stock_pay_account);
    }

    public String getStock_pay_time() {
        return getStr(STOCK_PAY_TIME);
    }

    public void setStock_pay_time(String stock_pay_time) {
        set(STOCK_PAY_TIME, stock_pay_time);
    }

    public String getStock_pay_result() {
        return getStr(STOCK_PAY_RESULT);
    }

    public void setStock_pay_result(String stock_pay_result) {
        set(STOCK_PAY_RESULT, stock_pay_result);
    }

}