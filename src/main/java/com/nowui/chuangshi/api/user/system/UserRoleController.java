package com.nowui.chuangshi.api.user.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/user/role")
public class UserRoleController extends Controller {

    @ActionKey("/system/user/role/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/user/role/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/user/role/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/user/role/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/user/role/delete")
    public void delete() {

        renderSuccessJson();
    }

}