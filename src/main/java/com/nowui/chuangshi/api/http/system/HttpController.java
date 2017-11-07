package com.nowui.chuangshi.api.http.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/http")
public class HttpController extends Controller {

    @ActionKey("/system/http/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/http/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/http/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/http/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/http/delete")
    public void delete() {

        renderSuccessJson();
    }

}