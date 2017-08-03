package com.nowui.chuangshi.api.code.admin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.jfinal.kit.Kv;
import com.jfinal.kit.PathKit;
import com.jfinal.template.Engine;
import com.jfinal.template.Template;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.constant.Config;
import com.nowui.chuangshi.util.FileUtil;
import com.nowui.chuangshi.util.ValidateUtil;

import java.util.ArrayList;
import java.util.List;

@ControllerKey("/admin/code")
public class CodeController extends Controller {

    private static Engine engine = Engine.create("code_engine");

    @ActionKey("/admin/code/save")
    public void save() {
        validateRequest("table_name", "package_name", "list");

        JSONObject jsonObject = getParameterJSONObject();

        String table_name = jsonObject.getString("table_name");
        String package_name = jsonObject.getString("package_name");
        JSONArray jsonArray = jsonObject.getJSONArray("list");

        List<JSONObject> columnList = new ArrayList<JSONObject>();
        List<JSONObject> searchColumnList = new ArrayList<JSONObject>();
        List<JSONObject> listColumnList = new ArrayList<JSONObject>();
        List<JSONObject> detailColumnList = new ArrayList<JSONObject>();

        try {
            String codeTemplatePath = CodeController.class.getResource("/").toURI().getPath();
            java.io.File parentFile = new java.io.File(codeTemplatePath).getParentFile();
            java.io.File[] files = parentFile.listFiles();
            Boolean isLocal = true;
            for (java.io.File file : files) {
                if (file.isDirectory() && file.getName().equals("sql")) {
                    isLocal = false;
                }
            }

            if (isLocal) {
                parentFile = new java.io.File(PathKit.getWebRootPath()).getParentFile();
                codeTemplatePath = parentFile.getPath();
            } else {
                codeTemplatePath = parentFile.getPath();
            }


            engine.setBaseTemplatePath(codeTemplatePath + "/resources/template/");
        } catch (java.lang.Exception e) {

        }

        String lower_model_name = table_name.replace("table_", "").toLowerCase();
        String upper_model_name = lower_model_name.toUpperCase();
        String first_upper_model_name = lower_model_name.substring(0, 1).toUpperCase() + lower_model_name.substring(1);
        String first_lower_model_name = lower_model_name.substring(0, 1).toLowerCase() + lower_model_name.substring(1);
        String first_upper_model_name_without_underline = removeUnderline(first_upper_model_name);
        String first_lower_model_name_without_underline = removeUnderline(first_lower_model_name);
        String primary_key = "";

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject record = jsonArray.getJSONObject(i);

            String column_name = record.getString("column_name");
            String data_type = record.getString("data_type");

            if (ValidateUtil.isNullOrEmpty(record.get("character_maximum_length"))) {
                String length = record.getString("column_type").replace(data_type, "")
                        .replace("(", "").replace(")", "");

                if (length.equals("") || length.contains(",")) {
                    length = "0";
                }

                record.put("character_maximum_length", length);
            } else {
                if (data_type.equals("longtext")) {
                    record.put("character_maximum_length", "0");
                }
            }

            if (record.getString("column_key").equals("PRI")) {
                primary_key = column_name;
            }

            record.put("first_column_name", column_name.substring(0, 1).toUpperCase() + column_name.substring(1));
            record.put("data_type", data_type.toUpperCase());
            record.put("upper_column_name", column_name.toUpperCase());

            columnList.add(record);

            if (record.getBoolean("is_search")) {
                searchColumnList.add(record);
            }

            if (record.getBoolean("is_list")) {
                listColumnList.add(record);
            }

            if (record.getBoolean("is_detail")) {
                detailColumnList.add(record);
            }
        }
        String first_upper_primary_key = primary_key.substring(0, 1).toUpperCase() + primary_key.substring(1);
        String upper_primary_key = primary_key.toUpperCase();
        String url_model_name = lower_model_name.replaceAll("_", "/");

        Kv templateMap = Kv.create();
        templateMap.put("package_name", package_name);
        templateMap.put("table_name", table_name);
        templateMap.put("primary_key", primary_key);
        templateMap.put("first_upper_primary_key", first_upper_primary_key);
        templateMap.put("upper_primary_key", upper_primary_key);
        templateMap.put("lower_model_name", lower_model_name);
        templateMap.put("url_model_name", url_model_name);
        templateMap.put("upper_model_name", upper_model_name);
        templateMap.put("first_upper_model_name", first_upper_model_name);
        templateMap.put("first_lower_model_name", first_lower_model_name);
        templateMap.put("first_upper_model_name_without_underline", first_upper_model_name_without_underline);
        templateMap.put("first_lower_model_name_without_underline", first_lower_model_name_without_underline);
        templateMap.put("columnList", columnList);
        templateMap.put("searchColumnList", searchColumnList);
        templateMap.put("listColumnList", listColumnList);
        templateMap.put("detailColumnList", detailColumnList);

        FileUtil.createPath(Config.code_generate_url + "sql");
        FileUtil.createPath(Config.code_generate_url + "model");
        FileUtil.createPath(Config.code_generate_url + "dao");
        FileUtil.createPath(Config.code_generate_url + "cache");
        FileUtil.createPath(Config.code_generate_url + "service");
        FileUtil.createPath(Config.code_generate_url + "mobile");
        FileUtil.createPath(Config.code_generate_url + "admin");
        FileUtil.createPath(Config.code_generate_url + "system");

        write(templateMap,"sql.template", "sql/" + first_upper_model_name_without_underline + ".sql");
        write(templateMap,"model.template", "model/" + first_upper_model_name_without_underline + ".java");
        write(templateMap,"dao.template", "dao/" + first_upper_model_name_without_underline + "Dao.java");
        write(templateMap,"cache.template", "cache/" + first_upper_model_name_without_underline + "Cache.java");
        write(templateMap,"service.template", "service/" + first_upper_model_name_without_underline + "Service.java");
        write(templateMap,"mobile.template", "mobile/" + first_upper_model_name_without_underline + "Controller.java");
        write(templateMap,"admin.template", "admin/" + first_upper_model_name_without_underline + "Controller.java");
        write(templateMap,"system.template", "system/" + first_upper_model_name_without_underline + "Controller.java");
        write(templateMap,"state.template", lower_model_name + ".js");
        write(templateMap,"index.template", first_upper_model_name_without_underline + "Index.js");
        write(templateMap,"detail.template", first_upper_model_name_without_underline + "Detail.js");
        write(templateMap,"router.template", "router.js");
        write(templateMap,"app.template", "index.js");

        renderSuccessJson();
    }

    private String removeUnderline(String name) {
        if (name.contains("_")) {
            int index = name.indexOf("_");
            name = name.substring(0, index) + name.substring(index + 1, index + 2).toUpperCase()  + name.substring(index + 2);

            if (name.contains("_")) {
                name = removeUnderline(name);
            }
        }

        return name;
    }

    private void write(Kv templateMap, String templateName, String file_path) {

        Template template = engine.getTemplate(templateName);

        String content = template.renderToString(templateMap);

        FileUtil.writeFile(content, Config.code_generate_url + file_path);
    }

}
