package com.nowui.chuangshi.api.role.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/role/menu")
public class RoleMenuController extends Controller {

    @ActionKey("/system/role/menu/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/role/menu/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/role/menu/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/role/menu/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/role/menu/delete")
    public void delete() {

        renderSuccessJson();
    }

}