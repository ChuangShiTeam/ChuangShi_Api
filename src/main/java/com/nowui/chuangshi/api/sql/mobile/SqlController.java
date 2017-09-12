package com.nowui.chuangshi.api.sql.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/sql")
public class SqlController extends Controller {

    @ActionKey("/mobile/sql/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/sql/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/sql/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/sql/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/sql/delete")
    public void delete() {

        renderSuccessJson();
    }

}