package com.nowui.chuangshi.common.sql;

public enum ConditionType {
    WHERE("WHERE", "WHERE"),
    AND("AND", "AND"),
    OR("OR", "OR"),
    BETWEEN("BETWEEN", "BETWEEN");

    private String key;
    private String value;

    private ConditionType(String key, String value) {
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
