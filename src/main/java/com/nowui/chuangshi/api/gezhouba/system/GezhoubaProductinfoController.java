package com.nowui.chuangshi.api.gezhouba.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;

@Before(SystemInterceptor.class)
@ControllerKey("/system/gezhouba/productinfo")
public class GezhoubaProductinfoController extends Controller {

    @ActionKey("/system/gezhouba/productinfo/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/gezhouba/productinfo/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/gezhouba/productinfo/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/gezhouba/productinfo/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/gezhouba/productinfo/delete")
    public void delete() {

        renderSuccessJson();
    }

}