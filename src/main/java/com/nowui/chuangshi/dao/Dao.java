package com.nowui.chuangshi.dao;

import java.util.Date;

import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.util.DateUtil;

public class Dao {
    protected void logSql(String sql_table, String sql_action, SqlPara sqlPara) {
        String sql_content = getSql(sqlPara.getSql(), sqlPara.getPara());

        System.out.println(sql_content);

//        Map<String, Object> mqMap = new HashMap<String, Object>();
//        mqMap.put(Sql.APP_ID, "");
//        mqMap.put(Sql.HTTP_ID, "");
//        mqMap.put(Sql.SQL_TABLE, sql_table);
//        mqMap.put(Sql.SQL_ACTION, sql_action);
//        mqMap.put(Sql.SQL_CONTENT, sql_content);
//        mqMap.put(Sql.SYSTEM_CREATE_USER_ID, "");
//        MQUtil.sendSync("sql", JSON.toJSONString(mqMap));
    }

    private String getSql(String sql, Object[] params) {
        // 1 如果没有参数，说明是不是动态SQL语句
        int paramNum = 0;
        if (null != params) {
            paramNum = params.length;
        }
        if (1 > paramNum) {
            return sql;
        }
        // 2 如果有参数，则是动态SQL语句
        StringBuffer returnSQL = new StringBuffer();
        String[] subSQL = sql.split("\\?");
        for (int i = 0; i < paramNum; i++) {
            if (params[i] instanceof Date) {
                returnSQL.append(subSQL[i]).append("'").append(DateUtil.getDateTimeString((java.util.Date) params[i])).append("'");
            } else if (params[i] instanceof Integer) {
                returnSQL.append(subSQL[i]).append(params[i]);
            } else {
                returnSQL.append(subSQL[i]).append("'").append(params[i]).append("'");
            }
        }

        if (subSQL.length > params.length) {
            returnSQL.append(subSQL[subSQL.length - 1]);
        }
        return returnSQL.toString();
    }

}
