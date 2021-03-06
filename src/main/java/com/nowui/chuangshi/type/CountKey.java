package com.nowui.chuangshi.type;

public enum CountKey {

    WAIT_PAY("WAIT_PAY", "待付款"),
    WAIT_SEND("WAIT_SEND", "待发货"),
    WAIT_RECEIVE("WAIT_RECEIVE", "待收货");

    private String key;
    private String value;

    private CountKey(String key, String value) {
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
