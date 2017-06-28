package com.nowui.chuangshi.service;

import com.jfinal.plugin.activerecord.Record;
import com.nowui.chuangshi.cache.CodeCache;

import java.util.List;

public class CodeService extends Service {

    private CodeCache codeCache = new CodeCache();

    public List<Record> listByTable_schema(String request_app_id, String request_http_id, String request_user_id) {
        return codeCache.listByTable_schema(request_app_id, request_http_id, request_user_id);
    }

    public List<Record> listByTable_name(String table_name, String request_app_id, String request_http_id, String request_user_id) {
        return codeCache.listByTable_name(table_name, request_app_id, request_http_id, request_user_id);
    }

}