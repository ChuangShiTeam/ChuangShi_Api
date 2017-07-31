package com.nowui.chuangshi.common.model;

import com.nowui.chuangshi.constant.Column;
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

    private static String TABLE_NAME = "";
    private static String PRIMARY_KEY = null;
    private StringBuilder selectSql = new StringBuilder();
    private StringBuilder conditionSql = new StringBuilder();
    private StringBuilder orderSql = new StringBuilder();
    private StringBuilder paginateSql = new StringBuilder();

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String SYSTEM_CREATE_USER_ID = "system_create_user_id";

    @Column(type = ColumnType.DATETIME, length = 0, comment = "")
    public static final String SYSTEM_CREATE_TIME = "system_create_time";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "")
    public static final String SYSTEM_UPDATE_USER_ID = "system_update_user_id";

    @Column(type = ColumnType.DATETIME, length = 0, comment = "")
    public static final String SYSTEM_UPDATE_TIME = "system_update_time";

    @Column(type = ColumnType.INT, length = 5, comment = "")
    public static final String SYSTEM_VERSION = "system_version";

    @Column(type = ColumnType.TINYINT, length = 0, comment = "")
    public static final String SYSTEM_STATUS = "system_status";

    private List<Map<String, Object>> columnList;

    public String getSystem_create_user_id() {
        return getStr(SYSTEM_CREATE_USER_ID);
    }

    public void setSystem_create_user_id(String system_create_user_id) {
        set(SYSTEM_CREATE_USER_ID, system_create_user_id);
    }

    public Date getSystem_create_time() {
        return getDate(SYSTEM_CREATE_TIME);
    }

    public void setSystem_create_time(Date system_create_time) {
        set(SYSTEM_CREATE_TIME, system_create_time);
    }

    public String getSystem_update_user_id() {
        return getStr(SYSTEM_UPDATE_USER_ID);
    }

    public void setSystem_update_user_id(String system_update_user_id) {
        set(SYSTEM_UPDATE_USER_ID, system_update_user_id);
    }

    public Date getSystem_update_time() {
        return getDate(SYSTEM_UPDATE_TIME);
    }

    public void setSystem_update_time(Date system_update_time) {
        set(SYSTEM_UPDATE_TIME, system_update_time);
    }

    public Integer getSystem_version() {
        return getInt(SYSTEM_VERSION);
    }

    public void setSystem_version(Integer system_version) {
        set(SYSTEM_VERSION, system_version);
    }

    public Boolean getSystem_status() {
        return getBoolean(SYSTEM_STATUS);
    }

    public void setSystem_status(Boolean system_status) {
        set(SYSTEM_STATUS, system_status);
    }

    public void setTable_name(String table_name) {
        TABLE_NAME = table_name;
    }

    public String getTable_name() {
        return TABLE_NAME;
    }

    public void setPrimary_key(String primary_key) {
        PRIMARY_KEY = primary_key;
    }

    public String getPrimary_key() {
        return PRIMARY_KEY;
    }

    public void validate(String... keys) {
        for (String key : keys) {
            if (this.getAttrs().containsKey(key)) {
                if (ValidateUtil.isNull(this.get(key))) {
                    throw new RuntimeException(key + " is null");
                }


            } else {
                throw new RuntimeException(key + " is null");
            }
        }
    }

//    public List<Map<String, Object>> getAttributeList() {
//        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//
//        Table table = getTable();
//        Map<String, Class<?>> columnTypeMap = table.getColumnTypeMap();
//
//        for (Map.Entry<String, Class<?>> column : columnTypeMap.entrySet()) {
//            Map<String, Object> map = new HashMap<String, Object>();
//
//            Boolean isExit = false;
//            for (Map.Entry<String, Object> attribute : this.getAttrs().entrySet()) {
//                if (column.getKey().equals(attribute.getKey())) {
//                    map.put("key", attribute.getKey());
//                    map.put("value", attribute.getValue());
//
//                    isExit = true;
//
//                    break;
//                }
//            }
//
//            if (isExit) {
//                list.add(map);
//            }
//        }
//
//        return list;
//    }

//    public List<Map<String, Object>> getAttributeAllList() {
//        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//
//        Table table = getTable();
//        Map<String, Class<?>> columnTypeMap = table.getColumnTypeMap();
//
//        for (Map.Entry<String, Class<?>> column : columnTypeMap.entrySet()) {
//            Map<String, Object> map = new HashMap<String, Object>();
//
//            map.put("key", column.getKey());
//
//            Boolean isExit = false;
//            for (Map.Entry<String, Object> attribute : this.getAttrs().entrySet()) {
//                if (column.getKey().equals(attribute.getKey())) {
//                    map.put("value", attribute.getValue());
//
//                    isExit = true;
//
//                    break;
//                }
//            }
//
//            if (isExit) {
//
//            } else {
//                if (column.getValue().getName().equals(String.class.getName())) {
//                    map.put("value", "");
//                } else if (column.getValue().getName().equals(Boolean.class.getName())) {
//                    map.put("value", true);
//                } else if (column.getValue().getName().equals(BigDecimal.class.getName())) {
//                    map.put("value", BigDecimal.valueOf(0));
//                } else if (column.getValue().getName().equals(Timestamp.class.getName())) {
//                    map.put("value", new Date());
//                } else {
//                    map.put("value", "");
//                }
//            }
//            list.add(map);
//        }
//
//        return list;
//    }

//    public Table getTable() {
//        return TableMapping.me().getTable(getUsefulClass());
//    }
//
//    private Class<? extends com.jfinal.plugin.activerecord.Model> getUsefulClass() {
//        Class c = getClass();
//        return c.getName().indexOf("EnhancerByCGLIB") == -1 ? c : c.getSuperclass();    // com.demo.blog.Blog$$EnhancerByCGLIB$$69a17158
//    }

    public List<Map<String, Object>> getColumnList() {
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

            Map<String, Object> map = new HashMap<String, Object>();
            map.put(Constant.NAME, SYSTEM_CREATE_USER_ID);
            map.put("type", ColumnType.VARCHAR);
            map.put("length", 32);
            map.put("comment", "");
            columnList.add(map);

            map = new HashMap<String, Object>();
            map.put(Constant.NAME, SYSTEM_CREATE_TIME);
            map.put("type", ColumnType.DATETIME);
            map.put("length", 0);
            map.put("comment", "");
            columnList.add(map);

            map = new HashMap<String, Object>();
            map.put(Constant.NAME, SYSTEM_UPDATE_USER_ID);
            map.put("type", ColumnType.VARCHAR);
            map.put("length", 32);
            map.put("comment", "");
            columnList.add(map);

            map = new HashMap<String, Object>();
            map.put(Constant.NAME, SYSTEM_UPDATE_TIME);
            map.put("type", ColumnType.DATETIME);
            map.put("length", 0);
            map.put("comment", "");
            columnList.add(map);

            map = new HashMap<String, Object>();
            map.put(Constant.NAME, SYSTEM_STATUS);
            map.put("type", ColumnType.TINYINT);
            map.put("length", 1);
            map.put("comment", "");
            columnList.add(map);
        }

        return columnList;
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
            stringBuilder.append("'");

            if (is_left_like) {
                stringBuilder.append("%");
            }

            stringBuilder.append(value);

            if (is_right_like) {
                stringBuilder.append("%");
            }

            stringBuilder.append("'");
        } else if (value instanceof Boolean) {
            stringBuilder.append((Boolean) value ? 1 : 0);
        } else {
            stringBuilder.append(value);
        }

        stringBuilder.append(" \n");

        return stringBuilder.toString();
    }

    public M where(String name, Object value) {
        conditionSql = new StringBuilder();
        conditionSql.append("WHERE ");
        conditionSql.append(regexCondition(name, "equal", value));

        return (M) this;
    }

    public M whereLike(String name, String value) {
        conditionSql = new StringBuilder();
        conditionSql.append("WHERE ");
        conditionSql.append(regexCondition(name, "like", value));

        return (M) this;
    }

    public M whereLeftLike(String name, String value) {
        conditionSql = new StringBuilder();
        conditionSql.append("WHERE ");
        conditionSql.append(regexCondition(name, "left_like", value));

        return (M) this;
    }

    public M whereRightLike(String name, String value) {
        conditionSql = new StringBuilder();
        conditionSql.append("WHERE ");
        conditionSql.append(regexCondition(name, "right_like", value));

        return (M) this;
    }

    public M and(String name, String value) {
        conditionSql.append("AND ");
        conditionSql.append(regexCondition(name, "equal", value));

        return (M) this;
    }

    public M or() {


        return (M) this;
    }

    public String getCountSql() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT \n");
        stringBuilder.append("COUNT(*) \n");
        stringBuilder.append("FROM ");
        stringBuilder.append(getTable_name());
        stringBuilder.append(" \n");
        stringBuilder.append(conditionSql);

        return stringBuilder.toString();
    }

    public String getListSql() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT \n");
        stringBuilder.append(getPrimary_key());
        stringBuilder.append(" \n");
        stringBuilder.append("FROM ");
        stringBuilder.append(getTable_name());
        stringBuilder.append(" \n");
        stringBuilder.append(conditionSql);

        return stringBuilder.toString();
    }

    public String getListWithoutCacheSql() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT \n");
        stringBuilder.append("* \n");
        stringBuilder.append("FROM ");
        stringBuilder.append(getTable_name());
        stringBuilder.append(" \n");
        stringBuilder.append(conditionSql);

        return stringBuilder.toString();
    }

    public String getSaveSql() {
        StringBuilder stringBuilder = new StringBuilder();

        StringBuilder stringBuilder2 = new StringBuilder();
        String condition = conditionSql.toString();
        if (condition.equals("")) {
            stringBuilder.append("INSERT INTO ");
            stringBuilder.append(getTable_name());
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
                    } else if (column.get("type").equals(ColumnType.DATETIME)) {
                        stringBuilder2.append("'");
                        stringBuilder2.append(DateUtil.getDateTimeString(new Date()));
                        stringBuilder2.append("'");
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

    public String getUpdateSql() {
        String condition = conditionSql.toString();
        if (condition.equals("")) {
            throw new RuntimeException("sql lost condition");
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

        stringBuilder.append("UPDATE ");
        stringBuilder.append(getTable_name());
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
        stringBuilder.append(conditionSql);

        return stringBuilder.toString();
    }

    private String regexVariable(ColumnType columnType, Object value) {
        StringBuilder stringBuilder = new StringBuilder();

        if (columnType.equals(ColumnType.TINYINT)) {
            stringBuilder.append((Boolean) value ? 1 : 1);
        } else if (columnType.equals(ColumnType.VARCHAR)) {
            stringBuilder.append("'");
            stringBuilder.append(value);
            stringBuilder.append("'");
        } else if (columnType.equals(ColumnType.TEXT)) {
            stringBuilder.append("'");
            stringBuilder.append(value);
            stringBuilder.append("'");
        } else if (columnType.equals(ColumnType.LONGTEXT)) {
            stringBuilder.append("'");
            stringBuilder.append(value);
            stringBuilder.append("'");
        } else if (columnType.equals(ColumnType.DATETIME)) {
            stringBuilder.append("'");
            stringBuilder.append(value);
            stringBuilder.append("'");
        } else {
            stringBuilder.append(value);
        }

        return stringBuilder.toString();
    }

}