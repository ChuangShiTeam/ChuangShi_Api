package com.nowui.chuangshi.model;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class TradePay extends Model<TradePay> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String TRADE_ID = "trade_id";

    @Column(type = ColumnType.VARCHAR, length = 10, comment = "")
    public static final String TRADE_PAY_TYPE = "trade_pay_type";

    @Column(type = ColumnType.VARCHAR, length = 50, comment = "")
    public static final String TRADE_PAY_NUMBER = "trade_pay_number";

    @Column(type = ColumnType.VARCHAR, length = 50, comment = "")
    public static final String TRADE_PAY_ACCOUNT = "trade_pay_account";

    @Column(type = ColumnType.VARCHAR, length = 19, comment = "")
    public static final String TRADE_PAY_TIME = "trade_pay_time";

    @Column(type = ColumnType.VARCHAR, length = 1000, comment = "")
    public static final String TRADE_PAY_RESULT = "trade_pay_result";

    public String getTrade_id() {
        return getStr(TRADE_ID);
    }

    public void setTrade_id(String trade_id) {
        set(TRADE_ID, trade_id);
    }

    public String getTrade_pay_type() {
        return getStr(TRADE_PAY_TYPE);
    }

    public void setTrade_pay_type(String trade_pay_type) {
        set(TRADE_PAY_TYPE, trade_pay_type);
    }

    public String getTrade_pay_number() {
        return getStr(TRADE_PAY_NUMBER);
    }

    public void setTrade_pay_number(String trade_pay_number) {
        set(TRADE_PAY_NUMBER, trade_pay_number);
    }

    public String getTrade_pay_account() {
        return getStr(TRADE_PAY_ACCOUNT);
    }

    public void setTrade_pay_account(String trade_pay_account) {
        set(TRADE_PAY_ACCOUNT, trade_pay_account);
    }

    public String getTrade_pay_time() {
        return getStr(TRADE_PAY_TIME);
    }

    public void setTrade_pay_time(String trade_pay_time) {
        set(TRADE_PAY_TIME, trade_pay_time);
    }

    public String getTrade_pay_result() {
        return getStr(TRADE_PAY_RESULT);
    }

    public void setTrade_pay_result(String trade_pay_result) {
        set(TRADE_PAY_RESULT, trade_pay_result);
    }

}