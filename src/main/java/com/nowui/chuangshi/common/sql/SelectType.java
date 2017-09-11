package com.nowui.chuangshi.common.sql;

public enum SelectType {
    NORMAL("NORMAL", "NORMAL"),
    IFNULL("IFNULL", "IFNULL");

    private String key;
    private String value;

    private SelectType(String key, String value) {
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
