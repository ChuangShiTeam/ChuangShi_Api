package com.nowui.chuangshi.common.sql;

public enum JoinType {
    LEFT_JOIN("LEFT_JOIN", "LEFT_JOIN");

    private String key;
    private String value;

    private JoinType(String key, String value) {
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
