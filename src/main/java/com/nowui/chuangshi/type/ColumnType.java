package com.nowui.chuangshi.type;

public enum ColumnType {
    INT("INT"),
    TINYINT("TINYINT"),
    VARCHAR("VARCHAR"),
    DECIMAL("DECIMAL"),
    TEXT("TEXT"),
    LONGTEXT("LONGTEXT"),
    DATE("DATE"),
    DATETIME("DATETIME");

    private String key;

    private ColumnType(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
