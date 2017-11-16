package com.nowui.chuangshi.api.app.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/app/config")
public class AppConfigController extends Controller {

    @ActionKey("/system/app/config/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/app/config/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/app/config/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/app/config/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/app/config/delete")
    public void delete() {

        renderSuccessJson();
    }

}