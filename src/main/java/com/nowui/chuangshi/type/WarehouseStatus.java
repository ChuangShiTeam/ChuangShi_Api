package com.nowui.chuangshi.type;

public enum WarehouseStatus {
    
    DISABLED("DISABLED", "停用"),
    AVAILABLE("AVAILABLE", "启用");

    private String key;
    private String value;

    private WarehouseStatus(String key, String value) {
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
