package com.nowui.chuangshi.type;

public enum StockReplenishAction {
	
	BREAKAGE("BREAKAGE", "报损"),
	OVERFLOW("OVERFLOW", "报溢");

    private String key;
    private String value;

    private StockReplenishAction(String key, String value) {
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
