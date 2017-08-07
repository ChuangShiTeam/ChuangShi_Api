package com.nowui.chuangshi.common.sql;

public class Set {

    private String key;
    private Object value;
    private SetType setType;

    public Set(String key, Object value, SetType setType) {
        this.key = key;
        this.value = value;
        this.setType = setType;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    public SetType getSetType() {
        return setType;
    }
}
