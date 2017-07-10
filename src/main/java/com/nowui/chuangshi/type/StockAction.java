package com.nowui.chuangshi.type;

public enum StockAction {
    IN("IN", "入库"),
    OUT("OUT", "出库"),
    REPLENISH("REPLENISH", "平台补充");

    private String key;
    private String value;

    private StockAction(String key, String value) {
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
