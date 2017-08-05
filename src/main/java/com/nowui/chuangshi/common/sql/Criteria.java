package com.nowui.chuangshi.common.sql;

import java.util.ArrayList;
import java.util.List;

public class Criteria {

    private List<Condition> conditionList;

    public Criteria() {
        conditionList = new ArrayList<Condition>();
    }

    public void addCondition(Condition condition) {
        conditionList.add(condition);
    }

    public List<Condition> getConditionList() {
        return conditionList;
    }

}
