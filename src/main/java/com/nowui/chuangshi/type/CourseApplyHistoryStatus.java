package com.nowui.chuangshi.type;

public enum CourseApplyHistoryStatus {
    
    WAITING("WAITING", "申请处理中"),
    SUCCESS("SUCCESS", "申请成功"),
    FAIL("FAIL", "申请失败"),
    CANCEL("CANCEL", "取消申请");

    private String key;
    private String value;

    private CourseApplyHistoryStatus(String key, String value) {
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
