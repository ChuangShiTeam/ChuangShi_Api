package com.nowui.chuangshi.type;

public enum ExpressBelong {
        
    MEMBER_PURCHASE_ORDER("MEMBER_PURCHASE_ORDER", "会员进货单"),
    MEMBER_DELIVERY_ORDER("MEMBER_DELIVERY_ORDER", "会员发货单"),
    TRADE("TRADE", "订单");

    private String key;
    private String value;

    private ExpressBelong(String key, String value) {
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
