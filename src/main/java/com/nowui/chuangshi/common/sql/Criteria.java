package com.nowui.chuangshi.common.sql;

import com.nowui.chuangshi.constant.Constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Criteria {

    private List<Set> setList;
    private List<Condition> conditionList;

    private Boolean isPaginate;
    private Integer m;
    private Integer n;
    private Boolean isSystemVersion;
    private Boolean isSystemStatus;

    public Criteria() {
        setList = new ArrayList<Set>();
        conditionList = new ArrayList<Condition>();
        isPaginate = false;
        isSystemVersion = true;
        isSystemStatus = true;
    }

    public void addSet(String key, Object value) {
        setList.add(new Set(key, value, SetType.NORMAL));
    }

    public List<Set> getSetList() {
        List<Set> list = new ArrayList<Set>();
        list.addAll(setList);

        if (isSystemVersion) {
            list.add(new Set(Constant.SYSTEM_VERSION, Constant.SYSTEM_VERSION + " + 1", SetType.OTHER));
        }

        return list;
    }

    public void addCondition(Condition condition) {
        conditionList.add(condition);
    }

    public List<Condition> getConditionList() {
        List<Condition> list = new ArrayList<Condition>();
        list.addAll(conditionList);

        if (isSystemStatus) {
            if (!isOnlyCondition(Constant.SYSTEM_STATUS)) {
                Expression expression = new Expression(Constant.SYSTEM_STATUS, ExpressionType.EQUAL, true);
                Condition condition = new Condition(ConditionType.WHERE, expression, false);
                list.add(condition);
            }
        }

        return list;
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

    public void setPaginate(Integer m, Integer n) {
        this.isPaginate = true;
        this.m = m;
        this.n = n;
    }

    public Boolean getIsPaginate() {
        return isPaginate;
    }

    public Integer getM() {
        return m;
    }

    public Integer getN() {
        return n;
    }
}
