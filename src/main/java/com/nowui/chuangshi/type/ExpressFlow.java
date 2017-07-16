package com.nowui.chuangshi.type;

public enum ExpressFlow {
    
    NOTRACK("NOTRACK", "无轨迹"),
    SENT("SENT", "已揽收"),
    ONWAY("ONWAY", "在途中"),
    TOCITY("TOCITY", "到达派件城市"),
    RECEIVED("RECEIVED", "签收"),
    PROBLEM("PROBLEM", "问题件");

    private String key;
    private String value;

    private ExpressFlow(String key, String value) {
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
