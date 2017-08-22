package com.nowui.chuangshi.api.website.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;

@Before(SystemInterceptor.class)
@ControllerKey("/system/website/menu")
public class WebsiteMenuController extends Controller {

    @ActionKey("/system/website/menu/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/website/menu/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/website/menu/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/website/menu/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/website/menu/delete")
    public void delete() {

        renderSuccessJson();
    }

}