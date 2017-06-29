package com.nowui.chuangshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.jfinal.kit.Kv;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.template.Engine;
import com.jfinal.template.Template;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.service.CodeService;
import com.nowui.chuangshi.util.Util;
import com.nowui.chuangshi.util.ValidateUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CodeController extends Controller {

    private static Engine engine = Engine.create("engine");
    private final CodeService codeService = new CodeService();

    @ActionKey(Url.CODE_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();

        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        List<Record> recordList = codeService.listByTable_schema(request_app_id, request_http_id, request_user_id);

        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();

        for (Record record : recordList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("table_name", record.getStr("table_name"));
            resultList.add(map);
        }

        renderSuccessJson(recordList.size(), resultList);
    }

    @ActionKey(Url.CODE_ADMIN_SAVE)
    public void adminSave() {
        JSONObject jsonObject = getParameterJSONObject();

        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();
        String table_name = jsonObject.getString("table_name");

        authenticateRequest_app_idAndRequest_user_id();

        save(table_name, request_app_id, request_http_id, request_user_id);

        renderSuccessJson();
    }

    @ActionKey(Url.CODE_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();

        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        List<Record> recordList = codeService.listByTable_schema(request_app_id, request_http_id, request_user_id);

        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();

        for (Record record : recordList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("table_name", record.getStr("table_name"));
            resultList.add(map);
        }

        renderSuccessJson(recordList.size(), resultList);
    }

    @ActionKey(Url.CODE_SYSTEM_SAVE)
    public void systemSave() {
        JSONObject jsonObject = getParameterJSONObject();

        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();
        String table_name = jsonObject.getString("table_name");

        save(table_name, request_app_id, request_http_id, request_user_id);

        renderSuccessJson();
    }

    private void save(String table_name, String request_app_id, String request_http_id, String request_user_id) {
        List<Record> codeList = codeService.listByTable_name(table_name, request_app_id, request_http_id, request_user_id);

        List<Record> columnList = new ArrayList<Record>();

        String lower_model_name = table_name.replace("table_", "").toLowerCase();
        String upper_model_name = lower_model_name.toUpperCase();
        String first_upper_model_name = lower_model_name.substring(0, 1).toUpperCase() + lower_model_name.substring(1);
        String first_lower_model_name = lower_model_name.substring(0, 1).toLowerCase() + lower_model_name.substring(1);
        String column_key = "lower_model_name" + "_id";

        first_upper_model_name = check(first_upper_model_name);
        first_lower_model_name = check(first_lower_model_name);

        for (Record record : codeList) {
            if (!record.getStr("column_name").startsWith("system_")) {
                if (ValidateUtil.isNullOrEmpty(record.get("character_maximum_length"))) {
                    String length = record.getStr("column_type").replace(record.get("data_type").toString(), "").replace("(", "").replace(")", "");

                    if (length.equals("") || length.contains(",")) {
                        length = "0";
                    }

                    record.set("character_maximum_length", length);
                }

                if (record.getStr("column_key").equals("PRI")) {
                    column_key = record.getStr("column_name");
                }

                record.set("first_column_name", record.getStr("column_name").substring(0, 1).toUpperCase() + record.getStr("column_name").substring(1));
                record.set("data_type", record.getStr("data_type").toUpperCase());
                record.set("upper_column_name", record.getStr("column_name").toUpperCase());

                columnList.add(record);
            }
        }

//        if (first_upper_model_name.contains("_")) {
//            int index = first_upper_model_name.indexOf("_");
//            first_upper_model_name = first_upper_model_name.substring(0, index) + first_upper_model_name.substring(index + 1, index + 2).toUpperCase() + first_upper_model_name.substring(index + 2);
//        }


        engine.setBaseTemplatePath(PathKit.getWebRootPath() + "/WEB-INF/template/");

        write(engine, column_key, lower_model_name, upper_model_name, first_upper_model_name, first_lower_model_name, columnList, "url.template", "Url.java");
        write(engine, column_key, lower_model_name, upper_model_name, first_upper_model_name, first_lower_model_name, columnList, "model.template", first_upper_model_name + ".java");
        write(engine, column_key, lower_model_name, upper_model_name, first_upper_model_name, first_lower_model_name, columnList, "dao.template", first_upper_model_name + "Dao.java");
        write(engine, column_key, lower_model_name, upper_model_name, first_upper_model_name, first_lower_model_name, columnList, "service.template", first_upper_model_name + "Service.java");
        write(engine, column_key, lower_model_name, upper_model_name, first_upper_model_name, first_lower_model_name, columnList, "cache.template", first_upper_model_name + "Cache.java");
        write(engine, column_key, lower_model_name, upper_model_name, first_upper_model_name, first_lower_model_name, columnList, "controller.template", first_upper_model_name + "Controller.java");
        write(engine, column_key, lower_model_name, upper_model_name, first_upper_model_name, first_lower_model_name, columnList, "config.template", "WebConfig.java");
        write(engine, column_key, lower_model_name, upper_model_name, first_upper_model_name, first_lower_model_name, columnList, "state.template", lower_model_name + ".js");
        write(engine, column_key, lower_model_name, upper_model_name, first_upper_model_name, first_lower_model_name, columnList, "index.template", first_upper_model_name + "Index.js");
        write(engine, column_key, lower_model_name, upper_model_name, first_upper_model_name, first_lower_model_name, columnList, "detail.template", first_upper_model_name + "Detail.js");
        write(engine, column_key, lower_model_name, upper_model_name, first_upper_model_name, first_lower_model_name, columnList, "router.template", "Router.js");
        write(engine, column_key, lower_model_name, upper_model_name, first_upper_model_name, first_lower_model_name, columnList, "app.template", "index.js");
        write(engine, column_key, lower_model_name, upper_model_name, first_upper_model_name, first_lower_model_name, columnList, "sql.template", first_upper_model_name + ".sql");

    }

    private String check(String name) {
        if (name.contains("_")) {
            int index = name.indexOf("_");
            name = name.substring(0, index) + name.substring(index + 1, index + 2).toUpperCase() + name.substring(index + 2);

            if (name.contains("_")) {
                name = check(name);
            }
        }

        return name;
    }

    private void write(Engine engine, String column_key, String lower_model_name, String upper_model_name, String first_upper_model_name, String first_lower_model_name, List<Record> columnList, String templateName, String fileName) {
        String first_upper_column_key = column_key.substring(0, 1).toUpperCase() + column_key.substring(1);
        String upper_column_key = column_key.toUpperCase();
        String url_model_name = lower_model_name.replaceAll("_", "/");

        Kv templateMap = Kv.create();
        templateMap.put("column_key", column_key);
        templateMap.put("first_upper_column_key", first_upper_column_key);
        templateMap.put("upper_column_key", upper_column_key);
        templateMap.put("lower_model_name", lower_model_name);
        templateMap.put("url_model_name", url_model_name);
        templateMap.put("upper_model_name", upper_model_name);
        templateMap.put("first_upper_model_name", first_upper_model_name);
        templateMap.put("first_lower_model_name", first_lower_model_name);
        templateMap.put("columnList", columnList);

        Template template = engine.getTemplate(templateName);

        String result = template.renderToString(templateMap);

        try {
            Writer writer = new FileWriter(new File("/Users/yongqiangzhong/Documents/Publish/" + fileName), false);
            writer.write(result.toCharArray());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}