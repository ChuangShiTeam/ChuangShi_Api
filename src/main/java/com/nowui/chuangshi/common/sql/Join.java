package com.nowui.chuangshi.common.sql;

public class Join {

    private String tableA;
    private String primaryA;
    private String tableB;
    private String primaryB;
    private JoinType joinType;

    public Join(String tableA, String primaryA, String tableB, String primaryB, JoinType joinType) {
        this.tableA = tableA;
        this.primaryA = primaryA;
        this.tableB = tableB;
        this.primaryB = primaryB;
        this.joinType = joinType;
    }

    public String getTableA() {
        return tableA;
    }

    public void setTableA(String tableA) {
        this.tableA = tableA;
    }

    public String getPrimaryA() {
        return primaryA;
    }

    public void setPrimaryA(String primaryA) {
        this.primaryA = primaryA;
    }

    public String getTableB() {
        return tableB;
    }

    public void setTableB(String tableB) {
        this.tableB = tableB;
    }

    public String getPrimaryB() {
        return primaryB;
    }

    public void setPrimaryB(String primaryB) {
        this.primaryB = primaryB;
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
