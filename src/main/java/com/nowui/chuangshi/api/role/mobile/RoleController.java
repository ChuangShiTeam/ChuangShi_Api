package com.nowui.chuangshi.api.role.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/role")
public class RoleController extends Controller {

    @ActionKey("/mobile/role/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/role/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/role/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/role/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/role/delete")
    public void delete() {

        renderSuccessJson();
    }

}