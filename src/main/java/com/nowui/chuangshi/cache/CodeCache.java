package com.nowui.chuangshi.cache;

import com.jfinal.plugin.activerecord.Record;
import com.nowui.chuangshi.dao.CodeDao;

import java.util.List;

public class CodeCache extends Cache {

    private CodeDao codeDao = new CodeDao();

    public List<Record> listByTable_schema(String table_name) {
        return codeDao.listByTable_schema(table_name);
    }

    public List<Record> listByTable_name(String table_name) {
        return codeDao.listByTable_name(table_name);
    }

}