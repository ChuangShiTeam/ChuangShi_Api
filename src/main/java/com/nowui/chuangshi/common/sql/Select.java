package com.nowui.chuangshi.common.sql;

public class Select {

    private String key;
    private Object value;
    private String name;
    private SelectType selectType;

    public Select(String key) {
        this.key = key;
        this.selectType = SelectType.NORMAL;
    }

    public Select(String key, String name) {
        this.key = key;
        this.name = name;
        this.selectType = SelectType.NORMAL;
    }

    public Select(String key, Object value, String name, SelectType selectType) {
        this.key = key;
        this.value = value;
        this.name = name;
        this.selectType = selectType;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public SelectType getSelectType() {
        return selectType;
    }

}
