package com.nowui.chuangshi.cache;

import com.jfinal.plugin.activerecord.Record;
import com.nowui.chuangshi.dao.CodeDao;

import java.util.List;

public class CodeCache extends Cache {

    private CodeDao codeDao = new CodeDao();

    public List<Record> listByTable_schema(String request_app_id, String request_http_id, String request_user_id) {
        return codeDao.listByTable_schema(request_app_id, request_http_id, request_user_id);
    }

    public List<Record> listByTable_name(String table_name, String request_app_id, String request_http_id, String request_user_id) {
        return codeDao.listByTable_name(table_name, request_app_id, request_http_id, request_user_id);
    }

}