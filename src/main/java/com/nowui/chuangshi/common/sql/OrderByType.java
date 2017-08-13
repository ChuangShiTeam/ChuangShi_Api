package com.nowui.chuangshi.common.sql;

public enum OrderByType {
    ASC("ASC", "ASC"),
    DESC("DESC", "DESC");

    private String key;
    private String value;

    private OrderByType(String key, String value) {
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
