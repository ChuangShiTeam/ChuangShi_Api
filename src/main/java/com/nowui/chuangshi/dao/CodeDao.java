package com.nowui.chuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Config;

import java.util.List;

public class CodeDao extends Dao {

    public List<Record> listByTable_schema(String table_name, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put("table_schema", Config.table_schema);
        sqlMap.put("table_name", table_name);
        SqlPara sqlPara = Db.getSqlPara("code.listByTable_schema", sqlMap);

        logSql(request_app_id, request_http_id, "tables", "listByTable_schema", sqlPara, request_user_id);

        return Db.find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Record> listByTable_name(String table_name, String request_app_id, String request_http_id, String request_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put("table_schema", Config.table_schema);
        sqlMap.put("table_name", table_name);
        SqlPara sqlPara = Db.getSqlPara("code.listByTable_name", sqlMap);

        logSql(request_app_id, request_http_id, "columns", "listByTable_name", sqlPara, request_user_id);

        return Db.find(sqlPara.getSql(), sqlPara.getPara());
    }

}