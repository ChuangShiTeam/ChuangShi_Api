package com.nowui.chuangshi.service;

import com.jfinal.plugin.activerecord.Record;
import com.nowui.chuangshi.cache.CodeCache;

import java.util.List;

public class CodeService extends Service {

    private CodeCache codeCache = new CodeCache();

    public List<Record> listByTable_schema(String table_name) {
        return codeCache.listByTable_schema(table_name);
    }

    public List<Record> listByTable_name(String table_name) {
        return codeCache.listByTable_name(table_name);
    }

}