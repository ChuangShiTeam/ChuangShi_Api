package com.nowui.chuangshi.api.sql.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/sql")
public class SqlController extends Controller {

    @ActionKey("/system/sql/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/sql/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/sql/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/sql/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/sql/delete")
    public void delete() {

        renderSuccessJson();
    }

}