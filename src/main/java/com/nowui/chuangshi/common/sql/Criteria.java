package com.nowui.chuangshi.common.sql;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Criteria {

    private List<Map<String, Object>> setList;
    private List<Condition> conditionList;

    public Criteria() {
        setList = new ArrayList<Map<String, Object>>();
        conditionList = new ArrayList<Condition>();
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

}
