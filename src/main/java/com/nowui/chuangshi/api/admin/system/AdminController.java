package com.nowui.chuangshi.api.admin.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/admin")
public class AdminController extends Controller {

    @ActionKey("/system/admin/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/admin/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/admin/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/admin/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/admin/delete")
    public void delete() {

        renderSuccessJson();
    }

}