package com.nowui.chuangshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.Sql;
import com.nowui.chuangshi.service.SqlService;
import com.nowui.chuangshi.util.Util;

import java.util.List;

public class SqlController extends Controller {

    private final SqlService sqlService = new SqlService();

    @ActionKey(Url.SQL_LIST)
    public void list() {
        validateRequest_app_id();
        validate(Constant.PAGE_SIZE, Constant.FIRST_CREATE_TIME, Constant.LAST_CREATE_TIME);

        String request_app_id = getRequest_app_id();
        JSONObject jsonObject = getParameterJSONObject();

        List<Sql> resultList = sqlService.listByApp_idAndSystem_create_timeAndLimit(request_app_id, jsonObject.getDate(Constant.LAST_CREATE_TIME), 0, getN());

        for (Sql result : resultList) {
            result.keep(Sql.SQL_ID, Sql.SYSTEM_VERSION);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.SQL_FIND)
    public void find() {
        validateRequest_app_id();
        validate(Sql.SQL_ID);

        Sql model = getModel(Sql.class);

        Sql sql = sqlService.findBySql_id(model.getSql_id());

        authenticateRequest_app_idAndRequest_user_id();
        authenticateApp_id(sql.getApp_id());
        authenticateSystem_create_user_id(sql.getSystem_create_user_id());

        sql.keep(Sql.SQL_ID, Sql.SYSTEM_VERSION);

        renderSuccessJson(sql);
    }

    @ActionKey(Url.SQL_SAVE)
    public void save() {
        validateRequest_app_id();
        validate(Sql.HTTP_ID, Sql.SQL_TABLE, Sql.SQL_ACTION, Sql.SQL_CONTENT);

        authenticateRequest_app_idAndRequest_user_id();

        Sql model = getModel(Sql.class);
        String sql_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        Boolean result = sqlService.save(sql_id, request_app_id, request_http_id, model.getSql_table(), model.getSql_action(), model.getSql_content(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.SQL_UPDATE)
    public void update() {
        validateRequest_app_id();
        validate(Sql.SQL_ID, Sql.HTTP_ID, Sql.SQL_TABLE, Sql.SQL_ACTION, Sql.SQL_CONTENT, Sql.SYSTEM_VERSION);

        Sql model = getModel(Sql.class);
        String request_user_id = getRequest_user_id();

        Sql sql = sqlService.findBySql_id(model.getSql_id());
        authenticateRequest_app_idAndRequest_user_id();
        authenticateApp_id(sql.getApp_id());
        authenticateSystem_create_user_id(sql.getSystem_create_user_id());

        Boolean result = sqlService.updateValidateSystem_version(model.getSql_id(), model.getSql_table(), model.getSql_action(), model.getSql_content(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.SQL_DELETE)
    public void delete() {
        validateRequest_app_id();
        validate(Sql.SQL_ID, Sql.SYSTEM_VERSION);

        Sql model = getModel(Sql.class);
        String request_user_id = getRequest_user_id();

        Sql sql = sqlService.findBySql_id(model.getSql_id());
        authenticateApp_id(sql.getApp_id());
        authenticateSystem_create_user_id(sql.getSystem_create_user_id());

        Boolean result = sqlService.deleteBySql_idAndSystem_update_user_idValidateSystem_version(model.getSql_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.SQL_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        String request_app_id = getRequest_app_id();

        Integer total = sqlService.countByApp_id(request_app_id);
        List<Sql> resultList = sqlService.listByApp_idAndLimit(request_app_id, getM(), getN());

        for (Sql result : resultList) {
            result.keep(Sql.SQL_ID, Sql.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.SQL_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(Sql.SQL_ID);

        Sql model = getModel(Sql.class);

        Sql sql = sqlService.findBySql_id(model.getSql_id());

        authenticateRequest_app_idAndRequest_user_id();
        authenticateApp_id(sql.getApp_id());

        sql.keep(Sql.SQL_ID, Sql.SYSTEM_VERSION);

        renderSuccessJson(sql);
    }

    @ActionKey(Url.SQL_ADMIN_SAVE)
    public void adminSave() {
        save();
    }

    @ActionKey(Url.SQL_ADMIN_UPDATE)
    public void adminUpdate() {
        validateRequest_app_id();
        validate(Sql.SQL_ID, Sql.HTTP_ID, Sql.SQL_TABLE, Sql.SQL_ACTION, Sql.SQL_CONTENT, Sql.SYSTEM_VERSION);

        Sql model = getModel(Sql.class);
        String request_user_id = getRequest_user_id();

        Sql sql = sqlService.findBySql_id(model.getSql_id());
        authenticateRequest_app_idAndRequest_user_id();
        authenticateApp_id(sql.getApp_id());

        Boolean result = sqlService.updateValidateSystem_version(model.getSql_id(), model.getSql_table(), model.getSql_action(), model.getSql_content(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.SQL_ADMIN_DELETE)
    public void adminDelete() {
        validateRequest_app_id();
        validate(Sql.SQL_ID, Sql.SYSTEM_VERSION);

        Sql model = getModel(Sql.class);
        String request_user_id = getRequest_user_id();

        Sql sql = sqlService.findBySql_id(model.getSql_id());
        authenticateApp_id(sql.getApp_id());

        Boolean result = sqlService.deleteBySql_idAndSystem_update_user_idValidateSystem_version(model.getSql_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.SQL_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(Sql.APP_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Sql model = getModel(Sql.class);

        Integer total = sqlService.countByOrApp_id(model.getApp_id());
        List<Sql> resultList = sqlService.listByOrApp_idAndLimit(model.getApp_id(), getM(), getN());

        for (Sql result : resultList) {
            result.keep(Sql.SQL_ID, Sql.SQL_TABLE, Sql.SQL_ACTION, Sql.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.SQL_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(Sql.SQL_ID);

        Sql model = getModel(Sql.class);

        Sql sql = sqlService.findBySql_id(model.getSql_id());

        sql.keep(Sql.SQL_ID, Sql.SQL_TABLE, Sql.SQL_ACTION, Sql.SQL_CONTENT, Sql.SYSTEM_VERSION);

        renderSuccessJson(sql);
    }

    @ActionKey(Url.SQL_SYSTEM_SAVE)
    public void systemSave() {
        save();
    }

    @ActionKey(Url.SQL_SYSTEM_UPDATE)
    public void systemUpdate() {
        validateRequest_app_id();
        validate(Sql.SQL_ID, Sql.HTTP_ID, Sql.SQL_TABLE, Sql.SQL_ACTION, Sql.SQL_CONTENT, Sql.SYSTEM_VERSION);

        Sql model = getModel(Sql.class);
        String request_user_id = getRequest_user_id();

        Boolean result = sqlService.updateValidateSystem_version(model.getSql_id(), model.getSql_table(), model.getSql_action(), model.getSql_content(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.SQL_SYSTEM_DELETE)
    public void systemDelete() {
        validateRequest_app_id();
        validate(Sql.SQL_ID, Sql.SYSTEM_VERSION);

        Sql model = getModel(Sql.class);
        String request_user_id = getRequest_user_id();

        Boolean result = sqlService.deleteBySql_idAndSystem_update_user_idValidateSystem_version(model.getSql_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}