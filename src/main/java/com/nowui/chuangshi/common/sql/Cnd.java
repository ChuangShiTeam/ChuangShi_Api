package com.nowui.chuangshi.common.sql;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.ValidateUtil;

public class Cnd {

    private Criteria criteria;

    Cnd() {
        criteria = new Criteria();
    }

    protected Cnd(Condition condition) {
        this();
        criteria.addCondition(condition);
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public static Cnd where(String key, Object value) {
        Expression expression = new Expression(key, ExpressionType.EQUAL, value);
        return new Cnd(new Condition(ConditionType.WHERE, expression, false));
    }

    public static Cnd whereAllowEmpty(String key, Object value) {
        if (ValidateUtil.isNullOrEmpty(value)) {
            return new Cnd();
        } else {
            return where(key, value);
        }
    }

    public static Cnd whereLike(String key, Object value) {
        Expression expression = new Expression(key, ExpressionType.LIKE, value);
        return new Cnd(new Condition(ConditionType.WHERE, expression, false));
    }

    public static Cnd whereLikeAllowEmpty(String key, Object value) {
        if (ValidateUtil.isNullOrEmpty(value)) {
            return new Cnd();
        } else {
            return whereLike(key, value);
        }
    }

    public static Cnd whereLeftLike(String key, Object value) {
        Expression expression = new Expression(key, ExpressionType.LEFT_LIKE, value);
        return new Cnd(new Condition(ConditionType.WHERE, expression, false));
    }

    public static Cnd whereLeftLikeAllowEmpty(String key, Object value) {
        if (ValidateUtil.isNullOrEmpty(value)) {
            return new Cnd();
        } else {
            return whereLeftLike(key, value);
        }
    }

    public static Cnd whereRightLike(String key, Object value) {
        Expression expression = new Expression(key, ExpressionType.RIGHT_LIKE, value);
        return new Cnd(new Condition(ConditionType.WHERE, expression, false));
    }

    public static Cnd whereRightLikeeAllowEmpty(String key, Object value) {
        if (ValidateUtil.isNullOrEmpty(value)) {
            return new Cnd();
        } else {
            return whereRightLike(key, value);
        }
    }

    public Cnd and(String key, Object value) {
        Expression expression = new Expression(key, ExpressionType.EQUAL, value);
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

    public Cnd andNotSystemStatus() {
        this.criteria.setSystemStatus(false);

        return this;
    }

    public Cnd andNotSystemVersion() {
        this.criteria.setSystemVersion(false);

        return this;
    }

    public Cnd set(String key, Object value) {
        this.criteria.addSet(key, value);
        return this;
    }

    public Cnd paginate(Integer m, Integer n) {
        this.criteria.setPaginate(m, n);
        return this;
    }

    public Cnd addSystemStatus() {
        if (this.criteria.getSystemStatus()) {
            Expression expression = new Expression(Constant.SYSTEM_STATUS, ExpressionType.EQUAL, true);
            this.criteria.addCondition(new Condition(ConditionType.WHERE, expression, false));
        }

        return this;
    }

    public Cnd asc(String key) {

        return this;
    }

}
