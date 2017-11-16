package com.nowui.chuangshi.api.app.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/app/config/category")
public class AppConfigCategoryController extends Controller {

    @ActionKey("/system/app/config/category/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/app/config/category/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/app/config/category/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/app/config/category/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/app/config/category/delete")
    public void delete() {

        renderSuccessJson();
    }

}