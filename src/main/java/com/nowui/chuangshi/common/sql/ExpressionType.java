package com.nowui.chuangshi.common.sql;

public enum ExpressionType {
    EQUAL("EQUAL", "EQUAL"),
    LIKE("LIKE", "LIKE"),
    LEFT_LIKE("LEFT_LIKE", "LEFT_LIKE"),
    RIGHT_LIKE("RIGHT_LIKE", "RIGHT_LIKE"),
    LESS_THAN("LESSTHAN", "LESSTHAN"),
    GREAT_THAN_EQUAL("GREAT_THAN_EQUAL", "GREAT_THAN_EQUAL"),
    LESS_THAN_EQUAL("LESS_THAN_EQUAL", "LESS_THAN_EQUAL"),
    BETWEEN("BETWEEN", "BETWEEN");

    private String key;
    private String value;

    private ExpressionType(String key, String value) {
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
