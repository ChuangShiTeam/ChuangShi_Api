package com.nowui.chuangshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.jfinal.kit.Kv;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.template.Engine;
import com.jfinal.template.Template;
import com.nowui.chuangshi.constant.Config;
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
        JSONObject jsonObject = getParameterJSONObject();
        String table_name = jsonObject.getString("table_name");

        authenticateRequest_app_idAndRequest_user_id();

        List<Record> recordList = codeService.listByTable_schema(table_name, request_app_id, request_http_id, request_user_id);

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
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();
        JSONObject jsonObject = getParameterJSONObject();
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
        JSONObject jsonObject = getParameterJSONObject();
        String table_name = jsonObject.getString("table_name");


        List<Record> recordList = codeService.listByTable_schema(table_name, request_app_id, request_http_id, request_user_id);

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
        String first_upper_model_name_without_underline = removeUnderline(first_upper_model_name);
        String first_lower_model_name_without_underline = removeUnderline(first_lower_model_name);
        String column_key = lower_model_name + "_id";

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


        engine.setBaseTemplatePath(PathKit.getWebRootPath() + "/WEB-INF/template/");

        write(engine, column_key, lower_model_name, upper_model_name, first_upper_model_name, first_lower_model_name, first_upper_model_name_without_underline, first_lower_model_name_without_underline, columnList, "url.template", "Url.java");
        write(engine, column_key, lower_model_name, upper_model_name, first_upper_model_name, first_lower_model_name, first_upper_model_name_without_underline, first_lower_model_name_without_underline, columnList, "model.template", first_upper_model_name_without_underline + ".java");
        write(engine, column_key, lower_model_name, upper_model_name, first_upper_model_name, first_lower_model_name, first_upper_model_name_without_underline, first_lower_model_name_without_underline, columnList, "dao.template", first_upper_model_name_without_underline + "Dao.java");
        write(engine, column_key, lower_model_name, upper_model_name, first_upper_model_name, first_lower_model_name, first_upper_model_name_without_underline, first_lower_model_name_without_underline, columnList, "service.template", first_upper_model_name_without_underline + "Service.java");
        write(engine, column_key, lower_model_name, upper_model_name, first_upper_model_name, first_lower_model_name, first_upper_model_name_without_underline, first_lower_model_name_without_underline, columnList, "cache.template", first_upper_model_name_without_underline + "Cache.java");
        write(engine, column_key, lower_model_name, upper_model_name, first_upper_model_name, first_lower_model_name, first_upper_model_name_without_underline, first_lower_model_name_without_underline, columnList, "controller.template", first_upper_model_name_without_underline + "Controller.java");
        write(engine, column_key, lower_model_name, upper_model_name, first_upper_model_name, first_lower_model_name, first_upper_model_name_without_underline, first_lower_model_name_without_underline, columnList, "config.template", "WebConfig.java");
        write(engine, column_key, lower_model_name, upper_model_name, first_upper_model_name, first_lower_model_name, first_upper_model_name_without_underline, first_lower_model_name_without_underline, columnList, "state.template", lower_model_name + ".js");
        write(engine, column_key, lower_model_name, upper_model_name, first_upper_model_name, first_lower_model_name, first_upper_model_name_without_underline, first_lower_model_name_without_underline, columnList, "index.template", first_upper_model_name_without_underline + "Index.js");
        write(engine, column_key, lower_model_name, upper_model_name, first_upper_model_name, first_lower_model_name, first_upper_model_name_without_underline, first_lower_model_name_without_underline, columnList, "detail.template", first_upper_model_name_without_underline + "Detail.js");
        write(engine, column_key, lower_model_name, upper_model_name, first_upper_model_name, first_lower_model_name, first_upper_model_name_without_underline, first_lower_model_name_without_underline, columnList, "router.template", "Router.js");
        write(engine, column_key, lower_model_name, upper_model_name, first_upper_model_name, first_lower_model_name, first_upper_model_name_without_underline, first_lower_model_name_without_underline, columnList, "app.template", "index.js");
        write(engine, column_key, lower_model_name, upper_model_name, first_upper_model_name, first_lower_model_name, first_upper_model_name_without_underline, first_lower_model_name_without_underline, columnList, "sql.template", first_upper_model_name_without_underline + ".sql");

    }

    private String removeUnderline(String name) {
        if (name.contains("_")) {
            int index = name.indexOf("_");
            name = name.substring(0, index) + name.substring(index + 1, index + 2).toUpperCase() + name.substring(index + 2);

            if (name.contains("_")) {
                name = removeUnderline(name);
            }
        }

        return name;
    }

    private void write(Engine engine, String column_key, String lower_model_name, String upper_model_name, String first_upper_model_name, String first_lower_model_name, String first_upper_model_name_without_underline, String first_lower_model_name_without_underline, List<Record> columnList, String templateName, String fileName) {
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
        templateMap.put("first_upper_model_name_without_underline", first_upper_model_name_without_underline);
        templateMap.put("first_lower_model_name_without_underline", first_lower_model_name_without_underline);
        templateMap.put("columnList", columnList);

        Template template = engine.getTemplate(templateName);

        String result = template.renderToString(templateMap);

        try {
            Writer writer = new FileWriter(new File(Config.code_generate_url + fileName), false);
            writer.write(result.toCharArray());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}