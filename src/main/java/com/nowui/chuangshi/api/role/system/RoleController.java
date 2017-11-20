package com.nowui.chuangshi.api.role.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/role")
public class RoleController extends Controller {

    @ActionKey("/system/role/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/role/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/role/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/role/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/role/delete")
    public void delete() {

        renderSuccessJson();
    }

}