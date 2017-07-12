package com.nowui.chuangshi.type;

public enum QrcodeType {

    PLATFORM("PLATFORM", "平台"),
    MEMBER("MEMBER", "会员");

    private String key;
    private String value;

    private QrcodeType(String key, String value) {
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
