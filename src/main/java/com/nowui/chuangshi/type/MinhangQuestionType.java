package com.nowui.chuangshi.type;

public enum MinhangQuestionType {
	
	RADIO("RADIO", "单选题"),
	CHECKBOX("CHECKBOX", "多选题"),
	GAP_FILLING("GAP_FILLING", "填空题");

    private String key;
    private String value;

    private MinhangQuestionType(String key, String value) {
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
