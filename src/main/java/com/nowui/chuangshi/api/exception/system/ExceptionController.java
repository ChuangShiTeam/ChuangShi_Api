package com.nowui.chuangshi.api.exception.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/exception")
public class ExceptionController extends Controller {

    @ActionKey("/system/exception/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/exception/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/exception/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/exception/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/exception/delete")
    public void delete() {

        renderSuccessJson();
    }

}