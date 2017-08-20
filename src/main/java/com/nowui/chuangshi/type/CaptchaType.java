package com.nowui.chuangshi.type;

public enum CaptchaType {

    REGISTER("REGISTER", "REGISTER"),
    PASSWORD("PASSWORD", "PASSWORD");

    private String key;
    private String value;

    private CaptchaType(String key, String value) {
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
