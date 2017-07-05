package com.nowui.chuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Config;

import java.util.List;

public class CodeDao extends Dao {

    public List<Record> listByTable_schema(String table_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put("table_schema", Config.table_schema);
        sqlMap.put("table_name", table_name);
        SqlPara sqlPara = Db.getSqlPara("code.listByTable_schema", sqlMap);

        logSql("tables", "listByTable_schema", sqlPara);

        return Db.find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Record> listByTable_name(String table_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put("table_schema", Config.table_schema);
        sqlMap.put("table_name", table_name);
        SqlPara sqlPara = Db.getSqlPara("code.listByTable_name", sqlMap);

        logSql("columns", "listByTable_name", sqlPara);

        return Db.find(sqlPara.getSql(), sqlPara.getPara());
    }

}