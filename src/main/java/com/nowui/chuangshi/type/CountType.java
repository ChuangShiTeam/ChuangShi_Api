package com.nowui.chuangshi.type;

public enum CountType {

    MEMBER("MEMBER", "会员");

    private String key;
    private String value;

    private CountType(String key, String value) {
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
