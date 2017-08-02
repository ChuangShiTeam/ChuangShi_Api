package com.nowui.chuangshi.common.sql;

public class Condition {

    private ConditionType conditionType;
    private ExpressionGroup expressionGroup;
    private Expression expression;
    private Boolean isAllowEmpty;

    public Condition(ConditionType conditionType, ExpressionGroup expressionGroup, Boolean isAllowEmpty) {
        this.conditionType = conditionType;
        this.expressionGroup = expressionGroup;
        this.isAllowEmpty = isAllowEmpty;
    }

    public Condition(ConditionType conditionType, Expression expression, Boolean isAllowEmpty) {
        this.conditionType = conditionType;
        this.expression = expression;
        this.isAllowEmpty = isAllowEmpty;
    }

    public ConditionType getConditionType() {
        return conditionType;
    }

    public void setConditionType(ConditionType conditionType) {
        this.conditionType = conditionType;
    }

    public ExpressionGroup getExpressionGroup() {
        return expressionGroup;
    }

    public void setExpressionGroup(ExpressionGroup expressionGroup) {
        this.expressionGroup = expressionGroup;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public Boolean getAllowEmpty() {
        return isAllowEmpty;
    }

    public void setAllowEmpty(Boolean allowEmpty) {
        isAllowEmpty = allowEmpty;
    }
}
