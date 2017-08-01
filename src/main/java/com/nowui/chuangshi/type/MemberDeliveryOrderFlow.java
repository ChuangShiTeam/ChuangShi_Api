package com.nowui.chuangshi.type;

public enum MemberDeliveryOrderFlow {
    
    WAIT_SEND("WAIT_SEND", "待发货"),
    WAIT_WAREHOUSE_SEND("WAIT_WAREHOUSE_SEND", "待仓库发货"),
    WAIT_RECEIVE("WAIT_RECEIVE", "待收货"),
    COMPLETE("COMPLETE", "已完成"),
    CANCEL("CANCEL", "已取消");

    private String key;
    private String value;

    private MemberDeliveryOrderFlow(String key, String value) {
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
