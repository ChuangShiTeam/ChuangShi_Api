package com.nowui.chuangshi.type;

public enum PayType {

    ALIPAY("ALIPAY", "支付宝"),
    WECHAT("WECHAT", "微信");

    private String key;
    private String value;

    private PayType(String key, String value) {
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
