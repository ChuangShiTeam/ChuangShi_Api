package com.nowui.chuangshi.controller;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.Sql;
import com.nowui.chuangshi.service.SqlService;

import java.util.List;

public class SqlController extends Controller {

    private final SqlService sqlService = new SqlService();

    @ActionKey(Url.SQL_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        String request_app_id = getRequest_app_id();

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = sqlService.countByApp_id(request_app_id);
        List<Sql> resultList = sqlService.listByApp_idAndLimit(request_app_id, getM(), getN());

        for (Sql result : resultList) {
            result.keep(Sql.SQL_ID, Sql.SQL_TABLE, Sql.SQL_ACTION, Sql.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.SQL_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(Sql.SQL_ID);

        Sql model = getModel(Sql.class);

        authenticateRequest_app_idAndRequest_user_id();

        Sql sql = sqlService.findBySql_id(model.getSql_id());

        authenticateApp_id(sql.getApp_id());

        sql.keep(Sql.SQL_ID, Sql.SYSTEM_VERSION);

        renderSuccessJson(sql);
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

}