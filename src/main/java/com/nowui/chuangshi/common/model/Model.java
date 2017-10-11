package com.nowui.chuangshi.common.model;

import com.jfinal.plugin.activerecord.Table;
import com.jfinal.plugin.activerecord.TableMapping;
import com.nowui.chuangshi.common.annotation.Column;
import com.nowui.chuangshi.common.sql.*;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.type.ColumnType;
import com.nowui.chuangshi.util.DateUtil;
import com.nowui.chuangshi.util.ValidateUtil;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;

public class Model<M extends Model> extends com.jfinal.plugin.activerecord.Model<M> implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Map<String, Object>> columnList;

    private Criteria criteria;

    public void setPrimaryKeyCriteria(String id) {
        Expression expression = new Expression(getTable().getPrimaryKey()[0], ExpressionType.EQUAL, id);
        Criteria criteria = new Criteria();
        criteria.addCondition(new Condition(ConditionType.WHERE, expression, false));

        setCriteria(criteria);
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    private Table getTable() {
        return TableMapping.me().getTable(getUsefulClass());
    }

    private Class<? extends com.jfinal.plugin.activerecord.Model> getUsefulClass() {
        Class c = getClass();
        return c.getName().indexOf("EnhancerByCGLIB") == -1 ? c : c.getSuperclass();
    }

    private List<Map<String, Object>> getColumnList() {
        if (columnList == null) {
            columnList = new ArrayList<Map<String, Object>>();

            Field[] fields = this.getClass().getDeclaredFields();
            for (Field field : fields) {
                Column column = field.getAnnotation(Column.class);

                if (column != null) {
                    Map<String, Object> map = new HashMap<String, Object>();

                    try {
                        map.put(Constant.NAME, field.get(Model.class).toString());
                        map.put("type", column.type());
                        map.put("length", column.length());
                        map.put("comment", column.comment());
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("IllegalAccessException: " + e);
                    }

                    columnList.add(map);
                }
            }
        }

        return columnList;
    }

    private Boolean isColumn(String key) {
        Boolean result = false;

        List<Map<String, Object>> columnList = getColumnList();
        for (Map<String, Object> map : columnList) {
            if (key.equals(map.get(Constant.NAME))) {
                result = true;

                break;
            }
        }

        return result;
    }

    private String regexVariable(ColumnType columnType, Object value) {
        StringBuilder stringBuilder = new StringBuilder();

        if (columnType.equals(ColumnType.TINYINT)) {
            stringBuilder.append((Boolean) value ? 1 : 0);
        } else if (columnType.equals(ColumnType.VARCHAR)) {
            if (((String)value).contains("\"")) {
                value = ((String)value).replaceAll("\"", "\\\"");
            }

            stringBuilder.append("\"");
            stringBuilder.append(value);
            stringBuilder.append("\"");
        } else if (columnType.equals(ColumnType.TEXT)) {
            if (((String)value).contains("\"")) {
                value = ((String)value).replaceAll("\"", "\\\"");
            }

            stringBuilder.append("\"");
            stringBuilder.append(value);
            stringBuilder.append("\"");
        } else if (columnType.equals(ColumnType.LONGTEXT)) {
            stringBuilder.append("\"");
            stringBuilder.append(value);
            stringBuilder.append("\"");
        } else if (columnType.equals(ColumnType.DATE)) {
            stringBuilder.append("\"");
            if (value instanceof String) {
                stringBuilder.append(value);
            } else if (value instanceof Date) {
                stringBuilder.append(DateUtil.getDateString((Date) value));
            } else {
                throw new RuntimeException("sql regex variable wrong");
            }
            stringBuilder.append("\"");
        } else if (columnType.equals(ColumnType.DATETIME)) {
            stringBuilder.append("\"");
            stringBuilder.append(DateUtil.getDateTimeString((Date) value));
            stringBuilder.append("\"");
        } else {
            stringBuilder.append(value);
        }

        return stringBuilder.toString();
    }

    private String regexCondition(String name, String operation, Object value) {
        boolean is_left_like = false;
        boolean is_right_like = false;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name);
        if (operation.equals("like")) {
            stringBuilder.append(" LIKE ");

            is_left_like = true;
            is_right_like = true;
        } else if (operation.equals("left_like")) {
            stringBuilder.append(" LIKE ");

            is_left_like = true;
        } else if (operation.equals("right_like")) {
            stringBuilder.append(" LIKE ");

            is_right_like = true;
        } else {
            stringBuilder.append(" = ");
        }

        if (value instanceof String) {
            stringBuilder.append("\"");

            if (is_left_like) {
                stringBuilder.append("%");
            }

            stringBuilder.append(value);

            if (is_right_like) {
                stringBuilder.append("%");
            }

            stringBuilder.append("\"");
        } else if (value instanceof Boolean) {
            stringBuilder.append((Boolean) value ? 1 : 0);
        } else if (value instanceof Date) {
            stringBuilder.append("\"");
            stringBuilder.append(DateUtil.getDateTimeString((Date) value));
            stringBuilder.append("\"");
        } else {
            stringBuilder.append(value);
        }

        return stringBuilder.toString();
    }

    private Boolean regexExit(String name) {
        Boolean isExit = false;

        for (int i = 0; i < getColumnList().size(); i++) {
            Map<String, Object> column = getColumnList().get(i);

            if (column.get("name").toString().equals(name)) {
                isExit = true;

                break;
            }
        }

        return isExit;
    }

//    public String buildSetSql() {
//        if (criteria == null) {
//            throw new RuntimeException("sql without set");
//        }
//
//        StringBuilder stringBuilder = new StringBuilder();
//
//        for (int i = 0; i < criteria.getSetList().size(); i++) {
//            Set set = criteria.getSetList().get(i);
//
//            if (set.getSetType().equals(SetType.NORMAL)) {
//                stringBuilder.append(regexCondition(set.getKey(), "equal", set.getValue()));
//            } else {
//                stringBuilder.append(set.getKey());
//                stringBuilder.append(" = ");
//                stringBuilder.append(set.getValue());
//            }
//            if (i + 1 == criteria.getSetList().size()) {
//                stringBuilder.append("\n");
//            } else {
//                stringBuilder.append(",\n");
//            }
//        }
//
//        return stringBuilder.toString();
//    }

    public String buildSelectSql() {
        StringBuilder stringBuilder = new StringBuilder();

        List<Select> selectList = criteria.getSelectList();

        if (selectList.size() > 0) {
            stringBuilder.append(",\n");

            for (int i = 0; i < selectList.size(); i++) {
                Select select = selectList.get(i);

                switch (select.getSelectType()) {
                    case IFNULL:
                        stringBuilder.append("IFNULL(");
                        stringBuilder.append(select.getKey());
                        stringBuilder.append(", '");
                        stringBuilder.append(select.getValue());
                        stringBuilder.append("') AS ");
                        stringBuilder.append(select.getName());
                        break;
                    case NORMAL:
                        stringBuilder.append(select.getKey());
                        if (!ValidateUtil.isNullOrEmpty(select.getName())) {
                            stringBuilder.append(" AS ");
                            stringBuilder.append(select.getName());
                        }
                        break;
                }

                if (i + 1 < selectList.size()) {
                    if (selectList.size() > 0) {
                        stringBuilder.append(",\n");
                    }
                }
            }
        }

        return stringBuilder.toString();
    }

    public String buildJoinSql() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Join join : criteria.getJoinList()) {
            switch (join.getJoinType()) {
                case LEFT_JOIN:
                    stringBuilder.append("LEFT JOIN ");
                    break;
            }

            stringBuilder.append(join.getTableA());
            stringBuilder.append(" ON ");
            stringBuilder.append(join.getTableA());
            stringBuilder.append(".");
            stringBuilder.append(join.getPrimaryA());
            stringBuilder.append(" = ");
            stringBuilder.append(join.getTableB());
            stringBuilder.append(".");
            stringBuilder.append(join.getPrimaryB());
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

    public String buildConditionSql() {
        if (criteria == null) {
            throw new RuntimeException("sql without condition");
        }

        StringBuilder stringBuilder = new StringBuilder();

        Boolean isWhere = false;
        Boolean isLeftLike = false;
        Boolean isRightike = false;

        for (Condition condition : criteria.getConditionList()) {
            Expression expression = condition.getExpression();
            if (expression != null) {
                if (isWhere) {
                    stringBuilder.append("AND ");
                } else {
                    isWhere = true;
                    stringBuilder.append("WHERE ");
                }

                String temp = "";
                Boolean isBetween = false;
                switch (expression.getExpressionType()) {
                    case EQUAL:
                        temp = " = ";
                        break;
                    case NOT_EQUAL:
                        temp = " != ";
                        break;
                    case LIKE:
                        isLeftLike = true;
                        isRightike = true;

                        temp = " LIKE ";
                        break;
                    case LEFT_LIKE:
                        isLeftLike = true;

                        temp = " LIKE ";
                        break;
                    case RIGHT_LIKE:
                        isRightike = true;

                        temp = " LIKE ";
                        break;
                    case LESS_THAN:
                        break;
                    case GREAT_THAN_EQUAL:
                        break;
                    case LESS_THAN_EQUAL:
                        break;
                    case BETWEEN:
                        isBetween = true;

                        temp = " BETWEEN ";
                        break;
                }

                if (isBetween) {
                    stringBuilder.append("(");
                }
                stringBuilder.append(expression.getKey());
                stringBuilder.append(temp);

                stringBuilder.append(buildConditionVariable(expression.getValue(), isLeftLike, isRightike, false));

                if (isBetween) {
                    stringBuilder.append(buildConditionVariable(expression.getValue2(), isLeftLike, isRightike, true));
                }

                stringBuilder.append("\n");

            } else if (condition.getExpressionGroup() != null) {

            }
        }

        return stringBuilder.toString();
    }

    public String buildConditionVariable(Object value, Boolean isLeftLike, Boolean isRightike, Boolean isBetween) {
        StringBuilder stringBuilder = new StringBuilder();

        if (isBetween) {
            stringBuilder.append(" AND ");
        }

        if (value instanceof String) {
            stringBuilder.append("\"");

            if (isLeftLike) {
                stringBuilder.append("%");
            }

            stringBuilder.append(value);

            if (isRightike) {
                stringBuilder.append("%");
            }

            stringBuilder.append("\"");
        } else if (value instanceof Boolean) {
            stringBuilder.append((Boolean) value ? 1 : 0);
        } else if (value instanceof Date) {
            stringBuilder.append("\"");
            stringBuilder.append(DateUtil.getDateTimeString((Date) value));
            stringBuilder.append("\"");
        } else {
            stringBuilder.append(value);
        }

        if (isBetween) {
            stringBuilder.append(")");
        }

        return stringBuilder.toString();
    }

    public String buildOrderBySql() {
        if (criteria == null) {
            throw new RuntimeException("sql without condition");
        }

        StringBuilder stringBuilder = new StringBuilder();
        List<OrderBy> orderByList = criteria.getOrderByList();

//        Iterator<OrderBy> iterator = orderByList.iterator();
//        while (iterator.hasNext()) {
//            OrderBy orderBy = iterator.next();
//
//            if (!isColumn(orderBy.getKey())) {
//                iterator.remove();
//            }
//        }

        if (orderByList.size() == 0) {
            return "";
        }

        stringBuilder.append("ORDER BY ");
        for (int i = 0; i < orderByList.size(); i++) {
            OrderBy orderBy = orderByList.get(i);

            stringBuilder.append(orderBy.getKey());
            stringBuilder.append(" ");
            stringBuilder.append(orderBy.getOrderByType().getKey());
            if (i + 1 < orderByList.size()) {
                stringBuilder.append(", ");
            } else {
                stringBuilder.append("\n");
            }
        }

        return stringBuilder.toString();
    }

    public String buildPaginateSql() {
        if (criteria == null) {
            throw new RuntimeException("sql without condition");
        }

        if (criteria.getIsPaginate()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("LIMIT ");
            stringBuilder.append(criteria.getM());
            stringBuilder.append(", ");
            stringBuilder.append(criteria.getN());
            stringBuilder.append("\n");

            return stringBuilder.toString();
        } else {
            return "";
        }
    }

    public String buildCountSql() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT \n");
        stringBuilder.append("COUNT(*) \n");
        stringBuilder.append("FROM ");
        stringBuilder.append(getTable().getName());
        stringBuilder.append("\n");
        stringBuilder.append(buildJoinSql());
        stringBuilder.append(buildConditionSql());

        return stringBuilder.toString();
    }

    public String buildListSql(Boolean isPrimaryKey) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT \n");
        if (criteria.getJoinList().size() > 0) {
            stringBuilder.append(getTable().getName());
            stringBuilder.append(".");
        }
        if (isPrimaryKey) {
            stringBuilder.append(getTable().getPrimaryKey()[0]);
        } else {
            stringBuilder.append("*");
        }
        stringBuilder.append(buildSelectSql());
        stringBuilder.append("\n");
        stringBuilder.append("FROM ");
        stringBuilder.append(getTable().getName());
        stringBuilder.append("\n");
        stringBuilder.append(buildJoinSql());
        stringBuilder.append(buildConditionSql());
        stringBuilder.append(buildOrderBySql());
        stringBuilder.append(buildPaginateSql());

        return stringBuilder.toString();
    }

    public String buildFindSql() {
        if (criteria.getConditionList().size() == 0) {
            throw new RuntimeException("sql without condition");
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT \n");
        if (criteria.getJoinList().size() > 0) {
            stringBuilder.append(getTable().getName());
            stringBuilder.append(".");
        }
        stringBuilder.append("*");
        stringBuilder.append(buildSelectSql());
        stringBuilder.append("\n");
        stringBuilder.append("FROM ");
        stringBuilder.append(getTable().getName());
        stringBuilder.append(" \n");
        stringBuilder.append(buildJoinSql());
        stringBuilder.append(buildConditionSql());

        return stringBuilder.toString();
    }

    public String buildSaveSql() {
        StringBuilder stringBuilder = new StringBuilder();

        StringBuilder stringBuilder2 = new StringBuilder();
        if (criteria == null) {
            stringBuilder.append("INSERT INTO ");
            stringBuilder.append(getTable().getName());
            stringBuilder.append(" ");
            stringBuilder.append("(\n");
            for (int i = 0; i < getColumnList().size(); i++) {
                Map<String, Object> column = getColumnList().get(i);

                stringBuilder.append(column.get("name"));
                if (i + 1 < getColumnList().size()) {
                    stringBuilder.append(",\n");
                } else {
                    stringBuilder.append("\n");
                }

                Boolean isExit = false;
                for (Map.Entry<String, Object> attribute : this.getAttrs().entrySet()) {
                    if (column.get("name").equals(attribute.getKey())) {
                        stringBuilder2.append(regexVariable((ColumnType) column.get("type"), attribute.getValue()));

                        isExit = true;

                        break;
                    }
                }

                if (isExit) {

                } else {
                    if (column.get("type").equals(ColumnType.INT)) {
                        stringBuilder2.append("0");
                    } else if (column.get("type").equals(ColumnType.TINYINT)) {
                        stringBuilder2.append("0");
                    } else if (column.get("type").equals(ColumnType.DECIMAL)) {
                        stringBuilder2.append(BigDecimal.valueOf(0));
                    } else if (column.get("type").equals(ColumnType.DATE)) {
                        stringBuilder2.append("\"");
                        stringBuilder2.append(DateUtil.getDateString(new Date()));
                        stringBuilder2.append("\"");
                    } else if (column.get("type").equals(ColumnType.DATETIME)) {
                        stringBuilder2.append("\"");
                        stringBuilder2.append(DateUtil.getDateTimeString(new Date()));
                        stringBuilder2.append("\"");
                    } else {
                        stringBuilder2.append("''");
                    }
                }

                if (i + 1 < getColumnList().size()) {
                    stringBuilder2.append(",\n");
                } else {
                    stringBuilder2.append("\n");
                }
            }
            stringBuilder.append(") VALUES (\n");
            stringBuilder.append(stringBuilder2);
            stringBuilder.append(")\n");
        } else {

        }

        return stringBuilder.toString();
    }

    public String buildUpdateSql() {
        if (criteria.getConditionList().size() == 0) {
            throw new RuntimeException("sql without condition");
        }

        StringBuilder stringBuilder = new StringBuilder();

        List<Map<String, Object>> updateColumnList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < getColumnList().size(); i++) {
            Map<String, Object> column = getColumnList().get(i);

            for (Map.Entry<String, Object> attribute : this.getAttrs().entrySet()) {
                if (column.get("name").equals(attribute.getKey())) {
                    column.put("value", attribute.getValue());
                    updateColumnList.add(column);

                    break;
                }
            }

        }

        if (updateColumnList.size() == 0) {
            throw new RuntimeException("sql without set variable");
        }

        stringBuilder.append("UPDATE ");
        stringBuilder.append(getTable().getName());
        stringBuilder.append(" SET\n");
        for (int i = 0; i < updateColumnList.size(); i++) {
            Map<String, Object> column = updateColumnList.get(i);

            stringBuilder.append(column.get("name"));
            stringBuilder.append(" = ");
            stringBuilder.append(regexVariable((ColumnType) column.get("type"), column.get("value")));
            if (i + 1 < updateColumnList.size()) {
                stringBuilder.append(",\n");
            } else {
                stringBuilder.append("\n");
            }
        }
        stringBuilder.append(buildConditionSql());

        return stringBuilder.toString();
    }

//    public String buildDeleteSql() {
//        if (criteria.getSetList().size() == 0) {
//            throw new RuntimeException("sql without set");
//        }
//
//        if (criteria.getConditionList().size() == 0) {
//            throw new RuntimeException("sql without condition");
//        }
//
//        StringBuilder stringBuilder = new StringBuilder();
//
//        stringBuilder.append("UPDATE ");
//        stringBuilder.append(getTable().getName());
//        stringBuilder.append(" SET\n");
//        stringBuilder.append(buildSetSql());
//        stringBuilder.append(buildConditionSql());
//
//        return stringBuilder.toString();
//    }

}