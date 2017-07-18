package com.nowui.chuangshi.type;

public enum ExpressPayWay {
    
    SELF_PAY("SELF_PAY", "自己付"),
    ARRIVE_PAY("ARRIVE_PAY", "到付"),
    MONTHLY_PAY("MONTHLY_PAY", "月结"),
    THIRD_PARTY_PAY("THIRD_PARTY_PAY", "第三方支付");

    private String key;
    private String value;

    private ExpressPayWay(String key, String value) {
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
