package com.nowui.chuangshi.common.sql;

import java.util.ArrayList;
import java.util.List;

public class Criteria {

//    private List<Set> setList;
    private List<Select> selectList;
    private List<Join> joinList;
    private List<Condition> conditionList;
    private List<OrderBy> orderByList;

    private Boolean isPaginate;
    private Integer m;
    private Integer n;

    public Criteria() {
//        setList = new ArrayList<Set>();
        selectList = new ArrayList<Select>();
        joinList = new ArrayList<Join>();
        conditionList = new ArrayList<Condition>();
        orderByList = new ArrayList<OrderBy>();
        isPaginate = false;
    }
//
//    public void addSet(String key, Object value) {
//        setList.add(new Set(key, value, SetType.NORMAL));
//    }
//
//    public List<Set> getSetList() {
//        List<Set> list = new ArrayList<Set>();
//        list.addAll(setList);
//
//        return list;
//    }

    public void addSelect(Select select) {
        selectList.add(select);
    }

    public List<Select> getSelectList() {
        List<Select> list = new ArrayList<Select>();
        list.addAll(selectList);

        return list;
    }

    public void addJoin(Join join) {
        joinList.add(join);
    }

    public List<Join> getJoinList() {
        List<Join> list = new ArrayList<Join>();
        list.addAll(joinList);

        return list;
    }

    public void addCondition(Condition condition) {
        conditionList.add(condition);
    }

    public List<Condition> getConditionList() {
        List<Condition> list = new ArrayList<Condition>();
        list.addAll(conditionList);

        return list;
    }

    public void addOrderBy(OrderBy orderBy) {
        orderByList.add(orderBy);
    }

    public List<OrderBy> getOrderByList() {
        List<OrderBy> list = new ArrayList<OrderBy>();
        list.addAll(orderByList);

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
