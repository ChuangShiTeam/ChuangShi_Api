package com.nowui.chuangshi.type;

public enum MinhangTaskType {
	
	QUESTION("QUESTION", "答题"),
	PICTURE("PICTURE", "上传图片"),
    RECORD("RECORD", "上传录音");

    private String key;
    private String value;

    private MinhangTaskType(String key, String value) {
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
