package com.nowui.chuangshi.type;

public enum TradeFlow {
    PAY("PAY", "待支付"),
    SEND("SEND", "待发货"),
    RECEIVE("RECEIVE", "待收货"),
    COMPLETE("COMPLETE", "已完成"),
    CANCEL("CANCEL", "已取消");

    private String key;
    private String value;

    private TradeFlow(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }


}
