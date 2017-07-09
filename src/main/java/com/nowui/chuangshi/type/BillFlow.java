package com.nowui.chuangshi.type;

public enum BillFlow {
    COMPLETE("COMPLETE", "已完成");

    private String key;
    private String value;

    private BillFlow(String key, String value) {
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
