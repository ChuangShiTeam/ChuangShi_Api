package com.nowui.chuangshi.common.sql;

public class Expression {

    private String key;
    private ExpressionType expressionType;
    private Object value;
    private Object value2;

    public Expression(String key, ExpressionType expressionType, Object value) {
        this.key = key;
        this.expressionType = expressionType;
        this.value = value;
    }

    public Expression(String key, ExpressionType expressionType, Object value, Object value2) {
        this.key = key;
        this.expressionType = expressionType;
        this.value = value;
        this.value2 = value2;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ExpressionType getExpressionType() {
        return expressionType;
    }

    public void setExpressionType(ExpressionType expressionType) {
        this.expressionType = expressionType;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getValue2() {
        return value2;
    }

    public void setValue2(Object value2) {
        this.value2 = value2;
    }
}
