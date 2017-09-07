package com.nowui.chuangshi.common.sql;

public class Join {

    private String table;
    private String primary;
    private JoinType joinType;

    public Join(String table, String primary, JoinType joinType) {
        this.table = table;
        this.primary = primary;
        this.joinType = joinType;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        primary = primary;
    }

    public JoinType getJoinType() {
        return joinType;
    }

    public void setJoinType(JoinType joinType) {
        this.joinType = joinType;
    }
}
