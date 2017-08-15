package com.nowui.chuangshi.common.sql;

public class Join {

    private String key;
    private JoinType joinType;

    public Join(String key, JoinType joinType) {
        this.key = key;
        this.joinType = joinType;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public JoinType getOrderByType() {
        return joinType;
    }

    public void setOrderByType(JoinType joinType) {
        this.joinType = joinType;
    }
}
