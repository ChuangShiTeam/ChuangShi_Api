package com.nowui.chuangshi.model;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class TradeExpress extends Model<TradeExpress> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String TRADE_ID = "trade_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String EXPRESS_ID = "express_id";

    public String getTrade_id() {
        return getStr(TRADE_ID);
    }

    public void setTrade_id(String trade_id) {
        set(TRADE_ID, trade_id);
    }

    public String getExpress_id() {
        return getStr(EXPRESS_ID);
    }

    public void setExpress_id(String express_id) {
        set(EXPRESS_ID, express_id);
    }

}