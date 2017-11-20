package com.nowui.chuangshi.api.role.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/role/menu")
public class RoleMenuController extends Controller {

    @ActionKey("/mobile/role/menu/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/role/menu/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/role/menu/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/role/menu/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/role/menu/delete")
    public void delete() {

        renderSuccessJson();
    }

}