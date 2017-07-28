package com.nowui.chuangshi.type;

public enum CertificateImageType {

    WX("wx", "微信"), OTHER("other", "其他");

    private String key;
    private String value;

    private CertificateImageType(String key, String value) {
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
