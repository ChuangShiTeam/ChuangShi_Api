package com.nowui.chuangshi.api.sql.admin;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.sql.model.Sql;
import com.nowui.chuangshi.api.sql.service.SqlService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;


@ControllerKey("/admin/sql")
public class SqlController extends Controller {

    @ActionKey("/admin/sql/list")
    public void list() {
        validateRequest(Sql.SQL_TABLE, Sql.SQL_ACTION, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Sql model = getModel(Sql.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = SqlService.instance.adminCount(request_app_id, model.getSql_table(), model.getSql_action());
        List<Sql> resultList = SqlService.instance.adminList(request_app_id, model.getSql_table(), model.getSql_action(), getM(), getN());

        validateResponse(Sql.SQL_ID, Sql.SQL_TABLE, Sql.SQL_ACTION, Sql.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/sql/find")
    public void find() {
        validateRequest(Sql.SQL_ID);

        Sql model = getModel(Sql.class);

        Sql result = SqlService.instance.find(model.getSql_id());

        validateResponse(Sql.HTTP_ID, Sql.SQL_TABLE, Sql.SQL_ACTION, Sql.SQL_CONTENT, Sql.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/sql/save")
    public void save() {
        validateRequest(Sql.HTTP_ID, Sql.SQL_TABLE, Sql.SQL_ACTION, Sql.SQL_CONTENT);

        Sql model = getModel(Sql.class);
        model.setSql_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = SqlService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/sql/update")
    public void update() {
        validateRequest(Sql.SQL_ID, Sql.HTTP_ID, Sql.SQL_TABLE, Sql.SQL_ACTION, Sql.SQL_CONTENT, Sql.SYSTEM_VERSION);

        Sql model = getModel(Sql.class);
        String request_user_id = getRequest_user_id();

        Boolean result = SqlService.instance.update(model, model.getSql_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/sql/delete")
    public void delete() {
        validateRequest(Sql.SQL_ID, Sql.SYSTEM_VERSION);

        Sql model = getModel(Sql.class);
        String request_user_id = getRequest_user_id();

        Boolean result = SqlService.instance.delete(model.getSql_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}