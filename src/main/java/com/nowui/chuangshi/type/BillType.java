package com.nowui.chuangshi.type;

public enum BillType {

    TRADE("trade", "订单"),
    COMMISSION("commission", "分成"),
    EXPRESS("express", "快递单");


    private String key;
    private String value;

    private BillType(String key, String value) {
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
