package com.nowui.chuangshi.api.app.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;

@Before(SystemInterceptor.class)
@ControllerKey("/system/app")
public class AppController extends Controller {

    @ActionKey("/system/app/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/app/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/app/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/app/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/app/delete")
    public void delete() {

        renderSuccessJson();
    }

}