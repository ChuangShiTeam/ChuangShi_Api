package com.nowui.chuangshi.type;

public enum TradeDeliveryPattern {
    CASH_BEFORE_DELIVERY("CASH_BEFORE_DELIVERY", "先付款后发货"),
    CASH_ON_DELIVERY("CASH_ON_DELIVERY", "货到付款");

    private String key;
    private String value;

    private TradeDeliveryPattern(String key, String value) {
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
