package com.nowui.chuangshi.type;

public enum CategoryType {

    MENU("MENU", "菜单"),
    PRODUCT("PRODUCT", "商品"),
    ARTICLE("ARTICLE", "文章"),
    EMPLOYEE_ORGANIZATION("EMPLOYEE_ORGANIZATION", "员工组织架构"),
    MEMBER_ORGANIZATION("MEMBER_ORGANIZATION", "会员组织架构");

    private String key;
    private String value;

    private CategoryType(String key, String value) {
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
