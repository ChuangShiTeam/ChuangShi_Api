package com.nowui.chuangshi.common.sql;

public enum SetType {
    NORMAL("NORMAL", "NORMAL"),
    OTHER("OTHER", "OTHER");

    private String key;
    private String value;

    private SetType(String key, String value) {
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
