package com.nowui.chuangshi.common.sql;

import com.nowui.chuangshi.util.ValidateUtil;

public class Cnd {

    private Criteria criteria;

    public Cnd() {
        this.criteria = new Criteria();
    }

    public Criteria getCriteria() {
        return this.criteria;
    }

    public Cnd select(String key) {
        Select select = new Select(key);
        this.criteria.addSelect(select);
        return this;
    }

    public Cnd select(String key, String name) {
        Select select = new Select(key, name);
        this.criteria.addSelect(select);
        return this;
    }

    public Cnd selectIfNull(String key, String value, String name) {
        Select select = new Select(key, value, name, SelectType.IFNULL);
        this.criteria.addSelect(select);
        return this;
    }

    public Cnd leftJoin(String tableA, String primaryA, String tableB, String primaryB) {
        Join join = new Join(tableA, primaryA, tableB, primaryB, JoinType.LEFT_JOIN);
        this.criteria.addJoin(join);
        return this;
    }

    public Cnd where(String key, Object value) {
        Expression expression = new Expression(key, ExpressionType.EQUAL, value);
        this.criteria.addCondition(new Condition(ConditionType.WHERE, expression, false));
        return this;
    }

    public Cnd whereAllowEmpty(String key, Object value) {
        if (ValidateUtil.isNullOrEmpty(value)) {
            return this;
        } else {
            return where(key, value);
        }
    }

    public Cnd whereLike(String key, Object value) {
        Expression expression = new Expression(key, ExpressionType.LIKE, value);
        this.criteria.addCondition(new Condition(ConditionType.WHERE, expression, false));
        return this;
    }

    public Cnd whereLikeAllowEmpty(String key, Object value) {
        if (ValidateUtil.isNullOrEmpty(value)) {
            return this;
        } else {
            return whereLike(key, value);
        }
    }

    public Cnd whereLeftLike(String key, Object value) {
        Expression expression = new Expression(key, ExpressionType.LEFT_LIKE, value);
        this.criteria.addCondition(new Condition(ConditionType.WHERE, expression, false));
        return this;
    }

    public Cnd whereLeftLikeAllowEmpty(String key, Object value) {
        if (ValidateUtil.isNullOrEmpty(value)) {
            return this;
        } else {
            return whereLeftLike(key, value);
        }
    }

    public Cnd whereRightLike(String key, Object value) {
        Expression expression = new Expression(key, ExpressionType.RIGHT_LIKE, value);
        this.criteria.addCondition(new Condition(ConditionType.WHERE, expression, false));
        return this;
    }

    public Cnd whereRightLikeeAllowEmpty(String key, Object value) {
        if (ValidateUtil.isNullOrEmpty(value)) {
            return this;
        } else {
            return whereRightLike(key, value);
        }
    }

    public Cnd and(String key, Object value) {
        Expression expression = new Expression(key, ExpressionType.EQUAL, value);
        this.criteria.addCondition(new Condition(ConditionType.WHERE, expression, false));
        return this;
    }

    public Cnd andNot(String key, Object value) {
        Expression expression = new Expression(key, ExpressionType.NOT_EQUAL, value);
        this.criteria.addCondition(new Condition(ConditionType.WHERE, expression, false));
        return this;
    }

    public Cnd andAllowEmpty(String key, Object value) {
        if (ValidateUtil.isNullOrEmpty(value)) {
            return this;
        } else {
            return and(key, value);
        }
    }

    public Cnd andLike(String key, Object value) {
        Expression expression = new Expression(key, ExpressionType.LIKE, value);
        this.criteria.addCondition(new Condition(ConditionType.WHERE, expression, false));
        return this;
    }

    public Cnd andLikeAllowEmpty(String key, Object value) {
        if (ValidateUtil.isNullOrEmpty(value)) {
            return this;
        } else {
            return andLike(key, value);
        }
    }

    public Cnd andLeftLike(String key, Object value) {
        Expression expression = new Expression(key, ExpressionType.LEFT_LIKE, value);
        this.criteria.addCondition(new Condition(ConditionType.WHERE, expression, false));
        return this;
    }

    public Cnd andLeftLikeAllowEmpty(String key, Object value) {
        if (ValidateUtil.isNullOrEmpty(value)) {
            return this;
        } else {
            return andLeftLike(key, value);
        }
    }

    public Cnd andRightLike(String key, Object value) {
        Expression expression = new Expression(key, ExpressionType.RIGHT_LIKE, value);
        this.criteria.addCondition(new Condition(ConditionType.WHERE, expression, false));
        return this;
    }

    public Cnd andRightLikeAllowEmpty(String key, Object value) {
        if (ValidateUtil.isNullOrEmpty(value)) {
            return this;
        } else {
            return andRightLike(key, value);
        }
    }

    public Cnd andBetween(String key, Object value, Object value2) {
        Expression expression = new Expression(key, ExpressionType.BETWEEN, value, value2);
        this.criteria.addCondition(new Condition(ConditionType.WHERE, expression, false));
        return this;
    }

//    public Cnd set(String key, Object value) {
//        this.criteria.addSet(key, value);
//        return this;
//    }

    public Cnd paginate(Integer m, Integer n) {
        this.criteria.setPaginate(m, n);
        return this;
    }

    public Cnd asc(String key) {
        OrderBy orderBy = new OrderBy(key, OrderByType.ASC);
        this.criteria.addOrderBy(orderBy);

        return this;
    }

    public Cnd desc(String key) {
        OrderBy orderBy = new OrderBy(key, OrderByType.DESC);
        this.criteria.addOrderBy(orderBy);

        return this;
    }

}
