package com.nowui.chuangshi.common.sql;

import com.nowui.chuangshi.constant.Constant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Criteria {

    private List<Map<String, Object>> setList;
    private List<Condition> conditionList;

    private Boolean isSystemVersion;
    private Boolean isSystemStatus;

    public Criteria() {
        setList = new ArrayList<Map<String, Object>>();
        conditionList = new ArrayList<Condition>();
        isSystemVersion = true;
        isSystemStatus = true;
    }

    public void addSet(Map<String, Object> set) {
        setList.add(set);
    }

    public List<Map<String, Object>> getSetList() {
        return setList;
    }

    public void addCondition(Condition condition) {
        conditionList.add(condition);
    }

    public List<Condition> getConditionList() {
        return conditionList;
    }

    public void addSystem_status() {
        if (!isOnlyCondition(Constant.SYSTEM_STATUS)) {
            Expression expression = new Expression(Constant.SYSTEM_STATUS, ExpressionType.EQUAL, true);
            Condition condition = new Condition(ConditionType.WHERE, expression, false);
            addCondition(condition);
        }
    }

    private Boolean isOnlyCondition(String key) {
        Boolean isExit = false;

        for (Condition condition : conditionList) {
            if (condition.getExpression() != null) {
                if (condition.getExpression().getKey().equals(key)) {
                    isExit = true;
                }
            }
        }

        return isExit;
    }

    public Boolean getSystemVersion() {
        return isSystemVersion;
    }

    public void setSystemVersion(Boolean systemVersion) {
        isSystemVersion = systemVersion;
    }

    public Boolean getSystemStatus() {
        return isSystemStatus;
    }

    public void setSystemStatus(Boolean systemStatus) {
        isSystemStatus = systemStatus;
    }

}
