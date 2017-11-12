package com.nowui.chuangshi.type;

public enum UserType {

    SYSTEM("SYSTEM", "系统管理员"),
    ADMIN("ADMIN", "管理员"),
    MEMBER("MEMBER", "会员"),
    DEALER("DEALER", "经销商"),
    SUPPLIER("SUPPLIER", "供应商"),
    STUDENT("STUDENT", "学生"),
    PUPIL_ADMISSIONS("PUPIL_ADMISSIONS", "小学报名者"),
    JUNIOR_ADMISSIONS("JUNIOR_ADMISSIONS", "中学报名者"),
    TEACHER("TEACHER", "老师"),
    RENAULT_MEMBER("RENAULT_MEMBER", "雷诺会员");

    private String key;
    private String value;

    private UserType(String key, String value) {
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
