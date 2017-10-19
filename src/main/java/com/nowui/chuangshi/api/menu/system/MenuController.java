package com.nowui.chuangshi.api.menu.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;

@Before(SystemInterceptor.class)
@ControllerKey("/system/menu")
public class MenuController extends Controller {

    @ActionKey("/system/menu/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/menu/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/menu/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/menu/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/menu/delete")
    public void delete() {

        renderSuccessJson();
    }

}